/*
 * The MIT License
 *
 * Copyright 2021 Mateus.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package BeutifulSalon.Tabelas;

import BeutifulSalon.controller.AgendamentoController;
import BeutifulSalon.controller.ServicoController;
import BeutifulSalon.model.Dinheiro;
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
public class ModalServicoTableModel extends AbstractTableModel {

    private final DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
    private final List<Servico> dados;
    private final String[] columns = {"Nome", "Valor", "Tempo Gasto"};
    private final ServicoController servicoController;

    public ModalServicoTableModel() {
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

        switch (columnIndex) {
            case 0:
                return dados.get(rowIndex).getNome();

            case 1:

                return Dinheiro.parseString(dados.get(rowIndex).getPreco());

            case 2:
                return dados.get(rowIndex).getTempoGasto().format(formatterHora) + "h";
        }

        return null;

    }

    public void addRow(Servico servico) {
        dados.add(servico);
        this.fireTableDataChanged();
    }
    
    public void removeRow(int rowIndex){
        dados.remove(rowIndex);
        fireTableDataChanged();
    }

    public List<Servico> getDados() {
        return dados;
    }

    public void addRow(List<Servico> servicos) {
        servicos.forEach(servico -> dados.add(servico));
        this.fireTableDataChanged();
    }

    public void getTodosServicos() {
        dados.clear();
        addRow(servicoController.listarServicos());
    }

    public void getServicosAgendamento(long idAgendamento) {
        dados.clear();
        AgendamentoController ag = new AgendamentoController();
        addRow(ag.listarServicosAgendamento(idAgendamento));

    }

    public void getServicosNome(String nome) {
        dados.clear();
        addRow(servicoController.listarServicos(nome));
    }

    public Servico getServico(int rowCount) {
        return dados.get(rowCount);
    }
    
    public void clearDados(){
        dados.clear();
        this.fireTableDataChanged();
    }

    public void calculaTempoTotal() {

        LocalTime tempoTotal = LocalTime.of(0, 0);
        for (Servico s : dados) {
            tempoTotal = tempoTotal.plusHours(s.getTempoGasto().getHour());
            tempoTotal = tempoTotal.plusMinutes(s.getTempoGasto().getMinute());
        }

        Servico servico = new Servico();
        servico.setNome("Duração");
        servico.setTempoGasto(tempoTotal);
        addRow(servico);

    }
}
