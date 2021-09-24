/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.view;

import BeutifulSalon.Ferramentas.GraficoDeBarras;
import BeutifulSalon.Ferramentas.GraficoDePizza;
import BeutifulSalon.Ferramentas.GraficoXY;
import BeutifulSalon.Ferramentas.JavaMail;
import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.Ferramentas.ManipulaFontes;
import BeutifulSalon.Ferramentas.OrdenaClientePorVisitas;
import BeutifulSalon.Ferramentas.OrdenaProdutoPorQuantidade;
import BeutifulSalon.Tabelas.CentralizaElementosTabela;
import BeutifulSalon.Tabelas.VendaTableModel;
import BeutifulSalon.controller.AgendamentoController;
import BeutifulSalon.controller.CabeleireiroController;
import BeutifulSalon.controller.ClienteController;
import BeutifulSalon.controller.CompraController;
import BeutifulSalon.controller.DespesaController;
import BeutifulSalon.controller.EstoqueController;
import BeutifulSalon.controller.ProdutoController;
import BeutifulSalon.controller.ServicoController;
import BeutifulSalon.controller.VendaController;
import BeutifulSalon.model.Cabeleireiro;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Produto;
import BeutifulSalon.view.modais.ModalEmail;
import BeutifulSalon.view.modais.ModalPostIt;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collections;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;


/**
 *
 * @author Mateus
 */
public class Dashboard extends javax.swing.JPanel {

    /**
     * Creates new form testePainel
     */
    
    private Color verde = new Color(57, 201, 114);
    private Color vermelho = new Color(248, 67, 69);
    private Cabeleireiro cabeleireiro;
    VendaTableModel modeloVendas = new VendaTableModel();
    private ModalPostIt modalPostIt;
    
