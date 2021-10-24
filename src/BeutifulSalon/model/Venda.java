/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.dao.VendaProdutoDAO;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mateu
 */
public class Venda {

    long idVenda;
    LocalDate data;
    long valorTotal;
    long valorDesconto;
    long idCliente;
    int quantidadeProdutosVendidos;
    String nomeCliente;
    String sobrenomeCliente;
    String nomeProduto;
    List<Item> itensVenda;

    public Venda() {
    }

    ;
    
    public Venda(LocalDate data, long valorDesconto, String cpfCliente, ArrayList<Item> itensCompra) {
        this.data = data;
        this.valorDesconto = valorDesconto;
        this.itensVenda = itensCompra;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDataFormatada() {
        return data.toString();
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public long getValorTotal() {

        long valorTotalSomado = 0;
        if (getItensCompra() != null) {
            for (Item it : getItensCompra()) {
                valorTotalSomado += it.getPrecoTotal();
            }
        }

        return valorTotalSomado;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public List<Item> getItensVenda() {
        return itensVenda;
    }

    public void setItensVenda(List<Item> itensVenda) {
        this.itensVenda = itensVenda;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getSobrenomeCliente() {
        return sobrenomeCliente;
    }

    public void setSobrenomeCliente(String sobrenomeCliente) {
        this.sobrenomeCliente = sobrenomeCliente;
    }

    public int getQuantidadeProdutosVendidos() {
        return quantidadeProdutosVendidos;
    }

    public void setQuantidadeProdutosVendidos(int quantidadeProdutosVendidos) {
        this.quantidadeProdutosVendidos = quantidadeProdutosVendidos;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public long getTotal() {
        return this.valorTotal;
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

    public List<Item> getItensCompra() {
        return itensVenda;
    }

    public void setItensCompra(List<Item> itensCompra) {
        this.itensVenda = itensCompra;
    }

    public void cadastrarVenda(Venda venda) throws ExceptionDAO {
        new VendaProdutoDAO().cadastrarVenda(venda);
    }

    public List<Venda> selecionaVendasDoAno(int anoReferente) throws ExceptionDAO {
        return new VendaProdutoDAO().selecionaVendasDoAno(anoReferente);
    }

    public long selecionaVendasPorMes(Month mes) throws ExceptionDAO {
        return new VendaProdutoDAO().selecionaVendasPorMes(mes);
    }

    public static List<Venda> jasperList() {
        try {
            return new VendaProdutoDAO().selecionaTodasVendas();
        } catch (ExceptionDAO ex) {
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<Venda> selecionaTodasVendas() throws ExceptionDAO {
        return new VendaProdutoDAO().selecionaTodasVendas();
    }

    public int retornaQuantidadeDeVendasHoje() throws ExceptionDAO {
        return new VendaProdutoDAO().retornaQuantidadeDeVendasHoje();
    }

    public long retornaSomaDeVendasMensal() throws ExceptionDAO {
        return new VendaProdutoDAO().retornaSomaDeVendasMensal();
    }

    public List<Venda> selecionaVendasPorNomeCliente(String nomeCliente) throws ExceptionDAO {
        return new VendaProdutoDAO().selecionaVendasPorNomeCliente(nomeCliente);
    }

    public List<Venda> selecionaVendasDoAnoPorNomeCliente(String nomeCliente) throws ExceptionDAO {
        return new VendaProdutoDAO().selecionaVendasDoAnoPorNomeCliente(nomeCliente);
    }

    public boolean excluirVenda(Venda venda) throws ExceptionDAO {
        return new VendaProdutoDAO().excluirVenda(venda);
    }

    public List<Venda> retornaComprasCliente(long idCliente) throws ExceptionDAO {
        return new VendaProdutoDAO().retornaComprasPorIDCliente(idCliente);
    }

    public boolean atualizarVenda(Venda v, List<Item> itensAntigos) throws ExceptionDAO {
        return new VendaProdutoDAO().atualizarVenda(v, itensAntigos);
    }

}
