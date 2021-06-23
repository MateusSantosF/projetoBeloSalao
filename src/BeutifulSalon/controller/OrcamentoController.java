/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.model.Orcamento;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class OrcamentoController {
    
     public boolean CadastrarOrcamento(boolean previsto, String nome, double jan, double fev, double mar,
            double abr, double mai, double jun, double jul, double ago, double set, double out, double nov, double dez) {
         
        if( nome.length() > 0){
            Orcamento orc = new Orcamento(previsto, nome, jan, fev, mar, abr, mai, jun, jul, ago, set, out, nov, dez);
            
            try {
                orc.cadastrarOrcamento(orc);
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao criar objeto orçamento" + e);
                return false;
            }
            
        }
        
        return false;
         
     }
}
