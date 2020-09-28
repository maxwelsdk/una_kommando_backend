package br.com.kommando.pedido.error;

public class InvalidPedidoException extends RuntimeException {
    public InvalidPedidoException(String message) {
        super(message);
    }
}
