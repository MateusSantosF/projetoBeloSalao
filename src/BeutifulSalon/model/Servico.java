/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.dao.ServicoDAO;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author mateus
 */
public class Servico {
    
    long id;
    String nome;
    long preco;
    ArrayList<Produto> produtos;
    LocalTime tempoGasto;

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
    
    public ArrayList<Servico> listarServicos() throws ExceptionDAO{
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
    
    
}
