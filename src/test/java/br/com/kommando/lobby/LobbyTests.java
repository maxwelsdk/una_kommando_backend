package br.com.kommando.lobby;

import br.com.kommando.consumidor.data.models.Consumidor;
import br.com.kommando.item.data.models.Item;
import br.com.kommando.lobby.data.enums.LobbyStatus;
import br.com.kommando.lobby.data.models.Lobby;
import br.com.kommando.pedido.data.models.Pedido;
import br.com.kommando.produto.data.models.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@SpringBootTest
public class LobbyTests {

    @Test
    @DisplayName("Lobby")
    void lobbyDevePossuirStatusEConsumidor() {
        Lobby lobby = new Lobby();
        List<Consumidor> consumidorList = new ArrayList<>();
        consumidorList.add(new Consumidor());
        lobby.setConsumidorList(consumidorList);
        Assertions.assertNotNull(lobby.getConsumidorList());
        Assertions.assertNotNull(lobby.getLobbyStatus());
    }

    @Test
    @DisplayName("Total dos consumidores na lobby")
    void deveCalcularTotalDaLobby() {
        Lobby lobby = new Lobby();

        Produto produto = new Produto();
        produto.setCodigo(1);
        produto.setDescricao("Produto aleatório");
        produto.setPreco(50.00);

        List<Item> itens = new ArrayList<>();
        Item item = new Item();

        item.setId("1");
        item.setProduto(produto);
        item.setQuantidade(2);
        itens.add(item);

        List<Pedido> pedidos = new ArrayList<>();
        Pedido pedido = new Pedido();
        Pedido pedido2 = new Pedido();

        pedido.setItems(itens);
        pedido2.setItems(itens);
        
        pedidos.add(pedido);
        pedidos.add(pedido2);

        List<Consumidor> consumidorList = new ArrayList<>();
        Consumidor consumidor = new Consumidor();
        Consumidor consumidor2 = new Consumidor();


        consumidor.setPedidos(pedidos);
        consumidor2.setPedidos(pedidos);

        consumidorList.add(consumidor);
        consumidorList.add(consumidor2);

        lobby.setId("1");
        lobby.setConsumidorList(consumidorList);
        lobby.setLobbyStatus(LobbyStatus.OCUPADA);
        AtomicReference<Double> totalConsumido = new AtomicReference<>();

        for(Consumidor c : lobby.getConsumidorList()) {
            c.getPedidos().forEach(
                    p1 -> {
                        p1.getItems().forEach(
                                item1 -> {
                                    totalConsumido.set(item1.getProduto().getPreco() * item1.getQuantidade());
                                }
                        );
                    }
            );
        }
        Assertions.assertEquals(100.0, totalConsumido.getOpaque());
    }

}
