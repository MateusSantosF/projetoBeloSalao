/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.Ferramentas.Valida;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Produto;
import BeutifulSalon.model.Servico;
import BeutifulSalon.view.Edicao.EditarServico;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class ServicoController {

    public List<Servico> listarServicos() {

        try {
            return new Servico().listarServicos();
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Serviços realizados" + e);
        }
        return null;
    }

    public ArrayList<Servico> listarServicos(String nome) {
        try {
            return new Servico().listarServicos(nome);
        } catch (ExceptionDAO e) {
        }
       return null;
    }

    public Servico buscarServico(long idServicoBuscado) {

        try {
            return new Servico().buscarServico(idServicoBuscado);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscarServico" + e);
        }
        return null;
    }

    public ArrayList<Servico> buscarServicoPeloAgendamento(long idAgendamento) {

        try {
            return new Servico().buscarServicoPeloAgendamento(idAgendamento);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
    
    public boolean cadastrarServico(Servico servico) {
        try {
            servico.cadastrarServico(servico);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    
    public boolean cadastrarServico(String nome, String preco, String tempoGasto, ArrayList<Produto> produtos) throws SQLException {

        if (nome.length() > 0 && preco.length() > 0 && Valida.isHora(tempoGasto)) {

            DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime horario = LocalTime.parse(tempoGasto, formatterHora);

            Servico servico = new Servico();
            servico.setNome(nome);
            servico.setPreco(Dinheiro.parseCent(Dinheiro.retiraCaracteres(preco)));
            servico.setTempoGasto(horario);
            servico.setProdutos(produtos);

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

    public List<Servico> listaOsCincoServicosMaisRealizados() {

        try {
            return new Servico().listarOsCincoServicosMaisRealizados();
        } catch (ExceptionDAO e) {

        }
        return null;
    }

    public List<Servico> listarServicosDeAgendamentoPorCliente(long id) {
        try {
            return new Servico().listarServicosDeAgendamentoPorCliente(id);
        } catch (ExceptionDAO e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Servico> selecionaServicosDoAno(int anoReferente) {

        try {
            return new Servico().selecionaServicosDoAno(anoReferente);
        } catch (ExceptionDAO e) {
            System.out.println("erro ao listar serviços do ano");
        }

        return null;

    }

    public List<Servico> listarServicosRealizadosAno() {

        try {
            return new Servico().listarServicosRealizadosAno();
        } catch (ExceptionDAO e) {
            System.out.println("Erro ao listar serviços anual");
        }

        return null;
    }

    public boolean editarServico(long id) {
        
        
        Servico servico = buscarServico(id);
        
        if(servico != null){
            
            new EditarServico(servico).setVisible(true);
            return true;
        }
        
        return false;
    }

    public boolean atualizarServico(long idServico, String nomeServico, String preco, String tempoGasto, ArrayList<Produto> Produtos) {
        
        if(nomeServico.length() > 0 && preco.length() > 0 && Valida.isHora(tempoGasto)){
            
            
            DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime horario = LocalTime.parse(tempoGasto, formatterHora);
            Servico servico = new Servico();
            
            servico.setId(idServico);
            servico.setNome(nomeServico);
            servico.setPreco(Dinheiro.parseCent(Dinheiro.retiraCaracteres(preco)));
            servico.setTempoGasto(horario);
            servico.setProdutos(Produtos);
            
            try {
                servico.atualizarServico(servico);
               
            } catch (ExceptionDAO e) {
                System.out.println("Erro ao atualizar servico" + e);
                return false;
            }
            return true;
        }else{
            
            return false;
        }

    }

}
