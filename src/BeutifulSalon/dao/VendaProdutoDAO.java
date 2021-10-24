/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.dao;

import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.controller.EstoqueController;
import BeutifulSalon.model.Compra;
import BeutifulSalon.model.Item;
import BeutifulSalon.model.RelatorioVenda;
import BeutifulSalon.model.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mateu
 */
public class VendaProdutoDAO {

    public void cadastrarVenda(Venda venda) {
        
        
        String insertCompra = "INSERT INTO VENDA (DATA, VALORTOTAL, VALORDESCONTO, ID_CLIENTE) "
                + "VALUES (?, ?, ? , ?)";

        String insertItemCompra = "INSERT INTO ITEM_VENDA (PRECOUNITARIO, QUANTIDADE, PRECOTOTAL, ID_PRODUTO, ID_VENDA) "
                + "VALUES (?,?,?,?,(SELECT ID_VENDA  FROM VENDA ORDER BY ID_VENDA DESC LIMIT 1))";

        Connection connection = null;
        PreparedStatement pStatement = null;
        EstoqueController estoque = new EstoqueController();

        try {

            connection = new ConnectionMVC().getConnection();
            connection.setAutoCommit(false);

            pStatement = connection.prepareStatement(insertCompra);
            pStatement.setDate(1, java.sql.Date.valueOf(venda.getData()));
            pStatement.setLong(2, venda.getValorTotal());
            pStatement.setLong(3, venda.getValorDesconto());
            pStatement.setLong(4, venda.getIdCliente());

            int firstInsert = pStatement.executeUpdate();

            if (firstInsert > 0) {
                try {

                    List<Item> itens = venda.getItensCompra();
                    for (Item it : itens) {

                        pStatement = connection.prepareStatement(insertItemCompra);
                        pStatement.setLong(1, it.getPreco());
                        pStatement.setInt(2, it.getQuantidade());
                        pStatement.setLong(3, it.getPrecoTotal());
                        pStatement.setLong(4, it.getId_produto());
                        pStatement.executeUpdate();

                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro registrar itemCompra" + e);
                    connection.rollback();
                }
            }

            connection.commit();

            try {
                boolean sucesso = estoque.atualizaEstoque(venda);

                if (sucesso == false) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque");

                }
            } catch (ExceptionDAO ex) {
                Logger.getLogger(CompraProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque" + ex);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro DAO" + e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(CompraProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

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
    
    public boolean atualizarVenda(Venda venda, List<Item> itensAntigos){
         
        String deleteitemVenda = "DELETE FROM ITEM_VENDA WHERE ID_VENDA = ?";
        String updateVenda = "UPDATE VENDA SET DATA = ?, VALORTOTAL =?, VALORDESCONTO = ?, ID_CLIENTE = ? WHERE ID_VENDA = ?";  
        String insertItemVenda = "INSERT INTO ITEM_VENDA (PRECOUNITARIO, QUANTIDADE, PRECOTOTAL, ID_PRODUTO, ID_VENDA) "
                + "VALUES (?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement pStatement = null;
        EstoqueController estoque = new EstoqueController();

        try {

            connection = new ConnectionMVC().getConnection();
            connection.setAutoCommit(false);
            
            //remove itens antigos
            
            if(!new EstoqueController().atualizaEstoqueExclusaoVenda(itensAntigos))return false;
           
            pStatement = connection.prepareStatement(deleteitemVenda);
            pStatement.setLong(1, venda.getIdVenda());
            pStatement.execute();
            
            pStatement = connection.prepareStatement(updateVenda);
            pStatement.setDate(1, java.sql.Date.valueOf(venda.getData()));
            pStatement.setLong(2, venda.getValorTotal());
     
            pStatement.setLong(3, venda.getValorDesconto());
            pStatement.setLong(4, venda.getIdCliente());
            pStatement.setLong(5, venda.getIdVenda());

            int firstUpdate = pStatement.executeUpdate();

            if (firstUpdate > 0) {
                try {

                    List<Item> itens = venda.getItensCompra();
                    for (Item it : itens) {

                        pStatement = connection.prepareStatement(insertItemVenda);
                        pStatement.setLong(1, it.getPreco());
                        pStatement.setInt(2, it.getQuantidade());
                        pStatement.setLong(3, it.getPrecoTotal());
                        pStatement.setLong(4, it.getId_produto());
                        pStatement.setLong(5, venda.getIdVenda());
                        pStatement.executeUpdate();

                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro registrar itemCompra" + e);
                    connection.rollback();
                    return false;
                }
            }else{
                connection.rollback();
                new EstoqueController().atualizaEstoqueExclusaoCompra(itensAntigos);
                return false;
            }

            connection.commit();

            try {
                boolean sucesso = estoque.atualizaEstoque(venda);

                if (sucesso == false) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque");

                }
            } catch (ExceptionDAO ex) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar estoque" + ex);
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro DAO" + e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println(ex);
                return false;
            }

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
        
        return true;
    }
    
    public List<Venda> selecionaVendasDoAno(int anoReferente) throws ExceptionDAO{
        
                
        String sql = "SELECT CLIENTE.NOME AS NOME, CLIENTE.SOBRENOME AS SOBRENOME,VENDA.ID_CLIENTE, VENDA.ID_VENDA, VENDA.DATA, VENDA.VALORTOTAL, VENDA.VALORDESCONTO, PRODUTO.NOME AS NOMEPRODUTO, "
                + "SUM(ITEM_VENDA.QUANTIDADE) AS qtdVendida FROM VENDA " +
        "    INNER JOIN CLIENTE ON CLIENTE.ID = VENDA.ID_CLIENTE " +
        "    INNER JOIN ITEM_VENDA ON VENDA.ID_VENDA  = ITEM_VENDA.ID_VENDA " +
        "    INNER JOIN PRODUTO ON PRODUTO.IDPRODUTO = ITEM_VENDA.ID_PRODUTO WHERE VENDA.DATA BETWEEN ? AND ? "
                + "GROUP BY VENDA.ID_VENDA ORDER BY VENDA.DATA DESC";
       

        List<Venda> vendas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
    
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            

            long inicioDoAno = LocalDate.ofYearDay(anoReferente, 1).toEpochDay() * 24 * 60 * 60 * 1000;
            long fimDoAno = LocalDate.ofYearDay(anoReferente, 1).plusYears(1).toEpochDay() * 24 * 60 * 60 * 1000; 
            pStatement.setLong(1, inicioDoAno);
            pStatement.setLong(2, fimDoAno);

            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    Venda vendaAtual = new Venda();
                    vendaAtual.setData(rs.getDate("DATA").toLocalDate());
                    vendaAtual.setValorTotal(rs.getLong("VALORTOTAL"));
                    vendaAtual.setValorDesconto(rs.getLong("VALORDESCONTO"));
                    vendaAtual.setIdCliente(rs.getLong("ID_CLIENTE"));      
                    vendaAtual.setIdVenda(rs.getLong("ID_VENDA"));
                    vendaAtual.setNomeCliente(rs.getString("NOME"));
                    vendaAtual.setSobrenomeCliente(rs.getString("SOBRENOME"));
                    vendaAtual.setQuantidadeProdutosVendidos(rs.getInt("qtdVendida"));
                    vendaAtual.setItensVenda(retornaItemsRelatorioVenda(vendaAtual.getIdVenda()));
                    vendas.add(vendaAtual);
                }
            }
            
            return vendas;
       

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro DAO" + e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(CompraProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

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
        
        return vendas;
    }
    
     public List<Venda> selecionaTodasVendas() throws ExceptionDAO{
        
        
         
        String sql = "SELECT CLIENTE.NOME AS NOME, CLIENTE.SOBRENOME AS SOBRENOME, VENDA.DATA,VENDA.ID_CLIENTE,VENDA.ID_VENDA, VENDA.VALORDESCONTO ,VENDA.VALORTOTAL, PRODUTO.NOME AS NOMEPRODUTO, "
                + "SUM(ITEM_VENDA.QUANTIDADE) AS qtdVendida FROM VENDA " +
        "    INNER JOIN CLIENTE ON CLIENTE.ID = VENDA.ID_CLIENTE " +
        "    INNER JOIN ITEM_VENDA ON VENDA.ID_VENDA  = ITEM_VENDA.ID_VENDA " +
        "    INNER JOIN PRODUTO ON PRODUTO.IDPRODUTO = ITEM_VENDA.ID_PRODUTO "
                + "GROUP BY VENDA.ID_VENDA ORDER BY VENDA.DATA DESC";

        List<Venda> vendas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
    
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            

            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
         
                    Venda vendaAtual = new Venda();
                    vendaAtual.setData(rs.getDate("DATA").toLocalDate());
                    vendaAtual.setIdCliente(rs.getLong("ID_CLIENTE"));
                    vendaAtual.setIdVenda(rs.getLong("ID_VENDA"));
                    vendaAtual.setValorTotal(rs.getLong("VALORTOTAL"));
                    vendaAtual.setNomeCliente(rs.getString("NOME"));
                    vendaAtual.setValorDesconto(rs.getLong("VALORDESCONTO"));
                    vendaAtual.setSobrenomeCliente(rs.getString("SOBRENOME"));
                    
                    vendaAtual.setItensVenda(retornaItemsRelatorioVenda(vendaAtual.getIdVenda()));
                    vendaAtual.setQuantidadeProdutosVendidos(rs.getInt("qtdVendida"));
                    vendas.add(vendaAtual);
                }
            }
            
            return vendas;
       

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
        
        return vendas;
    }
     public List<Venda> selecionaVendasPorNomeCliente(String nomeCliente) throws ExceptionDAO{
        
        
         
        String sql = "SELECT CLIENTE.NOME ||' '|| CLIENTE.SOBRENOME AS NOMECOMPLETO,CLIENTE.NOME AS NOME, "
                + "CLIENTE.SOBRENOME AS SOBRENOME, VENDA.DATA, VENDA.ID_CLIENTE,VENDA.VALORTOTAL,VENDA.ID_VENDA, PRODUTO.NOME AS NOMEPRODUTO , VENDA.VALORDESCONTO, "
                + "SUM(ITEM_VENDA.QUANTIDADE) AS qtdVendida FROM VENDA " +
        "    INNER JOIN CLIENTE ON CLIENTE.ID = VENDA.ID_CLIENTE " +
        "    INNER JOIN ITEM_VENDA ON VENDA.ID_VENDA  = ITEM_VENDA.ID_VENDA " +
        "    INNER JOIN PRODUTO ON PRODUTO.IDPRODUTO = ITEM_VENDA.ID_PRODUTO WHERE NOMECOMPLETO  LIKE '%" + nomeCliente + "%' "
        + "GROUP BY VENDA.ID_VENDA ";

        List<Venda> vendas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
    
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            

            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
         
                    Venda vendaAtual = new Venda();
                    vendaAtual.setData(rs.getDate("DATA").toLocalDate());
                    vendaAtual.setValorTotal(rs.getLong("VALORTOTAL"));
                    vendaAtual.setIdCliente(rs.getLong("ID_CLIENTE"));
                      vendaAtual.setIdVenda(rs.getLong("ID_VENDA"));
                    vendaAtual.setValorDesconto(rs.getLong("VALORDESCONTO"));
                    vendaAtual.setNomeCliente(rs.getString("NOME"));
                    vendaAtual.setSobrenomeCliente(rs.getString("SOBRENOME"));
                    vendaAtual.setQuantidadeProdutosVendidos(rs.getInt("qtdVendida"));
                     vendaAtual.setItensVenda(retornaItemsRelatorioVenda(vendaAtual.getIdVenda()));
                    vendas.add(vendaAtual);
                }
            }
            
