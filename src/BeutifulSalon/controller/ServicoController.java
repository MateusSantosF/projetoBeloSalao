/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Produto;
import BeutifulSalon.model.Servico;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author mateus
 */
public class ServicoController {

    public ArrayList<Servico> listarServicos() throws ExceptionDAO {
        return new Servico().listarServicos();
    }

    public ArrayList<Servico> listarServicos(String nome) throws ExceptionDAO {
        return new Servico().listarServicos(nome);
    }

    public Servico buscarServico(long idServicoBuscado) throws ExceptionDAO {

        return new Servico().buscarServico(idServicoBuscado);
    }

    public boolean cadastrarServico(String nome, String preco, LocalTime tempoGasto, ArrayList<Produto> produto) throws SQLException {

        if (nome.length() > 0 && preco.length() > 0 && produto.isEmpty() == false) {
            Servico servico = new Servico();
            servico.setNome(nome);
            servico.setPreco(Dinheiro.parseCent(Dinheiro.retiraCaracteres(preco)));
            servico.setTempoGasto(tempoGasto);
            servico.setProdutos(produto);

            try {
                servico.cadastrarServico(servico);
            } catch (SQLException e) {
                return false;
            }

            return true;
        } else {
            return false;
        }

    }

}
