package br.com.kommando.pedido.data.services;

import br.com.kommando.consumidor.repository.ConsumidorRepository;
import br.com.kommando.exception.error.DataNotFoundException;
import br.com.kommando.lobby.repository.LobbyRepository;
import br.com.kommando.pedido.data.models.Pedido;
import br.com.kommando.pedido.error.InvalidPedidoException;
import br.com.kommando.pedido.error.PedidoHasItemsException;
import br.com.kommando.pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PedidoServices {

    @Autowired
    PedidoRepository repository;

    @Autowired
    ConsumidorRepository consumidorRepository;

    @Autowired
    LobbyRepository lobbyRepository;

    public Pedido savePedido(Pedido pedido) {
        ArrayList<Boolean> validations = new ArrayList<>();
        validations.add(pedido.getLobbyId().isBlank());
        validations.add(pedido.getConsumidorId().isBlank());
        validations.forEach(aBoolean -> {
            if (aBoolean) throw new InvalidPedidoException("Não foi possível identificar o pedido");
        });

        if (lobbyRepository.findById(pedido.getLobbyId()).isPresent()
                && consumidorRepository.findById(pedido.getConsumidorId()).isPresent()) {

            return repository.save(pedido);
        } else {
            throw new DataNotFoundException("Lobby ou consumidor inválido");
        }

    }

    public List<Pedido> findAll() {
        return repository.findAll();
    }

    public Optional<Pedido> findById(String id) {
        return repository.findById(id);
    }

    public void deleteById(String id) {
        final Optional<Pedido> foundPedido = repository.findById(id);
        if (foundPedido.isPresent() && foundPedido.get().getItems().isEmpty()) {
            repository.deleteById(id);
        } else {
            throw new PedidoHasItemsException("Pedido possui itens, não pode ser excluído");
        }
    }

    public Pedido updatePedido(Pedido pedido) {
        final Optional<Pedido> foundPedido = repository.findById(pedido.getId());
        if (foundPedido.isPresent()) {
            repository.deleteById(pedido.getId());
            return repository.save(pedido);
        } else {
            throw new DataNotFoundException("Pedido não encontrado");
        }
    }
}
