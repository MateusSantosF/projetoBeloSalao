/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.view;

import BeutifulSalon.Ferramentas.GraficoDePizza;
import BeutifulSalon.Ferramentas.GraficoXY;
import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.Ferramentas.ManipulaFontes;
import BeutifulSalon.Ferramentas.OrdenaClientePorVisitas;
import BeutifulSalon.Ferramentas.OrdenaProdutoPorQuantidade;
import BeutifulSalon.controller.AgendamentoController;
import BeutifulSalon.controller.CabeleireiroController;
import BeutifulSalon.controller.ClienteController;
import BeutifulSalon.controller.EstoqueController;
import BeutifulSalon.controller.ProdutoController;
import BeutifulSalon.controller.ServicoController;
import BeutifulSalon.controller.VendaController;
import BeutifulSalon.model.Cabeleireiro;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Produto;
import java.awt.Component;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 *
 * @author Mateus
 */
public class Dashboard extends javax.swing.JPanel {

    /**
     * Creates new form testePainel
     */
    
   
    
    public Dashboard() {
        initComponents();
         
        ClienteController clientec = new ClienteController();
        CabeleireiroController cc = new CabeleireiroController();
        AgendamentoController ag = new AgendamentoController();
        EstoqueController ec = new EstoqueController();
        ServicoController sc = new ServicoController();
        ProdutoController pc = new ProdutoController();
        VendaController vc = new VendaController();
        
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
      
        
        

        Cabeleireiro cabeleireiro = cc.selecionaCabeleireiro();
        int nAgendamentos = ag.listarAgendamentosHoje().size();
        long nProdutosEstoque = ec.somaProdutosEstoque();
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

        jLabelNumeroAgendamentos.setText(String.valueOf(nAgendamentos));
        jLabelQtdEstoque.setText(String.valueOf(nProdutosEstoque) + " un.");
        jLabelQtdVendas.setText(String.valueOf(vc.retornaQuantidadeDeVendasHoje()));
        
        
        List<Produto> produtos = pc.produtosMaisVendidosDoAno(LocalDate.now().getYear());
        Collections.sort(produtos, new OrdenaProdutoPorQuantidade());
       
        for( int i = 0; i < produtos.size(); i++){
            if(produtos.get(i) != null){
                labelNomes[i].setText(produtos.get(i).getNome());
                labelQuantidade[i].setText(String.valueOf(produtos.get(i).getRendimento()));
            }
        }
       
        List<Cliente> clientes = clientec.top5Clientes(LocalDate.now().getYear());
        Collections.sort(clientes, new OrdenaClientePorVisitas());
        
        for( int j = 0; j < clientes.size(); j++){
            if(clientes.get(j) != null){
                labelNomesCliente[j].setText(clientes.get(j).getNOME() + " " + clientes.get(j).getSOBRENOME());
                labelQuantidadeVisitas[j].setText(String.valueOf(clientes.get(j).getDeOndeConheceu())); //gambiarra para nao criar atributos a mais na classe cliente
            }
        }

        new GraficoDePizza(jPanelGraficoPizza, sc.listaOsCincoServicosMaisRealizados()).plotaGrafico();
        new GraficoXY(jPanelGrafico).plotaGrafico();
      
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabelNomeCabeleireiro = new javax.swing.JLabel();
        painelNumeroAgendamentos = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabelNumeroAgendamentos = new javax.swing.JLabel();
        painelNumeroAgendamentos3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabelQtdEstoque = new javax.swing.JLabel();
        painelNumeroAgendamentos2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabelReceitaMensal = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanelGrafico = new javax.swing.JPanel();
        jPanelGraficoPizza = new javax.swing.JPanel();
        jPanelProdutosMaisVendidos = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabelProduto2 = new javax.swing.JLabel();
        jLabelProduto1 = new javax.swing.JLabel();
        jLabelProduto4 = new javax.swing.JLabel();
        jLabelQtdP1 = new javax.swing.JLabel();
        jLabelQtdP2 = new javax.swing.JLabel();
        jLabelQtdP3 = new javax.swing.JLabel();
        jLabelProduto3 = new javax.swing.JLabel();
        jLabelQtdP4 = new javax.swing.JLabel();
        jLabelProduto5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabelQtdP5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        painelNumeroAgendamentos4 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabelQtdVendas = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabelNomeCabeleireiro.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabelNomeCabeleireiro.setText("jLabel2");

        painelNumeroAgendamentos.setBackground(new java.awt.Color(255, 255, 255));
        painelNumeroAgendamentos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {200};
        jPanel2Layout.rowHeights = new int[] {1};
        painelNumeroAgendamentos.setLayout(jPanel2Layout);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Agendamentos");
        jLabel3.setIconTextGap(40);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 50, 0);
        painelNumeroAgendamentos.add(jLabel3, gridBagConstraints);

