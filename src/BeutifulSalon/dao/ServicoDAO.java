 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.dao;

import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.ItemCompra;
import BeutifulSalon.model.Produto;
import BeutifulSalon.model.Servico;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class ServicoDAO {

    public List<Servico> listarServicos() throws ExceptionDAO {

        String sql = "SELECT ID_SERVICO, NOME, PRECO FROM SERVICO ORDER BY NOME DESC";

        Connection connection = null;
        PreparedStatement pStatement = null;
        List<Servico> servicos = null;

        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);

            ResultSet rs = pStatement.executeQuery();

            if (rs != null) {
                servicos = new ArrayList<>();

                while (rs.next()) {
                    Servico servicoAtual = new Servico();
                    servicoAtual.setNome(rs.getString("NOME"));
                    servicoAtual.setPreco(rs.getLong("PRECO"));
                    servicoAtual.setId(rs.getLong("ID_SERVICO"));
                    servicos.add(servicoAtual);
                }

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ConnectionMVC: " + e);
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

        return servicos;

    }

    public ArrayList<Servico> listarServicos(String nome) {

        String sql = "SELECT ID_SERVICO, NOME, PRECO FROM SERVICO WHERE NOME LIKE '%" + nome + "%' ORDER BY NOME DESC";

        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Servico> servicos = null;

        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);

            ResultSet rs = pStatement.executeQuery();

            if (rs != null) {
                servicos = new ArrayList<>();

                while (rs.next()) {
                    Servico servicoAtual = new Servico();
                    servicoAtual.setNome(rs.getString("NOME"));
                    servicoAtual.setPreco(rs.getLong("PRECO"));
                    servicoAtual.setId(rs.getLong("ID_SERVICO"));
                    servicos.add(servicoAtual);
                }

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ConnectionMVC: " + e);
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

        return servicos;

    }
    
    public Servico buscarServico(long id){
        
        String sql = "SELECT ID_SERVICO, NOME, PRECO FROM SERVICO WHERE ID_SERVICO = ?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
       
        try{
            
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, id);         
            rs = pStatement.executeQuery();
            
            Servico servicoBuscado = new Servico();
   
            if(rs != null){                
                while(rs.next()){  
             
                servicoBuscado.setNome(rs.getString("NOME"));
                servicoBuscado.setPreco(rs.getLong("PRECO"));
                servicoBuscado.setId(rs.getLong("ID_SERVICO"));
  
                }
            }
            
            return servicoBuscado;
       
            
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

    public void cadastrarServico(Servico servico) throws SQLException {
        String cadastraServico = "INSERT INTO SERVICO (NOME, PRECO, TEMPOGASTO) VALUES (?, ?, ?)";
        String cadastraProdutosServicos = "INSERT INTO PRODUTO_SERVICO (ID_PRODUTO, RENDIMENTO, ID_SERVICO) VALUES (? ,?, (SELECT ID_SERVICO FROM SERVICO ORDER BY ID_SERVICO DESC LIMIT 1))";
        
        Connection connection = null;
        PreparedStatement pStatement = null;

        try {

            connection = new ConnectionMVC().getConnection();
            connection.setAutoCommit(false);

            pStatement = connection.prepareStatement(cadastraServico);
            pStatement.setString(1, servico.getNome());
            pStatement.setLong(2, servico.getPreco());
            pStatement.setTime(3,java.sql.Time.valueOf(servico.getTempoGasto()));

            int firstInsert = pStatement.executeUpdate();

            if (firstInsert > 0) {
                try {

                    ArrayList<Produto> produtos = servico.getProdutos();
                    
                    if(!produtos.isEmpty()){
                      for (Produto p : produtos) {
                        pStatement = connection.prepareStatement(cadastraProdutosServicos);
                        pStatement.setLong(1, p.getId_produto());
                        pStatement.setInt(2, p.getRendimento());
                        pStatement.executeUpdate();
                       }  
                    }
                    

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro registrar Produto_Servico" + e);
                    connection.rollback();
                }
            }

            connection.commit();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro DAO" + e);
            connection.rollback();

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
     public Servico buscarServicoAgendamento(long id){
        
        String sql = "SELECT * FROM SERVICO WHERE ID_SERVICO = ?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
      
        
        try{
            
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, id);         
            rs = pStatement.executeQuery();
            
            Servico servicoBuscado = new Servico();
   
            if(rs != null){                
                while(rs.next()){  
             
                servicoBuscado.setNome(rs.getString("NOME"));
                servicoBuscado.setPreco(rs.getLong("PRECO"));
                servicoBuscado.setId(rs.getLong("ID_SERVICO"));
                servicoBuscado.setTempoGasto(rs.getTime("TEMPOGASTO").toLocalTime());
  
                }
            }
            
            return servicoBuscado;
       
            
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
    public ArrayList<Servico> buscaServicoPeloAgendamento(long idAgendamento){
        
        String sql = "SELECT ID_SERVICO FROM AGENDAMENTO_SERVICO WHERE ID_AGENDAMENTO = ?";
        ArrayList<Servico> servicos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
      
        
        try{
            
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, idAgendamento);         
            rs = pStatement.executeQuery();
            
        
   
            if(rs != null){                
                while(rs.next()){  
                
                 Servico servicoBuscado = buscarServicoAgendamento(rs.getLong("ID_SERVICO"));
                 servicos.add(servicoBuscado);
                }
            }
            
            return servicos;
       
            
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


