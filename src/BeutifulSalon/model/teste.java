/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author mateus
 */

public class teste {
    public static void main(String[] args) {
       BigDecimal dg =  new BigDecimal("3,12".replaceAll("[^0-9]","")).divide(new BigDecimal(Math.pow(10, 2)));
       
       long teste = dg.multiply(new BigDecimal(100)).longValue();
       System.out.println(dg.multiply(new BigDecimal(100)).longValue());
       
       BigDecimal convertido = new BigDecimal(teste).divide(new BigDecimal(100));
        System.out.println((convertido));
               
    }
               
}
