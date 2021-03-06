/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.controller.CabeleireiroController;
import BeutifulSalon.model.Email;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Mateus
 */
public class JavaMail {

    public static final int EMAIL_PADRAO = 0;
    public static final int EMAIL_ANIVERSARIO_ULTIMAVISITA = 1;
    public static final int EMAIL_RELATORIO = 2;

    private Email email;
    private int tipo;

    public JavaMail() {

    }

    public JavaMail(Email email, int tipo) {
        this.email = email;
        this.tipo = tipo;
    }

    public boolean sendMail() throws MessagingException {

        Properties properties = new Properties();
        CabeleireiroController cc = new CabeleireiroController();

        System.out.println("Host =>" + email.getSmtpHostMail());
        System.out.println("Porta =>" + email.getSmtpPortMail());
        System.out.println("Rementente =>" + email.getRementente());
        System.out.println("Destinatário =>" + email.getDestinatario());

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", email.getSmtpHostMail()); //linha adicionada para antivirus não dar problema
        properties.put("mail.smtp.host", email.getSmtpHostMail());
        properties.put("mail.smtp.port", email.getSmtpPortMail());
        
        String myAccountEmail;
        String password;
        
        if(tipo != EMAIL_RELATORIO){
            myAccountEmail = cc.selecionaCabeleireiro().getEmail();
            password = cc.selecionaCabeleireiro().getSenha(); 
        }else{
            myAccountEmail = "beutifulsalontest@gmail.com";
            password = "40028922@";
        }
       

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //session.setDebug(true);
        Message message = prepareMessage(session, myAccountEmail, email.getDestinatario(), email, tipo);

        try {

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            System.out.println("tranport => " + e);
            return false;
        }

    }

    private static Message prepareMessage(
            Session session,
            String myAccountEmail,
            String recepient,
            Email email,
            int tipo) {

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(myAccountEmail));

            message.setRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(recepient));

            message.setSubject(email.getTitulo());

            if (email.getDiretorioArquivo() != null && email.getDiretorioArquivo().length() > 0) {

                // Criando a parte que vai tratar a imagem
                MimeMultipart multipart = new MimeMultipart("related");

                if (tipo == EMAIL_PADRAO) {

                    // Corpo da mensagem
                    BodyPart messageBodyPart = new MimeBodyPart();

                    String htmlText = "<p>" + email.getTexto() + "</p>";

                    messageBodyPart.setContent(email.getTexto(), "text/html; charset=utf-8");

                    // Add
                    multipart.addBodyPart(messageBodyPart);

                    messageBodyPart = new MimeBodyPart();

                    DataSource file = new FileDataSource(email.getDiretorioArquivo());

                    messageBodyPart.setDataHandler(new DataHandler(file));

                    messageBodyPart.setFileName(email.getNomeDoArquivo());

                    // Add arquivo
                    multipart.addBodyPart(messageBodyPart);

                    // Juntando tudo
                    message.setContent(multipart, "charset=UTF-8");
                }

                if (tipo == EMAIL_ANIVERSARIO_ULTIMAVISITA) {

                    // Corpo da mensagem
                    BodyPart messageBodyPart = new MimeBodyPart();
                    String htmlText = "<p>" + email.getTexto() + "</p>" + "<img src='cid:image' />";

                    messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
                    // Add
                    multipart.addBodyPart(messageBodyPart);
                    messageBodyPart = new MimeBodyPart();
                    File file = null;
                    try {
                        file = File.createTempFile("temp" + LocalDate.now().toEpochDay(), ".png");
                        if (file.exists()) {

                            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                            bos.write(email.getAnexo());
                            bos.close();
                        } else {
                            System.out.println("NAO EXISTE KKKKKKKK");
                        }

                        DataSource t = new FileDataSource(file.getAbsolutePath());
                        messageBodyPart.setDataHandler(new DataHandler(t));
                        messageBodyPart.setHeader("Content-ID", "<image>");
                        messageBodyPart.setFileName(email.getNomeDoArquivo());

                    } catch (IOException e) {

                        System.out.println(e);
                    }

                    // Add arquivo
                    multipart.addBodyPart(messageBodyPart);

                    // Juntando tudo
                    message.setContent(multipart, "text/html; charset=utf-8");
                }

            } else {
              
                MimeMultipart multipart = new MimeMultipart("related");

                BodyPart messageBodyPart = new MimeBodyPart();
                String htmlText = "<p>" + email.getTexto() + "</p>";

                messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");

                multipart.addBodyPart(messageBodyPart);
                message.setContent(multipart);
            }

            if (tipo == EMAIL_RELATORIO) {

                MimeMultipart multipart = new MimeMultipart("related");
                // Corpo da mensagem
                BodyPart messageBodyPart = new MimeBodyPart();
                String htmlText = "<p>" + email.getTexto() + "</p>";

                messageBodyPart.setContent(email.getTexto(), "text/html; charset=utf-8");

                // Add 
                multipart.addBodyPart(messageBodyPart);
                // Add Arquivos

                List<String> anexos = email.getAnexos();
                for (int i = 0; i < anexos.size(); i++) {
                    try {
                        messageBodyPart = new MimeBodyPart();

                        DataSource file = new FileDataSource(anexos.get(i));
                        messageBodyPart.setDataHandler(new DataHandler(file));
                        messageBodyPart.setFileName(email.getNomeDoArquivo(i));
                        multipart.addBodyPart(messageBodyPart);
                    } catch (MessagingException e) {
                    }

                }
                // Juntando tudo
                message.setContent(multipart, "charset=UTF-8");
            }

            return message;

        } catch (MessagingException ex) {
            System.out.println("erro email " + ex);
        }

        return null;

    }

}
