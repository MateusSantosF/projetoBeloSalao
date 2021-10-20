/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Tabelas;

import BeutifulSalon.controller.EstoqueController;
import BeutifulSalon.controller.ProdutoController;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mateu
 */
public class ProdutoTableModel extends AbstractTableModel {

    private final List<Produto> dados;
    private final String[] columns = {"Nome", "Marca", "Preço de Venda","Último Valor Pago", "Quantidade em Estoque"};

    public ProdutoTableModel() {
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
        EstoqueController estoque = new EstoqueController();
        switch (columnIndex) {
            case 0:
                return dados.get(rowIndex).getNome();

            case 1:
                return dados.get(rowIndex).getMarca();
            case 2:
                if (dados.get(rowIndex).getPreco() < 0) {
                    return "Não é Vendido";
                } else {
                    return Dinheiro.parseString(dados.get(rowIndex).getPreco());
                }
            case 3:
                return Dinheiro.parseString(estoque.ultimoValorPagoProduto(dados.get(rowIndex).getId_produto()));
            case 4:
                return estoque.quantidadeProduto(dados.get(rowIndex).getId_produto());
            default:
                return null;

        }

    }

    public void addRow(Produto produto) {
        dados.add(produto);
        this.fireTableDataChanged();
    }

    public void addRow(List<Produto> produtos) {
        produtos.forEach(p -> dados.add(p));
        this.fireTableDataChanged();
    }

    public void removeRow(int rowIndex) {
        dados.remove(rowIndex);
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Produto getProduto(int rowIndex) {
        return dados.get(rowIndex);
    }

    public void getTodosProdutos() {
        ProdutoController pc = new ProdutoController();
        dados.clear();
        addRow(pc.listarProdutos());
    }

    public void getProdutosPeloNome(String nome) {
        ProdutoController pc = new ProdutoController();
        dados.clear();
        addRow(pc.listarProdutos(nome));
    }

    public boolean removeProduto(int rowIndex) {
        ProdutoController pc = new ProdutoController();
        return pc.excluirProduto(dados.get(rowIndex).getId_produto());
    }

    public boolean editProduto(int rowIndex) {
        ProdutoController pc = new ProdutoController();
        return pc.editarProduto(dados.get(rowIndex).getId_produto());
    }
}
