package br.com.kommando.pedido.repository;

import br.com.kommando.pedido.data.models.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PedidoRepository extends MongoRepository<Pedido ,String> {
    public List<Pedido> findByLobbyIdAndConsumidorId(String lobbyId, String consumidorId);
    public List<Pedido> findByLobbyId(String lobbyId);
}
