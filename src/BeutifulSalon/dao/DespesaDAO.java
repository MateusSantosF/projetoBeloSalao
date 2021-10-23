/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.dao;

import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.model.Despesa;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

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
    
    public ArrayList<Despesa> listarDespesas(){
        
        String sql = "SELECT ID_DESPESA, VALORPAGO, FORMAPAGAMENTO, ANO, DATALANCAMENTO, DATAVENCIMENTO, DATAPAGAMENTO, STATUS, ANOTACAO, ID_ORCAMENTO FROM DESPESAMENSAL WHERE ANO = ? ORDER BY DATALANCAMENTO";
        
        ArrayList<Despesa> despesas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        try{
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            
            pStatement.setString(1, String.valueOf(LocalDate.now().getYear())); // busca ano atual
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
    
     public Despesa buscarDespesaPaga(LocalDate ano, Month mes, long idOrcamento){
        
        String sql = "SELECT ID_DESPESA, VALORPAGO, FORMAPAGAMENTO, ANO, DATALANCAMENTO, DATAVENCIMENTO, DATAPAGAMENTO,"
                + " STATUS, ANOTACAO, ID_ORCAMENTO FROM DESPESAMENSAL WHERE ANO = ? AND STATUS = TRUE AND DATALANCAMENTO BETWEEN ? AND ? AND ID_ORCAMENTO = ? ORDER BY DATALANCAMENTO";
        
        ManipulaData manipulaData = new ManipulaData();
        Despesa despesaAtual = new Despesa();
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        try{
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            
            pStatement.setString(1, String.valueOf(ano.getYear())); 
            pStatement.setLong(2, manipulaData.inicioDoMes(ano, mes));
            pStatement.setLong(3, manipulaData.fimDoMes(ano, mes));
            pStatement.setLong(4, idOrcamento);
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    
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
                }
            }
            
            return despesaAtual;
            
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
        return despesaAtual;
    }
    
    public Despesa listarDespesa(long idDespesa) throws ExceptionDAO{
        
        String sql = "SELECT ID_DESPESA, VALORPAGO, FORMAPAGAMENTO, ANO, DATALANCAMENTO, DATAVENCIMENTO, DATAPAGAMENTO, STATUS, ANOTACAO, ID_ORCAMENTO FROM DESPESAMENSAL WHERE ID_DESPESA = ?";
        
        Despesa despesaBuscada = null;
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        try{
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            
            pStatement.setLong(1, idDespesa); // busca ano atual
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    despesaBuscada = new Despesa();
                    despesaBuscada.setIdDespesa(rs.getLong("ID_DESPESA"));
                    despesaBuscada.setLancameto(rs.getDate("DATALANCAMENTO").toLocalDate());
                    despesaBuscada.setVencimento(rs.getDate("DATAVENCIMENTO").toLocalDate());
                    despesaBuscada.setAno(rs.getString("ANO"));
                    despesaBuscada.setStatus(rs.getBoolean("STATUS"));
                    despesaBuscada.setIdOrcamento(rs.getLong("ID_ORCAMENTO"));
                    despesaBuscada.setValorPago(rs.getLong("VALORPAGO"));
                    despesaBuscada.setFormaPagamento(rs.getString("FORMAPAGAMENTO")); 
                    
                    if(rs.getDate("DATAPAGAMENTO") == null){
                        despesaBuscada.setPagamento(null);
                    }else{
                        despesaBuscada.setPagamento(rs.getDate("DATAPAGAMENTO").toLocalDate());     
                    }
                   
                    despesaBuscada.setAnotacao(rs.getString("ANOTACAO"));
                   
       
                }
            }
            
            return despesaBuscada;
            
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
        return despesaBuscada;
    }
    
    public ArrayList<Despesa> listarDespesasVencimento(long inicioMes, long fimMes)throws ExceptionDAO{
        
        String sql = "SELECT ID_DESPESA, VALORPAGO, FORMAPAGAMENTO, ANO, DATALANCAMENTO, DATAVENCIMENTO, DATAPAGAMENTO,"
                + " STATUS, ANOTACAO, ID_ORCAMENTO FROM DESPESAMENSAL WHERE ANO = ? AND DATAVENCIMENTO BETWEEN ? AND ? ORDER BY DATALANCAMENTO";
        
        ArrayList<Despesa> despesas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        try{
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            
            pStatement.setString(1, String.valueOf(LocalDate.now().getYear())); // busca ano atual
            pStatement.setLong(2, inicioMes);
            pStatement.setLong(3, fimMes);
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
    public ArrayList<Despesa> listarDespesasLancamento(long inicioMes, long fimMes) throws ExceptionDAO{
        
        String sql = "SELECT ID_DESPESA, VALORPAGO, FORMAPAGAMENTO, ANO, DATALANCAMENTO, DATAVENCIMENTO, DATAPAGAMENTO,"
                + " STATUS, ANOTACAO, ID_ORCAMENTO FROM DESPESAMENSAL WHERE ANO = ? AND DATALANCAMENTO BETWEEN ? AND ? ORDER BY DATALANCAMENTO";
        
        ArrayList<Despesa> despesas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        try{
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            
            pStatement.setString(1, String.valueOf(LocalDate.now().getYear())); // busca ano atual
            pStatement.setLong(2, inicioMes);
            pStatement.setLong(3, fimMes);
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    Despesa despesaAtual = new Despesa();
                    
                    despesaAtual.setIdDespesa(rs.getLong("ID_DESPESA"));
                    despesaAtual.setLancameto(rs.getDate("DATALANCAMENTO").toLocalDate());
                    despesaAtual.setVencimento(rs.getDate("DATAVENCIMENTO").toLocalDate());
                    despesaAtual.setAno(rs.getString("ANO"));
                    if(despesaAtual.getAno().equals("2022")){
                        System.out.println("Q INFERNO");
                    }
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
    
    public List<Despesa> listarDespesas(String ano){
        
        String sql = "SELECT ID_DESPESA, VALORPAGO, FORMAPAGAMENTO, "
                + "ANO, DATALANCAMENTO, DATAVENCIMENTO, DATAPAGAMENTO,"
                + " STATUS, ANOTACAO, ID_ORCAMENTO FROM DESPESAMENSAL WHERE DESPESAMENSAL.ANO = ?";
        
        List<Despesa> despesas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        try{
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            
            pStatement.setString(1, ano); // busca ano atual
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
            sql = "SELECT COUNT(*) AS QTD FROM DESPESAMENSAL WHERE ID_ORCAMENTO = ? AND ANO = ? AND DATALANCAMENTO BETWEEN " + inicioMes + " AND " + fimMes + " AND DATAPAGAMENTO > 0";
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
        
        return qtd;

    }
    
    public long verificaExistencia(long idOrcamento, long inicioMes, long fimMes, String ano) {
        
        String sql = "";
        long id = 0;

        sql = "SELECT ID_DESPESA AS ID FROM DESPESAMENSAL WHERE ID_ORCAMENTO = ? AND ANO = ? AND DATALANCAMENTO BETWEEN " + inicioMes + " AND " + fimMes ;
        
         
        Connection connection = null;
        PreparedStatement pStatement = null;        
        try {
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, idOrcamento);
            pStatement.setString(2,ano );
                
            ResultSet rs = pStatement.executeQuery();
                
            if(rs != null){
                while(rs.next()){
                    System.out.println("teste");
                    id = rs.getLong("ID");
                }      
            }
            
            return id;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
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
        return id;
    }

    public void atualizarDespesa(Despesa despesa) {

        String sql1 = "UPDATE DESPESAMENSAL SET VALORPAGO =?, ANO = ?, DATALANCAMENTO = ?, DATAVENCIMENTO = ?, DATAPAGAMENTO = ?, STATUS = ?,"
                + "ANOTACAO = ?, ID_ORCAMENTO = ?, FORMAPAGAMENTO = ? WHERE ID_DESPESA = ?";

         String sql2 = "UPDATE DESPESAMENSAL SET VALORPAGO = NULL, ANO = ?, DATALANCAMENTO = ?, DATAVENCIMENTO = ?, DATAPAGAMENTO = NULL, STATUS = ?,"
                + "ANOTACAO = NULL, ID_ORCAMENTO = ?, FORMAPAGAMENTO = NULL WHERE ID_DESPESA = ?";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        try{
            connection = new ConnectionMVC().getConnection();

            if (despesa.isStatus()) {
                pStatement = connection.prepareStatement(sql1);
                
                pStatement.setLong(10, despesa.getIdDespesa());
                pStatement.setLong(1, despesa.getValorPago());
                pStatement.setString(2, despesa.getAno());
                pStatement.setDate(3, Date.valueOf(despesa.getLancameto()));
                pStatement.setDate(4, Date.valueOf(despesa.getVencimento()) );
                pStatement.setDate(5, Date.valueOf(despesa.getPagamento()));
                pStatement.setBoolean(6, despesa.isStatus());
                pStatement.setString(7, despesa.getAnotacao());
                pStatement.setLong(8, despesa.getIdOrcamento());
                pStatement.setString(9, despesa.getFormaPagamento());

                pStatement.executeUpdate();
            }else{  
                pStatement = connection.prepareStatement(sql2);
                
                pStatement.setLong(6, despesa.getIdDespesa());
                pStatement.setString(1, despesa.getAno());
                pStatement.setDate(2, Date.valueOf(despesa.getLancameto()));
                pStatement.setDate(3, Date.valueOf(despesa.getVencimento()) );
                pStatement.setBoolean(4, despesa.isStatus());
                pStatement.setLong(5, despesa.getIdOrcamento());
         

                pStatement.executeUpdate();
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERRO despesaDAO" + e);
            
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
        
    
    }

    public boolean excluirDespesa(long idDespesa) {

        String sql = "DELETE FROM DESPESAMENSAL WHERE ID_DESPESA = ?";
        Connection connection = null;
        PreparedStatement ps = null;
        
        
        try {
            connection = new ConnectionMVC().getConnection();
            
            ps = connection.prepareStatement(sql);
            
            ps.setLong(1, idDespesa);
            
            return ps.executeUpdate() == 1;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERRO despesaDAO" + e);
        }finally {
            
            try {
                if (ps != null) {
                    ps.close();
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
        return false;
    }

    public long retornaSomaDeDespesasMensais(Month mes) {
        String sql = "SELECT SUM(VALORPAGO) AS DESPESAMENSAL FROM DESPESAMENSAL WHERE DATALANCAMENTO BETWEEN ? AND ?";
        long despesas = 0;
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
    
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
        
            pStatement.setLong(1, new ManipulaData().inicioDoMes(LocalDate.now(), mes));
            pStatement.setLong(2, new ManipulaData().fimDoMes(LocalDate.now(), mes));

            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                   despesas = rs.getInt("DESPESAMENSAL");
                }
            }
            
            return despesas;
       

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
        
        return despesas;
    }

   
    
    
}
