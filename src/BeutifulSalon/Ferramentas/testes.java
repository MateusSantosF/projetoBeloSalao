/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;





/**
 *
 * @author mateus
 */
public class testes {

    public static void main(String[] args) {
       
      String nomeCompletoDoArquivo = "/home/gustavo/arquivo de imagem.jpg";
      
       String extensao = nomeCompletoDoArquivo.substring(nomeCompletoDoArquivo.lastIndexOf("."));
       String nome = nomeCompletoDoArquivo.substring(nomeCompletoDoArquivo.lastIndexOf("/") + 1, nomeCompletoDoArquivo.lastIndexOf("."));
       
        System.out.println(nome+extensao);
        System.out.println(extensao);
    }
}
