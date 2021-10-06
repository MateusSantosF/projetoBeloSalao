/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.model.Servico;
import BeutifulSalon.model.Venda;
import java.util.Comparator;

/**
 *
 * @author Mateus
 */
public class OrdenaServicoPorData implements Comparator<Servico> {
     @Override
    public int compare(Servico o1, Servico o2) {
        return o1.getDataRealizado().compareTo(o2.getDataRealizado());
    }
}
