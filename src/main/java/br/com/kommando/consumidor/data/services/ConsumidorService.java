package br.com.kommando.consumidor.data.services;

import br.com.kommando.consumidor.data.models.Consumidor;
import br.com.kommando.consumidor.error.NotValidConsumerException;
import br.com.kommando.consumidor.repository.ConsumidorRepository;
import br.com.kommando.pedido.data.models.Pedido;
import br.com.kommando.pedido.repository.PedidoRepository;
import br.com.kommando.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumidorService {

    @Autowired
    private ConsumidorRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Consumidor saveConsumidor(Consumidor consumidor) {
        if (repository.findOne(Example.of(consumidor)).isPresent()) {
            throw new NotValidConsumerException("Consumidor existente");
        }
        if (userRepository.existsByUid(consumidor.getUid())) {
            return repository.save(consumidor);
        }
        return null;
    }

    public List<Consumidor> findAllConsumidores(String id) {
        List<Consumidor> consumidores = repository.findAll(Example.of(new Consumidor(id)));
        consumidores.forEach(consumidor -> {
                    Pedido pedido = new Pedido(consumidor.getLobbyId(), consumidor.getId());
                    List<Pedido> pedidos = pedidoRepository.findAll(Example.of(pedido));
                    for (Pedido p : pedidos) {
                        consumidor.getPedidos().add(p.getId());
                    }
                }
        );
        return consumidores;
    }
}
