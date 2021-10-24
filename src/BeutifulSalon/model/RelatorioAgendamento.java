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

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Mateus
 */
public class RelatorioAgendamento {
        
    
  
    private String nomeCliente;
    private String sobrenomeCliente;
    private long total;
    private long desconto;
    private long valorAdicional;
    private LocalDate data;
    private LocalTime horario;
    private List<Servico> servicosSolicitados;
    private boolean statusPagamento;
    private String formaPagamento;
    
    DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getSobrenomeCliente() {
        return sobrenomeCliente;
    }

    public void setSobrenomeCliente(String sobrenomeCliente) {
        this.sobrenomeCliente = sobrenomeCliente;
    }

    public String getTotal() {
        return Dinheiro.parseString(total);
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getDesconto() {
        return Dinheiro.parseString(desconto);
    }

    public void setDesconto(long desconto) {
        this.desconto = desconto;
    }

    public String getValorAdicional() {
        return Dinheiro.parseString(valorAdicional);
    }

    public void setValorAdicional(long valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    public String getData() {
        return formatterData.format(data);
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getHorario() {
        return formatterHora.format(horario);
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getStatusPagamento() {
        return statusPagamento ? "Pago" : "Pendente";
    }

    public void setStatusPagamento(boolean statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
    public List<Servico> getServicosSolicitados() {
        return servicosSolicitados;
    }

    public void setServicosSolicitados(List<Servico> servicosSolicitados) {
        this.servicosSolicitados = servicosSolicitados;
    }
    
    
    
    
    
}
