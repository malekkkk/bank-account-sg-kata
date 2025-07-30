package malek.jerbi.bank.account.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BankAccountException extends RuntimeException {

    private final HttpStatus httpStatus;

    public BankAccountException(String message) {
        super(message);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public BankAccountException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
