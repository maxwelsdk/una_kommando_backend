package br.com.kommando.lobby.data.enums;

public enum LobbyStatus {
    ABERTA("Aberta"),
    OCUPADA("Ocupada"),
    PREPARAÇAO("Em preparação"),
    RESERVADA("Reservada"),
    FECHADA("Fechada");

    private final String status;

    LobbyStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