            return vendas;
       

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
        
        return vendas;
    }
     
     public List<Venda> selecionaVendasDoAnoPorNomeCliente(String nomeCliente) throws ExceptionDAO{
        
        
         
        String sql = "SELECT CLIENTE.NOME ||' '|| CLIENTE.SOBRENOME AS NOMECOMPLETO,CLIENTE.NOME AS NOME, "
                + "CLIENTE.SOBRENOME AS SOBRENOME, VENDA.DATA, VENDA.VALORTOTAL,VENDA.ID_CLIENTE, VENDA.ID_CLIENTE, PRODUTO.NOME AS NOMEPRODUTO , VENDA.VALORDESCONTO, "
                + "SUM(ITEM_VENDA.QUANTIDADE) AS qtdVendida FROM VENDA " +
        "    INNER JOIN CLIENTE ON CLIENTE.ID = VENDA.ID_CLIENTE " +
        "    INNER JOIN ITEM_VENDA ON VENDA.ID_VENDA  = ITEM_VENDA.ID_VENDA " +
        "    INNER JOIN PRODUTO ON PRODUTO.IDPRODUTO = ITEM_VENDA.ID_PRODUTO"
                + " WHERE (VENDA.DATA BETWEEN ? AND ?) AND (NOMECOMPLETO  LIKE '%" + nomeCliente + "%')  "
        + "GROUP BY VENDA.ID_VENDA ";

        List<Venda> vendas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
    
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            long inicioDoAno = LocalDate.ofYearDay(LocalDate.now().getYear(), 1).toEpochDay() * 24 * 60 * 60 * 1000;
            long fimDoAno = LocalDate.ofYearDay(LocalDate.now().getYear(), 1).plusYears(1).toEpochDay() * 24 * 60 * 60 * 1000; 
            pStatement.setLong(1, inicioDoAno);
            pStatement.setLong(2, fimDoAno);
  
            

            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
         
                    Venda vendaAtual = new Venda();
                    vendaAtual.setData(rs.getDate("DATA").toLocalDate());
                    vendaAtual.setValorTotal(rs.getLong("VALORTOTAL"));
                    vendaAtual.setValorDesconto(rs.getLong("VALORDESCONTO"));
                    vendaAtual.setNomeCliente(rs.getString("NOME"));
                    vendaAtual.setIdCliente(rs.getLong("ID_CLIENTE"));
                    vendaAtual.setIdVenda(rs.getLong("ID_VENDA"));
                    vendaAtual.setSobrenomeCliente(rs.getString("SOBRENOME"));
                    vendaAtual.setQuantidadeProdutosVendidos(rs.getInt("qtdVendida"));
                     vendaAtual.setItensVenda(retornaItemsRelatorioVenda(vendaAtual.getIdVenda()));
                    vendas.add(vendaAtual);
                }
            }
            
            return vendas;
       

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
        
        return vendas;
    }
     
     
    public int retornaQuantidadeDeVendasHoje(){
         
        String sql = "SELECT COUNT(VENDA.ID_VENDA) AS QTD FROM VENDA " +
        "    WHERE VENDA.DATA BETWEEN ? AND ?";
        int vendas = 0;
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
    
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            

            
            pStatement.setLong(1, new ManipulaData().meiaNoiteHoje());
            pStatement.setLong(2, new ManipulaData().MeiaNoiteAmanha());

            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    vendas = rs.getInt("QTD");
                }
            }
            
            return vendas;
       

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro DAO" + e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(CompraProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

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
        
        return vendas;
    }

    public List<Item> retornaItemsCompra(long idCliente) {
        
        String sql = "SELECT PRODUTO.NOME, PRODUTO.MARCA, ITEM_VENDA.QUANTIDADE, VENDA.DATA, PRODUTO.PRECO FROM ITEM_VENDA " 
        + "INNER JOIN PRODUTO ON PRODUTO.IDPRODUTO = ITEM_VENDA.ID_PRODUTO " 
        + "INNER JOIN VENDA ON VENDA.ID_VENDA = ITEM_VENDA.ID_VENDA "
        + "WHERE VENDA.ID_CLIENTE = ? ORDER BY VENDA.DATA DESC LIMIT 20";
        
        List<Item> items = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setLong(1, idCliente);
  

            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
   
                   Item i = new Item();
                   i.setNome(rs.getString("NOME"));
                   i.setMarca(rs.getString("MARCA"));
                   i.setQuantidade(rs.getInt("QUANTIDADE"));
                   i.setDataReg(rs.getDate("DATA").toLocalDate());
                   i.setPreco(rs.getLong("PRECO"));
                   items.add(i);
                }
            }
            
            return items;
       

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
        
        return items;
    }
    
     public List<RelatorioVenda> relatorioVendas(long dataInicio, long dataFim) {
        
        String sql = "SELECT ID_VENDA, DATA, VALORTOTAL, VALORDESCONTO, CLIENTE.NOME AS NOMECLIENTE, CLIENTE.SOBRENOME FROM VENDA " +
                     "INNER JOIN CLIENTE ON VENDA.ID_CLIENTE = CLIENTE.ID " +
                     "WHERE VENDA.DATA BETWEEN ? AND ? ORDER BY DATA DESC";
        
        ArrayList<RelatorioVenda> vendas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        try {

            connection = new ConnectionMVC().getConnection();
            
            connection.setAutoCommit(false);
            pStatement = connection.prepareStatement(sql);

            pStatement.setLong(1, dataInicio);
            pStatement.setLong(2, dataFim);
 
            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    
                   RelatorioVenda v = new RelatorioVenda();
                   v.setIdVenda(rs.getLong("ID_VENDA"));
                   v.setNomeCliente(rs.getString("NOMECLIENTE"));
                   v.setSobrenomeCliente(rs.getString("SOBRENOME"));
                   v.setTotal(rs.getLong("VALORTOTAL"));
                   v.setDesconto(rs.getLong("VALORDESCONTO"));
                   v.setData(rs.getDate("DATA").toLocalDate());
                   v.setItensVendidos(retornaItemsRelatorioVenda(v.getIdVenda()));
                   vendas.add(v);
                }
            }
            
            connection.commit();
            connection.setAutoCommit(true);
            return vendas;
       

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
        
        return vendas;
    }
     
     public List<Item> retornaItemsRelatorioVenda(long idVenda) {
        
        String sql = "SELECT PRODUTO.NOME AS NOME , PRODUTO.MARCA AS MARCA, QUANTIDADE, PRODUTO.IDPRODUTO AS IDPROD,"
                + " PRODUTO.PRECO AS PRECO, PRECOTOTAL FROM ITEM_VENDA "
                + "INNER JOIN PRODUTO ON PRODUTO.IDPRODUTO = ID_PRODUTO"
                + " WHERE ITEM_VENDA.ID_VENDA = ?";
        
        List<Item> items = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setLong(1, idVenda);
  

            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
   
            
                   Item i = new Item();
                   i.setId_produto(rs.getLong("IDPROD"));
                   i.setNome(rs.getString("NOME"));
                   i.setMarca(rs.getString("MARCA"));
                   i.setQuantidade(rs.getInt("QUANTIDADE"));
                   i.setPreco(rs.getLong("PRECO"));
                   i.setPrecoTotal(rs.getLong("PRECOTOTAL"));
                   items.add(i);
                }
            }
            
            return items;
       

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
        
        return items;
    }
    
    
    
    /**
     * Passivel de exclusão. Retorna soma de vendas do mes atual
     * @return 
     */    
    public long retornaSomaDeVendasMensal(){
         
        String sql = "SELECT SUM(VENDA.VALORTOTAL) AS RENDAMENSAL FROM VENDA WHERE VENDA.DATA BETWEEN ? AND ?";
        long vendas = 0;
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
                    vendas = rs.getInt("RENDAMENSAL");
                }
            }
            
            return vendas;
       

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
        
        return vendas;
    }
    
    /**
     * Retorna a soma de vendas mensais
     * @param mes
     * @return
     * @throws ExceptionDAO 
     */
    public long selecionaVendasPorMes(Month mes)throws ExceptionDAO {
        
   
        
        String sql2 = "SELECT (SUM(VENDA.VALORTOTAL) - SUM(VENDA.VALORDESCONTO)) AS RENDAMENSAL"
                + " FROM VENDA WHERE VENDA.DATA BETWEEN ? AND ?";
        long vendas = 0;

        //List<Venda> vendas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
        long inicio, fim;
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql2);
            

            inicio = new ManipulaData().inicioDoMes(LocalDate.now(), mes);
            fim = new ManipulaData().fimDoMes(LocalDate.now(), mes);
            
            pStatement.setLong(1, inicio);// Inicio do mes
            pStatement.setLong(2, fim); // fim

            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    
                    vendas += rs.getLong("RENDAMENSAL");
                }
            }
            
            return vendas;
       

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
        
        return vendas;

    }

    public boolean excluirVenda(Venda venda) {
        
        
        String sql = "DELETE FROM VENDA WHERE ID_VENDA = ?";
        String sql2 = "DELETE FROM ITEM_VENDA WHERE ID_VENDA = ?";
        
        Connection connection = null;
        PreparedStatement pStatement = null;
        PreparedStatement pStatement2 = null;
  
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, venda.getIdVenda());
            
             pStatement2 = connection.prepareStatement(sql2);
            pStatement2.setLong(1, venda.getIdVenda());
            
            return pStatement.executeUpdate() == 1 && pStatement2.executeUpdate() >= 1;

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
        return false;
    }

    public List<Venda> retornaComprasPorIDCliente(long idCliente) {
        String sql = "SELECT CLIENTE.NOME AS NOME, CLIENTE.SOBRENOME AS SOBRENOME, VENDA.DATA,VENDA.ID_CLIENTE,VENDA.ID_VENDA, VENDA.VALORDESCONTO ,VENDA.VALORTOTAL, PRODUTO.NOME AS NOMEPRODUTO, "
                + "SUM(ITEM_VENDA.QUANTIDADE) AS qtdVendida FROM VENDA " +
        "    INNER JOIN CLIENTE ON CLIENTE.ID = VENDA.ID_CLIENTE " +
        "    INNER JOIN ITEM_VENDA ON VENDA.ID_VENDA  = ITEM_VENDA.ID_VENDA " +
        "    INNER JOIN PRODUTO ON PRODUTO.IDPRODUTO = ITEM_VENDA.ID_PRODUTO "
                + " WHERE VENDA.ID_CLIENTE = ? GROUP BY VENDA.ID_VENDA ORDER BY VENDA.DATA DESC";

        List<Venda> vendas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pStatement = null;
        ResultSet rs = null;
    
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, idCliente);

            rs = pStatement.executeQuery();
            
            if(rs != null){
                while(rs.next()){
         
                    Venda vendaAtual = new Venda();
                    vendaAtual.setData(rs.getDate("DATA").toLocalDate());
                    vendaAtual.setIdCliente(rs.getLong("ID_CLIENTE"));
                    vendaAtual.setIdVenda(rs.getLong("ID_VENDA"));
                    vendaAtual.setValorTotal(rs.getLong("VALORTOTAL"));
                    vendaAtual.setNomeCliente(rs.getString("NOME"));
                    vendaAtual.setValorDesconto(rs.getLong("VALORDESCONTO"));
                    vendaAtual.setSobrenomeCliente(rs.getString("SOBRENOME"));
                    
                    vendaAtual.setItensVenda(retornaItemsRelatorioVenda(vendaAtual.getIdVenda()));
                    vendaAtual.setQuantidadeProdutosVendidos(rs.getInt("qtdVendida"));
                    vendas.add(vendaAtual);
                }
            }
            
            return vendas;
       

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
        
        return vendas;

    }

   
    
    
}
