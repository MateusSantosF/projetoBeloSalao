/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author mateus
 */
public class Dinheiro {
    
    
    
    public static BigDecimal retiraCaracteres(String VALOR){
        
        //Retira todos caracteres (pontos e vírgulas) da String passada por parâmetro, e divide pelo número
        //máximo de casas decimais 
        
        //É DE SUMA IMPORTANCIA QUE TODOS OS CAMPOS MONETÁRIOS TENHAM APENAS DUAS CASAS DECIMAIS, 
        //SE NÃO ESTE MÉTODO **DEVE** SER ALTERADO
        return new BigDecimal(VALOR.replaceAll("[^0-9]","")).divide(new BigDecimal(Math.pow(10, 2)));
        

    }
    
    public static long parseCent(BigDecimal VALOR){
       
        return VALOR.multiply(new BigDecimal(100)).longValue();
    }
    
    public static String parseDecimal(long VALOR){
        
        double valorD = (double)VALOR/100;
        
        return new DecimalFormat("#,###,###.00").format(valorD);
    }
    
    public static BigDecimal parseBigDecimal(Long VALOR){
        
        return new BigDecimal(VALOR).divide(new BigDecimal(100));
    }
    
    public static String parseString(Long VALOR){
        
        if(VALOR == 0){
            return "R$ 0,00";
        }
        BigDecimal ValorConvertido = parseBigDecimal(VALOR);
        DecimalFormat df = new DecimalFormat("R$ ,#,###.00");
       
        return df.format(ValorConvertido);
    }
    
    public static String parseString(BigDecimal VALOR){
              
        BigDecimal ValorConvertido = VALOR;
        DecimalFormat df = new DecimalFormat("R$ ,#,###.00");
       
        return df.format(ValorConvertido);
    }
    
}
