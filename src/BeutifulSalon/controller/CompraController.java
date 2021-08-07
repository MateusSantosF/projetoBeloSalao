/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.Ferramentas.Valida;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Compra;
import BeutifulSalon.model.ItemCompra;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class CompraController {
    
    
     public boolean RegistraCompra(Date data, long valorDesconto, String cpfCliente, ArrayList<ItemCompra> itensCompra) throws ExceptionDAO {
         
         if(Valida.isCpf(cpfCliente)){
             
             Compra compraAtual = new Compra();
             
             compraAtual.setCpfCliente(cpfCliente);
             compraAtual.setValorDesconto(valorDesconto);
             compraAtual.setData(data);
             compraAtual.setItensCompra(itensCompra);
             
             try {
                compraAtual.cadastraCompra(compraAtual); 
             } catch (SQLException e) {
                 
                JOptionPane.showMessageDialog(null, "Erro ao criar objeto Compra " + e);
                return false;
             }
             
         }else{
             return false;
         }
         
         return true;
    }
}
