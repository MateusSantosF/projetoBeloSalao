/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.dao;

import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class AgendamentoDAO {

    public void cadastraAgendamento(Agendamento agendamento) throws SQLException, SQLException {

        String insertAgendamento = "INSERT INTO AGENDAMENTO (DATA, HORARIO, CPF_CLIENTE, REALIZADO) VALUES (?, ?, ?, ?)";

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
            pStatement.setString(3, agendamento.getCpfCliente());
            pStatement.setBoolean(4, false ); // ao cadastrar o orçamento, ele ainda não eh realizado por isso false.

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
    
    public ArrayList<Agendamento> listarAgendamentos(){
        
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, CPF_CLIENTE FROM AGENDAMENTO";
        
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
                   
                    ag.setId(rs.getLong("ID_AGENDAMENTO"));
                    ag.setData(rs.getDate("DATA").toLocalDate());
                    ag.setHorario(rs.getTime("HORARIO").toLocalTime());                    
                    ag.setCpfCliente(rs.getString("CPF_CLIENTE"));
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
    
    public ArrayList<Agendamento> listarAgendamentosHoje(){
        
        ManipulaData datas = new ManipulaData();
        
        
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, CPF_CLIENTE FROM AGENDAMENTO"
                + " WHERE DATA BETWEEN " + datas.meiaNoiteHoje() + " AND " + datas.MeiaNoiteAmanha();
        
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
                   
                    ag.setId(rs.getLong("ID_AGENDAMENTO"));
                    ag.setData(rs.getDate("DATA").toLocalDate());
                    ag.setHorario(rs.getTime("HORARIO").toLocalTime());                    
                    ag.setCpfCliente(rs.getString("CPF_CLIENTE"));
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
    
    public ArrayList<Agendamento> listarAgendamentosAmanha(){
        
        ManipulaData datas = new ManipulaData();
        
        
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, CPF_CLIENTE FROM AGENDAMENTO"
                + " WHERE DATA BETWEEN " + datas.MeiaNoiteAmanha() + " AND " + datas.somaDia(LocalDateTime.now().plusDays(1), 1);
        
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
                   
                    ag.setId(rs.getLong("ID_AGENDAMENTO"));
                    ag.setData(rs.getDate("DATA").toLocalDate());
                    ag.setHorario(rs.getTime("HORARIO").toLocalTime());                    
                    ag.setCpfCliente(rs.getString("CPF_CLIENTE"));
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
    
    public ArrayList<Agendamento> listarAgendamentosSemana(){
        
        ManipulaData datas = new ManipulaData();
        
        
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, CPF_CLIENTE FROM AGENDAMENTO"
                + " WHERE DATA BETWEEN " + datas.meiaNoiteHoje() + " AND " + datas.somaDia(LocalDateTime.now(), 5);
        
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
                   
                    ag.setId(rs.getLong("ID_AGENDAMENTO"));
                    ag.setData(rs.getDate("DATA").toLocalDate());
                    ag.setHorario(rs.getTime("HORARIO").toLocalTime());                    
                    ag.setCpfCliente(rs.getString("CPF_CLIENTE"));
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
        
        
        
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, CPF_CLIENTE, CLIENTE.NOME FROM AGENDAMENTO"
        + " INNER JOIN CLIENTE ON AGENDAMENTO.CPF_CLIENTE = CLIENTE.CPF AND CLIENTE.NOME LIKE'%" + nome +"%'";
        
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
                   
                    ag.setId(rs.getLong("ID_AGENDAMENTO"));
                    ag.setData(rs.getDate("DATA").toLocalDate());
                    ag.setHorario(rs.getTime("HORARIO").toLocalTime());                    
                    ag.setCpfCliente(rs.getString("CPF_CLIENTE"));
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


}
