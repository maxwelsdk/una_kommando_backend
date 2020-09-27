package br.com.kommando.user.error;

public class InvalidUidException extends RuntimeException {
    public InvalidUidException(String message) {
        super(message);
    }
}
