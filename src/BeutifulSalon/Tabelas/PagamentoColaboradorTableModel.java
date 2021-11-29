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

import BeutifulSalon.controller.ColaboradorController;
import BeutifulSalon.model.Colaborador;
import BeutifulSalon.model.Dinheiro;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mateus
 */
public class PagamentoColaboradorTableModel extends AbstractTableModel {

    private final List<Colaborador> dados;
    private final String[] columns = {"Nome", "Comissão", "Qtd. Realizada", "Pagamento"};

    public PagamentoColaboradorTableModel() {
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

        switch (columnIndex) {
            case 0:
                return dados.get(rowIndex).getNome();
            case 1:
                if (dados.get(rowIndex).isComissionado()) {
                    return Dinheiro.parseString(dados.get(rowIndex).getPorcentagemComisao());

                } else {
                    return "Não comissionado";
                }
            case 2:
                return dados.get(rowIndex).getQtdRealizada();
            case 3:
                return Dinheiro.parseString(dados.get(rowIndex).getQtdRealizada() * dados.get(rowIndex).getPorcentagemComisao());
        }

        return null;
    }

    public void addRow(Colaborador c) {
        dados.add(c);
        this.fireTableDataChanged();
    }

    public void addRow(List<Colaborador> c) {
        c.forEach(col -> dados.add(col));
        this.fireTableDataChanged();
    }

    public Colaborador getColaborador(int rowCount) {
        return dados.get(rowCount);
    }

    public void getTodosColaboradores() {
        dados.clear();
        addRow(new ColaboradorController().listarColaboradores());
    }

    public List<Colaborador> getDados() {
        return dados;
    }
    
    public void clearDados(){
        dados.clear();
    }

}
