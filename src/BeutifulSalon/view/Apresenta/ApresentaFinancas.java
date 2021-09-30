/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.view.Apresenta;

import BeutifulSalon.Ferramentas.ManipulaFontes;
import BeutifulSalon.Tabelas.CentralizaElementosTabela;
import BeutifulSalon.Tabelas.DespesaComparadaTableModel;
import BeutifulSalon.Tabelas.DespesaPrevistaTableModel;
import BeutifulSalon.Tabelas.DestacaVencimentosTabela;
import BeutifulSalon.Tabelas.LancamentoTableModel;
import BeutifulSalon.Tabelas.ServicoComparadoTableModel;
import BeutifulSalon.Tabelas.ServicoPrevistoTableModel;
import BeutifulSalon.Tabelas.ServicoRealizadoTableModel;
import BeutifulSalon.controller.DespesaController;
import BeutifulSalon.controller.OrcamentoController;
import BeutifulSalon.model.Despesa;
import BeutifulSalon.model.OrcamentoServico;
import BeutifulSalon.view.Edicao.EditarOrcamentoServico;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Mateus
 */
public class ApresentaFinancas extends javax.swing.JPanel {

    /**
     * Creates new form ApreFinancas
     */
    private final ServicoRealizadoTableModel modeloServicoRealizado = new ServicoRealizadoTableModel();
    private final LancamentoTableModel modeloLancamento = new LancamentoTableModel();
    private final ServicoComparadoTableModel modeloServicoComparado = new ServicoComparadoTableModel();
    private final DespesaComparadaTableModel modeloDespesaComparada = new DespesaComparadaTableModel();
    private final ServicoPrevistoTableModel modeloServicoPrevisto = new ServicoPrevistoTableModel();
    private final DespesaPrevistaTableModel modeloDespesaPrevista = new DespesaPrevistaTableModel();
    private final String ANOATUAL = String.valueOf(LocalDate.now().getYear());

