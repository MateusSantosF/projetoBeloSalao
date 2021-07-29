/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.Ferramentas.Valida;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Cabeleireiro;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class CabeleireiroController {
    
    
    public boolean cadastrarCabeleireiro(String nome, String cpf, String email, ArrayList<LocalTime> expediente){
        
        if(nome.length() > 0 && Valida.isCpf(cpf) && Valida.isEmail(email) && !expediente.isEmpty() && expediente.size() == 14){
             
            try {
                 Cabeleireiro cabeleireiro = new Cabeleireiro(cpf, nome, email, expediente);
                 
                 cabeleireiro.cadastrarCabeleireiro(cabeleireiro);
            } catch (ExceptionDAO e) {
                JOptionPane.showMessageDialog(null, "Controller" + e);
            }
            
        }else{
            return false;
        }
        
        return true;
    }
    
    public Cabeleireiro selecionaCabeleireiro() throws ExceptionDAO{       
       return new Cabeleireiro().selecionaCabeleireiro();
    }
    
    public ArrayList<LocalTime> selecionaExpediente(int diaDaSemana){
        return new Cabeleireiro().selecionaExpediente(diaDaSemana);
    }
    
}
