/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.controller.AgendamentoController;
import BeutifulSalon.controller.ClienteController;
import BeutifulSalon.controller.DespesaController;
import BeutifulSalon.controller.EstoqueController;
import BeutifulSalon.controller.OrcamentoController;
import BeutifulSalon.controller.ProdutoController;
import BeutifulSalon.controller.ServicoController;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Despesa;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Orcamento;
import BeutifulSalon.model.OrcamentoServico;
import BeutifulSalon.model.Produto;
import BeutifulSalon.model.Servico;
import BeutifulSalon.view.Apresenta.ApresentaCliente;
import BeutifulSalon.view.Apresenta.ApresentaFinancas;
import BeutifulSalon.view.Apresenta.ApresentaProduto;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mateus
 */
public class ApresentaTabela {

    public DefaultTableModel apresentarOrcamentos(JTable tabela) {

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

    public DefaultTableModel apresentarOrcamentos(JTable tabela, String ano) {

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
    
    public DefaultTableModel apresentaServicosAgendamento(JTable tabela, long idAgendamento){
        
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);
        AgendamentoController ag = new AgendamentoController();
        ArrayList<Servico> servicos = null;
        
        try {
            servicos = ag.listarServicosAgendamento(idAgendamento);
            
            if(servicos == null)return modelo;
            
            for(Servico s: servicos){
      
                modelo.addRow(new Object[]{
                    s.getNome(),
                    Dinheiro.parseString(s.getPreco()),
                    s.getId()
                });
            }
            
        } catch (ExceptionDAO e) {     
            JOptionPane.showMessageDialog(null, e);
        }
        
        return modelo;
    }
    public DefaultTableModel OrcamentosServico(JTable tabela) {

        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);
        ArrayList<OrcamentoServico> orcamentos = null;

        OrcamentoController oc = new OrcamentoController();
        ServicoController sc = new ServicoController();
        Servico servicoAtual = null;

        try {
            orcamentos = oc.listarOrcamentosServico();
            if (orcamentos == null) {
                return modelo;
            }
            for (OrcamentoServico orcamento : orcamentos) {

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
    
    public DefaultTableModel OrcamentoServicoRealizado(JTable tabela, String anoReferente){
              
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);
        ServicoController sc = new ServicoController();
        OrcamentoController oc = new OrcamentoController();
        ArrayList<Servico> servicos = null;
        ArrayList<OrcamentoServico> orcamentos = new ArrayList<>();
        ManipulaData manipulaData = new ManipulaData();
        LocalDate ano = null;
        try {
            ano = LocalDate.ofYearDay(Integer.parseInt(anoReferente), 1);  
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar ano" + e);
        }
 
        try {
            servicos = sc.listarServicos();

            for (Servico s : servicos) {

                long janeiro = 0;
                long fevereiro = 0;
                long marco = 0;
                long abril = 0;
                long maio = 0;
                long junho = 0;
                long julho = 0;
                long agosto = 0;
                long setembro = 0;
                long outubro = 0;
                long novembro = 0;
                long dezembro = 0;

                for (Month m : manipulaData.meses(ano)) {

                    switch (m) {
                        case JANUARY:
                            janeiro = oc.listarOrcamentoServicorRealizado(ano, Month.JANUARY, s.getId()).getQuantidadeMensal();
                            janeiro *= s.getPreco();
                            break;
                        case FEBRUARY:
                            fevereiro = oc.listarOrcamentoServicorRealizado(ano, Month.FEBRUARY, s.getId()).getQuantidadeMensal();
                            fevereiro *= s.getPreco();
                            break;
                        case MARCH:
                            marco = oc.listarOrcamentoServicorRealizado(ano, Month.MARCH, s.getId()).getQuantidadeMensal();
                            marco *= s.getPreco();
                            break;
                        case APRIL:
                            abril = oc.listarOrcamentoServicorRealizado(ano, Month.APRIL, s.getId()).getQuantidadeMensal();
                            abril *= s.getPreco();
                            
                            break;
                        case MAY:
                            maio = oc.listarOrcamentoServicorRealizado(ano, Month.MAY, s.getId()).getQuantidadeMensal();
                            maio *= s.getPreco();
                        case JUNE:
                            junho = oc.listarOrcamentoServicorRealizado(ano, Month.JUNE, s.getId()).getQuantidadeMensal();
                            junho *= s.getPreco();

                            break;
                        case JULY:
                            julho = oc.listarOrcamentoServicorRealizado(ano, Month.JULY, s.getId()).getQuantidadeMensal();
                            julho *= s.getPreco();
                            break;
                        case AUGUST:
                            agosto = oc.listarOrcamentoServicorRealizado(ano, Month.AUGUST, s.getId()).getQuantidadeMensal();
                            agosto *= s.getPreco();
                        case SEPTEMBER:
                            setembro = oc.listarOrcamentoServicorRealizado(ano, Month.SEPTEMBER, s.getId()).getQuantidadeMensal();
                            setembro *= s.getPreco();
                            break;
                        case OCTOBER:
                            outubro = oc.listarOrcamentoServicorRealizado(ano, Month.OCTOBER, s.getId()).getQuantidadeMensal();
                            outubro *= s.getPreco();
                            break;
                        case NOVEMBER:
                            novembro = oc.listarOrcamentoServicorRealizado(ano, Month.NOVEMBER, s.getId()).getQuantidadeMensal();
                            novembro *= s.getPreco();
                            break;
                        case DECEMBER:
                            dezembro = oc.listarOrcamentoServicorRealizado(ano, Month.DECEMBER, s.getId()).getQuantidadeMensal();
                            dezembro *= s.getPreco();
                            break;
                    }

                }
                OrcamentoServico orcamentoAtual = new OrcamentoServico();
                orcamentoAtual.setNome(s.getNome());
                orcamentoAtual.setJan(janeiro);
                orcamentoAtual.setFev(fevereiro);
                orcamentoAtual.setMar(marco);
                orcamentoAtual.setAbr(abril);
                orcamentoAtual.setMai(maio);
                orcamentoAtual.setJun(junho);
                orcamentoAtual.setJul(julho);
                orcamentoAtual.setAgo(agosto);
                orcamentoAtual.setSet(setembro);
                orcamentoAtual.setOut(outubro);
                orcamentoAtual.setNov(novembro);
                orcamentoAtual.setDez(dezembro);

                orcamentos.add(orcamentoAtual);
            }
            
            orcamentos.forEach(orcamento -> {
                modelo.addRow(new Object[] {
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
                });
            });
            
            

        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        return modelo;
    }

    public DefaultTableModel OrcamentosServico(JTable tabela, String ano) {

        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);
        ArrayList<OrcamentoServico> orcamentos = null;

        OrcamentoController oc = new OrcamentoController();
        ServicoController sc = new ServicoController();
        Servico servicoAtual = null;

        try {
            orcamentos = oc.listarOrcamentosServico(ano);
            if (orcamentos == null) {
                return modelo;
            }
            for (OrcamentoServico orcamento : orcamentos) {

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

    public DefaultTableModel servicosComparados(JTable tabela, String anoReferente) {

        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);
        ArrayList<OrcamentoServico> orcamentos = null;

        ManipulaData manipulaData = new ManipulaData();
        OrcamentoController oc = new OrcamentoController();
        ServicoController sc = new ServicoController();
        Servico servicoAtual = null;
        LocalDate ano = null;
        try {
            ano = LocalDate.ofYearDay(Integer.parseInt(anoReferente), 1);  
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar ano" + e);
        }
        try {

            orcamentos = oc.listarOrcamentosServico(anoReferente);
            for (OrcamentoServico orcamento : orcamentos) {

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
                for (Month m : manipulaData.meses(ano)) {

                    switch (m) {
                        case JANUARY:
                            Long valorPrevistoJan = orcamento.getJan() * servicoAtual.getPreco();
                            Long valorRealizadoJan = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.JANUARY),
                                    manipulaData.fimDoMes(ano, Month.JANUARY),
                                    orcamento.getId_servico());

                            double comparacaoJan = (valorRealizadoJan.doubleValue() / valorPrevistoJan.doubleValue()) * 100;
                            janeiro = comparacaoJan;
                            break;

                        case FEBRUARY:
                            Long valorPrevistoFev = orcamento.getFev() * servicoAtual.getPreco();
                            Long valorRealizadoFev = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.FEBRUARY),
                                    manipulaData.fimDoMes(ano, Month.FEBRUARY),
                                    orcamento.getId_servico());

                            double comparacaoFev = (valorRealizadoFev.doubleValue() / valorPrevistoFev.doubleValue()) * 100;
                            fevereiro = comparacaoFev;
                            break;
                        case MARCH:
                            Long valorPrevistoMar = orcamento.getMar() * servicoAtual.getPreco();
                            Long valorRealizadoMar = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.MARCH),
                                    manipulaData.fimDoMes(ano, Month.MARCH),
                                    orcamento.getId_servico());

                            double comparacaoMar = (valorRealizadoMar.doubleValue() / valorPrevistoMar.doubleValue()) * 100;
                            marco = comparacaoMar;
                            break;
                        case APRIL:
                            Long valorPrevistoAbr = orcamento.getAbr() * servicoAtual.getPreco();
                            Long valorRealizadoAbr = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.APRIL),
                                    manipulaData.fimDoMes(ano, Month.APRIL),
                                    orcamento.getId_servico());

                            double comparacaoAbr = (valorRealizadoAbr.doubleValue() / valorPrevistoAbr.doubleValue()) * 100;
                            abril = comparacaoAbr;
                            break;
                        case MAY:
                            Long valorPrevistoMai = orcamento.getMai() * servicoAtual.getPreco();
                            Long valorRealizadoMai = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.MAY),
                                    manipulaData.fimDoMes(ano, Month.MAY),
                                    orcamento.getId_servico());

                            double comparacaoMai = (valorRealizadoMai.doubleValue() / valorPrevistoMai.doubleValue()) * 100;
                            maio = comparacaoMai;
                            break;
                        case JUNE:
                            Long valorPrevistoJun = orcamento.getJun() * servicoAtual.getPreco();
                            Long valorRealizadoJun = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.JUNE),
                                    manipulaData.fimDoMes(ano, Month.JUNE),
                                    orcamento.getId_servico());

                            double comparacaoJun = (valorRealizadoJun.doubleValue() / valorPrevistoJun.doubleValue()) * 100;
                            junho = comparacaoJun;
                            break;
                        case JULY:
                            Long valorPrevistoJul = orcamento.getJul() * servicoAtual.getPreco();
                            Long valorRealizadoJul = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.JULY),
                                    manipulaData.fimDoMes(ano, Month.JULY),
                                    orcamento.getId_servico());

                            double comparacaoJul = (valorRealizadoJul.doubleValue() / valorPrevistoJul.doubleValue()) * 100;
                            julho = comparacaoJul;
                            break;
                        case AUGUST:
                            Long valorPrevistoAgo = orcamento.getAgo() * servicoAtual.getPreco();
                            Long valorRealizadoAgo = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.AUGUST),
                                    manipulaData.fimDoMes(ano, Month.AUGUST),
                                    orcamento.getId_servico());

                            double comparacaoAgo = (valorRealizadoAgo.doubleValue() / valorPrevistoAgo.doubleValue()) * 100;
                            agosto = comparacaoAgo;
                            break;
                        case SEPTEMBER:
                            Long valorPrevistoSet = orcamento.getSet() * servicoAtual.getPreco();
                            Long valorRealizadoSet = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.SEPTEMBER),
                                    manipulaData.fimDoMes(ano, Month.SEPTEMBER),
                                    orcamento.getId_servico());

                            double comparacaoSet = (valorRealizadoSet.doubleValue() / valorPrevistoSet.doubleValue()) * 100;
                            setembro = comparacaoSet;
                            break;
                        case OCTOBER:
                            Long valorPrevistoOut = orcamento.getOut() * servicoAtual.getPreco();
                            Long valorRealizadoOut = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.OCTOBER),
                                    manipulaData.fimDoMes(ano, Month.OCTOBER),
                                    orcamento.getId_servico());

                            double comparacaoOut = (valorRealizadoOut.doubleValue() / valorPrevistoOut.doubleValue()) * 100;
                            outubro = comparacaoOut;
                            break;
                        case NOVEMBER:
                            Long valorPrevistoNov = orcamento.getNov() * servicoAtual.getPreco();
                            Long valorRealizadoNov = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.NOVEMBER),
                                    manipulaData.fimDoMes(ano, Month.NOVEMBER),
                                    orcamento.getId_servico());

                            double comparacaoNov = (valorRealizadoNov.doubleValue() / valorPrevistoNov.doubleValue()) * 100;
                            novembro = comparacaoNov;
                            break;
                        case DECEMBER:
                            Long valorPrevistoDez = orcamento.getNov() * servicoAtual.getPreco();
                            Long valorRealizadoDez = oc.somaTotalGanhoServicoMensal(
                                    manipulaData.inicioDoMes(ano, Month.DECEMBER),
                                    manipulaData.fimDoMes(ano, Month.DECEMBER),
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

    public DefaultTableModel apresentaProdutos(JTable tabela) {

        DefaultTableModel tabelaProdutoModel = (DefaultTableModel) tabela.getModel(); // tabela
        tabelaProdutoModel.setRowCount(0);
      
        ProdutoController pc = new ProdutoController();
        EstoqueController ec = new EstoqueController();
        
        ArrayList<Produto> produtosListados = null;
        try {
            produtosListados = pc.listarProdutos();
        } catch (ExceptionDAO ex) {
            java.util.logging.Logger.getLogger(ApresentaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        try {
           for(Produto produto: produtosListados){
                
                long quantidade = ec.quantidadeProduto(produto.getId_produto());
                System.out.println("Quantidade = " + quantidade);
                tabelaProdutoModel.addRow(new Object[]{
                    produto.getNome(),
                    produto.getMarca(),
                    Dinheiro.parseString(produto.getPreco()),
                    quantidade,
                    produto.getId_produto()                  
                });

            }

        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao listarProdutos" + e);
        }

        return tabelaProdutoModel;
    }

    public DefaultTableModel apresentaProdutos(JTable tabela, String nome) {

        DefaultTableModel tabelaProdutoModel = (DefaultTableModel) tabela.getModel(); // tabela

        tabelaProdutoModel.setRowCount(0);

        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");

        ProdutoController pc = new ProdutoController();
        EstoqueController ec = new EstoqueController();
        
        ArrayList<Produto> produtosListados = null;
        try {
            produtosListados = pc.listarProdutos(nome);
        } catch (ExceptionDAO ex) {
            java.util.logging.Logger.getLogger(ApresentaProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        try {
            for(Produto produto: produtosListados){
                
                long quantidade = ec.quantidadeProduto(produto.getId_produto());
                tabelaProdutoModel.addRow(new Object[]{
                    produto.getNome(),
                    produto.getMarca(),
                    Dinheiro.parseString(produto.getPreco()),
                    quantidade,
                    produto.getId_produto()                  
                });

            }

        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao listarProdutos" + e);
        }
        return tabelaProdutoModel;
    }

    //CLIENTES
    public DefaultTableModel apresentaClientes(JTable tabela) {

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

    public DefaultTableModel apresentaClientes(JTable tabela, String nome) {

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
    public DefaultTableModel apresentaAgendamentos(JTable tabela, int opc, String nome) {

        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);

        AgendamentoController ag = new AgendamentoController();
        ServicoController sc = new ServicoController();
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
                    agendamentos = ag.listarAgendamentosHoje();
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
                case 5:
                    agendamentos = ag.listarAgendamentosNaoRealizados();
                    break;
            }

            for (Agendamento g : agendamentos) {
                
                ArrayList<Servico> servicosAgendamento = sc.buscarServicoPeloAgendamento(g.getId());      
                LocalTime inicioAgendamento = g.getHorario();
                
                LocalTime fimAgendamento = inicioAgendamento;
                
                int horas = 0;
                int minutos = 0;
                
                for (Servico s : servicosAgendamento) {
                    horas += s.getTempoGasto().getHour();
                    minutos += s.getTempoGasto().getMinute();
                }
                fimAgendamento = fimAgendamento.plusHours(horas);
                fimAgendamento = fimAgendamento.plusMinutes(minutos);
                
                Cliente cliente = new Cliente();
                String realizado;

                if (g.getRealizado()) {
                    realizado = "Realizado";
                } else {
                    realizado = "Não Realizado";
                }
                model.addRow(new Object[]{
                    cliente.buscarCliente(g.getCpfCliente()).getNOME(),
                    g.getData().format(formatterData),
                    g.getHorario().format(parserHora),
                    fimAgendamento.format(parserHora),
                    realizado,
                    g.getId()
                });
            }

        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco " + e);
        }
        return model;
    }
    
    public DefaultTableModel apresentaDespesas(JTable tabela, String ano){
        
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        DespesaController dc = new DespesaController();
        OrcamentoController oc = new OrcamentoController();
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        ArrayList<Despesa> despesas;
        
        try {
            modelo.setRowCount(0);
            
            if(ano == null){
                despesas = dc.listarDespesas();
            }else{
                despesas = dc.listarDespesas(ano);
            }
            
            
            if(despesas == null){
    
                return modelo;
            }
            
            for(Despesa d: despesas){
                
                Orcamento orcamentoAtual = oc.buscarOrcamento(d.getIdOrcamento());
                
                String lancamento =  d.getLancameto().format(formatterData);
                String vencimento = d.getVencimento().format(formatterData);
                
                String pagamento = (d.getPagamento() == null) ? "Não Pago" : d.getPagamento().format(formatterData);
                String formaPagamento = (d.getFormaPagamento() == null) ? "--" : d.getFormaPagamento();
                String status = d.isStatus() ? "Pagamento Realizado" : "Pendente";
                String valorPago = Dinheiro.parseString(d.getValorPago());
                
                modelo.addRow(new Object[]{
                    orcamentoAtual.getNome(),
                    lancamento,
                    vencimento,
                    pagamento,
                    formaPagamento,
                    valorPago,
                    status,
                    d.getIdDespesa()
                });
            }
            
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return modelo;
    }
    
    public DefaultTableModel apresentaDespesasVencimento(JTable tabela, int mes){
        
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        DespesaController dc = new DespesaController();
        OrcamentoController oc = new OrcamentoController();
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        ArrayList<Despesa> despesas;
        
        try {
            modelo.setRowCount(0);
            
             despesas = dc.listarDespesasVencimento(mes);
                      
            if(despesas == null){ return modelo; }
           
            for(Despesa d: despesas){
                
                Orcamento orcamentoAtual = oc.buscarOrcamento(d.getIdOrcamento());
                
                String lancamento =  d.getLancameto().format(formatterData);
                String vencimento = d.getVencimento().format(formatterData);
                
                String pagamento = (d.getPagamento() == null) ? "Não Pago" : d.getPagamento().format(formatterData);
                String formaPagamento = (d.getFormaPagamento() == null) ? "--" : d.getFormaPagamento();
                String status = d.isStatus() ? "Pagamento Realizado" : "Pendente";
                String valorPago = Dinheiro.parseString(d.getValorPago());
                
                modelo.addRow(new Object[]{
                    orcamentoAtual.getNome(),
                    lancamento,
                    vencimento,
                    pagamento,
                    formaPagamento,
                    valorPago,
                    status,
                    d.getIdDespesa()
                });
            }
            
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return modelo;
    }
    
    public DefaultTableModel apresentaDespesasLancamento(JTable tabela, int mes){
        
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        DespesaController dc = new DespesaController();
        OrcamentoController oc = new OrcamentoController();
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        ArrayList<Despesa> despesas;
        
        try {
            modelo.setRowCount(0);
            
             despesas = dc.listarDespesasLancamento(mes);
                      
            if(despesas == null){ return modelo; }
           
            for(Despesa d: despesas){
                
                Orcamento orcamentoAtual = oc.buscarOrcamento(d.getIdOrcamento());
                
                String lancamento =  d.getLancameto().format(formatterData);
                String vencimento = d.getVencimento().format(formatterData);
                
                String pagamento = (d.getPagamento() == null) ? "Não Pago" : d.getPagamento().format(formatterData);
                String formaPagamento = (d.getFormaPagamento() == null) ? "--" : d.getFormaPagamento();
                String status = d.isStatus() ? "Pagamento Realizado" : "Pendente";
                String valorPago = Dinheiro.parseString(d.getValorPago());
                
                modelo.addRow(new Object[]{
                    orcamentoAtual.getNome(),
                    lancamento,
                    vencimento,
                    pagamento,
                    formaPagamento,
                    valorPago,
                    status
                });
            }
            
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return modelo;
    }

}
