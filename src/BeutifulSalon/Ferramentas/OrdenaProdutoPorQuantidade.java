/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.model.Produto;
import BeutifulSalon.model.Servico;
import java.util.Comparator;

/**
 *
 * @author Mateus
 */
public class OrdenaProdutoPorQuantidade implements Comparator<Produto>{


    @Override
    public int compare(Produto o1, Produto o2) {
        
        if(o1.getRendimento() > o2.getRendimento()){
            return 1;
        }
        return 0;
    }
}
