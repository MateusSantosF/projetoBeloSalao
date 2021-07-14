/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.Servico;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class AgendamentoController {
    
    
    
    public boolean cadastraAgendamento(Date data, LocalTime horario, String cpfCliente, ArrayList<Servico> servicos){
        
        if( cpfCliente.length() > 0 ){
            Agendamento agendamento = new Agendamento();
            
            agendamento.setCpfCliente(cpfCliente);
            agendamento.setData(data);
            agendamento.setHorario(horario);
            agendamento.setServicos(servicos);
            try {
                agendamento.cadastraAgendamento(agendamento);
            } catch (Exception e) {
                
                JOptionPane.showMessageDialog(null, "AgendamentoController" + e);
                return false;
            }
            
        }
        
        return true;
    }

    
}
