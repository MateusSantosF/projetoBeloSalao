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
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.RingPlot;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.VerticalAlignment;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author Mateus
 */
public class GraficoDePizza {

    private JPanel painelGrafico;
    private List<Servico> servicos;
    private Color verde = new Color(9, 213, 147);
    private Color vermelho = new Color(248, 67, 69);
    private Color azul = new Color(6, 116, 245);
    private Color amarelo = new Color(248, 164, 53);
    private Color roxo = new Color(139, 113, 219);

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
                    Long qtd = s.getQuantidadeRealizada();
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
        plot.setSectionDepth(0.30); //Define tamanho/expressura das seções
        plot.setSeparatorStroke(new BasicStroke(5)); // aumenta expressura da linha que separa as seções
        plot.setSeparatorPaint(Color.WHITE);

        plot.setOuterSeparatorExtension(0);
        plot.setInnerSeparatorExtension(0);

        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
                "{1} ({2})")); // Mostra quantidade {1} e apresenta a porcentagem {2}
        plot.setLabelBackgroundPaint(null);
        //plot.setSimpleLabels(true); // Labeis simples ficam em cima das seções ao invez de criar aquela risquinha

        int cont = 0;

        for (Object k : dataset.getKeys()) {
            switch (cont) {
                case 0:
                    plot.setSectionPaint(dataset.getKey(0), verde);
                    break;
                case 1:
                    plot.setSectionPaint(dataset.getKey(1), vermelho);
                    break;
                case 2:
                    plot.setSectionPaint(dataset.getKey(2), azul);
                    break;
                case 3:
                    plot.setSectionPaint(dataset.getKey(3), amarelo);
                    break;

                case 4:
                    plot.setSectionPaint(dataset.getKey(4), roxo);
                    break;
            }
            cont++;
        }

        ManipulaFontes mp = new ManipulaFontes();
        Font font = mp.getFont(mp.BOLD, Font.BOLD, 12);
        Font fontLegend = mp.getFont(mp.BOLD, Font.BOLD, 14);
        plot.setLabelFont(font);
        plot.setLabelBackgroundPaint(new Color(36, 46, 66));
        plot.setLabelPaint(Color.WHITE);

        JFreeChart chart = new JFreeChart("", font, plot, true);

        chart.setBackgroundPaint(Color.WHITE);
        chart.setPadding(new RectangleInsets(-10, 0, 0, 0));
        chart.getLegend().setItemFont(fontLegend);
        chart.getLegend().setHorizontalAlignment(HorizontalAlignment.CENTER);

        return chart;
    }

    public void plotaGrafico() {

        int largura = 222;
        int altura = 100;

        ChartPanel g = new ChartPanel(criaGrafico(), largura, altura, largura, altura, 480, 840, true,
                true, true, true, true, true);

        g.setBackground(Color.WHITE);
        painelGrafico.add(g);
    }

}
