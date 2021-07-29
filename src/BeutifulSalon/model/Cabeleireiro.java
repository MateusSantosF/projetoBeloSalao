/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import BeutifulSalon.dao.CabeleireiroDAO;
import BeutifulSalon.dao.ExceptionDAO;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author mateus
 */
public class Cabeleireiro {
    
    
    private String cpf;
    private String nome;
    private String email;
    private LocalTime segundaE;
    private LocalTime tercaE;
    private LocalTime quartaE;
    private LocalTime quintaE;
    private LocalTime sextaE;
    private LocalTime sabadoE;
    private LocalTime domingoE;
    private LocalTime segundaS;
    private LocalTime tercaS;
    private LocalTime quartaS;
    private LocalTime quintaS;
    private LocalTime sextaS;
    private LocalTime sabadoS;
    private LocalTime domingoS;

    public Cabeleireiro(){};

    public Cabeleireiro(String cpf, String nome, String email, ArrayList<LocalTime> expediente) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.segundaE = expediente.get(0);
        this.segundaS = expediente.get(1);
        
        this.tercaE = expediente.get(2);
        this.tercaS = expediente.get(3);
        
        this.quartaE = expediente.get(4);
        this.quartaS = expediente.get(5);
        
        this.quintaE = expediente.get(6);
        this.quintaS = expediente.get(7);
        
        this.sextaE = expediente.get(8);
        this.sextaS = expediente.get(9);
        
        this.sabadoE = expediente.get(10);
        this.sabadoS = expediente.get(11);
        
        this.domingoE = expediente.get(12);   
        this.domingoS = expediente.get(13);
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


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalTime getSegundaE() {
        return segundaE;
    }

    public void setSegundaE(LocalTime segundaE) {
        this.segundaE = segundaE;
    }

    public LocalTime getTercaE() {
        return tercaE;
    }

    public void setTercaE(LocalTime tercaE) {
        this.tercaE = tercaE;
    }

    public LocalTime getQuartaE() {
        return quartaE;
    }

    public void setQuartaE(LocalTime quartaE) {
        this.quartaE = quartaE;
    }

    public LocalTime getQuintaE() {
        return quintaE;
    }

    public void setQuintaE(LocalTime quintaE) {
        this.quintaE = quintaE;
    }

    public LocalTime getSextaE() {
        return sextaE;
    }

    public void setSextaE(LocalTime sextaE) {
        this.sextaE = sextaE;
    }

    public LocalTime getSabadoE() {
        return sabadoE;
    }

    public void setSabadoE(LocalTime sabadoE) {
        this.sabadoE = sabadoE;
    }

    public LocalTime getDomingoE() {
        return domingoE;
    }

    public void setDomingoE(LocalTime domingoE) {
        this.domingoE = domingoE;
    }

    public LocalTime getSegundaS() {
        return segundaS;
    }

    public void setSegundaS(LocalTime segundaS) {
        this.segundaS = segundaS;
    }

    public LocalTime getTercaS() {
        return tercaS;
    }

    public void setTercaS(LocalTime tercaS) {
        this.tercaS = tercaS;
    }

    public LocalTime getQuartaS() {
        return quartaS;
    }

    public void setQuartaS(LocalTime quartaS) {
        this.quartaS = quartaS;
    }

    public LocalTime getQuintaS() {
        return quintaS;
    }

    public void setQuintaS(LocalTime quintaS) {
        this.quintaS = quintaS;
    }

    public LocalTime getSextaS() {
        return sextaS;
    }

    public void setSextaS(LocalTime sextaS) {
        this.sextaS = sextaS;
    }

    public LocalTime getSabadoS() {
        return sabadoS;
    }

    public void setSabadoS(LocalTime sabadoS) {
        this.sabadoS = sabadoS;
    }

    public LocalTime getDomingoS() {
        return domingoS;
    }

    public void setDomingoS(LocalTime domingoS) {
        this.domingoS = domingoS;
    }
            
            

   
    
    public void cadastrarCabeleireiro(Cabeleireiro cabeleireiro) throws ExceptionDAO{
        new CabeleireiroDAO().cadastrarCabeleireiro(cabeleireiro);
    }
    
    public Cabeleireiro selecionaCabeleireiro() throws ExceptionDAO{
        return new CabeleireiroDAO().selecionaCabeleireiro();
    }

    public ArrayList<LocalTime> selecionaExpediente(int diaDaSemana) {
        return new CabeleireiroDAO().selecionaExpediente(diaDaSemana);
    }
}
