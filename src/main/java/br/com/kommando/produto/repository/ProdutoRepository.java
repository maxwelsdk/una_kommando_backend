package br.com.kommando.produto.repository;

import br.com.kommando.produto.data.models.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProdutoRepository extends MongoRepository<Produto, String> {
}
