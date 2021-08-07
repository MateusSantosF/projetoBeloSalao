/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.Ferramentas.Valida;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.Servico;
import BeutifulSalon.view.Edicao.EditarAgendamento;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class AgendamentoController {

    public boolean cadastraAgendamento(String data, String horario, String cpfCliente, ArrayList<Servico> servicos, long total, long desconto, boolean realizado) throws ExceptionDAO {

        if ( Valida.isCpf(cpfCliente) && Valida.isHora(horario) && !servicos.isEmpty()) {
                               
            //Formatadores
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/M/uuuu");
            DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
            //Horario
            LocalTime h = null;
            LocalDate dataAgendamento = null;
            
            try {
                h = LocalTime.parse(horario, formatterHora);
                dataAgendamento = LocalDate.parse(data, formatterData);
            } catch (DateTimeException e) {
                JOptionPane.showMessageDialog(null, "Erro ao convertar datas " + e);
                return false;
            }
            //Passando parametros
         
            Agendamento agendamento = new Agendamento();

            agendamento.setTotal(total);
            agendamento.setDesconto(desconto);
            agendamento.setCpfCliente(cpfCliente);
            agendamento.setData(dataAgendamento);
            agendamento.setHorario(h);                   
            agendamento.setServicos(servicos);
            agendamento.setRealizado(realizado);
               
            try {
                agendamento.cadastraAgendamento(agendamento);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "AgendamentoController" + e);
                return false;
            }

        } else {
            return false;
        }

        return true;
    }
    
     public boolean atualizarAgendamento(String data, String horario, String cpfCliente, ArrayList<Servico> servicos, long total, long desconto,boolean realizado, long idAgendamento) throws ExceptionDAO {

        if ( Valida.isCpf(cpfCliente) && Valida.isHora(horario) && !servicos.isEmpty()) {
                               
            //Formatadores
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/M/uuuu");
            DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
            //Horario
            LocalTime h = null;
            LocalDate dataAgendamento = null;
            
            try {
                h = LocalTime.parse(horario, formatterHora);
                dataAgendamento = LocalDate.parse(data, formatterData);
            } catch (DateTimeException e) {
                JOptionPane.showMessageDialog(null, "Erro ao convertar datas " + e);
                return false;
            }
            //Passando parametros
         
            Agendamento agendamento = new Agendamento();
            agendamento.setId(idAgendamento);
            agendamento.setTotal(total);
            agendamento.setDesconto(desconto);
            agendamento.setCpfCliente(cpfCliente);
            agendamento.setData(dataAgendamento);
            agendamento.setHorario(h);                   
            agendamento.setServicos(servicos);
            agendamento.setRealizado(realizado);
               
            try {
                agendamento.atualizarAgendamento(agendamento);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "AgendamentoController" + e);
                return false;
            }

        } else {
            return false;
        }

        return true;
    }
    
    public boolean editarAgendamento(long idAgendamento){
              
        try {
        
            Agendamento ag = listarAgendamento(idAgendamento);
            
            if(ag != null){
                new EditarAgendamento(ag).setVisible(true);
            }else{
                return false;
            }
            
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar objeto agendamento" + e);
        }      
        return true;
    }
    
    public Agendamento listarAgendamento(long idAgendamento) throws ExceptionDAO{
        return new Agendamento().listarAgendamento(idAgendamento);
    }
    
    public ArrayList<Servico> listarServicosAgendamento(long idAgendamento) throws ExceptionDAO{
        return new Agendamento().listarServicosAgendamento(idAgendamento);
    }
    
    public ArrayList<Agendamento> listarAgendamentos() throws ExceptionDAO {
        return new Agendamento().listarAgendamentos();
    }
    public ArrayList<Agendamento> listarAgendamentos(LocalDate data) throws ExceptionDAO {
        return new Agendamento().listarAgendamentos(data);
    }
    
    public ArrayList<Agendamento> listarAgendamentosHoje() throws ExceptionDAO{
        return new Agendamento().listarAgendamentosHoje();
    }

    public ArrayList<Agendamento> listarAgendamentosAmanha() throws ExceptionDAO {
        return new Agendamento().listarAgendamentosAmanha();
    }
    
    public ArrayList<Agendamento> listarAgendamentosSemana() throws ExceptionDAO{
        return new Agendamento().listarAgendamentosSemana();
    }
    
    public ArrayList<Agendamento> listarAgendamentosNome(String nome) throws ExceptionDAO {
        return new Agendamento().listarAgendamentosNome(nome);
    }

    public ArrayList<Agendamento> listarAgendamentosNaoRealizados() {
        return new Agendamento().listarAgendamentosNaoRealizados();
    }

}
