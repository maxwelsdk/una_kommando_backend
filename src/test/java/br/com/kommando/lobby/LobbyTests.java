package br.com.kommando.lobby;

import br.com.kommando.lobby.data.models.Lobby;
import br.com.kommando.lobby.data.services.LobbyService;
import br.com.kommando.lobby.repository.LobbyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LobbyTests {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private LobbyService service;

    @Mock
    private LobbyRepository repository;


    static ArrayList<Lobby> lobbies = new ArrayList<>();

    @BeforeAll
    static void setUpAll() {
        for (int i = 0; i < 5; i++) {
            lobbies.add(new Lobby());
        }
    }

    @Test
    void shouldReturnAListOfLobbies() throws Exception {
        Mockito.when(service.findAll()).thenReturn(lobbies);
        mockMvc.perform(get("/lobbies")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldCreateAnewLobby() throws Exception {
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
