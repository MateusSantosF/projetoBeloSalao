/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.dao;

import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.controller.EstoqueController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import BeutifulSalon.model.Compra;
import BeutifulSalon.model.Item;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mateus
 */
public class CompraProdutoDAO {

    public void cadastraCompra(Compra compra) {

        String insertCompra = "INSERT INTO COMPRA (DATA, VALORTOTAL, VALORDESCONTO) "
                + "VALUES (?, ?, ?)";

        String insertItemCompra = "INSERT INTO ITEM_COMPRA (PRECOUNITARIO, QUANTIDADE, PRECOTOTAL, ID_PRODUTO, ID_COMPRA) "
                + "VALUES (?,?,?,?,(SELECT ID_COMPRA  FROM COMPRA ORDER BY ID_COMPRA DESC LIMIT 1))";

        Connection connection = null;
        PreparedStatement pStatement = null;
        EstoqueController estoque = new EstoqueController();

        try {

            connection = new ConnectionMVC().getConnection();
            connection.setAutoCommit(false);

            pStatement = connection.prepareStatement(insertCompra);
            pStatement.setDate(1, java.sql.Date.valueOf(compra.getData()));
            pStatement.setLong(2, compra.getValorTotal());
            pStatement.setLong(3, compra.getValorDesconto());
;

            int firstInsert = pStatement.executeUpdate();

            if (firstInsert > 0) {
                try {

                    List<Item> itens = compra.getItensCompra();
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

     
                boolean sucesso = estoque.atualizaEstoque(compra);

                if (sucesso == false) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque");
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
    
    
    public boolean atualizarCompra(Compra compra, List<Item> itensAntigos) {
        
        String deleteItemCompra = "DELETE FROM ITEM_COMPRA WHERE ID_COMPRA = ?";
        
        String updateCompra = "UPDATE COMPRA SET DATA = ?, VALORTOTAL = ?, VALORDESCONTO = ? WHERE ID_COMPRA = ?";

        String insertItemCompra = "INSERT INTO ITEM_COMPRA (PRECOUNITARIO, QUANTIDADE, PRECOTOTAL, ID_PRODUTO, ID_COMPRA) "
                + "VALUES (?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement pStatement = null;
        EstoqueController estoque = new EstoqueController();

        try {

            connection = new ConnectionMVC().getConnection();
            connection.setAutoCommit(false);
            
            if(!new EstoqueController().atualizaEstoqueExclusaoCompra(itensAntigos))return false;
            pStatement = connection.prepareStatement(deleteItemCompra);
            pStatement.setLong(1, compra.getIdCompra());
            pStatement.execute();
            
            pStatement = connection.prepareStatement(updateCompra);
            pStatement.setDate(1, java.sql.Date.valueOf(compra.getData()));
            pStatement.setLong(2, compra.getValorTotal());
            pStatement.setLong(3, compra.getValorDesconto());
            pStatement.setLong(4, compra.getIdCompra());
       
            int firstInsert = pStatement.executeUpdate();
            if (firstInsert > 0) {
                try {

                    List<Item> itens = compra.getItensCompra();
                    for (Item it : itens) {

                        pStatement = connection.prepareStatement(insertItemCompra);
                        pStatement.setLong(1, it.getPreco());
                        pStatement.setInt(2, it.getQuantidade());
                        pStatement.setLong(3, it.getPrecoTotal());
                        pStatement.setLong(4, it.getId_produto());
                        pStatement.setLong(5, compra.getIdCompra());
                        pStatement.executeUpdate();
                   
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro registrar itemCompra" + e);
                    connection.rollback();
                }
            }else{
                connection.rollback();
                new EstoqueController().atualizaEstoqueExclusaoVenda(itensAntigos);
                return false;
            }

          connection.commit();

     
                boolean sucesso = estoque.atualizaEstoque(compra);

                if (sucesso == false) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque");
                    return false;
                }
          
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro DAO" + e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                
                return false;
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
        
        return false;
    }
    
    public long retornaSomaDeComprasMensais(Month mes){
        
        String sql = "SELECT (SUM(COMPRA.VALORTOTAL) - SUM(VALORDESCONTO)) AS RENDAMENSAL FROM COMPRA WHERE COMPRA.DATA BETWEEN ? AND ?";
        long compras = 0;
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
    
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
        
            pStatement.setLong(1, new ManipulaData().inicioDoMes(LocalDate.now(), mes));
            pStatement.setLong(2, new ManipulaData().fimDoMes(LocalDate.now(), mes));

            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    compras = rs.getInt("RENDAMENSAL");
                }
            }          
            return compras;     

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro DAO" + e);
   
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
        
        return compras;
    }
    
    public List<Item> retornaProdutosDaCompra(long idCompra){
        String sql = "SELECT PRECOUNITARIO, QUANTIDADE, PRECOTOTAL, ID_PRODUTO, ID_COMPRA, PRODUTO.NOME, PRODUTO.MARCA FROM ITEM_COMPRA"
                + " INNER JOIN PRODUTO ON PRODUTO.IDPRODUTO = ITEM_COMPRA.ID_PRODUTO  WHERE ID_COMPRA = ?";
        List<Item> itemCompras = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, idCompra);
            
            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                   Item i = new Item();
                   i.setQuantidade(rs.getInt("QUANTIDADE"));
                   i.setPrecoTotal(rs.getLong("PRECOTOTAL"));
                   i.setId_produto(rs.getLong("ID_PRODUTO"));
                   i.setId_compra(rs.getLong("ID_COMPRA"));
                   i.setPreco(rs.getLong("PRECOUNITARIO"));
                   i.setNome(rs.getString("NOME"));
                   i.setMarca(rs.getString("MARCA"));  
                   itemCompras.add(i);
                }
            }
            
            return itemCompras;
            
            
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Erro DAO" + e);
        }finally {

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
        
        return itemCompras;
    }
    
