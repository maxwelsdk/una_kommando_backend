package br.com.kommando.consumidor.data.models;

import br.com.kommando.pedido.data.models.Pedido;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document
public class Consumidor implements Serializable {

    private static final long serialVersionUID = 3594353686249387658L;

    @Id
    private String id;

    private String uid;

    private String lobbyId;

    private List<Pedido> pedidos;

    public Consumidor() {
        this.pedidos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(String lobbyId) {
        this.lobbyId = lobbyId;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "Consumidor{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", lobbyId='" + lobbyId + '\'' +
                ", pedidos=" + pedidos +
                '}';
    }
}
