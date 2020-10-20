package br.com.kommando.lobby.data.services;

import br.com.kommando.exception.error.DataFoundException;
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

    public Lobby findById(String id) {
        Optional<Lobby> lobbyOptional = repository.findById(id);
        if (lobbyOptional.isPresent()) {
            return lobbyOptional.get();
        } else {
            throw new DataNotFoundException("Lobby não encontrada");
        }
    }

    public void deleteById(String id) {
        Optional<Lobby> lobbyOptional = repository.findById(id);
        if (lobbyOptional.isPresent()) {
            if (lobbyOptional.get().getConsumidorList().isEmpty()) {
                repository.deleteById(id);
            } else {
                throw new DataFoundException("Lobby possui consumidores, não poderá ser deletada");
            }
        } else {
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
