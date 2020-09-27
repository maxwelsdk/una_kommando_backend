package br.com.kommando.consumidor.error;

public class NotValidConsumerException extends RuntimeException {
    public NotValidConsumerException(String message) {
        super(message);
    }
}
