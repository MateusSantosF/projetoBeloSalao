/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Tabelas;

import BeutifulSalon.controller.AgendamentoController;
import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.Servico;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mateus
 */
public class ClienteServicoTableModel extends AbstractTableModel{
    
    private final List<Agendamento> dados;
    private final String[] columns = {"Serviços", "Data", "Horário"};

    public final AgendamentoController agendamentoController;

    public ClienteServicoTableModel() {
        this.dados = new ArrayList<>();
        this.agendamentoController = new AgendamentoController();
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
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
        
        switch(columnIndex){
            case 0:
                StringBuilder listaServicos = new StringBuilder();
                List<Servico> servicos = dados.get(rowIndex).getServicos();
                
                int cont = 0;
                for(Servico s: servicos){
                    if(s != null){
                        listaServicos.append(s.getNome());
                    }
                    if( cont != servicos.size() -1){
                        listaServicos.append("\n");
                    }
                }
                return listaServicos.toString();
            
            case 1:
                return dados.get(rowIndex).getData().format(formatterData);
      
            case 2:
                return dados.get(rowIndex).getHorario().format(formatterHora);       
        }
        
        return null;
    }

    
    public void removeRow(int rowIndex) {
        dados.remove(rowIndex);
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public void addRow(Agendamento ag) {
        dados.add(ag);
        this.fireTableDataChanged();
    }

    public void addRow(List<Agendamento> agendamentos) {
        agendamentos.forEach(ag -> dados.add(ag));
        this.fireTableDataChanged();
    }
    
    public Agendamento getAgendamento(int rowCount){
        return dados.get(rowCount);
    }
    
    public void listarServicos(long id){     
        dados.clear();
        addRow(agendamentoController.listarAgendamentosIDCliente(id));               
    }
    

    
}
