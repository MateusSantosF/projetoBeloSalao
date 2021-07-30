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

    public static void main(String[] args) {

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
            agendamentos = ag.listarAgendamentos(LocalDate.of(2021, Month.JULY, 18));

            //recupera dia da semana
            diaDaSemana = agendamentos.get(0).getData().getDayOfWeek().getValue();
            System.out.println("Dia da semana: " + diaDaSemana);

            //recupera horario de inicio e término do expediente
            expedienteDoDia = cc.selecionaExpediente(diaDaSemana);

            entrada = expedienteDoDia.get(0);
            saida = expedienteDoDia.get(1);

            System.out.println("entrada e saida " + entrada + " " + saida);

            LocalTime horarioEntrada = null;
            //itera pelos agendamentos
            for (Agendamento a : agendamentos) {

                //System.out.println("\n\nHorario de entrada" + horarioEntrada);
                if(horarios.size() == 0){
                    horarioEntrada = entrada;
                }
                System.out.println("Horario do Agendamento = " + a.getHorario());
                Long tempoComparado = horarioEntrada.until(a.getHorario(), ChronoUnit.NANOS);

                if (tempoComparado >= 0) {
                    try {
                        LocalTime saidaAtual = LocalTime.ofNanoOfDay(tempoComparado);
                        System.out.println("Tempo até o proximo agedamento" + saidaAtual);
                        horarios.add(saidaAtual);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro ao verificar intervalo entre horas" + e);
                    }
                }

                //guardo no array o tempComprado
                int horas = 0;
                int minutos = 0;

                LocalTime agendamentoAtual = a.getHorario();
                ArrayList<Servico> servicosAgendamentoAtual = a.getServicos();

                //itera pelo serviços do agendamento
                for (Servico s : servicosAgendamentoAtual) {
                    horas += s.getTempoGasto().getHour();
                    minutos += s.getTempoGasto().getMinute();
                }

                agendamentoAtual = agendamentoAtual.plusHours(horas).plusMinutes(minutos);
                if(agendamentoAtual.isAfter(saida)){
                   // break;
                }
                System.out.println("Horario agendamento somado " + agendamentoAtual);
                horarioEntrada = agendamentoAtual;
                horarios.add(horarioEntrada);
            }
                horarios.add(0, entrada);
            
        } catch (ExceptionDAO ex) {
            Logger.getLogger(ManipulaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
        System.out.println("\n\n");
        for (LocalTime t : horarios) {
            System.out.println(t);
        }
    }
}
