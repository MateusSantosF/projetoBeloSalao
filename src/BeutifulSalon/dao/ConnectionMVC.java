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
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectionMVC {

    public Connection getConnection() throws SQLException {

        Connection conn = null;

        try {
            // Usar PARA COMPILAR

            //String caminhoBanco = new File("BancoDeDados/beutifulsalondb.db").getAbsolutePath();
            //conn = DriverManager.getConnection("jdbc:sqlite:" + caminhoBanco);

            // UTILIZAR ENQUANTO NO NETBEANS
            conn = DriverManager.getConnection("jdbc:sqlite:src/BancoDeDados/beutifulsalondb.db");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "erro: " + e);
        }

        return conn;
    }

    public static void main(String[] args) throws SQLException {
        new ConnectionMVC().getConnection();
    }
}
