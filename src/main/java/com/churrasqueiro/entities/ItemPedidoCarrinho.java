package com.churrasqueiro.entities;

public class ItemPedidoCarrinho {

    private ItemCardapio item;
    private int quantidade;

    public ItemPedidoCarrinho(ItemCardapio item) {
        this.item = item;
        this.quantidade = 1;
    }

    public ItemCardapio getItem() {
        return item;
    }

    public void setItem(ItemCardapio item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void incrementar() {
        this.quantidade++;
    }

    public void decrementar() {
        if (this.quantidade > 0) {
            this.quantidade--;
        }
    }

    public double getSubtotal() {
        return item.getPreco() * quantidade;
    }
}
