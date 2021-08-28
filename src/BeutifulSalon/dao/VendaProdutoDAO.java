/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.dao;

import BeutifulSalon.controller.EstoqueController;
import BeutifulSalon.model.Item;
import BeutifulSalon.model.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mateu
 */
public class VendaProdutoDAO {

    public void cadastrarVenda(Venda venda) {
        
        
        String insertCompra = "INSERT INTO VENDA (DATA, VALORTOTAL, VALORDESCONTO, CPF_CLIENTE) "
                + "VALUES (?, ?, ? , ?)";

        String insertItemCompra = "INSERT INTO ITEM_VENDA (PRECOUNITARIO, QUANTIDADE, PRECOTOTAL, ID_PRODUTO, ID_VENDA) "
                + "VALUES (?,?,?,?,(SELECT ID_VENDA  FROM VENDA ORDER BY ID_VENDA DESC LIMIT 1))";

        Connection connection = null;
        PreparedStatement pStatement = null;
        EstoqueController estoque = new EstoqueController();

        try {

            connection = new ConnectionMVC().getConnection();
            connection.setAutoCommit(false);

            pStatement = connection.prepareStatement(insertCompra);
            pStatement.setDate(1, java.sql.Date.valueOf(venda.getData()));
            pStatement.setLong(2, venda.getValorTotal());
            pStatement.setLong(3, venda.getValorDesconto());
            pStatement.setString(4, venda.getCpfCliente());

            int firstInsert = pStatement.executeUpdate();

            if (firstInsert > 0) {
                try {

                    ArrayList<Item> itens = venda.getItensCompra();
                    for (Item it : itens) {

                        pStatement = connection.prepareStatement(insertItemCompra);
                        pStatement.setLong(1, it.getPreco());
                        pStatement.setInt(2, it.getQuantidade());
                        pStatement.setLong(3, it.getPrecoTotal());
                        pStatement.setLong(4, it.getId_produto());
                        pStatement.executeUpdate();

                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro registrar itemCompra" + e);
                    connection.rollback();
                }
            }

            connection.commit();

            try {
                boolean sucesso = estoque.atualizaEstoque(venda);

                if (sucesso == false) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque");

                }
            } catch (ExceptionDAO ex) {
                Logger.getLogger(CompraProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque" + ex);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro DAO" + e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(CompraProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } finally {

            try {
                if (pStatement != null) {
                    pStatement.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar statement" + e);
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão" + e);
            }
        }

     }
    
    
}
