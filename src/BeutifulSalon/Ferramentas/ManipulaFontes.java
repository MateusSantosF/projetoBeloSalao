/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import javax.swing.JOptionPane;
import org.bridj.Platform;

/**
 *
 * @author Mateus
 */
public class ManipulaFontes {

    private Font font;
    public String REGULAR = "Quicksand-Regular.ttf";
    public String BOLD = "Quicksand-Bold.ttf";
    public String LIGHT = "Quicksand-Light.ttf";
    public String MEDIUM = "Quicksand-Medium.ttf";
    public String SEMIBOLD = "Quicksand-SemiBold.ttf";
    private static File file;

    public ManipulaFontes() {}

    public Font getFont(String nome, int estilo, float tamanho) {
        try {

        //Para compilar na dist
       
            //font = Font.createFont( Font.TRUETYPE_FONT,new File("Fontes\\" + nome));
        //para usar no netbeans
           font = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("\\Fontes\\" + nome));

        } catch (IOException | FontFormatException e) {
            System.out.println(e);
            font = new Font("Arial", Font.PLAIN, 14);
        }
        font = font.deriveFont(estilo, tamanho);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        return font;
    }
}
