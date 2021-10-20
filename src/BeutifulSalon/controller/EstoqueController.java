/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Compra;
import BeutifulSalon.model.Estoque;
import BeutifulSalon.model.Item;
import BeutifulSalon.model.Venda;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mateus
 */
public class EstoqueController {

    public boolean atualizaEstoque(Compra compra) {

        ArrayList<Item> produtosComprados = compra.getItensCompra();

        for (Item i : produtosComprados) {
            Estoque estoque = new Estoque();

            estoque.setIdProduto(i.getId_produto());
            estoque.setQuantidade(i.getQuantidade());
            estoque.setValorUnitario(i.getPreco());
            try {
                estoque.atualizaEstoque(estoque, false);
            } catch (ExceptionDAO e) {
                return false;
            }

        }

        return true;

    }

    public boolean atualizaEstoque(Venda venda) throws ExceptionDAO {

        List<Item> produtosVendidos = venda.getItensCompra();

        for (Item i : produtosVendidos) {
            Estoque estoque = new Estoque();
            estoque.setIdProduto(i.getId_produto());
            estoque.setQuantidade(i.getQuantidade() * -1);
            //estoque.setValorUnitario(i.getPreco());
            try {
                estoque.atualizaEstoque(estoque, true);
            } catch (ExceptionDAO e) {
                return false;
            }

        }

        return true;

    }

    public long somaProdutosEstoque() {
        try {
            return new Estoque().somaProdutosEstoque();
        } catch (ExceptionDAO e) {
        }
        return 0;
    }

    public long quantidadeProduto(long idProduto) {
        try {
            return new Estoque().quantidadeProduto(idProduto);
        } catch (ExceptionDAO e) {
        }
        return 0;
    }

    public long ultimoValorPagoProduto(long idProduto) {

        try {
            return new Estoque().ultimoValorPagoProduto(idProduto);
        } catch (ExceptionDAO e) {
            System.out.println("produto não encontrado");
        }

        return 0;
    }

}
