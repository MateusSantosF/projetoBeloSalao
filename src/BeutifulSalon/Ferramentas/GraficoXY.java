/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.controller.ServicoController;
import BeutifulSalon.controller.VendaController;
import BeutifulSalon.model.Servico;
import BeutifulSalon.model.Venda;
import java.awt.BasicStroke;
import java.awt.Color;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;
import jdk.net.Sockets;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.time.Day;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author Mateus
 */
public class GraficoXY {

    private JPanel painelGrafico;
    private List<Servico> servicos;

    public GraficoXY(JPanel painelGrafico) {
        this.painelGrafico = painelGrafico;
    }

    private TimeSeriesCollection createDataset() {

        int anoAtual = LocalDate.now().getYear();
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        VendaController vc = new VendaController();
        ServicoController sc = new ServicoController();

        List<Servico> servicos = sc.selecionaServicosDoAno(anoAtual);
        List<Venda> vendas = vc.selecionaVendasDoAno(anoAtual);
        Collections.sort(vendas, new OrdenaVendaPorData());
        Collections.sort(servicos, new OrdenaServicoPorData());

        TimeSeries series1 = new TimeSeries("Vendas Realizadas");
        TimeSeries series2 = new TimeSeries("Serviços Realizados");

        series1.add(new Day(1, 1, anoAtual), 0.0); // adiciona 0 ao primeiro dia do ANO na serie 
        series2.add(new Day(1, 1, anoAtual), 0.0);

        if (!vendas.isEmpty()) {
            int dia = vendas.get(0).getData().getDayOfMonth();
            int mes = vendas.get(0).getData().getMonthValue();
            long qtd = 1;
            int contador = 1;
            
            if(vendas.size() == 1){
                series1.add(new Day(dia, mes, anoAtual), 1);
            }

            for (int i = 1; i < vendas.size(); i++) {

                String dataAtual = vendas.get(i).getData().toString();
                String dataAnterior = vendas.get(i - 1).getData().toString();

                if (dataAtual.equals(dataAnterior)) {
                    series1.addOrUpdate(new Day(dia, mes, anoAtual), ++qtd);
                   

                } else {
                    dia = vendas.get(i).getData().getDayOfMonth();
                    mes = vendas.get(i).getData().getMonthValue();
                    series1.add(new Day(dia, mes, anoAtual), qtd);
                    qtd = 1;
                }

            }
        }

        if (!servicos.isEmpty()) {
     
            int diaS = servicos.get(0).getDataRealizado().getDayOfMonth();
            int mesS = servicos.get(0).getDataRealizado().getMonthValue();
            long qtdS = 1;
            
            if(servicos.size() == 1){
                series2.add(new Day(diaS, mesS, anoAtual), 1);
            }
            
            for (int i = 1; i < servicos.size(); i++) {

                String dataAtual = servicos.get(i).getDataRealizado().toString();
                String dataAnterior = servicos.get(i - 1).getDataRealizado().toString();
                if (dataAtual.equals(dataAnterior)) {
                    series2.addOrUpdate(new Day(diaS, mesS, anoAtual), ++qtdS);
                } else {
                    diaS = servicos.get(i).getDataRealizado().getDayOfMonth();
                    mesS = servicos.get(i).getDataRealizado().getMonthValue();
                    series2.add(new Day(diaS, mesS, anoAtual), qtdS);
                    qtdS = 1;
                }

            }
        }

        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    public void plotaGrafico() {

        String chartTitle = "";
        String xAxisLabel = "Data";
        String yAxisLabel = "Quantidade";
        XYDataset dataset = createDataset();

        ValueAxis xAxis = new DateAxis(xAxisLabel);
        ValueAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setAutoRange(true);
      
        JFreeChart chart = ChartFactory.createTimeSeriesChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset, true, true, false);
        chart.setPadding(new RectangleInsets(20, 8, 20, 8));
        XYPlot plot = chart.getXYPlot();
   
        plot.setRangeCrosshairVisible(true);
       
       
        plot.setBackgroundPaint(Color.WHITE);
        //plot.setDomainGridlinePaint(Color.red);
        //plot.setRangeGridlinePaint(Color.red);
        
        SimpleDateFormat datas = new SimpleDateFormat("dd/MM/yyyy"); 
        NumberFormat numeros = NumberFormat.getIntegerInstance();
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
        renderer.setSeriesToolTipGenerator(0, new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT, datas, numeros));
        renderer.setSeriesToolTipGenerator(1, new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT, datas, numeros));
     
        renderer.setSeriesPaint(0, new Color(239, 70, 55));
        renderer.setSeriesPaint(1, new Color(0, 172, 178));
        renderer.setSeriesStroke(0, new BasicStroke(1.0f));
        renderer.setSeriesStroke(1, new BasicStroke(1.0f));
        plot.setRenderer(renderer);

        // adiciona JChartPanel ao painel chamador
        int largura = 500;
        int altura = 310;

        ChartPanel g = new ChartPanel(chart, largura, altura, largura, altura,800, 600, true,
                true, true, true, true, true);
   
        g.setMouseZoomable(true);
        g.setMouseWheelEnabled(true);
        painelGrafico.add(g);
    }
}
