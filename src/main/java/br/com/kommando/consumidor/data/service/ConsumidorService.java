package br.com.kommando.consumidor.data.service;

import br.com.kommando.consumidor.data.models.Consumidor;
import br.com.kommando.consumidor.error.NotValidConsumerException;
import br.com.kommando.consumidor.repository.ConsumidorRepository;
import br.com.kommando.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class ConsumidorService {

    @Autowired
    private ConsumidorRepository repository;

    @Autowired
    private UserRepository userRepository;

    public Consumidor saveConsumidor(Consumidor consumidor) {
        if (repository.findOne(Example.of(consumidor)).isPresent()) {
            throw new NotValidConsumerException("Consumidor existente");
        }
        if (userRepository.existsByUid(consumidor.getUid())) {
            return repository.save(consumidor);
        }
        return null;
    }

}
