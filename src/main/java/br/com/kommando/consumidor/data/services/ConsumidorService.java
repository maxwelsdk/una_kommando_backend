package br.com.kommando.consumidor.data.services;

import br.com.kommando.consumidor.data.models.Consumidor;
import br.com.kommando.consumidor.error.NotValidConsumerException;
import br.com.kommando.consumidor.repository.ConsumidorRepository;
import br.com.kommando.exception.error.DataFoundException;
import br.com.kommando.exception.error.DataNotFoundException;
import br.com.kommando.lobby.data.models.Lobby;
import br.com.kommando.lobby.repository.LobbyRepository;
import br.com.kommando.pedido.data.models.Pedido;
import br.com.kommando.pedido.error.PedidoHasItemsException;
import br.com.kommando.pedido.repository.PedidoRepository;
import br.com.kommando.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumidorService {

    @Autowired
    private ConsumidorRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private LobbyRepository lobbyRepository;

    public Consumidor saveConsumidor(Consumidor consumidor) {
//        if (repository.findOne(Example.of(consumidor)).isPresent()) {
//            throw new NotValidConsumerException("Existe um consumidor com este id conectado");
//        }
        if (userRepository.existsByUid(consumidor.getUid())) {
            Optional<Lobby> lobby = lobbyRepository.findById(consumidor.getLobbyId());
            if (lobby.isPresent()) {
                Consumidor save = repository.save(consumidor);
                List<String> consumidorList = lobby.get().getConsumidores();
                consumidorList.add(save.getId());
                lobbyRepository.save(lobby.get());
                return save;
            } else {
                throw new DataFoundException("Lobby não encontrada");
            }
        } else {
            throw new NotValidConsumerException("Usuário inválido");
        }
    }

    public List<Consumidor> findAllConsumidores(String lobbyId) {
        List<Consumidor> consumidores = repository.findAll(Example.of(new Consumidor(lobbyId)));
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

    public void deleteById(String id) {
        final Optional<Consumidor> foundConsumidor = repository.findById(id);
        if (foundConsumidor.isPresent()) {
            Optional<Lobby> lobby = lobbyRepository.findById(foundConsumidor.get().getLobbyId());
            if (lobby.isPresent()) {
                lobby.get().getConsumidores().remove(foundConsumidor.get().getId());
                lobbyRepository.save(lobby.get());
            }
            if (foundConsumidor.get().getPedidos().isEmpty()) {
                repository.deleteById(id);
            } else {
                throw new PedidoHasItemsException("Há pedidos para este consumidor, não pode ser excluído");
            }
        } else {
            throw new DataNotFoundException("Consumidr não existe");
        }
    }
}
