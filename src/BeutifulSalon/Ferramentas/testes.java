/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import com.github.sarxos.webcam.Webcam;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;





/**
 *
 * @author mateus
 */
public class testes {

    public static void main(String[] args) throws IOException {
       
     Webcam webcam = Webcam.getDefault();
     webcam.open();
        ImageIO.write(webcam.getImage(), "jpg", new File("HelloWorld.jpg"));
    }
}
