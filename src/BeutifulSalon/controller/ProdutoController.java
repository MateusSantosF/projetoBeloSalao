package BeutifulSalon.controller;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Produto;
import BeutifulSalon.view.Edicao.EditarProduto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
//import java.sql.Date;

/**
 *
 * @author Melissa
 */
//CONTROL
public class ProdutoController {

    public boolean cadastrarProduto(String nome, String marca, String preco, LocalDate dataReg, boolean isVendido) {

        if (nome != null && nome.length() > 0 && marca != null && marca.length() > 0) {

            long valorDeVenda = -1;
            if (isVendido) {
                valorDeVenda = Dinheiro.parseCent(Dinheiro.retiraCaracteres(preco));
                if (valorDeVenda <= 0) {
                    return false;
                }
            }
            Produto produto = new Produto(nome, marca, valorDeVenda, dataReg);
            try {
                produto.cadastrarProduto(produto);
            } catch (ExceptionDAO e) {
                JOptionPane.showMessageDialog(null, e);
                return false;
            }

        } else {
            return false;
        }
        return true;
    }

    public ArrayList<Produto> listarProdutos(String nome) {
        try {
            return new Produto().listarProdutos(nome);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public ArrayList<Produto> listarProdutos() {
        try {
            return new Produto().listarProdutos();
        } catch (ExceptionDAO e) {
        }
        return null;
    }

    public Produto buscarProduto(long id) {
        try {
            return new Produto().buscarProduto(id);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public boolean excluirProduto(long idProdutoSelecionado) {

        Produto p = new Produto();
        try {
            p.excluirProduto(idProdutoSelecionado);
        } catch (ExceptionDAO e) {
            JOptionPane.showInputDialog(null, "Erro Controller, excluir produto" + e);
            return false;
        }
        return true;
    }

    public boolean editarProduto(long id_produto) {

        try {
            Produto p = new Produto();
            Produto produtoEditado;

            produtoEditado = p.editarProduto(id_produto);

            if (produtoEditado != null) {
                new EditarProduto(produtoEditado).setVisible(true);
            } else {
                return false;
            }
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar objeto produto" + e);
            return false;
        }
        return true;
    }

    public boolean AtualizarProduto(String nome, String marca, String preco, long idProduto, boolean isVendido) {

        if (nome != null && nome.length() > 0 && marca != null && marca.length() > 0) {

            long valorDeVenda = -1;
            if (isVendido) {
                valorDeVenda = Dinheiro.parseCent(Dinheiro.retiraCaracteres(preco));
                if (valorDeVenda <= 0) {
                    return false;
                }
            }
            Produto produto = new Produto(nome, marca, valorDeVenda, idProduto);
            try {
                produto.atualizarProduto(produto);
            } catch (ExceptionDAO e) {
                JOptionPane.showMessageDialog(null, e);
            }
        } else {
            return false;
        }
        return true;

    }

    public List<Produto> produtosMaisVendidosDoAno(int anoReferente) {
        try {
            return new Produto().produtosMaisVendidosDoAno(anoReferente);
        } catch (ExceptionDAO e) {
            System.out.println("erro ao listar produtos mais vendidos" + e);
        }

        return null;

    }
}
