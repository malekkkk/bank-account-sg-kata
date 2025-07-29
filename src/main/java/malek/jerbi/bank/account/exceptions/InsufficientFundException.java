package malek.jerbi.bank.account.exceptions;

public class InsufficientFundException extends RuntimeException {
    public InsufficientFundException(float balance) {
        super("Insufficient Fund Exception, current balance: " + balance);
    }
}
