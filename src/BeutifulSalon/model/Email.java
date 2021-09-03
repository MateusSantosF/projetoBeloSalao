/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import BeutifulSalon.Ferramentas.JavaMail;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;

/**
 *
 * @author Mateus
 */
public class Email {
    
    String rementente;
    String destinatario;
    String diretorioArquivo;
    String Titulo;
    String Texto;
    
    
    public Email(){};

    public Email(String rementente, String destinatario, String diretorioArquivo, String Titulo, String Texto) {
        this.rementente = rementente;
        this.destinatario = destinatario;
        this.diretorioArquivo = diretorioArquivo;
        this.Titulo = Titulo;
        this.Texto = Texto;
    }
    
    
    public String getRementente() {
        return rementente;
    }

    public void setRementente(String rementente) {
        this.rementente = rementente;
    }
    
    
    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getDiretorioArquivo() {
        return diretorioArquivo;
    }

    public void setDiretorioArquivo(String diretorioArquivo) {
        this.diretorioArquivo = diretorioArquivo;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }
    
    public String getNomeDoArquivo(){
        String diretorio = this.getDiretorioArquivo();
        String nome =  diretorio.substring( diretorio.lastIndexOf("\\") + 1,  diretorio.lastIndexOf("."));
        String extensao =  diretorio.substring( diretorio.lastIndexOf("."));
        
        return nome+extensao;
    }

    public String getTexto() {
        
       List<Character> novaString = new ArrayList<>();
       StringBuilder sb = new StringBuilder();
       
       for(Character c: Texto.toCharArray()){
        
           if(c.equals('\n') || c.equals('\r') || c.equals("\t")){
               sb.append("<br>");
               
           }else{
               sb.append(c);
           }
       }
      
        
        return sb.toString();
    }

    public void setTexto(String Texto) {
        this.Texto = Texto;
    }
    
    public void sendEmail() throws MessagingException{
        new JavaMail(this).sendMail();
    }
    
    
    
    
}
