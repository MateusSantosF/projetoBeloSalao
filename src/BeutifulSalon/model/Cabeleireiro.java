/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import BeutifulSalon.dao.CabeleireiroDAO;
import BeutifulSalon.dao.ExceptionDAO;

/**
 *
 * @author mateus
 */
public class Cabeleireiro {
    
    
    String cpf;
    String nome;
    String senha;
    String email;

    public Cabeleireiro(){};
    
    
    public Cabeleireiro(String cpf, String nome, String senha, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
    }
    
    

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void cadastrarCabeleireiro(Cabeleireiro cabeleireiro) throws ExceptionDAO{
        new CabeleireiroDAO().cadastrarCabeleireiro(cabeleireiro);
    }
    
    public Cabeleireiro selecionaCabeleireiro() throws ExceptionDAO{
        return new CabeleireiroDAO().selecionaCabeleireiro();
    }
}
