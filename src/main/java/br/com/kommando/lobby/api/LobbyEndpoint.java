package br.com.kommando.lobby.api;

import br.com.kommando.lobby.data.models.Lobby;
import br.com.kommando.lobby.data.services.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(name = "lobbies")
public class LobbyEndpoint {

    @Autowired
    private LobbyService lobbyServices;

    @PostMapping
    public ResponseEntity<HashMap<String, Object>> newLobby(@RequestBody Lobby lobby) {
        final HashMap<String, Object> response = new HashMap<>();
        final Lobby savedLobby = lobbyServices.saveLobby(lobby);
        if (savedLobby != null) {
            response.put("msg", "Lobby criadas com successo");
            response.put("lobby", savedLobby);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        response.put("msg", "Falha ao salvar a lobby");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
