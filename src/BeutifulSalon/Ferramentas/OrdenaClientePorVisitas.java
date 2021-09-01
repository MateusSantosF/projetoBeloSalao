/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Produto;
import java.util.Comparator;

/**
 *
 * @author Mateus
 */
public class OrdenaClientePorVisitas implements Comparator<Cliente>{

    @Override
    public int compare(Cliente o1, Cliente o2) {
        if(o1.getDeOndeConheceu() > o2.getDeOndeConheceu()){
            return 1;
        }
        
        return 0;
    }
    
}
