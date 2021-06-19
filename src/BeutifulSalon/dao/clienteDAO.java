
package BeutifulSalon.dao;


/**
 *
 * @author Mateus
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import BeutifulSalon.model.Cliente;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class clienteDAO {
    
    public void cadastrarCliente(Cliente cliente) throws Exception{
        
        
        String sqlScript = "INSERT INTO CLIENTE (CPF ,NOME, SOBRENOME, EMAIL, DATANASC, CEP, BAIRRO, RUA, CIDADE, TELEFONE, CELULAR, DATAREG)"
                +"VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setString(1, cliente.getCPF());
            pStatement.setString(2, cliente.getNOME());
            pStatement.setString(3, cliente.getSOBRENOME());
            pStatement.setString(4, cliente.getEMAIL());
            pStatement.setDate(5, new Date(cliente.getDATANASC().getTime()));
            //JOptionPane.showMessageDialog(null, "Data: " + new Date(cliente.getDATANASC().getTime()) );
            pStatement.setString(6, cliente.getCEP());
            pStatement.setString(7, cliente.getBAIRRO());
            pStatement.setString(8, cliente.getRUA());
            pStatement.setString(9, cliente.getCIDADE());
            pStatement.setString(10, cliente.getTELEFONE());
            pStatement.setString(11, cliente.getCELULAR());
            //JOptionPane.showMessageDialog(null, "Data: " + new Date(cliente.getDATAREG().getTime()) );
            pStatement.setDate(12, new Date(cliente.getDATAREG().getTime()));
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
    
    public ArrayList<Cliente> listarClientes(String nome) throws ExceptionDAO {
        
        
        String sql  = "SELECT NOME,SOBRENOME,CELULAR,EMAIL FROM CLIENTE WHERE NOME LIKE '%" + nome + "%' ORDER BY NOME";
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        ArrayList<Cliente> clientes =  null;
            
        try {
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sql);
            
            ResultSet rs = pStatement.executeQuery(sql);
            
            if(rs != null){
                clientes = new ArrayList<Cliente>();
                
                while(rs.next()){
                    Cliente clienteAtual = new Cliente();
                    clienteAtual.setNOME(rs.getString("NOME"));
                    clienteAtual.setSOBRENOME(rs.getString("SOBRENOME"));
                    clienteAtual.setCELULAR(rs.getString("CELULAR"));
                    clienteAtual.setEMAIL(rs.getString("EMAIL"));
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
    
    public ArrayList<Cliente> listarClientes(){
        
        String sql  = "SELECT  NOME, SOBRENOME, CELULAR, EMAIL FROM CLIENTE";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Cliente> clientes =  null;
        
        try{
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            
            ResultSet rs = pStatement.executeQuery(sql);
            
            if(rs != null){
                clientes = new ArrayList<Cliente>();
                
                while(rs.next()){
                    Cliente clienteAtual = new Cliente();
                    clienteAtual.setNOME(rs.getString("NOME"));
                    clienteAtual.setSOBRENOME(rs.getString("SOBRENOME"));
                    clienteAtual.setCELULAR(rs.getString("CELULAR"));
                    clienteAtual.setEMAIL(rs.getString("EMAIL"));
                    clientes.add(clienteAtual);
                }
                
            }
            
            
            
            
        } catch (SQLException e){
            
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
    
    
}
