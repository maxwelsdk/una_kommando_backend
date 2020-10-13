package br.com.kommando.produto.data.services;

import br.com.kommando.produto.data.models.Produto;
import br.com.kommando.produto.error.ProdutoNotFoundException;
import br.com.kommando.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServices {

    @Autowired
    ProdutoRepository repository;

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public Optional<Produto> findOneById(String id) {
        return repository.findById(id);
    }

    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    public void deleteById(String id) {
        try {
            if (repository.findById(id).isPresent()) {
                repository.deleteById(id);
            } else {
                throw new ProdutoNotFoundException("Produto não encontrado");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Produto update(Produto produto) {
        Optional<Produto> produtoOptional = repository.findById(produto.getId());
        if (produtoOptional.isPresent()) {
            repository.deleteById(produto.getId());
            return repository.save(produto);
        } else {
            throw new ProdutoNotFoundException("Produto não encontrado");
        }

    }

}
