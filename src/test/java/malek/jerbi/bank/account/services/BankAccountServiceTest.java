package malek.jerbi.bank.account.services;

import malek.jerbi.bank.account.entities.BankAccountEntity;
import malek.jerbi.bank.account.exceptions.BankAccountException;
import malek.jerbi.bank.account.repositories.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BankAccountServiceTest {

    private BankAccountRepository mockRepository;
    private BankAccountService bankAccountService;

    @BeforeEach
    void setUp() {
        mockRepository = mock(BankAccountRepository.class);
        bankAccountService = new BankAccountService(mockRepository);
    }

    @Test
    void testDeposit_shouldIncreaseBalanceAndAddStatement() {
        BankAccountEntity account = new BankAccountEntity(1, 500f, new ArrayList<>());
        when(mockRepository.findById(1)).thenReturn(account);

        String result = bankAccountService.deposit(1, 200f);

        assertTrue(result.contains("Deposit"));
        assertEquals(700f, account.getBalance());
        assertEquals(1, account.getStatements().size());
        verify(mockRepository).save(account);
    }

    @Test
    void testWithdraw_shouldDecreaseBalanceAndAddStatement() {
        BankAccountEntity account = new BankAccountEntity(2, 1000f, new ArrayList<>());
        when(mockRepository.findById(2)).thenReturn(account);

        String result = bankAccountService.withdraw(2, 400f);

        assertTrue(result.contains("Withdraw"));
        assertEquals(600f, account.getBalance());
        assertEquals(1, account.getStatements().size());
        verify(mockRepository).save(account);
    }

    @Test
    void testWithdraw_shouldThrowBankAccountException_WhenInsufficientFunds() {
        BankAccountEntity account = new BankAccountEntity(3, 100f, new ArrayList<>());
        when(mockRepository.findById(3)).thenReturn(account);

        BankAccountException ex = assertThrows(BankAccountException.class, () -> {
            bankAccountService.withdraw(3, 200f);
        });

        assertTrue(ex.getMessage().contains("Insufficient Fund Exception"));
        verify(mockRepository, never()).save(account);
    }

    @Test
    void testGetStatements_returnsCorrectStatements() {
        List<String> statements = List.of("Deposit 100", "Withdraw 50");
        BankAccountEntity account = new BankAccountEntity(4, 500f, new ArrayList<>(statements));
        when(mockRepository.findById(4)).thenReturn(account);

        List<String> result = bankAccountService.getStatements(4);

        assertEquals(2, result.size());
        assertEquals("Deposit 100", result.get(0));
    }

    @Test
    void testGetStatements_shouldThrowBankAccountException_WhenAccountNotFound() {
        when(mockRepository.findById(999)).thenReturn(null);

        BankAccountException ex = assertThrows(BankAccountException.class, () -> {
            bankAccountService.getStatements(999);
        });

        assertTrue(ex.getMessage().contains("Account Not Found Exception"));
    }
}
