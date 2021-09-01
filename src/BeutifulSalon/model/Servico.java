/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.dao.ServicoDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mateus
 */
public class Servico {
    
    private long id;
    private String nome;
    private long preco;
    private ArrayList<Produto> produtos;
    private LocalTime tempoGasto;
    private LocalDate dataRealizado; //data em que foi feito;
    private long quantidadeMensal;

    public LocalTime getTempoGasto() {
        return tempoGasto;
    }

    public void setTempoGasto(LocalTime tempoGasto) {
        this.tempoGasto = tempoGasto;
    }
    
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
    
    public Servico(){};
    
    public Servico(long id, String nome, long valor) {
        this.id = id;
        this.nome = nome;
        this.preco = valor;
    }
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getPreco() {
        return preco;
    }

    public void setPreco(long preco) {
        this.preco = preco;
    }

    public LocalDate getDataRealizado() {
        return dataRealizado;
    }

    public void setDataRealizado(LocalDate dataRealizado) {
        this.dataRealizado = dataRealizado;
    }
    

    public long getQuantidadeMensal() {
        return quantidadeMensal;
    }

    public void setQuantidadeMensal(long quantidadeMensal) {
        this.quantidadeMensal = quantidadeMensal;
    }
    
    public List<Servico> listarServicos() throws ExceptionDAO{
        return new ServicoDAO().listarServicos();
    }
    
    public ArrayList<Servico> listarServicos(String nome) throws ExceptionDAO{
        return new ServicoDAO().listarServicos(nome);
    }
    
    public Servico buscarServico(long idServicoBuscado) throws ExceptionDAO{
        return new ServicoDAO().buscarServico(idServicoBuscado);
    }
    public ArrayList<Servico> buscarServicoPeloAgendamento(long idAgendamento) throws ExceptionDAO{
        return new ServicoDAO().buscaServicoPeloAgendamento(idAgendamento);
    }
    
    public void cadastrarServico(Servico servico) throws SQLException{
        new ServicoDAO().cadastrarServico(servico);
    }

    public List<Servico> listarOsCincoServicosMaisRealizados() throws ExceptionDAO{
        return new ServicoDAO().listaOsCincoServicosMaisRealizados();
    }

    public List<Servico> selecionaServicosDoAno(int anoReferente) throws ExceptionDAO {
        return new ServicoDAO().selecionaServicosDoAno(anoReferente);
    }

    public List<Servico> listarServicosDeAgendamentoPorCliente(String cpf) throws ExceptionDAO{
        return new ServicoDAO().listarServicosDeAgendamentoPorCliente(cpf);
    }
    
    
}
