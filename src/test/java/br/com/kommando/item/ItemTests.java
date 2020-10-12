package br.com.kommando.item;

import br.com.kommando.item.data.models.Item;
import br.com.kommando.item.data.services.ItemService;
import br.com.kommando.produto.data.models.Produto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService service;

    static ArrayList<Item> itens = new ArrayList<>();

    @BeforeAll
    static void setUpAll() {
        for (int i = 0; i < 5; i++) {
            itens.add(new Item(String.valueOf(i), new Produto(1 + i, "Teste " + i, 29.0), 1));
        }
    }

    @Test
    void shouldReturnAListOfItem() throws Exception {
        Mockito.when(service.findAll()).thenReturn(itens);
        mockMvc.perform(get("/itens")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldAddAItem() throws Exception {
        Item item = new Item("99", new Produto(), 1);
        Mockito.when(service.save(item)).thenReturn(item);
        mockMvc.perform(MockMvcRequestBuilders.post("/itens")
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(item)))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    void shouldRemoveOneItemFromTheList() throws Exception {
        mockMvc.perform(delete("/itens/1"));
    }

}
