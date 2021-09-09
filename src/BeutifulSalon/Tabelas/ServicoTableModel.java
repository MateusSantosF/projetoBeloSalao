/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Tabelas;

import BeutifulSalon.controller.ServicoController;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Servico;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mateus
 */
public class ServicoTableModel extends AbstractTableModel {
     
    private final DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
    private final List<Servico> dados;
    private final String[] columns = {"Nome", "Valor", "Tempo Gasto", "Qtd. Realizada Anual"};
    private final ServicoController servicoController;

    public ServicoTableModel() {
        this.dados = new ArrayList<>();
        this.servicoController = new ServicoController();
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
       

        switch(columnIndex){
            case 0:
                return dados.get(rowIndex).getNome();
            
            case 1:
                return Dinheiro.parseString(dados.get(rowIndex).getPreco());
            
            case 2:
                return dados.get(rowIndex).getTempoGasto().format(formatterHora) + "h";
            
            case 3:
               return dados.get(rowIndex).getQuantidadeRealizada();
        }
        
        return null;

    }
    
    
    public void addRow(Servico servico) {
        dados.add(servico);
        this.fireTableDataChanged();
    }

    public void addRow(List<Servico> servicos) {
       servicos.forEach(servico -> dados.add(servico));
       this.fireTableDataChanged();
    }
    
    public void getTodosServicos(){
        dados.clear();
        addRow(servicoController.listarServicos());
    }

    
    public void getServicosNome(String nome){
        dados.clear();
        addRow(servicoController.listarServicos(nome));
    }
    
    public Servico getServico(int rowCount){
        return dados.get(rowCount);
    }
}
