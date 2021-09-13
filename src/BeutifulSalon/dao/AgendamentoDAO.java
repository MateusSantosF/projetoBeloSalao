/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.dao;

import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.controller.ServicoController;
import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class AgendamentoDAO {

    public void cadastraAgendamento(Agendamento agendamento) throws SQLException, SQLException {

        String insertAgendamento = "INSERT INTO AGENDAMENTO (DATA, HORARIO, ID_CLIENTE, REALIZADO, DESCONTO, TOTAL) VALUES (?, ?, ?, ?, ?,?)";

        String insertServicoAgendamento = "INSERT INTO AGENDAMENTO_SERVICO (ID_AGENDAMENTO, ID_SERVICO) "
                + "VALUES ((SELECT ID_AGENDAMENTO FROM AGENDAMENTO ORDER BY ID_AGENDAMENTO DESC LIMIT 1), ?)";

        Connection connection = null;
        PreparedStatement pStatement = null;

        try {

            connection = new ConnectionMVC().getConnection();
            connection.setAutoCommit(false);

            pStatement = connection.prepareStatement(insertAgendamento);
            pStatement.setDate(1, java.sql.Date.valueOf(agendamento.getData()));
            pStatement.setTime(2, java.sql.Time.valueOf(agendamento.getHorario()));
            pStatement.setLong(3, agendamento.getIdCliente());
            pStatement.setBoolean(4, agendamento.getRealizado() ); 
            pStatement.setLong(5, agendamento.getDesconto());
            pStatement.setLong(6, agendamento.getTotal());
            int firstInsert = pStatement.executeUpdate();

            if (firstInsert > 0) {
                try {

                    ArrayList<Servico> servicos = agendamento.getServicos();

                    for (Servico s : servicos) {
                        pStatement = connection.prepareStatement(insertServicoAgendamento);
                        pStatement.setLong(1, s.getId());
                        pStatement.executeUpdate();
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro registrar serviço" + e);
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
    public Agendamento listarAgendamento(long idAgendamento){
        
 
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO,TOTAL, DESCONTO, ID_CLIENTE FROM AGENDAMENTO WHERE ID_AGENDAMENTO = ? ";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        Agendamento agendamento = null;

        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, idAgendamento);
            
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                
                while(rs.next()){
                    agendamento = new Agendamento();
                    agendamento.setIdAgendamento(rs.getLong("ID_AGENDAMENTO"));
                    agendamento.setData(rs.getDate("DATA").toLocalDate());
                    agendamento.setHorario(rs.getTime("HORARIO").toLocalTime());                    
                    agendamento.setIdCliente(rs.getLong("ID_CLIENTE"));
                    agendamento.setRealizado(rs.getBoolean("REALIZADO"));
                    agendamento.setTotal(rs.getLong("TOTAL"));
                    agendamento.setDesconto(rs.getLong("DESCONTO"));
                }
            }
           
            return agendamento;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro agendamentoDAO" + e);
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
        
        
        return agendamento;
    }
    
    public ArrayList<Agendamento> listarAgendamentos(){
        
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, ID_CLIENTE FROM AGENDAMENTO WHERE REALIZADO = TRUE ORDER BY DATA ";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Agendamento> agendamentos = null;

        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                
                agendamentos = new ArrayList<>();
                while(rs.next()){
                    Agendamento ag = new Agendamento();
                   
                    ag.setIdAgendamento(rs.getLong("ID_AGENDAMENTO"));
                    ag.setData(rs.getDate("DATA").toLocalDate());
                    ag.setHorario(rs.getTime("HORARIO").toLocalTime());                    
                    ag.setIdCliente(rs.getLong("ID_CLIENTE"));
                    ag.setRealizado(rs.getBoolean("REALIZADO"));
                    agendamentos.add(ag);         
                }
            }
           
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro agendamentoDAO" + e);
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
        
        
        return agendamentos;
    }
    
    public ArrayList<Servico> listaServicosAgendamento(long idAgendamento) throws ExceptionDAO{
        
        String sql = "SELECT * FROM AGENDAMENTO_SERVICO WHERE ID_AGENDAMENTO = ?";
        
        ServicoController sc = new ServicoController();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Servico> servicos = null;
        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, idAgendamento);
            
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                servicos = new ArrayList<>();
                while(rs.next()){
                    Servico sv = new Servico();
                    sv = sc.buscarServico(rs.getLong("ID_SERVICO"));
                    servicos.add(sv);              
                }
            }
            
            return servicos;
            
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, e);
        }
          return servicos;  
    }
    
    public ArrayList<Agendamento> listarAgendamentosHoje() throws ExceptionDAO{
        
        ManipulaData datas = new ManipulaData();
        
        
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, ID_CLIENTE FROM AGENDAMENTO"
                + " WHERE DATA BETWEEN " + datas.meiaNoiteHoje() + " AND " + datas.MeiaNoiteAmanha() + " AND REALIZADO = TRUE ORDER BY HORARIO ";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Agendamento> agendamentos = null;

        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                
                agendamentos = new ArrayList<>();
                while(rs.next()){
                    Agendamento ag = new Agendamento();
                   
                    ag.setIdAgendamento(rs.getLong("ID_AGENDAMENTO"));
                    ag.setData(rs.getDate("DATA").toLocalDate());
                    ag.setHorario(rs.getTime("HORARIO").toLocalTime());                    
                    ag.setIdCliente(rs.getLong("ID_CLIENTE"));
                    ag.setRealizado(rs.getBoolean("REALIZADO"));
                    agendamentos.add(ag);         
                }
            }
           
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro agendamentoDAO" + e);
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
        
        
        return agendamentos;
    }
    
    public ArrayList<Agendamento> listarAgendamentosAmanha()  throws ExceptionDAO{
        
        ManipulaData datas = new ManipulaData();
        
        
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, ID_CLIENTE FROM AGENDAMENTO"
                + " WHERE DATA BETWEEN " + datas.MeiaNoiteAmanha() + " AND " + datas.somaDia(LocalDateTime.now().plusDays(1), 1)
                + " AND REALIZADO = TRUE ORDER BY HORARIO";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Agendamento> agendamentos = null;

        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                
                agendamentos = new ArrayList<>();
                while(rs.next()){
                    Agendamento ag = new Agendamento();
                   
                    ag.setIdAgendamento(rs.getLong("ID_AGENDAMENTO"));
                    ag.setData(rs.getDate("DATA").toLocalDate());
                    ag.setHorario(rs.getTime("HORARIO").toLocalTime());                    
                    ag.setIdCliente(rs.getLong("ID_CLIENTE"));
                    ag.setRealizado(rs.getBoolean("REALIZADO"));
                    agendamentos.add(ag);         
                }
            }
           
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro agendamentoDAO" + e);
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
        
        
        return agendamentos;
    }
    
    public ArrayList<Agendamento> listarAgendamentosSemana() throws ExceptionDAO{
        
        ManipulaData datas = new ManipulaData();
        
        
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, ID_CLIENTE FROM AGENDAMENTO"
                + " WHERE DATA BETWEEN " + datas.meiaNoiteHoje() + " AND " + datas.somaDia(LocalDateTime.now(), 5) 
                +" AND REALIZADO = TRUE ORDER BY DATA";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Agendamento> agendamentos = null;

        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                
                agendamentos = new ArrayList<>();
                while(rs.next()){
                    Agendamento ag = new Agendamento();
                   
                    ag.setIdAgendamento(rs.getLong("ID_AGENDAMENTO"));
                    ag.setData(rs.getDate("DATA").toLocalDate());
                    ag.setHorario(rs.getTime("HORARIO").toLocalTime());                    
                    ag.setIdCliente(rs.getLong("ID_CLIENTE"));
                    ag.setRealizado(rs.getBoolean("REALIZADO"));
                    agendamentos.add(ag);         
                }
            }
           
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro agendamentoDAO" + e);
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
        
        
        return agendamentos;
    }
    
    
    
    
     public ArrayList<Agendamento> listarAgendamentosNome(String nome){
             
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, ID_CLIENTE, CLIENTE.NOME FROM AGENDAMENTO"
        + " INNER JOIN CLIENTE ON AGENDAMENTO.ID_CLIENTE = CLIENTE.ID AND CLIENTE.NOME LIKE'%" + nome +"%'";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Agendamento> agendamentos = null;

        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                
                agendamentos = new ArrayList<>();
                while(rs.next()){
                    Agendamento ag = new Agendamento();
                   
                    ag.setIdAgendamento(rs.getLong("ID_AGENDAMENTO"));
                    ag.setData(rs.getDate("DATA").toLocalDate());
                    ag.setHorario(rs.getTime("HORARIO").toLocalTime());                    
                    ag.setIdCliente(rs.getLong("ID_CLIENTE"));
                    ag.setRealizado(rs.getBoolean("REALIZADO"));
                    agendamentos.add(ag);         
                }
            }
           
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro agendamentoDAO" + e);
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
        
        
        return agendamentos;
    }

    public ArrayList<Agendamento> listarAgendamentos(LocalDate data) {
        
        ManipulaData datas = new ManipulaData();
      
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, ID_CLIENTE FROM AGENDAMENTO"
                + " WHERE DATA BETWEEN " + datas.meiaNoite(data) + " AND " + datas.meiaNoiteAmanha(data) +" ORDER BY HORARIO";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Agendamento> agendamentos = null;

        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                
                agendamentos = new ArrayList<>();
                while(rs.next()){
                    Agendamento ag = new Agendamento();
                    ServicoController sc = new ServicoController();
                     
                    ag.setServicos(sc.buscarServicoPeloAgendamento(rs.getLong("ID_AGENDAMENTO")));
                    ag.setIdAgendamento(rs.getLong("ID_AGENDAMENTO"));
                    ag.setData(rs.getDate("DATA").toLocalDate());
                    ag.setHorario(rs.getTime("HORARIO").toLocalTime());                    
                    ag.setIdCliente(rs.getLong("ID_CLIENTE"));
                    ag.setRealizado(rs.getBoolean("REALIZADO"));
                  
       
         
                    agendamentos.add(ag);         
                }
            }
           
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro agendamentoDAO" + e);
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
        
        
        return agendamentos;
    }
    
    public ArrayList<Agendamento> listarAgendamentosRealizados(LocalDate data) {
        
        ManipulaData datas = new ManipulaData();
      
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, ID_CLIENTE FROM AGENDAMENTO"
                + " WHERE REALIZADO = TRUE AND DATA BETWEEN " + datas.meiaNoite(data) + " AND " + datas.meiaNoiteAmanha(data) +" ORDER BY HORARIO";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Agendamento> agendamentos = null;

        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                
                agendamentos = new ArrayList<>();
                while(rs.next()){
                    Agendamento ag = new Agendamento();
                    ServicoController sc = new ServicoController();
                                    
                    ag.setIdAgendamento(rs.getLong("ID_AGENDAMENTO"));
                    ag.setData(rs.getDate("DATA").toLocalDate());
                    ag.setHorario(rs.getTime("HORARIO").toLocalTime());                    
                    ag.setServicos(sc.buscarServicoPeloAgendamento(rs.getLong("ID_AGENDAMENTO")));
                    ag.setRealizado(rs.getBoolean("REALIZADO"));
                    

                       ag.setServicos(sc.buscarServicoPeloAgendamento(rs.getLong("ID_AGENDAMENTO")));
              
         
                    agendamentos.add(ag);         
                }
            }
           
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro agendamentoDAO" + e);
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
        
        
        return agendamentos;
    }

    public void atualizarAgendamento(Agendamento agendamento) throws SQLException {
        
        String insertAgendamento = "UPDATE AGENDAMENTO SET DATA = ?, HORARIO = ? , ID_CLIENTE = ? ,"
                 + " REALIZADO = ? , DESCONTO = ? , TOTAL = ?  WHERE ID_AGENDAMENTO = ?";
         
        String deletaServicoAgendamentoAntigo = "DELETE FROM AGENDAMENTO_SERVICO WHERE ID_AGENDAMENTO = ?";
        String insertServicoAgendamento = "INSERT INTO AGENDAMENTO_SERVICO (ID_AGENDAMENTO, ID_SERVICO) "
                + "VALUES (?, ?)";

 
        Connection connection = null;
        PreparedStatement pStatement = null;

        try {

            connection = new ConnectionMVC().getConnection();
            connection.setAutoCommit(false);

            pStatement = connection.prepareStatement(insertAgendamento);
            pStatement.setLong(7, agendamento.getIdAgendamento());
            pStatement.setDate(1, java.sql.Date.valueOf(agendamento.getData()));
            pStatement.setTime(2, java.sql.Time.valueOf(agendamento.getHorario()));
            pStatement.setLong(3, agendamento.getIdCliente());
            pStatement.setBoolean(4, agendamento.getRealizado() ); 
            pStatement.setLong(5, agendamento.getDesconto());
            pStatement.setLong(6, agendamento.getTotal());
            int firstInsert = pStatement.executeUpdate();

            if (firstInsert > 0) {
                
                pStatement = connection.prepareStatement(deletaServicoAgendamentoAntigo);
                pStatement.setLong(1, agendamento.getIdAgendamento());
                pStatement.executeUpdate();
                
                try {

                    ArrayList<Servico> servicos = agendamento.getServicos();

                    for (Servico s : servicos) {
                        pStatement = connection.prepareStatement(insertServicoAgendamento);
                        pStatement.setLong(1, agendamento.getIdAgendamento());
                        pStatement.setLong(2, s.getId());
                        pStatement.executeUpdate();
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro registrar serviço" + e);
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
    
    public ArrayList<Agendamento> listarAgendamentosNaoRealizados(){
        
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, ID_CLIENTE FROM AGENDAMENTO WHERE REALIZADO = FALSE ORDER BY DATA ";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Agendamento> agendamentos = null;

        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                
                agendamentos = new ArrayList<>();
                while(rs.next()){
                    Agendamento ag = new Agendamento();
                   
                    ag.setIdAgendamento(rs.getLong("ID_AGENDAMENTO"));
                    ag.setData(rs.getDate("DATA").toLocalDate());
                    ag.setHorario(rs.getTime("HORARIO").toLocalTime());                    
                    ag.setIdCliente(rs.getLong("ID_CLIENTE"));
                    ag.setRealizado(rs.getBoolean("REALIZADO"));
                    agendamentos.add(ag);         
                }
            }
           
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro agendamentoDAO" + e);
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
        
        
        return agendamentos;
    }
    
     public long retornaSomaDeAgendamentosMensal(){
        
         String sql = "SELECT SUM(AGENDAMENTO.TOTAL) AS RENDAMENSAL FROM AGENDAMENTO WHERE AGENDAMENTO.DATA BETWEEN ? AND ?";
        long agendamentos = 0;
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
    
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
        
            pStatement.setLong(1, new ManipulaData().inicioDoMes(LocalDate.now(), LocalDate.now().getMonth()));
            pStatement.setLong(2, new ManipulaData().fimDoMes(LocalDate.now(), LocalDate.now().getMonth()));

            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    agendamentos = rs.getInt("RENDAMENSAL");
                }
            }
            
            return agendamentos;
       

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
        
        return agendamentos;
     }
}
