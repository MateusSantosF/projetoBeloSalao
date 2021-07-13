/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Servico;
import java.util.ArrayList;
/**
 *
 * @author mateus
 */
public class ServicoController {
    
    
    public ArrayList<Servico> listarServicos() throws ExceptionDAO{
        return new Servico().listarServicos();
    }
    
    public ArrayList<Servico> listarServicos(String nome) throws ExceptionDAO{
        return new Servico().listarServicos(nome);
    }

    public Servico buscarServico(long idServicoBuscado) throws ExceptionDAO {
        
        return new Servico().buscarServico(idServicoBuscado);
    }
}
