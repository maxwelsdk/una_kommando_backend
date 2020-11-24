package br.com.kommando.user.data.enums;

public enum UserRoles {
    FUNCIONARIO("Funcionário"),
    CONSUMIDOR("Consumidor");

    private final String status;

    UserRoles(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
