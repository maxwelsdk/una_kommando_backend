package br.com.kommando.lobby.data.services;

import br.com.kommando.consumidor.repository.ConsumidorRepository;
import br.com.kommando.exception.error.DataFoundException;
import br.com.kommando.item.data.models.Item;
import br.com.kommando.item.repository.ItemRepository;
import br.com.kommando.lobby.data.models.Lobby;
import br.com.kommando.lobby.repository.LobbyRepository;
import br.com.kommando.pedido.data.models.Pedido;
import br.com.kommando.pedido.repository.PedidoRepository;
import br.com.kommando.produto.data.models.Produto;
import br.com.kommando.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class LobbyFinanceiroService {

    @Autowired
    LobbyRepository lobbyRepository;

    @Autowired
    ConsumidorRepository consumidorRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ItemRepository itemRepository;

    public double getTotalAcumulado(String lobbyId) {
        Optional<Lobby> lobby = lobbyRepository.findById(lobbyId);

        if (lobby.isEmpty()) throw new DataFoundException("Lobby não encontrada");

        List<String> pedidosId = new ArrayList<>();
        consumidorRepository.findAllById(lobby.get().getConsumidores()).forEach(
                consumidor ->
                        pedidosId.addAll(consumidor.getPedidos())
        );

        List<String> itensId = new ArrayList<>();
        pedidosId.forEach(
                s -> {
                    Optional<Pedido> pedido = pedidoRepository.findById(s);
                    pedido.ifPresent(pedido1 -> itensId.addAll(pedido1.getItems()));
                }
        );

        AtomicReference<Double> current = new AtomicReference<>(0.0);
        itensId.forEach(
                itemId -> {
                    itemRepository.findById(itemId).ifPresent(
                            item -> {
                                Optional<Produto> produto = produtoRepository.findById(item.getProdutoId());
                                produto.ifPresent(p -> current.updateAndGet(v -> v + p.getPreco() * item.getQuantidade()));
                            }
                    );

                }
        );
        return current.get();
    }
}