    public List<Compra> retornaTodasCompras(){
        
        String sql = "SELECT * FROM COMPRA GROUP BY COMPRA.ID_COMPRA ORDER BY DATA DESC";
        List<Compra> compras = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
    
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
        


            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                   Compra c = new Compra();
                   c.setIdCompra(rs.getLong("ID_COMPRA"));
                   c.setData(rs.getDate("DATA").toLocalDate());
                   c.setValorTotal(rs.getLong("VALORTOTAL"));
                   c.setValorDesconto(rs.getLong("VALORDESCONTO"));
                   c.setItensCompra(retornaProdutosDaCompra(c.getIdCompra()));
                   compras.add(c);
                }
            }
            
            return compras;
       

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro DAO" + e);
   
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
        
        return compras;
    
    }
    
      public List<Compra> retornaTodasComprasDoAno(){
        
        String sql = "SELECT * FROM COMPRA"
                + " WHERE COMPRA.DATA BETWEEN ? AND ? GROUP BY COMPRA.ID_COMPRA ORDER BY DATA DESC";
        List<Compra> compras = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
    
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            long inicioDoAno = LocalDate.ofYearDay(LocalDate.now().getYear(), 1).toEpochDay() * 24 * 60 * 60 * 1000;
            long fimDoAno = LocalDate.ofYearDay(LocalDate.now().getYear(), 1).plusYears(1).toEpochDay() * 24 * 60 * 60 * 1000; 
            pStatement.setLong(1, inicioDoAno);
            pStatement.setLong(2, fimDoAno);
            
            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                   Compra c = new Compra();
                   c.setIdCompra(rs.getLong("ID_COMPRA"));
                   c.setData(rs.getDate("DATA").toLocalDate());
                   c.setValorTotal(rs.getLong("VALORTOTAL"));
                   c.setValorDesconto(rs.getLong("VALORDESCONTO"));
                   c.setItensCompra(retornaProdutosDaCompra(c.getIdCompra()));
                   compras.add(c);
                }
            }
            
            return compras;
       

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro DAO" + e);
   
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
        
        return compras;
    
    }
    
    public long retornaMaxID(){
        
        String sql = "SELECT MAX(IDPRODUTO) AS MAX FROM PRODUTO";
        long compras = 0;
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
    
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
        


            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                   compras =  rs.getLong("MAX");
                }
            }
            
            return compras;
       

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro DAO" + e);
   
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
        
        return compras;
    }

    public List<Compra> retornaComprasDashboard() {
        
        String sql = "SELECT * FROM COMPRA GROUP BY COMPRA.ID_COMPRA ORDER BY DATA DESC LIMIT 100";
        List<Compra> compras = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
    
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
        


            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                   Compra c = new Compra();
                   c.setIdCompra(rs.getLong("ID_COMPRA"));
                   c.setData(rs.getDate("DATA").toLocalDate());
                   c.setValorTotal(rs.getLong("VALORTOTAL"));
                   c.setValorDesconto(rs.getLong("VALORDESCONTO"));
                   c.setItensCompra(retornaProdutosDaCompra(c.getIdCompra()));
                   compras.add(c);
                }
            }
            
            return compras;
       

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro DAO" + e);
   
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
        
        return compras;
    }

    public List<Compra> getComprasPorNomeProduto(String nomeProduto) {
        
        String sql = "SELECT COMPRA.ID_COMPRA, DATA, VALORTOTAL, VALORDESCONTO, PRODUTO.NOME FROM COMPRA "
                + " INNER JOIN ITEM_COMPRA ON ITEM_COMPRA.ID_COMPRA = COMPRA.ID_COMPRA"
                + " INNER JOIN PRODUTO ON ITEM_COMPRA.ID_PRODUTO = PRODUTO.IDPRODUTO"
                + " WHERE PRODUTO.NOME LIKE '%"+ nomeProduto +"%' "
                + "GROUP BY COMPRA.ID_COMPRA ORDER BY DATA DESC";
        List<Compra> compras = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
    
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);


            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                   Compra c = new Compra();
                   c.setIdCompra(rs.getLong("ID_COMPRA"));
                   c.setData(rs.getDate("DATA").toLocalDate());
                   c.setValorTotal(rs.getLong("VALORTOTAL"));
                   c.setValorDesconto(rs.getLong("VALORDESCONTO"));
                   c.setItensCompra(retornaProdutosDaCompra(c.getIdCompra()));
                   compras.add(c);
                }
            }
            
            return compras;
       

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro DAO" + e);
   
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
        
        return compras;
    }
    
     public List<Compra> getComprasPorNomeProdutoDoAno(String nomeProduto) {
        
        String sql = "SELECT COMPRA.ID_COMPRA, DATA, VALORTOTAL, VALORDESCONTO, PRODUTO.NOME FROM COMPRA "
                + " INNER JOIN ITEM_COMPRA ON ITEM_COMPRA.ID_COMPRA = COMPRA.ID_COMPRA"
                + " INNER JOIN PRODUTO ON ITEM_COMPRA.ID_PRODUTO = PRODUTO.IDPRODUTO"
                + " WHERE PRODUTO.NOME LIKE '%"+ nomeProduto +"%' AND COMPRA.DATA BETWEEN ? AND ? "
                + "GROUP BY COMPRA.ID_COMPRA ORDER BY DATA DESC";
        List<Compra> compras = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
    
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
        
            long inicioDoAno = LocalDate.ofYearDay(LocalDate.now().getYear(), 1).toEpochDay() * 24 * 60 * 60 * 1000;
            long fimDoAno = LocalDate.ofYearDay(LocalDate.now().getYear(), 1).plusYears(1).toEpochDay() * 24 * 60 * 60 * 1000; 
            pStatement.setLong(1, inicioDoAno);
            pStatement.setLong(2, fimDoAno);


            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                   Compra c = new Compra();
                   c.setIdCompra(rs.getLong("ID_COMPRA"));
                   c.setData(rs.getDate("DATA").toLocalDate());
                   c.setValorTotal(rs.getLong("VALORTOTAL"));
                   c.setValorDesconto(rs.getLong("VALORDESCONTO"));
                   c.setItensCompra(retornaProdutosDaCompra(c.getIdCompra()));
                   compras.add(c);
                }
            }
            
            return compras;
       

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro DAO" + e);
   
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
        
        return compras;
    }

    public boolean excluirCompra(Compra compra) {

        String sql = "DELETE FROM COMPRA WHERE ID_COMPRA = ?";
        String sql2 = "DELETE FROM ITEM_COMPRA WHERE ID_COMPRA = ?";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        PreparedStatement pStatement2 = null;
  
  
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, compra.getIdCompra());
            
            pStatement2 = connection.prepareStatement(sql2);
            pStatement2.setLong(1, compra.getIdCompra());
            
            
            return pStatement.executeUpdate() == 1 && pStatement2.executeUpdate() >= 1;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro DAO" + e);

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
        return false;
    }

   

   

}
