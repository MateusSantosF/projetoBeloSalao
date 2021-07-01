package BeutifulSalon.controller;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Produto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.sql.Date;

/**
 *
 * @author Melissa
 */

//CONTROL

public class ProdutoController {
    public boolean cadastrarProduto(String nome, String marca, long preco, Date dataRegistro, Date dataValidade) throws ExceptionDAO{
        if(nome  != null && nome.length() > 0 && marca != null && marca.length() > 0){
            Produto produto = new Produto(nome, marca, preco, dataRegistro, dataValidade);
            produto.cadastrarProduto(produto);
            return true;
        }else{
            return false;
        }
       
    }
}