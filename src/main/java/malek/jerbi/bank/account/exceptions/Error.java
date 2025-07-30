package malek.jerbi.bank.account.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Map;

public record Error(HttpStatus status, String message, Map<String, String> errors) {
}
