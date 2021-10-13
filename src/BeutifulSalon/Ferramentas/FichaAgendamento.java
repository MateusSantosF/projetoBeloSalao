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
package BeutifulSalon.Ferramentas;

import BeutifulSalon.controller.ClienteController;
import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.Dinheiro;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Mateus
 */
public class FichaAgendamento {
    
    
    public void imprimirFicha(Agendamento ag){
        
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
        
        Map<String, Object> params = new HashMap<>();
        params.put("nomeCliente", new ClienteController().buscarCliente(ag.getIdCliente()).getNomeCompleto());
        params.put("dataAgendamento", formatterData.format(ag.getData()));
        params.put("horarioAgendamento", formatterHora.format(ag.getHorario()) + "h");
        params.put("servicosSolicitadosAgendamento", ag.getServicos());
        params.put("formaDePagamento", ag.getFormaDePagamento());
        params.put("statusPagamento",  ag.isPago() ? "Pago" : "Pendente");
        params.put("subtotal", Dinheiro.parseString(ag.getTotal() + ag.getDesconto() - ag.getValorAdicional()));
        params.put("valorAdicional", Dinheiro.parseString(ag.getValorAdicional()));
        params.put("valorDesconto", Dinheiro.parseString(ag.getDesconto()));
        params.put("valorTotal", Dinheiro.parseString(ag.getTotal()));

        try {

            JasperReport j = JasperCompileManager.compileReport("src\\FichaAgendamento.jrxml");
            JasperPrint rp = JasperFillManager.fillReport(j, params, new JREmptyDataSource());
            JasperPrintManager.printPage(rp, 0, true);
    
        } catch (JRException e) {

            JOptionPane.showMessageDialog(null, e);

        }
    }
    
}
