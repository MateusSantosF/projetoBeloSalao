/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Compra;
import BeutifulSalon.model.Estoque;
import BeutifulSalon.model.ItemCompra;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class EstoqueController {

    public boolean atualizaEstoque(Compra compra) throws ExceptionDAO {

        CabeleireiroController cc = new CabeleireiroController();

        ArrayList<ItemCompra> produtosComprados = compra.getItensCompra();

        String cpfCabeleireiro = cc.selecionaCabeleireiro().getCpf();
        String cpfCompra = compra.getCpfCliente();

        try {
            if (cpfCompra.equals(cpfCabeleireiro)) {
                for (ItemCompra i : produtosComprados) {
                    Estoque estoque = new Estoque();

                    estoque.setIdProduto(i.getId_produto());
                    estoque.setQuantidade(i.getQuantidade());
                    estoque.setValorUnitario(i.getPreco());

                    estoque.atualizaEstoque(estoque);
                }

            } else {
                for (ItemCompra i : produtosComprados) {
                    Estoque estoque = new Estoque();
                    estoque.setIdProduto(i.getId_produto());
                    estoque.setQuantidade(i.getQuantidade() * -1);
                    estoque.setValorUnitario(i.getPreco());
                    estoque.atualizaEstoque(estoque);
                }
            }

            return true;
            
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Controller" + e);
            return false;
        }

    }
    
    public long somaProdutosEstoque() throws ExceptionDAO{
        return new Estoque().somaProdutosEstoque();
    }
    
    public long quantidadeProduto(long idProduto) throws ExceptionDAO{
        return new Estoque().quantidadeProduto(idProduto);
    }

}