        jLabelNumeroAgendamentos.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelNumeroAgendamentos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNumeroAgendamentos.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        painelNumeroAgendamentos.add(jLabelNumeroAgendamentos, gridBagConstraints);

        painelNumeroAgendamentos3.setBackground(new java.awt.Color(255, 255, 255));
        painelNumeroAgendamentos3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        painelNumeroAgendamentos3.setLayout(new java.awt.GridBagLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Qtd. Estoque");
        jLabel8.setIconTextGap(40);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 50, 0);
        painelNumeroAgendamentos3.add(jLabel8, gridBagConstraints);

        jLabelQtdEstoque.setBackground(new java.awt.Color(255, 255, 255));
        jLabelQtdEstoque.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelQtdEstoque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelQtdEstoque.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        painelNumeroAgendamentos3.add(jLabelQtdEstoque, gridBagConstraints);

        painelNumeroAgendamentos2.setBackground(new java.awt.Color(255, 255, 255));
        painelNumeroAgendamentos2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        painelNumeroAgendamentos2.setLayout(new java.awt.GridBagLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Receita Mensal");
        jLabel7.setIconTextGap(40);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 50, 0);
        painelNumeroAgendamentos2.add(jLabel7, gridBagConstraints);

        jLabelReceitaMensal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelReceitaMensal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelReceitaMensal.setText("R$");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        painelNumeroAgendamentos2.add(jLabelReceitaMensal, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Produtos Mais Vendidos");

        jPanelGrafico.setBackground(new java.awt.Color(0, 102, 102));
        jPanelGrafico.setLayout(new java.awt.BorderLayout());

        jPanelGraficoPizza.setBackground(new java.awt.Color(255, 255, 255));
        jPanelGraficoPizza.setForeground(new java.awt.Color(255, 255, 255));
        jPanelGraficoPizza.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelGraficoPizzaMousePressed(evt);
            }
        });
        jPanelGraficoPizza.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jPanelGraficoPizzaPropertyChange(evt);
            }
        });
        jPanelGraficoPizza.setLayout(new java.awt.BorderLayout());

        jPanelProdutosMaisVendidos.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/prata.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/bronze.png"))); // NOI18N

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/medalhaOuro.png"))); // NOI18N

        jLabelProduto2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelProduto2.setText("Produto 2");

        jLabelProduto1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelProduto1.setText("Produto 1");

        jLabelProduto4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelProduto4.setText("Produto 4");

        jLabelQtdP1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelQtdP1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelQtdP1.setText("0");
        jLabelQtdP1.setToolTipText("");

        jLabelQtdP2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelQtdP2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelQtdP2.setText("0");

        jLabelQtdP3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelQtdP3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelQtdP3.setText("0");

        jLabelProduto3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelProduto3.setText("Produto 3");

        jLabelQtdP4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelQtdP4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelQtdP4.setText("0");

        jLabelProduto5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelProduto5.setText("Produto 5");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("#5");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("#4");

        jLabelQtdP5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelQtdP5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelQtdP5.setText("0");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Quantidade");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Nome");

        javax.swing.GroupLayout jPanelProdutosMaisVendidosLayout = new javax.swing.GroupLayout(jPanelProdutosMaisVendidos);
        jPanelProdutosMaisVendidos.setLayout(jPanelProdutosMaisVendidosLayout);
        jPanelProdutosMaisVendidosLayout.setHorizontalGroup(
            jPanelProdutosMaisVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdutosMaisVendidosLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanelProdutosMaisVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProdutosMaisVendidosLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanelProdutosMaisVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanelProdutosMaisVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelProduto4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelProduto3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                            .addComponent(jLabelProduto5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelProdutosMaisVendidosLayout.createSequentialGroup()
                        .addGroup(jPanelProdutosMaisVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelProdutosMaisVendidosLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProdutosMaisVendidosLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)))
                        .addGroup(jPanelProdutosMaisVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelProdutosMaisVendidosLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabelProduto2, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelProduto1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelProdutosMaisVendidosLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelProdutosMaisVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabelQtdP2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelQtdP3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelQtdP4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelQtdP5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelQtdP1, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .addComponent(jLabel12))
                .addContainerGap())
        );
        jPanelProdutosMaisVendidosLayout.setVerticalGroup(
            jPanelProdutosMaisVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdutosMaisVendidosLayout.createSequentialGroup()
                .addGroup(jPanelProdutosMaisVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelProdutosMaisVendidosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelProdutosMaisVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelProdutosMaisVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelProdutosMaisVendidosLayout.createSequentialGroup()
                        .addGroup(jPanelProdutosMaisVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelProduto1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelQtdP1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelProdutosMaisVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelProduto2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelQtdP2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanelProdutosMaisVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelProdutosMaisVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelQtdP3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelProduto3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProdutosMaisVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelProduto4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelQtdP4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProdutosMaisVendidosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelProduto5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelQtdP5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpainelUltimosClientesLayout.createSequentialGroup()
                        .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelCliente1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                            .addComponent(jLabelCliente2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelCliente3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelCliente4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelCliente5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpainelUltimosClientesLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(jpainelUltimosClientesLayout.createSequentialGroup()
                        .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelVisita5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelVisita4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelVisita1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelVisita2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelVisita3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32))))
        );
        jpainelUltimosClientesLayout.setVerticalGroup(
            jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpainelUltimosClientesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addGap(33, 33, 33)
                .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jpainelUltimosClientesLayout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpainelUltimosClientesLayout.createSequentialGroup()
                        .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpainelUltimosClientesLayout.createSequentialGroup()
                                .addComponent(jLabelCliente1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(9, 9, 9))
                            .addGroup(jpainelUltimosClientesLayout.createSequentialGroup()
                                .addComponent(jLabelVisita1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCliente2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelVisita2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jpainelUltimosClientesLayout.createSequentialGroup()
                        .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCliente3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCliente4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jpainelUltimosClientesLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabelVisita3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelVisita4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpainelUltimosClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelCliente5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelVisita5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(98, 98, 98))
        );

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Vendas e Serviços realizados");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Rank de Clientes");
        jLabel5.setToolTipText("");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Top 5 Serviços");

        painelNumeroAgendamentos4.setBackground(new java.awt.Color(255, 255, 255));
        painelNumeroAgendamentos4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        painelNumeroAgendamentos4.setLayout(new java.awt.GridBagLayout());

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Vendas Hoje");
        jLabel17.setIconTextGap(40);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 50, 0);
        painelNumeroAgendamentos4.add(jLabel17, gridBagConstraints);

        jLabelQtdVendas.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelQtdVendas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelQtdVendas.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        painelNumeroAgendamentos4.add(jLabelQtdVendas, gridBagConstraints);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanelGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanelGraficoPizza, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jpainelUltimosClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(136, 136, 136)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(12, 12, 12))
                                    .addComponent(jPanelProdutosMaisVendidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelNomeCabeleireiro, javax.swing.GroupLayout.PREFERRED_SIZE, 829, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(painelNumeroAgendamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(painelNumeroAgendamentos3, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(painelNumeroAgendamentos4, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(painelNumeroAgendamentos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(62, 62, 62))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabelNomeCabeleireiro, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(painelNumeroAgendamentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelNumeroAgendamentos3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelNumeroAgendamentos2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(painelNumeroAgendamentos4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelGraficoPizza, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelProdutosMaisVendidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpainelUltimosClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(131, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelGraficoPizzaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelGraficoPizzaMousePressed
        
    }//GEN-LAST:event_jPanelGraficoPizzaMousePressed

    private void jPanelGraficoPizzaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanelGraficoPizzaPropertyChange

    }//GEN-LAST:event_jPanelGraficoPizzaPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCliente1;
    private javax.swing.JLabel jLabelCliente2;
    private javax.swing.JLabel jLabelCliente3;
    private javax.swing.JLabel jLabelCliente4;
    private javax.swing.JLabel jLabelCliente5;
    private javax.swing.JLabel jLabelNomeCabeleireiro;
    private javax.swing.JLabel jLabelNumeroAgendamentos;
    private javax.swing.JLabel jLabelProduto1;
    private javax.swing.JLabel jLabelProduto2;
    private javax.swing.JLabel jLabelProduto3;
    private javax.swing.JLabel jLabelProduto4;
    private javax.swing.JLabel jLabelProduto5;
    private javax.swing.JLabel jLabelQtdEstoque;
    private javax.swing.JLabel jLabelQtdP1;
    private javax.swing.JLabel jLabelQtdP2;
    private javax.swing.JLabel jLabelQtdP3;
    private javax.swing.JLabel jLabelQtdP4;
    private javax.swing.JLabel jLabelQtdP5;
    private javax.swing.JLabel jLabelQtdVendas;
    private javax.swing.JLabel jLabelReceitaMensal;
    private javax.swing.JLabel jLabelVisita1;
    private javax.swing.JLabel jLabelVisita2;
    private javax.swing.JLabel jLabelVisita3;
    private javax.swing.JLabel jLabelVisita4;
    private javax.swing.JLabel jLabelVisita5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelGrafico;
    private javax.swing.JPanel jPanelGraficoPizza;
    private javax.swing.JPanel jPanelProdutosMaisVendidos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpainelUltimosClientes;
    private javax.swing.JPanel painelNumeroAgendamentos;
    private javax.swing.JPanel painelNumeroAgendamentos2;
    private javax.swing.JPanel painelNumeroAgendamentos3;
    private javax.swing.JPanel painelNumeroAgendamentos4;
    // End of variables declaration//GEN-END:variables
}
