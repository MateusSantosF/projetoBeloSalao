/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Tabelas;

import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.controller.OrcamentoController;
import BeutifulSalon.controller.ServicoController;
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
public class ServicoRealizadoTableModel extends AbstractTableModel {

    private final List<OrcamentoServico> dados;
    private final String[] columns = {"Serviço", "Janeiro", "Fevereiro", "Março",
        "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    public ServicoRealizadoTableModel() {
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

            case 0 -> {
                return dados.get(rowIndex).getNome();
            }
            case 1 -> {
                return Dinheiro.parseString(dados.get(rowIndex).getJan());
            }
            case 2 -> {
                return Dinheiro.parseString(dados.get(rowIndex).getFev());
            }

            case 3 -> {
                return Dinheiro.parseString(dados.get(rowIndex).getMar());
            }

            case 4 -> {
                return Dinheiro.parseString(dados.get(rowIndex).getAbr());
            }
            case 5 -> {
                return Dinheiro.parseString(dados.get(rowIndex).getMai());
            }
            case 6 -> {
                return Dinheiro.parseString(dados.get(rowIndex).getJun());
            }
            case 7 -> {
                return Dinheiro.parseString(dados.get(rowIndex).getJul());
            }
            case 8 -> {
                return Dinheiro.parseString(dados.get(rowIndex).getAgo());
            }

            case 9 -> {
                return Dinheiro.parseString(dados.get(rowIndex).getSet());
            }

            case 10 -> {
                return Dinheiro.parseString(dados.get(rowIndex).getOut());
            }

            case 11 -> {
                return Dinheiro.parseString(dados.get(rowIndex).getNov());
            }

            case 12 -> {
                return Dinheiro.parseString(dados.get(rowIndex).getDez());
            }

        }
        return null;
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

