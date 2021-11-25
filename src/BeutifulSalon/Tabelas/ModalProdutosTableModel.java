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

import BeutifulSalon.controller.EstoqueController;
import BeutifulSalon.controller.ProdutoController;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mateus
 */
public class ModalProdutosTableModel extends AbstractTableModel {

    private final List<Produto> dados;
    private boolean isCabeleireiroView;
    private final String[] columns = {"Nome", "Marca", "Preço de Venda", "Qtd. Estoque"};

    public ModalProdutosTableModel() {
        this.dados = new ArrayList<>();
        isCabeleireiroView = true;
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

    public boolean isIsCabeleireiroView() {
        return isCabeleireiroView;
    }

    public void setIsCabeleireiroView(boolean isCabeleireiroView) {
        this.isCabeleireiroView = isCabeleireiroView;
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

        List<Produto> produtos = pc.listarProdutos();

        if (isCabeleireiroView) {
            addRow(produtos);
        } else {
            produtos.forEach(p -> {
                if (p.getPreco() > 0) {
                    addRow(p);
                }

            });
        }

    }

    public void getProdutosPeloNome(String nome) {
        ProdutoController pc = new ProdutoController();
        dados.clear();
        List<Produto> produtos = pc.listarProdutos(nome);

        if (isCabeleireiroView) {
            addRow(pc.listarProdutos(nome));
        } else {
            produtos.forEach(p -> {
                if (p.getPreco() > 0) {
                    addRow(p);
                }
            });
        }

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
