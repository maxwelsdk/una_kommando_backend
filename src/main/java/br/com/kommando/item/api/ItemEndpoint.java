package br.com.kommando.item.api;

import br.com.kommando.item.data.models.Item;
import br.com.kommando.item.data.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("itens")
public class ItemEndpoint {

    @Autowired
    private ItemService service;

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> getAll() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("itens", service.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HashMap<String, Object>> newItem(@RequestBody Item item) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("msg", "item criado com sucesso!");
        response.put("item", service.save(item));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<HashMap<String, Object>> deleteItem(Item item) {
        HashMap<String, Object> response = new HashMap<>();
        service.removeById(item.getId());
        response.put("item deletado", item.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<HashMap<String, Object>> updateItem(@RequestBody Item item) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("item", service.updateItem(item));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
