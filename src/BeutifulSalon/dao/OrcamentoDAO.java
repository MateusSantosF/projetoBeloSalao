/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.dao;

import BeutifulSalon.model.Orcamento;
import BeutifulSalon.view.Edicao.EditarOrcamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class OrcamentoDAO {
    
    public void cadastrarOrcamento(Orcamento orcamento){
        
        String sqlScript = "INSERT INTO ORCAMENTO (NOME, JANEIRO, FEVEREIRO, MARCO, ABRIL,"+ 
               "MAIO, JUNHO, JULHO, AGOSTO, SETEMBRO, OUTUBRO, NOVEMBRO, DEZEMBRO, PREVISTO, ANO)"+
               "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
               
                    
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
            pStatement.setBoolean(14, orcamento.isPrevisto());// TRUE quando eh orçamento PREVISTO
            pStatement.setString(15, orcamento.getAno());
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
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão" + e);
            }
           
        }

    }
    
    
    public ArrayList<Orcamento> listarOrcamento(){
        String sql  = "SELECT ID_ORCAMENTO, NOME,JANEIRO,FEVEREIRO,MARCO,ABRIL,MAIO,JUNHO,JULHO,AGOSTO,SETEMBRO,OUTUBRO,"
                + "NOVEMBRO,DEZEMBRO, ANO FROM ORCAMENTO WHERE ANO = ?";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Orcamento> orcamentos =  null;
        
        try{
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);            
            pStatement.setString(1, Year.now().toString());//RETORNA o ANO atual do SISTEMA
            ResultSet rs = pStatement.executeQuery();
                    
            if(rs != null){
                orcamentos = new ArrayList<>();
                
                while(rs.next()){
                    Orcamento orcamentoAtual = new Orcamento();
                    orcamentoAtual.setId_orcamento(rs.getInt("ID_ORCAMENTO"));
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
                    orcamentoAtual.setAno(rs.getString("ANO"));
                    
          
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
    
     public ArrayList<Orcamento> listarOrcamentoPorNome(String nome){
        String sql  = "SELECT ID_ORCAMENTO, NOME,JANEIRO,FEVEREIRO,MARCO,ABRIL,MAIO,JUNHO,JULHO,AGOSTO,SETEMBRO,OUTUBRO,"
                + "NOVEMBRO,DEZEMBRO, ANO FROM ORCAMENTO WHERE NOME LIKE '%" + nome + "%'";
        
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
                    orcamentoAtual.setId_orcamento(rs.getInt("ID_ORCAMENTO"));
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
                    orcamentoAtual.setAno(rs.getString("ANO"));
                    
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
    
    public ArrayList<Orcamento> listarOrcamento(String ano){
        String sql  = "SELECT ID_ORCAMENTO, NOME,JANEIRO,FEVEREIRO,MARCO,ABRIL,MAIO,JUNHO,JULHO,AGOSTO,SETEMBRO,OUTUBRO,"
                + "NOVEMBRO,DEZEMBRO FROM ORCAMENTO WHERE ANO = ?";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Orcamento> orcamentos =  null;
        
        try{
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
                  
            pStatement.setString(1, ano);
            ResultSet rs = pStatement.executeQuery();
           
            
            if(rs != null){
                orcamentos = new ArrayList<>();
                
                while(rs.next()){
                    Orcamento orcamentoAtual = new Orcamento();
                    orcamentoAtual.setId_orcamento(rs.getInt("ID_ORCAMENTO"));
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
    
    public long somarOrcamento(String mes, String ano){
        String sql  = "SELECT SUM("+ mes +") FROM ORCAMENTO WHERE ANO = ?";     
        Connection connection = null;
        PreparedStatement pStatement = null;
        long somatoria = 0;
        
        try{
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, ano);
            ResultSet rs = pStatement.executeQuery();
            somatoria = rs.getLong(1);
            
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
        
        return somatoria;
    }

    public boolean excluirOrcamento(long id_orcamento) {
        
        String sql  = "DELETE FROM ORCAMENTO WHERE ID_ORCAMENTO = ?";
        String sql2 = "DELETE FROM DESPESAMENSAL WHERE ID_ORCAMENTO = ?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        try {
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, id_orcamento);
            int orcamento = pStatement.executeUpdate();
            pStatement = connection.prepareStatement(sql2);
            pStatement.setLong(1, id_orcamento);
            int despesas = pStatement.executeUpdate();
            
            return orcamento == 1;
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Erro ao excluir orçamento: " + e);
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
        return false;
    }
    
    public Orcamento buscarOrcamento(long id_orcamento){
        String sql = "SELECT ID_ORCAMENTO, ANO, NOME,JANEIRO,FEVEREIRO,MARCO,ABRIL,MAIO,JUNHO,JULHO,AGOSTO,SETEMBRO,OUTUBRO,"
                + "NOVEMBRO,DEZEMBRO FROM ORCAMENTO WHERE ID_ORCAMENTO = ?";
        
        Connection connection = null;
        PreparedStatement ps = null;
        
        try {
            connection = new ConnectionMVC().getConnection();
            ps = connection.prepareStatement(sql);
            
            ps.setLong(1, id_orcamento);
            
            ResultSet rs = ps.executeQuery();
            Orcamento orcamentoAtual = new Orcamento();
            if(rs != null){
                while(rs.next()){
                    orcamentoAtual.setId_orcamento(rs.getLong("ID_ORCAMENTO"));
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
                    orcamentoAtual.setAno(rs.getString("ANO"));
                }
            }
      
            return orcamentoAtual;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }finally{
            
            try {
                if(ps != null) ps.close();

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

    public void editarOrcamento(long id_orcamento) {
        
    String sqlScript = "SELECT ID_ORCAMENTO,NOME,JANEIRO,FEVEREIRO, MARCO"
            + ", ABRIL, MAIO, JUNHO , JULHO, AGOSTO , SETEMBRO, OUTUBRO , NOVEMBRO, DEZEMBRO, ANO"
            + " FROM ORCAMENTO WHERE ID_ORCAMENTO = ?";
        
        PreparedStatement pStatement = null;
        Connection connection = null;
        ResultSet rs = null;
        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setLong(1, id_orcamento);
            rs = pStatement.executeQuery();
            Orcamento orcamento = new Orcamento(); 
      
            if(rs != null){                
                while(rs.next()){
                    orcamento.setNome(rs.getString("NOME"));
                    orcamento.setJan(rs.getLong("JANEIRO"));
                    orcamento.setFev(rs.getLong("FEVEREIRO"));
                    orcamento.setMar(rs.getLong("MARCO"));
                    orcamento.setAbr(rs.getLong("ABRIL"));
                    orcamento.setMai(rs.getLong("MAIO"));
                    orcamento.setJun(rs.getLong("JUNHO"));
                    orcamento.setJul(rs.getLong("JULHO"));
                    orcamento.setAgo(rs.getLong("AGOSTO"));
                    orcamento.setSet(rs.getLong("SETEMBRO"));
                    orcamento.setOut(rs.getLong("OUTUBRO"));
                    orcamento.setNov(rs.getLong("NOVEMBRO"));
                    orcamento.setDez(rs.getLong("DEZEMBRO"));
                    orcamento.setAno(rs.getString("ANO"));
                    orcamento.setId_orcamento(rs.getLong("ID_ORCAMENTO"));
                    
                }
            }
           
            new EditarOrcamento(orcamento).setVisible(true); 
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro OrcamentoDAO " + e);
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
    
    public void atualizarOrcamento(Orcamento orcamento) {
        
        
        String sqlScript = "UPDATE ORCAMENTO SET NOME = ? ,"
                + "JANEIRO = ? , FEVEREIRO = ?, MARCO = ? , "
                + "ABRIL = ? , MAIO = ? , JUNHO = ? , JULHO = ? , "
                + "AGOSTO = ? , SETEMBRO = ?, OUTUBRO = ? , NOVEMBRO = ?, DEZEMBRO = ?, ANO = ? "
                + "WHERE ID_ORCAMENTO = ? ";
        
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setLong(15, orcamento.getId_orcamento());
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
            pStatement.setString(14, orcamento.getAno());
            
            pStatement.execute(); 
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro atualizar orçamento"  + e);
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

   
}

