package br.com.kommando.user.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUidException extends RuntimeException {
    public InvalidUidException(String message) {
        super(message);
    }
}
