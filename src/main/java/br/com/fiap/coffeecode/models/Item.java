package br.com.fiap.coffeecode.models;

import java.math.BigDecimal;

public class Item {
    private String categoria;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private boolean ativo;

    public Item(String categoria, String nome, String descricao, BigDecimal preco, boolean ativo) {
        this.categoria = categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.ativo = ativo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Item [categoria=" + categoria + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco
                + ", ativo=" + ativo + "]";
    }

    
    
}
