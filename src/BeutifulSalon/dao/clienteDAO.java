
package BeutifulSalon.dao;


/**
 *
 * @author Mateus
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.view.EditarCliente;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class clienteDAO {
    
    public void cadastrarCliente(Cliente cliente) throws Exception{
        
        
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
            pStatement.setDate(5,  new Date(cliente.getDATANASC().getTime()));
            System.out.println(cliente.getDATANASC());
            pStatement.setString(6, cliente.getCEP());
            pStatement.setString(7, cliente.getBAIRRO());
            pStatement.setString(8, cliente.getRUA());
            pStatement.setString(9, cliente.getCIDADE());
            pStatement.setString(10, cliente.getTELEFONE());
            pStatement.setString(11, cliente.getCELULAR());
            pStatement.setDate(12, new Date(cliente.getDATAREG().getTime()));
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
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão" + e);
            }
           
        }
        
        
        
    }
    
    public ArrayList<Cliente> listarClientes(String nome) throws ExceptionDAO {
        
        
        String sql  = "SELECT NOME,SOBRENOME,CELULAR,EMAIL,CPF FROM CLIENTE WHERE NOME LIKE '%" + nome + "%' ORDER BY NOME";
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        ArrayList<Cliente> clientes =  null;
            
        try {
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sql);
            
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                clientes = new ArrayList<Cliente>();
                
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
    
    public ArrayList<Cliente> listarClientes(){
        
        String sql  = "SELECT CPF, NOME, SOBRENOME, CELULAR, EMAIL FROM CLIENTE";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Cliente> clientes =  null;
        
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
            
        } catch (Exception e) {
            
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
    
    public void editarCliente(String cpf){
        
        
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
                cliente.setDATANASC(rs.getDate("DATANASC"));
                cliente.setCEP(rs.getString("CEP"));
                cliente.setBAIRRO(rs.getString("BAIRRO"));
                cliente.setRUA(rs.getString("RUA"));
                cliente.setNUMERO(rs.getString("NUMERO"));
                cliente.setCIDADE(rs.getString("CIDADE"));
                cliente.setCELULAR(rs.getString("CELULAR"));
                cliente.setTELEFONE(rs.getString("TELEFONE")); 
                }
            }
           
            new EditarCliente(cliente).setVisible(true); 
            
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
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão" + e);
            }
           
        }
        
    }

    public void atualizarCliente(Cliente cliente) {
        
        
        String sqlScript = "UPDATE CLIENTE SET CPF = ? ,NOME = ? , SOBRENOME = ?, EMAIL = ? , DATANASC = ? , CEP = ? , BAIRRO = ? , RUA = ? , CIDADE = ? , TELEFONE = ?, CELULAR = ? ,NUMERO = ? WHERE CPF = ? ";
        
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
            pStatement.setDate(5, new Date(cliente.getDATANASC().getTime()));
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
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Erro ao fechar conexão" + e);
            }
           
        }
        
    }
    
    
}
