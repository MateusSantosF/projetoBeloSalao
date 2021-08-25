/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import BeutifulSalon.dao.CompraProdutoDAO;
import BeutifulSalon.dao.ExceptionDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mateus
 */
public class Compra {
    
    LocalDate data;
    long valorTotal;
    long valorDesconto;
    String cpfCliente;
    ArrayList<ItemCompra> itensCompra;

    
    public Compra() { }
    
    public Compra(LocalDate data, long valorDesconto, String cpfCliente, ArrayList<ItemCompra> itensCompra) {
        this.data = data;
        this.valorDesconto = valorDesconto;
        this.cpfCliente = cpfCliente;
        this.itensCompra = itensCompra;
    }


    
    
    
    

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public long getValorTotal() {
        
        long valorTotal = 0;
        
        for(ItemCompra it : getItensCompra()){
            valorTotal += it.getPrecoTotal();
        }
        
        return valorTotal;
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

    public ArrayList<ItemCompra> getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(ArrayList<ItemCompra> itensCompra) {
        this.itensCompra = itensCompra;
    }
    
    
    
    public void cadastraCompra(Compra compra) throws ExceptionDAO, SQLException{
        new CompraProdutoDAO().cadastraCompra(compra);
    }
    
    
    
}
