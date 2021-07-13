/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mateus
 */
public interface Observador {
    
    public void update(Object obj);
    
    public void update(DefaultTableModel model);
    
    public void update(String valorDesconto);
    
    public void update(Cliente cliente);
}
