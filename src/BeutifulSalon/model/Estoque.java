/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import BeutifulSalon.dao.EstoqueDAO;
import BeutifulSalon.dao.ExceptionDAO;

/**
 *
 * @author mateus
 */
public class Estoque {
    
    private long id;
    private long idProduto;
    private long quantidade;
    private long valorUnitario;
    
    
    public Estoque() {
    }

    public Estoque(long id, long idProduto, long quantidade, long valorUnitario) {
        this.id = id;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }
   
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }

    public long getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(long valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    
    public void atualizaEstoque(Estoque estoque, boolean venda) throws ExceptionDAO{
        new EstoqueDAO().atualizaEstoque(estoque, venda);
    }

    public long somaProdutosEstoque() throws ExceptionDAO{
        return new EstoqueDAO().somaProdutosEstoque();
    }

    public long quantidadeProduto(long idProduto) throws ExceptionDAO {
        return new EstoqueDAO().quantidadeProduto(idProduto);
    }
    
    public long ultimoValorPagoProduto(long idProduto) throws ExceptionDAO{
        return new EstoqueDAO().ultimoValorPagoProduto(idProduto);
    }
    
    

    
}
