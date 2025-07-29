package malek.jerbi.bank.account.controllers;

import malek.jerbi.bank.account.services.BankAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BankAccountControllerTest {

    private BankAccountService mockService;
    private BankAccountController controller;

    @BeforeEach
    void setUp() {
        mockService = mock(BankAccountService.class);
        controller = new BankAccountController(mockService);
    }

    @Test
    void testDeposit_returnsServiceResult() {
        when(mockService.deposit(1, 100)).thenReturn("Deposited 100");

        String result = controller.deposit(1, 100);

        assertEquals("Deposited 100", result);
        verify(mockService).deposit(1, 100);
    }

    @Test
    void testWithdraw_returnsServiceResult() {
        when(mockService.withdraw(1, 50)).thenReturn("Withdrawn 50");

        String result = controller.withdraw(1, 50);

        assertEquals("Withdrawn 50", result);
        verify(mockService).withdraw(1, 50);
    }

    @Test
    void testStatement_returnsListFromService() {
        List<String> expectedStatements = List.of("Deposit 100", "Withdraw 50");
        when(mockService.getStatements(1)).thenReturn(expectedStatements);

        List<String> result = controller.statement(1);

        assertEquals(expectedStatements, result);
        verify(mockService).getStatements(1);
    }
}
