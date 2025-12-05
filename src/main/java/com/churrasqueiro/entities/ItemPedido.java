package com.churrasqueiro.entities;

public class ItemPedido {
    private int id;
    private int pedidoId;
    private int itemCardapioId;
    private int quantidade;
    private double precoUnitario;
    private double totalItem;

    public int getPedidoId() {
        return pedidoId;
    }
    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getItemCardapioId() {
        return itemCardapioId;
    }
    public void setItemCardapioId(int itemCardapioId) {
        this.itemCardapioId = itemCardapioId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.totalItem = this.quantidade * this.precoUnitario;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }
    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
        this.totalItem = this.quantidade * this.precoUnitario;
    }

    public double getTotalItem() {
        return totalItem;
    }
    public void setTotalItem(double totalItem) {
        this.totalItem = totalItem;
    }

    public void CalcularTotalItens(){
        this.totalItem = this.quantidade * this.precoUnitario;
    }
    public ItemPedido(int id,int pedidoId, int itemCardapioId,int quantidade, double precoUnitario){
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.id = id;
        this.pedidoId = pedidoId;
        this.itemCardapioId = itemCardapioId;

        this.CalcularTotalItens();
    }
    @Override
    public String toString() {
        return "ItemPedido{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                ", totalItem=" + totalItem +
                '}';
    }
}
