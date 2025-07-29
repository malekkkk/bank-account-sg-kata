package malek.jerbi.bank.account.repositories;

import malek.jerbi.bank.account.entities.BankAccountEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountRepositoryTest {

    private BankAccountRepository repository;

    @BeforeEach
    void setUp() {
        repository = new BankAccountRepository(); // uses default constructor with 2 mock accounts
    }

    @Test
    void testFindAll_returnsAllAccounts() {
        List<BankAccountEntity> accounts = repository.findAll();
        assertEquals(2, accounts.size(), "Should return 2 predefined accounts");
    }

    @Test
    void testFindById_returnsCorrectAccount() {
        BankAccountEntity account = repository.findById(1);
        assertNotNull(account);
        assertEquals(1, account.getBankAccountId());
        assertEquals(500f, account.getBalance());
    }

    @Test
    void testFindById_returnsNullIfNotFound() {
        BankAccountEntity account = repository.findById(999);
        assertNull(account, "Should return null for non-existent account");
    }

    @Test
    void testSave_addsNewAccount() {
        BankAccountEntity newAccount = new BankAccountEntity(3, 300f, List.of("Initial deposit"));
        repository.save(newAccount);

        BankAccountEntity saved = repository.findById(3);
        assertNotNull(saved);
        assertEquals(300f, saved.getBalance());
        assertEquals(1, saved.getStatements().size());
    }

    @Test
    void testSave_updatesExistingAccount() {
        BankAccountEntity updated = new BankAccountEntity(1, 999f, List.of("Updated"));
        repository.save(updated);

        BankAccountEntity saved = repository.findById(1);
        assertEquals(999f, saved.getBalance());
        assertEquals("Updated", saved.getStatements().get(0));
    }
}

