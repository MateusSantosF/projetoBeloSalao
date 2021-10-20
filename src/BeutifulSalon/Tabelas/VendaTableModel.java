/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Tabelas;

import BeutifulSalon.Ferramentas.ManipulaStrings;

import BeutifulSalon.controller.VendaController;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Venda;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mateu
 */
public class VendaTableModel extends AbstractTableModel {

    private final List<Venda> dados;
    private ManipulaStrings manipulaStrings = new ManipulaStrings();
    private final String[] columns = {"Cliente", "Data", "Quantidade de Produtos","Desconto","Total"};

    public VendaTableModel() {
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
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        switch (columnIndex) {
            case 0:
                return manipulaStrings.abreviarNome(dados.get(rowIndex).getNomeCliente() + " " + dados.get(rowIndex).getSobrenomeCliente());
            case 1:
                return dados.get(rowIndex).getData().format(formatterData);
            case 2:
                return dados.get(rowIndex).getQuantidadeProdutosVendidos();
            case 3:         
               return Dinheiro.parseString(dados.get(rowIndex).getValorDesconto());
            case 4:
                return Dinheiro.parseString(dados.get(rowIndex).getTotal());
            default:
                return null;

        }

    }

    public void addRow(Venda venda) {
        dados.add(venda);
        this.fireTableDataChanged();
    }

    public void addRow(List<Venda> vendas) {
       vendas.forEach(v -> dados.add(v));
        this.fireTableDataChanged();
    }

    public void removeRow(int rowIndex) {
        dados.remove(rowIndex);
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Venda getVenda(int rowIndex) {
        return dados.get(rowIndex);
    }

    public void getTodasVendas() {
        VendaController vc = new VendaController();
        dados.clear();
        addRow(vc.selecionaTodasVendas());
    }
    
    public void getVendasPorNomeCliente(String nomeCliente){
        VendaController vc = new VendaController();
        dados.clear();
        addRow(vc.selecionaVendasPorNomeCliente(nomeCliente));
    }

    public void getVendasDoAno() {
        VendaController vc = new VendaController();
        dados.clear();
        addRow(vc.selecionaVendasDoAno(LocalDate.now().getYear()));
    }

    public void getVendasDoAnoPorNomeCliente(String nomeCliente) {
        VendaController vc = new VendaController();
        dados.clear();
        addRow(vc.selecionaVendasDoAnoPorNomeCliente(nomeCliente));
    }


}
