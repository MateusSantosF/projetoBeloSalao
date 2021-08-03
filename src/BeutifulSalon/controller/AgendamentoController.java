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

    public boolean cadastraAgendamento(String data, String horario, String cpfCliente, ArrayList<Servico> servicos, long total) throws ExceptionDAO {

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
            agendamento.setCpfCliente(cpfCliente);
            agendamento.setData(dataAgendamento);
            agendamento.setHorario(h);                   
            agendamento.setServicos(servicos);
                
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

    
    
    public ArrayList<Agendamento> listarAgendamentos() throws ExceptionDAO {
        return new Agendamento().listarAgendamentos();
    }
    public ArrayList<Agendamento> listarAgendamentos(LocalDate data) throws ExceptionDAO {
        return new Agendamento().listarAgendamentos(data);
    }
    
    public ArrayList<Agendamento> listarAgendamentosHoje() throws ExceptionDAO{
        return new Agendamento().listarAgendamentosHoje();
    }

    public ArrayList<Agendamento> listarAgendamentosAmanha() {
        return new Agendamento().listarAgendamentosAmanha();
    }
    
    public ArrayList<Agendamento> listarAgendamentosSemana() {
        return new Agendamento().listarAgendamentosSemana();
    }
    
    public ArrayList<Agendamento> listarAgendamentosNome(String nome) {
        return new Agendamento().listarAgendamentosNome(nome);
    }

}
