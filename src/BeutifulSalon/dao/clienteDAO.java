
package BeutifulSalon.dao;


/**
 *
 * @author Mateus
 */

import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.controller.CabeleireiroController;
import BeutifulSalon.model.Cabeleireiro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import BeutifulSalon.model.Cliente;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;


public class clienteDAO {
    
    public void cadastrarCliente(Cliente cliente) throws ExceptionDAO{
        
        
        String sqlScript = "INSERT INTO CLIENTE (NOME, SOBRENOME, EMAIL, DATANASC, CEP, BAIRRO, RUA, CIDADE, TELEFONE, CELULAR, DATAREG, NUMERO)"
                +"VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
   
        
        try {
            
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sqlScript);
   
            pStatement.setString(1, cliente.getNome());
            pStatement.setString(2, cliente.getSobrenome());
            pStatement.setString(3, cliente.getEmail());
            pStatement.setString(4,  cliente.getDataNasc());
            pStatement.setString(5, cliente.getCep());
            pStatement.setString(6, cliente.getBairro());
            pStatement.setString(7, cliente.getRua());
            pStatement.setString(8, cliente.getCidade());
            pStatement.setString(9, cliente.getTelefoneResidencial());
            pStatement.setString(10, cliente.getCelular());
            pStatement.setDate(11, java.sql.Date.valueOf(cliente.getDataDeRegistro()));
            pStatement.setString(12, cliente.getNumeroDaCasa());
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
    
   
                             
    
    public void atualizarUltimoEnvioEmailAniversario(long id){
        
        String sql = "UPDATE EMAILANIVERSARIO SET ULTIMOENVIO = ? WHERE ID_CLIENTE = ?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(2, id);
            pStatement.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
           
            
            pStatement.execute(); 
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro atualizar ultimo envio de email"  + e);
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
    
    public void atualizarUltimoEnvioEmailUltimaVisita(long id){
        
        String sql = "UPDATE EMAILULTIMAVISITA SET ULTIMOENVIO = ? WHERE ID_CLIENTE = ?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        try {
            
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(2, id);
            pStatement.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
           
            
            pStatement.execute(); 
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro atualizar ultimo envio de email"  + e);
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
        
        String sql = "SELECT * FROM CLIENTE " +
        " INNER JOIN EMAILANIVERSARIO ON EMAILANIVERSARIO.ID_CLIENTE = CLIENTE.ID " +
        " WHERE Substr(Cliente.DATANASC,0, 6) = ? AND (EMAILANIVERSARIO.ULTIMOENVIO NOT BETWEEN ? AND ? OR EMAILANIVERSARIO.ULTIMOENVIO IS NULL)"
                + " AND CLIENTE.EXCLUIDO = FALSE";
        Connection connection = null;
        PreparedStatement pStatement = null;
   
        List<Cliente> clientes =  null;
            
        try {   
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/LL");
            
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sql);
            ManipulaData md = new ManipulaData();
            pStatement.setString(1, LocalDate.now().format(formatterData));
            pStatement.setLong(2 ,md.meiaNoiteHoje());
            pStatement.setLong(3, md.MeiaNoiteAmanha());
            
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                clientes = new ArrayList<>();
                
                while(rs.next()){
                    Cliente clienteAtual = new Cliente();
                    clienteAtual.setId(rs.getLong("ID"));
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
    
     public List<Cliente> listaClientesEmailUltimaVisita() throws ExceptionDAO{
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        ManipulaData md = new ManipulaData();
        List<Cliente> clientes = null;
        Cabeleireiro c = new CabeleireiroController().selecionaCabeleireiro();
        long periodoDeReenvio = LocalDate.now().minusMonths(c.getEmailUltimaVisita().getPeriodoReenvio()).toEpochDay() * 24 * 60 * 60 * 1000;
        
        
        String sql1 = "SELECT NOME, SOBRENOME, EMAIL, CLIENTE.ID FROM CLIENTE "
                + "INNER JOIN AGENDAMENTO ON AGENDAMENTO.ID_CLIENTE = CLIENTE.ID "
                + "INNER JOIN EMAILULTIMAVISITA ON EMAILULTIMAVISITA.ID_CLIENTE = CLIENTE.ID "
                + "WHERE AGENDAMENTO.DATA NOT BETWEEN ? AND " + md.meiaNoiteHoje() +" AND EMAILULTIMAVISITA.ULTIMOENVIO IS NULL AND CLIENTE.EXCLUIDO = FALSE";
        
        String sql2 = "SELECT NOME, SOBRENOME, EMAIL, CLIENTE.ID FROM CLIENTE "
                + "INNER JOIN AGENDAMENTO ON AGENDAMENTO.ID_CLIENTE = CLIENTE.ID "
                + "INNER JOIN EMAILULTIMAVISITA ON EMAILULTIMAVISITA.ID_CLIENTE = CLIENTE.ID "
                + " WHERE EMAILULTIMAVISITA.ULTIMOENVIO < ? AND CLIENTE.EXCLUIDO = FALSE AND EMAILULTIMAVISITA.ULTIMOENVIO IS NOT NULL ";
         
         try {

             connection = new ConnectionMVC().getConnection();

             pStatement = connection.prepareStatement(sql1);

             pStatement.setLong(1, periodoDeReenvio);

             ResultSet rs = pStatement.executeQuery();

             if (rs != null) {
                 clientes = new ArrayList<>();

                 while (rs.next()) {

                     Cliente clienteAtual = new Cliente();
                     clienteAtual.setId(rs.getLong("ID"));
                     clienteAtual.setNome(rs.getString("NOME"));
                     clienteAtual.setSobrenome(rs.getString("SOBRENOME"));
                     clienteAtual.setEmail(rs.getString("EMAIL"));
                     clientes.add(clienteAtual);

                 }

             }

             pStatement = connection.prepareStatement(sql2);

             pStatement.setLong(1, periodoDeReenvio);

             rs = pStatement.executeQuery();

             if (rs != null) {

                 while (rs.next()) {

                     Cliente clienteAtual = new Cliente();
                     clienteAtual.setId(rs.getLong("ID"));
                     clienteAtual.setNome(rs.getString("NOME"));
                     clienteAtual.setSobrenome(rs.getString("SOBRENOME"));
                     clienteAtual.setEmail(rs.getString("EMAIL"));
                     clientes.add(clienteAtual);

                 }

             }
            
        
                     
        } catch (SQLException e) {
            throw new ExceptionDAO("Erro ao listar emails ultimavisita cliente (classClienteDAO)" + e);
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
        
        
        String sql  = "SELECT ID, NOME  ||' '|| SOBRENOME AS NOMECOMPLETO,NOME,SOBRENOME, CELULAR,EMAIL FROM CLIENTE WHERE "
                + " NOMECOMPLETO LIKE '%" + nome + "%' AND CLIENTE.EXCLUIDO = FALSE ORDER BY DATAREG DESC";
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
                    clienteAtual.setId(rs.getLong("ID"));
                    clienteAtual.setNome(rs.getString("NOME"));
                    clienteAtual.setSobrenome(rs.getString("SOBRENOME"));
                    clienteAtual.setCelular(rs.getString("CELULAR"));
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
    
    public List<Cliente> listarClientes() throws ExceptionDAO{
        
        String sql  = "SELECT ID, NOME, SOBRENOME, CELULAR, EMAIL,"
                + "(SELECT MAX(DATA) FROM AGENDAMENTO WHERE ID_CLIENTE = ID AND REALIZADO = TRUE) AS ULTIMAVISITA "
                + "FROM CLIENTE  WHERE CLIENTE.EXCLUIDO = FALSE ORDER BY DATAREG DESC";
        
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
                    clienteAtual.setId(rs.getLong("ID"));
                    clienteAtual.setNome(rs.getString("NOME"));
                    clienteAtual.setSobrenome(rs.getString("SOBRENOME"));
                    clienteAtual.setCelular(rs.getString("CELULAR"));
                    clienteAtual.setEmail(rs.getString("EMAIL"));
                    
                   
                    if(rs.getDate("ULTIMAVISITA") != null){
                        clienteAtual.setUltimaVisita(rs.getDate("ULTIMAVISITA").toLocalDate());
                    }
                        
                   
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
    
    public void deletarCliente(long id){
        
        
        String sql  = "UPDATE CLIENTE SET EXCLUIDO = TRUE WHERE ID = ?";
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        try {
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, id);
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
    
    /**
     * Retorna um objeto Cliente, para enviar para tela "editarCliente" e apresentar as informações
     * @param id
     * @return 
     */
    public Cliente editarCliente(long id){
        
        
        String sqlScript = "SELECT ID,NOME,SOBRENOME, EMAIL, CELULAR, DATANASC, CEP, BAIRRO, RUA,NUMERO, CIDADE,CELULAR, TELEFONE FROM CLIENTE WHERE ID = ?";
        
        PreparedStatement pStatement = null;
        Connection connection = null;
        ResultSet rs = null;
        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setLong(1, id);
            rs = pStatement.executeQuery();
            Cliente cliente = new Cliente(); 
      
            if(rs != null){                
                while(rs.next()){
                cliente.setId(rs.getLong("ID"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setSobrenome(rs.getString("SOBRENOME"));
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
    
    public Cliente buscarCliente(long id){
        
        
        String sqlScript = "SELECT ID, NOME,SOBRENOME, EMAIL, CELULAR, DATANASC,DATAREG, "
                + "CEP, BAIRRO, RUA,NUMERO, CIDADE,CELULAR, TELEFONE,"
                +" TIPODECABELO, TAMANHOCABELO,CORCABELO, CONHECEU, FACEBOOK,INSTAGRAM, OBSERVACOES"
                + " FROM CLIENTE WHERE ID = ?";
        
        PreparedStatement pStatement = null;
        Connection connection = null;
        ResultSet rs = null;
        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setLong(1, id);
            rs = pStatement.executeQuery();
            Cliente cliente = new Cliente(); 
      
            if(rs != null){                
                while(rs.next()){
                
                cliente.setId(rs.getLong("ID"));
                cliente.setNome(rs.getString("NOME"));
                cliente.setSobrenome(rs.getString("SOBRENOME"));
                cliente.setEmail(rs.getString("EMAIL"));
                cliente.setCelular(rs.getString("CELULAR"));
                cliente.setDataNasc(rs.getString("DATANASC"));
                cliente.setCep(rs.getString("CEP"));
                cliente.setBairro(rs.getString("BAIRRO"));
                cliente.setRua(rs.getString("RUA"));
                cliente.setDataDeRegistro(rs.getDate("DATAREG").toLocalDate());
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
    /**
     * Recebe um objeto cliente, com as informações inseridas na tela "editarCliente", e realiza o update
     * @param cliente 
     */
     
    public void atualizarCliente(Cliente cliente) {
        
        
        String sqlScript = "UPDATE CLIENTE SET NOME = ? , SOBRENOME = ?, EMAIL = ? , DATANASC = ? , CEP = ? , BAIRRO = ? , RUA = ? , "
                + "CIDADE = ? , TELEFONE = ?, CELULAR = ? , NUMERO = ? WHERE ID = ? ";
        
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setLong(12, cliente.getId());
            pStatement.setString(1, cliente.getNome());
            pStatement.setString(2, cliente.getSobrenome());
            pStatement.setString(3, cliente.getEmail());
            pStatement.setString(4, cliente.getDataNasc());
            pStatement.setString(5, cliente.getCep());
            pStatement.setString(6, cliente.getBairro());
            pStatement.setString(7, cliente.getRua());
            pStatement.setString(8, cliente.getCidade());
            pStatement.setString(9, cliente.getTelefoneResidencial());
            pStatement.setString(10, cliente.getCelular());
            pStatement.setString(11, cliente.getNumeroDaCasa());
            
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
    
        String sqlScript = "UPDATE CLIENTE SET TIPODECABELO = ?, TAMANHOCABELO = ?, CORCABELO = ?, CONHECEU = ? , FACEBOOK = ?, INSTAGRAM = ?, OBSERVACOES = ? WHERE ID = ? ";
        
        PreparedStatement pStatement = null;
        Connection connection = null;
        
        try {
            
            connection = new ConnectionMVC().getConnection();
            
            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setLong(8, cliente.getId());
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
    
     
    public LocalDate ultimaVisita(long id) throws ExceptionDAO{
        
        String sql = "SELECT MAX(DATA) AS ULTIMAVISITA FROM AGENDAMENTO WHERE ID_CLIENTE = ? AND REALIZADO = TRUE ";
        Connection connection = null;
        PreparedStatement pStatement = null;
        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);          
            pStatement.setLong(1, id);
            
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
        
        String sql = "SELECT COUNT(*) AS QTD FROM CLIENTE WHERE CLIENTE.CPF = ? AND CLIENTE.EXCLUIDO = FALSE";
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
         String sql  = "SELECT COUNT(AGENDAMENTO.ID_CLIENTE) AS QTD, CLIENTE.NOME, CLIENTE.SOBRENOME FROM AGENDAMENTO\n" +
                "INNER JOIN CLIENTE ON CLIENTE.ID = AGENDAMENTO.ID_CLIENTE " +
                "WHERE AGENDAMENTO.DATA BETWEEN ? AND ? AND CLIENTE.EXCLUIDO = FALSE AND AGENDAMENTO.REALIZADO = TRUE " +
                "GROUP BY AGENDAMENTO.ID_CLIENTE ORDER BY COUNT(AGENDAMENTO.ID_CLIENTE) DESC LIMIT 5;";
        
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
                    clienteAtual.setQtdVisitas(rs.getInt("QTD"));
        
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
    
      
    
    
    public boolean cadastraImagemPerfil(long id, byte[] imagem){
        
  
        String sql2 = "UPDATE CLIENTE SET FOTOPERFIL = ? WHERE CLIENTE.ID = ?";

        
        Connection connection = null;
        PreparedStatement pStatement = null;
   
        
        try {
            connection = new ConnectionMVC().getConnection();  
            pStatement = connection.prepareStatement(sql2);
            pStatement.setLong(2, id);
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

    public byte[] recuperaImagemPerfil(long id) {
        
        String sql = "SELECT FOTOPERFIL FROM CLIENTE WHERE ID = ?";

        
        Connection connection = null;
        PreparedStatement pStatement = null;
  
        
        try {
            connection = new ConnectionMVC().getConnection();  
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, id);
            
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
