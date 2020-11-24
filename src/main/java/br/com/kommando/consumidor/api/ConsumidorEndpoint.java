package br.com.kommando.consumidor.api;

import br.com.kommando.consumidor.data.models.Consumidor;
import br.com.kommando.consumidor.data.services.ConsumidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("consumidores")
public class ConsumidorEndpoint {

    @Autowired
    private ConsumidorService service;

    @PostMapping
    public ResponseEntity<HashMap<String, Object>> adicionarConsumidor(@RequestBody Consumidor consumidor) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("consumidor", service.saveConsumidor(consumidor));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{consumidorId}")
    public ResponseEntity<HashMap<String, Object>> findConsumidor(@PathVariable String consumidorId) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("consumidor", service.find(consumidorId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteConsumidor(@PathVariable String id) {
        HashMap<String, Object> response = new HashMap<>();
        service.deleteById(id);
        response.put("msg", "Consumidor removido com sucesso!");
        response.put("id", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
