/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.controller.AgendamentoController;
import BeutifulSalon.controller.ClienteController;
import BeutifulSalon.controller.OrcamentoController;
import BeutifulSalon.controller.ProdutoController;
import BeutifulSalon.controller.ServicoController;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Orcamento;
import BeutifulSalon.model.Produto;
import BeutifulSalon.model.Servico;
import BeutifulSalon.view.Apresenta.ApresentaCliente;
import BeutifulSalon.view.Apresenta.ApresentaFinancas;
import BeutifulSalon.view.Apresenta.ApresentaProduto;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        ServicoController sc = new ServicoController();
        Servico servicoAtual = null;

        try {
            orcamentos = oc.listarOrcamentosServico();
            if (orcamentos == null) {
                return modelo;
            }
            for (Orcamento orcamento : orcamentos) {

                try {
                    servicoAtual = sc.buscarServico(orcamento.getId_servico());
                } catch (ExceptionDAO ex) {
                    Logger.getLogger(ApresentaTabela.class.getName()).log(Level.SEVERE, null, ex);
                }

                long preco = servicoAtual.getPreco();
                modelo.addRow(new Object[]{
                    orcamento.getNome(),
                    Dinheiro.parseString(orcamento.getJan() * preco),
                    Dinheiro.parseString(orcamento.getFev() * preco),
                    Dinheiro.parseString(orcamento.getMar() * preco),
                    Dinheiro.parseString(orcamento.getAbr() * preco),
                    Dinheiro.parseString(orcamento.getMai() * preco),
                    Dinheiro.parseString(orcamento.getJun() * preco),
                    Dinheiro.parseString(orcamento.getJul() * preco),
                    Dinheiro.parseString(orcamento.getAgo() * preco),
                    Dinheiro.parseString(orcamento.getSet() * preco),
                    Dinheiro.parseString(orcamento.getOut() * preco),
                    Dinheiro.parseString(orcamento.getNov() * preco),
                    Dinheiro.parseString(orcamento.getDez() * preco),
                    orcamento.getId_orcamento(),
                    orcamento.getId_servico()
                });
            }

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
        ServicoController sc = new ServicoController();
        Servico servicoAtual = null;

        try {
            orcamentos = oc.listarOrcamentosServico(ano);
            if (orcamentos == null) {
                return modelo;
            }
            for (Orcamento orcamento : orcamentos) {

                try {
                    servicoAtual = sc.buscarServico(orcamento.getId_servico());
                } catch (ExceptionDAO ex) {
                    Logger.getLogger(ApresentaTabela.class.getName()).log(Level.SEVERE, null, ex);
                }

                long preco = servicoAtual.getPreco();
                modelo.addRow(new Object[]{
                    orcamento.getNome(),
                    Dinheiro.parseString(orcamento.getJan() * preco),
                    Dinheiro.parseString(orcamento.getFev() * preco),
                    Dinheiro.parseString(orcamento.getMar() * preco),
                    Dinheiro.parseString(orcamento.getAbr() * preco),
                    Dinheiro.parseString(orcamento.getMai() * preco),
                    Dinheiro.parseString(orcamento.getJun() * preco),
                    Dinheiro.parseString(orcamento.getJul() * preco),
                    Dinheiro.parseString(orcamento.getAgo() * preco),
                    Dinheiro.parseString(orcamento.getSet() * preco),
                    Dinheiro.parseString(orcamento.getOut() * preco),
                    Dinheiro.parseString(orcamento.getNov() * preco),
                    Dinheiro.parseString(orcamento.getDez() * preco),
                    orcamento.getId_orcamento(),
                    orcamento.getId_servico()
                });
            }

        } catch (ExceptionDAO e) {

            JOptionPane.showMessageDialog(null, "Erro ao listar Orçamentos" + e);

        }
        return modelo;
    }

    public DefaultTableModel servicosComparados(JTable tabela) {

        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);
        ArrayList<Orcamento> orcamentos = null;

        ManipulaData manipulaData = new ManipulaData();
        OrcamentoController oc = new OrcamentoController();
        ServicoController sc = new ServicoController();
        Servico servicoAtual = null;

        try {

            orcamentos = oc.listarOrcamentosServico();
            for (Orcamento orcamento : orcamentos) {

                try {
                    servicoAtual = sc.buscarServico(orcamento.getId_servico());
                } catch (ExceptionDAO ex) {
                    Logger.getLogger(ApresentaTabela.class.getName()).log(Level.SEVERE, null, ex);
                }

                double janeiro = 0;
                double fevereiro = 0;
                double marco = 0;
                double abril = 0;
                double maio = 0;
                double junho = 0;
                double julho = 0;
                double agosto = 0;
                double setembro = 0;
                double outubro = 0;
                double novembro = 0;
                double dezembro = 0;
                for (Month m : manipulaData.meses(LocalDate.now())) {

                    switch (m) {
                        case JANUARY:
                            Long valorPrevistoJan = orcamento.getJan() * servicoAtual.getPreco();
                            Long valorRealizadoJan = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(LocalDate.now(), Month.JANUARY),
                                    manipulaData.fimDoMes(LocalDate.now(), Month.JANUARY),
                                    orcamento.getId_servico());

                            double comparacaoJan = (valorRealizadoJan.doubleValue() / valorPrevistoJan.doubleValue()) * 100;
                            janeiro = comparacaoJan;
                            break;

                        case FEBRUARY:
                            Long valorPrevistoFev = orcamento.getFev() * servicoAtual.getPreco();
                            Long valorRealizadoFev = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(LocalDate.now(), Month.FEBRUARY),
                                    manipulaData.fimDoMes(LocalDate.now(), Month.FEBRUARY),
                                    orcamento.getId_servico());

                            double comparacaoFev = (valorRealizadoFev.doubleValue() / valorPrevistoFev.doubleValue()) * 100;
                            fevereiro = comparacaoFev;
                            break;
                        case MARCH:
                            Long valorPrevistoMar = orcamento.getMar() * servicoAtual.getPreco();
                            Long valorRealizadoMar = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(LocalDate.now(), Month.MARCH),
                                    manipulaData.fimDoMes(LocalDate.now(), Month.MARCH),
                                    orcamento.getId_servico());

                            double comparacaoMar = (valorRealizadoMar.doubleValue() / valorPrevistoMar.doubleValue()) * 100;
                            marco = comparacaoMar;
                            break;
                        case APRIL:
                            Long valorPrevistoAbr = orcamento.getAbr() * servicoAtual.getPreco();
                            Long valorRealizadoAbr = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(LocalDate.now(), Month.APRIL),
                                    manipulaData.fimDoMes(LocalDate.now(), Month.APRIL),
                                    orcamento.getId_servico());

                            double comparacaoAbr = (valorRealizadoAbr.doubleValue() / valorPrevistoAbr.doubleValue()) * 100;
                            abril = comparacaoAbr;
                            break;
                        case MAY:
                            Long valorPrevistoMai = orcamento.getMai() * servicoAtual.getPreco();
                            Long valorRealizadoMai = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(LocalDate.now(), Month.MAY),
                                    manipulaData.fimDoMes(LocalDate.now(), Month.MAY),
                                    orcamento.getId_servico());

                            double comparacaoMai = (valorRealizadoMai.doubleValue() / valorPrevistoMai.doubleValue()) * 100;
                            maio = comparacaoMai;
                            break;
                        case JUNE:
                            Long valorPrevistoJun = orcamento.getJun() * servicoAtual.getPreco();
                            Long valorRealizadoJun = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(LocalDate.now(), Month.JUNE),
                                    manipulaData.fimDoMes(LocalDate.now(), Month.JUNE),
                                    orcamento.getId_servico());

                            double comparacaoJun = (valorRealizadoJun.doubleValue() / valorPrevistoJun.doubleValue()) * 100;
                            junho = comparacaoJun;
                            break;
                        case JULY:
                            Long valorPrevistoJul = orcamento.getJul() * servicoAtual.getPreco();
                            Long valorRealizadoJul = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(LocalDate.now(), Month.JULY),
                                    manipulaData.fimDoMes(LocalDate.now(), Month.JULY),
                                    orcamento.getId_servico());

                            double comparacaoJul = (valorRealizadoJul.doubleValue() / valorPrevistoJul.doubleValue()) * 100;
                            julho = comparacaoJul;
                            break;
                        case AUGUST:
                            Long valorPrevistoAgo = orcamento.getAgo() * servicoAtual.getPreco();
                            Long valorRealizadoAgo = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(LocalDate.now(), Month.AUGUST),
                                    manipulaData.fimDoMes(LocalDate.now(), Month.AUGUST),
                                    orcamento.getId_servico());

                            double comparacaoAgo = (valorRealizadoAgo.doubleValue() / valorPrevistoAgo.doubleValue()) * 100;
                            agosto = comparacaoAgo;
                            break;
                        case SEPTEMBER:
                            Long valorPrevistoSet = orcamento.getSet() * servicoAtual.getPreco();
                            Long valorRealizadoSet = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(LocalDate.now(), Month.SEPTEMBER),
                                    manipulaData.fimDoMes(LocalDate.now(), Month.SEPTEMBER),
                                    orcamento.getId_servico());

                            double comparacaoSet = (valorRealizadoSet.doubleValue() / valorPrevistoSet.doubleValue()) * 100;
                            setembro = comparacaoSet;
                            break;
                        case OCTOBER:
                            Long valorPrevistoOut = orcamento.getOut() * servicoAtual.getPreco();
                            Long valorRealizadoOut = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(LocalDate.now(), Month.OCTOBER),
                                    manipulaData.fimDoMes(LocalDate.now(), Month.OCTOBER),
                                    orcamento.getId_servico());

                            double comparacaoOut = (valorRealizadoOut.doubleValue() / valorPrevistoOut.doubleValue()) * 100;
                            outubro = comparacaoOut;
                            break;
                        case NOVEMBER:
                            Long valorPrevistoNov = orcamento.getNov() * servicoAtual.getPreco();
                            Long valorRealizadoNov = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(LocalDate.now(), Month.NOVEMBER),
                                    manipulaData.fimDoMes(LocalDate.now(), Month.NOVEMBER),
                                    orcamento.getId_servico());

                            double comparacaoNov = (valorRealizadoNov.doubleValue() / valorPrevistoNov.doubleValue()) * 100;
                            novembro = comparacaoNov;
                            break;
                        case DECEMBER:
                            Long valorPrevistoDez = orcamento.getNov() * servicoAtual.getPreco();
                            Long valorRealizadoDez = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(LocalDate.now(), Month.DECEMBER),
                                    manipulaData.fimDoMes(LocalDate.now(), Month.DECEMBER),
                                    orcamento.getId_servico());

                            double comparacaoDez = (valorRealizadoDez.doubleValue() / valorPrevistoDez.doubleValue()) * 100;
                            dezembro = comparacaoDez;
                            break;
                    }

                }

                modelo.addRow(new Object[]{
                    orcamento.getNome(),
                    String.format("%.2f", janeiro) + "%",
                    String.format("%.2f", fevereiro) + "%",
                    String.format("%.2f", marco) + "%",
                    String.format("%.2f", abril) + "%",
                    String.format("%.2f", maio) + "%",
                    String.format("%.2f", junho) + "%",
                    String.format("%.2f", julho) + "%",
                    String.format("%.2f", agosto) + "%",
                    String.format("%.2f", setembro) + "%",
                    String.format("%.2f", outubro) + "%",
                    String.format("%.2f", novembro) + "%",
                    String.format("%.2f", dezembro) + "%"
                });
            }

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
                    produto.getId_produto(),});

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

    //AGENDAMENTOS
    public DefaultTableModel Agendamentos(JTable tabela, int opc, String nome) {

        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);

        AgendamentoController ag = new AgendamentoController();
        ClienteController cc = new ClienteController();

        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        DateTimeFormatter parserHora = DateTimeFormatter.ofPattern("HH:mm");

        ArrayList<Agendamento> agendamentos = null;

        try {

            switch (opc) {

                case 0:
                    agendamentos = ag.listarAgendamentosNome(nome);
                    break;
                case 1:
                    agendamentos = ag.listarAgedaAgendamentosHoje();
                    break;
                case 2:
                    agendamentos = ag.listarAgendamentosAmanha();
                    break;
                case 3:
                    agendamentos = ag.listarAgendamentosSemana();
                    break;
                case 4:
                    agendamentos = ag.listarAgendamentos();
                    break;
            }

            for (Agendamento g : agendamentos) {

                Cliente cliente = new Cliente();
                String realizado;

                if (g.getRealizado()) {
                    realizado = "Realizado";
                } else {
                    realizado = "Em andamento";
                }
                model.addRow(new Object[]{
                    cliente.buscarCliente(g.getCpfCliente()).getNOME(),
                    g.getData().format(formatterData),
                    g.getHorario().format(parserHora),
                    realizado,
                    g.getId()

                });
            }

        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco " + e);
        }
        return model;
    }

}
