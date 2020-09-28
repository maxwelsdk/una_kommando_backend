package br.com.kommando.pedido.repository;

import br.com.kommando.pedido.data.models.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoRepository extends MongoRepository<Pedido ,String> {
}
