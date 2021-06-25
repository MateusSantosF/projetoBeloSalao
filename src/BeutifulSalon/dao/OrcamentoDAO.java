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
import java.util.ArrayList;
import java.sql.ResultSet;
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
            pStatement.setLong(2, orcamento.getJan());
            pStatement.setLong(3, orcamento.getFev());
            pStatement.setLong(4, orcamento.getMar());
            pStatement.setLong(5, orcamento.getAbr());
            pStatement.setLong(6, orcamento.getMai());
            pStatement.setLong(7, orcamento.getJun());
            pStatement.setLong(8, orcamento.getJul());
            pStatement.setLong(9, orcamento.getAgo());
            pStatement.setLong(10, orcamento.getSet());
            pStatement.setLong(11, orcamento.getOut());
            pStatement.setLong(12, orcamento.getNov());
            pStatement.setLong(13, orcamento.getDez());
            pStatement.setBoolean(14, orcamento.isPrevisto()); // TRUE quando eh orçamento PREVISTO
            pStatement.execute(); 
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ao cadastrar orçamento no banco" + e);
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
    
    
    public ArrayList<Orcamento> listarOrcamento(){
        String sql  = "SELECT NOME,JANEIRO,FEVEREIRO,MARCO,ABRIL,MAIO,JUNHO,JULHO,AGOSTO,SETEMBRO,OUTUBRO,NOVEMBRO,DEZEMBRO FROM ORCAMENTO";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Orcamento> orcamentos =  null;
        
        try{
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            
            ResultSet rs = pStatement.executeQuery();
           
            
            if(rs != null){
                orcamentos = new ArrayList<>();
                
                while(rs.next()){
                    Orcamento orcamentoAtual = new Orcamento();
                    orcamentoAtual.setNome(rs.getString("NOME"));
                    orcamentoAtual.setJan(rs.getLong("JANEIRO"));
                    orcamentoAtual.setFev(rs.getLong("FEVEREIRO"));
                    orcamentoAtual.setMar(rs.getLong("MARCO"));
                    orcamentoAtual.setAbr(rs.getLong("ABRIL"));
                    orcamentoAtual.setMai(rs.getLong("MAIO"));
                    orcamentoAtual.setJun(rs.getLong("JUNHO"));
                    orcamentoAtual.setJul(rs.getLong("JULHO"));
                    orcamentoAtual.setAgo(rs.getLong("AGOSTO"));
                    orcamentoAtual.setSet(rs.getLong("SETEMBRO"));
                    orcamentoAtual.setOut(rs.getLong("OUTUBRO"));
                    orcamentoAtual.setNov(rs.getLong("NOVEMBRO"));
                    orcamentoAtual.setDez(rs.getLong("DEZEMBRO"));                   
                    orcamentos.add(orcamentoAtual);
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
        
        return orcamentos;
    }
    
}
