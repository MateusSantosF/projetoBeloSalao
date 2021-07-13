/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.dao.ServicoDAO;
import java.util.ArrayList;

/**
 *
 * @author mateus
 */
public class Servico {
    
    long id;
    String nome;
    long valor;

    
    public Servico(){};
    
    public Servico(long id, String nome, long valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
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

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
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
    
    
}
