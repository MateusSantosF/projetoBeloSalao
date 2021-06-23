/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.dao;

import BeutifulSalon.model.Orcamento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class OrcamentoDAO {
    
    public void cadastrarOrcamento(Orcamento orcamento){
        
        String sqlScript = "INSERT INTO ORCAMENTO (NOME, JANEIRO, FEVEREIRO, MARCO, ABRIL,"+ 
               "MAIO, JUNHO, JULHO, AGOSTO, SETEMBRO, OUTUBRO, NOVEMBRO, DEZEMBRO, PREVISTO)"+
               "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
               
                    
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setString(1, orcamento.getNome());
            pStatement.setDouble(2, orcamento.getJan());
            pStatement.setDouble(3, orcamento.getFev());
            pStatement.setDouble(4, orcamento.getMar());
            pStatement.setDouble(5, orcamento.getAbr());
            pStatement.setDouble(6, orcamento.getMai());
            pStatement.setDouble(7, orcamento.getJun());
            pStatement.setDouble(8, orcamento.getJul());
            pStatement.setDouble(9, orcamento.getAgo());
            pStatement.setDouble(10, orcamento.getSet());
            pStatement.setDouble(11, orcamento.getOut());
            pStatement.setDouble(12, orcamento.getNov());
            pStatement.setDouble(13, orcamento.getDez());
            pStatement.setBoolean(14, orcamento.isPrevisto()); // TRUE quando eh orçamento PREVISTO
            pStatement.execute(); 
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao cadastrar cliente no banco" + e);
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

    }
}
