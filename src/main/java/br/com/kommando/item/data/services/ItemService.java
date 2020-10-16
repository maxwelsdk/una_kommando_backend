package br.com.kommando.item.data.services;

import br.com.kommando.exception.error.DataNotFoundException;
import br.com.kommando.item.data.models.Item;
import br.com.kommando.item.repository.ItemRepository;
import br.com.kommando.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Item> findAll() {
        return repository.findAll();
    }

    public Item save(Item item) {
        if (produtoRepository.findById(item.getProdutoId()).isPresent()) {
            return repository.save(item);
        } else {
            throw new DataNotFoundException("Produto não encontrado");
        }
    }

    public Optional<Item> findById(String id) {
        return repository.findById(id);
    }

    public void removeById(String id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
