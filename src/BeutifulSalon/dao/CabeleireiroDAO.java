/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.view.Edicao.EditarCliente;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import BeutifulSalon.model.Cabeleireiro;

/**
 *
 * @author mateus
 */
public class CabeleireiroDAO {
    
    public void cadastrarCabeleireiro(Cabeleireiro cabeleireiro){
        String sqlScript = "INSERT INTO CABELEIREIRO (CPF ,EMAIL, NOME, SENHA) + VALUES(?,?,?,?)";
    
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setString(1, cabeleireiro.getCpf());
            pStatement.setString(2, cabeleireiro.getEmail());
            pStatement.setString(3, cabeleireiro.getNome());
            pStatement.setString(4, cabeleireiro.getSenha());
            pStatement.execute(); 
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao cadastrar cabeleireiro." + e);
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
    
    public Cabeleireiro selecionaCabeleireiro(){
             
        String sqlScript = "SELECT * FROM CABELEIREIRO";
        
        PreparedStatement pStatement = null;
        Connection connection = null;
        ResultSet rs = null;
        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sqlScript);
           
            
            rs = pStatement.executeQuery();
            Cabeleireiro cabeleireiro = new Cabeleireiro(); 
      
            if(rs != null){                
                while(rs.next()){
                cabeleireiro.setNome(rs.getString("NOME"));
                cabeleireiro.setEmail(rs.getString("EMAIL"));
                cabeleireiro.setCpf(rs.getString("CPF"));
                cabeleireiro.setSenha(rs.getString("SENHA"));         
                }
            }
           
            return cabeleireiro;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro CabeleireiroDAO " + e);
        }finally{
            
            try {
                if(pStatement != null) pStatement.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar statement" + e);
            }
            
            try {
                if(connection != null) connection.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão" + e);
            }
           
        }
       
        return null;
    }

    
}
