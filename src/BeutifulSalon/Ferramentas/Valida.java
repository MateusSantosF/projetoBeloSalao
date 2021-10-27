/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */



public class Valida {
    
    private static final String EMAIL_PATTERN = 
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final Pattern patternEmail = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

    public static boolean isCpf(String cpf) {

        if (cpf.length() == 14) {

            String digitos = cpf.replaceAll("\\.", "").replace("-", "");
            int soma = 0;
            int peso = 0;
            int resto;
            char dig10, dig11;

            try {

                //primeiro digito
                peso = 10;
                for (int i = 0; i < 9; i++) {
                    int n = (int) (digitos.charAt(i) - 48);
                    soma += n * peso--;
                }

                resto = 11 - (soma % 11);

                if (resto == 10 || resto == 11) {
                    dig10 = '0';
                } else {
                    dig10 = (char) (resto + 48);
                }

                //segundo digito
                peso = 11;
                soma = 0;
                for (int i = 0; i < 10; i++) {
                    int n = (int) (digitos.charAt(i) - 48);
                    soma += n * peso--;
                }

                resto = 11 - (soma % 11);

                if (resto == 10 || resto == 11) {
                    dig11 = '0';
                } else {
                    dig11 = (char) (resto + 48);
                }

                //valida digitos
                if ((dig10 == digitos.charAt(9)) && (dig11 == digitos.charAt(10))) {
                    return true;
                } else {
                     JOptionPane.showMessageDialog(null, "Erro ao validar CPF");
                    return false;
                }

            } catch (InputMismatchException e) {
                JOptionPane.showMessageDialog(null, "Erro ao validar CPF " + e);
                return false;
            }

        } else {
            return false;
        }

    }
    
    public static boolean isData(String data){
        
        String dateFormat = "dd/MM/uuuu";
         
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern(dateFormat)
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate date = LocalDate.parse(data, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Erro ao validar data " + e);
            return false;
        }

    }
    
     public static boolean isDataSemNotificar(String data){
        
        String dateFormat = "dd/MM/uuuu";
         
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern(dateFormat)
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate date = LocalDate.parse(data, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
           // JOptionPane.showMessageDialog(null, "Erro ao validar data " + e);
            return false;
        }

    }
    
    public static boolean isEmail(String email){
        Matcher matcher = patternEmail.matcher(email);        
        boolean sucesso = matcher.matches();
        
        if(!sucesso){
            JOptionPane.showMessageDialog(null, "Email inválido!");
        }
        
        return sucesso;
    }
    
    public static boolean isEmailSemNotificar(String email){
        Matcher matcher = patternEmail.matcher(email);        
  
        
        return  matcher.matches();
    }
    
    public static boolean isHora(String hora){
        
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("HH:mm").withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalTime d = LocalTime.parse(hora, formatador);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Horário inválido.");
            return false;
        }
        
    }

  
}