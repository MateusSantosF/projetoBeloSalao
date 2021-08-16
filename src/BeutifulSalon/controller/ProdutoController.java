package BeutifulSalon.controller;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Produto;
import BeutifulSalon.view.Edicao.EditarProduto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
//import java.sql.Date;

/**
 *
 * @author Melissa
 */
//CONTROL
public class ProdutoController {

    public boolean cadastrarProduto(String nome, String marca, long preco, String dataReg) {

        if (nome != null && nome.length() > 0 && marca != null && marca.length() > 0) {

            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/M/uuuu");

            //Convertendo datas de String para Date
            LocalDate dataRegistro = LocalDate.parse(dataReg, formatterData);

            Produto produto = new Produto(nome, marca, preco, dataRegistro);
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

    public boolean AtualizarProduto(String nome, String marca, long preco, long idProduto) {

        if (nome != null && nome.length() > 0 && marca != null && marca.length() > 0 && preco > 0) {
            Produto produto = new Produto(nome, marca, preco, idProduto);
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
}
