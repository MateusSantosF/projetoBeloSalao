/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.Ferramentas.Valida;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Compra;
import BeutifulSalon.model.Item;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class CompraController {

    public boolean RegistraCompra(LocalDate data, long valorDesconto, ArrayList<Item> itensCompra) {

        if (!itensCompra.isEmpty()) {

            Compra compraAtual = new Compra();

            compraAtual.setValorDesconto(valorDesconto);
            compraAtual.setData(data);
            compraAtual.setItensCompra(itensCompra);
            
            if(compraAtual.getValorTotal() - valorDesconto < 0){
                JOptionPane.showMessageDialog(null, "Você não pode registrar uma Compra com valor negativo.");
                return false;
            }

            try {
                compraAtual.cadastraCompra(compraAtual);
            } catch (ExceptionDAO e) {
                JOptionPane.showMessageDialog(null, "Erro ao criar objeto Compra " + e);
                return false;
            }

        } else {
            return false;
        }

        return true;
    }

    public boolean atualizarCompra(Compra c, List<Item> itensAntigos) {

        if ((c.getValorTotal() - c.getValorDesconto()) >= 0) {
            try {
                return c.atualizarCompra(c, itensAntigos);
                
            } catch (ExceptionDAO e) {
                System.out.println(e);
                return false;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Você não pode registrar uma compra com total negativo.");
            return false;
        }
    }

    public long retornaSomaDeComprasMensais(Month mes) {
        try {
            return new Compra().retornaSomaDeComprasMensais(mes);
        } catch (ExceptionDAO e) {
        }

        return 0L;
    }

    public List<Compra> retornaTodasCompras() {
        try {
            return new Compra().retornaTodasCompras();
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar compras " + e);
            return null;
        }
    }

    public List<Compra> retornaComprasDashboard() {
        try {
            return new Compra().retornaComprasDashboard();
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar compras " + e);
            return null;
        }
    }

    public List<Compra> getComprasPorNomeProduto(String nomeProduto) {
        try {
            return new Compra().getComprasPorNomeProduto(nomeProduto);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar compras por nome produto " + e);
            return null;
        }
    }

    public List<Compra> retornaTodasComprasDoAno() {
        try {
            return new Compra().retornaTodasComprasDoAno();
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar compras do ano" + e);
            return null;
        }
    }

    public List<Compra> getComprasPorNomeProdutoDoAno(String nomeProduto) {
        try {
            return new Compra().getComprasPorNomeProdutoDoAno(nomeProduto);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar compras por nome produto do ano" + e);
            return null;
        }
    }

    public boolean excluiCompra(Compra compra) {
        try {
            return new Compra().excluirCompra(compra);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir compra" + e);
            return false;
        }

    }

}
