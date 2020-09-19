package br.com.kommando.consumidor.repository;

import br.com.kommando.consumidor.data.models.Consumidor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConsumidorRepository extends MongoRepository<Consumidor, String> {
}
