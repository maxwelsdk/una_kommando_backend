package br.com.kommando.lobby.repository;

import br.com.kommando.lobby.data.models.Lobby;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LobbyRepository extends MongoRepository<Lobby, String> {
}
