/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Mateus
 */
public class ManipulaImagem {

    public ImageIcon redimensionaImg(byte []img) throws IOException {
      int new_w = 320;
      int new_h = 240;
        
      try {
     
            BufferedImage imagem = ImageIO.read(new DataInputStream(new ByteArrayInputStream(img)));
            BufferedImage new_img = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
           

            Graphics2D g = new_img.createGraphics();
            g.drawImage(imagem, 0, 0, new_w, new_h, null);
            g.dispose();

            return new ImageIcon(new_img);
           
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } 
      
    }
}