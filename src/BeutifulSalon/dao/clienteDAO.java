
package BeutifulSalon.dao;


/**
 *
 * @author Mateus
 */

import BeutifulSalon.Ferramentas.ManipulaData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import BeutifulSalon.model.Cliente;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
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
            pStatement.setString(1, cliente.getCpf());
            pStatement.setString(2, cliente.getNome());
            pStatement.setString(3, cliente.getSobrenome());
            pStatement.setString(4, cliente.getEmail());
            pStatement.setString(5,  cliente.getDataNasc());
            pStatement.setString(6, cliente.getCep());
            pStatement.setString(7, cliente.getBairro());
            pStatement.setString(8, cliente.getRua());
            pStatement.setString(9, cliente.getCidade());
            pStatement.setString(10, cliente.getTelefoneResidencial());
            pStatement.setString(11, cliente.getCelular());
            pStatement.setDate(12, java.sql.Date.valueOf(cliente.getDataDeRegistro()));
            pStatement.setString(13, cliente.getNumeroDaCasa());
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
    
    public List<Cliente> listarAniversariantesDoMes() throws ExceptionDAO{
        
        String sql = "SELECT * FROM CLIENTE WHERE Substr(Cliente.DATANASC,0, 6) = (SELECT strftime('%d/%m',date('now', 'localtime')))";
        Connection connection = null;
        PreparedStatement pStatement = null;
   
        List<Cliente> clientes =  null;
            
        try {
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sql);
            ManipulaData md = new ManipulaData();

            
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                clientes = new ArrayList<>();
                
                while(rs.next()){
                    Cliente clienteAtual = new Cliente();
                    clienteAtual.setNome(rs.getString("NOME"));
                    clienteAtual.setSobrenome(rs.getString("SOBRENOME"));
                    clienteAtual.setEmail(rs.getString("EMAIL"));
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
                    clienteAtual.setNome(rs.getString("NOME"));
                    clienteAtual.setSobrenome(rs.getString("SOBRENOME"));
                    clienteAtual.setCelular(rs.getString("CELULAR"));
                    clienteAtual.setEmail(rs.getString("EMAIL"));
                    clienteAtual.setCpf(rs.getString("CPF"));
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
                    clienteAtual.setNome(rs.getString("NOME"));
                    clienteAtual.setSobrenome(rs.getString("SOBRENOME"));
                    clienteAtual.setCelular(rs.getString("CELULAR"));
                    clienteAtual.setEmail(rs.getString("EMAIL"));
                    clienteAtual.setCpf(rs.getString("CPF"));
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
                cliente.setNome(rs.getString("NOME"));
                cliente.setSobrenome(rs.getString("SOBRENOME"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setEmail(rs.getString("EMAIL"));
                cliente.setCelular(rs.getString("CELULAR"));
                cliente.setDataNasc(rs.getString("DATANASC"));
                cliente.setCep(rs.getString("CEP"));
                cliente.setBairro(rs.getString("BAIRRO"));
                cliente.setRua(rs.getString("RUA"));
                cliente.setNumeroDaCasa(rs.getString("NUMERO"));
                cliente.setCidade(rs.getString("CIDADE"));
                cliente.setCelular(rs.getString("CELULAR"));
                cliente.setTelefoneResidencial(rs.getString("TELEFONE")); 
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
        
        
        String sqlScript = "SELECT NOME,SOBRENOME,CPF, EMAIL, CELULAR, DATANASC, "
                + "CEP, BAIRRO, RUA,NUMERO, CIDADE,CELULAR, TELEFONE,"
                +" TIPODECABELO, TAMANHOCABELO,CORCABELO, CONHECEU, FACEBOOK,INSTAGRAM, OBSERVACOES"
                + " FROM CLIENTE WHERE CPF = ?";
        
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
                cliente.setNome(rs.getString("NOME"));
                cliente.setSobrenome(rs.getString("SOBRENOME"));
                cliente.setCpf(rs.getString("CPF"));
                cliente.setEmail(rs.getString("EMAIL"));
                cliente.setCelular(rs.getString("CELULAR"));
                cliente.setDataNasc(rs.getString("DATANASC"));
                cliente.setCep(rs.getString("CEP"));
                cliente.setBairro(rs.getString("BAIRRO"));
                cliente.setRua(rs.getString("RUA"));
                cliente.setNumeroDaCasa(rs.getString("NUMERO"));
                cliente.setCidade(rs.getString("CIDADE"));
                cliente.setCelular(rs.getString("CELULAR"));
                cliente.setTelefoneResidencial(rs.getString("TELEFONE")); 
                cliente.setTipoDeCabelo(rs.getInt("TIPODECABELO"));
                cliente.setTamanhoCabelo(rs.getInt("TAMANHOCABELO"));
                cliente.setCorCabelo(rs.getString("CORCABELO"));
                cliente.setDeOndeConheceu(rs.getInt("CONHECEU"));
                cliente.setFacebook(rs.getString("FACEBOOK"));
                cliente.setInstagram(rs.getString("INSTAGRAM"));
                cliente.setObservacoes(rs.getString("OBSERVACOES"));
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
            pStatement.setString(13, cliente.getCpf());
            pStatement.setString(1, cliente.getCpf());
            pStatement.setString(2, cliente.getNome());
            pStatement.setString(3, cliente.getSobrenome());
            pStatement.setString(4, cliente.getEmail());
            pStatement.setString(5, cliente.getDataNasc());
            pStatement.setString(6, cliente.getCep());
            pStatement.setString(7, cliente.getBairro());
            pStatement.setString(8, cliente.getRua());
            pStatement.setString(9, cliente.getCidade());
            pStatement.setString(10, cliente.getTelefoneResidencial());
            pStatement.setString(11, cliente.getCelular());
            pStatement.setString(12, cliente.getNumeroDaCasa());
            
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
     
    public void atualizarDetalhesCliente(Cliente cliente) {
    
        String sqlScript = "UPDATE CLIENTE SET TIPODECABELO = ?, TAMANHOCABELO = ?, CORCABELO = ?, CONHECEU = ? , FACEBOOK = ?, INSTAGRAM = ?, OBSERVACOES = ? WHERE CPF = ? ";
        
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setString(8, cliente.getCpf());
            pStatement.setInt(1, cliente.getTipoDeCabelo());
            pStatement.setInt(2, cliente.getTamanhoCabelo());
            pStatement.setString(3, cliente.getCorCabelo());
            pStatement.setInt(4, cliente.getDeOndeConheceu());
            pStatement.setString(5, cliente.getFacebook());
            pStatement.setString(6, cliente.getInstagram());
            pStatement.setString(7, cliente.getObservacoes());
            
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
    
    public List<Cliente> top5Clientes(int anoReferente){
         String sql  = "SELECT COUNT(AGENDAMENTO.CPF_CLIENTE) AS QTD, CLIENTE.NOME, CLIENTE.SOBRENOME FROM AGENDAMENTO\n" +
                "INNER JOIN CLIENTE ON CLIENTE.CPF = AGENDAMENTO.CPF_CLIENTE " +
                "WHERE AGENDAMENTO.DATA BETWEEN ? AND ?" +
                "GROUP BY AGENDAMENTO.CPF_CLIENTE ORDER BY COUNT(AGENDAMENTO.CPF_CLIENTE) DESC LIMIT 5;";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        List<Cliente> clientes =  null;
        
        try{
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            
            long inicioDoAno = LocalDate.ofYearDay(anoReferente, 1).toEpochDay() * 24 * 60 * 60 * 1000;
            long fimDoAno = LocalDate.ofYearDay(anoReferente, 1).plusYears(1).toEpochDay() * 24 * 60 * 60 * 1000; 
            pStatement.setLong(1, inicioDoAno);
            pStatement.setLong(2, fimDoAno);
            
            ResultSet rs = pStatement.executeQuery();
           
            
            if(rs != null){
                clientes = new ArrayList<>();
                
                while(rs.next()){
                    Cliente clienteAtual = new Cliente();
                    clienteAtual.setNome(rs.getString("NOME"));
                    clienteAtual.setSobrenome(rs.getString("SOBRENOME"));
                    clienteAtual.setDeOndeConheceu(rs.getInt("QTD"));//gambiarra
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
    
      
    
    
    public boolean cadastraImagemPerfil(String cpf, byte[] imagem){
        
  
        String sql2 = "UPDATE CLIENTE SET FOTOPERFIL = ? WHERE CLIENTE.CPF = ?";

        
        Connection connection = null;
        PreparedStatement pStatement = null;
   
        
        try {
            connection = new ConnectionMVC().getConnection();  
            pStatement = connection.prepareStatement(sql2);
            pStatement.setString(2, cpf);
            pStatement.setObject(1, imagem);
            
            int sucesso = pStatement.executeUpdate();
            
            return sucesso > 0;
   
        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(null,"Erro Cliente DAO"  + e);
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

    public byte[] recuperaImagemPerfil(String cpf) {
        
        String sql = "SELECT FOTOPERFIL FROM CLIENTE WHERE CPF = ?";

        
        Connection connection = null;
        PreparedStatement pStatement = null;
  
        
        try {
            connection = new ConnectionMVC().getConnection();  
            pStatement = connection.prepareStatement(sql);
            pStatement.setString(1, cpf);
            
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
            
                return rs.getBytes("FOTOPERFIL");
            }
  
        } catch (SQLException e) { 
            JOptionPane.showMessageDialog(null,"Erro Cliente DAO"  + e);
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
}
