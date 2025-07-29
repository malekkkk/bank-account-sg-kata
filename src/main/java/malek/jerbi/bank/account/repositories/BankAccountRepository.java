package malek.jerbi.bank.account.repositories;

import malek.jerbi.bank.account.entities.BankAccountEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BankAccountRepository {
    // data base mock
    private final Map<Integer, BankAccountEntity> bankAccountDbMock = new HashMap<Integer, BankAccountEntity>();

    public BankAccountRepository() {
        this.bankAccountDbMock.put(1, new BankAccountEntity(1, 500, new ArrayList<>()));
        this.bankAccountDbMock.put(2, new BankAccountEntity(2, 1200, new ArrayList<>()));
    }

    public List<BankAccountEntity> findAll() {
        return bankAccountDbMock.values().stream().toList();
    }

    public BankAccountEntity findById(int bankAccountId) {
        return bankAccountDbMock.get(bankAccountId);
    }

    public void save(BankAccountEntity bankAccount) {
        bankAccountDbMock.put(bankAccount.getBankAccountId(), bankAccount);
    }
}
