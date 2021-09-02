/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.model.Email;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.activation.DataHandler; //imports para imagem
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

    private Email email;

    public JavaMail(Email email) {
        this.email = email;
    }

    public void sendMail() throws MessagingException {

        System.out.println("Preparando para enviar email");

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com"); //linha adicionada para antivirus não dar problema
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "beutifulsalontest@gmail.com";
        String password = "40028922@";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, email.getDestinatario());

        Transport.send(message);
      
    }

    private Message prepareMessage(
            Session session,
            String myAccountEmail,
            String recepient) {

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(
                    Message.RecipientType.TO,
                    new InternetAddress(recepient));

            message.setSubject(email.getTitulo());
            //message.setText( "Acho que pode dar certo o teste hahahha" );

            if (email.getDiretorioArquivo() != null && email.getDiretorioArquivo().length() > 0) {
                // Criando a parte que vai tratar a imagem

                MimeMultipart multipart = new MimeMultipart("related");

                // Corpo da mensagem
                BodyPart messageBodyPart = new MimeBodyPart();
                String htmlText = "<p>" + email.getTexto() + "</p>";

                messageBodyPart.setContent(htmlText, "text/html");
                // Add
                multipart.addBodyPart(messageBodyPart);

                // Pegando a imagem
                messageBodyPart = new MimeBodyPart();

                DataSource file = new FileDataSource(email.getDiretorioArquivo());

                messageBodyPart.setDataHandler(new DataHandler(file));

                messageBodyPart.setFileName(email.getNomeDoArquivo());

                // Add arquivo
                multipart.addBodyPart(messageBodyPart);

                // Juntando tudo
                message.setContent(multipart);

            } else {
                MimeMultipart multipart = new MimeMultipart("related");
                BodyPart messageBodyPart = new MimeBodyPart();
                String htmlText = "<p>" + email.getTexto() + "</p>";

                messageBodyPart.setContent(htmlText, "text/html");

                multipart.addBodyPart(messageBodyPart);
                message.setContent(multipart);
            }

            return message;

        } catch (MessagingException ex) {
            System.out.println("erro email " + ex);
        }

        return null;

    }
}
