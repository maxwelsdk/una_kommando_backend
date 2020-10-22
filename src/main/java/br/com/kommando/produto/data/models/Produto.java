package br.com.kommando.produto.data.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Produto implements Serializable {

    private static final long serialVersionUID = 6834300721370293017L;

    @Id
    private String id;

    private int codigo;

    private String titulo;

    private String descricao;

    private Double preco;


    public Produto() {
    }

    public Produto(int codigo, String titulo,String descricao, Double preco) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

}
