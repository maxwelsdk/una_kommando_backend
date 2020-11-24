package br.com.kommando.pedido.data.services;

import br.com.kommando.consumidor.data.models.Consumidor;
import br.com.kommando.consumidor.repository.ConsumidorRepository;
import br.com.kommando.exception.error.DataNotFoundException;
import br.com.kommando.item.data.models.Item;
import br.com.kommando.item.repository.ItemRepository;
import br.com.kommando.lobby.repository.LobbyRepository;
import br.com.kommando.pedido.data.models.Pedido;
import br.com.kommando.pedido.error.InvalidPedidoException;
import br.com.kommando.pedido.error.PedidoHasItemsException;
import br.com.kommando.pedido.repository.PedidoRepository;
import br.com.kommando.user.data.enums.UserRoles;
import br.com.kommando.user.data.models.User;
import br.com.kommando.user.repository.UserRepository;
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

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserRepository userRepository;

    public Pedido savePedido(Pedido pedido) {
        ArrayList<Boolean> validations = new ArrayList<>();
        validations.add(pedido.getLobbyId().isBlank());
        validations.add(pedido.getConsumidorId().isBlank());
        validations.forEach(aBoolean -> {
            if (aBoolean) throw new InvalidPedidoException("Não foi possível identificar o pedido");
        });

        if (lobbyRepository.findById(pedido.getLobbyId()).isPresent()
                && consumidorRepository.findById(pedido.getConsumidorId()).isPresent()) {
            for (String item : pedido.getItems()) {
                Item item1 = new Item();
                item1.setPedidoId(pedido.getId());
                item1.setProdutoId(item1.getProdutoId());
                Item savedItem = itemRepository.save(item1);
                pedido.getItems().add(savedItem.getId());
            }
            Pedido saved = repository.save(pedido);
            Optional<Consumidor> consumidorOptional = consumidorRepository.findById(saved.getConsumidorId());
            consumidorOptional.ifPresent(consumidor -> {
                consumidor.getPedidos().add(saved.getId());
                consumidorRepository.save(consumidor);
            });
            return saved;
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

    public List<Item> findAllByLobbyAndConsumidor(String lobbyId, String consumidorId) {
        List<Item> itens = new ArrayList<>();
        List<Pedido> pedidos = repository.findByLobbyIdAndConsumidorId(lobbyId, consumidorId);

        pedidos.forEach(pedido -> {
            itemRepository.findAllById(pedido.getItems()).forEach(itens::add);
        });

        return itens;
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

    public List<Item> itensPedidosDesconhecidos(String lobbyId) {
        List<Pedido> pedidos = repository.findByLobbyId(lobbyId);
        List<Item> itens = new ArrayList<>();
        pedidos.forEach(pedido -> {
            Optional<Consumidor> consumidorOptional = consumidorRepository.findById(pedido.getConsumidorId());
            if (consumidorOptional.isPresent()) {
                User user = userRepository.findByUid(consumidorOptional.get().getUid());
                if (user != null && user.getRole() == UserRoles.FUNCIONARIO) {
                    pedido.getItems().forEach(s -> {
                        Optional<Item> foundItem = itemRepository.findById(s);
                        foundItem.ifPresent(itens::add);
                    });
                }
            }
        });

        return itens;
    }
}
