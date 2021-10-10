/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import BeutifulSalon.dao.ExceptionDAO;

import BeutifulSalon.dao.OrcamentoServicoDAO;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

/**
 *
 * @author Mateus
 */
public class OrcamentoServico {
    
    private boolean previsto; //previsto(true) | realizado(false)
    private long id_orcamento;
    private long id_servico; 
    private String nome;
    private String ano;
    private long jan;
    private long fev;
    private long mar;
    private long abr;
    private long mai;
    private long jun;
    private long jul;
    private long ago;
    private long set;
    private long out;
    private long nov;
    private long dez;
    private long quantidadeRealizada;

    public OrcamentoServico() { };
    
     public OrcamentoServico(boolean previsto, String nome, long jan, long fev, long mar,
            long abr, long mai, long jun, long jul, long ago, long set, long out, long nov, long dez, String ano) {
        this.previsto = previsto;
        this.nome = nome;
        this.jan = jan;
        this.fev = fev;
        this.mar = mar;
        this.abr = abr;
        this.mai = mai;
        this.jun = jun;
        this.jul = jul;
        this.ago = ago;
        this.set = set;
        this.out = out;
        this.nov = nov;
        this.dez = dez;
        this.ano = ano;
    }

    public OrcamentoServico(boolean previsto, String nome, long jan, long fev, long mar,
            long abr, long mai, long jun, long jul, long ago, long set, long out, long nov, long dez, String ano, long id_orcamento) {
        this.previsto = previsto;
        this.nome = nome;
        this.jan = jan;
        this.fev = fev;
        this.mar = mar;
        this.abr = abr;
        this.mai = mai;
        this.jun = jun;
        this.jul = jul;
        this.ago = ago;
        this.set = set;
        this.out = out;
        this.nov = nov;
        this.dez = dez;
        this.ano = ano;
        this.id_orcamento = id_orcamento;
    }

    public OrcamentoServico(boolean previsto, String nomeServico, long id_servico, long jan, long fev, long mar,
            long abr, long mai, long jun, long jul, long ago, long set, long out, long nov, long dez, String ano) {
        this.previsto = previsto;
        this.nome = nomeServico;
        this.jan = jan;
        this.fev = fev;
        this.mar = mar;
        this.abr = abr;
        this.mai = mai;
        this.jun = jun;
        this.jul = jul;
        this.ago = ago;
        this.set = set;
        this.out = out;
        this.nov = nov;
        this.dez = dez;
        this.ano = ano;
        this.id_servico = id_servico;
    }

    public long getQuantidadeRealizada() {
        return quantidadeRealizada;
    }

    public void setQuantidadeRealizada(long quantidadeRealizada) {
        this.quantidadeRealizada = quantidadeRealizada;
    }
    
    public long getId_orcamento() {
        return id_orcamento;
    }

    public void setId_orcamento(long id_orcamento) {
        this.id_orcamento = id_orcamento;
    }

    public long getId_servico() {
        return id_servico;
    }

    public void setId_servico(long id_servico) {
        this.id_servico = id_servico;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public boolean isPrevisto() {
        return previsto;
    }

    public void setPrevisto(boolean previsto) {
        this.previsto = previsto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getJan() {
        return jan;
    }

    public void setJan(long jan) {
        this.jan = jan;
    }

    public long getFev() {
        return fev;
    }

    public void setFev(long fev) {
        this.fev = fev;
    }

    public long getMar() {
        return mar;
    }

    public void setMar(long mar) {
        this.mar = mar;
    }

    public long getAbr() {
        return abr;
    }

    public void setAbr(long abr) {
        this.abr = abr;
    }

    public long getMai() {
        return mai;
    }

    public void setMai(long mai) {
        this.mai = mai;
    }

    public long getJun() {
        return jun;
    }

    public void setJun(long jun) {
        this.jun = jun;
    }

    public long getJul() {
        return jul;
    }

    public void setJul(long jul) {
        this.jul = jul;
    }

    public long getAgo() {
        return ago;
    }

    public void setAgo(long ago) {
        this.ago = ago;
    }

    public long getSet() {
        return set;
    }

    public void setSet(long set) {
        this.set = set;
    }

    public long getOut() {
        return out;
    }

    public void setOut(long out) {
        this.out = out;
    }

    public long getNov() {
        return nov;
    }

    public void setNov(long nov) {
        this.nov = nov;
    }

    public long getDez() {
        return dez;
    }

    public void setDez(long dez) {
        this.dez = dez;
    }
    
    public void cadastrarOrcamentoServico(OrcamentoServico orcamento) throws ExceptionDAO {
        new OrcamentoServicoDAO().cadastrarOrcamentoServico(orcamento);
    }
    
    public ArrayList<OrcamentoServico> listarOrcamentosServico() throws ExceptionDAO {
        return new OrcamentoServicoDAO().listarOrcamentoServico();
    }

    public ArrayList<OrcamentoServico> listarOrcamentosServico(String ano) throws ExceptionDAO {
        return new OrcamentoServicoDAO().listarOrcamentoServico(ano);
    }

    public long somaTotalGanhoServicoMensal(long inicio, long fim, long idServico) throws ExceptionDAO {
        return new OrcamentoServicoDAO().somaTotalGanhoServicoMensal(inicio, fim, idServico);
    }
    public Servico listarOrcamentoServicorRealizado(LocalDate ano, Month mes, long idServico) throws ExceptionDAO {
        return new OrcamentoServicoDAO().listarOrcamentoServicorRealizado(ano, mes, idServico);
    }

    public boolean excluirOrcamento(long id_orcamento) throws ExceptionDAO{
        return new OrcamentoServicoDAO().excluirOrcamento(id_orcamento);
    }

    public boolean atualizarOrcamentoServico(OrcamentoServico sc)throws ExceptionDAO {
        return new OrcamentoServicoDAO().atualizarOrcamentoServico(sc);
    }

    public OrcamentoServico buscarOrcamentoServico(long id) throws ExceptionDAO {
        return new OrcamentoServicoDAO().buscarOrcamentoServico(id);
    }
    
     public OrcamentoServico buscarOrcamentoServicoPeloServico(long idServico) throws ExceptionDAO{
        return new OrcamentoServicoDAO().buscarOrcamentoServicoPeloServico(idServico);
     }
   
}
