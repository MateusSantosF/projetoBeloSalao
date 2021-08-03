/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.dao.OrcamentoServicoDAO;
import BeutifulSalon.model.Orcamento;
import BeutifulSalon.model.OrcamentoServico;
import BeutifulSalon.model.Servico;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class OrcamentoController {
    
     public boolean CadastrarOrcamento(boolean previsto, String nome, long jan, long fev, long mar, 
            long abr, long mai, long jun, long jul, long ago, long set, long out, long nov, long dez, String ano) {
         
        if( nome.length() > 0 && !nome.equals("Ex: Conta de água") && jan >= 0 && 
                fev >= 0 && mar >= 0 && abr >= 0 && mai >= 0 && jun >= 0 && jul >= 0 && ago >= 0 && set >= 0 && 
                out >= 0 && nov >= 0 && dez >= 0 && ano != null){
              
            Orcamento orc = new Orcamento(previsto, nome, jan, fev, mar, abr, mai, jun, jul, ago, set, out, nov, dez,ano);
            
            try {
                orc.cadastrarOrcamento(orc);
            } catch (ExceptionDAO e) {
                JOptionPane.showMessageDialog(null, "Erro ao criar objeto orçamento" + e);
                return false;
            }
            
        }else{
            return false;
        }
        
        return true;
         
    }
     
    public boolean cadastraOrcamentoServico(boolean previsto, String nomeServico, long Idservico, long jan, long fev, long mar, 
            long abr, long mai, long jun, long jul, long ago, long set, long out, long nov, long dez, String ano){
        
        if(ano.length() > 0 && nomeServico.length() > 0 && Idservico > 0){
            
            try {
                OrcamentoServico orcamentoServico; 
                orcamentoServico = new OrcamentoServico(previsto, 
                    nomeServico, Idservico, jan,fev,mar, abr,mai,jun,jul,ago,set,out,nov,dez,ano);
                
                orcamentoServico.cadastrarOrcamentoServico(orcamentoServico);
                
            
            } catch (ExceptionDAO e) {
                
                JOptionPane.showMessageDialog(null, "Erro OrcController " + e);
                return false;
            }
          
            
            
        }else{
            return false;
        }
        
        return true;
    }
     
    public boolean excluirOrcamento(long id_orcamento){
        
        try {
            new Orcamento().excluirOrcamento(id_orcamento);
                 
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir orçamento " + e);
            return false;
        }
        
        return true;
    }
     
    public ArrayList<Orcamento> listarOrcamentos() throws ExceptionDAO{
        return new Orcamento().listarOrcamentos();
    }
     
    public ArrayList<Orcamento> listarOrcamentos(String ano) throws ExceptionDAO{
        return new Orcamento().listarOrcamentos(ano);
    }
    
    public long somarOrcamento(String mes, String ano) throws ExceptionDAO{
        return new Orcamento().somarOrcamento(mes, ano);
    }
    
    public boolean AtualizarOrcamento(boolean previsto, String nome, long jan, long fev, long mar, 
            long abr, long mai, long jun, long jul, long ago, long set, long out, long nov, long dez, String ano, long id) {
         
        if( nome.length() > 0){
            Orcamento orc = new Orcamento(previsto, nome, jan, fev, mar, abr, mai, jun, jul, ago, set, out, nov, dez,ano, id);
            
            try {
                orc.AtualizarOrcamento(orc);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao criar objeto orçamento" + e);
                return false;
            }
            
        }
        
        return true;
         
    }
    
     public boolean editarOrcamento(long id_orcamento) {
            try {
             
            new Orcamento().editarOrcamento(id_orcamento);
            
        } catch (ExceptionDAO e) {      
            JOptionPane.showMessageDialog(null, "Erro ao criar objeto orçamento" + e);
            return false;
        }
        
        return true;
    }
    
    // ORÇAMENTO SERVICO
    
    
    public ArrayList<OrcamentoServico> listarOrcamentosServico() throws ExceptionDAO{
        return new OrcamentoServico().listarOrcamentosServico();
    }
    
     public ArrayList<OrcamentoServico> listarOrcamentosServico(String ano) throws ExceptionDAO{
        return new OrcamentoServico().listarOrcamentosServico(ano);
    }
       
    public long somaTotalGanhoServicoMensal(long inicio, long fim, long idServico) throws ExceptionDAO{
        return new OrcamentoServico().somaTotalGanhoServicoMensal(inicio, fim, idServico);
    }
    public Servico listarOrcamentoServicorRealizado(LocalDate ano, Month mes, long idServico) {
        return new OrcamentoServico().listarOrcamentoServicorRealizado(ano, mes, idServico);
    }

    
   

   
    
   
}
