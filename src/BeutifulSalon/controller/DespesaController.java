/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.Ferramentas.Valida;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Despesa;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.view.Edicao.EditarDespesa;
import java.awt.HeadlessException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class DespesaController {

    public boolean CadastrarDespesa(long idOrcamento, String lancamento, String vencimento, String pagamento,
            String valorPago, String anotacao, String ano, String formaPagamento, boolean status) {

        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/M/uuuu");
        Despesa despesa = new Despesa();

        if (idOrcamento > 0 && Valida.isData(lancamento) && Valida.isData(vencimento)) {

            if (status) {

                if (Valida.isData(pagamento) && valorPago.length() > 0 && !ano.equals("null") && !formaPagamento.equals("")) {

                    try {
                        LocalDate dataLacamento = null;
                        LocalDate dataVencimento = null;
                        LocalDate dataPagamento = null;

                        try {
                            dataLacamento = LocalDate.parse(lancamento, formatterData);
                            dataVencimento = LocalDate.parse(vencimento, formatterData);
                            dataPagamento = LocalDate.parse(pagamento, formatterData);
                        } catch (DateTimeException e) {
                            JOptionPane.showMessageDialog(null, e);
                        }

                        if (dataLacamento.isAfter(dataVencimento)) {
                            JOptionPane.showMessageDialog(null, "A data de lançamento não pode ser maior que a de vencimento.");
                            return false;
                        }

                        despesa.setAnotacao(anotacao);
                        despesa.setIdOrcamento(idOrcamento);
                        despesa.setLancameto(dataLacamento);
                        despesa.setVencimento(dataVencimento);
                        despesa.setPagamento(dataPagamento);
                        despesa.setValorPago(Dinheiro.parseCent(Dinheiro.retiraCaracteres(valorPago)));
                        despesa.setFormaPagamento(formaPagamento);
                        despesa.setAno(ano);
                        despesa.setStatus(status);

                        despesa.cadastrarDespesa(despesa);
                        return true;
                    } catch (ExceptionDAO | HeadlessException e) {
                        JOptionPane.showMessageDialog(null, e);
                        return false;
                    }

                } else {
                    return false;
                }
            } else {

                try {
                    LocalDate dataLacamento = null;
                    LocalDate dataVencimento = null;

                    try {
                        dataLacamento = LocalDate.parse(lancamento, formatterData);
                        dataVencimento = LocalDate.parse(vencimento, formatterData);
                    } catch (DateTimeException e) {
                        JOptionPane.showMessageDialog(null, e);
                    }

                    if (dataLacamento.isAfter(dataVencimento)) {
                        JOptionPane.showMessageDialog(null, "A data de lançamento não pode ser maior que a de vencimento.");
                        return false;
                    }
                   
                    despesa.setIdOrcamento(idOrcamento);
                    despesa.setAno(ano);
                    despesa.setLancameto(dataLacamento);
                    despesa.setVencimento(dataVencimento);
                    despesa.setStatus(status);
                    despesa.setAnotacao(anotacao);
                    despesa.cadastrarDespesa(despesa);
                    return true;

                } catch (ExceptionDAO e) {
                    JOptionPane.showMessageDialog(null, e);
                    return false;
                }

            }

        } else {
            return false;
        }
    }
    
    public boolean atualizarDespesa(long idOrcamento, String lancamento, String vencimento, String pagamento,
            String valorPago, String anotacao, String ano, String formaPagamento, boolean status, long idDespesa) {

        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/M/uuuu");
        Despesa despesa = new Despesa();

        if (idOrcamento > 0 && Valida.isData(lancamento) && Valida.isData(vencimento)) {

            if (status) {

                if (Valida.isData(pagamento) && valorPago.length() > 0 && !ano.equals("null") && !formaPagamento.equals("")) {

                    try {
                        LocalDate dataLacamento = null;
                        LocalDate dataVencimento = null;
                        LocalDate dataPagamento = null;

                        try {
                            dataLacamento = LocalDate.parse(lancamento, formatterData);
                            dataVencimento = LocalDate.parse(vencimento, formatterData);
                            dataPagamento = LocalDate.parse(pagamento, formatterData);
                        } catch (DateTimeException e) {
                            JOptionPane.showMessageDialog(null, e);
                        }

                        if (dataLacamento.isAfter(dataVencimento)) {
                            JOptionPane.showMessageDialog(null, "A data de lançamento não pode ser maior que a de vencimento.");
                            return false;
                        }
                        despesa.setIdDespesa(idDespesa);
                        despesa.setAnotacao(anotacao);
                        despesa.setIdOrcamento(idOrcamento);
                        despesa.setLancameto(dataLacamento);
                        despesa.setVencimento(dataVencimento);
                        despesa.setPagamento(dataPagamento);
                        despesa.setValorPago(Dinheiro.parseCent(Dinheiro.retiraCaracteres(valorPago)));
                        despesa.setFormaPagamento(formaPagamento);
                        despesa.setAno(ano);
                        despesa.setStatus(status);

                        despesa.atualizarDespesa(despesa);
                        return true;
                    } catch (ExceptionDAO | HeadlessException e) {
                        JOptionPane.showMessageDialog(null, e);
                        return false;
                    }

                } else {
                    return false;
                }
            } else {

                try {
                    LocalDate dataLacamento = null;
                    LocalDate dataVencimento = null;

                    try {
                        dataLacamento = LocalDate.parse(lancamento, formatterData);
                        dataVencimento = LocalDate.parse(vencimento, formatterData);
                    } catch (DateTimeException e) {
                        JOptionPane.showMessageDialog(null, e);
                    }

                    if (dataLacamento.isAfter(dataVencimento)) {
                        JOptionPane.showMessageDialog(null, "A data de lançamento não pode ser maior que a de vencimento.");
                        return false;
                    }
                    
                    despesa.setIdDespesa(idDespesa);
                    despesa.setIdOrcamento(idOrcamento);
                    despesa.setAno(ano);
                    despesa.setLancameto(dataLacamento);
                    despesa.setVencimento(dataVencimento);
                    despesa.setStatus(status);
                    despesa.setAnotacao(anotacao);
                    despesa.atualizarDespesa(despesa);
                    return true;

                } catch (ExceptionDAO e) {
                    JOptionPane.showMessageDialog(null, e);
                    return false;
                }

            }

        } else {
            return false;
        }
    }
    
    public Despesa listarDespesa(long idDespesa) throws ExceptionDAO{
        return new Despesa().listarDespesa(idDespesa);
    }
    
    public boolean editarDespesa(long idDespesa) throws ExceptionDAO{
        
        try {
             Despesa despesa = listarDespesa(idDespesa);
             
             if(despesa != null){
                 new EditarDespesa(despesa).setVisible(true);
             }else{
                 return false;
             }
        } catch (ExceptionDAO e) {
            
        }     
        return true;       
    }
    
    public boolean verificaExistenciaPagamento(long idOrcamento, String dataPagamento, boolean pagamento){
        
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/M/uuuu");
        ManipulaData manipulaData = new ManipulaData();
        
        LocalDate dataPagamentoFormatada = LocalDate.parse(dataPagamento, formatterData);
        Month mesReferente = dataPagamentoFormatada.getMonth();
        long qtd = 0;
        long inicioMes = 0;
        long fimMes = 0;
        int ano = 0;
        
        try {
           inicioMes = manipulaData.inicioDoMes(dataPagamentoFormatada, mesReferente);
           fimMes = manipulaData.fimDoMes(dataPagamentoFormatada, mesReferente);
           ano = dataPagamentoFormatada.getYear();
        } catch (Exception e) {
            
        }
        
        try {
            qtd = new Despesa().verificaExistencia(idOrcamento, inicioMes, fimMes, String.valueOf(ano) , pagamento);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        
        return qtd > 0;
    }
    
    public ArrayList<Despesa> listarDespesas() throws ExceptionDAO{
        
        return new Despesa().listarDespesas();  
    }
    
    public ArrayList<Despesa> listarDespesas(String ano) throws ExceptionDAO{
        
        return new Despesa().listarDespesas(ano);  
    }
    
     public ArrayList<Despesa> listarDespesasVencimento(int mes) throws ExceptionDAO{
        
         
         Month mesSelecionado = Month.of(mes);
         LocalDate dataAtual = LocalDate.now();
         ManipulaData manipulaData = new ManipulaData();
         long inicioMes = 0;
         long fimMes = 0;
         
         try {
             inicioMes = manipulaData.inicioDoMes(dataAtual, mesSelecionado);
             fimMes = manipulaData.fimDoMes(dataAtual, mesSelecionado);
             
         } catch (Exception e) {
         }
          
        return new Despesa().listarDespesasVencimento(inicioMes, fimMes);  
    }
     
      public ArrayList<Despesa> listarDespesasLancamento(int mes) throws ExceptionDAO{
        
         
         Month mesSelecionado = Month.of(mes);
         LocalDate dataAtual = LocalDate.now();
         ManipulaData manipulaData = new ManipulaData();
         long inicioMes = 0;
         long fimMes = 0;
         
         try {
             inicioMes = manipulaData.inicioDoMes(dataAtual, mesSelecionado);
             fimMes = manipulaData.fimDoMes(dataAtual, mesSelecionado);
             
         } catch (Exception e) {
         }
          
        return new Despesa().listarDespesasLancamento(inicioMes, fimMes);  
    }
}
