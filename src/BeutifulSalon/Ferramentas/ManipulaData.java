/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.controller.AgendamentoController;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Agendamento;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mateus
 */
public class ManipulaData {

    public ManipulaData() {
    }
    
    
    public long meiaNoiteHoje(){
             
        LocalDate hoje = LocalDate.now();
        LocalTime meiaNoite = LocalTime.MIDNIGHT;     
        LocalDateTime diff = LocalDateTime.of(hoje, meiaNoite);
        
        long meiaNoiteMs = diff.toLocalDate().toEpochDay() * 24 * 60 * 60 * 1000;
       
        return meiaNoiteMs;
    }
    
    public long meiaNoite(LocalDate dia){
        
        LocalTime meiaNoite = LocalTime.MIDNIGHT;
        LocalDateTime diff = LocalDateTime.of(dia, meiaNoite);
        
        long meiaNoiteMs = diff.toLocalDate().toEpochDay() * 24 * 60 * 60 * 1000;
        
        return meiaNoiteMs;
    }
    
    public long meiaNoiteAmanha(LocalDate dia){
        
        LocalTime meiaNoite = LocalTime.MIDNIGHT;
        LocalDateTime diff = LocalDateTime.of(dia, meiaNoite);
        LocalDateTime meiaNoiteAmannha = diff.plusDays(1);
        long meiaNoiteMs = meiaNoiteAmannha.toLocalDate().toEpochDay() * 24 * 60 * 60 * 1000;
        
        return meiaNoiteMs;
    }
    
    public long MeiaNoiteAmanha(){
        
        LocalDate hoje = LocalDate.now();
        LocalTime meiaNoite = LocalTime.MIDNIGHT;     
        LocalDateTime diff = LocalDateTime.of(hoje, meiaNoite);
        LocalDateTime meiaNoiteAmanha = diff.plusDays(1);
        
        long meiaNoiteAmanhaMs = meiaNoiteAmanha.toLocalDate().toEpochDay() * 24 * 60 * 60 * 1000;
        
        return meiaNoiteAmanhaMs;
    }
    
    public long somaDia(LocalDateTime diaAtual, long qtdDias){
        
        LocalDateTime soma = diaAtual.plusDays(qtdDias);
   
        long somaMs = soma.toLocalDate().toEpochDay() * 24 * 60 * 60 * 1000;
        
        return somaMs;
    }
    
    
    public ArrayList<LocalTime> recuperaHorariosDisponiveis(){
       
        
        ArrayList<LocalTime> horarios = new ArrayList<>(); 
        try {
          
            
            ArrayList<Agendamento> agendamentos = new AgendamentoController().listarAgendamentos();
            
            agendamentos.forEach(a -> a.getData().getDayOfWeek());
     
        } catch (ExceptionDAO ex) {
            Logger.getLogger(ManipulaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          return horarios;
    }
}
