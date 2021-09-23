/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.Ferramentas.Valida;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Compra;
import BeutifulSalon.model.Item;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class CompraController {
    
    
     public boolean RegistraCompra(LocalDate data, long valorDesconto, ArrayList<Item> itensCompra) {
         
         if(!itensCompra.isEmpty()){
             
            Compra compraAtual = new Compra();
             
     
            compraAtual.setValorDesconto(valorDesconto);
            compraAtual.setData(data);
            compraAtual.setItensCompra(itensCompra);
             
            try {
                compraAtual.cadastraCompra(compraAtual); 
            } catch (ExceptionDAO e) {          
                JOptionPane.showMessageDialog(null, "Erro ao criar objeto Compra " + e);
                return false;
            }
             
         }else{
             return false;
         }
         
         return true;
    }
}
