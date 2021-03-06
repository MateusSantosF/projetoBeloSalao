/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.Ferramentas.FichaAgendamento;
import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.Ferramentas.Valida;
import BeutifulSalon.dao.AgendamentoDAO;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.Item;
import BeutifulSalon.model.Servico;
import BeutifulSalon.view.Edicao.EditarAgendamento;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author mateus
 */
public class AgendamentoController {

    public boolean cadastraAgendamento(String data, String horario, long idCliente, List<Servico> servicos,
            List<Item> produtosComprados, long total,
            long desconto, long valorAdicional, boolean realizado, boolean pago, String FormaDePagamento, long idColaborador) throws ExceptionDAO {

        Agendamento ag = null;
        if (Valida.isHora(horario) && !servicos.isEmpty() && idCliente > 0) {

            if (total < 0) {
                JOptionPane.showMessageDialog(null, "Você não pode registrar um agendamento com valores negativos.");
                return false;
            }
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

            LocalTime fimAgendamento = h;
            int horas = 0;
            int minutos = 0;
            servicos.remove(servicos.size() - 1);
            for (Servico s : servicos) {

                Servico sAtual = new ServicoController().buscarServico(s.getId());
                horas += sAtual.getTempoGasto().getHour();
                minutos += sAtual.getTempoGasto().getMinute();
            }
            fimAgendamento = fimAgendamento.plusHours(horas);
            fimAgendamento = fimAgendamento.plusMinutes(minutos);

            if (new ManipulaData().validaHorarioAgendamento(h, fimAgendamento, dataAgendamento) == false) {
                JOptionPane.showMessageDialog(null, "Horário não disponível");
                return false;
            }
            //RETIRA LINHA DE TEMPO TOTAL DOS SERVIÇOS

            Agendamento agendamento = new Agendamento();
            agendamento.setProdutosComprados(produtosComprados);
            agendamento.setFimAgendamento(fimAgendamento);
            //RETIRANDO VALOR DOS PRODUTOS COMPRADOS DO TOTAL DO AGENDAMENTO, PARA GARANTIR UNIDADE
            if (produtosComprados.size() >= 1) {
                for (Item i : produtosComprados) {
                    total -= i.getPrecoTotal();
                }
            }
            agendamento.setTotal(total);
            agendamento.setDesconto(desconto);
            agendamento.setIdCliente(idCliente);
            agendamento.setData(dataAgendamento);
            agendamento.setHorario(h);
            agendamento.setServicos(servicos);
            agendamento.setRealizado(realizado);
            agendamento.setValorAdicional(valorAdicional);
            agendamento.setPago(pago);
            agendamento.setFormaDePagamento(FormaDePagamento);
            agendamento.setIdColaborador(idColaborador);

            try {
                agendamento.cadastraAgendamento(agendamento);
                ag = agendamento;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "AgendamentoController" + e);
                return false;
            }

        } else {
            return false;
        }

        int opc = JOptionPane.showConfirmDialog(null, "Deseja imprimir a ficha de agendamento?", "Imprimir Ficha", JOptionPane.YES_NO_OPTION);

        if (opc == 0 && ag != null) {
            new FichaAgendamento().imprimirFicha(ag);
        }

