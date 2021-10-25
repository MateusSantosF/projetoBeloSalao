/*
 * The MIT License
 *
 * Copyright 2021 Mateus.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package BeutifulSalon.model;

import BeutifulSalon.dao.DespesaDAO;
import BeutifulSalon.dao.ExceptionDAO;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Mateus
 */
public class RelatorioDespesa extends Despesa {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private String nomeDespesa;

    
    public String getValorPagoDespesa(){
        return Dinheiro.parseString(super.getValorPago());
    }

    public String getDataLancamento() {
        return super.getLancameto().format(formatter);
    }

    public String getDataVencimento() {
        return super.getLancameto().format(formatter);
    }

    public String getNomeDespesa() {
        return nomeDespesa;
    }

    public void setNomeDespesa(String nomeDespesa) {
        this.nomeDespesa = nomeDespesa;
    }
    
    
    public String getDataPagamento() {
        if (super.isStatus()) {
            return super.getPagamento().format(formatter);
        }else{
            return "--";
        }
    }

    public String getAnoDespesa() {
        return super.getAno();
    }

    public String getStatusPagamento() {
        return super.isStatus() ? "Pago" : "Pendente";
    }
    
    
    public String getFormaPagamento() {
        
        String f = super.getFormaPagamento();
        
        return f == null || f == "" ?  "--" :super.getFormaPagamento();
    }

    public String getAnotacao(){
        
        String a = super.getAnotacao();
        
        return a  ==  null  || a == "" ? "Nada Informado." : super.getAnotacao();
    }
    
  
    public List<RelatorioDespesa> listarDespesasRelatorio(long inicio, long fim) throws ExceptionDAO {
        return new DespesaDAO().listarDespesasRelatorio(inicio, fim);
    }
    
    
    
}
