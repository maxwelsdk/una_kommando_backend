package br.com.kommando.consumidor.data.service;

import br.com.kommando.consumidor.data.models.Consumidor;
import br.com.kommando.consumidor.repository.ConsumidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumidorService {

    @Autowired
    private ConsumidorRepository repository;

    public Consumidor saveConsumidor(Consumidor consumidor) {
        return repository.save(consumidor);
    }

}
