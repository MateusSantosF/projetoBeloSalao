/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Tabelas;

import BeutifulSalon.controller.DespesaController;
import BeutifulSalon.controller.OrcamentoController;
import BeutifulSalon.model.Despesa;
import BeutifulSalon.model.Dinheiro;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mateu
 */
public class LancamentoTableModel extends AbstractTableModel {
    
    private List<Despesa> dados;
    private final String[] columns = {"Nome", "Data Lançamento", "Data Vencimento",
        "Data Pagamento", "Forma Pagamento", "Valor Pago", "Status"};
    private final OrcamentoController orcamentoController;
    private final DespesaController despesaController;
    
    public LancamentoTableModel() {
        this.dados = new ArrayList<>();
        orcamentoController = new OrcamentoController();
        despesaController = new DespesaController();
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
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        switch (columnIndex) {
            
            case 0 -> {
                return orcamentoController.buscarOrcamento(dados.get(rowIndex).getIdOrcamento()).getNome();
            }            
            case 1 -> {
                return dados.get(rowIndex).getLancameto().format(formatterData);
            }
            
            case 2 -> {
                return dados.get(rowIndex).getVencimento().format(formatterData);
            }
            case 3 -> {
                if (dados.get(rowIndex).getPagamento() == null) {
                    return " -- ";
                } else {
                    return dados.get(rowIndex).getPagamento().format(formatterData);
                }
            }
            
            case 4 ->{
                return dados.get(rowIndex).getFormaPagamento();
            }
            case 5 -> {
                return Dinheiro.parseString(dados.get(rowIndex).getValorPago());
            }
            case 6 -> {
                return dados.get(rowIndex).isStatus() ? "Pagamento Realizado" : "Pendente";
            }
        }
        return null;
    }
    
    public void addRow(Despesa despesa) {
        dados.add(despesa);
        this.fireTableDataChanged();
    }
    
    public void addRow(List<Despesa> despesas) {
        despesas.forEach(despesa -> dados.add(despesa));
        this.fireTableDataChanged();
    }
    
    public Despesa getDespesa(int rowIndex){
        return dados.get(rowIndex);
    }
    
    public void getDespesasPorLancamento(int mes) {
        dados.clear();
        addRow(despesaController.listarDespesasLancamento(mes));
        
    }
    
    public void getDespesasPorVencimento(int mes) {
        dados.clear();
        addRow(despesaController.listarDespesasVencimento(mes));
    }
    
    public void getDespesasAnual(String ano) {
        dados.clear();
        addRow(despesaController.listarDespesas(ano));
    }
    
}
