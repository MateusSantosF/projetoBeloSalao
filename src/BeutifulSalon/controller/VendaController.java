/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.Ferramentas.Valida;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.dao.VendaProdutoDAO;
import BeutifulSalon.model.Compra;
import BeutifulSalon.model.Item;
import BeutifulSalon.model.Venda;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mateu
 */
public class VendaController {

    public boolean cadastrarVenda(LocalDate data, long valorDesconto, long idCliente, List<Item> itensCompra) {

        if (validaQuantidadeProduto(itensCompra) ) {

            Venda vendaAtual = new Venda();

            vendaAtual.setIdCliente(idCliente);
            vendaAtual.setValorDesconto(valorDesconto);
            vendaAtual.setData(data);
            vendaAtual.setItensCompra(itensCompra);
            
            if(vendaAtual.getValorTotal() - valorDesconto < 0){
                JOptionPane.showMessageDialog(null, "Você não pode registrar uma venda com valor negativo.");
                return false;
            }

            try {
                vendaAtual.cadastrarVenda(vendaAtual);
            } catch (ExceptionDAO e) {
                JOptionPane.showMessageDialog(null, "Erro ao criar objeto Compra " + e);
                return false;
            }

        } else {
            return false;
        }

        return true;
    }

    public boolean atualizarVenda(Venda v, List<Item> itensAntigos) {
        if (validaQuantidadeProduto(v.getItensVenda())) {
            if ((v.getValorTotal() - v.getValorDesconto()) > 0) {

                try {
                    return v.atualizarVenda(v, itensAntigos);
                } catch (ExceptionDAO e) {
                    System.out.println("Erro atualizar venda " + e);
                    return false;
                }

            } else {
                JOptionPane.showMessageDialog(null, "Você não pode cadastrar uma venda com total negativo!");
                return false;
            }
        } else {
            return false;
        }

    }

    public boolean excluiVenda(Venda venda) {

        try {
            return venda.excluirVenda(venda);
        } catch (ExceptionDAO e) {
            System.out.println("Erro ao excluir venda:" + e);
            return false;
        }

    }

    public boolean validaQuantidadeProduto(List<Item> itensDaVenda) {

        EstoqueController ec = new EstoqueController();
        boolean comEstoque = true;
        for (Item i : itensDaVenda) {
            long qtd = ec.quantidadeProduto(i.getId_produto());
            if (qtd >= i.getQuantidade()) {

            } else {
                JOptionPane.showMessageDialog(null, "Quantidade de produto(s) insuficiente no estoque.\n"
                        + "Produto:" + i.getNome()
                        + "\nQuantidade em estoque: " + qtd + "\n"
                        + "Quantidade Solicitada:" + i.getQuantidade());
                comEstoque = false;
            }
        }

        return comEstoque;
    }

    public List<Venda> selecionaVendasDoAno(int anoReferente) {
        try {
            return new Venda().selecionaVendasDoAno(anoReferente);
        } catch (ExceptionDAO e) {
            System.out.println("erro ao listar vendas do ano");
            return null;
        }

    }

    public long selecionaVendasPorMes(Month mes) {
        try {
            return new Venda().selecionaVendasPorMes(mes);
        } catch (ExceptionDAO e) {
            System.out.println("Erro ao listar vendas por mes" + e);
        }
        return 0;
    }

    public List<Venda> selecionaTodasVendas() {
        try {
            return new Venda().selecionaTodasVendas();
        } catch (ExceptionDAO e) {
            System.out.println("Erro ao listar todas vendas" + e);
        }
        return null;
    }

    public List<Venda> selecionaVendasPorNomeCliente(String nomeCliente) {
        try {
            return new Venda().selecionaVendasPorNomeCliente(nomeCliente);
        } catch (ExceptionDAO e) {
            System.out.println("Erro ao listar todas vendas" + e);
        }
        return null;
    }

    public List<Venda> retornaComprasPorIDCliente(long idCliente) {
        try {
            return new Venda().retornaComprasCliente(idCliente);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar compras cliente " + e);
            return null;
        }
    }

    public List<Item> retornaItemsCompra(long idCliente) {

        try {
            return new Item().retornaItemsCompra(idCliente);
        } catch (ExceptionDAO e) {
            System.out.println(e);
        }

        return null;
    }

    public int retornaQuantidadeDeVendasHoje() {

        try {
            return new Venda().retornaQuantidadeDeVendasHoje();
        } catch (ExceptionDAO e) {
            System.out.println(e);
        }

        return 0;

    }

    public long retornaSomaDeVendasMensal() {
        try {
            return new Venda().retornaSomaDeVendasMensal();
        } catch (ExceptionDAO e) {
            System.out.println("Erro ao somar vendas mensais.");
        }
        return 0L;
    }

    public List<Venda> selecionaVendasDoAnoPorNomeCliente(String nomeCliente) {
        try {
            return new Venda().selecionaVendasDoAnoPorNomeCliente(nomeCliente);
        } catch (ExceptionDAO e) {
            System.out.println("Erro ao listar todas vendas" + e);
        }
        return null;
    }

}
