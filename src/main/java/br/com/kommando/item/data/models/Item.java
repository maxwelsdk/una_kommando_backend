package br.com.kommando.item.data.models;

import br.com.kommando.produto.data.models.Produto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Item implements Serializable {

    private static final long serialVersionUID = -1300663070263094887L;

    @Id
    private String id;

    private Produto produto;

    private int quantidade;

    public Item() {
    }

    public Item(String id, Produto produto, int quantidade) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                '}';
    }
}
