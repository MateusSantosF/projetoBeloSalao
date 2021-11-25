/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.dao;

import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.controller.ServicoController;
import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.Compra;
import BeutifulSalon.model.Item;
import BeutifulSalon.model.RelatorioAgendamento;
import BeutifulSalon.model.Servico;
import BeutifulSalon.model.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class AgendamentoDAO {

    public void cadastraAgendamento(Agendamento agendamento) throws SQLException, SQLException {

        String insertAgendamento = "INSERT INTO AGENDAMENTO (DATA, HORARIO, ID_CLIENTE, REALIZADO, DESCONTO, TOTAL,"
                + " VALORADICIONAL, PAGO, FORMADEPAGAMENTO, FIMAGENDAMENTO) VALUES (?, ?, ?, ?, ?,?,?,?,?,?)";

        String insertServicoAgendamento = "INSERT INTO AGENDAMENTO_SERVICO (ID_AGENDAMENTO, ID_SERVICO) "
                + "VALUES ((SELECT ID_AGENDAMENTO FROM AGENDAMENTO ORDER BY ID_AGENDAMENTO DESC LIMIT 1), ?)";
        
        String insertAgendamentoCompra = "INSERT INTO AGENDAMENTO_PRODUTO (ID_AGENDAMENTO, ID_VENDA) "
                + "VALUES (((SELECT ID_AGENDAMENTO FROM AGENDAMENTO ORDER BY ID_AGENDAMENTO DESC LIMIT 1)) ,"
                + " ((SELECT ID_VENDA FROM VENDA ORDER BY VENDA.ID_VENDA DESC LIMIT 1)))";
        
        if(agendamento.getProdutosComprados().size() >= 1){
            Venda venda = new Venda();
            venda.setData(agendamento.getData());
            venda.setIdCliente(agendamento.getIdCliente());
            venda.setItensVenda(agendamento.getProdutosComprados());
            
            new VendaProdutoDAO().cadastrarVenda(venda);
           
        }

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
            pStatement.setLong(7, agendamento.getValorAdicional());
            pStatement.setBoolean(8, agendamento.isPago());
            pStatement.setString(9, agendamento.getFormaDePagamento());
            pStatement.setTime(10, java.sql.Time.valueOf(agendamento.getFimAgendamento()));
            int firstInsert = pStatement.executeUpdate();

            if (firstInsert > 0) {
                try {

                    List<Servico> servicos = agendamento.getServicos();

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
            
            if(agendamento.getProdutosComprados().size() >= 1){
                pStatement = connection.prepareStatement(insertAgendamentoCompra);
                pStatement.execute();
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
    
    
    public List<Item> buscaProdutosCompradosAgendamento(long idAgendamento){
        
        String sql = "SELECT PRODUTO.IDPRODUTO AS ID, PRODUTO.NOME AS NOME, PRODUTO.MARCA AS MARCA, ITEM_VENDA.QUANTIDADE AS QTD,"
                + " ITEM_VENDA.PRECOUNITARIO AS PUNITARIO FROM VENDA " +
        "    INNER JOIN AGENDAMENTO_PRODUTO ON AGENDAMENTO_PRODUTO.ID_VENDA = VENDA.ID_VENDA " +
        "    INNER JOIN AGENDAMENTO ON AGENDAMENTO.ID_AGENDAMENTO = AGENDAMENTO_PRODUTO.ID_AGENDAMENTO " +
        "    INNER JOIN ITEM_VENDA ON VENDA.ID_VENDA  = ITEM_VENDA.ID_VENDA " +
        "    INNER JOIN PRODUTO ON PRODUTO.IDPRODUTO = ITEM_VENDA.ID_PRODUTO " +
        "WHERE AGENDAMENTO.ID_AGENDAMENTO = ?";
        
        List<Item> itensComprados = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
       
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, idAgendamento);
            
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    Item i = new Item();
                    i.setId_produto(rs.getLong("ID"));
                    i.setNome(rs.getString("NOME"));
                    i.setMarca(rs.getString("MARCA"));
                    i.setQuantidade(rs.getInt("QTD"));
                    i.setPreco(rs.getLong("PUNITARIO"));
                    itensComprados.add(i);
                }
            }
            
            return itensComprados;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro agendamentoDAO" + e);
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
        
        return null;
    }
    public Agendamento listarAgendamento(long idAgendamento){
        
 
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO,TOTAL,"
                + " DESCONTO, VALORADICIONAL, ID_CLIENTE, PAGO, FORMADEPAGAMENTO FROM AGENDAMENTO WHERE ID_AGENDAMENTO = ? ";
        
       
        
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
                    agendamento.setValorAdicional(rs.getLong("VALORADICIONAL"));
                    agendamento.setPago(rs.getBoolean("PAGO"));
                    agendamento.setFormaDePagamento(rs.getString("FORMADEPAGAMENTO"));
                }
            }
            agendamento.setProdutosComprados(buscaProdutosCompradosAgendamento(agendamento.getIdAgendamento()));
            
           
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
        
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, "
                + "ID_CLIENTE, TOTAL, DESCONTO, VALORADICIONAL, PAGO, FORMADEPAGAMENTO FROM AGENDAMENTO "
                + "INNER JOIN CLIENTE ON CLIENTE.ID = AGENDAMENTO.ID_CLIENTE"
                + " WHERE REALIZADO = TRUE AND CLIENTE.EXCLUIDO = FALSE ORDER BY DATA DESC ";
        
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
                    ag.setTotal(rs.getLong("TOTAL"));
                    ag.setDesconto(rs.getLong("DESCONTO"));
                    ag.setValorAdicional(rs.getLong("VALORADICIONAL"));
                    ag.setPago(rs.getBoolean("PAGO"));
                    ag.setFormaDePagamento(rs.getString("FORMADEPAGAMENTO"));
                    ag.setProdutosComprados(buscaProdutosCompradosAgendamento(ag.getIdAgendamento()));
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
        
        
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, ID_CLIENTE, TOTAL, DESCONTO, VALORADICIONAL, PAGO, FORMADEPAGAMENTO  FROM AGENDAMENTO"
                + " INNER JOIN CLIENTE ON CLIENTE.ID = AGENDAMENTO.ID_CLIENTE "
                + " WHERE DATA BETWEEN " + datas.meiaNoiteHoje() + " AND " + datas.MeiaNoiteAmanha() + " AND REALIZADO = TRUE "
                + " AND CLIENTE.EXCLUIDO = FALSE ORDER BY HORARIO ASC ";
        
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
                    ag.setTotal(rs.getLong("TOTAL"));
                    ag.setDesconto(rs.getLong("DESCONTO"));
                    ag.setValorAdicional(rs.getLong("VALORADICIONAL"));
                    ag.setPago(rs.getBoolean("PAGO"));
                    ag.setFormaDePagamento(rs.getString("FORMADEPAGAMENTO"));
                    ag.setProdutosComprados(buscaProdutosCompradosAgendamento(ag.getIdAgendamento()));
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
    
     public ArrayList<RelatorioAgendamento> listarAgendamentosRelatorio(long inicio, long fim) {
        
             
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, ID_CLIENTE, CLIENTE.NOME AS NOME, CLIENTE.SOBRENOME AS SOBRENOME, TOTAL, DESCONTO, VALORADICIONAL, PAGO, FORMADEPAGAMENTO  FROM AGENDAMENTO"
                + " INNER JOIN CLIENTE ON CLIENTE.ID = AGENDAMENTO.ID_CLIENTE "
                + " WHERE DATA BETWEEN " + inicio + " AND " + fim + " AND REALIZADO = TRUE "
                + " AND CLIENTE.EXCLUIDO = FALSE ORDER BY HORARIO ASC ";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<RelatorioAgendamento> agendamentos = null;

        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                
                agendamentos = new ArrayList<>();
                while(rs.next()){
                   RelatorioAgendamento ag = new RelatorioAgendamento();
                   
                
                    ag.setData(rs.getDate("DATA").toLocalDate());
                    ag.setHorario(rs.getTime("HORARIO").toLocalTime());     
                    ag.setFormaPagamento(rs.getString("FORMADEPAGAMENTO"));
                    ag.setNomeCliente(rs.getString("NOME"));
                    ag.setSobrenomeCliente(rs.getString("SOBRENOME"));
                    ag.setStatusPagamento(rs.getBoolean("REALIZADO"));
                    ag.setTotal(rs.getLong("TOTAL"));
                    ag.setDesconto(rs.getLong("DESCONTO"));
                    ag.setValorAdicional(rs.getLong("VALORADICIONAL"));
                    try {
                        ag.setServicosSolicitados(listaServicosAgendamento(rs.getLong("ID_AGENDAMENTO")));
                    } catch (ExceptionDAO e) {
                    }
              
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
        
        
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, ID_CLIENTE, TOTAL, DESCONTO, VALORADICIONAL, PAGO, FORMADEPAGAMENTO FROM AGENDAMENTO"
                + " INNER JOIN CLIENTE ON CLIENTE.ID = AGENDAMENTO.ID_CLIENTE "
                + " WHERE DATA BETWEEN " + datas.MeiaNoiteAmanha() + " AND " + datas.somaDia(LocalDateTime.now().plusDays(1), 1)
                + " AND REALIZADO = TRUE AND CLIENTE.EXCLUIDO = FALSE ORDER BY HORARIO ASC";
        
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
                    ag.setTotal(rs.getLong("TOTAL"));
                    ag.setDesconto(rs.getLong("DESCONTO"));
                    ag.setValorAdicional(rs.getLong("VALORADICIONAL"));
                    ag.setPago(rs.getBoolean("PAGO"));
                    ag.setFormaDePagamento(rs.getString("FORMADEPAGAMENTO"));
                        ag.setProdutosComprados(buscaProdutosCompradosAgendamento(ag.getIdAgendamento()));
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
        
        
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, ID_CLIENTE, TOTAL, DESCONTO, VALORADICIONAL, PAGO, FORMADEPAGAMENTO FROM AGENDAMENTO"
                + " INNER JOIN CLIENTE ON CLIENTE.ID = AGENDAMENTO.ID_CLIENTE"
                + " WHERE DATA BETWEEN " + datas.meiaNoiteHoje() + " AND " + datas.somaDia(LocalDateTime.now(), 5) 
                +" AND REALIZADO = TRUE AND CLIENTE.EXCLUIDO = FALSE ORDER BY DATA DESC";
        
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
                    ag.setTotal(rs.getLong("TOTAL"));
                    ag.setDesconto(rs.getLong("DESCONTO"));
                    ag.setValorAdicional(rs.getLong("VALORADICIONAL"));
                    ag.setPago(rs.getBoolean("PAGO"));
                    ag.setFormaDePagamento(rs.getString("FORMADEPAGAMENTO"));
                        ag.setProdutosComprados(buscaProdutosCompradosAgendamento(ag.getIdAgendamento()));
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
    
     public ArrayList<Agendamento> listarAgendamentosPorMes(Month mes) throws ExceptionDAO{
        
        ManipulaData datas = new ManipulaData();
        
        
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, ID_CLIENTE, TOTAL, DESCONTO, VALORADICIONAL, PAGO, FORMADEPAGAMENTO FROM AGENDAMENTO"
                + " INNER JOIN CLIENTE ON CLIENTE.ID = AGENDAMENTO.ID_CLIENTE"
                + " WHERE DATA BETWEEN " + datas.inicioDoMes(LocalDate.now(), mes) + " AND " + datas.fimDoMes(LocalDate.now(), mes) 
                +" AND REALIZADO = TRUE AND CLIENTE.EXCLUIDO = FALSE ORDER BY DATA DESC";
        
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
                    ag.setTotal(rs.getLong("TOTAL"));
                    ag.setDesconto(rs.getLong("DESCONTO"));
                    ag.setValorAdicional(rs.getLong("VALORADICIONAL"));
                    ag.setPago(rs.getBoolean("PAGO"));
                    ag.setFormaDePagamento(rs.getString("FORMADEPAGAMENTO"));
                        ag.setProdutosComprados(buscaProdutosCompradosAgendamento(ag.getIdAgendamento()));
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
             
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, ID_CLIENTE, CLIENTE.NOME, TOTAL, DESCONTO, VALORADICIONAL,"
                + " PAGO, FORMADEPAGAMENTO, CLIENTE.NOME  ||' '|| CLIENTE.SOBRENOME AS NOMECOMPLETO FROM AGENDAMENTO"
        + " INNER JOIN CLIENTE ON AGENDAMENTO.ID_CLIENTE = CLIENTE.ID AND NOMECOMPLETO LIKE'%" + nome +"%'"
                + " WHERE CLIENTE.EXCLUIDO = FALSE";
        
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
                    ag.setTotal(rs.getLong("TOTAL"));
                    ag.setDesconto(rs.getLong("DESCONTO"));
                    ag.setValorAdicional(rs.getLong("VALORADICIONAL"));
                    ag.setPago(rs.getBoolean("PAGO"));
                    ag.setFormaDePagamento(rs.getString("FORMADEPAGAMENTO"));
                        ag.setProdutosComprados(buscaProdutosCompradosAgendamento(ag.getIdAgendamento()));
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
      
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, ID_CLIENTE, TOTAL, DESCONTO, VALORADICIONAL"
                + ", PAGO, FORMADEPAGAMENTO FROM AGENDAMENTO"
                + " INNER JOIN CLIENTE ON CLIENTE.ID = AGENDAMENTO.ID_CLIENTE"
                + " WHERE DATA BETWEEN " + datas.meiaNoite(data) + " AND " + datas.meiaNoiteAmanha(data) +" "
                + " AND CLIENTE.EXCLUIDO = FALSE ORDER BY HORARIO ASC";
        
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
                    ag.setTotal(rs.getLong("TOTAL"));
                    ag.setDesconto(rs.getLong("DESCONTO"));
                    ag.setValorAdicional(rs.getLong("VALORADICIONAL"));
                    ag.setPago(rs.getBoolean("PAGO"));
                    ag.setFormaDePagamento(rs.getString("FORMADEPAGAMENTO"));
           ag.setProdutosComprados(buscaProdutosCompradosAgendamento(ag.getIdAgendamento()));
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
      
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO,FIMAGENDAMENTO, REALIZADO, ID_CLIENTE, TOTAL, DESCONTO, VALORADICIONAL,"
                + "PAGO, FORMADEPAGAMENTO FROM AGENDAMENTO"
                + " INNER JOIN CLIENTE ON CLIENTE.ID = AGENDAMENTO.ID_CLIENTE"
                + " WHERE REALIZADO = TRUE AND DATA BETWEEN " + datas.meiaNoite(data) + " AND " + datas.meiaNoiteAmanha(data)  
                + " AND CLIENTE.EXCLUIDO = FALSE ORDER BY HORARIO ASC";
        
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
                    ag.setTotal(rs.getLong("TOTAL"));
                    ag.setValorAdicional(rs.getLong("VALORADICIONAL"));
                    ag.setDesconto(rs.getLong("DESCONTO"));
                    ag.setPago(rs.getBoolean("PAGO"));
                    ag.setFormaDePagamento(rs.getString("FORMADEPAGAMENTO"));
                    ag.setFimAgendamento(rs.getTime("FIMAGENDAMENTO").toLocalTime());
                    

                    ag.setServicos(sc.buscarServicoPeloAgendamento(rs.getLong("ID_AGENDAMENTO")));
                        ag.setProdutosComprados(buscaProdutosCompradosAgendamento(ag.getIdAgendamento()));
         
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
                 + " REALIZADO = ? , DESCONTO = ? , TOTAL = ?, VALORADICIONAL = ?, PAGO = ?, FORMADEPAGAMENTO = ? WHERE ID_AGENDAMENTO = ?";
         
        String deletaServicoAgendamentoAntigo = "DELETE FROM AGENDAMENTO_SERVICO WHERE ID_AGENDAMENTO = ?";
        String insertServicoAgendamento = "INSERT INTO AGENDAMENTO_SERVICO (ID_AGENDAMENTO, ID_SERVICO) "
                + "VALUES (?, ?)";

 
        Connection connection = null;
        PreparedStatement pStatement = null;

        try {

            connection = new ConnectionMVC().getConnection();
            connection.setAutoCommit(false);

            pStatement = connection.prepareStatement(insertAgendamento);
            pStatement.setLong(10, agendamento.getIdAgendamento());
            pStatement.setDate(1, java.sql.Date.valueOf(agendamento.getData()));
            pStatement.setTime(2, java.sql.Time.valueOf(agendamento.getHorario()));
            pStatement.setLong(3, agendamento.getIdCliente());
            pStatement.setBoolean(4, agendamento.getRealizado() ); 
            pStatement.setLong(5, agendamento.getDesconto());
            pStatement.setLong(6, agendamento.getTotal());
            pStatement.setLong(7, agendamento.getValorAdicional());
            pStatement.setBoolean(8, agendamento.isPago());
            pStatement.setString(9, agendamento.getFormaDePagamento());
            int firstInsert = pStatement.executeUpdate();

            if (firstInsert > 0) {
                
                pStatement = connection.prepareStatement(deletaServicoAgendamentoAntigo);
                pStatement.setLong(1, agendamento.getIdAgendamento());
                pStatement.executeUpdate();
                
                try {

                    List<Servico> servicos = agendamento.getServicos();

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
        
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, ID_CLIENTE, TOTAL, DESCONTO, VALORADICIONAL,"
                + "PAGO, FORMADEPAGAMENTO FROM AGENDAMENTO WHERE REALIZADO = FALSE ORDER BY DATA DESC ";
        
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
                    ag.setTotal(rs.getLong("TOTAL"));
                    ag.setDesconto(rs.getLong("DESCONTO"));
                    ag.setValorAdicional(rs.getLong("VALORADICIONAL"));
                    ag.setPago(rs.getBoolean("PAGO"));
                    ag.setFormaDePagamento(rs.getString("FORMADEPAGAMENTO"));
                        ag.setProdutosComprados(buscaProdutosCompradosAgendamento(ag.getIdAgendamento()));
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
    /**
     * Passivel de exclusão. Retorna soma de agendamentos mensal apenas do mes atual
     * @return 
     */
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
     
    public long retornaSomaDeAgendamentosMensal(Month mes){
        
         String sql = "SELECT (SUM(AGENDAMENTO.TOTAL)) AS RENDAMENSAL FROM AGENDAMENTO "
                 + " WHERE AGENDAMENTO.DATA BETWEEN ? AND ? AND AGENDAMENTO.REALIZADO = TRUE";
        long agendamentos = 0;
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
    
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
        
            pStatement.setLong(1, new ManipulaData().inicioDoMes(LocalDate.now(),mes));
            pStatement.setLong(2, new ManipulaData().fimDoMes(LocalDate.now(), mes));

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

    public boolean excluirAgendamento(Agendamento agendamento)throws ExceptionDAO {
        
        String sql = "DELETE FROM AGENDAMENTO WHERE ID_AGENDAMENTO = ?";
        String sql2 = "DELETE FROM AGENDAMENTO_SERVICO WHERE ID_AGENDAMENTO = ?";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
    
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql2);
            pStatement.setLong(1, agendamento.getIdAgendamento());
            pStatement.executeUpdate();
            
            pStatement = connection.prepareStatement(sql);     
            pStatement.setLong(1, agendamento.getIdAgendamento());
    
            return pStatement.executeUpdate() == 1;
       
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro DAO " + e);
   
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
        return false;
    }

    public ArrayList<Agendamento> listarAgendamentosNaoPagos() {
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, ID_CLIENTE, TOTAL, DESCONTO, VALORADICIONAL,"
                + "PAGO, FORMADEPAGAMENTO FROM AGENDAMENTO WHERE PAGO = FALSE ORDER BY DATA DESC";
        
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
                    ag.setTotal(rs.getLong("TOTAL"));
                    ag.setDesconto(rs.getLong("DESCONTO"));
                    ag.setValorAdicional(rs.getLong("VALORADICIONAL"));
                    ag.setPago(rs.getBoolean("PAGO"));
                    ag.setFormaDePagamento(rs.getString("FORMADEPAGAMENTO"));
                        ag.setProdutosComprados(buscaProdutosCompradosAgendamento(ag.getIdAgendamento()));
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

    public List<Agendamento> listarAgendamentosIDCliente(long idCliente) {
         String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, ID_CLIENTE, CLIENTE.NOME, TOTAL, DESCONTO, VALORADICIONAL,"
                + " PAGO, FORMADEPAGAMENTO, CLIENTE.NOME  ||' '|| CLIENTE.SOBRENOME AS NOMECOMPLETO FROM AGENDAMENTO"
        + " INNER JOIN CLIENTE ON AGENDAMENTO.ID_CLIENTE = CLIENTE.ID "
                + " WHERE CLIENTE.EXCLUIDO = FALSE AND CLIENTE.ID = ? ORDER BY DATA DESC";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        ArrayList<Agendamento> agendamentos = null;

        
        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, idCliente);
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
                    ag.setTotal(rs.getLong("TOTAL"));
                    try {
                        ag.setServicos(listaServicosAgendamento(ag.getIdAgendamento()));
                    } catch (ExceptionDAO e) {
                    }
                   
                    ag.setDesconto(rs.getLong("DESCONTO"));
                    ag.setValorAdicional(rs.getLong("VALORADICIONAL"));
                    ag.setPago(rs.getBoolean("PAGO"));
                    ag.setFormaDePagamento(rs.getString("FORMADEPAGAMENTO"));
                        ag.setProdutosComprados(buscaProdutosCompradosAgendamento(ag.getIdAgendamento()));
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
    
    public List<Agendamento> listarAgendamentosPorData(LocalDate inicio, LocalDate fim) {
      
        
        ManipulaData datas = new ManipulaData();
            
        String sql = "SELECT ID_AGENDAMENTO, DATA, HORARIO, REALIZADO, ID_CLIENTE, TOTAL, DESCONTO, VALORADICIONAL, PAGO, FORMADEPAGAMENTO  FROM AGENDAMENTO"
                + " INNER JOIN CLIENTE ON CLIENTE.ID = AGENDAMENTO.ID_CLIENTE "
                + " WHERE DATA BETWEEN " + datas.meiaNoite(inicio) + " AND " + datas.meiaNoite(fim) 
                + " AND CLIENTE.EXCLUIDO = FALSE ORDER BY DATA ASC ";
        
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
                    ag.setTotal(rs.getLong("TOTAL"));
                    ag.setDesconto(rs.getLong("DESCONTO"));
                    ag.setValorAdicional(rs.getLong("VALORADICIONAL"));
                    ag.setPago(rs.getBoolean("PAGO"));
                    ag.setFormaDePagamento(rs.getString("FORMADEPAGAMENTO"));
                        ag.setProdutosComprados(buscaProdutosCompradosAgendamento(ag.getIdAgendamento()));
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
