/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mateus
 */
public interface Observador {
    
    public void update(Object obj);
    
    public void update(Orcamento orcamento);
    
    public void update(DefaultTableModel model);
    
    public void update(String valorDesconto);
    
    public void update(Cliente cliente);
    
    public void update(Servico servico);
    
    public void update(ArrayList<LocalTime> horarios);
}