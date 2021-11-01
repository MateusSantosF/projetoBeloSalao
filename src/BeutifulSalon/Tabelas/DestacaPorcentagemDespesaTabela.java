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

import BeutifulSalon.controller.OrcamentoController;
import BeutifulSalon.controller.ServicoController;
import BeutifulSalon.model.Despesa;
import BeutifulSalon.model.Orcamento;
import BeutifulSalon.model.OrcamentoServico;
import java.awt.Color;
import java.awt.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Mateus
 */
public class DestacaPorcentagemDespesaTabela extends DefaultTableCellRenderer {

    private final int columnIndex;
    private final Color defaultForeground;
    private final DespesaComparadaTableModel modelo;
    //private Color verde = new Color(9, 213, 147);
    private Color vermelho = new Color(57, 201, 114);
    private Color verde = new Color(248, 67, 69);

    public DestacaPorcentagemDespesaTabela(int column,  DespesaComparadaTableModel modelo) {
        super();
        columnIndex = column;
        this.defaultForeground = getForeground();
        this.modelo = modelo;
    }
    
      

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Orcamento oc = modelo.getOrcamento(row);
        
        switch (this.columnIndex) {
            case 1:

                if (oc.getJan() >= 100) {
                    c.setForeground(verde);
                } else {
                    c.setForeground(vermelho);
                }
                break;
            case 2:
                if (oc.getFev() >= 100) {
                    c.setForeground(verde);
                } else {
                    c.setForeground(vermelho);
                }
                break;
            case 3:

                if (oc.getMar() >= 100) {
                    c.setForeground(verde);
                } else {
                    c.setForeground(vermelho);
                }
                break;
            case 4:
                if (oc.getAbr() >= 100) {
                    c.setForeground(verde);
                } else {
                    c.setForeground(vermelho);
                }
                break;
            case 5:
                if (oc.getMai() >= 100) {
                    c.setForeground(verde);
                } else {
                    c.setForeground(vermelho);
                }
                break;
            case 6:
                if (oc.getJun() >= 100) {
                    c.setForeground(verde);
                } else {
                    c.setForeground(vermelho);
                }
                break;
            case 7:
                if (oc.getJul() >= 100) {
                    c.setForeground(verde);
                } else {
                    c.setForeground(vermelho);
                }
                break;
            case 8:
                if (oc.getAgo() >= 100) {
                    c.setForeground(verde);
                } else {
                    c.setForeground(vermelho);
                }
                break;
            case 9:
                if (oc.getSet() >= 100) {
                    c.setForeground(verde);
                } else {
                    c.setForeground(vermelho);
                }
                break;
            case 10:
                if (oc.getOut() >= 100) {
                    c.setForeground(verde);
                } else {
                    c.setForeground(vermelho);
                }

                break;
            case 11:
                if (oc.getNov() >= 100) {
                    c.setForeground(verde);
                } else {
                    c.setForeground(vermelho);
                }
                break;
            case 12:
                if (oc.getDez() >= 100) {
                    c.setForeground(verde);
                } else {
                    c.setForeground(vermelho);
                }
                break;
        }

        return super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);
    }
}
