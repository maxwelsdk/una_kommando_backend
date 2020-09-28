package br.com.kommando.pedido.error;

public class PedidoHasItemsException extends RuntimeException{
    public PedidoHasItemsException(String message) {
        super(message);
    }
}
