/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.dao;

import BeutifulSalon.model.Estoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class EstoqueDAO {

    public void atualizaEstoque(Estoque estoque){
        
      String sql = "SELECT count(*) AS QTD FROM ESTOQUE WHERE ID_PRODUTO = ?";
      
      String sql2 = "UPDATE ESTOQUE SET QUANTIDADE = QUANTIDADE + ?, VALOR_UNITARIO = ? WHERE ID_PRODUTO  = ?";
      
      String sql3 = "INSERT INTO ESTOQUE (ID_PRODUTO, QUANTIDADE, VALOR_UNITARIO) VALUES (?,?,?)";
      
      Connection connection = null;
        PreparedStatement ps = null;
        int nRegistro = 0;
        
        try {
            connection = new ConnectionMVC().getConnection();
 
            ps = connection.prepareStatement(sql);
            
            ps.setLong(1, estoque.getIdProduto());
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    nRegistro = rs.getInt("QTD");
                }
            }
            
            //verifica se o produto já está no estoque, caso afirmativo, realiza um update, caso negativo insere
            if(nRegistro > 0){
                ps = connection.prepareStatement(sql2);
                ps.setLong(3, estoque.getIdProduto());
                ps.setLong(1, estoque.getQuantidade());
                ps.setLong(2, estoque.getValorUnitario()); 
                ps.executeUpdate();
            }else{
                ps = connection.prepareStatement(sql3);  
                ps.setLong(1, estoque.getIdProduto());
                ps.setLong(2, estoque.getQuantidade());
                ps.setLong(3, estoque.getValorUnitario());
                
                ps.executeQuery();
            }
            
            
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro CabeleireiroDAO " + e);
        } finally {

            try {
                if (ps != null) {
                    ps.close();
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
    
    public long somaProdutosEstoque(){
        
        String sql = "SELECT SUM(QUANTIDADE) as QTD FROM ESTOQUE ";

      
        Connection connection = null;
        PreparedStatement ps = null;
        long nProdutos = 0;
        
        try {
            connection = new ConnectionMVC().getConnection();
           
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    nProdutos += rs.getLong("QTD");
                }
            }
  
            return nProdutos;
            
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro CabeleireiroDAO " + e);
        } finally {

            try {
                if (ps != null) {
                    ps.close();
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
        return nProdutos;
    }
    
}
