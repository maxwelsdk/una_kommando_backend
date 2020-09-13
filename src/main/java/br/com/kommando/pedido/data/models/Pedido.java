package br.com.kommando.pedido.data.models;

import br.com.kommando.item.data.models.Item;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document
public class Pedido implements Serializable {

    private static final long serialVersionUID = 643339112693158644L;

    @Id
    private String id;

    private String lobbyId;

    private String consumidorId;

    private List<Item> items;

    public Pedido() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(String lobbyId) {
        this.lobbyId = lobbyId;
    }

    public String getConsumidorId() {
        return consumidorId;
    }

    public void setConsumidorId(String consumidorId) {
        this.consumidorId = consumidorId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return "Pedido{" +
                "id='" + id + '\'' +
                ", lobbyId='" + lobbyId + '\'' +
                ", consumidorId='" + consumidorId + '\'' +
                ", items=" + items +
                '}';
    }
}
