package br.com.kommando.lobby.api;

import br.com.kommando.lobby.data.models.Lobby;
import br.com.kommando.lobby.data.services.LobbyService;
import br.com.kommando.lobby.error.LobbyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

@RestController
@RequestMapping("lobbies")
public class LobbyEndpoint {

    @Autowired
    private LobbyService lobbyServices;

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> getLobbies() {
        HashMap<String, Object> response = new HashMap<>();
        final List<Lobby> lobbies = lobbyServices.findAll();
        response.put("lobbies", Objects.requireNonNullElseGet(lobbies, (Supplier<List<Lobby>>) ArrayList::new));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<HashMap<String, Object>> getLobby(@PathVariable String id) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("lobby", lobbyServices.findById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HashMap<String, Object>> deleteLobby(@PathVariable String id) {
        lobbyServices.deleteById(id);
        HashMap<String, Object> response = new HashMap<>();
        response.put("msg", "Lobby deletada com sucesso!");
        response.put("id", id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<HashMap<String, Object>> updateLobby(@RequestBody Lobby lobby) {
        HashMap<String, Object> response = new HashMap<>();
        final Lobby update = lobbyServices.updateLobby(lobby);
        if (update == null) throw new LobbyNotFoundException("Lobby inexistente");
        response.put("lobby", update);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HashMap<String, Object>> newLobby(@RequestBody Lobby lobby) {
        final HashMap<String, Object> response = new HashMap<>();
        final Lobby savedLobby = lobbyServices.saveLobby(lobby);
        if (savedLobby != null) {
            response.put("msg", "Lobby criada com successo");
            response.put("lobby", savedLobby);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        response.put("msg", "Falha ao salvar a lobby");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
