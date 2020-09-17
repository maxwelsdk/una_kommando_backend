package br.com.kommando.lobby.data.models;

import br.com.kommando.consumidor.data.models.Consumidor;
import br.com.kommando.lobby.data.enums.LobbyStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document
public class Lobby implements Serializable {

    private static final long serialVersionUID = -8362856355714789051L;

    @Id
    private String id;

    private String descricao;

    private LobbyStatus lobbyStatus = LobbyStatus.ABERTA;

    private List<Consumidor> consumidorList;

    public Lobby() {
        this.consumidorList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LobbyStatus getLobbyStatus() {
        return lobbyStatus;
    }

    public void setLobbyStatus(LobbyStatus lobbyStatus) {
        this.lobbyStatus = lobbyStatus;
    }

    public List<Consumidor> getConsumidorList() {
        return consumidorList;
    }

    public void setConsumidorList(List<Consumidor> consumidorList) {
        this.consumidorList = consumidorList;
    }

    @Override
    public String toString() {
        return "Lobby{" +
                "id='" + id + '\'' +
                ", descricao='" + descricao + '\'' +
                ", lobbyStatus=" + lobbyStatus +
                ", consumidorList=" + consumidorList +
                '}';
    }
}
