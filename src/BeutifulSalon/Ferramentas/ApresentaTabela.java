/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.controller.ClienteController;
import BeutifulSalon.controller.OrcamentoController;
import BeutifulSalon.controller.ProdutoController;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Orcamento;
import BeutifulSalon.model.Produto;
import BeutifulSalon.view.Apresenta.ApresentaCliente;
import BeutifulSalon.view.Apresenta.ApresentaFinancas;
import BeutifulSalon.view.Apresenta.ApresentaProduto;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mateus
 */
public class ApresentaTabela {

    public DefaultTableModel Orcamentos(JTable tabela) {

        DefaultTableModel tabelaOrcamentoModel = (DefaultTableModel) tabela.getModel(); // tabela
        tabelaOrcamentoModel.setRowCount(0);

        OrcamentoController oc = new OrcamentoController();

        ArrayList<Orcamento> orcamentos = null;

        try {
            orcamentos = oc.listarOrcamentos();
        } catch (ExceptionDAO ex) {
            java.util.logging.Logger.getLogger(ApresentaFinancas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        try {
            orcamentos.forEach((Orcamento orcamento) -> {
                tabelaOrcamentoModel.addRow(new Object[]{
                    orcamento.getNome(),
                    Dinheiro.parseString(orcamento.getJan()),
                    Dinheiro.parseString(orcamento.getFev()),
                    Dinheiro.parseString(orcamento.getMar()),
                    Dinheiro.parseString(orcamento.getAbr()),
                    Dinheiro.parseString(orcamento.getMai()),
                    Dinheiro.parseString(orcamento.getJun()),
                    Dinheiro.parseString(orcamento.getJul()),
                    Dinheiro.parseString(orcamento.getAgo()),
                    Dinheiro.parseString(orcamento.getSet()),
                    Dinheiro.parseString(orcamento.getOut()),
                    Dinheiro.parseString(orcamento.getNov()),
                    Dinheiro.parseString(orcamento.getDez()),
                    orcamento.getId_orcamento()

                });

            });

            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            java.util.Date ano = new java.util.Date();

            tabelaOrcamentoModel.addRow(new Object[]{"TOTAL",
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("JANEIRO", df.format(ano)))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("FEVEREIRO", df.format(ano)))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("MARCO", df.format(ano)))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("ABRIL", df.format(ano)))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("MAIO", df.format(ano)))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("JUNHO", df.format(ano)))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("JULHO", df.format(ano)))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("AGOSTO", df.format(ano)))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("SETEMBRO", df.format(ano)))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("OUTUBRO", df.format(ano)))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("NOVEMBRO", df.format(ano)))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("DEZEMBRO", df.format(ano)))),});

        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Orçamentos" + e);
        }

        return tabelaOrcamentoModel;
    }

    public DefaultTableModel Orcamentos(JTable tabela, String ano) {

        DefaultTableModel tabelaOrcamentoModel = (DefaultTableModel) tabela.getModel(); // tabela
        tabelaOrcamentoModel.setRowCount(0);

        OrcamentoController oc = new OrcamentoController();

        ArrayList<Orcamento> orcamentos = null;

        try {
            orcamentos = oc.listarOrcamentos(ano);
        } catch (ExceptionDAO ex) {
            java.util.logging.Logger.getLogger(ApresentaFinancas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        try {
            orcamentos.forEach((Orcamento orcamento) -> {
                tabelaOrcamentoModel.addRow(new Object[]{
                    orcamento.getNome(),
                    Dinheiro.parseString(orcamento.getJan()),
                    Dinheiro.parseString(orcamento.getFev()),
                    Dinheiro.parseString(orcamento.getMar()),
                    Dinheiro.parseString(orcamento.getAbr()),
                    Dinheiro.parseString(orcamento.getMai()),
                    Dinheiro.parseString(orcamento.getJun()),
                    Dinheiro.parseString(orcamento.getJul()),
                    Dinheiro.parseString(orcamento.getAgo()),
                    Dinheiro.parseString(orcamento.getSet()),
                    Dinheiro.parseString(orcamento.getOut()),
                    Dinheiro.parseString(orcamento.getNov()),
                    Dinheiro.parseString(orcamento.getDez()),
                    orcamento.getId_orcamento(),});

            });

            tabelaOrcamentoModel.addRow(new Object[]{"TOTAL",
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("JANEIRO", ano))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("FEVEREIRO", ano))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("MARCO", ano))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("ABRIL", ano))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("MAIO", ano))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("JUNHO", ano))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("JULHO", ano))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("AGOSTO", ano))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("SETEMBRO", ano))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("OUTUBRO", ano))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("NOVEMBRO", ano))),
                Dinheiro.parseString(Dinheiro.parseBigDecimal(oc.somarOrcamento("DEZEMBRO", ano)))

            });

        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar Orçamentos" + e);
        }

        return tabelaOrcamentoModel;
    }

    public DefaultTableModel OrcamentosServico(JTable tabela) {

        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);
        ArrayList<Orcamento> orcamentos = null;

        OrcamentoController oc = new OrcamentoController();

        try {
            orcamentos = oc.listarOrcamentosServico();

            orcamentos.forEach((Orcamento orcamento) -> {
                modelo.addRow(new Object[]{
                    orcamento.getNome(),
                    orcamento.getJan(),
                    orcamento.getFev(),
                    orcamento.getMar(),
                    orcamento.getAbr(),
                    orcamento.getMai(),
                    orcamento.getJun(),
                    orcamento.getJul(),
                    orcamento.getAgo(),
                    orcamento.getSet(),
                    orcamento.getOut(),
                    orcamento.getNov(),
                    orcamento.getDez(),
                    orcamento.getId_orcamento(),
                    orcamento.getId_servico()
                });
            });

        } catch (ExceptionDAO e) {

            JOptionPane.showMessageDialog(null, "Erro ao listar Orçamentos" + e);

        }
        return modelo;
    }

    public DefaultTableModel OrcamentosServico(JTable tabela, String ano) {

        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);
        ArrayList<Orcamento> orcamentos = null;

        OrcamentoController oc = new OrcamentoController();

        try {
            orcamentos = oc.listarOrcamentosServico(ano);

            orcamentos.forEach((Orcamento orcamento) -> {

                modelo.addRow(new Object[]{
                    orcamento.getNome(),
                    orcamento.getJan(),
                    orcamento.getFev(),
                    orcamento.getMar(),
                    orcamento.getAbr(),
                    orcamento.getMai(),
                    orcamento.getJun(),
                    orcamento.getJul(),
                    orcamento.getAgo(),
                    orcamento.getSet(),
                    orcamento.getOut(),
                    orcamento.getNov(),
                    orcamento.getDez(),
                    orcamento.getId_orcamento(),
                    orcamento.getId_servico()
                });
            });

        } catch (ExceptionDAO e) {

            JOptionPane.showMessageDialog(null, "Erro ao listar Orçamentos" + e);

        }
        return modelo;
    }

    public DefaultTableModel Produtos(JTable tabela) {

        DefaultTableModel tabelaProdutoModel = (DefaultTableModel) tabela.getModel(); // tabela
        tabelaProdutoModel.setRowCount(0);
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");

        ProdutoController pc = new ProdutoController();

        ArrayList<Produto> produtosListados = null;
        try {
            produtosListados = pc.listarProdutos();
        } catch (ExceptionDAO ex) {
            java.util.logging.Logger.getLogger(ApresentaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        try {
            produtosListados.forEach((Produto produto) -> {
                tabelaProdutoModel.addRow(new Object[]{
                    produto.getNome(),
                    produto.getMarca(),
                    Dinheiro.parseString(produto.getPreco()),
                    produto.getDataValidade().format(formatterData),
                    produto.getId_produto()
                });

            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listarProdutos" + e);
        }

        return tabelaProdutoModel;
    }

    public DefaultTableModel Produtos(JTable tabela, String nome) {

        DefaultTableModel tabelaProdutoModel = (DefaultTableModel) tabela.getModel(); // tabela

        tabelaProdutoModel.setRowCount(0);

        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");

        ProdutoController pc = new ProdutoController();

        ArrayList<Produto> produtosListados = null;
        try {
            produtosListados = pc.listarProdutos(nome);
        } catch (ExceptionDAO ex) {
            java.util.logging.Logger.getLogger(ApresentaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        try {
            produtosListados.forEach((Produto produto) -> {
                tabelaProdutoModel.addRow(new Object[]{
                    produto.getNome(),
                    produto.getMarca(),
                    Dinheiro.parseString(produto.getPreco()),
                    produto.getDataValidade().format(formatterData),
                    produto.getId_produto(),
                });

            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listarProdutos" + e);
        }
        return tabelaProdutoModel;
    }

    //CLIENTES
    public DefaultTableModel Clientes(JTable tabela) {

        DefaultTableModel tabelaClienteModel = (DefaultTableModel) tabela.getModel(); // tabela
        tabelaClienteModel.setRowCount(0);
        ClienteController cc = new ClienteController();

        ArrayList<Cliente> clientesConsultados = null;
        try {
            clientesConsultados = cc.listarClientes();
        } catch (ExceptionDAO ex) {
            java.util.logging.Logger.getLogger(ApresentaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        try {
            clientesConsultados.forEach((Cliente cliente) -> {
                tabelaClienteModel.addRow(new Object[]{cliente.getNOME(),
                    cliente.getSOBRENOME(),
                    cliente.getCELULAR(),
                    cliente.getEMAIL(),
                    cliente.getCPF()
                });

            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listarClientes" + e);
        }
        return tabelaClienteModel;
    }

    public DefaultTableModel Clientes(JTable tabela, String nome) {

        DefaultTableModel tabelaClienteModel = (DefaultTableModel) tabela.getModel(); // tabela
        tabelaClienteModel.setRowCount(0);

        ClienteController cc = new ClienteController();

        ArrayList<Cliente> clientesConsultados = null;
        try {
            clientesConsultados = cc.listarClientes(nome);
        } catch (ExceptionDAO ex) {
            java.util.logging.Logger.getLogger(ApresentaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        try {
            clientesConsultados.forEach((Cliente cliente) -> {
                tabelaClienteModel.addRow(new Object[]{cliente.getNOME(),
                    cliente.getSOBRENOME(),
                    cliente.getCELULAR(),
                    cliente.getEMAIL(),
                    cliente.getCPF()});

            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listarClientes" + e);
        }
        
        return tabelaClienteModel;
    }
}
