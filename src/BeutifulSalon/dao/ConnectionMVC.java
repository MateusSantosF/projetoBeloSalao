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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectionMVC {
   

    private String serverName;
    private String portNumber;
    
    public Connection getConnection(){
        
             Connection conn = null;
             serverName = "localhost";
             portNumber = "3306";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro jdbc.driver: " + ex);
        }
            try {
                conn = DriverManager.getConnection(
                   "jdbc:mysql://"+
                   this.serverName +
                   ":" + this.portNumber + "/beutifulsalondb?useSSL=false","root", "Jaralin123@");
               
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro: " + e);
                
            }
        
        return conn;
    }
}
