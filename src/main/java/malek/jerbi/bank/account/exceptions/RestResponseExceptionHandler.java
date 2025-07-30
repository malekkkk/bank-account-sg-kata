package malek.jerbi.bank.account.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.util.Collections.emptyMap;

@ControllerAdvice
@Slf4j
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BankAccountException.class, })
    public ResponseEntity<Error> handleCityNotFoundException(BankAccountException ex, WebRequest request) {
        log.error("Error : {} for {}", ex.getMessage(), request.getDescription(false));
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(buildResponse(ex.getHttpStatus(), ex.getMessage()));
    }

    private Error buildResponse(HttpStatus status, String message) {
        return new Error(status, message, emptyMap());
    }
}
