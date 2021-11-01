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
package BeutifulSalon.controller;

import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.Ferramentas.Valida;
import BeutifulSalon.dao.AgendamentoDAO;
import BeutifulSalon.dao.VendaProdutoDAO;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.RelatorioAgendamento;
import BeutifulSalon.model.RelatorioDespesa;
import BeutifulSalon.model.RelatorioVenda;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.swing.JRViewerToolbar;
import net.sf.jasperreports.view.JRSaveContributor;
import net.sf.jasperreports.view.save.JRDocxSaveContributor;
import net.sf.jasperreports.view.save.JRPdfSaveContributor;

/**
 *
 * @author Mateus
 */
public class RelatorioController {

    private String localAtual = "src";

    public boolean gerarRelatorioVenda(String dataInicio, String dataFim) {

        if (Valida.isData(dataInicio) && Valida.isData(dataFim)) {

            ManipulaData md = new ManipulaData();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            LocalDate inicio = LocalDate.parse(dataInicio, formatter);
            LocalDate fim = LocalDate.parse(dataFim, formatter);

            if (inicio.isAfter(fim)) {
                return false;
            }

            List<RelatorioVenda> datasource = new VendaProdutoDAO().relatorioVendas(md.meiaNoite(inicio), md.meiaNoiteAmanha(fim));
            long totalVendido = 0;
            long totalDescontos = 0;

            for (RelatorioVenda dt : datasource) {
                totalVendido += Dinheiro.parseCent(Dinheiro.retiraCaracteres(dt.getTotal()));
                totalDescontos += Dinheiro.parseCent(Dinheiro.retiraCaracteres(dt.getDesconto()));
            }

            Map<String, Object> params = new HashMap<>();
            params.put("DataInicio", inicio.format(formatterData));
            params.put("DataFim", fim.format(formatterData));
            params.put("TotalVendas", Dinheiro.parseString(totalVendido));
            params.put("TotalDescontos", Dinheiro.parseString(totalDescontos));
            params.put("TotalFinal", Dinheiro.parseString(totalVendido - totalDescontos));
            params.put("numeroTotalVendas", String.valueOf(datasource.size()));

            try {

                JasperReport j = JasperCompileManager.compileReport(localAtual + "\\RelatorioVendas.jrxml");
                JasperPrint rp = JasperFillManager.fillReport(j, params, new JRBeanCollectionDataSource(datasource));

                JDialog tela = new JDialog();
                tela.setSize(1080, 720);

                JRViewer painel = new MyJRViewer(rp);

                tela.getContentPane().add(painel);

                tela.setVisible(true);
                return true;
            } catch (JRException e) {

                JOptionPane.showMessageDialog(null, e);
                return true;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Verifique as datas digitadas e tente novamente.");
            return false;
        }

    }

    public class MyJRViewer extends JRViewer {
        //define the constructor that you use

        public MyJRViewer(JasperPrint jasperPrint) {
            super(jasperPrint);
        }

        @Override
        protected JRViewerToolbar createToolbar() {
            JRViewerToolbar toolbar = super.createToolbar();

            Locale locale = viewerContext.getLocale();
            ResourceBundle resBundle = viewerContext.getResourceBundle();
            JRPdfSaveContributor pdf = new JRPdfSaveContributor(locale, resBundle);
            JRDocxSaveContributor docx = new JRDocxSaveContributor(locale, resBundle);
            toolbar.setSaveContributors(new JRSaveContributor[]{pdf, docx});

            return toolbar;
        }
    }

    public boolean gerarRelatorioAgendamento(String dataInicio, String dataFim) {

        if (Valida.isData(dataInicio) && Valida.isData(dataFim)) {
            ManipulaData md = new ManipulaData();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            LocalDate inicio = LocalDate.parse(dataInicio, formatter);
            LocalDate fim = LocalDate.parse(dataFim, formatter);

            List<RelatorioAgendamento> datasource = new AgendamentoDAO().listarAgendamentosRelatorio(md.meiaNoite(inicio), md.meiaNoiteAmanha(fim));
            long totalAdicional = 0;
            long totalDescontos = 0;
            long totalFinal = 0;

            for (RelatorioAgendamento r : datasource) {
                totalAdicional += Dinheiro.parseCent(Dinheiro.retiraCaracteres(r.getValorAdicional()));
                totalDescontos += Dinheiro.parseCent(Dinheiro.retiraCaracteres(r.getDesconto()));
                totalFinal += Dinheiro.parseCent(Dinheiro.retiraCaracteres(r.getTotal()));
            }

            Map<String, Object> params = new HashMap<>();
            params.put("DataInicio", inicio.format(formatterData));
            params.put("DataFim", fim.format(formatterData));
            params.put("totalAdicionais", Dinheiro.parseString(totalAdicional));
            params.put("totalDesconto", "-" + Dinheiro.parseString(totalDescontos));
            params.put("TotalFinal", Dinheiro.parseString(totalFinal));
            params.put("subTotal", Dinheiro.parseString(totalFinal + totalDescontos - totalAdicional));
            params.put("numeroTotalAgendamentos", String.valueOf(datasource.size()));

            try {

                JasperReport j = JasperCompileManager.compileReport(localAtual + "\\RelatorioDeAgendamentos.jrxml");
                JasperPrint rp = JasperFillManager.fillReport(j, params, new JRBeanCollectionDataSource(datasource));

                JDialog tela = new JDialog();
                tela.setSize(1080, 720);

                JRViewer painel = new MyJRViewer(rp);

                tela.getContentPane().add(painel);

                tela.setVisible(true);

            } catch (JRException e) {

                JOptionPane.showMessageDialog(null, e);
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    public boolean gerarRelatorioDespesas(String dataInicio, String dataFim) {

        if (Valida.isData(dataInicio) && Valida.isData(dataFim)) {
            ManipulaData md = new ManipulaData();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            LocalDate inicio = LocalDate.parse(dataInicio, formatter);
            LocalDate fim = LocalDate.parse(dataFim, formatter);

            List<RelatorioDespesa> datasource = new DespesaController().listarDespesasRelatorio(md.meiaNoite(inicio), md.meiaNoite(fim));

            long totalFinal = 0;

            for (RelatorioDespesa r : datasource) {

                totalFinal += r.getValorPago();
            }

            Map<String, Object> params = new HashMap<>();
            params.put("DataInicio", inicio.format(formatterData));
            params.put("DataFim", fim.format(formatterData));
            params.put("TotalFinal", Dinheiro.parseString(totalFinal));
            params.put("numeroTotalDespesas", String.valueOf(datasource.size()));

            try {

                JasperReport j = JasperCompileManager.compileReport(localAtual + "\\RelatorioDeDespesas.jrxml");
                JasperPrint rp = JasperFillManager.fillReport(j, params, new JRBeanCollectionDataSource(datasource));

                JDialog tela = new JDialog();
                tela.setSize(1080, 720);

                JRViewer painel = new MyJRViewer(rp);

                tela.getContentPane().add(painel);

                tela.setVisible(true);

            } catch (JRException e) {

                JOptionPane.showMessageDialog(null, e);
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    public boolean gerarRelatorioVenda(String dataInicio, String dataFim, String diretorio) {

        if (Valida.isData(dataInicio) && Valida.isData(dataFim)) {

            ManipulaData md = new ManipulaData();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            LocalDate inicio = LocalDate.parse(dataInicio, formatter);
            LocalDate fim = LocalDate.parse(dataFim, formatter);

            if (inicio.isAfter(fim)) {
                return false;
            }

            List<RelatorioVenda> datasource = new VendaProdutoDAO().relatorioVendas(md.meiaNoite(inicio), md.meiaNoiteAmanha(fim));
            long totalVendido = 0;
            long totalDescontos = 0;

            for (RelatorioVenda dt : datasource) {
                totalVendido += Dinheiro.parseCent(Dinheiro.retiraCaracteres(dt.getTotal()));
                totalDescontos += Dinheiro.parseCent(Dinheiro.retiraCaracteres(dt.getDesconto()));
            }

            Map<String, Object> params = new HashMap<>();
            params.put("DataInicio", inicio.format(formatterData));
            params.put("DataFim", fim.format(formatterData));
            params.put("TotalVendas", Dinheiro.parseString(totalVendido));
            params.put("TotalDescontos", Dinheiro.parseString(totalDescontos));
            params.put("TotalFinal", Dinheiro.parseString(totalVendido - totalDescontos));
            params.put("numeroTotalVendas", String.valueOf(datasource.size()));

            try {

                JasperReport j = JasperCompileManager.compileReport(localAtual + "\\RelatorioVendas.jrxml");
                JasperPrint rp = JasperFillManager.fillReport(j, params, new JRBeanCollectionDataSource(datasource));

                JasperExportManager.exportReportToPdfFile(rp, diretorio);

                return true;
            } catch (JRException e) {

                // JOptionPane.showMessageDialog(null, e);
                return true;
            }

        } else {

            return false;
        }

    }

    public boolean gerarRelatorioAgendamento(String dataInicio, String dataFim, String diretorio) {

        if (Valida.isData(dataInicio) && Valida.isData(dataFim)) {
            ManipulaData md = new ManipulaData();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            LocalDate inicio = LocalDate.parse(dataInicio, formatter);
            LocalDate fim = LocalDate.parse(dataFim, formatter);

            List<RelatorioAgendamento> datasource = new AgendamentoDAO().listarAgendamentosRelatorio(md.meiaNoite(inicio), md.meiaNoiteAmanha(fim));
            long totalAdicional = 0;
            long totalDescontos = 0;
            long totalFinal = 0;

            for (RelatorioAgendamento r : datasource) {
                totalAdicional += Dinheiro.parseCent(Dinheiro.retiraCaracteres(r.getValorAdicional()));
                totalDescontos += Dinheiro.parseCent(Dinheiro.retiraCaracteres(r.getDesconto()));
                totalFinal += Dinheiro.parseCent(Dinheiro.retiraCaracteres(r.getTotal()));
            }

            Map<String, Object> params = new HashMap<>();
            params.put("DataInicio", inicio.format(formatterData));
            params.put("DataFim", fim.format(formatterData));
            params.put("totalAdicionais", Dinheiro.parseString(totalAdicional));
            params.put("totalDesconto", "-" + Dinheiro.parseString(totalDescontos));
            params.put("TotalFinal", Dinheiro.parseString(totalFinal));
            params.put("subTotal", Dinheiro.parseString(totalFinal + totalDescontos - totalAdicional));
            params.put("numeroTotalAgendamentos", String.valueOf(datasource.size()));

            try {

                JasperReport j = JasperCompileManager.compileReport(localAtual + "\\RelatorioDeAgendamentos.jrxml");
                JasperPrint rp = JasperFillManager.fillReport(j, params, new JRBeanCollectionDataSource(datasource));
                JasperExportManager.exportReportToPdfFile(rp, diretorio);

            } catch (JRException e) {

                // JOptionPane.showMessageDialog(null, e);
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    public boolean gerarRelatorioDespesas(String dataInicio, String dataFim, String diretorio) {

        if (Valida.isData(dataInicio) && Valida.isData(dataFim)) {
            ManipulaData md = new ManipulaData();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd LLLL yyyy");
            LocalDate inicio = LocalDate.parse(dataInicio, formatter);
            LocalDate fim = LocalDate.parse(dataFim, formatter);

            List<RelatorioDespesa> datasource = new DespesaController().listarDespesasRelatorio(md.meiaNoite(inicio), md.meiaNoite(fim));

            long totalFinal = 0;

            for (RelatorioDespesa r : datasource) {

                totalFinal += r.getValorPago();
            }

            Map<String, Object> params = new HashMap<>();
            params.put("DataInicio", inicio.format(formatterData));
            params.put("DataFim", fim.format(formatterData));
            params.put("TotalFinal", Dinheiro.parseString(totalFinal));
            params.put("numeroTotalDespesas", String.valueOf(datasource.size()));

            try {

                JasperReport j = JasperCompileManager.compileReport(localAtual + "\\RelatorioDeDespesas.jrxml");
                JasperPrint rp = JasperFillManager.fillReport(j, params, new JRBeanCollectionDataSource(datasource));

                JasperExportManager.exportReportToPdfFile(rp, diretorio);

            } catch (JRException e) {

                JOptionPane.showMessageDialog(null, e);
                return false;
            }
        } else {
            return false;
        }

        return true;
    }
}
