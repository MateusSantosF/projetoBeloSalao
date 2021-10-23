/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import javax.swing.JPanel;

/**
 *
 * @author Mateus
 */
public class GerenciadorJPanel {
    
    private JPanel container;
    private JPanel content;
    
    
    public GerenciadorJPanel(JPanel container, JPanel content) {
      
        this.container = container;
        this.content = content;  
        this.container.removeAll();    
        this.container.revalidate();
        this.container.repaint();
        this.container.add(content);   
        this.container.revalidate();
        this.container.repaint();
         
        
    }
}
