package br.com.kommando.exception;

import br.com.kommando.consumidor.data.models.Consumidor;
import br.com.kommando.consumidor.error.NotValidConsumerException;
import br.com.kommando.lobby.data.models.Lobby;
import br.com.kommando.lobby.error.LobbyNotFoundException;
import br.com.kommando.pedido.data.models.Pedido;
import br.com.kommando.pedido.error.InvalidPedidoException;
import br.com.kommando.pedido.error.PedidoHasItemsException;
import br.com.kommando.user.data.models.User;
import br.com.kommando.user.error.InvalidUidException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LobbyNotFoundException.class)
    public ResponseEntity<Object> handleLobbyNotFoundException(LobbyNotFoundException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), status.value(), status.name(), Lobby.class.getName(), null);
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(PedidoHasItemsException.class)
    public ResponseEntity<Object> handlePedidoHasItemsException(PedidoHasItemsException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), status.value(), status.name(), Pedido.class.getName(), null);
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(InvalidPedidoException.class)
    public ResponseEntity<Object> handleInvalidPedidoException(InvalidPedidoException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), status.value(), status.name(), Pedido.class.getName(), null);
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(InvalidUidException.class)
    public ResponseEntity<Object> handleInvalidUidException(InvalidUidException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), status.value(), status.name(), User.class.getName(), null);
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(NotValidConsumerException.class)
    public ResponseEntity<Object> handleNotValidConsumerException(NotValidConsumerException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), status.value(), status.name(), Consumidor.class.getName(), null);
        return new ResponseEntity<>(errorResponse, status);
    }

    @Override
    protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(@NotNull MethodArgumentNotValidException ex, @NotNull HttpHeaders headers, @NotNull HttpStatus status, @NotNull WebRequest request) {
        List<Error> errors = getErrors(ex);
        ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
        return new ResponseEntity<>(errorResponse, status);
    }

    private List<Error> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new Error(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
                .collect(Collectors.toList());
    }

    private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status, List<Error> errors) {
        return new ErrorResponse("Requisição possui campos inválidos", status.value(),
                status.getReasonPhrase(), ex.getBindingResult().getObjectName(), errors);
    }


}
