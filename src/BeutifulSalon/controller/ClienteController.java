/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.view.Edicao.EditarCliente;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class ClienteController {
    
    public boolean cadastrarCliente(String CPF, String NOME, String SOBRENOME, String EMAIL, String DATANASC, 
            String CEP, String BAIRRO, String RUA, String CIDADE,String NUMERO,
            String TELEFONE, String CELULAR, String DATAREG){
        
        if( NOME  != null && NOME.length() > 0 && validarCPF(CPF) && EMAIL != null && EMAIL.length() > 0){
           
            
            try {
                
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                //Convertendo datas de String para Date
                Date dataNasc  = format.parse(DATANASC);
                Date dataReg = format.parse(DATAREG);
               
                
                //objeto cliente
                Cliente cliente = new Cliente(CPF, NOME, SOBRENOME, EMAIL, dataNasc, CEP,
                        BAIRRO, RUA, CIDADE,NUMERO,TELEFONE, CELULAR, dataReg);
                try {       
                    //Chamando construtor de Cliente
                   cliente.cadastrarCliente(cliente);             
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao criar objeto cliente");
                    return false;
                }
                return true;
            } catch (ParseException ex) {
                //modal de erro na data de nascimento
                return false;
            }
          
        }
        return false;
    }
    
    public boolean atualizarCliente(String CPF, String NOME, String SOBRENOME, String EMAIL, String DATANASC, 
            String CEP, String BAIRRO, String RUA, String CIDADE,String NUMERO,
            String TELEFONE, String CELULAR, String DATAREG){
        
        
          if( NOME  != null && NOME.length() > 0 && validarCPF(CPF) && EMAIL != null && EMAIL.length() > 0){
           
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                //Convertendo datas de String para Date
                Date dataNasc  = format.parse(DATANASC);
                
                //objeto cliente
                Cliente cliente = new Cliente(CPF, NOME, SOBRENOME, EMAIL, dataNasc, CEP,
                        BAIRRO, RUA, CIDADE,NUMERO,TELEFONE, CELULAR);
                try {       
                    //Chamando construtor de Cliente
                   cliente.atualizarCliente(cliente);             
                } catch (ExceptionDAO ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao criar objeto cliente" + ex);
                    return false;
                }
                return true;
            } catch (ParseException ex) {
                //modal de erro na data de nascimento
                return false;
            }
          
        }else{
             return false;
        }

        
        
    }
    
    
    
    public boolean validarCPF(String cpf){
        
        for(int i = 0; i < cpf.length(); i++){
            if(! Character.isDigit(cpf.charAt(i))){
                if( ! (i == 3 || i == 7 || i == 11)){
                    JOptionPane.showMessageDialog(null, "CPF Inválido");
                    return false;
                }
              
            }    
        }
             return true;
    }
    
    
    public ArrayList<Cliente> listarClientes(String nome) throws ExceptionDAO{
        
        return new Cliente().listarClientes(nome);
    }
    
    public ArrayList<Cliente> listarClientes() throws ExceptionDAO{
        return new Cliente().listarClientes();
    }
    
    public boolean excluirCliente(String cpf){
        
        try {
            Cliente c = new Cliente();
            c.excluirCliente(cpf);
          
        } catch (Exception e) {
            JOptionPane.showInputDialog(null, "Erro Controller, excluir cliente");
            return false;
        }
        
          return true;
    }
    
    public boolean editarCliente(String cpf){
        
        try {
            Cliente c = new Cliente();
            Cliente clienteEditado;
            
            clienteEditado = c.editarCliente(cpf);
            
            if(clienteEditado != null){
               new EditarCliente(clienteEditado).setVisible(true); 
            }else{
                return false;
            }
            
          
        } catch (Exception e) {      
            JOptionPane.showMessageDialog(null, "Erro ao criar objeto cliente" + e);
            return false;
        }
        
        return true;
    }
    
    
}
