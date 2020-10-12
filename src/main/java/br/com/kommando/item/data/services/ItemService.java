package br.com.kommando.item.data.services;

import br.com.kommando.item.data.models.Item;
import br.com.kommando.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    public List<Item> findAll() {
        return repository.findAll();
    }

    public Item save(Item item) {
        return repository.save(item);
    }

    public void removeById(String id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    public Item updateItem(Item item) {
        if (repository.findById(item.getId()).isPresent()) {
            removeById(item.getId());
            return save(item);
        }
        return null;
    }
}
