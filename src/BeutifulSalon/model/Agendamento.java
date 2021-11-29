/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import BeutifulSalon.dao.AgendamentoDAO;
import BeutifulSalon.dao.ExceptionDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mateus
 */
public class Agendamento {
    
    private long idColaborador;
    private long idAgendamento;
    private long idCliente;
    private long total;
    private long desconto;
    private long valorAdicional;
    private boolean pago;
    private String formaDePagamento;
    private LocalDate data;
    private LocalTime horario;
    private LocalTime fimAgendamento;
    private Boolean realizado;
    private List<Servico> servicos;
    private List<Item> produtosComprados;
    private List<Item> antigosprodutosComprados;
    
    public Agendamento() {
    };

    
    
    public Agendamento(LocalDate data, LocalTime horario, long idCliente, ArrayList<Servico> servicos) {

        this.data = data;
        this.horario = horario;
        this.idCliente = idCliente;
        this.servicos = servicos;
    }

    public long getDesconto() {
        return desconto;
    }

    public void setDesconto(long desconto) {
        this.desconto = desconto;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Boolean getRealizado() {
        return realizado;
    }

    public void setRealizado(Boolean realizado) {
        this.realizado = realizado;
    }

    public long getIdAgendamento() {
        return idAgendamento;
    }

    public long getValorAdicional() {
        return valorAdicional;
    }

    public List<Item> getAntigosprodutosComprados() {
        return antigosprodutosComprados;
    }

    public void setAntigosprodutosComprados(List<Item> antigosprodutosComprados) {
        this.antigosprodutosComprados = antigosprodutosComprados;
    }

   
    
    

    public void setValorAdicional(long valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    public long getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(long idColaborador) {
        this.idColaborador = idColaborador;
    }
    
    

    public void setIdAgendamento(long idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public LocalTime getFimAgendamento() {
        return fimAgendamento;
    }

    public void setFimAgendamento(LocalTime fimAgendamento) {
        this.fimAgendamento = fimAgendamento;
    }

    public List<Item> getProdutosComprados() {
        return produtosComprados;
    }

    public void setProdutosComprados(List<Item> produtosComprados) {
        this.produtosComprados = produtosComprados;
    }
    
    

    public String getTempoPrevisto() {

        LocalTime fimAgendamento = LocalTime.MIDNIGHT;
        int horas = 0;
        int minutos = 0;

        List<Servico> servicos = this.servicos;
        if (servicos != null) {
            for (Servico s : servicos) {
                horas += s.getTempoGasto().getHour();
                minutos += s.getTempoGasto().getMinute();
            }

            fimAgendamento = fimAgendamento.plusHours(horas);
            fimAgendamento = fimAgendamento.plusMinutes(minutos);
        }
        
        return fimAgendamento.toString() + "h";
    }

    

    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }

    public void cadastraAgendamento(Agendamento agendamento) throws ExceptionDAO, SQLException {
        new AgendamentoDAO().cadastraAgendamento(agendamento);
    }

    public void atualizarAgendamento(Agendamento agendamento) throws ExceptionDAO, SQLException {
        new AgendamentoDAO().atualizarAgendamento(agendamento);
    }

    public ArrayList<Agendamento> listarAgendamentos() throws ExceptionDAO {
        return new AgendamentoDAO().listarAgendamentos();
    }

    public Agendamento listarAgendamento(long idAgendamento) throws ExceptionDAO {
        return new AgendamentoDAO().listarAgendamento(idAgendamento);
    }

    public ArrayList<Agendamento> listarAgendamentos(LocalDate data) throws ExceptionDAO {
        return new AgendamentoDAO().listarAgendamentos(data);
    }

    public ArrayList<Agendamento> listarAgendamentosRealizados(LocalDate data) {
        return new AgendamentoDAO().listarAgendamentosRealizados(data);
    }

    public ArrayList<Agendamento> listarAgendamentosHoje() throws ExceptionDAO {
        return new AgendamentoDAO().listarAgendamentosHoje();
    }

    public ArrayList<Agendamento> listarAgendamentosAmanha() throws ExceptionDAO {
        return new AgendamentoDAO().listarAgendamentosAmanha();
    }

    public ArrayList<Agendamento> listarAgendamentosSemana() throws ExceptionDAO {
        return new AgendamentoDAO().listarAgendamentosSemana();
    }

    public ArrayList<Agendamento> listarAgendamentosNome(String nome) throws ExceptionDAO {
        return new AgendamentoDAO().listarAgendamentosNome(nome);
    }

    public ArrayList<Servico> listarServicosAgendamento(long idAgendamento) throws ExceptionDAO {
        return new AgendamentoDAO().listaServicosAgendamento(idAgendamento);
    }

    public ArrayList<Agendamento> listarAgendamentosNaoRealizados() throws ExceptionDAO {
        return new AgendamentoDAO().listarAgendamentosNaoRealizados();
    }

    public long retornaSomaDeAgendamentosMensal() throws ExceptionDAO {
        return new AgendamentoDAO().retornaSomaDeAgendamentosMensal();
    }

    public long retornaSomaDeAgendamentosMensal(Month mes) throws ExceptionDAO {
        return new AgendamentoDAO().retornaSomaDeAgendamentosMensal(mes);
    }

    public boolean excluirAgendamento(Agendamento agendamento) throws ExceptionDAO {
        return new AgendamentoDAO().excluirAgendamento(agendamento);
    }

    public ArrayList<Agendamento> listarAgendamentosNaoPagos() throws ExceptionDAO {
        return new AgendamentoDAO().listarAgendamentosNaoPagos();
    }

    public List<Agendamento> listarAgendamentosIDCliente(long idCliente) throws ExceptionDAO {
        return new AgendamentoDAO().listarAgendamentosIDCliente(idCliente);
    }

      public List<Agendamento> listarAgendamentosPorData(LocalDate inicio, LocalDate fim) throws ExceptionDAO{
        return new AgendamentoDAO().listarAgendamentosPorData(inicio, fim);              
    }
}
