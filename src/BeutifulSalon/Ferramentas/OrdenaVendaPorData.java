/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.model.Venda;
import java.util.Comparator;

/**
 *
 * @author Mateus
 */
public class OrdenaVendaPorData implements Comparator<Venda> {
    
    @Override
    public int compare(Venda o1, Venda o2) {
        return o1.getData().compareTo(o2.getData());
    }
    
}
