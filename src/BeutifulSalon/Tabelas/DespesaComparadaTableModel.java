/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Tabelas;

import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.controller.DespesaController;
import BeutifulSalon.controller.OrcamentoController;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Orcamento;
import BeutifulSalon.model.OrcamentoServico;
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
public class DespesaComparadaTableModel extends AbstractTableModel{
    
    List<Orcamento> dados;
    private final String[] columns = {"Despesa", "Janeiro", "Fevereiro", "Março",
        "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    public DespesaComparadaTableModel() {
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
    public String getColumnName(int rowIndex){
        return columns[rowIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {

            case 0 -> {
                return dados.get(rowIndex).getNome();
            }
            case 1 -> {
                return dados.get(rowIndex).getJan()+"%";
            }
            case 2 -> {
                return dados.get(rowIndex).getFev()+"%";
            }
            case 3 -> {
                return dados.get(rowIndex).getMar()+"%";
            }
            case 4 -> {
                return dados.get(rowIndex).getAbr()+"%";
            }
            case 5 -> {
                return dados.get(rowIndex).getMai()+"%";
            }
            case 6 -> {
                return dados.get(rowIndex).getJun()+"%";
            }
            case 7 -> {
                return dados.get(rowIndex).getJul()+"%";
            }
            case 8 -> {
                return dados.get(rowIndex).getAgo()+"%";
            }
            case 9 -> {
                return dados.get(rowIndex).getSet()+"%";
            }
            case 10 -> {
                return dados.get(rowIndex).getOut()+"%";
            }
            case 11 -> {
                return dados.get(rowIndex).getNov()+"%";
            }
            case 12 -> {
                return dados.get(rowIndex).getDez()+"%";
            }
        }
        return null;
    }
    
     public void removeRow(int rowIndex) {
        dados.remove(rowIndex);
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(Orcamento orcamento) {
        dados.add(orcamento);
        this.fireTableDataChanged();
    }

    public void addRow(List<Orcamento> orcamentos) {
        orcamentos.forEach(orc -> dados.add(orc));
        this.fireTableDataChanged();
    }
    
    public void getDespesaComparadaPorAno(String anoReferente){
        
        dados.clear();
        ManipulaData manipulaData = new ManipulaData();
        OrcamentoController oc = new OrcamentoController();
        DespesaController dc = new DespesaController();
        List<Orcamento> orcamentos = null;
        List<Orcamento> orcamentoComparado = new ArrayList<>();
        LocalDate ano;
        
        try {
            ano = LocalDate.ofYearDay(Integer.parseInt(anoReferente), 1);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar ano digitado" + e);
        }finally{
            ano = LocalDate.now();
        }

        try {
            orcamentos = oc.listarOrcamentos(anoReferente);
            
            for (Orcamento o : orcamentos) {
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
                        case JANUARY -> {
                            Long realizadoJan = dc.buscarDespesaPaga(ano, m.JANUARY, o.getId_orcamento()).getValorPago();
                            Long previstoJan = o.getJan();

                            janeiro = (realizadoJan.doubleValue() / previstoJan.doubleValue()) * 100;
                        }
                        case FEBRUARY -> {
                            Long realizadoFev = dc.buscarDespesaPaga(ano, m.FEBRUARY, o.getId_orcamento()).getValorPago();
                            Long previstoFev = o.getFev();

                            fevereiro = (realizadoFev.doubleValue() / previstoFev.doubleValue()) * 100;
                        }
                        case MARCH -> {
                            Long realizadoMar = dc.buscarDespesaPaga(ano, m.MARCH, o.getId_orcamento()).getValorPago();
                            Long previstoMar = o.getMar();

                            marco = (realizadoMar.doubleValue() / previstoMar.doubleValue()) * 100;
                        }
                        case APRIL -> {
                            Long realizadoAbr = dc.buscarDespesaPaga(ano, m.APRIL, o.getId_orcamento()).getValorPago();
                            Long previstoAbr = o.getAbr();

                            abril = (realizadoAbr.doubleValue() / previstoAbr.doubleValue()) * 100;
                        }
                        case MAY -> {
                            Long realizadoMai = dc.buscarDespesaPaga(ano, m.MAY, o.getId_orcamento()).getValorPago();
                            Long previstoMai = o.getMai();
                            maio = (realizadoMai.doubleValue() / previstoMai.doubleValue()) * 100;
                        }
                        case JUNE -> {
                            Long realizadoJun = dc.buscarDespesaPaga(ano, m.JUNE, o.getId_orcamento()).getValorPago();
                            Long previstoJun = o.getJun();
                            junho = (realizadoJun.doubleValue() / previstoJun.doubleValue()) * 100;
                        }
                        case JULY -> {
                            Long realizadoJul = dc.buscarDespesaPaga(ano, m.JULY, o.getId_orcamento()).getValorPago();
                            Long previstoJul = o.getJul();
                            julho = (realizadoJul.doubleValue() / previstoJul.doubleValue()) * 100;
                        }
                        case AUGUST -> {
                            Long realizadoAgo = dc.buscarDespesaPaga(ano, m.AUGUST, o.getId_orcamento()).getValorPago();
                            Long previstoAgo = o.getAgo();
                            agosto = (realizadoAgo.doubleValue() / previstoAgo.doubleValue()) * 100;
                        }
                        case SEPTEMBER -> {
                            Long realizadoSet = dc.buscarDespesaPaga(ano, m.SEPTEMBER, o.getId_orcamento()).getValorPago();
                            Long previstoSet = o.getSet();
                            setembro = (realizadoSet.doubleValue() / previstoSet.doubleValue()) * 100;
                        }
                        case OCTOBER -> {
                            Long realizadoOut = dc.buscarDespesaPaga(ano, m.OCTOBER, o.getId_orcamento()).getValorPago();
                            Long previstoOut = o.getOut();
                            setembro = (realizadoOut.doubleValue() / previstoOut.doubleValue()) * 100;
                        }
                        case NOVEMBER -> {
                            Long realizadoNov = dc.buscarDespesaPaga(ano, m.NOVEMBER, o.getId_orcamento()).getValorPago();
                            Long previstoNov = o.getNov();
                            novembro = (realizadoNov.doubleValue() / previstoNov.doubleValue()) * 100;
                        }
                        case DECEMBER -> {
                            Long realizadoDez = dc.buscarDespesaPaga(ano, m.DECEMBER, o.getId_orcamento()).getValorPago();
                            Long previstoDez = o.getDez();
                            novembro = (realizadoDez.doubleValue() / previstoDez.doubleValue()) * 100;
                        }
                    }
                }
                Orcamento orcamentoAtual = new Orcamento();
                orcamentoAtual.setNome(o.getNome());
                orcamentoAtual.setJan(janeiro.longValue());
                orcamentoAtual.setFev(fevereiro.longValue());
                orcamentoAtual.setMar(marco.longValue());
                orcamentoAtual.setAbr(abril.longValue());
                orcamentoAtual.setMai(maio.longValue());
                orcamentoAtual.setJun(junho.longValue());
                orcamentoAtual.setJul(julho.longValue());
                orcamentoAtual.setAgo(agosto.longValue());
                orcamentoAtual.setSet(setembro.longValue());
                orcamentoAtual.setOut(outubro.longValue());
                orcamentoAtual.setNov(novembro.longValue());
                orcamentoAtual.setDez(dezembro.longValue());
                orcamentoComparado.add(orcamentoAtual);
         
            }
            addRow(orcamentoComparado);

        } catch (ExceptionDAO e) {
            System.out.println(e);
        }
    }
    
}
