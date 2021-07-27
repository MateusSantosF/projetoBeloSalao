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
public class testes {
    public static void main(String[] args){
        
        
        LocalDate hoje = LocalDate.now();
        LocalTime hoje2 = LocalTime.MIDNIGHT;
        
            
        LocalDateTime teste = LocalDateTime.of(hoje, hoje2);
        LocalDateTime teste2 = teste.plusDays(1);
        System.out.println(teste.toLocalDate().toEpochDay() * 86400000);
        System.out.println(teste2.toLocalDate().toEpochDay() * 86400000);
    }
}
