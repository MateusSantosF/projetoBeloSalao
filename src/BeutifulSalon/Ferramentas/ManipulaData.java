/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.controller.AgendamentoController;
import BeutifulSalon.controller.CabeleireiroController;
import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.Cabeleireiro;
import BeutifulSalon.model.Servico;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        try {
            return Period.between(dataNascimento, LocalDate.now()).getYears();
        } catch (DateTimeParseException e) {
        }
        return 0;
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

    public List<LocalTime> recuperaHorariosDisponiveis(LocalDate data) {

        List<LocalTime> horarios = new ArrayList<>();
        CabeleireiroController cc = new CabeleireiroController();
        Cabeleireiro cabeleireiro = cc.selecionaCabeleireiro();
        AgendamentoController ag = new AgendamentoController();

        //busca agendamentos do dia inserido na tela de agendamento
        ArrayList<Agendamento> agendamentos = ag.listarAgendamentosRealizados(data);

        ArrayList<LocalTime> expedienteDoDia;
        LocalTime entradaExpediente;
        LocalTime saidaExpediente;
        int diaDaSemana = 1; // 1 (segunda) -  7 (Domingo)

        diaDaSemana = data.getDayOfWeek().getValue();

        //recupera horario de inicio e término do expediente
        expedienteDoDia = cc.selecionaExpediente(diaDaSemana);
        entradaExpediente = expedienteDoDia.get(0);
        saidaExpediente = expedienteDoDia.get(1);

        LocalTime horarioEntrada = entradaExpediente;

        for (Agendamento g : agendamentos) {

            //se é ANTES do agendamento e MAIOR que 15 minutos
            if (horarioEntrada.isBefore(g.getHorario())) {

                if (horarioEntrada.until(g.getHorario(), ChronoUnit.MINUTES) >= cabeleireiro.getTempoEntreHorariosLivres()) {
                    horarios.add(horarioEntrada);
                    horarios.add(g.getHorario());
                }

            }
            horarioEntrada = g.getFimAgendamento();

        }

        if (horarioEntrada.isBefore(saidaExpediente)) {
            horarios.add(horarioEntrada);
            horarios.add(saidaExpediente);
        }

  

        return horarios;
    }

    public boolean validaHorarioAgendamento(LocalTime entradaCliente, LocalTime terminoAgendamento, LocalDate data) {

        CabeleireiroController cc = new CabeleireiroController();
        Cabeleireiro cabeleireiro = cc.selecionaCabeleireiro();
        System.out.println(cabeleireiro.isVerificarHorariosDisponiveis());
        if (cabeleireiro.isVerificarHorariosDisponiveis()) {
            return true;
        }

        AgendamentoController ag = new AgendamentoController();

        //busca agendamentos do dia inserido na tela de agendamento
        ArrayList<Agendamento> agendamentos = ag.listarAgendamentosRealizados(data);
        boolean estaLivre = false;
        long inicio = entradaCliente.toNanoOfDay();
        long fim = terminoAgendamento.toNanoOfDay();

        ArrayList<LocalTime> expedienteDoDia;
        LocalTime entrada;
        LocalTime saida;
        int diaDaSemana = 1; // 1 (segunda) -  7 (Domingo)

        diaDaSemana = data.getDayOfWeek().getValue();

        //recupera horario de inicio e término do expediente
        expedienteDoDia = cc.selecionaExpediente(diaDaSemana);
        entrada = expedienteDoDia.get(0);
        saida = expedienteDoDia.get(1);

        if (!(inicio >= entrada.toNanoOfDay())) {
            JOptionPane.showMessageDialog(null, "Você não pode marcar um agendamento antes do seu horário de expediente.");
            return false;
        }

        Agendamento ultimoAgendamentoProcessado = null;
        System.out.println("============================");
        System.out.println("Inicio A1=>" + entradaCliente);
        System.out.println("FIM    A1=>" + terminoAgendamento);

        if (agendamentos.size() > 0) {

            for (Agendamento g : agendamentos) {

                System.out.println("Inicio A2=>" + g.getHorario());
                System.out.println("FIM    A2=>" + g.getFimAgendamento());
                long inicioTemp = g.getHorario().toNanoOfDay();
                long fimTemp = g.getFimAgendamento().toNanoOfDay();
                System.out.println("Inicio" + inicio);
                System.out.println("Fim" + fim);
                System.out.println("Iniciotemp" + inicioTemp);
                System.out.println("Fimtemp" + fimTemp);

                if (inicio >= fimTemp || fim <= inicioTemp) {
                    estaLivre = true;
                    System.out.println("TRUE");
                } else {
                    return false;
                }
                ultimoAgendamentoProcessado = g;
                System.out.println("============================");
            }

            if (ultimoAgendamentoProcessado.getFimAgendamento().toNanoOfDay() <= saida.toNanoOfDay()) {
                if (entradaCliente.isAfter(ultimoAgendamentoProcessado.getFimAgendamento())) {
                    estaLivre = true;
                }

            }

            System.out.println("============================");
            return estaLivre;

        } else {
            return true;
        }

    }

    public boolean validaHorarioAgendamentoEdit(LocalTime entradaCliente, LocalTime terminoAgendamento, LocalDate data, long idAg) {

        AgendamentoController ag = new AgendamentoController();
        CabeleireiroController cc = new CabeleireiroController();

        //busca agendamentos do dia inserido na tela de agendamento
        ArrayList<Agendamento> agendamentos = ag.listarAgendamentosRealizados(data);
        boolean estaLivre = false;
        long inicio = entradaCliente.toNanoOfDay();
        long fim = terminoAgendamento.toNanoOfDay();

        ArrayList<LocalTime> expedienteDoDia;
        LocalTime entrada;
        LocalTime saida;
        int diaDaSemana = 1; // 1 (segunda) -  7 (Domingo)
        Agendamento ultimoAgendamentoProcessado = null;

        if (agendamentos.size() > 0) {

            for (Agendamento g : agendamentos) {
                if (g.getIdAgendamento() != idAg) {

                    long inicioTemp = g.getHorario().toNanoOfDay();
                    long fimTemp = g.getFimAgendamento().toNanoOfDay();

                    if (inicio >= fimTemp || fim <= inicioTemp) {
                        estaLivre = true;

                    } else {
                        return false;
                    }
                    ultimoAgendamentoProcessado = g;

                }

            }

            diaDaSemana = data.getDayOfWeek().getValue();

            //recupera horario de inicio e término do expediente
            expedienteDoDia = cc.selecionaExpediente(diaDaSemana);
            entrada = expedienteDoDia.get(0);
            saida = expedienteDoDia.get(1);

            if (ultimoAgendamentoProcessado.getFimAgendamento().toNanoOfDay() <= saida.toNanoOfDay()) {
                if (entradaCliente.isAfter(ultimoAgendamentoProcessado.getFimAgendamento())) {
                    estaLivre = true;
                }

            }

            return estaLivre;

        } else {
            return true;
        }

    }

    public ArrayList<String> formataHorariosDisponiveis(List<LocalTime> horarios, LocalDate dataBuscada) {

        ArrayList<String> horariosFormatados = new ArrayList<>();

        int tamanho = horarios.size();

        for (int i = 0; i < tamanho - 1; i++) {

            String entrada = horarios.get(i).toString();
            String saida = horarios.get(i + 1).toString();
            String formatado = entrada + "h até " + saida + "h";
            horariosFormatados.add(formatado);
        }

        return horariosFormatados;

    }

}
