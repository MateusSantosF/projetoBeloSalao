/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Tabelas;


import BeutifulSalon.controller.VendaController;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Item;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mateus
 */
public class ClienteCompraTableModel extends AbstractTableModel {
    
    private final List<Item> dados;
    private final String[] columns = {"Nome", "Marca", "Quantidade", "Total", "Data"};
    private final VendaController vendaController;

    public ClienteCompraTableModel() {
        this.dados = new ArrayList<>();
        vendaController = new VendaController();
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
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        
        switch(columnIndex){
            case 0:
                return dados.get(rowIndex).getNome();      
            case 1:
                  return dados.get(rowIndex).getMarca();  
            case 2:
                return dados.get(rowIndex).getQuantidade();
            case 3:
                return Dinheiro.parseString(dados.get(rowIndex).getPrecoTotal());
            case 4:
                return dados.get(rowIndex).getDataReg().format(formatterData);
        }
        
        return null;
    }

    
    public void removeRow(int rowIndex) {
        dados.remove(rowIndex);
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public void addRow(Item item) {
        dados.add(item);
        this.fireTableDataChanged();
    }

    public void addRow(List<Item> itens) {
        itens.forEach(i -> dados.add(i));
        this.fireTableDataChanged();
    }
    
    public Item getItem(int rowCount){
        return dados.get(rowCount);
    }
    
    public void listarItens(long idCliente){
        dados.clear();
        addRow(vendaController.retornaItemsCompra(idCliente));               
    }
    
}
