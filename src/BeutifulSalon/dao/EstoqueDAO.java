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

    public void atualizaEstoque(Estoque estoque, boolean venda){
        
      String sql = "SELECT count(1) AS QTD FROM ESTOQUE WHERE ID_PRODUTO = ?";
      String sql2;
      String sql3;
      if(venda){
        sql2 = "UPDATE ESTOQUE SET QUANTIDADE = QUANTIDADE + ? WHERE ID_PRODUTO  = ?";
      }else{
        sql2 = "UPDATE ESTOQUE SET QUANTIDADE = QUANTIDADE + ?, VALOR_UNITARIO = ? WHERE ID_PRODUTO  = ?";
      }
     
      if(venda){
           sql3 = "INSERT INTO ESTOQUE (ID_PRODUTO, QUANTIDADE) VALUES (?,?)";
      }else{
          sql3 = "INSERT INTO ESTOQUE (ID_PRODUTO, QUANTIDADE, VALOR_UNITARIO) VALUES (?,?,?)";
      }
    
      
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
               
                if(!venda){ // eh uma compra
                    ps.setLong(3, estoque.getIdProduto());
                    ps.setLong(1, estoque.getQuantidade());
                    ps.setLong(2, estoque.getValorUnitario()); 
                }else{
                    ps.setLong(2, estoque.getIdProduto());
                    ps.setLong(1, estoque.getQuantidade());
    
                }
                
                ps.executeUpdate();
            }else{
                ps = connection.prepareStatement(sql3);  
                ps.setLong(1, estoque.getIdProduto());
                ps.setLong(2, estoque.getQuantidade());
                if(!venda){// eh uma compra
                    ps.setLong(3, estoque.getValorUnitario()); 
                    ps.setLong(1, estoque.getIdProduto());
                    ps.setLong(2, estoque.getQuantidade());
                }else{
                    ps.setLong(2, estoque.getIdProduto());
                    ps.setLong(1, estoque.getQuantidade());
    
                }
                
                ps.execute();
            }
            
            
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro EstoqueDAO" + e);
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
    
    public long ultimoValorPagoProduto(long idProduto){
        
        String sql = "SELECT VALOR_UNITARIO AS VALOR FROM ESTOQUE WHERE ID_PRODUTO = ? ";

        Connection connection = null;
        PreparedStatement ps = null;
        long valor = 0;
        
        try {
            connection = new ConnectionMVC().getConnection();
           
            ps = connection.prepareStatement(sql);
            ps.setLong(1, idProduto);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    valor += rs.getLong("VALOR");
                }
            }
  
            return valor;
            
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
        return valor;
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
    
    public long quantidadeProduto(long idProduto){
        
        String sql = "SELECT SUM(QUANTIDADE) as QTD FROM ESTOQUE WHERE ID_PRODUTO = ? ";

      
        Connection connection = null;
        PreparedStatement ps = null;
        long nProdutos = 0;
        
        try {
            connection = new ConnectionMVC().getConnection();
           
            ps = connection.prepareStatement(sql);
            ps.setLong(1, idProduto);
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
