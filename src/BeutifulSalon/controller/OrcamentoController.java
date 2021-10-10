/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.dao.OrcamentoServicoDAO;
import BeutifulSalon.model.Orcamento;
import BeutifulSalon.model.OrcamentoServico;
import BeutifulSalon.model.Servico;
import BeutifulSalon.view.Edicao.EditarOrcamentoServico;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class OrcamentoController {

    public boolean CadastrarOrcamento(boolean previsto, String nome, long jan, long fev, long mar,
            long abr, long mai, long jun, long jul, long ago, long set, long out, long nov, long dez, String ano) {

        if (nome.length() > 0 && !nome.equals("Ex: Conta de água") && jan >= 0
                && fev >= 0 && mar >= 0 && abr >= 0 && mai >= 0 && jun >= 0 && jul >= 0 && ago >= 0 && set >= 0
                && out >= 0 && nov >= 0 && dez >= 0 && ano != null && ano.length() > 0) {

            Orcamento orc = new Orcamento(previsto, nome, jan, fev, mar, abr, mai, jun, jul, ago, set, out, nov, dez, ano);

            try {
                orc.cadastrarOrcamento(orc);
            } catch (ExceptionDAO e) {
                JOptionPane.showMessageDialog(null, "Erro ao criar objeto orçamento" + e);
                return false;
            }

        } else {
            return false;
        }

        return true;

    }

    public boolean cadastraOrcamentoServico(boolean previsto, String nomeServico, long Idservico, long jan, long fev, long mar,
            long abr, long mai, long jun, long jul, long ago, long set, long out, long nov, long dez, String ano) {

        if (ano.length() > 0 && nomeServico.length() > 0 && Idservico > 0 && jan >= 0 && fev >= 0 && mar >= 0 && abr >= 0 && mai >= 0 && jun >= 0 && jul >= 0
                && ago >= 0 && set >= 0 && out >= 0 && nov >= 0 && dez >= 0) {

            try {
                OrcamentoServico orcamentoServico;
                orcamentoServico = new OrcamentoServico(previsto,
                        nomeServico.trim(), Idservico, jan, fev, mar, abr, mai, jun, jul, ago, set, out, nov, dez, ano.trim());

                orcamentoServico.cadastrarOrcamentoServico(orcamentoServico);

            } catch (ExceptionDAO e) {

                JOptionPane.showMessageDialog(null, "Erro OrcController " + e);
                return false;
            }

        } else {
            return false;
        }

        return true;
    }

    public boolean excluirOrcamento(long id_orcamento) {

        try {
            return new Orcamento().excluirOrcamento(id_orcamento);

        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir orçamento " + e);
            return false;
        }

    }

    public boolean excluirOrcamentoServico(long id_orcamento) {

        try {
            return new OrcamentoServico().excluirOrcamento(id_orcamento);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir orçamento serviço " + e);
            return false;
        }

    }

    public ArrayList<Orcamento> listarOrcamentos() throws ExceptionDAO {
        return new Orcamento().listarOrcamentos();
    }

    public ArrayList<Orcamento> listarOrcamentosPorNome(String nome) {
        try {
            return new Orcamento().listarOrcamentoPorNome(nome);
        } catch (Exception e) {
            System.out.println("Erro ao listar orçamentos por nome " + e);
        }
        return null;
    }

    public ArrayList<Orcamento> listarOrcamentos(String anoReferente) {
        try {
            return new Orcamento().listarOrcamentos(anoReferente);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao Listar orçamentos " + e);
        }
        return null;
    }

    public long somarOrcamento(String mes, String ano) throws ExceptionDAO {
        return new Orcamento().somarOrcamento(mes, ano);
    }

    public boolean AtualizarOrcamento(boolean previsto, String nome, long jan, long fev, long mar,
            long abr, long mai, long jun, long jul, long ago, long set, long out, long nov, long dez, String ano, long id) {

        if (nome.length() > 0) {
            Orcamento orc = new Orcamento(previsto, nome, jan, fev, mar, abr, mai, jun, jul, ago, set, out, nov, dez, ano, id);

            try {
                orc.AtualizarOrcamento(orc);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao criar objeto orçamento" + e);
                return false;
            }

        }

        return true;

    }

    public boolean editarOrcamento(long id_orcamento) {
        try {

            new Orcamento().editarOrcamento(id_orcamento);

        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar objeto orçamento" + e);
            return false;
        }

        return true;
    }

    // ORÇAMENTO SERVICO
    public ArrayList<OrcamentoServico> listarOrcamentosServico() {
        try {
            return new OrcamentoServico().listarOrcamentosServico();
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao litar orçamentos");
        }
        return null;
    }

    public ArrayList<OrcamentoServico> listarOrcamentosServico(String ano) {
        try {
            return new OrcamentoServico().listarOrcamentosServico(ano);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar orçamento serviço" + e);
        }
        return null;
    }

    public long somaTotalGanhoServicoMensal(long inicio, long fim, long idServico) {
        try {
            return new OrcamentoServico().somaTotalGanhoServicoMensal(inicio, fim, idServico);
        } catch (ExceptionDAO e) {
            System.out.println("Erro ao somar ganhos de serviço mensalmente");
        }
        return -1;
    }

    public Servico listarOrcamentoServicorRealizado(LocalDate ano, Month mes, long idServico) {
        try {
            return new OrcamentoServico().listarOrcamentoServicorRealizado(ano, mes, idServico);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao lsitar orçamento do servico" + e);
        }
        return null;
    }

    public Orcamento buscarOrcamento(long idOrcamento) {
        return new Orcamento().buscarOrcamento(idOrcamento);
    }

    public void editarOrcamentoServico(OrcamentoServico ocs) {

        new EditarOrcamentoServico(ocs).setVisible(true);
    }

    public boolean atualizarOrcamentoServico(boolean b, String nome, long idServico, long jan, long fev, long mar,
            long abr, long mai, long jun, long jul, long ago, long set, long out, long nov,
            long dez, String anoReferente, long idOrcamento) {

        if (anoReferente.replaceAll(" ", "").length() == 4) {
            try {
                OrcamentoServico sc = new OrcamentoServico();
                sc.setNome(nome);
                sc.setId_orcamento(idOrcamento);
                sc.setPrevisto(b);
                sc.setJan(jan);
                sc.setFev(fev);
                sc.setMar(mar);
                sc.setAbr(abr);
                sc.setMai(mai);
                sc.setJun(jun);
                sc.setJul(jul);
                sc.setAgo(ago);
                sc.setSet(set);
                sc.setOut(out);
                sc.setNov(nov);
                sc.setDez(dez);
                sc.setAno(anoReferente);

                return sc.atualizarOrcamentoServico(sc);

            } catch (ExceptionDAO e) {
                System.out.println("Erro ao editar orcamento " + e);
                return false;
            }
        } else {
            return false;
        }

    }

    public OrcamentoServico buscarOrcamentoServico(long id) {

        try {
            return new OrcamentoServico().buscarOrcamentoServico(id);
        } catch (ExceptionDAO e) {
            System.out.println("erro ao buscar orçamento serviço");
            return null;
        }
    }

    public OrcamentoServico buscarOrcamentoServicoPeloServico(long idServico) {
        try {
            return new OrcamentoServico().buscarOrcamentoServicoPeloServico(idServico);
        } catch (ExceptionDAO e) {
            System.out.println("erro ao buscar orçamento serviço pelo Serviço");
            return null;
        }

    }

}
