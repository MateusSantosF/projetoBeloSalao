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
    
    private String rementente;
    private String destinatario;
    private String diretorioArquivo;
    private String Titulo;
    private String Texto;
    private byte[] anexo;
    private boolean enviar;
    
    
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

    public byte[] getAnexo() {
        return anexo;
    }

    public void setAnexo(byte[] anexo) {
        this.anexo = anexo;
    }

    public boolean isEnviar() {
        return enviar;
    }

    public void setEnviar(boolean enviar) {
        this.enviar = enviar;
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
        
        if(this.getDiretorioArquivo() != null){
           String diretorio = this.getDiretorioArquivo();
            String nome =  diretorio.substring( diretorio.lastIndexOf("\\") + 1,  diretorio.lastIndexOf("."));
            String extensao =  diretorio.substring( diretorio.lastIndexOf("."));
            return nome+extensao;
        }else{
            return "Não existem arquivos anexados";
        }
        
        
       
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
    
    public void sendEmail(int tipo) throws MessagingException{
        new JavaMail(this, tipo).sendMail();
    }
    
    //gmail, outlook, hotmail?
    public String getSmtpHostMail(){

      
        String host = getRementente().substring(getRementente().lastIndexOf("@"));
        
        if(host.contains("gmail")){
            return "smtp.gmail.com";
        }else if(host.contains("outlook")){
            return "smtp.live.com";
        }else if(host.contains("hotmail")){
            return "smtp.live.com";
        }else{
            return null;
        }
    }
    
   
    public String getSmtpPortMail(){
        
        String host = getRementente().substring(getRementente().lastIndexOf("@"));
        
        if(host.contains("gmail")){
            return "587";
        }else if(host.contains("outlook")){
            return "25";
        }else if(host.contains("hotmail")){
            return "25";
        }
        
        return null;
        
    }
    
    //verifica se precisa de autenticação
    public String getSmtpAuth(){
        return "true";
    }
    
    //conexão segura
    public String smtpStarttls(){
          return "true";
    }
    
    
    
    
}
