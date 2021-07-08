package BeutifulSalon.controller;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Produto;
import BeutifulSalon.view.EditarProduto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            //Convertendo datas de String para Date

            try {
                Date dataVal = format.parse(dataValidade);
                Date dataRegistro = format.parse(dataReg);
                Produto produto = new Produto(nome, marca, preco, dataVal, dataRegistro);
                produto.cadastrarProduto(produto);

            } catch (ParseException ex) {
                Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }

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
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
 
            try {
                Date dataVal = format.parse(dataValidade);
                Produto produto = new Produto(nome, marca, preco, dataVal, idProduto);
                
                produto.atualizarProduto(produto);

            } catch (ParseException ex ) {
                Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }

        } else {
            return false;
        }
        return true;

    }
}