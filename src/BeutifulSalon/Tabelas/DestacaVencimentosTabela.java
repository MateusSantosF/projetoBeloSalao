/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Tabelas;

import BeutifulSalon.model.Despesa;
import java.awt.Color;
import java.awt.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author mateu
 */
public class DestacaVencimentosTabela extends DefaultTableCellRenderer{
    
    private final int columnIndex;
    private final Color defaultForeground;
    private final LancamentoTableModel modelo;
    
    public DestacaVencimentosTabela(int column, LancamentoTableModel modelo) {
        super();
        columnIndex = column;
        this.defaultForeground = getForeground();
        this.modelo = modelo;
    }
       
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if(column == columnIndex){
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            LocalDate dataVencimento = LocalDate.parse(String.valueOf(value), formatter);
            Despesa despesaAtual = modelo.getDespesa(row);
            
            
            if(dataVencimento.isBefore(LocalDate.now()) && !despesaAtual.isStatus()){
                c.setForeground(Color.red);
            }else{
                c.setForeground(defaultForeground);
            }
        }
        
        return super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);
    }
}
