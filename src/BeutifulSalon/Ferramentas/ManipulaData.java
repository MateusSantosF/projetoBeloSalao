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
public class ManipulaData {

    public ManipulaData() {
    }

    public long meiaNoiteHoje() {

        LocalDate hoje = LocalDate.now();
        LocalTime meiaNoite = LocalTime.MIDNIGHT;
        LocalDateTime diff = LocalDateTime.of(hoje, meiaNoite);

        long meiaNoiteMs = diff.toLocalDate().toEpochDay() * 24 * 60 * 60 * 1000;

        return meiaNoiteMs;
    }

    public long meiaNoite(LocalDate dia) {

        LocalTime meiaNoite = LocalTime.MIDNIGHT;
        LocalDateTime diff = LocalDateTime.of(dia, meiaNoite);

        long meiaNoiteMs = diff.toLocalDate().toEpochDay() * 24 * 60 * 60 * 1000;

        return meiaNoiteMs;
    }

    public long meiaNoiteAmanha(LocalDate dia) {

        LocalTime meiaNoite = LocalTime.MIDNIGHT;
        LocalDateTime diff = LocalDateTime.of(dia, meiaNoite);
        LocalDateTime meiaNoiteAmannha = diff.plusDays(1);
        long meiaNoiteMs = meiaNoiteAmannha.toLocalDate().toEpochDay() * 24 * 60 * 60 * 1000;

        return meiaNoiteMs;
    }

    public long MeiaNoiteAmanha() {

        LocalDate hoje = LocalDate.now();
        LocalTime meiaNoite = LocalTime.MIDNIGHT;
        LocalDateTime diff = LocalDateTime.of(hoje, meiaNoite);
        LocalDateTime meiaNoiteAmanha = diff.plusDays(1);

        long meiaNoiteAmanhaMs = meiaNoiteAmanha.toLocalDate().toEpochDay() * 24 * 60 * 60 * 1000;

        return meiaNoiteAmanhaMs;
    }

    public long somaDia(LocalDateTime diaAtual, long qtdDias) {

        LocalDateTime soma = diaAtual.plusDays(qtdDias);

        long somaMs = soma.toLocalDate().toEpochDay() * 24 * 60 * 60 * 1000;

        return somaMs;
    }

    public ArrayList<LocalTime> recuperaHorariosDisponiveis(LocalDate data) {

        ArrayList<LocalTime> horarios = new ArrayList<>(); // array de horarios disponivel ({inicio, tempoAtéPróximoAgendamento})
        ArrayList<Agendamento> agendamentos = null; // agendamentos do dia
        ArrayList<LocalTime> expedienteDoDia = null; // expediente do dia da semana referente a data    
        LocalTime entrada;
        LocalTime saida;
        int diaDaSemana = 0; // 1 (segunda) -  7 (Domingo)

        try {

            AgendamentoController ag = new AgendamentoController();
            CabeleireiroController cc = new CabeleireiroController();

            //busca agendamentos do dia inserido na tela de agendamento
            
            agendamentos = ag.listarAgendamentos(data);
            if(agendamentos.isEmpty()){
                 System.out.println("teste");
                return null;
               
            }
            //recupera dia da semana
            diaDaSemana = agendamentos.get(0).getData().getDayOfWeek().getValue();
            //System.out.println("Dia da semana: " + diaDaSemana);

            //recupera horario de inicio e término do expediente
            expedienteDoDia = cc.selecionaExpediente(diaDaSemana);

            entrada = expedienteDoDia.get(0);
            saida = expedienteDoDia.get(1);

            //System.out.println("entrada e saida " + entrada + " " + saida);
            LocalTime horarioEntrada = null;
            //itera pelos agendamentos
            for (Agendamento a : agendamentos) {

                if (horarios.size() == 0) {
                    horarioEntrada = entrada;
                }

                //System.out.println("Horario do Agendamento = " + a.getHorario());
                Long tempoComparado = null;
                try {
                    tempoComparado = horarioEntrada.until(a.getHorario(), ChronoUnit.NANOS);
                } catch (Exception e) {
                    System.out.println(e);
                }

                if (tempoComparado >= 0) {
                    try {
                        LocalTime saidaAtual = LocalTime.ofNanoOfDay(tempoComparado);
                        //System.out.println("Tempo entre o agedamento" + saidaAtual);
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

                //soma horas e minutos
                agendamentoAtual = agendamentoAtual.plusHours(horas);
                agendamentoAtual = agendamentoAtual.plusMinutes(minutos);
                
                //verifica se o tempo dos serviços do agendamento é maior que o fim do expediente
                if (agendamentoAtual.isAfter(saida)) {
                    break;
                }
                //System.out.println("Horario agendamento somado " + agendamentoAtual);
                horarioEntrada = agendamentoAtual;
                horarios.add(horarioEntrada);
            }

            horarios.add(0, entrada);

        } catch (ExceptionDAO ex) {
            Logger.getLogger(ManipulaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return horarios;
    }

    public ArrayList<String> formataHorariosDisponiveis(ArrayList<LocalTime> horarios) {

        ArrayList<String> horariosFormatados = new ArrayList<>();

        if (horarios != null) {
            int i = 0;
            int tamanho = horarios.size();
 
            for (i = 0; i < tamanho; i++) {

                if (i % 2 != 0) {
                    String formatado = horarios.get(i - 1).toString() + "h às " + horarios.get(i - 1).plusHours(horarios.get(i).getHour()).plusMinutes(horarios.get(i).getMinute()) + "h";
                    horariosFormatados.add(formatado);
                }

            }
        }else{
            horariosFormatados.add("Todos os horários estão \ndisponíveis.");
        }

        return horariosFormatados;

    }
}
