package br.com.kommando.consumidor.api;

import br.com.kommando.consumidor.data.models.Consumidor;
import br.com.kommando.consumidor.data.service.ConsumidorService;
import br.com.kommando.consumidor.error.NotValidConsumerException;
import br.com.kommando.lobby.error.LobbyNotFoundException;
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
        Consumidor consumidorResult = service.saveConsumidor(consumidor);
        if (consumidorResult == null) throw new NotValidConsumerException("Usuário inválido ou não existe");
        response.put("consumidor", consumidorResult);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
