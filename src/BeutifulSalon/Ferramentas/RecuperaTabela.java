/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Item;
import BeutifulSalon.model.Produto;
import BeutifulSalon.model.Servico;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Mateus
 */
public class RecuperaTabela {

    public ArrayList<Produto> Produtos(JTable tabela) {

        ArrayList<Produto> produtos = new ArrayList<>();
        if(tabela.getRowCount() < 1)return produtos;
        try {
            for (int i = 0; i < tabela.getRowCount(); i++) {
                Produto produtoAtual = new Produto();
                for (int j = 0; j < tabela.getColumnCount(); j++) {

                    if (j == 0) {
                        produtoAtual.setNome(tabela.getValueAt(i, j).toString());
                    } else if (j == 1) {
                        produtoAtual.setMarca(tabela.getValueAt(i, j).toString());
                    } else if (j == 2) {
                        produtoAtual.setRendimento(Integer.parseInt(tabela.getValueAt(i, j).toString()));
                    } else {
                        produtoAtual.setId_produto(Integer.parseInt(tabela.getValueAt(i, j).toString()));
                    }
                }
                produtos.add(produtoAtual);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao recuperar Tabela.");
        }

        return produtos;
    }

    public ArrayList<Item> recuperaItensCompra(JTable tabela) {

        ArrayList<Item> itensCompra = new ArrayList<>();

        try {
            for (int i = 0; i < tabela.getRowCount(); i++) {
                Item produtoAtual = new Item();
                for (int j = 0; j < tabela.getColumnCount(); j++) {

                    if (j == 0) {
                        produtoAtual.setNome(tabela.getValueAt(i, j).toString());
                    } else if (j == 1) {
                        produtoAtual.setMarca(tabela.getValueAt(i, j).toString());
                    } else if (j == 2) {
                        produtoAtual.setPreco(Dinheiro.parseCent(Dinheiro.retiraCaracteres(tabela.getValueAt(i, j).toString())));
                    } else if (j == 3) {
                        produtoAtual.setQuantidade(Integer.parseInt(tabela.getValueAt(i, j).toString()));
                    } else {
                        produtoAtual.setId_produto(Long.parseLong(tabela.getValueAt(i, j).toString()));
                    }
                }
                itensCompra.add(produtoAtual);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao recuperar Tabela.");
        }

        return itensCompra;

    }

    public ArrayList<Servico> recuperaServicos(JTable tabela) {

        ArrayList<Servico> servicosSolicitados = new ArrayList<>();

        try {
            for (int i = 0; i < tabela.getRowCount(); i++) {

                Servico servicoAtual = new Servico();

                for (int j = 0; j < tabela.getColumnCount(); j++) {

                    if (j == 0) {
                        servicoAtual.setNome(tabela.getValueAt(i, j).toString());
                    } else if (j == 1) {
                        servicoAtual.setPreco(Dinheiro.parseCent(
                                Dinheiro.retiraCaracteres(tabela.getValueAt(i, j).toString())));
                    } else {
                        servicoAtual.setId(Long.parseLong(tabela.getValueAt(i, j).toString()));
                    }

                }
                servicosSolicitados.add(servicoAtual);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao recuperar Tabela.");
        }

        return servicosSolicitados;
    }

}
