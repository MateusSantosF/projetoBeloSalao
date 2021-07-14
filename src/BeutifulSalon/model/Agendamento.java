/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import BeutifulSalon.dao.AgendamentoDAO;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mateus
 */
public class Agendamento {
    
    long id;
    Date data;
    LocalTime horario;
    String cpfCliente;
    ArrayList<Servico> servicos;

    
    public Agendamento(){};
    
    public Agendamento(Date data, LocalTime horario, String cpfCliente, ArrayList<Servico> servicos) {
      
        this.data = data;
        this.horario = horario;
        this.cpfCliente = cpfCliente;
        this.servicos = servicos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public ArrayList<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(ArrayList<Servico> servicos) {
        this.servicos = servicos;
    }
    
    
    public void cadastraAgendamento(Agendamento agendamento){
        new AgendamentoDAO().cadastraAgendamento(agendamento);
    }
    
    
}
