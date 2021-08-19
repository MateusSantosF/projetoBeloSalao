/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Tabelas;

import BeutifulSalon.controller.OrcamentoController;
import BeutifulSalon.controller.ServicoController;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.OrcamentoServico;
import BeutifulSalon.model.Servico;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mateu
 */
public class ServicoPrevistoTableModel extends AbstractTableModel {
    
    private final List<OrcamentoServico> dados;
    private final String[] columns = {"Serviço", "Janeiro", "Fevereiro", "Março",
        "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    public ServicoPrevistoTableModel() {
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
     public void addRow(OrcamentoServico orcamentoServico) {
        dados.add(orcamentoServico);
        this.fireTableDataChanged();
    }

    public void addRow(List<OrcamentoServico> orcamentoServico) {
        orcamentoServico.forEach(orcS -> dados.add(orcS));
        this.fireTableDataChanged();
    }
    public void getServicoPrevisto(String anoReferente){
         
        dados.clear();
        List<OrcamentoServico> orcamentos = null;
        List<OrcamentoServico> orcamentoPrevisto = new ArrayList<>();
        OrcamentoController oc = new OrcamentoController();
        ServicoController sc = new ServicoController();
        
        orcamentos = oc.listarOrcamentosServico(anoReferente);
  
            for (OrcamentoServico orcamento : orcamentos) {

                Servico servicoAtual = sc.buscarServico(orcamento.getId_servico());
                long preco = servicoAtual.getPreco();
                OrcamentoServico orcamentoServicoAtual = new OrcamentoServico();
                orcamentoServicoAtual.setNome(orcamento.getNome());
                orcamentoServicoAtual.setJan(orcamento.getJan() * preco);
                orcamentoServicoAtual.setFev(orcamento.getFev() * preco);
                orcamentoServicoAtual.setMar(orcamento.getMar() * preco);
                orcamentoServicoAtual.setAbr(orcamento.getAbr() * preco);
                orcamentoServicoAtual.setMai(orcamento.getMai() * preco);
                orcamentoServicoAtual.setJun(orcamento.getJun() * preco);
                orcamentoServicoAtual.setJul(orcamento.getJul() * preco);
                orcamentoServicoAtual.setAgo(orcamento.getAgo() * preco);
                orcamentoServicoAtual.setSet(orcamento.getSet() * preco);
                orcamentoServicoAtual.setOut(orcamento.getOut() * preco);
                orcamentoServicoAtual.setNov(orcamento.getNov() * preco);
                orcamentoServicoAtual.setDez(orcamento.getDez() * preco);
                
                orcamentoPrevisto.add(orcamentoServicoAtual);
            }        
        addRow(orcamentoPrevisto);
        calculaTotalServicosPrevisto();
    }
    public void calculaTotalServicosPrevisto() {

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
