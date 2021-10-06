/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import BeutifulSalon.dao.CompraProdutoDAO;
import BeutifulSalon.dao.ExceptionDAO;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

/**
 *
 * @author mateus
 */
public class Compra {

    LocalDate data;
    long valorTotal;
    long valorDesconto;
    ArrayList<Item> itensCompra;

    public Compra() {
    }

    public Compra(LocalDate data, long valorDesconto, String cpfCabeleireiro, ArrayList<Item> itensCompra) {
        this.data = data;
        this.valorDesconto = valorDesconto;
        this.itensCompra = itensCompra;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public long getValorTotal() {

        long valorTotalSomado = 0;

        for (Item it : getItensCompra()) {
            valorTotalSomado += it.getPrecoTotal();
        }

        return valorTotalSomado;
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

    public ArrayList<Item> getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(ArrayList<Item> itensCompra) {
        this.itensCompra = itensCompra;
    }

    public void cadastraCompra(Compra compra) throws ExceptionDAO {
        new CompraProdutoDAO().cadastraCompra(compra);
    }

    public long retornaSomaDeComprasMensais(Month mes) throws ExceptionDAO {
        return new CompraProdutoDAO().retornaSomaDeComprasMensais(mes);
    }

}