    public Dashboard() {
        initComponents();

        ManipulaFontes mf = new ManipulaFontes();
        jLabelNomeCabeleireiro.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 40f)); //Nome Cabeleireiro

        jLabelBemVindoDeVolta.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 25f));
        jLabel3.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Agendamentos
        jLabelNumeroAgendamentos.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 25f));
        jLabel8.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Qtd estoque
        jLabelQtdEstoque.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 25f)); //Qtd estoque
        jLabel17.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Vendas hoje
        jLabelQtdVendas.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 25f)); //Vendas hoje
        jLabel7.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 25f));
        
        //jScrollPane3.setFont(mf.getFont(mf.SEMIBOLD, Font.PLAIN, 15f)); //Tabela
        jTable1.setFont(mf.getFont(mf.SEMIBOLD, Font.PLAIN, 15f)); // tabela
        
        jLabelHistoricoVenda.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 25f)); //Titulo grafico
        jLabelTop5.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 25f)); //Top5 serviços

        jLabel5.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Rank Clientes
        jLabelProdutosVendidos.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Produtos mais vendidos

        jLabel15.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 18f));
        jLabel16.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 18f));
        jLabelCliente1.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 18f));
        jLabelCliente2.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 18f));
        jLabelCliente3.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 18f));
        jLabelCliente4.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 18f));
        jLabelCliente5.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 18f));

       
        jLabelProduto1.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 18f));
        jLabelProduto2.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 18f));
        jLabelProduto3.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 18f));
        jLabelProduto4.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 18f));
        jLabelProduto5.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 18f));

        ClienteController clientec = new ClienteController();
        CabeleireiroController cc = new CabeleireiroController();
        AgendamentoController ag = new AgendamentoController();
        EstoqueController ec = new EstoqueController();
        ServicoController sc = new ServicoController();
        ProdutoController pc = new ProdutoController();
        VendaController vc = new VendaController();
        DespesaController dc = new DespesaController();
        CompraController compraController = new CompraController();

        JLabel[] labelNomesCliente = new JLabel[5];
        labelNomesCliente[0] = jLabelCliente1;
        labelNomesCliente[1] = jLabelCliente2;
        labelNomesCliente[2] = jLabelCliente3;
        labelNomesCliente[3] = jLabelCliente4;
        labelNomesCliente[4] = jLabelCliente5;

        JLabel[] labelQuantidadeVisitas = new JLabel[5];
        labelQuantidadeVisitas[0] = jLabelVisita1;
        labelQuantidadeVisitas[1] = jLabelVisita2;
        labelQuantidadeVisitas[2] = jLabelVisita3;
        labelQuantidadeVisitas[3] = jLabelVisita4;
        labelQuantidadeVisitas[4] = jLabelVisita5;

        JLabel[] labelNomes = new JLabel[5];
        labelNomes[0] = jLabelProduto1;
        labelNomes[1] = jLabelProduto2;
        labelNomes[2] = jLabelProduto3;
        labelNomes[3] = jLabelProduto4;
        labelNomes[4] = jLabelProduto5;

        JLabel[] labelQuantidade = new JLabel[5];
        labelQuantidade[0] = jLabelQtdP1;
        labelQuantidade[1] = jLabelQtdP2;
        labelQuantidade[2] = jLabelQtdP3;
        labelQuantidade[3] = jLabelQtdP4;
        labelQuantidade[4] = jLabelQtdP5;
        
        

        cabeleireiro = cc.selecionaCabeleireiro();
         if (cabeleireiro.getNome() != null) {

            switch (new ManipulaData().periodoDoDia(LocalDateTime.now())) {
                case 0:
                    jLabelNomeCabeleireiro.setText("Bom dia, " + cabeleireiro.getNome());
                    break;
                case 1:
                    jLabelNomeCabeleireiro.setText("Boa tarde, " + cabeleireiro.getNome());
                    break;
                case 2:
                    jLabelNomeCabeleireiro.setText("Boa noite, " + cabeleireiro.getNome());
                    break;
                default:
                    jLabelNomeCabeleireiro.setText("Bom dia, " + cabeleireiro.getNome());
                    break;
            }

        } else {
            jLabelNomeCabeleireiro.setText("Olá, Cabeleireiro");
        }

        
        int nAgendamentos = ag.listarAgendamentosHoje().size();
        long nProdutosEstoque = ec.somaProdutosEstoque();

        long receitaMensal = vc.retornaSomaDeVendasMensal() + ag.retornaSomaDeAgendamentosMensal();
        
        //FINANCEIRO

        long entrada,saida;
        
        Month m = LocalDate.now().getMonth();
        entrada = vc.selecionaVendasPorMes(m) + ag.retornaSomaDeAgendamentosMensal(m);
        saida = compraController.retornaSomaDeComprasMensais(m) + dc.retornaSomaDeDespesasMensais(m);
  
       
       
        
        
        modeloVendas.getTodasVendas();
        jTable1.setModel(modeloVendas);
        CentralizaElementosTabela render = new CentralizaElementosTabela();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);    
        jTable1.setDefaultRenderer(Object.class, render);
   
        
        
        jLabelSaidaDeCaixa.setText(Dinheiro.parseString(saida));
        jLabelSaidaDeCaixa.setForeground(vermelho);
        jLabelEntradaDeCaixa.setForeground(verde);
        jLabelEntradaDeCaixa.setText(Dinheiro.parseString(entrada));
        
        long balancoFinal = entrada - saida;
        
        jLabelBalanco.setText(Dinheiro.parseString(balancoFinal));
        if(balancoFinal<0){
            jLabelBalanco.setForeground(vermelho);
        }else{
            jLabelBalanco.setForeground(verde);
        }
        
        jLabelNumeroAgendamentos.setText(String.valueOf(nAgendamentos));
        jLabelQtdEstoque.setText(String.valueOf(nProdutosEstoque) + " un.");
        jLabelQtdVendas.setText(String.valueOf(vc.retornaQuantidadeDeVendasHoje()));
        
        
        //===============//

      
       

        List<Produto> produtos = pc.produtosMaisVendidosDoAno(LocalDate.now().getYear());
        Collections.sort(produtos, new OrdenaProdutoPorQuantidade());

        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i) != null) {
                labelNomes[i].setText(produtos.get(i).getNome());
                labelQuantidade[i].setText(String.valueOf(produtos.get(i).getRendimento()));
            }
        }

        List<Cliente> clientes = clientec.top5Clientes(LocalDate.now().getYear());
        Collections.sort(clientes, new OrdenaClientePorVisitas());

        for (int j = 0; j < clientes.size(); j++) {
            if (clientes.get(j) != null) {
                labelNomesCliente[j].setText(clientes.get(j).getNome() + " " + clientes.get(j).getSobrenome());
                labelQuantidadeVisitas[j].setText(String.valueOf(clientes.get(j).getQtdVisitas()));
            }
        }
        //Gráficos
        new GraficoDePizza(jPanelGraficoPizza1, sc.listaOsCincoServicosMaisRealizados()).plotaGrafico();
        //new GraficoXY(jPanelGrafico1).plotaGrafico();
        new GraficoDeBarras().plotaGrafico(jPanelGrafico);

       

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabelNomeCabeleireiro = new javax.swing.JLabel();
        jPanelGrafico = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabelHistoricoVenda = new javax.swing.JLabel();
        jPanelGraficoPizza1 = new javax.swing.JPanel();
        painelBalanço = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabelBalanco = new javax.swing.JLabel();
        jLabelTop5 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jpainelUltimosClientes = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabelCliente2 = new javax.swing.JLabel();
        jLabelCliente1 = new javax.swing.JLabel();
        jLabelCliente3 = new javax.swing.JLabel();
        jLabelCliente4 = new javax.swing.JLabel();
        jLabelCliente5 = new javax.swing.JLabel();
        jLabelVisita2 = new javax.swing.JLabel();
        jLabelVisita1 = new javax.swing.JLabel();
        jLabelVisita4 = new javax.swing.JLabel();
        jLabelVisita3 = new javax.swing.JLabel();
        jLabelVisita5 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanelProdutosMaisVendidos1 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabelProduto2 = new javax.swing.JLabel();
        jLabelProduto1 = new javax.swing.JLabel();
        jLabelProduto4 = new javax.swing.JLabel();
        jLabelQtdP1 = new javax.swing.JLabel();
        jLabelQtdP2 = new javax.swing.JLabel();
        jLabelQtdP3 = new javax.swing.JLabel();
        jLabelProduto3 = new javax.swing.JLabel();
        jLabelQtdP4 = new javax.swing.JLabel();
        jLabelProduto5 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabelQtdP5 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabelNumeroAgendamentos = new javax.swing.JLabel();
        jLabelQtdEstoque = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabelQtdVendas = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabelEntradaDeCaixa = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabelBemVindoDeVolta = new javax.swing.JLabel();
        jLabelProdutosVendidos = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabelSaidaDeCaixa = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1043, 815));

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane4.setToolTipText("");
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabelNomeCabeleireiro.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabelNomeCabeleireiro.setText("Olá, cabeleireiro");

        jPanelGrafico.setBackground(new java.awt.Color(0, 102, 102));
        jPanelGrafico.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(null);
        jScrollPane3.setForeground(new java.awt.Color(255, 255, 255));

        jTable1.setBackground(new java.awt.Color(255, 255, 255));
        jTable1.setForeground(new java.awt.Color(0, 0, 0));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setRowHeight(20);
        jScrollPane3.setViewportView(jTable1);

        jLabelHistoricoVenda.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelHistoricoVenda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelHistoricoVenda.setText("Histórico de Vendas");

        jPanelGraficoPizza1.setBackground(new java.awt.Color(255, 255, 255));
        jPanelGraficoPizza1.setForeground(new java.awt.Color(255, 255, 255));
        jPanelGraficoPizza1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelGraficoPizza1MousePressed(evt);
            }
        });
        jPanelGraficoPizza1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPanelGraficoPizza1PropertyChange(evt);
            }
        });
        jPanelGraficoPizza1.setLayout(new java.awt.BorderLayout());

        painelBalanço.setBackground(new java.awt.Color(255, 255, 255));
        painelBalanço.setLayout(new java.awt.GridBagLayout());

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(204, 204, 204));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("Balanço");
        jLabel50.setToolTipText("Entrada - Saída");
        jLabel50.setIconTextGap(40);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 50, 0);
        painelBalanço.add(jLabel50, gridBagConstraints);

        jLabelBalanco.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelBalanco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBalanco.setText("R$ 0,00");
        jLabelBalanco.setToolTipText("Entrada - Saída");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        painelBalanço.add(jLabelBalanco, gridBagConstraints);

        jLabelTop5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelTop5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTop5.setText("Top 5 Serviços");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Rank de Clientes");
        jLabel5.setToolTipText("");

        jpainelUltimosClientes.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Nome");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Nº Visitas");

        jLabelCliente2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelCliente2.setText("Cliente 2");

        jLabelCliente1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelCliente1.setText("Cliente 1");

        jLabelCliente3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelCliente3.setText("Cliente 3");

        jLabelCliente4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelCliente4.setText("Cliente 4");

        jLabelCliente5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelCliente5.setText("Cliente 5");

        jLabelVisita2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelVisita2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVisita2.setText("0");

        jLabelVisita1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelVisita1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVisita1.setText("0");

        jLabelVisita4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelVisita4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVisita4.setText("0");

        jLabelVisita3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelVisita3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVisita3.setText("0");

        jLabelVisita5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelVisita5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVisita5.setText("0");

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/medalhaOuro.png"))); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/prata.png"))); // NOI18N

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/bronze.png"))); // NOI18N

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("#4");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("#5");

        javax.swing.GroupLayout jpainelUltimosClientesLayout = new javax.swing.GroupLayout(jpainelUltimosClientes);
        jpainelUltimosClientes.setLayout(jpainelUltimosClientesLayout);
        jpainelUltimosClientesLayout.setHorizontalGroup(
            jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpainelUltimosClientesLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpainelUltimosClientesLayout.createSequentialGroup()
                        .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelCliente4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                            .addComponent(jLabelCliente3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelCliente1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelCliente2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelCliente5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelVisita3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelVisita1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelVisita2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelVisita4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelVisita5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );
        jpainelUltimosClientesLayout.setVerticalGroup(
            jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpainelUltimosClientesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpainelUltimosClientesLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelVisita1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelVisita2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabelVisita3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelVisita4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelVisita5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpainelUltimosClientesLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jpainelUltimosClientesLayout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpainelUltimosClientesLayout.createSequentialGroup()
                                .addComponent(jLabelCliente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabelCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCliente3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCliente4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCliente5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanelProdutosMaisVendidos1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/prata.png"))); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/bronze.png"))); // NOI18N

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/medalhaOuro.png"))); // NOI18N

        jLabelProduto2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelProduto2.setText("Produto 2");

        jLabelProduto1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelProduto1.setText("Produto 1");

        jLabelProduto4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelProduto4.setText("Produto 4");

        jLabelQtdP1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelQtdP1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelQtdP1.setText("0");
        jLabelQtdP1.setToolTipText("");

        jLabelQtdP2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelQtdP2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelQtdP2.setText("0");

        jLabelQtdP3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelQtdP3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelQtdP3.setText("0");

        jLabelProduto3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelProduto3.setText("Produto 3");

        jLabelQtdP4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelQtdP4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelQtdP4.setText("0");

        jLabelProduto5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelProduto5.setText("Produto 5");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("#5");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("#4");

        jLabelQtdP5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelQtdP5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelQtdP5.setText("0");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel36.setText("Quantidade");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel37.setText("Nome");

        javax.swing.GroupLayout jPanelProdutosMaisVendidos1Layout = new javax.swing.GroupLayout(jPanelProdutosMaisVendidos1);
        jPanelProdutosMaisVendidos1.setLayout(jPanelProdutosMaisVendidos1Layout);
        jPanelProdutosMaisVendidos1Layout.setHorizontalGroup(
            jPanelProdutosMaisVendidos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdutosMaisVendidos1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelProdutosMaisVendidos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProdutosMaisVendidos1Layout.createSequentialGroup()
                        .addGroup(jPanelProdutosMaisVendidos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelProdutosMaisVendidos1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanelProdutosMaisVendidos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(jPanelProdutosMaisVendidos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabelProduto4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                    .addComponent(jLabelProduto3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelProduto5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanelProdutosMaisVendidos1Layout.createSequentialGroup()
                                .addGroup(jPanelProdutosMaisVendidos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelProdutosMaisVendidos1Layout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProdutosMaisVendidos1Layout.createSequentialGroup()
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)))
                                .addGroup(jPanelProdutosMaisVendidos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelProduto1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelProdutosMaisVendidos1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabelProduto2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelProdutosMaisVendidos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelQtdP4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelQtdP3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelQtdP2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelQtdP1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelQtdP5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelProdutosMaisVendidos1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addGap(207, 207, 207)
                        .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanelProdutosMaisVendidos1Layout.setVerticalGroup(
            jPanelProdutosMaisVendidos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdutosMaisVendidos1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelProdutosMaisVendidos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanelProdutosMaisVendidos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelProdutosMaisVendidos1Layout.createSequentialGroup()
                        .addGroup(jPanelProdutosMaisVendidos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelProdutosMaisVendidos1Layout.createSequentialGroup()
                                .addGroup(jPanelProdutosMaisVendidos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabelProduto1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelProduto2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanelProdutosMaisVendidos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelProdutosMaisVendidos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelProduto3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelQtdP3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelProdutosMaisVendidos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelProduto4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelQtdP4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelProdutosMaisVendidos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelProduto5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelQtdP5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProdutosMaisVendidos1Layout.createSequentialGroup()
                        .addComponent(jLabelQtdP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelQtdP2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138)))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Agendamentos");
        jLabel3.setToolTipText("Quantidade de agendamentos hoje");
        jLabel3.setIconTextGap(40);

        jLabelNumeroAgendamentos.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelNumeroAgendamentos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNumeroAgendamentos.setText("0");
        jLabelNumeroAgendamentos.setToolTipText("Quantidade de agendamentos hoje");

        jLabelQtdEstoque.setBackground(new java.awt.Color(255, 255, 255));
        jLabelQtdEstoque.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelQtdEstoque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelQtdEstoque.setText("0");
        jLabelQtdEstoque.setToolTipText("Quantidade de Produtos em Estoque");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Qtd. Estoque");
        jLabel8.setToolTipText("Quantidade de Produtos em Estoque");
        jLabel8.setIconTextGap(40);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Vendas Hoje");
        jLabel17.setToolTipText("Quantidade de Vendas realizadas Hoje");
        jLabel17.setIconTextGap(40);

        jLabelQtdVendas.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelQtdVendas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelQtdVendas.setText("0");
        jLabelQtdVendas.setToolTipText("Quantidade de Vendas realizadas Hoje");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelQtdVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNumeroAgendamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabelQtdEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabelNumeroAgendamentos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(jLabelQtdEstoque)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(51, 51, 51)
                .addComponent(jLabelQtdVendas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addGap(35, 35, 35))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setText("Fluxo de Caixa");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabelEntradaDeCaixa.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelEntradaDeCaixa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEntradaDeCaixa.setText("R$ 0,00");
        jLabelEntradaDeCaixa.setToolTipText("Soma de Serviços e Vendas");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 204, 204));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Entrada de Caixa");
        jLabel23.setToolTipText("Soma de Serviços e Vendas");
        jLabel23.setIconTextGap(40);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelEntradaDeCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelEntradaDeCaixa)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLabelBemVindoDeVolta.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelBemVindoDeVolta.setForeground(new java.awt.Color(204, 204, 204));
        jLabelBemVindoDeVolta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelBemVindoDeVolta.setText("Bem vindo de  volta!");
        jLabelBemVindoDeVolta.setIconTextGap(40);

        jLabelProdutosVendidos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelProdutosVendidos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelProdutosVendidos.setText("Produtos Mais Vendidos");
        jLabelProdutosVendidos.setToolTipText("");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-emaildashboard.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabelSaidaDeCaixa.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelSaidaDeCaixa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSaidaDeCaixa.setText(" R$ 0,00");
        jLabelSaidaDeCaixa.setToolTipText("Soma de compras e pagamentos de despesas");

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(204, 204, 204));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("Saída de Caixa");
        jLabel49.setToolTipText("Soma de compras e pagamentos de despesas");
        jLabel49.setIconTextGap(40);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
            .addComponent(jLabelSaidaDeCaixa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelSaidaDeCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-postit.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabelBemVindoDeVolta, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 720, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabelNomeCabeleireiro, javax.swing.GroupLayout.PREFERRED_SIZE, 829, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelGraficoPizza1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTop5, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(painelBalanço, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelHistoricoVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jpainelUltimosClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(287, 287, 287)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelProdutosMaisVendidos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelProdutosVendidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNomeCabeleireiro)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelBemVindoDeVolta, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelGrafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTop5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelHistoricoVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelGraficoPizza1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(painelBalanço, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelProdutosVendidos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpainelUltimosClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelProdutosMaisVendidos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100))
        );

        jScrollPane4.setViewportView(jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1126, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelGraficoPizza1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelGraficoPizza1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanelGraficoPizza1MousePressed

    private void jPanelGraficoPizza1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanelGraficoPizza1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanelGraficoPizza1PropertyChange

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        new ModalEmail().setVisible(true);
    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        
        if(modalPostIt == null){
         modalPostIt = new ModalPostIt(cabeleireiro);  
         modalPostIt.setVisible(true);
        }else{
            modalPostIt.setVisible(true);
        }
        
    }//GEN-LAST:event_jLabel2MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelBalanco;
    private javax.swing.JLabel jLabelBemVindoDeVolta;
    private javax.swing.JLabel jLabelCliente1;
    private javax.swing.JLabel jLabelCliente2;
    private javax.swing.JLabel jLabelCliente3;
    private javax.swing.JLabel jLabelCliente4;
    private javax.swing.JLabel jLabelCliente5;
    private javax.swing.JLabel jLabelEntradaDeCaixa;
    private javax.swing.JLabel jLabelHistoricoVenda;
    private javax.swing.JLabel jLabelNomeCabeleireiro;
    private javax.swing.JLabel jLabelNumeroAgendamentos;
    private javax.swing.JLabel jLabelProduto1;
    private javax.swing.JLabel jLabelProduto2;
    private javax.swing.JLabel jLabelProduto3;
    private javax.swing.JLabel jLabelProduto4;
    private javax.swing.JLabel jLabelProduto5;
    private javax.swing.JLabel jLabelProdutosVendidos;
    private javax.swing.JLabel jLabelQtdEstoque;
    private javax.swing.JLabel jLabelQtdP1;
    private javax.swing.JLabel jLabelQtdP2;
    private javax.swing.JLabel jLabelQtdP3;
    private javax.swing.JLabel jLabelQtdP4;
    private javax.swing.JLabel jLabelQtdP5;
    private javax.swing.JLabel jLabelQtdVendas;
    private javax.swing.JLabel jLabelSaidaDeCaixa;
    private javax.swing.JLabel jLabelTop5;
    private javax.swing.JLabel jLabelVisita1;
    private javax.swing.JLabel jLabelVisita2;
    private javax.swing.JLabel jLabelVisita3;
    private javax.swing.JLabel jLabelVisita4;
    private javax.swing.JLabel jLabelVisita5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelGrafico;
    private javax.swing.JPanel jPanelGraficoPizza1;
    private javax.swing.JPanel jPanelProdutosMaisVendidos1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel jpainelUltimosClientes;
    private javax.swing.JPanel painelBalanço;
    // End of variables declaration//GEN-END:variables
}
