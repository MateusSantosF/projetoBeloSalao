/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Tabelas;

import BeutifulSalon.controller.OrcamentoController;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Orcamento;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mateu
 */
public class DespesaPrevistaTableModel extends AbstractTableModel {

    private final List<Orcamento> dados;
    private final String[] columns = {"Despesa", "Janeiro", "Fevereiro", "Março",
        "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    public DespesaPrevistaTableModel() {
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
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {

            case 0:
                return dados.get(rowIndex).getNome();

            case 1:
                return Dinheiro.parseString(dados.get(rowIndex).getJan());

            case 2:
                return Dinheiro.parseString(dados.get(rowIndex).getFev());
            case 3:
                return Dinheiro.parseString(dados.get(rowIndex).getMar());

            case 4:
                return Dinheiro.parseString(dados.get(rowIndex).getAbr());

            case 5:
                return Dinheiro.parseString(dados.get(rowIndex).getMai());

            case 6:
                return Dinheiro.parseString(dados.get(rowIndex).getJun());

            case 7:
                return Dinheiro.parseString(dados.get(rowIndex).getJul());

            case 8:
                return Dinheiro.parseString(dados.get(rowIndex).getAgo());

            case 9:
                return Dinheiro.parseString(dados.get(rowIndex).getSet());

            case 10:
                return Dinheiro.parseString(dados.get(rowIndex).getOut());

            case 11:
                return Dinheiro.parseString(dados.get(rowIndex).getNov());

            case 12:
                return Dinheiro.parseString(dados.get(rowIndex).getDez());
            default:
                return null;
        }

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

    public Orcamento getOrcamento(int rowIndex) {
        return dados.get(rowIndex);
    }

    public void getTodasDespesasPrevistas(String anoReferente) {
        dados.clear();
        OrcamentoController orcamentoController = new OrcamentoController();
        List<Orcamento> orcamentos = orcamentoController.listarOrcamentos(anoReferente);
        addRow(orcamentos);
        calculaTotal();
    }

    public void calculaTotal() {

        long janeiro, fevereiro, marco, abril, maio, junho, julho, agosto, setembro, outubro, novembro, dezembro;
        janeiro = fevereiro = marco = abril = maio = junho = julho = agosto = setembro = outubro = novembro = dezembro = 0;

        for (Orcamento ocs : dados) {
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

        Orcamento orcamentoTotal = new Orcamento();
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