    public void getTodosServicosRealizados(String anoReferente) {
        dados.clear();

        ServicoController sc = new ServicoController();
        OrcamentoController oc = new OrcamentoController();
        List<Servico> servicos = null;
        ArrayList<OrcamentoServico> orcamentos = new ArrayList<>();
        ManipulaData manipulaData = new ManipulaData();
        LocalDate ano = LocalDate.now();

        try {
            ano = LocalDate.ofYearDay(Integer.parseInt(anoReferente), 1);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar ano" + e);
        }
        servicos = sc.listarServicos();

        if (servicos != null) {

            for (Servico s : servicos) {

                long janeiro = 0;
                long fevereiro = 0;
                long marco = 0;
                long abril = 0;
                long maio = 0;
                long junho = 0;
                long julho = 0;
                long agosto = 0;
                long setembro = 0;
                long outubro = 0;
                long novembro = 0;
                long dezembro = 0;

                for (Month m : manipulaData.meses(ano)) {

                    switch (m) {
                        case JANUARY -> {
                            janeiro = oc.listarOrcamentoServicorRealizado(ano, Month.JANUARY, s.getId()).getQuantidadeMensal();
                            janeiro *= s.getPreco();
                        }
                        case FEBRUARY -> {
                            fevereiro = oc.listarOrcamentoServicorRealizado(ano, Month.FEBRUARY, s.getId()).getQuantidadeMensal();
                            fevereiro *= s.getPreco();
                        }
                        case MARCH -> {
                            marco = oc.listarOrcamentoServicorRealizado(ano, Month.MARCH, s.getId()).getQuantidadeMensal();
                            marco *= s.getPreco();
                        }
                        case APRIL -> {
                            abril = oc.listarOrcamentoServicorRealizado(ano, Month.APRIL, s.getId()).getQuantidadeMensal();
                            abril *= s.getPreco();
                        }
                        case MAY -> {
                            maio = oc.listarOrcamentoServicorRealizado(ano, Month.MAY, s.getId()).getQuantidadeMensal();
                            maio *= s.getPreco();
                        }
                        case JUNE -> {
                            junho = oc.listarOrcamentoServicorRealizado(ano, Month.JUNE, s.getId()).getQuantidadeMensal();
                            junho *= s.getPreco();
                        }
                        case JULY -> {
                            julho = oc.listarOrcamentoServicorRealizado(ano, Month.JULY, s.getId()).getQuantidadeMensal();
                            julho *= s.getPreco();
                        }
                        case AUGUST -> {
                            agosto = oc.listarOrcamentoServicorRealizado(ano, Month.AUGUST, s.getId()).getQuantidadeMensal();
                            agosto *= s.getPreco();
                        }
                        case SEPTEMBER -> {
                            setembro = oc.listarOrcamentoServicorRealizado(ano, Month.SEPTEMBER, s.getId()).getQuantidadeMensal();
                            setembro *= s.getPreco();
                        }
                        case OCTOBER -> {
                            outubro = oc.listarOrcamentoServicorRealizado(ano, Month.OCTOBER, s.getId()).getQuantidadeMensal();
                            outubro *= s.getPreco();
                        }
                        case NOVEMBER -> {
                            novembro = oc.listarOrcamentoServicorRealizado(ano, Month.NOVEMBER, s.getId()).getQuantidadeMensal();
                            novembro *= s.getPreco();
                        }
                        case DECEMBER -> {
                            dezembro = oc.listarOrcamentoServicorRealizado(ano, Month.DECEMBER, s.getId()).getQuantidadeMensal();
                            dezembro *= s.getPreco();
                        }
                    }

                }

                OrcamentoServico orcamentoAtual = new OrcamentoServico();
                orcamentoAtual.setNome(s.getNome());
                orcamentoAtual.setJan(janeiro);
                orcamentoAtual.setFev(fevereiro);
                orcamentoAtual.setMar(marco);
                orcamentoAtual.setAbr(abril);
                orcamentoAtual.setMai(maio);
                orcamentoAtual.setJun(junho);
                orcamentoAtual.setJul(julho);
                orcamentoAtual.setAgo(agosto);
                orcamentoAtual.setSet(setembro);
                orcamentoAtual.setOut(outubro);
                orcamentoAtual.setNov(novembro);
                orcamentoAtual.setDez(dezembro);

                orcamentos.add(orcamentoAtual);
            }
        }

        addRow(orcamentos);
        calculaTotalServicosRealizados();
    }

    public void calculaTotalServicosRealizados() {

        long janeiro, fevereiro, marco, abril, maio, junho, julho, agosto, setembro, outubro, novembro, dezembro;
        janeiro = fevereiro = marco = abril = maio = junho = julho = agosto = setembro = outubro = novembro = dezembro = 0;

        for (OrcamentoServico ocs : dados) {
            janeiro += ocs.getJan();
            fevereiro += ocs.getFev();
            marco += ocs.getMar();
            abril += ocs.getAbr();
            maio += ocs.getMai();
            junho += ocs.getJun();
            julho += ocs.getJul();
            agosto += ocs.getAgo();
            setembro += ocs.getSet();
            outubro += ocs.getOut();
            novembro += ocs.getNov();
            dezembro += ocs.getDez();
        }

        OrcamentoServico orcamentoTotal = new OrcamentoServico();
        orcamentoTotal.setNome("TOTAL");
        orcamentoTotal.setJan(janeiro);
        orcamentoTotal.setFev(fevereiro);
        orcamentoTotal.setMar(marco);
        orcamentoTotal.setAbr(abril);
        orcamentoTotal.setMai(maio);
        orcamentoTotal.setJun(junho);
        orcamentoTotal.setJul(julho);
        orcamentoTotal.setAgo(agosto);
        orcamentoTotal.setSet(setembro);
        orcamentoTotal.setOut(outubro);
        orcamentoTotal.setNov(novembro);
        orcamentoTotal.setDez(dezembro);
        addRow(orcamentoTotal);
    }

}
