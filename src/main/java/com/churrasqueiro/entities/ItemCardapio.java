package com.churrasqueiro.entities;

public class ItemCardapio {
    
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private int categoriaId;
    private String fotoUrl; 
    private double precoComparacao;
    
    
    public ItemCardapio() {}

    public ItemCardapio(int id, String nome, String descricao, double preco, int categoriaId, String fotoUrl, double precoComparacao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoriaId = categoriaId;
        this.fotoUrl = fotoUrl;
        this.precoComparacao = precoComparacao;
    }
    
    public ItemCardapio(String nome, String descricao, double preco, int categoriaId, String fotoUrl, double precoComparacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoriaId = categoriaId;
        this.fotoUrl = fotoUrl;
        this.precoComparacao = precoComparacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public double getPrecoComparacao() {
        return precoComparacao;
    }

    public void setPrecoComparacao(double precoComparacao) {
        this.precoComparacao = precoComparacao;
    }
    
    @Override
    public String toString() {
        return "ItemCardapio [id=" + id + ", nome=" + nome + ", preco=" + preco + ", categoriaId=" + categoriaId + "]";
    }
}