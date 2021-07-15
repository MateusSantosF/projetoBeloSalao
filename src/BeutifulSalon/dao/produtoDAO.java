package BeutifulSalon.dao;
/**
 *
 * @author Melissa
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import BeutifulSalon.model.Produto;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class produtoDAO {
    
    
    public void cadastrarProduto(Produto produto){
        String sql = "INSERT INTO produto(nome, marca, preco, dataValidade, datareg) values (?,?,?,?,?)";
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try{
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
  
            pStatement.setString(1, produto.getNome());
            pStatement.setString(2, produto.getMarca());
            pStatement.setLong(3, produto.getPreco());
            pStatement.setDate(4, java.sql.Date.valueOf(produto.getDataValidade()));
            pStatement.setDate(5, java.sql.Date.valueOf(produto.getDataReg()));
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
 
     String sql  = "SELECT IDPRODUTO, NOME, MARCA, PRECO, DATAREG, DATAVALIDADE FROM PRODUTO ORDER BY DATAREG DESC";
        
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
                    produtoAtual.setDataValidade(rs.getDate("DATAVALIDADE").toLocalDate());
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
        
        String sql  = "SELECT IDPRODUTO, NOME, MARCA, PRECO, DATAREG, DATAVALIDADE FROM "
                + "PRODUTO WHERE NOME LIKE '%"+nome+"%' ORDER BY DATAREG DESC";
   
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
                    produtoAtual.setDataValidade(rs.getDate("DATAVALIDADE").toLocalDate());
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
    
    

    public void deletarProduto(long idProdutoSelecionado) {
        
        String sql  = "DELETE FROM PRODUTO WHERE IDPRODUTO = ?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        try {
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, idProdutoSelecionado);
            pStatement.executeUpdate();
            
        } catch (Exception e) {
            
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
        
        
        String sql  = "SELECT IDPRODUTO,NOME, MARCA, PRECO, DATAREG, DATAVALIDADE FROM PRODUTO WHERE IDPRODUTO = ?"; 
                  
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
                produto.setDataValidade(rs.getDate("DATAVALIDADE").toLocalDate());
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
        
        String sqlScript = "UPDATE PRODUTO SET NOME = ? , MARCA = ?, PRECO = ? , DATAVALIDADE = ?"
                + " WHERE IDPRODUTO = ? ";
        
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sqlScript);
            
            pStatement.setLong(5, produto.getId_produto());
            pStatement.setString(1, produto.getNome());
            pStatement.setString(2, produto.getMarca());
            pStatement.setLong(3, produto.getPreco());
            pStatement.setDate(4, java.sql.Date.valueOf(produto.getDataValidade()));
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
    
}
