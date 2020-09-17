package br.com.kommando.lobby.data.services;

import br.com.kommando.lobby.data.models.Lobby;
import br.com.kommando.lobby.repository.LobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LobbyService {

    @Autowired
    private LobbyRepository repository;

    public Lobby saveLobby(Lobby lobby) {
        return repository.save(lobby);
    }

}
