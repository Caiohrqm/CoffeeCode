package br.com.fiap.coffeecode.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pedido {
    private int id;
    private ArrayList<Item> itens;
    private BigDecimal total;
    private LocalDateTime data;
    private int senha;
    private String nome;
    private boolean entregue = false;
    
    public Pedido(int id, ArrayList<Item> itens, BigDecimal total, LocalDateTime data, int senha, String nome,
            boolean entregue) {
        this.id = id;
        this.itens = itens;
        this.total = total;
        this.data = data;
        this.senha = senha;
        this.nome = nome;
        this.entregue = entregue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public void setItens(ArrayList<Item> itens) {
        this.itens = itens;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isEntregue() {
        return entregue;
    }

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }

    @Override
    public String toString() {
        return "Pedido [id=" + id + ", itens=" + itens + ", total=" + total + ", data=" + data + ", senha=" + senha
                + ", nome=" + nome + ", entregue=" + entregue + "]";
    }

}
