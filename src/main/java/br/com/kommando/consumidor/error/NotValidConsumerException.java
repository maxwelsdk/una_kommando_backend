package br.com.kommando.consumidor.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotValidConsumerException extends RuntimeException {
    public NotValidConsumerException(String message) {
        super(message);
    }
}
