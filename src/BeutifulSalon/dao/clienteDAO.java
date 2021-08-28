
package BeutifulSalon.dao;


/**
 *
 * @author Mateus
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import BeutifulSalon.model.Cliente;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;


public class clienteDAO {
    
    public void cadastrarCliente(Cliente cliente) throws ExceptionDAO{
        
        
        String sqlScript = "INSERT INTO CLIENTE (CPF ,NOME, SOBRENOME, EMAIL, DATANASC, CEP, BAIRRO, RUA, CIDADE, TELEFONE, CELULAR, DATAREG, NUMERO)"
                +"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        PreparedStatement pStatement = null;
        Connection connection = null;
        
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setString(1, cliente.getCPF());
            pStatement.setString(2, cliente.getNOME());
            pStatement.setString(3, cliente.getSOBRENOME());
            pStatement.setString(4, cliente.getEMAIL());
            pStatement.setDate(5,  java.sql.Date.valueOf(cliente.getDATANASC()));
            pStatement.setString(6, cliente.getCEP());
            pStatement.setString(7, cliente.getBAIRRO());
            pStatement.setString(8, cliente.getRUA());
            pStatement.setString(9, cliente.getCIDADE());
            pStatement.setString(10, cliente.getTELEFONE());
            pStatement.setString(11, cliente.getCELULAR());
            pStatement.setDate(12, java.sql.Date.valueOf(cliente.getDATAREG()));
            pStatement.setString(13, cliente.getNUMERO());
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
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão" + e);
            }
           
        }
       
    }
    
    public List<Cliente> listarClientes(String nome) throws ExceptionDAO{
        
        
        String sql  = "SELECT NOME,SOBRENOME,CELULAR,EMAIL,CPF FROM CLIENTE WHERE NOME LIKE '%" + nome + "%' ORDER BY DATAREG DESC";
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        List<Cliente> clientes =  null;
            
        try {
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sql);
            
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                clientes = new ArrayList<>();
                
                while(rs.next()){
                    Cliente clienteAtual = new Cliente();
                    clienteAtual.setNOME(rs.getString("NOME"));
                    clienteAtual.setSOBRENOME(rs.getString("SOBRENOME"));
                    clienteAtual.setCELULAR(rs.getString("CELULAR"));
                    clienteAtual.setEMAIL(rs.getString("EMAIL"));
                    clienteAtual.setCPF(rs.getString("CPF"));
                    clientes.add(clienteAtual);
                }
            }
            
            
        } catch (SQLException e) {
            throw new ExceptionDAO("Erro ao consultar cliente (classClienteDAO)");
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
                    
        return clientes;
                   
    }
    
    public List<Cliente> listarClientes() throws ExceptionDAO{
        
        String sql  = "SELECT CPF, NOME, SOBRENOME, CELULAR, EMAIL FROM CLIENTE ORDER BY DATAREG DESC";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        List<Cliente> clientes =  null;
        
        try{
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            
            ResultSet rs = pStatement.executeQuery();
           
            
            if(rs != null){
                clientes = new ArrayList<>();
                
                while(rs.next()){
                    Cliente clienteAtual = new Cliente();
                    clienteAtual.setNOME(rs.getString("NOME"));
                    clienteAtual.setSOBRENOME(rs.getString("SOBRENOME"));
                    clienteAtual.setCELULAR(rs.getString("CELULAR"));
                    clienteAtual.setEMAIL(rs.getString("EMAIL"));
                    clienteAtual.setCPF(rs.getString("CPF"));
                    clientes.add(clienteAtual);
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
        
        return clientes;
        
        
    }
    
    public void deletarCliente(String cpf){
        
        
        String sql  = "DELETE FROM CLIENTE WHERE CPF = ?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        try {
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, cpf);
            pStatement.executeUpdate();
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente: " + e);
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
    
    public Cliente editarCliente(String cpf){
        
        
        String sqlScript = "SELECT NOME,SOBRENOME,CPF, EMAIL, CELULAR, DATANASC, CEP, BAIRRO, RUA,NUMERO, CIDADE,CELULAR, TELEFONE FROM CLIENTE WHERE CPF = ?";
        
        PreparedStatement pStatement = null;
        Connection connection = null;
        ResultSet rs = null;
        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setString(1, cpf);
            rs = pStatement.executeQuery();
            Cliente cliente = new Cliente(); 
      
            if(rs != null){                
                while(rs.next()){
                cliente.setNOME(rs.getString("NOME"));
                cliente.setSOBRENOME(rs.getString("SOBRENOME"));
                cliente.setCPF(rs.getString("CPF"));
                cliente.setEMAIL(rs.getString("EMAIL"));
                cliente.setCELULAR(rs.getString("CELULAR"));
                cliente.setDATANASC(rs.getDate("DATANASC").toLocalDate());
                cliente.setCEP(rs.getString("CEP"));
                cliente.setBAIRRO(rs.getString("BAIRRO"));
                cliente.setRUA(rs.getString("RUA"));
                cliente.setNUMERO(rs.getString("NUMERO"));
                cliente.setCIDADE(rs.getString("CIDADE"));
                cliente.setCELULAR(rs.getString("CELULAR"));
                cliente.setTELEFONE(rs.getString("TELEFONE")); 
                }
            }
           
            return cliente;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ClienteDAO " + e);
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
    
     public Cliente buscarCliente(String cpf){
        
        
        String sqlScript = "SELECT NOME,SOBRENOME,CPF, EMAIL, CELULAR, DATANASC, CEP, BAIRRO, RUA,NUMERO, CIDADE,CELULAR, TELEFONE FROM CLIENTE WHERE CPF = ?";
        
        PreparedStatement pStatement = null;
        Connection connection = null;
        ResultSet rs = null;
        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setString(1, cpf);
            rs = pStatement.executeQuery();
            Cliente cliente = new Cliente(); 
      
            if(rs != null){                
                while(rs.next()){
                cliente.setNOME(rs.getString("NOME"));
                cliente.setSOBRENOME(rs.getString("SOBRENOME"));
                cliente.setCPF(rs.getString("CPF"));
                cliente.setEMAIL(rs.getString("EMAIL"));
                cliente.setCELULAR(rs.getString("CELULAR"));
                cliente.setDATANASC(rs.getDate("DATANASC").toLocalDate());
                cliente.setCEP(rs.getString("CEP"));
                cliente.setBAIRRO(rs.getString("BAIRRO"));
                cliente.setRUA(rs.getString("RUA"));
                cliente.setNUMERO(rs.getString("NUMERO"));
                cliente.setCIDADE(rs.getString("CIDADE"));
                cliente.setCELULAR(rs.getString("CELULAR"));
                cliente.setTELEFONE(rs.getString("TELEFONE")); 
                }
            }
           
            return cliente;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro ClienteDAO " + e);
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
     
    public void atualizarCliente(Cliente cliente) {
        
        
        String sqlScript = "UPDATE CLIENTE SET CPF = ? ,NOME = ? , SOBRENOME = ?, EMAIL = ? , DATANASC = ? , CEP = ? , BAIRRO = ? , RUA = ? , CIDADE = ? , TELEFONE = ?, CELULAR = ? , NUMERO = ? WHERE CPF = ? ";
        
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setString(13, cliente.getCPF());
            pStatement.setString(1, cliente.getCPF());
            pStatement.setString(2, cliente.getNOME());
            pStatement.setString(3, cliente.getSOBRENOME());
            pStatement.setString(4, cliente.getEMAIL());
            pStatement.setDate(5, java.sql.Date.valueOf(cliente.getDATANASC()));
            pStatement.setString(6, cliente.getCEP());
            pStatement.setString(7, cliente.getBAIRRO());
            pStatement.setString(8, cliente.getRUA());
            pStatement.setString(9, cliente.getCIDADE());
            pStatement.setString(10, cliente.getTELEFONE());
            pStatement.setString(11, cliente.getCELULAR());
            pStatement.setString(12, cliente.getNUMERO());
            
            pStatement.execute(); 
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro atualizar dados do cliente"  + e);
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
     
    public LocalDate ultimaVisita(String cpf) throws ExceptionDAO{
        
        String sql = "SELECT MAX(DATA) AS ULTIMAVISITA FROM AGENDAMENTO WHERE CPF_CLIENTE = ? AND REALIZADO = TRUE ";
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);          
            pStatement.setString(1, cpf);
            
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                  java.sql.Date data = rs.getDate("ULTIMAVISITA");
                  if(data != null){
                      return data.toLocalDate();
                  }else{
                      return null;
                  }
                }            
            }
         
        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(null,"Erro buscar ultima visita do cliente"  + e);
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

    public boolean verificaExistenciaCliente(String cpf) {
        
        String sql = "SELECT COUNT(*) AS QTD FROM CLIENTE WHERE CLIENTE.CPF = ?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);          
            pStatement.setString(1, cpf);
            
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                   
                    return rs.getLong("QTD") > 0; //true = existe cliente com este cpf
                    //false = nao existe cliente
              
                }            
            }
         
        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(null,"Erro buscar CPF existente"  + e);
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
        return true;
    }
    
}
