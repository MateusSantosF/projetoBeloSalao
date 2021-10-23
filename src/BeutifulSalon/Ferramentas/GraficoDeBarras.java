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

import BeutifulSalon.controller.AgendamentoController;
import BeutifulSalon.controller.CompraController;
import BeutifulSalon.controller.DespesaController;
import BeutifulSalon.controller.VendaController;
import BeutifulSalon.model.Dinheiro;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.Stroke;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.util.SortOrder;
import org.jfree.data.RangeType;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Mateus
 */
public class GraficoDeBarras {

    private Color verde = new Color(9, 213, 147);
    private Color vermelho = new Color(248, 67, 69);
    private Color azul = new Color(6, 116, 245);
    private Color amarelo = new Color(248, 164,53);

    public GraficoDeBarras() {

    }

    public void plotaGrafico(JPanel painel) {

        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setMouseZoomable(true);
        chartPanel.setMouseWheelEnabled(true);

        painel.add(chartPanel);
    }

    private CategoryDataset createDataset() {

        //controllers
        //entradas
        VendaController vc = new VendaController();
        AgendamentoController ag = new AgendamentoController();
        //saidas
        CompraController cc = new CompraController();
        DespesaController dc = new DespesaController();

        ManipulaData md = new ManipulaData();

        // row keys...
        final String series1 = "Saída";
        final String series2 = "Entrada";
        final String series3 = "Lucro";

        // column keys...
        final String category1 = "Jan";
        final String category2 = "Fev";
        final String category3 = "Mar";
        final String category4 = "Abr";
        final String category5 = "Mai";
        final String category6 = "Jun";
        final String category7 = "Jul";
        final String category8 = "Ago";
        final String category9 = "Set";
        final String category10 = "Out";
        final String category11 = "Nov";
        final String category12 = "Dez";

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        md.meses(LocalDate.now()).forEach(m -> {

            long entrada;
            long saida;

            switch (m) {

                case JANUARY:
                    entrada = Dinheiro.parseDecimal(vc.selecionaVendasPorMes(m) + ag.retornaSomaDeLucrosAgendamentosMensal(m));
                    saida = Dinheiro.parseDecimal(cc.retornaSomaDeComprasMensais(m) + dc.retornaSomaDeDespesasMensais(m));

                    dataset.addValue(saida, series1, category1);
                    dataset.addValue(entrada, series2, category1);
                    dataset.addValue(entrada - saida, series3, category1);
                    break;
                case FEBRUARY:
                    entrada = Dinheiro.parseDecimal(vc.selecionaVendasPorMes(m) + ag.retornaSomaDeLucrosAgendamentosMensal(m));
                    saida = Dinheiro.parseDecimal(cc.retornaSomaDeComprasMensais(m) + dc.retornaSomaDeDespesasMensais(m));

                    dataset.addValue(saida, series1, category2);
                    dataset.addValue(entrada, series2, category2);
                    dataset.addValue(entrada - saida, series3, category2);

                    break;
                case MARCH:
                    entrada = Dinheiro.parseDecimal(vc.selecionaVendasPorMes(m) + ag.retornaSomaDeLucrosAgendamentosMensal(m));
                    saida = Dinheiro.parseDecimal(cc.retornaSomaDeComprasMensais(m) + dc.retornaSomaDeDespesasMensais(m));

                    dataset.addValue(saida, series1, category3);
                    dataset.addValue(entrada, series2, category3);
                    dataset.addValue(entrada - saida, series3, category3);

                    break;
                case APRIL:
                    entrada = Dinheiro.parseDecimal(vc.selecionaVendasPorMes(m) + ag.retornaSomaDeLucrosAgendamentosMensal(m));
                    saida = Dinheiro.parseDecimal(cc.retornaSomaDeComprasMensais(m) + dc.retornaSomaDeDespesasMensais(m));

                    dataset.addValue(saida, series1, category4);
                    dataset.addValue(entrada, series2, category4);
                    dataset.addValue(entrada - saida, series3, category4);
                    break;
                case MAY:
                    entrada = Dinheiro.parseDecimal(vc.selecionaVendasPorMes(m) + ag.retornaSomaDeLucrosAgendamentosMensal(m));
                    saida = Dinheiro.parseDecimal(cc.retornaSomaDeComprasMensais(m) + dc.retornaSomaDeDespesasMensais(m));

                    dataset.addValue(saida, series1, category5);
                    dataset.addValue(entrada, series2, category5);
                    dataset.addValue(entrada - saida, series3, category5);

                    break;
                case JUNE:
                    entrada = Dinheiro.parseDecimal(vc.selecionaVendasPorMes(m) + ag.retornaSomaDeLucrosAgendamentosMensal(m));
                    saida = Dinheiro.parseDecimal(cc.retornaSomaDeComprasMensais(m) + dc.retornaSomaDeDespesasMensais(m));

                    dataset.addValue(saida, series1, category6);
                    dataset.addValue(entrada, series2, category6);
                    dataset.addValue(entrada - saida, series3, category6);

                    break;
                case JULY:
                    entrada = Dinheiro.parseDecimal(vc.selecionaVendasPorMes(m) + ag.retornaSomaDeLucrosAgendamentosMensal(m));
                    saida = Dinheiro.parseDecimal(cc.retornaSomaDeComprasMensais(m) + dc.retornaSomaDeDespesasMensais(m));

                    dataset.addValue(saida, series1, category7);
                    dataset.addValue(entrada, series2, category7);
                    dataset.addValue(entrada - saida, series3, category7);

                    break;
                case AUGUST:
                    entrada = Dinheiro.parseDecimal(vc.selecionaVendasPorMes(m) + ag.retornaSomaDeLucrosAgendamentosMensal(m));
                    saida = Dinheiro.parseDecimal(cc.retornaSomaDeComprasMensais(m) + dc.retornaSomaDeDespesasMensais(m));

                    dataset.addValue(saida, series1, category8);
                    dataset.addValue(entrada, series2, category8);
                    dataset.addValue(entrada - saida, series3, category8);

                    break;
                case SEPTEMBER:
                    entrada = Dinheiro.parseDecimal(vc.selecionaVendasPorMes(m) + ag.retornaSomaDeLucrosAgendamentosMensal(m));
                    saida = Dinheiro.parseDecimal(cc.retornaSomaDeComprasMensais(m) + dc.retornaSomaDeDespesasMensais(m));

                    dataset.addValue(saida, series1, category9);
                    dataset.addValue(entrada, series2, category9);
                    dataset.addValue(entrada - saida, series3, category9);

                    break;
                case OCTOBER:
                    entrada = Dinheiro.parseDecimal(vc.selecionaVendasPorMes(m) + ag.retornaSomaDeLucrosAgendamentosMensal(m));
                    saida = Dinheiro.parseDecimal(cc.retornaSomaDeComprasMensais(m) + dc.retornaSomaDeDespesasMensais(m));

                    dataset.addValue(saida, series1, category10);
                    dataset.addValue(entrada, series2, category10);
                    dataset.addValue(entrada - saida, series3, category10);

                    break;
                case NOVEMBER:
                    entrada = Dinheiro.parseDecimal(vc.selecionaVendasPorMes(m) + ag.retornaSomaDeLucrosAgendamentosMensal(m));
                    saida = Dinheiro.parseDecimal(cc.retornaSomaDeComprasMensais(m) + dc.retornaSomaDeDespesasMensais(m));

                    dataset.addValue(saida, series1, category11);
                    dataset.addValue(entrada, series2, category11);
                    dataset.addValue(entrada - saida, series3, category11);

                    break;
                case DECEMBER:
                    entrada = Dinheiro.parseDecimal(vc.selecionaVendasPorMes(m) + ag.retornaSomaDeLucrosAgendamentosMensal(m));
                    saida = Dinheiro.parseDecimal(cc.retornaSomaDeComprasMensais(m) + dc.retornaSomaDeDespesasMensais(m));

                    dataset.addValue(saida, series1, category12);
                    dataset.addValue(entrada, series2, category12);
                    dataset.addValue(entrada - saida, series3, category12);

                    break;

            }

        });

        return dataset;

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset the dataset.
     *
     * @return The chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
                "", // chart title
                "", // domain axis label
                "", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips?
                false // URLs?
        );

