/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Tabelas;

import BeutifulSalon.controller.AgendamentoController;
import BeutifulSalon.controller.ClienteController;
import BeutifulSalon.controller.ServicoController;
import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.Servico;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mateus
 */
public class ClienteServicoTableModel extends AbstractTableModel{
    
    private final List<Servico> dados;
    private final String[] columns = {"Serviço", "Data", "Horário"};
    private final ClienteController clienteController;
    private final ServicoController servicoController;
    public final AgendamentoController agendamentoController;

    public ClienteServicoTableModel() {
        this.dados = new ArrayList<>();
        this.clienteController = new ClienteController();
        this.servicoController = new ServicoController();
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
                return dados.get(rowIndex).getNome();
            
            case 1:
                return dados.get(rowIndex).getDataRealizado().format(formatterData);
      
            case 2:
                return dados.get(rowIndex).getTempoGasto().format(formatterHora);       
        }
        
        return null;
    }

    
    public void removeRow(int rowIndex) {
        dados.remove(rowIndex);
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public void addRow(Servico servico) {
        dados.add(servico);
        this.fireTableDataChanged();
    }

    public void addRow(List<Servico> servicos) {
        servicos.forEach(s -> dados.add(s));
        this.fireTableDataChanged();
    }
    
    public Servico getServico(int rowCount){
        return dados.get(rowCount);
    }
    
    public void listarServicos(long id){     
        dados.clear();
        addRow(servicoController.listarServicosDeAgendamentoPorCliente(id));               
    }
    

    
}
