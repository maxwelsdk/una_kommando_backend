package br.com.kommando.categoria.repository;

import br.com.kommando.categoria.data.models.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoriaRepository extends MongoRepository<Categoria, String> {
}
