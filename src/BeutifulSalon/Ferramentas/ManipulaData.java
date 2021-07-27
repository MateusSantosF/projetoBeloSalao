/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
}
