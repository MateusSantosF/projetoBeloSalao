 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.dao;


import BeutifulSalon.controller.ProdutoController;
import BeutifulSalon.model.Produto;
import BeutifulSalon.model.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class ServicoDAO {

    public List<Servico> listarServicos() throws ExceptionDAO {

        String sql = "SELECT ID_SERVICO, NOME, PRECO, TEMPOGASTO, (SELECT COUNT(AGENDAMENTO_SERVICO.ID_SERVICO) FROM AGENDAMENTO_SERVICO WHERE AGENDAMENTO_SERVICO.ID_SERVICO = SERVICO.ID_SERVICO) AS QTD FROM SERVICO "
                + " WHERE SERVICO.EXCLUIDO = FALSE ORDER BY NOME";
        String sql2 = "SELECT * FROM PRODUTO_SERVICO WHERE ID_SERVICO = ?";

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
                    servicoAtual.setTempoGasto(rs.getTime("TEMPOGASTO").toLocalTime());
                    servicoAtual.setId(rs.getLong("ID_SERVICO"));
                    servicoAtual.setQuantidadeRealizada(rs.getLong("QTD"));
                    
                    pStatement = connection.prepareStatement(sql2);
        
                    pStatement.setLong(1, servicoAtual.getId());
                    ResultSet rs2 = pStatement.executeQuery();
                    
                    if(rs2 != null){
                        ArrayList<Produto> produtos = new ArrayList<>();                
                        while(rs2.next()){
                            Produto p = new ProdutoController().buscarProduto(rs2.getLong("ID_PRODUTO"));
                            p.setRendimento(rs2.getInt("RENDIMENTO"));
                            produtos.add(p);                          
                        }
                        servicoAtual.setProdutos(produtos);
                    }
                servicos.add(servicoAtual);
                }

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
        
        return servicos;

    }
    
     public int somaQtdServicosRegistrados() throws ExceptionDAO {


        String sql = "SELECT COUNT(*) AS QTD FROM SERVICO WHERE EXCLUIDO = FALSE";

        Connection connection = null;
        PreparedStatement pStatement = null;
        int servicos = 0;

        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);

            ResultSet rs = pStatement.executeQuery();

            if (rs != null) {
                
                servicos = rs.getInt("QTD");
            }

            return servicos;
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
        
        return servicos;

    }
    
    public List<Servico> listarServicosIndependenteDeExclusao() throws ExceptionDAO {

        String sql = "SELECT ID_SERVICO, NOME, PRECO, TEMPOGASTO, (SELECT COUNT(AGENDAMENTO_SERVICO.ID_SERVICO) FROM AGENDAMENTO_SERVICO"
                + " WHERE AGENDAMENTO_SERVICO.ID_SERVICO = SERVICO.ID_SERVICO) AS QTD FROM SERVICO "
                + ""
                + " ORDER BY NOME DESC";
        String sql2 = "SELECT * FROM PRODUTO_SERVICO WHERE ID_SERVICO = ?";

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
                    servicoAtual.setTempoGasto(rs.getTime("TEMPOGASTO").toLocalTime());
                    servicoAtual.setId(rs.getLong("ID_SERVICO"));
                    servicoAtual.setQuantidadeRealizada(rs.getLong("QTD"));
                    
                    pStatement = connection.prepareStatement(sql2);
        
                    pStatement.setLong(1, servicoAtual.getId());
                    ResultSet rs2 = pStatement.executeQuery();
                    
                    if(rs2 != null){
                        ArrayList<Produto> produtos = new ArrayList<>();                
                        while(rs2.next()){
                            Produto p = new ProdutoController().buscarProduto(rs2.getLong("ID_PRODUTO"));
                            p.setRendimento(rs2.getInt("RENDIMENTO"));
                            produtos.add(p);                          
                        }
                        servicoAtual.setProdutos(produtos);
                    }
                servicos.add(servicoAtual);
                }

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
        
        return servicos;

    }

    public ArrayList<Servico> listarServicos(String nome) {

 
        String sql2 = "SELECT ID_SERVICO, NOME, PRECO, TEMPOGASTO, "
                + "(SELECT COUNT(AGENDAMENTO_SERVICO.ID_SERVICO) FROM AGENDAMENTO_SERVICO WHERE "
                + "AGENDAMENTO_SERVICO.ID_SERVICO = SERVICO.ID_SERVICO) AS QTD FROM SERVICO WHERE SERVICO.NOME LIKE '%" + nome + "%' "
                + " AND SERVICO.EXCLUIDO = FALSE ORDER BY NOME ASC";
        
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Servico> servicos = null;

        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql2);

            ResultSet rs = pStatement.executeQuery();

            if (rs != null) {
                servicos = new ArrayList<>();
       
                while (rs.next()) {
                    Servico servicoAtual = new Servico();
                    servicoAtual.setNome(rs.getString("NOME"));
                    servicoAtual.setPreco(rs.getLong("PRECO"));
                    servicoAtual.setTempoGasto(rs.getTime("TEMPOGASTO").toLocalTime());
                    servicoAtual.setQuantidadeRealizada(rs.getLong("QTD"));
                    servicoAtual.setId(rs.getLong("ID_SERVICO"));
                    servicos.add(servicoAtual);
                }

            }
            return servicos;

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
    
    public List<Servico> listaOsCincoServicosMaisRealizados(){
         
        String sql ="SELECT COUNT(AGENDAMENTO_SERVICO.ID_SERVICO) AS QTD, AGENDAMENTO_SERVICO.ID_SERVICO AS ID_SERVICO, SERVICO.NOME FROM AGENDAMENTO_SERVICO " +
                    "INNER JOIN SERVICO ON SERVICO.ID_SERVICO = AGENDAMENTO_SERVICO.ID_SERVICO " +
                    "INNER JOIN AGENDAMENTO ON AGENDAMENTO.DATA BETWEEN ? AND ? AND AGENDAMENTO.ID_AGENDAMENTO = AGENDAMENTO_SERVICO.ID_AGENDAMENTO " +
                    " AND SERVICO.EXCLUIDO = FALSE GROUP BY AGENDAMENTO_SERVICO.ID_SERVICO ORDER BY COUNT(AGENDAMENTO_SERVICO.ID_SERVICO) DESC LIMIT 5";
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        List<Servico> servicos = new ArrayList<>();
       
        try{
            
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql); 
            
            try{
                LocalDate anoAtual = LocalDate.now();
                long inicioDoAno = LocalDate.ofYearDay(anoAtual.getYear(), 1).toEpochDay() * 24 * 60 * 60 * 1000;
                long fimDoAno = LocalDate.ofYearDay(anoAtual.getYear(), 1).plusYears(1).toEpochDay() * 24 * 60 * 60 * 1000; 
                pStatement.setLong(1, inicioDoAno);
                pStatement.setLong(2, fimDoAno);
            }catch(DateTimeException e){
                sql = "SELECT COUNT(AGENDAMENTO_SERVICO.ID_SERVICO), AGENDAMENTO_SERVICO.ID_SERVICO AS ID_SERVICO, SERVICO.NOME FROM AGENDAMENTO_SERVICO " +
                "INNER JOIN SERVICO ON SERVICO.ID_SERVICO = AGENDAMENTO_SERVICO.ID_SERVICO " +
                "GROUP BY AGENDAMENTO_SERVICO.ID_SERVICO ORDER BY COUNT(AGENDAMENTO_SERVICO.ID_SERVICO) DESC LIMIT 5";
                throw new DateTimeException("erro");
            }
       
            rs = pStatement.executeQuery();
            
          
   
            if(rs != null){                
                while(rs.next()){
                
                Servico servicoBuscado = new Servico();
                servicoBuscado.setNome(rs.getString("NOME"));
                servicoBuscado.setQuantidadeRealizada(rs.getLong("QTD"));
                servicoBuscado.setId(rs.getLong("ID_SERVICO"));
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
    
    public List<Servico> listarServicosRealizadosAno(){
         
        String sql ="SELECT COUNT(AGENDAMENTO_SERVICO.ID_SERVICO) AS QTD, AGENDAMENTO_SERVICO.ID_SERVICO AS ID, SERVICO.NOME, SERVICO.PRECO, SERVICO.TEMPOGASTO FROM AGENDAMENTO_SERVICO " +
                    "INNER JOIN SERVICO ON SERVICO.ID_SERVICO = AGENDAMENTO_SERVICO.ID_SERVICO " +
                    "INNER JOIN AGENDAMENTO ON AGENDAMENTO.DATA BETWEEN ? AND ? AND AGENDAMENTO.ID_AGENDAMENTO = AGENDAMENTO_SERVICO.ID_AGENDAMENTO " +
                    " GROUP BY AGENDAMENTO_SERVICO.ID_SERVICO ORDER BY COUNT(AGENDAMENTO_SERVICO.ID_SERVICO)";
        
        String sql2 = "SELECT * FROM PRODUTO_SERVICO WHERE ID_SERVICO = ?";

        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        List<Servico> servicos = new ArrayList<>();
       
        try{
            
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql); 
            
            try{
                LocalDate anoAtual = LocalDate.now();
                long inicioDoAno = LocalDate.ofYearDay(anoAtual.getYear(), 1).toEpochDay() * 24 * 60 * 60 * 1000;
                long fimDoAno = LocalDate.ofYearDay(anoAtual.getYear(), 1).plusYears(1).toEpochDay() * 24 * 60 * 60 * 1000; 
                pStatement.setLong(1, inicioDoAno);
                pStatement.setLong(2, fimDoAno);
            }catch(DateTimeException e){
                sql = "SELECT COUNT(AGENDAMENTO_SERVICO.ID_SERVICO), AGENDAMENTO_SERVICO.ID_SERVICO, SERVICO.NOME FROM AGENDAMENTO_SERVICO " +
                "INNER JOIN SERVICO ON SERVICO.ID_SERVICO = AGENDAMENTO_SERVICO.ID_SERVICO " +
                "GROUP BY AGENDAMENTO_SERVICO.ID_SERVICO ORDER BY COUNT(AGENDAMENTO_SERVICO.ID_SERVICO) DESC LIMIT 5";
                throw new DateTimeException("erro");
            }
       
            rs = pStatement.executeQuery();
     
   
            if(rs != null){                
                while(rs.next()){
                
                Servico servicoBuscado = new Servico();
                servicoBuscado.setNome(rs.getString("NOME"));
                servicoBuscado.setPreco(rs.getLong("PRECO"));
                servicoBuscado.setTempoGasto(rs.getTime("TEMPOGASTO").toLocalTime());
                servicoBuscado.setQuantidadeRealizada(rs.getLong("QTD"));
                servicoBuscado.setId(rs.getLong("ID"));
                
                 pStatement = connection.prepareStatement(sql2);
        
                    pStatement.setLong(1, servicoBuscado.getId());
                    ResultSet rs2 = pStatement.executeQuery();
                    
                    if(rs2 != null){
                        ArrayList<Produto> produtos = new ArrayList<>();
                    
                        while(rs2.next()){
                            Produto p = new ProdutoController().buscarProduto(rs2.getLong("ID_PRODUTO"));
                            p.setRendimento(rs2.getInt("RENDIMENTO"));
                            produtos.add(p);
                           
                        }
                        servicoBuscado.setProdutos(produtos);
                    }
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
    
    
    public Servico buscarServico(long id){
        
        String sql = "SELECT ID_SERVICO, NOME, PRECO, TEMPOGASTO FROM SERVICO WHERE ID_SERVICO = ?";
        String sql2 = "SELECT * FROM PRODUTO_SERVICO WHERE ID_SERVICO = ?";

        Connection connection = null;
        PreparedStatement pStatement = null;
        Servico servico = null;

        try {
            connection = new ConnectionMVC().getConnection();
   
           
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, id);

            ResultSet rs = pStatement.executeQuery();

            if (rs != null) {

                Servico servicoAtual = new Servico();
                servicoAtual.setNome(rs.getString("NOME"));
                servicoAtual.setPreco(rs.getLong("PRECO"));
                servicoAtual.setTempoGasto(rs.getTime("TEMPOGASTO").toLocalTime());
                servicoAtual.setId(rs.getLong("ID_SERVICO"));

                pStatement = connection.prepareStatement(sql2);

                pStatement.setLong(1, servicoAtual.getId());
                ResultSet rs2 = pStatement.executeQuery();

                if (rs2 != null) {
                    ArrayList<Produto> produtos = new ArrayList<>();

                    while (rs2.next()) {
                    
                        Produto p = new ProdutoController().buscarProduto(rs2.getLong("ID_PRODUTO"));
                        p.setRendimento(rs2.getInt("RENDIMENTO"));
                        produtos.add(p);

                    }
                    servicoAtual.setProdutos(produtos);
                }

                servico = servicoAtual;

            }
            
            
            return servico;

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
        
        return servico;
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
    
    public List<Servico> selecionaServicosDoAno(int anoReferente) throws ExceptionDAO{
       
        //ignora se o serviço está excluido ou não
        String sql = "SELECT AGENDAMENTO.DATA AS DATA, SERVICO.ID_SERVICO, SERVICO.NOME, SERVICO.PRECO FROM SERVICO " +
        "    INNER JOIN AGENDAMENTO_SERVICO ON SERVICO.ID_SERVICO = AGENDAMENTO_SERVICO.ID_SERVICO " +
        "    INNER JOIN AGENDAMENTO ON AGENDAMENTO.ID_AGENDAMENTO = AGENDAMENTO_SERVICO.ID_AGENDAMENTO " +
        "WHERE AGENDAMENTO.DATA BETWEEN ? AND ?";
        ArrayList<Servico> servicos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
      
        
        try{
            
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
          
            long inicioDoAno = LocalDate.ofYearDay(anoReferente, 1).toEpochDay() * 24 * 60 * 60 * 1000;
            long fimDoAno = LocalDate.ofYearDay(anoReferente, 1).plusYears(1).toEpochDay() * 24 * 60 * 60 * 1000; 
            
 
            pStatement.setLong(1, inicioDoAno);
            pStatement.setLong(2, fimDoAno);       
            rs = pStatement.executeQuery();
  
            if(rs != null){                
                while(rs.next()){  
              
                Servico servicoBuscado = new Servico();     
                servicoBuscado.setNome(rs.getString("NOME"));
                servicoBuscado.setPreco(rs.getLong("PRECO"));
                servicoBuscado.setId(rs.getLong("ID_SERVICO"));
                servicoBuscado.setDataRealizado(rs.getDate("DATA").toLocalDate());     
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

    public List<Servico> listarServicosDeAgendamentoPorCliente(long id) {
        
        String sql = "SELECT SERVICO.ID_SERVICO, SERVICO.NOME, AGENDAMENTO.DATA, AGENDAMENTO.HORARIO FROM AGENDAMENTO_SERVICO " +
        "INNER JOIN SERVICO ON SERVICO.ID_SERVICO = AGENDAMENTO_SERVICO.ID_SERVICO " +
        "INNER JOIN AGENDAMENTO ON AGENDAMENTO.ID_AGENDAMENTO = AGENDAMENTO_SERVICO.ID_AGENDAMENTO " +
        "WHERE ID_CLIENTE = ? ORDER BY AGENDAMENTO.DATA DESC";
        ArrayList<Servico> servicos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
      
        
        try{
            
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, id);         
            rs = pStatement.executeQuery();
   
            if(rs != null){                
                while(rs.next()){  
                    Servico s = new Servico();
                    s.setId(rs.getLong("ID_SERVICO"));
                    s.setNome(rs.getString("NOME"));
                    s.setDataRealizado(rs.getDate("DATA").toLocalDate());
                    s.setTempoGasto(rs.getTime("HORARIO").toLocalTime());
                    servicos.add(s);
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

    public void atualizarServico(Servico servico) throws ExceptionDAO {
        
        String deleteServico = "DELETE FROM PRODUTO_SERVICO WHERE ID_SERVICO = ?";
        String atualizaServico = "UPDATE SERVICO SET NOME = ?, PRECO = ?, TEMPOGASTO = ?  WHERE ID_SERVICO = ?";
        String cadastraProdutosServicos = "INSERT INTO PRODUTO_SERVICO (ID_PRODUTO, RENDIMENTO, ID_SERVICO) VALUES (? ,?, "+ servico.getId()+")";
        
        Connection connection = null;
        PreparedStatement pStatement = null;

        try {

            connection = new ConnectionMVC().getConnection();
            connection.setAutoCommit(false);
            
            pStatement = connection.prepareStatement(atualizaServico);         
            pStatement.setLong(4, servico.getId());
            pStatement.setString(1, servico.getNome());
            pStatement.setLong(2, servico.getPreco());
            pStatement.setTime(3, java.sql.Time.valueOf(servico.getTempoGasto()));
            pStatement.executeUpdate();
            
     
            pStatement = connection.prepareStatement(deleteServico);
            pStatement.setLong(1, servico.getId());

            int firstDelete = pStatement.executeUpdate();

            if (firstDelete >= 0) {
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
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro DAO" + e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex);
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

    public void excluirServico(long id) {
        
        String sql = "UPDATE SERVICO SET EXCLUIDO = TRUE WHERE ID_SERVICO = ?";
        ArrayList<Servico> servicos = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
      
        try{
            
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, id);         
            pStatement.executeUpdate();
      
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

    }
    
    
}


