package malek.jerbi.bank.account.exceptions;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException() {
        super("Account Not Found Exception");
    }
}
