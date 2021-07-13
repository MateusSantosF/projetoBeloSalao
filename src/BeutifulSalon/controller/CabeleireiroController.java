/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Cabeleireiro;

/**
 *
 * @author mateus
 */
public class CabeleireiroController {
    
    
    public Cabeleireiro selecionaCabeleireiro() throws ExceptionDAO{       
       return  new Cabeleireiro().selecionaCabeleireiro();
    }
    
}
