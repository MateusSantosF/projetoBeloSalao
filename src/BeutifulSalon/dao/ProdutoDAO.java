package BeutifulSalon.dao;
/**
 *
 * @author Mateus Santos
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import BeutifulSalon.model.Produto;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ProdutoDAO {
    
    
    public void cadastrarProduto(Produto produto){
        String sql = "INSERT INTO produto(nome, marca, preco,datareg) values (?,?,?,?)";
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try{
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
  
            pStatement.setString(1, produto.getNome());
            pStatement.setString(2, produto.getMarca());
            pStatement.setLong(3, produto.getPreco());
            pStatement.setDate(4, java.sql.Date.valueOf(produto.getDataReg()));
            pStatement.execute();
            
        } catch(SQLException e){
           JOptionPane.showMessageDialog(null,"Erro ao cadastrar cliente no banco" + e);
        } finally{
            try{
                if(pStatement != null){
                    pStatement.close();
                }
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Erro ao fechar statement" + e);
            }try{
                if(connection!= null){
                    connection.close();
                }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão" + e);
            }
        }
    }

    public ArrayList<Produto> listarProdutos() {
 
     String sql  = "SELECT IDPRODUTO, NOME, MARCA, PRECO, DATAREG FROM PRODUTO WHERE PRODUTO.EXCLUIDO = FALSE ORDER BY DATAREG DESC";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Produto> produtos =  null;
        
        try{
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            
            ResultSet rs = pStatement.executeQuery();
     
            
            if(rs != null){
                produtos = new ArrayList<>();
                
                while(rs.next()){
                    Produto produtoAtual = new Produto();
                    produtoAtual.setNome(rs.getString("NOME"));
                    produtoAtual.setMarca(rs.getString("MARCA"));
                    produtoAtual.setPreco(rs.getLong("PRECO"));
                    produtoAtual.setDataReg(rs.getDate("DATAREG").toLocalDate());
                    produtoAtual.setId_produto(rs.getLong("IDPRODUTO"));
  
                    produtos.add(produtoAtual);
               
                }
                
            }

         
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ConnectionMVC: " + e);
        }finally{
            
            try {
                if(pStatement != null) pStatement.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar statement" + e);
            }
            
            try {
                if(connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão" + e);
            }
            
        }
        
        return produtos;
    
    }
    public ArrayList<Produto> listarProdutos(String nome) throws ExceptionDAO{
        
        String sql  = "SELECT IDPRODUTO, NOME, MARCA, PRECO, DATAREG FROM "
                + "PRODUTO WHERE NOME LIKE '%"+nome+"%' AND PRODUTO.EXCLUIDO = FALSE ORDER BY DATAREG DESC";
   
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        ArrayList<Produto> produtos =  null;
            
       try{
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            
            ResultSet rs = pStatement.executeQuery();
           
            
            if(rs != null){
                produtos = new ArrayList<>();
                
                while(rs.next()){
                    Produto produtoAtual = new Produto();
                    produtoAtual.setNome(rs.getString("NOME"));
                    produtoAtual.setMarca(rs.getString("MARCA"));
                    produtoAtual.setPreco(rs.getLong("PRECO"));
                    produtoAtual.setDataReg(rs.getDate("DATAREG").toLocalDate());
                    produtoAtual.setId_produto(rs.getLong("IDPRODUTO"));
                    produtos.add(produtoAtual);
                }
                
            }
         
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ConnectionMVC: " + e);
        }finally{
            
            try {
                if(pStatement != null) pStatement.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar statement" + e);
            }
            
            try {
                if(connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão" + e);
            }
            
        }
        
        return produtos;
                
    }
    
    

    public void deletarProduto(long idProdutoSelecionado) throws ExceptionDAO {
        
        String sql  = "UPDATE PRODUTO SET EXCLUIDO = TRUE WHERE IDPRODUTO = ?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        try {
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, idProdutoSelecionado);
            pStatement.executeUpdate();
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Erro ao excluir Produto: " + e);
        }finally{
            
            try {
                if(pStatement != null) pStatement.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar statement" + e);
            }
            
            try {
                if(connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão" + e);
            }
            
        }

    }
    
    public Produto editarProduto(long id_produto){
        
        
        String sql  = "SELECT IDPRODUTO,NOME, MARCA, PRECO, DATAREG FROM PRODUTO WHERE IDPRODUTO = ?"; 
                  
        PreparedStatement pStatement = null;
        Connection connection = null;
        ResultSet rs = null;
        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            
            pStatement.setLong(1, id_produto);
            
            rs = pStatement.executeQuery();
            
            Produto produto = new Produto(); 
      
            if(rs != null){                
                while(rs.next()){
               
                produto.setNome(rs.getString("NOME"));
                produto.setMarca(rs.getString("MARCA"));
                produto.setId_produto(rs.getLong("IDPRODUTO"));
                produto.setPreco(rs.getLong("PRECO"));
                }
            }
           
            return produto;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro produtoDAO " + e);
        }finally{
            
            try {
                if(pStatement != null) pStatement.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar statement" + e);
            }
            
            try {
                if(connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão" + e);
            }
           
        }
       
        return null;
    }

    public void atualizarProduto(Produto produto) {
        
        String sqlScript = "UPDATE PRODUTO SET NOME = ? , MARCA = ?, PRECO = ?"
                + " WHERE IDPRODUTO = ? ";
        
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sqlScript);
            
            pStatement.setLong(4, produto.getId_produto());
            pStatement.setString(1, produto.getNome());
            pStatement.setString(2, produto.getMarca());
            pStatement.setLong(3, produto.getPreco());
            pStatement.execute(); 
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro atualizar produto: "  + e);
        }finally{
            
            try {
                if(pStatement != null) pStatement.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar statement" + e);
            }
            
            try {
                if(connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão" + e);
            }
           
        }


    }

    public Produto buscarProduto(long id) {

        String sql = "SELECT NOME, MARCA, PRECO, IDPRODUTO FROM PRODUTO WHERE IDPRODUTO = ?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
      
        
        try{
            
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, id);         
            rs = pStatement.executeQuery();
            
            Produto produtoBuscado = new Produto();
   
            if(rs != null){                
                while(rs.next()){  
             
                produtoBuscado.setNome(rs.getString("NOME"));
                produtoBuscado.setMarca(rs.getString("MARCA"));
                produtoBuscado.setPreco(rs.getLong("PRECO"));
                produtoBuscado.setId_produto(rs.getLong("IDPRODUTO"));
  
                }
            }
            
            return produtoBuscado;
       
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco(DAO) " + e);
        }finally{
              try {
                if(pStatement != null) pStatement.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar statement" + e);
            }
            
            try {
                if(connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão" + e);
            }
            
        }

        return null;
    }
    
    public Produto buscarUltimoProdutoCadastrado() {

        String sql = "SELECT NOME, MARCA, PRECO, IDPRODUTO FROM PRODUTO WHERE IDPRODUTO = (SELECT MAX(IDPRODUTO) FROM PRODUTO)";
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
      
        
        try{
            
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
                 
            rs = pStatement.executeQuery();
            
            Produto produtoBuscado = new Produto();
   
            if(rs != null){                
                while(rs.next()){  
             
                produtoBuscado.setNome(rs.getString("NOME"));
                produtoBuscado.setMarca(rs.getString("MARCA"));
                produtoBuscado.setPreco(rs.getLong("PRECO"));
                produtoBuscado.setId_produto(rs.getLong("IDPRODUTO"));
  
                }
            }
            
            return produtoBuscado;
       
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco(DAO) " + e);
        }finally{
              try {
                if(pStatement != null) pStatement.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar statement" + e);
            }
            
            try {
                if(connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão" + e);
            }
            
        }

        return null;
    }
    
    public Produto buscarProdutoNomeEMarca(String nome, String marca) {

        String sql = "SELECT NOME, MARCA, PRECO, IDPRODUTO FROM PRODUTO WHERE "
                + "NOME = ? AND MARCA = ?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
      
        
        try{
            
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, nome);
            pStatement.setString(2, marca);
            rs = pStatement.executeQuery();
            
            Produto produtoBuscado = new Produto();
   
            if(rs != null){                
                while(rs.next()){  
             
                produtoBuscado.setNome(rs.getString("NOME"));
                produtoBuscado.setMarca(rs.getString("MARCA"));
                produtoBuscado.setPreco(rs.getLong("PRECO"));
                produtoBuscado.setId_produto(rs.getLong("IDPRODUTO"));
  
                }
            }
            
            return produtoBuscado;
       
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco(DAO) busca por nomeemarca" + e);
        }finally{
              try {
                if(pStatement != null) pStatement.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar statement" + e);
            }
            
            try {
                if(connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão" + e);
            }
            
        }

        return null;
    }
    
     public int verificaExistenciaProduto(String nome, String marca) {

        String sql = "SELECT NOME, MARCA FROM PRODUTO WHERE NOME = ? AND MARCA = ?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        int existe = 0;
      
        
        try{
            
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, nome);
            pStatement.setString(2, marca);
                  
            rs = pStatement.executeQuery();
            
            Produto produtoBuscado = new Produto();
   
            if(rs != null){                
                while(rs.next()){  
             
                existe++;
  
                }
            }
            System.out.println("EXISTE=>" + existe);
            return existe;
       
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Erro ao consultar o banco(DAO) existencia " + e);
        }finally{
              try {
                if(pStatement != null) pStatement.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar statement" + e);
            }
            
            try {
                if(connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão" + e);
            }
            
        }

        return existe;
    }
    
    
    
    public List<Produto> produtosMaisVendidosDoAno(int anoReferente){
        String sql = "SELECT SUM(ITEM_VENDA.QUANTIDADE) AS QTD , PRODUTO.NOME " +
        " FROM ITEM_VENDA " +
        " INNER JOIN PRODUTO ON PRODUTO.IDPRODUTO = ITEM_VENDA.ID_PRODUTO " +
        " INNER JOIN VENDA ON VENDA.ID_VENDA = ITEM_VENDA.ID_VENDA " +
        " WHERE VENDA.DATA BETWEEN ? AND ? AND PRODUTO.EXCLUIDO = FALSE " +
        " GROUP BY ITEM_VENDA.ID_PRODUTO ORDER BY SUM(ITEM_VENDA.QUANTIDADE) DESC LIMIT 5";

        List<Produto> produtos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
    
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            

            long inicioDoAno = LocalDate.ofYearDay(anoReferente, 1).toEpochDay() * 24 * 60 * 60 * 1000;
            long fimDoAno = LocalDate.ofYearDay(anoReferente, 1).plusYears(1).toEpochDay() * 24 * 60 * 60 * 1000; 
            pStatement.setLong(1, inicioDoAno);
            pStatement.setLong(2, fimDoAno);

            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    Produto p = new Produto();
                    p.setNome(rs.getString("NOME"));
                    p.setRendimento(rs.getInt("QTD"));
                    
                    produtos.add(p);
                }
            }
            
            return produtos;
       

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
        
        return produtos;
    }
    
}
