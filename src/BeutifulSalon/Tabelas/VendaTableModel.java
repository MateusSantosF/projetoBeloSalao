/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Tabelas;

import BeutifulSalon.Ferramentas.ManipulaStrings;
import BeutifulSalon.controller.CompraController;

import BeutifulSalon.controller.VendaController;
import BeutifulSalon.model.Compra;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Item;
import BeutifulSalon.model.Venda;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mateu
 */
public class VendaTableModel extends AbstractTableModel {

    private List<Venda> dados;
    private ManipulaStrings manipulaStrings = new ManipulaStrings();
    private final String[] columns = {"Cliente", "Data", "Produtos", "Total Bruto","Desconto", "Total"};

    public VendaTableModel() {
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
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        switch (columnIndex) {
            case 0:
                return manipulaStrings.abreviarNome(dados.get(rowIndex).getNomeCliente() + " " + dados.get(rowIndex).getSobrenomeCliente());
            case 1:
                return dados.get(rowIndex).getData().format(formatterData);
            case 2:
                StringBuilder produtos = new StringBuilder();
                List<Item> itens = dados.get(rowIndex).getItensVenda();

                int rows = 0;
                for (Item i : itens) {
                    produtos.append(i.getQuantidade() + "un. " + i.getNome());
                    if (rows != itens.size() - 1) {
                        produtos.append("\n");
                    }
                    rows++;
                }

                return produtos.toString();
            case 3:
                return Dinheiro.parseString(dados.get(rowIndex).getValorTotal());
            case 4:
                return Dinheiro.parseString(dados.get(rowIndex).getValorDesconto());
            case 5:
                return Dinheiro.parseString(dados.get(rowIndex).getTotal() - dados.get(rowIndex).getValorDesconto());
            default:
                return null;

        }

    }

    public void addRow(Venda venda) {
        dados.add(venda);
        this.fireTableDataChanged();
    }

    public void addRow(List<Venda> vendas) {
        vendas.forEach(v -> dados.add(v));
        this.fireTableDataChanged();
    }

    public void removeRow(int rowIndex) {
        dados.remove(rowIndex);
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Venda getVenda(int rowIndex) {
        return dados.get(rowIndex);
    }

    public void getTodasVendas() {
        VendaController vc = new VendaController();
        dados.clear();
        addRow(vc.selecionaTodasVendas());
    }

    public void getVendasPorNomeCliente(String nomeCliente) {
        VendaController vc = new VendaController();
        dados.clear();
        addRow(vc.selecionaVendasPorNomeCliente(nomeCliente));
    }

    public void getVendasDoAno() {
        VendaController vc = new VendaController();
        dados.clear();
        addRow(vc.selecionaVendasDoAno(LocalDate.now().getYear()));
    }

    public void getVendasDoAnoPorNomeCliente(String nomeCliente) {
        VendaController vc = new VendaController();
        dados.clear();
        addRow(vc.selecionaVendasDoAnoPorNomeCliente(nomeCliente));
    }

    public void getComprasCliente(long idCliente) {
        dados.clear();
        addRow(new VendaController().retornaComprasPorIDCliente(idCliente));
    }

    public void getVendasPorMes(int mes, boolean anoAtual) {

        List<Venda> compras = new ArrayList<>();
        if (anoAtual) {
            dados = new VendaController().selecionaVendasDoAno(LocalDate.now().getYear());
        } else {
            dados = new VendaController().selecionaTodasVendas();
        }

        switch (mes) {

            case 0:
                for (Venda c : dados) {
                    if (c.getData().getMonth() == Month.JANUARY) {
                        compras.add(c);
                    }
                }
                break;
            case 1:
                for (Venda c : dados) {
                    if (c.getData().getMonth() == Month.FEBRUARY) {
                        compras.add(c);
                    }
                }
                break;
            case 2:
                for (Venda c : dados) {
                    if (c.getData().getMonth() == Month.MARCH) {
                        compras.add(c);
                    }
                }
                break;
            case 3:
                for (Venda c : dados) {
                    if (c.getData().getMonth() == Month.APRIL) {
                        compras.add(c);
                    }
                }
                break;

            case 4:
                for (Venda c : dados) {
                    if (c.getData().getMonth() == Month.MAY) {
                        compras.add(c);
                    }
                }
                break;

            case 5:
                for (Venda c : dados) {
                    if (c.getData().getMonth() == Month.JUNE) {
                        compras.add(c);
                    }
                }
                break;
            case 6:
                for (Venda c : dados) {
                    if (c.getData().getMonth() == Month.JULY) {
                        compras.add(c);
                    }
                }
                break;
            case 7:
                for (Venda c : dados) {
                    if (c.getData().getMonth() == Month.AUGUST) {
                        compras.add(c);
                    }
                }
                break;
            case 8:
                for (Venda c : dados) {
                    if (c.getData().getMonth() == Month.SEPTEMBER) {
                        compras.add(c);
                    }
                }
                break;
            case 9:
                for (Venda c : dados) {
                    if (c.getData().getMonth() == Month.OCTOBER) {
                        compras.add(c);
                    }
                }
                break;
            case 10:
                for (Venda c : dados) {
                    if (c.getData().getMonth() == Month.NOVEMBER) {
                        compras.add(c);
                    }
                }
                break;
            case 11:
                for (Venda c : dados) {
                    if (c.getData().getMonth() == Month.DECEMBER) {
                        compras.add(c);
                    }
                }
                break;
            case 12:
                if (anoAtual) {
                    compras = new VendaController().selecionaVendasDoAno(LocalDate.now().getYear());
                } else {
                    compras = new VendaController().selecionaTodasVendas();
                }
                break;
        }
        dados.clear();
        addRow(compras);
    }

    public String getTotalVendas() {

        long total = 0;
        long totalDescontos = 0;

        for (Venda c : dados) {
            total += c.getValorTotal();
            totalDescontos += c.getValorDesconto();
        }

        return Dinheiro.parseString(total - totalDescontos);
    }

}
