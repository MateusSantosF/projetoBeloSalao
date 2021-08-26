/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;





/**
 *
 * @author mateus
 */
public class testes {

    public static void main(String[] args) {

        //pegar todos orçamento servico por ano
        //pegar agendamentos por mes e verificar seus serviços 
        //pegar a quantidade mensal de um serviço e multiplicar pelo preço
       // DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LL yyyy");
       // LocalDate data = LocalDate.parse("16 de ago. de 2021", formatterData);    
        //System.out.println(data);

        LocalDate teste = LocalDate.now();
        
   
        
        LocalDate teste2 = LocalDate.ofYearDay( teste.getYear(), 1);
       
        System.out.println( teste2.plusYears(1));
    }
}
