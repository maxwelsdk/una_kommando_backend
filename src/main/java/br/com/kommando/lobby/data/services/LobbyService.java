package br.com.kommando.lobby.data.services;

import br.com.kommando.lobby.data.models.Lobby;
import br.com.kommando.lobby.repository.LobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LobbyService {

    @Autowired
    private LobbyRepository repository;

    public Lobby saveLobby(Lobby lobby) {
        return repository.save(lobby);
    }

    @Transactional(readOnly = true)
    public List<Lobby> findAll() {
        return repository.findAll();
    }

    public Optional<Lobby> findById(String id) {
        return repository.findById(id);
    }

    public Object deleteById(String id) {
        repository.deleteById(id);
        return null;
    }

    public Lobby updateLobby(Lobby lobby) {
        final Optional<Lobby> foundLobby = repository.findById(lobby.getId());
        if (foundLobby.isPresent()) {
            repository.deleteById(lobby.getId());
            return saveLobby(lobby);
        }
        return null;
    }

}
