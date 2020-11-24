package br.com.kommando.user.data.models;

import br.com.kommando.user.data.enums.UserRoles;
import com.mongodb.lang.NonNull;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class User implements Serializable {

    private static final long serialVersionUID = 3342136292954850806L;

    @Id
    private String id;

    @NonNull
    private String uid;

    @NonNull
    private String nome;

    @NonNull
    private String displayName;

    @NonNull
    private String cpf;

    @NonNull
    private String telefone;

    @NonNull
    private UserRoles role = UserRoles.CONSUMIDOR;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    @NonNull
    public String getNome() {
        return nome;
    }

    public void setNome(@NonNull String nome) {
        this.nome = nome;
    }

    @NonNull
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(@NonNull String displayName) {
        this.displayName = displayName;
    }

    @NonNull
    public String getCpf() {
        return cpf;
    }

    public void setCpf(@NonNull String cpf) {
        this.cpf = cpf;
    }

    @NonNull
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NonNull String telefone) {
        this.telefone = telefone;
    }

    @NonNull
    public UserRoles getRole() {
        return role;
    }

    public void setRole(@NonNull UserRoles role) {
        this.role = role;
    }
}
