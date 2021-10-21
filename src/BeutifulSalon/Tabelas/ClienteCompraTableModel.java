/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Tabelas;


import BeutifulSalon.Ferramentas.ManipulaStrings;
import BeutifulSalon.controller.VendaController;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Item;
import BeutifulSalon.model.Venda;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mateus
 */
public class ClienteCompraTableModel extends AbstractTableModel {
    
     private final List<Venda> dados;
    private ManipulaStrings manipulaStrings = new ManipulaStrings();
    private final String[] columns = {"Data", "Produtos","Desconto","Total"};

    public ClienteCompraTableModel() {
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
                return dados.get(rowIndex).getData().format(formatterData);
            case 1:
                StringBuilder produtos = new StringBuilder();
                List<Item> itens = dados.get(rowIndex).getItensVenda();
                
                int rows = 0;
                for(Item i: itens){
                    produtos.append(i.getQuantidade()+"un. "+i.getNome());
                    if( rows != itens.size() -1){
                        produtos.append("\n");
                    }
                    rows++;
                }   
                return produtos.toString();           
            case 2:         
               return Dinheiro.parseString(dados.get(rowIndex).getValorDesconto());
            case 3:
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
    
      
    public void getComprasCliente(long idCliente){
        dados.clear();
        addRow(new VendaController().retornaComprasPorIDCliente(idCliente));
    }
    
}
