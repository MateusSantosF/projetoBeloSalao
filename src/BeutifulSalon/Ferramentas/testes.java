/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.controller.AgendamentoController;
import BeutifulSalon.controller.CabeleireiroController;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.Servico;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class testes {
    public static void main(String[] args){
        
        
       ArrayList<LocalTime> horarios = new ArrayList<>(); // array de horarios disponivel ({inicio, tempoAtéPróximoAgendamento})
       ArrayList<Agendamento> agendamentos = null; // agendamentos do dia
       ArrayList<LocalTime> expedienteDoDia = null; // expediente do dia da semana referente a data    
       LocalTime entrada;
       LocalTime saida;
       LocalDate dataAgendamento;
       int diaDaSemana = 0; // 1 (segunda) -  7 (Domingo)
        
        try {
          
            AgendamentoController ag = new AgendamentoController();
            CabeleireiroController cc = new CabeleireiroController();
                        
            //busca agendamentos do dia inserido na tela de agendamento
                agendamentos = ag.listarAgendamentos(LocalDate.of(2021, Month.JULY, 30));         
            
            //recupera dia da semana
                diaDaSemana = agendamentos.get(0).getData().getDayOfWeek().getValue();
                System.out.println("Dia da semana: " + diaDaSemana);
             
            
            //recupera horario de inicio e término do expediente
                expedienteDoDia = cc.selecionaExpediente(diaDaSemana);
                

                entrada = expedienteDoDia.get(0);
                saida = expedienteDoDia.get(1);
                
           
     
            LocalTime horarioEntrada = entrada;
            //itera pelos agendamentos
            for(Agendamento a: agendamentos){
               
               horarios.add(horarioEntrada);
                System.out.println("Horario do Agendamento = " + a.getHorario());
               Long tempoComparadoMinutos = horarioEntrada.until(a.getHorario(), ChronoUnit.NANOS);
                try {
                   LocalTime saidaAtual = LocalTime.ofNanoOfDay(tempoComparadoMinutos);
                   System.out.println("Tempo entre o agedamento" + saidaAtual);
                   horarios.add(saidaAtual);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao vefificar intervalo entre hroas");
                }
               //guardo no array o tempComprado
             
               
               int horas = 0;
               int minutos = 0;
               
               LocalTime agendamentoAtual = a.getHorario();   
               ArrayList<Servico> servicosAgendamentoAtual = a.getServicos();

               //itera pelo serviços do agendamento
               for(Servico s: servicosAgendamentoAtual){
                   horas += s.getTempoGasto().getHour();
                   minutos += s.getTempoGasto().getMinute();
               }
    
                
               agendamentoAtual = agendamentoAtual.plusHours(horas);
               agendamentoAtual = agendamentoAtual.plusMinutes(minutos);
               
               System.out.println("Horario agendamento somado " + agendamentoAtual);
               horarioEntrada = agendamentoAtual;
            }
            
           
                      
        } catch (ExceptionDAO ex) {
            Logger.getLogger(ManipulaData.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("\n\n");
         for(LocalTime t: horarios){
             System.out.println(t);
         }
    }
}
