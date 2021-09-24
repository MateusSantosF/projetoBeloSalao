/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.dao;

import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.controller.EstoqueController;
import BeutifulSalon.model.Item;
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

                    ArrayList<Item> itens = venda.getItensCompra();
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
    
    public List<Venda> selecionaVendasDoAno(int anoReferente) throws ExceptionDAO{
        
        
        String sql = "SELECT * FROM VENDA WHERE VENDA.DATA BETWEEN ? AND ? ORDER BY DATA ASC";

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
        
        
         
        String sql = "SELECT CLIENTE.NOME AS NOME, CLIENTE.SOBRENOME AS SOBRENOME, VENDA.DATA, VENDA.VALORTOTAL, PRODUTO.NOME AS NOMEPRODUTO "
                + "FROM VENDA " +
        "    INNER JOIN CLIENTE ON CLIENTE.ID = VENDA.ID_CLIENTE " +
        "    INNER JOIN ITEM_VENDA ON VENDA.ID_VENDA  = ITEM_VENDA.ID_VENDA " +
        "    INNER JOIN PRODUTO ON PRODUTO.IDPRODUTO = ITEM_VENDA.ID_PRODUTO ORDER BY VENDA.DATA DESC LIMIT 300";

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
                    vendaAtual.setNomeCliente(rs.getString("NOME"));
                    vendaAtual.setSobrenomeCliente(rs.getString("SOBRENOME"));
                    vendaAtual.setNomeProduto(rs.getString("NOMEPRODUTO"));
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
        
        
        String sql = "SELECT CLIENTE.NOME AS NOME, CLIENTE.SOBRENOME AS SOBRENOME, VENDA.DATA, VENDA.VALORTOTAL, PRODUTO.NOME AS NOMEPRODUTO "
                + "FROM VENDA " +
        "    INNER JOIN CLIENTE ON CLIENTE.ID = VENDA.ID_CLIENTE " +
        "    INNER JOIN ITEM_VENDA ON VENDA.ID_VENDA  = ITEM_VENDA.ID_VENDA " +
        "    INNER JOIN PRODUTO ON PRODUTO.IDPRODUTO = ITEM_VENDA.ID_PRODUTO"
        + "  WHERE VENDA.DATA BETWEEN ? AND ?";
        
        String sql2 = "SELECT SUM(VENDA.VALORTOTAL) AS RENDAMENSAL FROM VENDA WHERE VENDA.DATA BETWEEN ? AND ?";
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

   
    
    
}
