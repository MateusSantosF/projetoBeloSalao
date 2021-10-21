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

import BeutifulSalon.controller.CompraController;
import BeutifulSalon.model.Compra;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Item;
import java.awt.TextArea;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Mateus
 */
public class CompraTableModel extends AbstractTableModel {

    List<Compra> dados = new ArrayList<>();
    String[] columns = {"Data", "Produtos", "Valor Desconto", "Valor Total"};

    @Override
    public String getColumnName(int column) {
        return columns[column];
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
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return TextArea.class;
        }
        return Object.class;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");

        switch (columnIndex) {
            case 0:
                return dados.get(rowIndex).getData().format(formatterData);
            case 1:
                StringBuilder produtos = new StringBuilder();

                List<Item> itens = dados.get(rowIndex).getItensCompra();
                int rows = 0;
                for (Item p : itens) {
                    produtos.append(p.getQuantidade() + "un. " + p.getNome());
                    if (rows != itens.size() - 1) {
                        produtos.append("\n");
                    }
                    rows++;
                }

                return produtos.toString();
            case 2:
                return Dinheiro.parseString(dados.get(rowIndex).getValorDesconto());
            case 3:
                return Dinheiro.parseString(dados.get(rowIndex).getValorTotal());
        }

        return null;
    }

    public void addRow(Compra compra) {
        dados.add(compra);
        this.fireTableDataChanged();
    }

    public void addRow(List<Compra> compras) {
        compras.forEach(c -> dados.add(c));
        this.fireTableDataChanged();
    }

    public Compra getCompra(int rowCount) {
        return dados.get(rowCount);
    }

    public void getTodasCompras() {
        dados.clear();
        addRow(new CompraController().retornaTodasCompras());
    }

    public void getTodasComprasDoAno() {
        dados.clear();
        addRow(new CompraController().retornaTodasComprasDoAno());
    }

    public void getComprasDashboard() {
        dados.clear();
        addRow(new CompraController().retornaComprasDashboard());

    }

    public void getComprasPorNomeProduto(String nomeProduto) {
        dados.clear();
        addRow(new CompraController().getComprasPorNomeProduto(nomeProduto));
    }

    public void getComprasPorNomeProdutoDoAno(String nomeProduto) {
        dados.clear();
        addRow(new CompraController().getComprasPorNomeProdutoDoAno(nomeProduto));
    }

    public void getComprasPorMes(int mes, boolean anoAtual) {

        List<Compra> compras = new ArrayList<>();
        if (anoAtual) {
            dados = new CompraController().retornaTodasComprasDoAno();

        } else {
            dados = new CompraController().retornaTodasCompras();
        }

        switch (mes) {

            case 0:

                for (Compra c : dados) {
                    if (c.getData().getMonth() == Month.JANUARY) {
                        compras.add(c);
                    }
                }

                break;
            case 1:
                for (Compra c : dados) {
                    if (c.getData().getMonth() == Month.FEBRUARY) {
                        compras.add(c);
                    }
                }
                break;
            case 2:
                for (Compra c : dados) {
                    if (c.getData().getMonth() == Month.MARCH) {
                        compras.add(c);
                    }
                }
                break;
            case 3:
                for (Compra c : dados) {
                    if (c.getData().getMonth() == Month.APRIL) {
                        compras.add(c);
                    }
                }
                break;

            case 4:
                for (Compra c : dados) {
                    if (c.getData().getMonth() == Month.MAY) {
                        compras.add(c);
                    }
                }
                break;

            case 5:
                for (Compra c : dados) {
                    if (c.getData().getMonth() == Month.JUNE) {
                        compras.add(c);
                    }
                }
                break;
            case 6:
                for (Compra c : dados) {
                    if (c.getData().getMonth() == Month.JULY) {
                        compras.add(c);
                    }
                }
                break;
            case 7:
                for (Compra c : dados) {
                    if (c.getData().getMonth() == Month.AUGUST) {
                        compras.add(c);
                    }
                }
                break;
            case 8:
                for (Compra c : dados) {
                    if (c.getData().getMonth() == Month.SEPTEMBER) {
                        compras.add(c);
                    }
                }
                break;
            case 9:
                for (Compra c : dados) {
                    if (c.getData().getMonth() == Month.OCTOBER) {
                        compras.add(c);
                    }
                }
                break;
            case 10:
                for (Compra c : dados) {
                    if (c.getData().getMonth() == Month.NOVEMBER) {
                        compras.add(c);
                    }
                }
                break;
            case 11:
                for (Compra c : dados) {
                    if (c.getData().getMonth() == Month.DECEMBER) {
                        compras.add(c);
                    }
                }
                break;
            case 12:
                if (anoAtual) {
                    compras = new CompraController().retornaTodasComprasDoAno();
                } else {
                    compras = new CompraController().retornaTodasCompras();
                }
                break;
        }
        dados.clear();
        addRow(compras);
    }

    public String getTotalCompras() {

        long total = 0;

        for (Compra c : dados) {
            total += c.getValorTotal();
        }

        return Dinheiro.parseString(total);
    }

}
