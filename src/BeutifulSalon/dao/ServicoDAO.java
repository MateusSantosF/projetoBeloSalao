 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.dao;

import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class ServicoDAO {

    public ArrayList<Servico> listarServicos() {

        String sql = "SELECT ID_SERVICO, NOME, PRECO FROM SERVICO ORDER BY NOME DESC";

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
                    servicoAtual.setValor(rs.getLong("PRECO"));
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
                    servicoAtual.setValor(rs.getLong("PRECO"));
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
                servicoBuscado.setValor(rs.getLong("PRECO"));
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

}
