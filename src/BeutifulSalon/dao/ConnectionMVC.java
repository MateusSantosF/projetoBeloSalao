/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.dao;

/**
 *
 * @author Mateus
 */
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConnectionMVC {

    public Connection getConnection() throws SQLException {

        Connection conn = null;

        try {
            // recupera path de onde o .jar foi executado para indicar caminho do banco
            String path = ConnectionMVC.class.getProtectionDomain().getCodeSource().getLocation().getPath();
           String decodedPath = URLDecoder.decode(path, "UTF-8");
            String caminhoBanco = decodedPath.replace("BeutifulSalon.jar", "BancoDeDados/beutifulsalondb.db");
            conn = DriverManager.getConnection("jdbc:sqlite:" + caminhoBanco);
            
            //UTILIZAR ENQUANTO NO NETBEANS
            //conn = DriverManager.getConnection("jdbc:sqlite:src/BancoDeDados/beutifulsalondb.db");

            //} catch (SQLException e) {
            //JOptionPane.showMessageDialog(null, "erro: " + e);
            //}
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ConnectionMVC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public static void main(String[] args) throws SQLException {
        new ConnectionMVC().getConnection();
    }
}
