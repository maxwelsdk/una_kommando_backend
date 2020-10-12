package br.com.kommando.lobby;

import br.com.kommando.item.data.models.Item;
import br.com.kommando.lobby.data.models.Lobby;
import br.com.kommando.lobby.data.services.LobbyService;
import br.com.kommando.produto.data.models.Produto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LobbyTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LobbyService service;

    static ArrayList<Lobby> lobbies = new ArrayList<>();

    @BeforeAll
    static void setUpAll() {
        for (int i = 0; i < 5; i++) {
            lobbies.add(new Lobby());
        }
    }

    @Test
    void shouldReturnAListOfItem() throws Exception {
        Mockito.when(service.findAll()).thenReturn(lobbies);
        mockMvc.perform(get("/lobbies")).andDo(print()).andExpect(status().isOk());
    }

}