        //chart.setPadding(new RectangleInsets(20, 10, 0, 0));
        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();

        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setRangeCrosshairVisible(true);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setUpperMargin(1.0);
        rangeAxis.setRangeType(RangeType.POSITIVE);

        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();

        renderer.setDrawBarOutline(false);
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setSeriesStroke(0, new BasicStroke(0.1f));
        renderer.setSeriesStroke(1, new BasicStroke(0.1f));
        renderer.setSeriesStroke(2, new BasicStroke(0.1f));
        renderer.setItemMargin(-0.001);

        NumberFormat numeros = NumberFormat.getCurrencyInstance();

        renderer.setSeriesToolTipGenerator(0, new StandardCategoryToolTipGenerator("({0}, {1}) = {2}", numeros));
        renderer.setSeriesToolTipGenerator(1, new StandardCategoryToolTipGenerator("({0}, {1}) = {2}", numeros));
        renderer.setSeriesToolTipGenerator(2, new StandardCategoryToolTipGenerator("({0}, {1}) = {2}", numeros));
        renderer.setSeriesPaint(0, vermelho); // despesa
        renderer.setSeriesPaint(1, verde); // ganhos
        renderer.setSeriesPaint(2, azul); // lucros

        final CategoryAxis domainAxis = plot.getDomainAxis();
        
        domainAxis.setCategoryLabelPositionOffset(1);

        // OPTIONAL CUSTOMISATION COMPLETED.
        return chart;

    }

}
