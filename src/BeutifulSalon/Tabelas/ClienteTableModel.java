/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Tabelas;


import BeutifulSalon.controller.ClienteController;
import BeutifulSalon.model.Cliente;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mateu
 */
public class ClienteTableModel extends AbstractTableModel {

    private final List<Cliente> dados;
    private final String[] columns = {"Nome", "Sobrenome", "Celular", "Telefone", "Email", "Última Vísita"};
    private final ClienteController clienteController;

    public ClienteTableModel() {
        this.dados = new ArrayList<>();
        this.clienteController = new ClienteController();
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
        switch(columnIndex){
            case 0:
                return dados.get(rowIndex).getNome();
            
            case 1:
                return dados.get(rowIndex).getSobrenome();
            
            case 2:
                return dados.get(rowIndex).getCelular();
            case 3:
                return dados.get(rowIndex).getTelefoneResidencial();
            case 4:
               return dados.get(rowIndex).getEmail();         
            case 5 :
                LocalDate ultimaVisita = dados.get(rowIndex).getUltimaVisita();
                
                if(ultimaVisita != null){
                    return ultimaVisita.format(formatterData);
                }else{
                    return "--";
                }
                        
        }
        
        return null;
    }
    
    public void addRow(Cliente cliente) {
        dados.add(cliente);
        this.fireTableDataChanged();
    }

    public void addRow(List<Cliente> clientes) {
        clientes.forEach(cliente -> dados.add(cliente));
        this.fireTableDataChanged();
    }
    
    public Cliente getCliente(int rowCount){
        return dados.get(rowCount);
    }
    
    public void getTodosClientes(){
        dados.clear();
        addRow(clienteController.listarClientes());
    }
      
    public void getClientePorNome(String nome){
        dados.clear();
        addRow(clienteController.listarClientes(nome));
    }
    
}
