package br.com.kommando.lobby.data.models;

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

    private List<String> consumidores;

    public Lobby() {
        this.consumidores = new ArrayList<>();
    }

    public Lobby(String descricao) {
        this.descricao = descricao;
    }

    public Lobby(String id, String descricao, LobbyStatus lobbyStatus) {
        this.id = id;
        this.descricao = descricao;
        this.lobbyStatus = lobbyStatus;
        this.consumidores = new ArrayList<>();
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

    public List<String> getConsumidores() {
        return consumidores;
    }

    public void setConsumidores(List<String> consumidores) {
        this.consumidores = consumidores;
    }

}
