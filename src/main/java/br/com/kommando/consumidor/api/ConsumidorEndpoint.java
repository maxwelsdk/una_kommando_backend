package br.com.kommando.consumidor.api;

import br.com.kommando.consumidor.data.models.Consumidor;
import br.com.kommando.consumidor.data.services.ConsumidorService;
import br.com.kommando.consumidor.error.NotValidConsumerException;
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

    @GetMapping(path = "/{lobbyId}")
    public ResponseEntity<HashMap<String, Object>> buscarConsumidores(@PathVariable String lobbyId) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("consumidores", service.findAllConsumidores(lobbyId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
