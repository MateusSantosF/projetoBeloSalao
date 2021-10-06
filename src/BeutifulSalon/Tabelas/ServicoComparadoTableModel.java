/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Tabelas;

import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.controller.OrcamentoController;
import BeutifulSalon.controller.ServicoController;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.OrcamentoServico;
import BeutifulSalon.model.Servico;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mateu
 */
public class ServicoComparadoTableModel extends AbstractTableModel {

    private final List<OrcamentoServico> dados;
    private final String[] columns = {"Serviço", "Janeiro", "Fevereiro", "Março",
        "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    public ServicoComparadoTableModel() {
        this.dados = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {

            case 0:
                return dados.get(rowIndex).getNome();
            case 1:
                return dados.get(rowIndex).getJan() + "%";
            case 2:
                return dados.get(rowIndex).getFev() + "%";
            case 3:
                return dados.get(rowIndex).getMar() + "%";
            case 4:
                return dados.get(rowIndex).getAbr() + "%";
            case 5:
                return dados.get(rowIndex).getMai() + "%";
            case 6:
                return dados.get(rowIndex).getJun() + "%";
            case 7:
                return dados.get(rowIndex).getJul() + "%";
            case 8:
                return dados.get(rowIndex).getAgo() + "%";
            case 9:
                return dados.get(rowIndex).getSet() + "%";
            case 10:
                return dados.get(rowIndex).getOut() + "%";
            case 11:
                return dados.get(rowIndex).getNov() + "%";
            case 12:
                return dados.get(rowIndex).getDez() + "%";
            default:
                return null;
        }

    }

    public void removeRow(int rowIndex) {
        dados.remove(rowIndex);
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(OrcamentoServico orcamentoServico) {
        dados.add(orcamentoServico);
        this.fireTableDataChanged();
    }

    public void addRow(List<OrcamentoServico> orcamentoServico) {
        orcamentoServico.forEach(orcS -> dados.add(orcS));
        this.fireTableDataChanged();
    }

    public void getOrcamentoComparadoPorAno(String anoReferente) {

        dados.clear();
        List<OrcamentoServico> orcamentos = null;
        List<OrcamentoServico> orcamentoComparado = new ArrayList<>();
        Servico servicoAtual = null;
        LocalDate ano;
        ManipulaData manipulaData = new ManipulaData();
        OrcamentoController oc = new OrcamentoController();
        ServicoController sc = new ServicoController();

        try {
            ano = LocalDate.ofYearDay(Integer.parseInt(anoReferente), 1);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar ano digitado" + e);
        } finally {
            ano = LocalDate.now();
        }

        orcamentos = oc.listarOrcamentosServico(anoReferente);
        for (OrcamentoServico orcamento : orcamentos) {

            servicoAtual = sc.buscarServico(orcamento.getId_servico());

            Double janeiro = 0.0;
            Double fevereiro = 0.0;
            Double marco = 0.0;
            Double abril = 0.0;
            Double maio = 0.0;
            Double junho = 0.0;
            Double julho = 0.0;
            Double agosto = 0.0;
            Double setembro = 0.0;
            Double outubro = 0.0;
            Double novembro = 0.0;
            Double dezembro = 0.0;

            for (Month m : manipulaData.meses(ano)) {

                switch (m) {
                    case JANUARY:
                        Long valorPrevistoJan = orcamento.getJan() * servicoAtual.getPreco();
                        if (valorPrevistoJan > 0) {
                            Long valorRealizadoJan = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.JANUARY),
                                    manipulaData.fimDoMes(ano, Month.JANUARY),
                                    orcamento.getId_servico());

                            janeiro = (valorRealizadoJan.doubleValue() / valorPrevistoJan.doubleValue()) * 100;
                        } else {
                            janeiro = 0.0;
                        }

                        break;

                    case FEBRUARY:
                        Long valorPrevistoFev = orcamento.getFev() * servicoAtual.getPreco();
                        if (valorPrevistoFev > 0) {
                            Long valorRealizadoFev = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.FEBRUARY),
                                    manipulaData.fimDoMes(ano, Month.FEBRUARY),
                                    orcamento.getId_servico());

                            fevereiro = (valorRealizadoFev.doubleValue() / valorPrevistoFev.doubleValue()) * 100;
                        } else {
                            fevereiro = 0.0;
                        }
                        break;
                    case MARCH:
                        Long valorPrevistoMar = orcamento.getMar() * servicoAtual.getPreco();
                        if (valorPrevistoMar > 0) {
                            Long valorRealizadoMar = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.MARCH),
                                    manipulaData.fimDoMes(ano, Month.MARCH),
                                    orcamento.getId_servico());

