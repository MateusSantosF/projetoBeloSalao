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
   
    
    public Connection getConnection(){
        
             Connection conn = null;
          
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:BancoDeDados/beutifulsalondb.db");                  
               
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "erro: " + e);
                
            }
        
        return conn;
    }
}