    public ApresentaFinancas() {
        initComponents();
        
        ManipulaFontes mf = new ManipulaFontes();    
        
	//SERVICOS PREVISTOS
        jLabel5.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 50f)); //Serviços Previstos
        jLabel2.setFont(mf.getFont(mf.LIGHT, Font.PLAIN, 30f)); //Confira aqui o orçamento de serviços previstos do seu salão!
        jLabel6.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Buscar por Ano
        jLabelEditar1.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Editar
        jLabelExcluir1.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Excluir
        jTableConsultaOrcamentoServico.setFont(mf.getFont(mf.SEMIBOLD, Font.PLAIN, 15f)); //Tabela

        //SERVICOS REALIZADOS
        jLabel8.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 50f)); //Serviços Realizados
        jLabel4.setFont(mf.getFont(mf.LIGHT, Font.PLAIN, 30f)); //Aqui você confere todos os serviços realizados e seus lucros!
        jLabel9.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Buscar por Ano
        jTableConsultaServicoRealizado.setFont(mf.getFont(mf.SEMIBOLD, Font.PLAIN, 15f)); //Tabela

        //COMPARATIVO SERVICOS REALIZADOS
        jLabel7.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 40f)); //Comparativo de Serviços Realizados
        jLabel23.setFont(mf.getFont(mf.LIGHT, Font.PLAIN, 23f)); //Aqui você encontra um comparativo entre o previsto e o realizado!
        jLabel27.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Buscar por Ano
        jTableServicoComparado.setFont(mf.getFont(mf.SEMIBOLD, Font.PLAIN, 15f)); //Tabela

        //LANÇAMENTOS REALIZADOS
        jLabel11.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 50f)); //Confira todos os Lançamentos Realizados
        jLabel26.setFont(mf.getFont(mf.LIGHT, Font.PLAIN, 30f)); //Confira aqui o pagamentos realizados!
        jLabel13.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Buscar por Ano
        jLabel15.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Lançamento em
        jLabel14.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Vencimento em
        jTextFieldAno4.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Box Busca por ano
        jComboBoxLancamento.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Box Lançamento
        jComboBoxVencimento.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Box Vencimento
        jButtonEditar2.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Pagar | Editar
        jTableLancamentos.setFont(mf.getFont(mf.SEMIBOLD, Font.PLAIN, 15f)); //Tabela

        //COMPARATIVO DESPESAS
        jLabel17.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 50f)); //Comparativo de Despesas
        jLabel24.setFont(mf.getFont(mf.LIGHT, Font.PLAIN, 30f)); //Aqui você confere o quanto você cumpriu do previsto!
        jLabel22.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Buscar por Ano
        jTableComparativoDespesas.setFont(mf.getFont(mf.SEMIBOLD, Font.PLAIN, 15f)); //Tabela

        //DESPESAS PREVISTAS
        jLabel18.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 50f)); //Despesas Previstas
        jLabel30.setFont(mf.getFont(mf.LIGHT, Font.PLAIN, 30f)); //Confira aqui o orçamento de despesas previstas do seu salão!
        jLabel10.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Buscar por Ano
        jLabelEditar2.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Editar
        jLabelExcluir2.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Excluir
        jTableConsultaOrcamento.setFont(mf.getFont(mf.SEMIBOLD, Font.PLAIN, 15f)); //Tabela
   
        String ano = String.valueOf(LocalDate.now().getYear());
        int mesAtual = LocalDate.now().getMonth().getValue() - 1;

        jComboBoxVencimento.setSelectedIndex(mesAtual);
        jComboBoxLancamento.setSelectedIndex(mesAtual);
        
        listarOrcamentoServicoRealizado(ANOATUAL);  
    
        modeloDespesaPrevista.getTodasDespesasPrevistas(ANOATUAL);
        jTableConsultaOrcamento.setModel(modeloDespesaPrevista);
        ((DefaultTableCellRenderer)  jTableConsultaOrcamento.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer)  jTableConsultaServicoRealizado.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer)  jTableConsultaOrcamentoServico.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer)  jTableServicoComparado.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer)  jTableLancamentos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((DefaultTableCellRenderer)  jTableComparativoDespesas.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);


    }
   
    
    
    
    
    public class FormatacaoConteudo extends DefaultTableCellRenderer implements TableCellRenderer {

        private Color color;
        private int row = -1;

        public FormatacaoConteudo(Color color, int row) {
            super();
            this.color = color;
            this.row = row;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {

            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (row != -1 && color != null) {
                if (row == this.row) {
                    c.setBackground(new Color(0, 0, 0));
                    c.setForeground(this.color);
                    this.setHorizontalAlignment(CENTER);
                } else {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
            }

            return c;
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbed = new javax.swing.JTabbedPane();
        ServicosRealizados = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableConsultaServicoRealizado = new javax.swing.JTable();
        jTextFieldAno2 = new javax.swing.JFormattedTextField();
        buscaPorAnoServicoRealizado = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        ServicoPrevisto = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableConsultaOrcamentoServico = new javax.swing.JTable();
        jLabelSearch1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldAno1 = new javax.swing.JFormattedTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelEditar1 = new javax.swing.JLabel();
        jLabelExcluir1 = new javax.swing.JLabel();
        ServicoComparado = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTableServicoComparado = new javax.swing.JTable();
        jLabelSearch3 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldAno3 = new javax.swing.JFormattedTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lançamentos = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableLancamentos = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jComboBoxLancamento = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jComboBoxVencimento = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jButtonEditar2 = new javax.swing.JButton();
        btnBuscaPorAno4 = new javax.swing.JLabel();
        jTextFieldAno4 = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabelExcluir3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        DespesasPrevistas = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableConsultaOrcamento = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jTextFieldAno6 = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabelSearch2 = new javax.swing.JLabel();
        jLabelEditar2 = new javax.swing.JLabel();
        jLabelExcluir2 = new javax.swing.JLabel();
        ServicosRealizados1 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableComparativoDespesas = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jTextFieldAno7 = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabelSearch5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1086, 686));
        setLayout(new java.awt.BorderLayout());

        jTabbed.setBackground(new java.awt.Color(255, 255, 255));
        jTabbed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedStateChanged(evt);
            }
        });

        ServicosRealizados.setBackground(new java.awt.Color(255, 255, 255));
        ServicosRealizados.setPreferredSize(new java.awt.Dimension(985, 843));

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jScrollPane5.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane5.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane5.setAutoscrolls(true);
        jScrollPane5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane5.setPreferredSize(new java.awt.Dimension(465, 402));

        jTableConsultaServicoRealizado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTableConsultaServicoRealizado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Serviço", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro", "id_orcamento", "id_servico"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableConsultaServicoRealizado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableConsultaServicoRealizado.setGridColor(new java.awt.Color(255, 255, 255));
        jTableConsultaServicoRealizado.setRowHeight(22);
        jTableConsultaServicoRealizado.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableConsultaServicoRealizado.setShowGrid(true);
        jScrollPane5.setViewportView(jTableConsultaServicoRealizado);
        if (jTableConsultaServicoRealizado.getColumnModel().getColumnCount() > 0) {
            jTableConsultaServicoRealizado.getColumnModel().getColumn(0).setMinWidth(120);
            jTableConsultaServicoRealizado.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTableConsultaServicoRealizado.getColumnModel().getColumn(13).setMinWidth(1);
            jTableConsultaServicoRealizado.getColumnModel().getColumn(13).setPreferredWidth(1);
            jTableConsultaServicoRealizado.getColumnModel().getColumn(13).setMaxWidth(1);
            jTableConsultaServicoRealizado.getColumnModel().getColumn(14).setMinWidth(1);
            jTableConsultaServicoRealizado.getColumnModel().getColumn(14).setPreferredWidth(1);
            jTableConsultaServicoRealizado.getColumnModel().getColumn(14).setMaxWidth(1);
        }

        jTextFieldAno2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("y"))));
        jTextFieldAno2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        buscaPorAnoServicoRealizado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconLupa.png"))); // NOI18N
        buscaPorAnoServicoRealizado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buscaPorAnoServicoRealizadoMousePressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Busca por Ano");

        jPanel3.setBackground(new java.awt.Color(48, 63, 79));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Serviços Realizados");

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons8-servicos-48.png"))); // NOI18N
        jLabel16.setText("jLabel16");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Confira aqui todos os serviços realizados e seus lucros!");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(48, 63, 79));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ServicosRealizadosLayout = new javax.swing.GroupLayout(ServicosRealizados);
        ServicosRealizados.setLayout(ServicosRealizadosLayout);
        ServicosRealizadosLayout.setHorizontalGroup(
            ServicosRealizadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ServicosRealizadosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(ServicosRealizadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(ServicosRealizadosLayout.createSequentialGroup()
                        .addComponent(jTextFieldAno2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buscaPorAnoServicoRealizado))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE))
                .addGap(25, 25, 25))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ServicosRealizadosLayout.setVerticalGroup(
            ServicosRealizadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ServicosRealizadosLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ServicosRealizadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldAno2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscaPorAnoServicoRealizado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbed.addTab("Serviços Realizados", ServicosRealizados);
        ServicosRealizados.getAccessibleContext().setAccessibleParent(jTabbed);

        ServicoPrevisto.setBackground(new java.awt.Color(255, 255, 255));
        ServicoPrevisto.setPreferredSize(new java.awt.Dimension(985, 843));

        jPanel1.setBackground(new java.awt.Color(48, 63, 79));
        jPanel1.setPreferredSize(new java.awt.Dimension(0, 100));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jScrollPane3.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane3.setAutoscrolls(true);
        jScrollPane3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane3.setPreferredSize(new java.awt.Dimension(469, 402));

        jTableConsultaOrcamentoServico.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTableConsultaOrcamentoServico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Serviço", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro", "id_orcamento", "id_servico"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableConsultaOrcamentoServico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableConsultaOrcamentoServico.setGridColor(new java.awt.Color(255, 255, 255));
        jTableConsultaOrcamentoServico.setRowHeight(22);
        jTableConsultaOrcamentoServico.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableConsultaOrcamentoServico.setShowGrid(true);
        jScrollPane3.setViewportView(jTableConsultaOrcamentoServico);
        if (jTableConsultaOrcamentoServico.getColumnModel().getColumnCount() > 0) {
            jTableConsultaOrcamentoServico.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTableConsultaOrcamentoServico.getColumnModel().getColumn(13).setMinWidth(1);
            jTableConsultaOrcamentoServico.getColumnModel().getColumn(13).setPreferredWidth(1);
            jTableConsultaOrcamentoServico.getColumnModel().getColumn(13).setMaxWidth(1);
            jTableConsultaOrcamentoServico.getColumnModel().getColumn(14).setMinWidth(1);
            jTableConsultaOrcamentoServico.getColumnModel().getColumn(14).setPreferredWidth(1);
            jTableConsultaOrcamentoServico.getColumnModel().getColumn(14).setMaxWidth(1);
        }

        jLabelSearch1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconLupa.png"))); // NOI18N
        jLabelSearch1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelSearch1MousePressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Buscar por Ano");

        jTextFieldAno1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("y"))));
        jTextFieldAno1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel7.setBackground(new java.awt.Color(48, 63, 79));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Serviços Previstos");

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons8-servicos-48.png"))); // NOI18N
        jLabel20.setText("jLabel20");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Confira aqui os serviços previstos do seu salão!");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 559, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jLabelEditar1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelEditar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEditar1.setText("Editar");
        jLabelEditar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelEditar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelEditar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelEditar1MousePressed(evt);
            }
        });

        jLabelExcluir1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelExcluir1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelExcluir1.setText("Excluir");
        jLabelExcluir1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelExcluir1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelExcluir1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelExcluir1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout ServicoPrevistoLayout = new javax.swing.GroupLayout(ServicoPrevisto);
        ServicoPrevisto.setLayout(ServicoPrevistoLayout);
        ServicoPrevistoLayout.setHorizontalGroup(
            ServicoPrevistoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ServicoPrevistoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(ServicoPrevistoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ServicoPrevistoLayout.createSequentialGroup()
                        .addComponent(jTextFieldAno1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelSearch1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6))
                .addGap(25, 25, 25))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
        );
        ServicoPrevistoLayout.setVerticalGroup(
            ServicoPrevistoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ServicoPrevistoLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(ServicoPrevistoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ServicoPrevistoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ServicoPrevistoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ServicoPrevistoLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(ServicoPrevistoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldAno1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)))
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbed.addTab("Serviços Previstos", ServicoPrevisto);
        ServicoPrevisto.getAccessibleContext().setAccessibleParent(jTabbed);

        ServicoComparado.setBackground(new java.awt.Color(255, 255, 255));
        ServicoComparado.setPreferredSize(new java.awt.Dimension(985, 843));

        jPanel2.setBackground(new java.awt.Color(48, 63, 79));
        jPanel2.setPreferredSize(new java.awt.Dimension(0, 100));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jScrollPane7.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jScrollPane7.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane7.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane7.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane7.setAutoscrolls(true);
        jScrollPane7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane7.setPreferredSize(new java.awt.Dimension(469, 402));

        jTableServicoComparado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTableServicoComparado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Serviço", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro", "id_orcamento", "id_servico"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableServicoComparado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableServicoComparado.setGridColor(new java.awt.Color(255, 255, 255));
        jTableServicoComparado.setRowHeight(22);
        jTableServicoComparado.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableServicoComparado.setShowGrid(true);
        jScrollPane7.setViewportView(jTableServicoComparado);
        if (jTableServicoComparado.getColumnModel().getColumnCount() > 0) {
            jTableServicoComparado.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTableServicoComparado.getColumnModel().getColumn(13).setMinWidth(1);
            jTableServicoComparado.getColumnModel().getColumn(13).setPreferredWidth(1);
            jTableServicoComparado.getColumnModel().getColumn(13).setMaxWidth(1);
            jTableServicoComparado.getColumnModel().getColumn(14).setMinWidth(1);
            jTableServicoComparado.getColumnModel().getColumn(14).setPreferredWidth(1);
            jTableServicoComparado.getColumnModel().getColumn(14).setMaxWidth(1);
        }

        jLabelSearch3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconLupa.png"))); // NOI18N
        jLabelSearch3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelSearch3MousePressed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel27.setText("Buscar por Ano");

        jTextFieldAno3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("y"))));
        jTextFieldAno3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel14.setBackground(new java.awt.Color(48, 63, 79));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Comparativo Serviços Realizados");

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons8-servicos-48.png"))); // NOI18N
        jLabel29.setText("jLabel20");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Aqui você encontra um comparativo entre o previsto e o realizado!");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(0, 383, Short.MAX_VALUE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ServicoComparadoLayout = new javax.swing.GroupLayout(ServicoComparado);
        ServicoComparado.setLayout(ServicoComparadoLayout);
        ServicoComparadoLayout.setHorizontalGroup(
            ServicoComparadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ServicoComparadoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(ServicoComparadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(ServicoComparadoLayout.createSequentialGroup()
                        .addGroup(ServicoComparadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ServicoComparadoLayout.createSequentialGroup()
                                .addComponent(jTextFieldAno3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelSearch3))
                            .addComponent(jLabel27))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
        );
        ServicoComparadoLayout.setVerticalGroup(
            ServicoComparadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ServicoComparadoLayout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(ServicoComparadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ServicoComparadoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel27)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(ServicoComparadoLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(ServicoComparadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldAno3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSearch3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)))
                .addGap(25, 25, 25)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbed.addTab("Comparativo Serviços", ServicoComparado);

        lançamentos.setBackground(new java.awt.Color(255, 255, 255));
        lançamentos.setPreferredSize(new java.awt.Dimension(985, 843));

        jPanel5.setBackground(new java.awt.Color(48, 63, 79));
        jPanel5.setPreferredSize(new java.awt.Dimension(0, 100));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jScrollPane4.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane4.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane4.setAutoscrolls(true);
        jScrollPane4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane4.setPreferredSize(new java.awt.Dimension(469, 402));

        jTableLancamentos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTableLancamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Serviço", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro", "id_orcamento", "id_servico"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableLancamentos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableLancamentos.setGridColor(new java.awt.Color(255, 255, 255));
        jTableLancamentos.setRowHeight(22);
        jTableLancamentos.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableLancamentos.setShowGrid(true);
        jScrollPane4.setViewportView(jTableLancamentos);
        if (jTableLancamentos.getColumnModel().getColumnCount() > 0) {
            jTableLancamentos.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTableLancamentos.getColumnModel().getColumn(13).setMinWidth(1);
            jTableLancamentos.getColumnModel().getColumn(13).setPreferredWidth(1);
            jTableLancamentos.getColumnModel().getColumn(13).setMaxWidth(1);
            jTableLancamentos.getColumnModel().getColumn(14).setMinWidth(1);
            jTableLancamentos.getColumnModel().getColumn(14).setPreferredWidth(1);
            jTableLancamentos.getColumnModel().getColumn(14).setMaxWidth(1);
        }

        jPanel10.setBackground(new java.awt.Color(48, 63, 79));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Lançamentos Realizados");

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-financas-48.png"))); // NOI18N

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Confira aqui os pagamentos realizados!");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jComboBoxLancamento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxLancamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));
        jComboBoxLancamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxLancamentoItemStateChanged(evt);
            }
        });
        jComboBoxLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxLancamentoActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Lançamento em:");

        jComboBoxVencimento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxVencimento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro" }));
        jComboBoxVencimento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxVencimentoItemStateChanged(evt);
            }
        });
        jComboBoxVencimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxVencimentoActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Vencimento em:");

        jButtonEditar2.setBackground(new java.awt.Color(255, 51, 51));
        jButtonEditar2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonEditar2.setForeground(new java.awt.Color(255, 255, 255));
        jButtonEditar2.setText("Pagar | Editar");
        jButtonEditar2.setBorder(null);
        jButtonEditar2.setBorderPainted(false);
        jButtonEditar2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonEditar2.setFocusPainted(false);
        jButtonEditar2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEditar2.setPreferredSize(new java.awt.Dimension(150, 65));
        jButtonEditar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditar2ActionPerformed(evt);
            }
        });

        btnBuscaPorAno4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconLupa.png"))); // NOI18N
        btnBuscaPorAno4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBuscaPorAno4MousePressed(evt);
            }
        });

        jTextFieldAno4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("y"))));
        jTextFieldAno4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Busca por Ano");

        jLabelExcluir3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelExcluir3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelExcluir3.setText("Excluir");
        jLabelExcluir3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelExcluir3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelExcluir3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelExcluir3MousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ou");

        javax.swing.GroupLayout lançamentosLayout = new javax.swing.GroupLayout(lançamentos);
        lançamentos.setLayout(lançamentosLayout);
        lançamentosLayout.setHorizontalGroup(
            lançamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(lançamentosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(lançamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(lançamentosLayout.createSequentialGroup()
                        .addGroup(lançamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(lançamentosLayout.createSequentialGroup()
                                .addComponent(jTextFieldAno4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscaPorAno4, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)))
                        .addGap(162, 162, 162)
                        .addGroup(lançamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(lançamentosLayout.createSequentialGroup()
                                .addComponent(jComboBoxLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(lançamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jComboBoxVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(jButtonEditar2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jLabelExcluir3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
        );
        lançamentosLayout.setVerticalGroup(
            lançamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lançamentosLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(lançamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(lançamentosLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lançamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscaPorAno4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldAno4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(lançamentosLayout.createSequentialGroup()
                        .addGroup(lançamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lançamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lançamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBoxVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonEditar2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelExcluir3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addComponent(jComboBoxLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbed.addTab("Lançamentos", lançamentos);

        DespesasPrevistas.setBackground(new java.awt.Color(255, 255, 255));
        DespesasPrevistas.setPreferredSize(new java.awt.Dimension(985, 843));

        jScrollPane8.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jScrollPane8.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane8.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane8.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane8.setAutoscrolls(true);
        jScrollPane8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane8.setPreferredSize(new java.awt.Dimension(465, 402));

        jTableConsultaOrcamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTableConsultaOrcamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Serviço", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro", "id_orcamento", "id_servico"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableConsultaOrcamento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableConsultaOrcamento.setGridColor(new java.awt.Color(255, 255, 255));
        jTableConsultaOrcamento.setRowHeight(22);
        jTableConsultaOrcamento.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableConsultaOrcamento.setShowGrid(true);
        jScrollPane8.setViewportView(jTableConsultaOrcamento);
        if (jTableConsultaOrcamento.getColumnModel().getColumnCount() > 0) {
            jTableConsultaOrcamento.getColumnModel().getColumn(0).setMinWidth(120);
            jTableConsultaOrcamento.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTableConsultaOrcamento.getColumnModel().getColumn(13).setMinWidth(1);
            jTableConsultaOrcamento.getColumnModel().getColumn(13).setPreferredWidth(1);
            jTableConsultaOrcamento.getColumnModel().getColumn(13).setMaxWidth(1);
            jTableConsultaOrcamento.getColumnModel().getColumn(14).setMinWidth(1);
            jTableConsultaOrcamento.getColumnModel().getColumn(14).setPreferredWidth(1);
            jTableConsultaOrcamento.getColumnModel().getColumn(14).setMaxWidth(1);
        }

        jPanel8.setBackground(new java.awt.Color(48, 63, 79));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Despesas Previstas");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-financas-48.png"))); // NOI18N
        jLabel19.setText("jLabel16");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Confira aqui as despesas previstas do seu salão!");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 111, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(48, 63, 79));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTextFieldAno6.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("y"))));
        jTextFieldAno6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Busca por Ano");

        jLabelSearch2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconLupa.png"))); // NOI18N
        jLabelSearch2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelSearch2MousePressed(evt);
            }
        });

        jLabelEditar2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelEditar2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEditar2.setText("Editar");
        jLabelEditar2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelEditar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelEditar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelEditar2MousePressed(evt);
            }
        });

        jLabelExcluir2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelExcluir2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelExcluir2.setText("Excluir");
        jLabelExcluir2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelExcluir2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelExcluir2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelExcluir2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout DespesasPrevistasLayout = new javax.swing.GroupLayout(DespesasPrevistas);
        DespesasPrevistas.setLayout(DespesasPrevistasLayout);
        DespesasPrevistasLayout.setHorizontalGroup(
            DespesasPrevistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(DespesasPrevistasLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(DespesasPrevistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DespesasPrevistasLayout.createSequentialGroup()
                        .addGroup(DespesasPrevistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DespesasPrevistasLayout.createSequentialGroup()
                                .addComponent(jTextFieldAno6, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelSearch2))
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelEditar2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabelExcluir2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DespesasPrevistasLayout.setVerticalGroup(
            DespesasPrevistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DespesasPrevistasLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(DespesasPrevistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(DespesasPrevistasLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DespesasPrevistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldAno6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSearch2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(DespesasPrevistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelEditar2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelExcluir2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbed.addTab("Despesas Previstas", DespesasPrevistas);

        ServicosRealizados1.setBackground(new java.awt.Color(255, 255, 255));
        ServicosRealizados1.setPreferredSize(new java.awt.Dimension(985, 843));

        jScrollPane9.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jScrollPane9.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane9.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane9.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane9.setAutoscrolls(true);
        jScrollPane9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane9.setPreferredSize(new java.awt.Dimension(465, 402));

        jTableComparativoDespesas.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTableComparativoDespesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Serviço", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro", "id_orcamento", "id_servico"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableComparativoDespesas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableComparativoDespesas.setGridColor(new java.awt.Color(255, 255, 255));
        jTableComparativoDespesas.setRowHeight(22);
        jTableComparativoDespesas.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableComparativoDespesas.setShowGrid(true);
        jScrollPane9.setViewportView(jTableComparativoDespesas);
        if (jTableComparativoDespesas.getColumnModel().getColumnCount() > 0) {
            jTableComparativoDespesas.getColumnModel().getColumn(0).setMinWidth(120);
            jTableComparativoDespesas.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTableComparativoDespesas.getColumnModel().getColumn(13).setMinWidth(1);
            jTableComparativoDespesas.getColumnModel().getColumn(13).setPreferredWidth(1);
            jTableComparativoDespesas.getColumnModel().getColumn(13).setMaxWidth(1);
            jTableComparativoDespesas.getColumnModel().getColumn(14).setMinWidth(1);
            jTableComparativoDespesas.getColumnModel().getColumn(14).setPreferredWidth(1);
            jTableComparativoDespesas.getColumnModel().getColumn(14).setMaxWidth(1);
        }

        jPanel6.setBackground(new java.awt.Color(48, 63, 79));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Comparativo de Despesas");

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-financas-48.png"))); // NOI18N
        jLabel31.setText("jLabel16");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Aqui você confere o quanto você cumpriu do previsto!");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(48, 63, 79));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTextFieldAno7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("y"))));
        jTextFieldAno7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel22.setText("Busca por Ano");

        jLabelSearch5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconLupa.png"))); // NOI18N
        jLabelSearch5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelSearch5MousePressed(evt);
            }
        });

        javax.swing.GroupLayout ServicosRealizados1Layout = new javax.swing.GroupLayout(ServicosRealizados1);
        ServicosRealizados1.setLayout(ServicosRealizados1Layout);
        ServicosRealizados1Layout.setHorizontalGroup(
            ServicosRealizados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ServicosRealizados1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(ServicosRealizados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
                    .addGroup(ServicosRealizados1Layout.createSequentialGroup()
                        .addGroup(ServicosRealizados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addGroup(ServicosRealizados1Layout.createSequentialGroup()
                                .addComponent(jTextFieldAno7, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelSearch5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(25, 25, 25))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ServicosRealizados1Layout.setVerticalGroup(
            ServicosRealizados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ServicosRealizados1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ServicosRealizados1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ServicosRealizados1Layout.createSequentialGroup()
                        .addComponent(jTextFieldAno7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                        .addGap(25, 25, 25)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ServicosRealizados1Layout.createSequentialGroup()
                        .addComponent(jLabelSearch5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jTabbed.addTab("Comparativo Despesas", ServicosRealizados1);

        add(jTabbed, java.awt.BorderLayout.CENTER);
        jTabbed.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    private void listarOrcamentoServicoRealizado(String ano) {
        modeloServicoRealizado.getTodosServicosRealizados(ano);
        jTableConsultaServicoRealizado.setModel(modeloServicoRealizado);
        jTableConsultaServicoRealizado.getColumnModel().getColumn(0).setCellRenderer(new FormatacaoConteudo(Color.WHITE, jTableConsultaServicoRealizado.getRowCount() - 1));

    }


    private void jLabelSearch1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSearch1MousePressed

        if (jTextFieldAno1.getText().equals("")) {
            String ano = String.valueOf(LocalDate.now().getYear());
            modeloServicoPrevisto.getServicoPrevisto(ano);
            jTableConsultaOrcamentoServico.setModel(modeloServicoPrevisto);
            jTableConsultaOrcamentoServico.getColumnModel().getColumn(0).setCellRenderer(new FormatacaoConteudo(Color.WHITE, jTableConsultaOrcamentoServico.getRowCount() - 1));
        } else {
            modeloServicoPrevisto.getServicoPrevisto(jTextFieldAno1.getText());
            jTableConsultaOrcamentoServico.setModel(modeloServicoPrevisto);
            jTableConsultaOrcamentoServico.getColumnModel().getColumn(0).setCellRenderer(new FormatacaoConteudo(Color.WHITE, jTableConsultaOrcamentoServico.getRowCount() - 1));

        }
    }//GEN-LAST:event_jLabelSearch1MousePressed

    private void jTabbedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedStateChanged
          CentralizaElementosTabela render = new CentralizaElementosTabela();
        int indice = jTabbed.getSelectedIndex();
        String ano = String.valueOf(LocalDate.now().getYear());
        switch (indice) {

            case 0:
                listarOrcamentoServicoRealizado(ano);
                break;
            case 1:
               
                modeloServicoPrevisto.getServicoPrevisto(ano);
                jTableConsultaOrcamentoServico.setModel(modeloServicoPrevisto);
                jTableConsultaOrcamentoServico.getColumnModel().getColumn(0).setCellRenderer(new FormatacaoConteudo(Color.WHITE, jTableConsultaOrcamentoServico.getRowCount() - 1));
                break;
                

            case 2:
                modeloServicoComparado.getOrcamentoComparadoPorAno(ano);
                jTableServicoComparado.setModel(modeloServicoComparado);
                jTableServicoComparado.setDefaultRenderer(Object.class, render);
                break;

            case 3:
                modeloLancamento.getDespesasAnual(ano);
                jTableLancamentos.setModel(modeloLancamento);
                jTableLancamentos.getColumnModel().getColumn(2).setCellRenderer(new DestacaVencimentosTabela(2, modeloLancamento));
              
                break;

            case 4:
                modeloDespesaPrevista.getTodasDespesasPrevistas(ANOATUAL);
                jTableConsultaOrcamento.setModel(modeloDespesaPrevista);
                jTableConsultaOrcamento.getColumnModel().getColumn(0).setCellRenderer(new FormatacaoConteudo(Color.WHITE, jTableConsultaOrcamento.getRowCount() - 1));

      
                break;
            case 5:
                
                modeloDespesaComparada.getDespesaComparadaPorAno(ano);
                jTableComparativoDespesas.setModel(modeloDespesaComparada);
                
               
                jTableComparativoDespesas.setDefaultRenderer(Object.class, render);


                break;
        }
      
    }//GEN-LAST:event_jTabbedStateChanged

    private void btnBuscaPorAno4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscaPorAno4MousePressed

        if (jTextFieldAno4.getText().equals("")) {
            modeloLancamento.getDespesasAnual(String.valueOf(LocalDate.now().getYear()));
            jTableLancamentos.setModel(modeloLancamento);

        } else {
            modeloLancamento.getDespesasAnual(jTextFieldAno4.getText());
            jTableLancamentos.setModel(modeloLancamento);
        }
    }//GEN-LAST:event_btnBuscaPorAno4MousePressed

    private void jComboBoxVencimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxVencimentoActionPerformed

        modeloLancamento.getDespesasPorVencimento(jComboBoxVencimento.getSelectedIndex() + 1);
        jTableLancamentos.setModel(modeloLancamento);
    }//GEN-LAST:event_jComboBoxVencimentoActionPerformed

    private void jComboBoxLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxLancamentoActionPerformed
        modeloLancamento.getDespesasPorLancamento(jComboBoxLancamento.getSelectedIndex() + 1);
        jTableLancamentos.setModel(modeloLancamento);
    }//GEN-LAST:event_jComboBoxLancamentoActionPerformed

    private void jButtonEditar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditar2ActionPerformed
        int index = jTableLancamentos.getSelectedRow();
        DespesaController dc = new DespesaController();
        boolean sucesso;

        if (index > -1) {
            long idLancamento = modeloLancamento.getDespesa(index).getIdDespesa();

            sucesso = dc.editarDespesa(idLancamento);

            if (!sucesso) {
                JOptionPane.showMessageDialog(null, "Erro ao selecionar PKDespesa");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma despesa.");
        }
    }//GEN-LAST:event_jButtonEditar2ActionPerformed

    private void jComboBoxLancamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxLancamentoItemStateChanged

    }//GEN-LAST:event_jComboBoxLancamentoItemStateChanged

    private void jComboBoxVencimentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxVencimentoItemStateChanged


    }//GEN-LAST:event_jComboBoxVencimentoItemStateChanged

    private void jLabelEditar1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEditar1MousePressed
        int indice = jTableConsultaOrcamentoServico.getSelectedRow();
        
        if( indice > -1){
            
            long id = modeloServicoPrevisto.getOrcamentoServico(indice).getId_orcamento();
           OrcamentoServico ocs = new OrcamentoController().buscarOrcamentoServico(id );
            new EditarOrcamentoServico(ocs).setVisible(true);
        }
            
    }//GEN-LAST:event_jLabelEditar1MousePressed

    private void jLabelExcluir1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExcluir1MousePressed
       
        int indice = jTableConsultaOrcamentoServico.getSelectedRow();
        int opc = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este orçamento?\n"
            + jTableConsultaOrcamentoServico.getValueAt(indice, 0), "Excluir Orçamento", JOptionPane.YES_NO_OPTION);
        
        if(opc == 0){
           if(indice > -1){
            
            OrcamentoServico os = modeloServicoPrevisto.getOrcamentoServico(indice);
            boolean sucesso = new OrcamentoController().excluirOrcamentoServico(os.getId_orcamento());         
            if(sucesso){
                JOptionPane.showMessageDialog(null, "Orçamento Serviço excluído com sucesso.");
                modeloServicoPrevisto.getServicoPrevisto(ANOATUAL);
                jTableConsultaOrcamentoServico.setModel(modeloServicoPrevisto);
                jTableConsultaOrcamentoServico.getColumnModel().getColumn(0).setCellRenderer(new FormatacaoConteudo(Color.WHITE, jTableConsultaOrcamentoServico.getRowCount() - 1));

            }else{
                JOptionPane.showMessageDialog(null, "ERRO ao excluir orçamento serviço.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecione um orçamento antes.");
        } 
        }
        
    }//GEN-LAST:event_jLabelExcluir1MousePressed

    private void buscaPorAnoServicoRealizadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscaPorAnoServicoRealizadoMousePressed
        if (jTextFieldAno2.getText().equals("")) {
            String ano = String.valueOf(LocalDate.now().getYear());
            modeloServicoRealizado.getTodosServicosRealizados(ano);
            jTableConsultaOrcamentoServico.setModel(modeloServicoRealizado);
        } else {
            modeloServicoRealizado.getTodosServicosRealizados(jTextFieldAno2.getText());
            jTableConsultaOrcamentoServico.setModel(modeloServicoRealizado);
        }
    }//GEN-LAST:event_buscaPorAnoServicoRealizadoMousePressed

    private void jLabelSearch3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSearch3MousePressed
       if (jTextFieldAno3.getText().equals("")) {
            String ano = String.valueOf(LocalDate.now().getYear());
            modeloServicoComparado.getOrcamentoComparadoPorAno(ano);
            jTableServicoComparado.setModel(modeloServicoComparado);
        } else {
            modeloServicoComparado.getOrcamentoComparadoPorAno(jTextFieldAno3.getText());
             jTableServicoComparado.setModel(modeloServicoComparado);
        }
    }//GEN-LAST:event_jLabelSearch3MousePressed

    private void jLabelSearch2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSearch2MousePressed
        if (jTextFieldAno6.getText().equals("")) {
            String ano = String.valueOf(LocalDate.now().getYear());
            modeloDespesaPrevista.getTodasDespesasPrevistas(ano);
            jTableConsultaOrcamento.setModel(modeloDespesaPrevista);
            jTableConsultaOrcamento.getColumnModel().getColumn(0).setCellRenderer(new FormatacaoConteudo(Color.WHITE, jTableConsultaOrcamento.getRowCount() - 1));

        } else {
            modeloDespesaPrevista.getTodasDespesasPrevistas(jTextFieldAno6.getText());
            jTableConsultaOrcamento.setModel(modeloDespesaPrevista);
            jTableConsultaOrcamento.getColumnModel().getColumn(0).setCellRenderer(new FormatacaoConteudo(Color.WHITE, jTableConsultaOrcamento.getRowCount() - 1));

        }
    }//GEN-LAST:event_jLabelSearch2MousePressed

    private void jLabelEditar2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEditar2MousePressed
        boolean resultado;
        int indice = jTableConsultaOrcamento.getSelectedRow();

        if (indice > -1 && indice != jTableConsultaOrcamento.getRowCount() - 1) {
            try {

                long id_orcamento = modeloDespesaPrevista.getOrcamento(indice).getId_orcamento(); // Retorna IDORCAMENTO
                OrcamentoController oc = new OrcamentoController();
                resultado = oc.editarOrcamento(id_orcamento);

                if (!resultado) {
                    JOptionPane.showMessageDialog(null, "Erro ao selecionar PKOrçamento");
                }

            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, "Erro ao retornar informações do orçamento: " + e);
            }
        } else {

            JOptionPane.showMessageDialog(null, "Selecione um orçamento.");
        }
    }//GEN-LAST:event_jLabelEditar2MousePressed

    private void jLabelExcluir2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExcluir2MousePressed
       int indice = jTableConsultaOrcamento.getSelectedRow();
        int opc = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este orçamento?\n"
            + jTableConsultaOrcamento.getValueAt(indice, 0)+"\n\nExcluir este orçamento, significa perder todos os"
                    + " lançamentos\nregistrados para este orçamento."
                , "Excluir Orçamento", JOptionPane.YES_NO_OPTION);

        if (opc == 0) {
            if (indice > -1) {
                try {

                    long id_orcamento = modeloDespesaPrevista.getOrcamento(indice).getId_orcamento(); // Retorna ID_ORÇAMENTO
                    OrcamentoController oc = new OrcamentoController();

                    if (oc.excluirOrcamento(id_orcamento)) {
                        JOptionPane.showMessageDialog(null, "Orçamento deletado com sucesso.");

                        modeloDespesaPrevista.getTodasDespesasPrevistas(ANOATUAL);
                        jTableConsultaOrcamento.setModel(modeloDespesaPrevista);
                        jTableConsultaOrcamento.getColumnModel().getColumn(0).setCellRenderer(new ApresentaFinancas.FormatacaoConteudo(Color.WHITE, jTableConsultaOrcamento.getRowCount() - 1));

                    } else {
                        JOptionPane.showMessageDialog(null, "Não foi possível excluir o Orçamento. Selecione um índice válido na tabela");
                    }
                } catch (HeadlessException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir orçamento: " + e);
                }

            }
        }
    }//GEN-LAST:event_jLabelExcluir2MousePressed

    private void jLabelSearch5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSearch5MousePressed
        if (jTextFieldAno7.getText().equals("")) {
            String ano = String.valueOf(LocalDate.now().getYear());
            modeloDespesaComparada.getDespesaComparadaPorAno(ano);
            jTableComparativoDespesas.setModel(modeloDespesaComparada);

        } else {
            modeloDespesaComparada.getDespesaComparadaPorAno(jTextFieldAno7.getText());
            jTableComparativoDespesas.setModel(modeloDespesaComparada);
            jTableComparativoDespesas.getColumnModel().getColumn(0).setCellRenderer(new ApresentaFinancas.FormatacaoConteudo(Color.WHITE, jTableConsultaOrcamento.getRowCount() - 1));

                
        }
        
    }//GEN-LAST:event_jLabelSearch5MousePressed

    private void jLabelExcluir3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExcluir3MousePressed
      
        int indice = jTableLancamentos.getSelectedRow();
        int opc = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este lançamento?"
             ,"Excluir Lançamento", JOptionPane.YES_NO_OPTION);
        
        if( opc == 0){
            
            if(indice > -1){
                
                Despesa d = modeloLancamento.getDespesa(indice);
                boolean sucesso = new DespesaController().excluirDespesa(d.getIdDespesa());
                
                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Lançamento excluído com sucesso.");
                    modeloLancamento.getDespesasAnual(ANOATUAL);
                    jTableLancamentos.setModel(modeloLancamento);
                } else {
                    JOptionPane.showMessageDialog(null, "ERRO ao excluir lançamento.");
                }
            }
        }
        
    }//GEN-LAST:event_jLabelExcluir3MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DespesasPrevistas;
    private javax.swing.JPanel ServicoComparado;
    private javax.swing.JPanel ServicoPrevisto;
    private javax.swing.JPanel ServicosRealizados;
    private javax.swing.JPanel ServicosRealizados1;
    private javax.swing.JLabel btnBuscaPorAno4;
    private javax.swing.JLabel buscaPorAnoServicoRealizado;
    private javax.swing.JButton jButtonEditar2;
    private javax.swing.JComboBox<String> jComboBoxLancamento;
    private javax.swing.JComboBox<String> jComboBoxVencimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelEditar1;
    private javax.swing.JLabel jLabelEditar2;
    private javax.swing.JLabel jLabelExcluir1;
    private javax.swing.JLabel jLabelExcluir2;
    private javax.swing.JLabel jLabelExcluir3;
    private javax.swing.JLabel jLabelSearch1;
    private javax.swing.JLabel jLabelSearch2;
    private javax.swing.JLabel jLabelSearch3;
    private javax.swing.JLabel jLabelSearch5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbed;
    private javax.swing.JTable jTableComparativoDespesas;
    private javax.swing.JTable jTableConsultaOrcamento;
    private javax.swing.JTable jTableConsultaOrcamentoServico;
    private javax.swing.JTable jTableConsultaServicoRealizado;
    private javax.swing.JTable jTableLancamentos;
    private javax.swing.JTable jTableServicoComparado;
    private javax.swing.JFormattedTextField jTextFieldAno1;
    private javax.swing.JFormattedTextField jTextFieldAno2;
    private javax.swing.JFormattedTextField jTextFieldAno3;
    private javax.swing.JFormattedTextField jTextFieldAno4;
    private javax.swing.JFormattedTextField jTextFieldAno6;
    private javax.swing.JFormattedTextField jTextFieldAno7;
    private javax.swing.JPanel lançamentos;
    // End of variables declaration//GEN-END:variables
}
