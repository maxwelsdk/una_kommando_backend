package br.com.kommando.item.repository;

import br.com.kommando.item.data.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
}
