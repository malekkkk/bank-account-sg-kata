package malek.jerbi.bank.account.services;

import malek.jerbi.bank.account.entities.BankAccountEntity;
import malek.jerbi.bank.account.exceptions.AccountNotFoundException;
import malek.jerbi.bank.account.exceptions.InsufficientFundException;
import malek.jerbi.bank.account.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public String deposit(int bankAccountId, float amount) {
        BankAccountEntity bankAccount = this.getBankAccountById(bankAccountId);
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        String statement = LocalDate.now() + " | Deposit | " + amount + " | Balance: " + bankAccount.getBalance();
        bankAccount.getStatements().add(statement);
        this.bankAccountRepository.save(bankAccount);

        return statement;
    }

    public String withdraw(int bankAccountId, float amount ) {
        BankAccountEntity bankAccount = this.getBankAccountById(bankAccountId);
        if (bankAccount.getBalance() < amount) {
            throw new InsufficientFundException(bankAccount.getBalance());
        } else {
            bankAccount.setBalance(bankAccount.getBalance() - amount);
            String statement = LocalDate.now() + " | Withdraw | " + amount + " | Balance: " + bankAccount.getBalance();
            bankAccount.getStatements().add(statement);
            this.bankAccountRepository.save(bankAccount);

            return statement;
        }
    }

    public List<String> getStatements(int bankAccountId) {
        return this.getBankAccountById(bankAccountId).getStatements();
    }

    private BankAccountEntity getBankAccountById(int bankAccountId) {
        BankAccountEntity bankAccount = this.bankAccountRepository.findById(bankAccountId);

        if (isNull(bankAccount)) {
            throw new AccountNotFoundException();
        } else {
            return bankAccount;
        }
    }
}
