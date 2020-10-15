package br.com.kommando.lobby.api;

import br.com.kommando.lobby.data.enums.LobbyStatus;
import br.com.kommando.lobby.data.models.Lobby;
import br.com.kommando.lobby.data.services.LobbyService;
import br.com.kommando.lobby.repository.LobbyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LobbyEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private LobbyService service;

    @Mock
    private LobbyRepository repository;


    @Test
    void getLobbies() throws Exception {
        ArrayList<Lobby> lobbies = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            lobbies.add(new Lobby());
        }
        Mockito.when(service.findAll()).thenReturn(lobbies);
        mockMvc.perform(get("/lobbies")).andExpect(status().isOk());
    }

    @Test
    void getLobby() throws Exception {
        Mockito.when(service.findById("0123456789"))
                .thenReturn(java.util.Optional.of(new Lobby("0123456789", "lobby teste", LobbyStatus.ABERTA)));
        mockMvc.perform(get("/lobbies/0123456789")).andExpect(status().isOk());
    }

    @Test
    void deleteLobby() throws Exception {
        mockMvc.perform(delete("/lobbies/0123456789")).andExpect(status().isOk());
    }

    @Test
    void updateLobby() {
    }

    @Test
    void newLobby() throws Exception {
        Lobby requestedLobby = new Lobby("lobby teste");
        Lobby responseLobby = new Lobby("lobby teste");
        responseLobby.setId("0123456789");

        Mockito.when(service.saveLobby(requestedLobby)).thenReturn(responseLobby);
        Mockito.when(repository.save(requestedLobby)).thenReturn(responseLobby);
        mockMvc.perform(MockMvcRequestBuilders.post("/lobbies")
                .contentType("application/json")
                .content(new ObjectMapper()
                        .writeValueAsString(responseLobby)))
                .andExpect(status().isCreated());
    }
}