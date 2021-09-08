/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.controller.AgendamentoController;
import BeutifulSalon.controller.CabeleireiroController;
import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.Servico;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Date;
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

    public int calculaIdade(LocalDate dataNascimento) {

        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public long meiaNoite(LocalDate dia) {

        LocalTime meiaNoite = LocalTime.MIDNIGHT;
        LocalDateTime diff = LocalDateTime.of(dia, meiaNoite);

        long meiaNoiteMs = diff.toLocalDate().toEpochDay() * 24 * 60 * 60 * 1000;

        return meiaNoiteMs;
    }

    public int periodoDoDia(LocalDateTime horario) {

        int hora = horario.getHour();

        if (hora >= 6 && hora < 12) {
            return 0;
        } else if (hora < 18 && hora >= 12) {
            return 1;
        } else {
            return 2;
        }
    }

    public long meiaNoiteAmanha(LocalDate dia) {

        LocalTime meiaNoite = LocalTime.MIDNIGHT;
        LocalDateTime diff = LocalDateTime.of(dia, meiaNoite);
        LocalDateTime meiaNoiteAmannha = diff.plusDays(1);
        long meiaNoiteMs = meiaNoiteAmannha.toLocalDate().toEpochDay() * 24 * 60 * 60 * 1000;

        return meiaNoiteMs;
    }

    public Date localDateToDate(LocalDate data) {
        return java.sql.Date.valueOf(data);
    }

    public long MeiaNoiteAmanha() {

        LocalDate hoje = LocalDate.now();
        LocalTime meiaNoite = LocalTime.MIDNIGHT;
        LocalDateTime diff = LocalDateTime.of(hoje, meiaNoite);
        LocalDateTime meiaNoiteAmanha = diff.plusDays(1);

        long meiaNoiteAmanhaMs = meiaNoiteAmanha.toLocalDate().toEpochDay() * 24 * 60 * 60 * 1000;
        return meiaNoiteAmanhaMs;
    }

    public long inicioDoMes(LocalDate data, Month mes) {

        LocalDate dataMes = LocalDate.of(data.getYear(), mes, 1);

        long tempoEmMs = dataMes.toEpochDay() * 24 * 60 * 60 * 1000;

        return tempoEmMs;
    }

    public long fimDoMes(LocalDate data, Month mes) {

        LocalDate dataMes = LocalDate.of(data.getYear(), mes, 1);
        int maximoDias = dataMes.getMonth().length(dataMes.isLeapYear());
        LocalDate ultimoDiaDoMes = LocalDate.of(dataMes.getYear(), mes, maximoDias);
        long tempoEmMs = ultimoDiaDoMes.toEpochDay() * 24 * 60 * 60 * 1000;

        return tempoEmMs;
    }

    public ArrayList<Month> meses(LocalDate data) {

        ArrayList<Month> meses = new ArrayList<>();

        //primeiro dia do ano referente a data passada por parametro
        LocalDate dataModificada = LocalDate.of(data.getYear(), 1, 1);

        meses.add(dataModificada.getMonth());
        for (int i = 2; i < 12; i++) {

            //soma-se meses referente ao ano para evitar problemas com qtd de dias;
            Month mesAtual = dataModificada.plusMonths(i).getMonth();
            meses.add(mesAtual);
        }

        return meses;
    }

    public long somaDia(LocalDateTime diaAtual, long qtdDias) {

        LocalDateTime soma = diaAtual.plusDays(qtdDias);

        long somaMs = soma.toLocalDate().toEpochDay() * 24 * 60 * 60 * 1000;

        return somaMs;
    }

    public ArrayList<LocalTime> recuperaHorariosDisponiveis(LocalDate data) {

        ArrayList<LocalTime> horarios = new ArrayList<>(); // array de horarios disponivel ({inicio, tempoAtéPróximoAgendamento})
        ArrayList<Agendamento> agendamentos; // agendamentos do dia
        ArrayList<LocalTime> expedienteDoDia; // expediente do dia da semana referente a data    

        LocalTime entrada;
        LocalTime saida;
        int diaDaSemana = 0; // 1 (segunda) -  7 (Domingo)

        AgendamentoController ag = new AgendamentoController();
        CabeleireiroController cc = new CabeleireiroController();

        //busca agendamentos do dia inserido na tela de agendamento
        agendamentos = ag.listarAgendamentosRealizados(data);
        if (agendamentos.isEmpty()) {
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

            if (horarios.isEmpty()) {
                horarioEntrada = entrada;
            }
            //System.out.println("Tempo de inicio " + horarioEntrada);
            //System.out.println("Próximo Agendamento = " + a.getHorario());
            Long tempoComparado = null;
            try {
                tempoComparado = horarioEntrada.until(a.getHorario(), ChronoUnit.NANOS);
                //System.out.println("Tempo comparado " + tempoComparado);
            } catch (Exception e) {
                System.out.println(e);
            }

            if (tempoComparado >= 0) {
                try {
                    LocalTime saidaAtual = LocalTime.ofNanoOfDay(tempoComparado);
                    //System.out.println("Tempo entre o proximo agendamento" + saidaAtual);
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
        if (!horarioEntrada.isAfter(saida)) {
            LocalTime ultimoHorario = saida.minusHours(horarios.get(horarios.size() - 1).getHour()).minusMinutes(horarios.get(horarios.size() - 1).getMinute());
            horarios.add(ultimoHorario);
        }

        horarios.add(0, entrada);

        return horarios;
    }

    public boolean validaHorarioAgendamento(LocalTime entradaCliente, LocalTime terminoAgendamento, LocalDate data) {

        ArrayList<LocalTime> horarios = recuperaHorariosDisponiveis(data);
        boolean livre = false;

        if (horarios != null) {
            int i = 0;
            int tamanho = horarios.size();

            for (i = 0; i < tamanho; i++) {

                if (i % 2 != 0) {
                    LocalTime entrada = horarios.get(i - 1);
                    LocalTime saida = horarios.get(i - 1).plusHours(horarios.get(i).getHour()).plusMinutes(horarios.get(i).getMinute());
        
                    if (!entrada.equals(saida)) {
                       if(entradaCliente.isAfter(entrada) || entradaCliente.equals(entrada)){
                           if( terminoAgendamento.isBefore(saida) || terminoAgendamento.equals(saida)){
                               livre = true;
                               return livre;
                           }
                       }
                    }

                }

            }
        } else {
            return true;
        }
        return livre;
    }

    public ArrayList<String> formataHorariosDisponiveis(ArrayList<LocalTime> horarios) {

        ArrayList<String> horariosFormatados = new ArrayList<>();

        if (horarios != null) {
            int i = 0;
            int tamanho = horarios.size();

            for (i = 0; i < tamanho; i++) {

                if (i % 2 != 0) {
                    String entrada = horarios.get(i - 1).toString();
                    String saida = horarios.get(i - 1).plusHours(horarios.get(i).getHour()).plusMinutes(horarios.get(i).getMinute()).toString();

                    if (!entrada.equals(saida)) {
                        String formatado = entrada + "h às " + saida + "h";
                        horariosFormatados.add(formatado);
                    }
                }

            }
        } else {
            horariosFormatados.add("Não existem agendamentos neste dia.");
        }

        return horariosFormatados;

    }
}
