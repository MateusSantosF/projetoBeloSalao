/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Orcamento;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class OrcamentoController {
    
     public boolean CadastrarOrcamento(boolean previsto, String nome, long jan, long fev, long mar, 
            long abr, long mai, long jun, long jul, long ago, long set, long out, long nov, long dez, String ano) {
         
        if( nome.length() > 0){
            Orcamento orc = new Orcamento(previsto, nome, jan, fev, mar, abr, mai, jun, jul, ago, set, out, nov, dez,ano);
            
            try {
                orc.cadastrarOrcamento(orc);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao criar objeto orçamento" + e);
                return false;
            }
            
        }
        
        return true;
         
     }
     
    public ArrayList<Orcamento> listarOrcamentos() throws ExceptionDAO{
        return new Orcamento().listarOrcamentos();
    }
     
    public ArrayList<Orcamento> listarOrcamentos(String ano) throws ExceptionDAO{
        return new Orcamento().listarOrcamentos(ano);
    }
    
    public long somarOrcamento(String mes, String ano){
        return new Orcamento().somarOrcamento(mes, ano);
    }
}
