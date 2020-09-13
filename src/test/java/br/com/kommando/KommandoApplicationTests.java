package br.com.kommando;

import br.com.kommando.consumidor.data.models.Consumidor;
import br.com.kommando.lobby.data.models.Lobby;
import br.com.kommando.pedido.data.models.Pedido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class KommandoApplicationTests {


    @Test
    void consumidorDevePossuirListaPedido() {
        Lobby lobby = new Lobby();
        List<Consumidor> consumidorList = new ArrayList<>();
        List<Pedido> pedidosList = new ArrayList<>();
        Consumidor consumidor = new Consumidor();

        consumidor.setLobbyId(lobby.getId());
        consumidor.setPedidos(pedidosList);
        pedidosList.add(new Pedido());
        consumidorList.add(consumidor);
        lobby.setConsumidorList(consumidorList);

        Assertions.assertNotNull(consumidor.getPedidos());
    }


}
