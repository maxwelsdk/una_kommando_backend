package br.com.kommando.lobby.data.services;

import br.com.kommando.exception.error.DataNotFoundException;
import br.com.kommando.lobby.data.models.Lobby;
import br.com.kommando.lobby.repository.LobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void deleteById(String id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new DataNotFoundException("Lobby não encontrada");
        }
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