                            marco = (valorRealizadoMar.doubleValue() / valorPrevistoMar.doubleValue()) * 100;
                        } else {
                            marco = 0.0;
                        }
                        break;
                    case APRIL:
                        Long valorPrevistoAbr = orcamento.getAbr() * servicoAtual.getPreco();
                        if (valorPrevistoAbr > 0) {
                            Long valorRealizadoAbr = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.APRIL),
                                    manipulaData.fimDoMes(ano, Month.APRIL),
                                    orcamento.getId_servico());

                            abril = (valorRealizadoAbr.doubleValue() / valorPrevistoAbr.doubleValue()) * 100;
                        } else {
                            abril = 0.0;
                        }

                        break;
                    case MAY:
                        Long valorPrevistoMai = orcamento.getMai() * servicoAtual.getPreco();
                        if (valorPrevistoMai > 0) {
                            Long valorRealizadoMai = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.MAY),
                                    manipulaData.fimDoMes(ano, Month.MAY),
                                    orcamento.getId_servico());

                            maio = (valorRealizadoMai.doubleValue() / valorPrevistoMai.doubleValue()) * 100;
                        } else {
                            maio = 0.0;
                        }

                        break;
                    case JUNE:
                        Long valorPrevistoJun = orcamento.getJun() * servicoAtual.getPreco();
                        if (valorPrevistoJun > 0) {
                            Long valorRealizadoJun = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.JUNE),
                                    manipulaData.fimDoMes(ano, Month.JUNE),
                                    orcamento.getId_servico());

                            junho = (valorRealizadoJun.doubleValue() / valorPrevistoJun.doubleValue()) * 100;
                        } else {
                            junho = 0.0;
                        }

                        break;
                    case JULY:
                        Long valorPrevistoJul = orcamento.getJul() * servicoAtual.getPreco();
                        if (valorPrevistoJul > 0) {
                            Long valorRealizadoJul = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.JULY),
                                    manipulaData.fimDoMes(ano, Month.JULY),
                                    orcamento.getId_servico());

                            julho = (valorRealizadoJul.doubleValue() / valorPrevistoJul.doubleValue()) * 100;
                        } else {
                            julho = 0.0;
                        }

                        break;
                    case AUGUST:
                        Long valorPrevistoAgo = orcamento.getAgo() * servicoAtual.getPreco();
                        if (valorPrevistoAgo > 0) {
                            Long valorRealizadoAgo = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.AUGUST),
                                    manipulaData.fimDoMes(ano, Month.AUGUST),
                                    orcamento.getId_servico());

                            agosto = (valorRealizadoAgo.doubleValue() / valorPrevistoAgo.doubleValue()) * 100;
                        } else {
                            agosto = 0.0;
                        }

                        break;
                    case SEPTEMBER:
                        Long valorPrevistoSet = orcamento.getSet() * servicoAtual.getPreco();
                        if (valorPrevistoSet > 0) {
                            Long valorRealizadoSet = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.SEPTEMBER),
                                    manipulaData.fimDoMes(ano, Month.SEPTEMBER),
                                    orcamento.getId_servico());

                            setembro = (valorRealizadoSet.doubleValue() / valorPrevistoSet.doubleValue()) * 100;
                        } else {
                            setembro = 0.0;
                        }

                        break;
                    case OCTOBER:
                        Long valorPrevistoOut = orcamento.getOut() * servicoAtual.getPreco();
                        if (valorPrevistoOut > 0) {
                            Long valorRealizadoOut = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.OCTOBER),
                                    manipulaData.fimDoMes(ano, Month.OCTOBER),
                                    orcamento.getId_servico());

                            outubro = (valorRealizadoOut.doubleValue() / valorPrevistoOut.doubleValue()) * 100;
                        } else {
                            outubro = 0.0;
                        }

                        break;
                    case NOVEMBER:

                        Long valorPrevistoNov = orcamento.getNov() * servicoAtual.getPreco();
                        if (valorPrevistoNov > 0) {
                            Long valorRealizadoNov = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.NOVEMBER),
                                    manipulaData.fimDoMes(ano, Month.NOVEMBER),
                                    orcamento.getId_servico());

                            novembro = (valorRealizadoNov.doubleValue() / valorPrevistoNov.doubleValue()) * 100;

                        } else {
                            novembro = 0.0;
                        }

                        break;
                    case DECEMBER:
                        Long valorPrevistoDez = orcamento.getNov() * servicoAtual.getPreco();
                        if (valorPrevistoDez > 0) {
                            Long valorRealizadoDez = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.DECEMBER),
                                    manipulaData.fimDoMes(ano, Month.DECEMBER),
                                    orcamento.getId_servico());

                            dezembro = (valorRealizadoDez.doubleValue() / valorPrevistoDez.doubleValue()) * 100;
                        } else {
                            dezembro = 0.0;
                        }

                }

            }
            OrcamentoServico orcamentoServicoAtual = new OrcamentoServico();
            orcamentoServicoAtual.setNome(orcamento.getNome());
            orcamentoServicoAtual.setJan(janeiro.longValue());
            orcamentoServicoAtual.setFev(fevereiro.longValue());
            orcamentoServicoAtual.setMar(marco.longValue());
            orcamentoServicoAtual.setAbr(abril.longValue());
            orcamentoServicoAtual.setMai(maio.longValue());
            orcamentoServicoAtual.setJun(junho.longValue());
            orcamentoServicoAtual.setJul(julho.longValue());
            orcamentoServicoAtual.setAgo(agosto.longValue());
            orcamentoServicoAtual.setSet(setembro.longValue());
            orcamentoServicoAtual.setOut(outubro.longValue());
            orcamentoServicoAtual.setNov(novembro.longValue());
            orcamentoServicoAtual.setDez(dezembro.longValue());
            orcamentoComparado.add(orcamentoServicoAtual);

        }
        addRow(orcamentoComparado);
    }
}
