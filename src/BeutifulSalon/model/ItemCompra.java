/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

/**
 *
 * @author mateus
 */
public class ItemCompra extends Produto {
    
    int quantidade;
    long precoTotal;
    long id_compra;

    public ItemCompra(){
        super();
    }
    
    public ItemCompra(String nome, String marca, long idProduto, 
            long preco, int quantidade, long precoTotal, long id_compra) {    
        super(nome, marca, preco, idProduto);
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;
        this.id_compra = id_compra;
    }
    
    
    

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public long getPrecoTotal() {
        return super.getPreco() * getQuantidade();
    }

    public void setPrecoTotal(long precoTotal) {
        this.precoTotal = precoTotal;
    }

    public long getId_compra() {
        return id_compra;
    }

    public void setId_compra(long id_compra) {
        this.id_compra = id_compra;
    }
    
    
    
}