        return true;
    }

    public boolean atualizarAgendamento(String data, String horario, long idCliente, List<Servico> servicos, long total,
            long desconto, boolean realizado, long idAgendamento, long valorAdicional, boolean pago, String formaDePagamento,
            LocalTime horarioInicioAntigo, LocalTime horarioFinalAntigo,long idColaborador, List<Item> itensComprados, List<Item> itensCompradosAntigos) throws ExceptionDAO {
        
        if (Valida.isHora(horario) && servicos.size() > 1) {

            if (total < 0) {
                JOptionPane.showMessageDialog(null, "Você não pode registrar um agendamento com valores negativos.");
                return false;
            }
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

            LocalTime fimAgendamento = h;
            int horas = 0;
            int minutos = 0;
            //removendo linha do total de horas previstas
            servicos.remove(servicos.size() - 1);
            for (Servico s : servicos) {
                Servico sAtual = new ServicoController().buscarServico(s.getId());
                horas += sAtual.getTempoGasto().getHour();
                minutos += sAtual.getTempoGasto().getMinute();
            }
            fimAgendamento = fimAgendamento.plusHours(horas);
            fimAgendamento = fimAgendamento.plusMinutes(minutos);

            if (!h.equals(horarioInicioAntigo) && !fimAgendamento.equals(horarioFinalAntigo)) {
                if (!new ManipulaData().validaHorarioAgendamentoEdit(h, fimAgendamento, dataAgendamento, idAgendamento)) {
                    JOptionPane.showMessageDialog(null, "Horário não disponível");
                    return false;
                }
            }

            //Passando parametros
            
            Agendamento agendamento = new Agendamento();
            
            agendamento.setIdAgendamento(idAgendamento);
            agendamento.setIdColaborador(idColaborador);
            agendamento.setProdutosComprados(itensComprados);
            agendamento.setTotal(total);
            agendamento.setDesconto(desconto);
            agendamento.setIdCliente(idCliente);
            agendamento.setData(dataAgendamento);
            agendamento.setHorario(h);
            agendamento.setServicos(servicos);
            agendamento.setRealizado(realizado);
            agendamento.setValorAdicional(valorAdicional);
            agendamento.setPago(pago);
            agendamento.setFormaDePagamento(formaDePagamento);
            agendamento.setProdutosComprados(itensComprados);
            agendamento.setAntigosprodutosComprados(itensCompradosAntigos);

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

    public boolean editarAgendamento(Agendamento ag, EditarAgendamento edag) {

        if (ag != null) {

            edag.setVisible(true);
        } else {
            return false;
        }

        return true;
    }

    public Agendamento listarAgendamento(long idAgendamento) {

        try {
            return new Agendamento().listarAgendamento(idAgendamento);
        } catch (ExceptionDAO e) {
            return null;
        }

    }

    public List<Agendamento> listarAgendamentosPorData(LocalDate inicio, LocalDate fim) {
        try {
            return new Agendamento().listarAgendamentosPorData(inicio, fim);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public ArrayList<Servico> listarServicosAgendamento(long idAgendamento) {
        try {
            return new Agendamento().listarServicosAgendamento(idAgendamento);
        } catch (ExceptionDAO e) {
            System.out.println("Erro ao listar serviços do agendamento");
        }
        return null;
    }

    public ArrayList<Agendamento> listarAgendamentos() {
        try {
            return new Agendamento().listarAgendamentos();
        } catch (ExceptionDAO e) {
        }
        return null;
    }

    public ArrayList<Agendamento> listarAgendamentos(LocalDate data) throws ExceptionDAO {
        return new Agendamento().listarAgendamentos(data);
    }

    public ArrayList<Agendamento> listarAgendamentosRealizados(LocalDate data) {

        return new AgendamentoDAO().listarAgendamentosRealizados(data);
    }

    public ArrayList<Agendamento> listarAgendamentosHoje() {
        try {
            return new Agendamento().listarAgendamentosHoje();
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public ArrayList<Agendamento> listarAgendamentosAmanha() {
        try {
            return new Agendamento().listarAgendamentosAmanha();
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public ArrayList<Agendamento> listarAgendamentosSemana() {
        try {
            return new Agendamento().listarAgendamentosSemana();
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public ArrayList<Agendamento> listarAgendamentosNome(String nome) {
        try {
            return new Agendamento().listarAgendamentosNome(nome);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public List<Agendamento> listarAgendamentosIDCliente(long idCliente) {
        try {
            return new Agendamento().listarAgendamentosIDCliente(idCliente);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public ArrayList<Agendamento> listarAgendamentosNaoRealizados() {
        try {
            return new Agendamento().listarAgendamentosNaoRealizados();
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public long retornaSomaDeAgendamentosMensal() {
        try {
            return new Agendamento().retornaSomaDeAgendamentosMensal();
        } catch (ExceptionDAO e) {
            System.out.println("erro ao retornar soma mensal de agemdamentos");
        }
        return 0L;
    }

    public long retornaSomaDeLucrosAgendamentosMensal(Month mes) {
        try {
            return new Agendamento().retornaSomaDeAgendamentosMensal(mes);
        } catch (ExceptionDAO e) {
            System.out.println("erro ao retornar soma mensal de agemdamentos");
        }
        return 0L;
    }

    public boolean excluirAgendamento(Agendamento agendamento) {

        try {
            return agendamento.excluirAgendamento(agendamento);
        } catch (ExceptionDAO e) {
            return false;
        }

    }

    public ArrayList<Agendamento> listarAgendamentosNaoPagos() {
        try {
            return new Agendamento().listarAgendamentosNaoPagos();
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

}
