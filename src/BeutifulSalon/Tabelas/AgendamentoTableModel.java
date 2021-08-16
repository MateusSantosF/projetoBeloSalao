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
 * @author mateu
 */
public class AgendamentoTableModel extends AbstractTableModel {
    
    private final List<Agendamento> dados;
    private final String[] columns = {"Cliente", "Data", "Horário", "Término Estimado", "Status"};
    private final ClienteController clienteController;
    private final ServicoController servicoController;
    public final AgendamentoController agendamentoController;

    public AgendamentoTableModel() {
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
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
        
        switch(columnIndex){
            case 0 -> {
                return clienteController.buscarCliente(dados.get(rowIndex).getCpfCliente()).getNOME();
            }
            case 1 -> {
                return dados.get(rowIndex).getData().format(formatterData);
            }
            case 2 -> {
                return dados.get(rowIndex).getHorario().format(formatterHora);
            }
            case 3 -> {
                Agendamento g = dados.get(rowIndex);
                ArrayList<Servico> servicosAgendamento = servicoController.buscarServicoPeloAgendamento(
                        dados.get(rowIndex).getId());
                LocalTime inicioAgendamento = g.getHorario();           
                LocalTime fimAgendamento = inicioAgendamento;
                
                int horas = 0;
                int minutos = 0;
                
                for (Servico s : servicosAgendamento) {
                    horas += s.getTempoGasto().getHour();
                    minutos += s.getTempoGasto().getMinute();
                }
                fimAgendamento = fimAgendamento.plusHours(horas);
                fimAgendamento = fimAgendamento.plusMinutes(minutos);
                return fimAgendamento.format(formatterHora);
            }
            case 4 -> {
                return dados.get(rowIndex).getRealizado() ? "Realizado" : "Não realizado";
            }            
        }
        
        return null;
    }
    
    public void addRow(Agendamento agendamento) {
        dados.add(agendamento);
        this.fireTableDataChanged();
    }

    public void addRow(List<Agendamento> agendamentos) {
        agendamentos.forEach(ag -> dados.add(ag));
        this.fireTableDataChanged();
    }
    
    public Agendamento getAgendamento(int rowCount){
        return dados.get(rowCount);
    }
    
    public void getTodosAgendamentos(){
        dados.clear();
        addRow(agendamentoController.listarAgendamentos());
    }
    public void getAgendamentosHoje(){
        dados.clear();
        addRow(agendamentoController.listarAgendamentosHoje());
    }
    
    public void getAgendamentosAmanha(){
        dados.clear();
        addRow(agendamentoController.listarAgendamentosAmanha());
    }
    public void getAgendamentosSemana(){
        dados.clear();
        addRow(agendamentoController.listarAgendamentosSemana());
    }
    
    public void getAgendamentosNaoRealizados(){
        dados.clear();
        addRow(agendamentoController.listarAgendamentosNaoRealizados());
    }
    
    public void getAgendamentosPorNomeCliente(String nome){
        dados.clear();
        addRow(agendamentoController.listarAgendamentosNome(nome));
    }
}
