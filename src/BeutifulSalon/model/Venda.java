/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.dao.VendaProdutoDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author mateu
 */
public class Venda{
    
    long idVenda;
    LocalDate data;
    long valorTotal;
    long valorDesconto;
    String cpfCliente;
    ArrayList<Item> itensVenda;

    
    public Venda() { };
    
    public Venda(LocalDate data, long valorDesconto, String cpfCliente, ArrayList<Item> itensCompra) {
        this.data = data;
        this.valorDesconto = valorDesconto;
        this.cpfCliente = cpfCliente;
        this.itensVenda = itensCompra;
    }
    
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public long getValorTotal() {
        
        long valorTotalSomado = 0;
        
        for(Item it : getItensCompra()){
            valorTotalSomado += it.getPrecoTotal();
        }
        
        return valorTotalSomado;
    }

    public long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(long idVenda) {
        this.idVenda = idVenda;
    }

    public void setValorTotal(long valorTotal) {
        this.valorTotal = valorTotal;
    }

    public long getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(long valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public ArrayList<Item> getItensCompra() {
        return itensVenda;
    }

    public void setItensCompra(ArrayList<Item> itensCompra) {
        this.itensVenda = itensCompra;
    }
    
    public void cadastrarVenda(Venda venda) throws ExceptionDAO{
        new VendaProdutoDAO().cadastrarVenda(venda);
    }

    public List<Venda> selecionaVendasDoAno(int anoReferente) throws ExceptionDAO{
        return new VendaProdutoDAO().selecionaVendasDoAno(anoReferente);
    }

    public int retornaQuantidadeDeVendasHoje() throws ExceptionDAO {
        return new VendaProdutoDAO().retornaQuantidadeDeVendasHoje();
    }
 


}
