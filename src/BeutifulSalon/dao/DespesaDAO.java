/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.dao;

import BeutifulSalon.model.Despesa;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Mateus
 */
public class DespesaDAO {

    public void cadastrarDespesa(Despesa despesa) {
        
        String sql = "INSERT INTO DESPESAMENSAL (VALORPAGO, ANO, DATALANCAMENTO, DATAVENCIMENTO, DATAPAGAMENTO, STATUS, ANOTACAO, ID_ORCAMENTO, FORMAPAGAMENTO)"
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        String sql2 = "INSERT INTO DESPESAMENSAL (ANO ,DATALANCAMENTO, DATAVENCIMENTO, STATUS, ID_ORCAMENTO) VALUES (?,?,?,?,?)";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
    
        try {
            connection = new ConnectionMVC().getConnection();

            if (despesa.isStatus()) {
                pStatement = connection.prepareStatement(sql);

                pStatement.setLong(1, despesa.getValorPago());
                pStatement.setString(2, despesa.getAno());
                pStatement.setDate(3, Date.valueOf(despesa.getLancameto()));
                pStatement.setDate(4, Date.valueOf(despesa.getVencimento()));
                pStatement.setDate(5, Date.valueOf(despesa.getPagamento()));
                pStatement.setBoolean(6, despesa.isStatus());
                pStatement.setString(7, despesa.getAnotacao());
                pStatement.setLong(8, despesa.getIdOrcamento());
                pStatement.setString(9, despesa.getFormaPagamento());

                pStatement.execute();
            }else{
                pStatement = connection.prepareStatement(sql2);

                pStatement.setString(1, despesa.getAno());
                pStatement.setDate(2, Date.valueOf(despesa.getLancameto()));
                pStatement.setDate(3, Date.valueOf(despesa.getVencimento()));
                pStatement.setBoolean(4, despesa.isStatus());
                pStatement.setLong(5, despesa.getIdOrcamento());

                pStatement.execute();
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Erro DespesaDAO " + e);
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
    
    public ArrayList<Despesa> listarDespesasAnoAtual(){
        
        String sql = "SELECT ID_DESPESA, VALORPAGO, FORMAPAGAMENTO, ANO, DATALANCAMENTO, DATAVENCIMENTO, DATAPAGAMENTO, STATUS, ANOTACAO, ID_ORCAMENTO FROM DESPESAMENSAL WHERE ANO = ? ORDER BY DATALANCAMENTO";
        
        ArrayList<Despesa> despesas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        try{
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            
            pStatement.setString(1, String.valueOf(LocalDate.now().getYear()));
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    Despesa despesaAtual = new Despesa();
                    
                    despesaAtual.setIdDespesa(rs.getLong("ID_DESPESA"));
                    despesaAtual.setLancameto(rs.getDate("DATALANCAMENTO").toLocalDate());
                    despesaAtual.setVencimento(rs.getDate("DATAVENCIMENTO").toLocalDate());
                    despesaAtual.setAno(rs.getString("ANO"));
                    despesaAtual.setStatus(rs.getBoolean("STATUS"));
                    despesaAtual.setIdOrcamento(rs.getLong("ID_ORCAMENTO"));
                    despesaAtual.setValorPago(rs.getLong("VALORPAGO"));
                    despesaAtual.setFormaPagamento(rs.getString("FORMAPAGAMENTO")); 
                    
                    if(rs.getDate("DATAPAGAMENTO") == null){
                        despesaAtual.setPagamento(null);
                    }else{
                        despesaAtual.setPagamento(rs.getDate("DATAPAGAMENTO").toLocalDate());     
                    }
                   
                    despesaAtual.setAnotacao(rs.getString("ANOTACAO"));
                   
                    despesas.add(despesaAtual);
                }
            }
            
            return despesas;
            
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Erro DespesaDAO " + e);
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
        return despesas;
    }

    public long verificaExistencia(long idOrcamento, long inicioMes, long fimMes, String ano, boolean pagamento) {
        
        String sql = "";
        
        if(pagamento){
            sql = "SELECT COUNT(*) AS QTD FROM DESPESAMENSAL WHERE ID_ORCAMENTO = ? AND ANO = ? AND DATAPAGAMENTO BETWEEN " + inicioMes + " AND " + fimMes;
        }else{
           sql = "SELECT COUNT(*) AS QTD FROM DESPESAMENSAL WHERE ID_ORCAMENTO = ? AND ANO = ? AND DATALANCAMENTO BETWEEN " + inicioMes + " AND " + fimMes;
        }
        
         
        Connection connection = null;
        PreparedStatement pStatement = null;
        long qtd = 0;
        
        try {
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sql);
            
            pStatement.setLong(1, idOrcamento);
            pStatement.setString(2,ano );
            
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    qtd += rs.getLong("QTD");
                }
            }
            
            return qtd;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        return qtd;

    }
    
}
