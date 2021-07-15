package BeutifulSalon.controller;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Produto;
import BeutifulSalon.view.Edicao.EditarProduto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
//import java.sql.Date;

/**
 *
 * @author Melissa
 */

//CONTROL

public class ProdutoController {
    public boolean cadastrarProduto(String nome, String marca, long preco, String dataValidade, String dataReg) throws ExceptionDAO{
        
        if (nome != null && nome.length() > 0 && marca != null && marca.length() > 0) {
            
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/M/uuuu");
     
            //Convertendo datas de String para Date
            LocalDate dataVal = LocalDate.parse(dataValidade, formatterData);
            LocalDate dataRegistro = LocalDate.parse(dataReg, formatterData);
            
            Produto produto = new Produto(nome, marca, preco, dataVal, dataRegistro);
            produto.cadastrarProduto(produto);

        } else {
            return false;
        }
        
        
        return true;
    }
    
    
    
    public ArrayList<Produto> listarProdutos(String nome) throws ExceptionDAO{
        return new Produto().listarProdutos(nome);
    }
    public ArrayList<Produto> listarProdutos() throws ExceptionDAO{
        return new Produto().listarProdutos();
    }
    
    public Produto buscarProduto(long id) throws ExceptionDAO{
        return new Produto().buscarProduto(id);
    }

    public boolean excluirProduto(long idProdutoSelecionado) {
         try {
            Produto p = new Produto();
            p.excluirProduto(idProdutoSelecionado);
          
        } catch (Exception e) {
            JOptionPane.showInputDialog(null, "Erro Controller, excluir produto");
            return false;
        }
        
          return true;

    }
    
     public boolean editarProduto(long id_produto){
        
        try {
            Produto p = new Produto();
            Produto produtoEditado;
            
            produtoEditado = p.editarProduto(id_produto);
            
            if(produtoEditado!= null){
               new EditarProduto(produtoEditado).setVisible(true); 
            }else{
                return false;
            }
            
          
        } catch (Exception e) {      
            JOptionPane.showMessageDialog(null, "Erro ao criar objeto cliente" + e);
            return false;
        }
        
        return true;
    }

    public boolean AtualizarProduto(String nome, String marca, long preco, String dataValidade, long idProduto) throws ExceptionDAO {
       
        if (nome != null && nome.length() > 0 && marca != null && marca.length() > 0 && preco > 0) {
            
            //Convertendo datas de String para Date
          
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/M/uuuu");
 
            LocalDate dataVal = LocalDate.parse(dataValidade, formatterData);
            Produto produto = new Produto(nome, marca, preco, dataVal, idProduto);
            produto.atualizarProduto(produto);

        } else {
            return false;
        }
        return true;

    }
}