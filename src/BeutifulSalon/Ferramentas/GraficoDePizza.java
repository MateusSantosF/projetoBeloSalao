/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Servico;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author Mateus
 */
public class GraficoDePizza {

    private JPanel painelGrafico;
    private List<Servico> servicos;

    public GraficoDePizza() {
    }

    public GraficoDePizza(JPanel painel, List<Servico> servicos) {
        this.painelGrafico = painel;
        this.servicos = servicos;
    }

    private PieDataset montaDataSet() {

        DefaultPieDataset dataset = new DefaultPieDataset();

        if (servicos.isEmpty()) {
            dataset.setValue("Nenhum Serviço Realizado", 0);
        } else {
            for (Servico s : servicos) {
                if (s != null) {
                    Long qtd = s.getQuantidadeMensal();
                    dataset.setValue(s.getNome(), qtd.doubleValue());
                }
            }
        }

        return dataset;

    }

    private JFreeChart criaGrafico() {

        PieDataset dataset = montaDataSet();
        RingPlot plot = new RingPlot(dataset);

        plot.setOutlineVisible(false);
        plot.setShadowPaint(null);
        plot.setOutlineVisible(false);
        plot.setBackgroundPaint(null);
        plot.setLabelGenerator(null);
        plot.getOutlinePaint();
        plot.setSectionDepth(0.25); //Define tamanho/expressura das seções
        plot.setSeparatorStroke(new BasicStroke(8)); // aumenta expressura da linha que separa as seções
        plot.setSeparatorPaint(Color.WHITE);

        plot.setOuterSeparatorExtension(0);
        plot.setInnerSeparatorExtension(0);

        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{1} ({2})")); // Mostra quantidade {1} e apresenta a porcentagem {2}
        plot.setLabelBackgroundPaint(null);
        //plot.setSimpleLabels(true); // Labeis simples ficam em cima das seções ao invez de criar aquela risquinha
        plot.setSectionPaint(dataset.getKey(0), new Color(0, 172, 178)); //Altera cores das seções com base na primeira cor passada

        Font font = new Font("Arial", 0, 12);
        plot.setLabelFont(font);
        plot.setLabelBackgroundPaint(new Color(36, 46, 66));
        plot.setLabelPaint(Color.WHITE);

        JFreeChart chart = new JFreeChart("Top 5 Serviços", new Font("Arial", 0, 28), plot, true);
        chart.setBackgroundPaint(Color.WHITE);
        chart.setPadding(new RectangleInsets(20, 8, 20, 2));

        return chart;
    }

    public void plotaGrafico() {

        int largura = 400;
        int altura = 400;

        ChartPanel g = new ChartPanel(criaGrafico(), largura, altura, largura, altura, 700, 700, true,
                true, true, true, true, true);

        g.setBackground(Color.WHITE);
        painelGrafico.add(g);
    }

}
