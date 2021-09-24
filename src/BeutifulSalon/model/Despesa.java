/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import BeutifulSalon.dao.DespesaDAO;
import BeutifulSalon.dao.ExceptionDAO;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateus
 */
public class Despesa {
    
    private long idDespesa;
    private long valorPago;
    private String ano;
    private String formaPagamento;
    private LocalDate lancameto;
    private LocalDate vencimento;
    private LocalDate pagamento;
    private boolean status;
    private String anotacao;
    private long idOrcamento;

    
    public Despesa(){};
    
    public Despesa(long idDespesa, long valorPago, String ano, LocalDate lancameto, LocalDate vencimento, LocalDate pagamento, boolean status, String anotacao, long idOrcamento, String formaPagamento) {
        this.idDespesa = idDespesa;
        this.valorPago = valorPago;
        this.ano = ano;
        this.lancameto = lancameto;
        this.vencimento = vencimento;
        this.pagamento = pagamento;
        this.status = status;
        this.anotacao = anotacao;
        this.idOrcamento = idOrcamento;
        this.formaPagamento = formaPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
    
    public long getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(long idDespesa) {
        this.idDespesa = idDespesa;
    }

    public long getValorPago() {
        return valorPago;
    }

    public void setValorPago(long valorPago) {
        this.valorPago = valorPago;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public LocalDate getLancameto() {
        return lancameto;
    }

    public void setLancameto(LocalDate lancameto) {
        this.lancameto = lancameto;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public LocalDate getPagamento() {
        return pagamento;
    }

    public void setPagamento(LocalDate pagamento) {
        this.pagamento = pagamento;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }

    public long getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(long idOrcamento) {
        this.idOrcamento = idOrcamento;
    }
    
    
    
    public void cadastrarDespesa(Despesa despesa) throws ExceptionDAO{
        new DespesaDAO().cadastrarDespesa(despesa);
    }
    
    public long verificaExistencia(long idOrcamento, long inicioMes, long fimMes, String ano, boolean pagamento) throws ExceptionDAO{
        return new DespesaDAO().verificaExistencia(idOrcamento, inicioMes, fimMes, ano, pagamento);
    }
    
    public ArrayList<Despesa> listarDespesas() throws ExceptionDAO{
        return  new DespesaDAO().listarDespesas();
    }
    
    public List<Despesa> listarDespesas(String ano) throws ExceptionDAO{
        return  new DespesaDAO().listarDespesas(ano);
    }

    public ArrayList<Despesa> listarDespesasVencimento(long inicioMes, long fimMes) throws ExceptionDAO{
        return new DespesaDAO().listarDespesasVencimento(inicioMes, fimMes);
    }
    
    public ArrayList<Despesa> listarDespesasLancamento(long inicioMes, long fimMes) throws ExceptionDAO {
        return new DespesaDAO().listarDespesasLancamento(inicioMes, fimMes);
    }
    
    public Despesa buscarDespesaPaga(LocalDate ano, Month mes, long idOrcamento) throws ExceptionDAO{
        return new DespesaDAO().buscarDespesaPaga(ano,mes,idOrcamento);
    }
    public Despesa listarDespesa(long idDespesa) throws ExceptionDAO {
        return new DespesaDAO().listarDespesa(idDespesa);
    }

    public void atualizarDespesa(Despesa despesa) throws ExceptionDAO{
       new DespesaDAO().atualizarDespesa(despesa);
    }

    public long verificaExistencia(long idOrcamento, long inicioMes, long fimMes, String ano) throws ExceptionDAO {
        return new DespesaDAO().verificaExistencia(idOrcamento, inicioMes, fimMes, ano);
    }

    public boolean excluirDespesa(long idDespesa)throws ExceptionDAO {
        return new DespesaDAO().excluirDespesa(idDespesa);
    }

    public long retornaSomaDeDespesasMensais(Month mes) throws ExceptionDAO {
        return new DespesaDAO().retornaSomaDeDespesasMensais(mes);
    }
}
