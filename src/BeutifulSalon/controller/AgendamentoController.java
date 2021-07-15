/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.Servico;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class AgendamentoController {

    public boolean cadastraAgendamento(String data, String horario, String cpfCliente, ArrayList<Servico> servicos) throws ExceptionDAO {

        if (cpfCliente.length() > 0 && data.length() > 0 && horario.length() > 0) {
            
            //Formatadores
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/M/uuuu");
            DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
            //Horario
            LocalTime h = null;
            LocalDate dataAgendamento = null;
            
            try {
                h = LocalTime.parse(horario, formatterHora);
                System.out.println(h);
                dataAgendamento = LocalDate.parse(data, formatterData);
            } catch (DateTimeException e) {
                JOptionPane.showMessageDialog(null, "Erro ao convertar datas " + e);
                return false;
            }
            //Passando parametros
            Agendamento agendamento = new Agendamento();
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

}
