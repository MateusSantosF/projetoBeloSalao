/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.view.Apresenta;

import BeutifulSalon.Ferramentas.ManipulaFontes;
import BeutifulSalon.Tabelas.DespesaComparadaTableModel;
import BeutifulSalon.Tabelas.DespesaPrevistaTableModel;
import BeutifulSalon.Tabelas.DestacaVencimentosTabela;
import BeutifulSalon.Tabelas.LancamentoTableModel;
import BeutifulSalon.Tabelas.ServicoComparadoTableModel;
import BeutifulSalon.Tabelas.ServicoPrevistoTableModel;
import BeutifulSalon.Tabelas.ServicoRealizadoTableModel;
import BeutifulSalon.controller.DespesaController;
import BeutifulSalon.controller.OrcamentoController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
        jLabel7.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 50f)); //Comparativo de Serviços Realizados
        jLabel23.setFont(mf.getFont(mf.LIGHT, Font.PLAIN, 30f)); //Aqui você encontra um comparativo entre o previsto e o realizado!
        jLabel10.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Buscar por Ano
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
        jLabel12.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Buscar por Ano
        jTableComparativoDespesas.setFont(mf.getFont(mf.SEMIBOLD, Font.PLAIN, 15f)); //Tabela

        //DESPESAS PREVISTAS
        jLabel1.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 50f)); //Despesas Previstas
        jLabel25.setFont(mf.getFont(mf.LIGHT, Font.PLAIN, 30f)); //Confira aqui o orçamento de despesas previstas do seu salão!
        jLabel3.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Buscar por Ano
        jLabelEditar.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Editar
        jLabelExcluir.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Excluir
        jTableConsultaOrcamento.setFont(mf.getFont(mf.SEMIBOLD, Font.PLAIN, 15f)); //Tabela
   
        String ano = String.valueOf(LocalDate.now().getYear());
        int mesAtual = LocalDate.now().getMonth().getValue() - 1;

        jComboBoxVencimento.setSelectedIndex(mesAtual);
        jComboBoxLancamento.setSelectedIndex(mesAtual);
        listarOrcamentoServicoRealizado(ano);

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
        ComparativoServico = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableServicoComparado = new javax.swing.JTable();
        jTextFieldAno3 = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabelSearch3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        Lancamentos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLancamentos = new javax.swing.JTable();
        jTextFieldAno4 = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        btnBuscaPorAno4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBoxVencimento = new javax.swing.JComboBox<>();
        jComboBoxLancamento = new javax.swing.JComboBox<>();
        jButtonEditar2 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        ComparativoDespesas = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableComparativoDespesas = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldAno5 = new javax.swing.JFormattedTextField();
        jLabelSearch4 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        DespesaPrevista = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableConsultaOrcamento = new javax.swing.JTable();
        jLabelSearch = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldAno = new javax.swing.JFormattedTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabelEditar = new javax.swing.JLabel();
        jLabelExcluir = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1086, 686));

        jTabbed.setBackground(new java.awt.Color(255, 255, 255));
        jTabbed.setPreferredSize(new java.awt.Dimension(1086, 686));
        jTabbed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedStateChanged(evt);
            }
        });

        ServicoPrevisto.setBackground(new java.awt.Color(255, 255, 255));
        ServicoPrevisto.setPreferredSize(new java.awt.Dimension(1086, 686));

        jPanel1.setBackground(new java.awt.Color(48, 63, 79));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
        );

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane3.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane3.setAutoscrolls(true);
        jScrollPane3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane3.setPreferredSize(new java.awt.Dimension(469, 402));

        jTableConsultaOrcamentoServico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        jTableConsultaOrcamentoServico.setGridColor(new java.awt.Color(34, 34, 34));
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
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 1044, Short.MAX_VALUE))
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
                        .addComponent(jLabel6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ServicoPrevistoLayout.createSequentialGroup()
                        .addGroup(ServicoPrevistoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ServicoPrevistoLayout.createSequentialGroup()
                                .addComponent(jTextFieldAno1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelSearch1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25))))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ServicoPrevistoLayout.setVerticalGroup(
            ServicoPrevistoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ServicoPrevistoLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ServicoPrevistoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ServicoPrevistoLayout.createSequentialGroup()
                        .addGroup(ServicoPrevistoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldAno1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSearch1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                        .addGap(25, 25, 25)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ServicoPrevistoLayout.createSequentialGroup()
                        .addGroup(ServicoPrevistoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabelEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jTabbed.addTab("Serviços Previstos", ServicoPrevisto);
        ServicoPrevisto.getAccessibleContext().setAccessibleParent(jTabbed);

        ServicosRealizados.setBackground(new java.awt.Color(255, 255, 255));
        ServicosRealizados.setPreferredSize(new java.awt.Dimension(1086, 686));

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane5.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane5.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane5.setAutoscrolls(true);
        jScrollPane5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane5.setPreferredSize(new java.awt.Dimension(465, 402));

        jTableConsultaServicoRealizado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        jTableConsultaServicoRealizado.setGridColor(new java.awt.Color(34, 34, 34));
        jTableConsultaServicoRealizado.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableConsultaServicoRealizado.setShowGrid(true);
        jScrollPane5.setViewportView(jTableConsultaServicoRealizado);
        if (jTableConsultaServicoRealizado.getColumnModel().getColumnCount() > 0) {
            jTableConsultaServicoRealizado.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTableConsultaServicoRealizado.getColumnModel().getColumn(13).setMinWidth(1);
            jTableConsultaServicoRealizado.getColumnModel().getColumn(13).setPreferredWidth(1);
            jTableConsultaServicoRealizado.getColumnModel().getColumn(13).setMaxWidth(1);
            jTableConsultaServicoRealizado.getColumnModel().getColumn(14).setMinWidth(1);
            jTableConsultaServicoRealizado.getColumnModel().getColumn(14).setPreferredWidth(1);
            jTableConsultaServicoRealizado.getColumnModel().getColumn(14).setMaxWidth(1);
        }

        jTextFieldAno2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("y"))));

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
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 1044, Short.MAX_VALUE)
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
            .addGroup(ServicosRealizadosLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
            .addGroup(ServicosRealizadosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(ServicosRealizadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1098, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addGroup(ServicosRealizadosLayout.createSequentialGroup()
                        .addComponent(jTextFieldAno2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscaPorAnoServicoRealizado)))
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ServicosRealizadosLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ServicosRealizadosLayout.setVerticalGroup(
            ServicosRealizadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ServicosRealizadosLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ServicosRealizadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldAno2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscaPorAnoServicoRealizado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbed.addTab("Serviços Realizados", ServicosRealizados);

        ComparativoServico.setBackground(new java.awt.Color(255, 255, 255));
        ComparativoServico.setPreferredSize(new java.awt.Dimension(1086, 686));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane4.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane4.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane4.setAutoscrolls(true);
        jScrollPane4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane4.setPreferredSize(new java.awt.Dimension(469, 402));

        jTableServicoComparado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableServicoComparado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro", "id_orcamento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableServicoComparado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableServicoComparado.setGridColor(new java.awt.Color(34, 34, 34));
        jTableServicoComparado.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableServicoComparado.setShowGrid(true);
        jScrollPane4.setViewportView(jTableServicoComparado);
        if (jTableServicoComparado.getColumnModel().getColumnCount() > 0) {
            jTableServicoComparado.getColumnModel().getColumn(13).setMinWidth(1);
            jTableServicoComparado.getColumnModel().getColumn(13).setMaxWidth(1);
        }

        jTextFieldAno3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("y"))));
        jTextFieldAno3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAno3ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("Busca por Ano");

        jLabelSearch3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconLupa.png"))); // NOI18N
        jLabelSearch3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelSearch3MousePressed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(48, 63, 79));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Comparativo de Serviços Realizados");

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icons8-servicos-48.png"))); // NOI18N
        jLabel19.setText("jLabel19");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Aqui você encontra um comparativo entre o previsto e o realizado!");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
                        .addGap(392, 392, 392))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 982, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(48, 63, 79));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ComparativoServicoLayout = new javax.swing.GroupLayout(ComparativoServico);
        ComparativoServico.setLayout(ComparativoServicoLayout);
        ComparativoServicoLayout.setHorizontalGroup(
            ComparativoServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ComparativoServicoLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(ComparativoServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(ComparativoServicoLayout.createSequentialGroup()
                        .addComponent(jTextFieldAno3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelSearch3))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ComparativoServicoLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        ComparativoServicoLayout.setVerticalGroup(
            ComparativoServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ComparativoServicoLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ComparativoServicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldAno3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSearch3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbed.addTab("Comparativo Serviços", ComparativoServico);

        Lancamentos.setBackground(new java.awt.Color(255, 255, 255));
        Lancamentos.setPreferredSize(new java.awt.Dimension(1086, 686));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTableLancamentos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableLancamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "Data Lançamento", "Data Vencimento", "Data Pagamento", "Forma de Pagamento", "Valor Pago", "Status", "ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableLancamentos);
        if (jTableLancamentos.getColumnModel().getColumnCount() > 0) {
            jTableLancamentos.getColumnModel().getColumn(7).setMinWidth(1);
            jTableLancamentos.getColumnModel().getColumn(7).setPreferredWidth(1);
            jTableLancamentos.getColumnModel().getColumn(7).setMaxWidth(1);
        }

        jTextFieldAno4.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("y"))));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Busca por Ano");

        btnBuscaPorAno4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconLupa.png"))); // NOI18N
        btnBuscaPorAno4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBuscaPorAno4MousePressed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Vencimento em:");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setText("Lançamento em:");

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

        jPanel8.setBackground(new java.awt.Color(48, 63, 79));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Lançamentos Realizados");

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-financas-48.png"))); // NOI18N
        jLabel18.setText("jLabel18");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Confira aqui os pagamentos realizados!");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel26))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
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

        javax.swing.GroupLayout LancamentosLayout = new javax.swing.GroupLayout(Lancamentos);
        Lancamentos.setLayout(LancamentosLayout);
        LancamentosLayout.setHorizontalGroup(
            LancamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LancamentosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(LancamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(LancamentosLayout.createSequentialGroup()
                        .addGroup(LancamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldAno4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscaPorAno4)
                        .addGap(18, 18, 18)
                        .addGroup(LancamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jComboBoxLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(LancamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxVencimento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(54, 54, 54)
                        .addComponent(jButtonEditar2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 478, Short.MAX_VALUE)))
                .addGap(22, 22, 22))
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        LancamentosLayout.setVerticalGroup(
            LancamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LancamentosLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(LancamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LancamentosLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(LancamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldAno4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscaPorAno4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LancamentosLayout.createSequentialGroup()
                        .addGroup(LancamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(LancamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxLancamento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonEditar2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbed.addTab("Lançamentos", Lancamentos);

        ComparativoDespesas.setBackground(new java.awt.Color(255, 255, 255));
        ComparativoDespesas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ComparativoDespesas.setPreferredSize(new java.awt.Dimension(1086, 686));

        jScrollPane6.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane6.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane6.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane6.setAutoscrolls(true);
        jScrollPane6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane6.setPreferredSize(new java.awt.Dimension(469, 402));

        jTableComparativoDespesas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableComparativoDespesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro", "id_orcamento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableComparativoDespesas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableComparativoDespesas.setGridColor(new java.awt.Color(34, 34, 34));
        jTableComparativoDespesas.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableComparativoDespesas.setShowGrid(true);
        jScrollPane6.setViewportView(jTableComparativoDespesas);
        if (jTableComparativoDespesas.getColumnModel().getColumnCount() > 0) {
            jTableComparativoDespesas.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTableComparativoDespesas.getColumnModel().getColumn(13).setMinWidth(1);
            jTableComparativoDespesas.getColumnModel().getColumn(13).setMaxWidth(1);
        }

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Busca por Ano");

        jTextFieldAno5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("y"))));

        jLabelSearch4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconLupa.png"))); // NOI18N
        jLabelSearch4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelSearch4MousePressed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(48, 63, 79));

        jLabel17.setBackground(new java.awt.Color(0, 0, 0));
        jLabel17.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Comparativo de Despesas");

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-financas-48.png"))); // NOI18N
        jLabel21.setText("jLabel21");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Aqui você confere o quanto você cumpriu do previsto!");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(591, 591, 591))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(48, 63, 79));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ComparativoDespesasLayout = new javax.swing.GroupLayout(ComparativoDespesas);
        ComparativoDespesas.setLayout(ComparativoDespesasLayout);
        ComparativoDespesasLayout.setHorizontalGroup(
            ComparativoDespesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ComparativoDespesasLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(ComparativoDespesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12)
                    .addGroup(ComparativoDespesasLayout.createSequentialGroup()
                        .addComponent(jTextFieldAno5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelSearch4)))
                .addGap(25, 25, 25))
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ComparativoDespesasLayout.setVerticalGroup(
            ComparativoDespesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ComparativoDespesasLayout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ComparativoDespesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldAno5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSearch4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbed.addTab("Comparativo Despesas", ComparativoDespesas);

        DespesaPrevista.setBackground(new java.awt.Color(255, 255, 255));
        DespesaPrevista.setPreferredSize(new java.awt.Dimension(1086, 686));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setAutoscrolls(true);
        jScrollPane2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jScrollPane2.setPreferredSize(new java.awt.Dimension(469, 402));

        jTableConsultaOrcamento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTableConsultaOrcamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Despesa", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro", "id_orcamento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableConsultaOrcamento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableConsultaOrcamento.setGridColor(new java.awt.Color(34, 34, 34));
        jTableConsultaOrcamento.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableConsultaOrcamento.setShowGrid(true);
        jScrollPane2.setViewportView(jTableConsultaOrcamento);
        if (jTableConsultaOrcamento.getColumnModel().getColumnCount() > 0) {
            jTableConsultaOrcamento.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTableConsultaOrcamento.getColumnModel().getColumn(13).setMinWidth(1);
            jTableConsultaOrcamento.getColumnModel().getColumn(13).setMaxWidth(1);
        }

        jLabelSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconLupa.png"))); // NOI18N
        jLabelSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelSearchMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Busca por Ano");

        jTextFieldAno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("y"))));

        jPanel6.setBackground(new java.awt.Color(48, 63, 79));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Despesas Previstas");

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-financas-48.png"))); // NOI18N
        jLabel22.setText("jLabel22");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Confira aqui as despesas previstas do seu salão!");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
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

        jLabelEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelEditar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEditar.setText("Editar");
        jLabelEditar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelEditarMousePressed(evt);
            }
        });

        jLabelExcluir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelExcluir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelExcluir.setText("Excluir");
        jLabelExcluir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelExcluirMousePressed(evt);
            }
        });

        javax.swing.GroupLayout DespesaPrevistaLayout = new javax.swing.GroupLayout(DespesaPrevista);
        DespesaPrevista.setLayout(DespesaPrevistaLayout);
        DespesaPrevistaLayout.setHorizontalGroup(
            DespesaPrevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(DespesaPrevistaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(DespesaPrevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DespesaPrevistaLayout.createSequentialGroup()
                        .addGroup(DespesaPrevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DespesaPrevistaLayout.createSequentialGroup()
                                .addComponent(jTextFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jLabelSearch))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        DespesaPrevistaLayout.setVerticalGroup(
            DespesaPrevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DespesaPrevistaLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(DespesaPrevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(DespesaPrevistaLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DespesaPrevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(DespesaPrevistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabelEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbed.addTab("Despesas Previstas", DespesaPrevista);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbed, javax.swing.GroupLayout.DEFAULT_SIZE, 1153, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbed, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
        );

        jTabbed.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    private void listarOrcamentoServicoRealizado(String ano) {
        modeloServicoRealizado.getTodosServicosRealizados(ano);
        jTableConsultaServicoRealizado.setModel(modeloServicoRealizado);
        jTableConsultaServicoRealizado.getColumnModel().getColumn(0).setCellRenderer(new FormatacaoConteudo(Color.WHITE, jTableConsultaServicoRealizado.getRowCount() - 1));

    }


    private void jLabelSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSearchMousePressed

        if (jTextFieldAno.getText().equals("")) {
            modeloDespesaPrevista.getTodasDespesasPrevistas(ANOATUAL);
            jTableConsultaOrcamento.setModel(modeloDespesaPrevista);

        } else {
            modeloDespesaPrevista.getTodasDespesasPrevistas(jTextFieldAno.getText());
            jTableConsultaOrcamento.setModel(modeloDespesaPrevista);
        }

    }//GEN-LAST:event_jLabelSearchMousePressed

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

        int indice = jTabbed.getSelectedIndex();
        String ano = String.valueOf(LocalDate.now().getYear());
        switch (indice) {

            case 0:
                listarOrcamentoServicoRealizado(ano);
                break;
            case 1:
                modeloLancamento.getDespesasAnual(ano);
                jTableLancamentos.setModel(modeloLancamento);
                jTableLancamentos.getColumnModel().getColumn(2).setCellRenderer(new DestacaVencimentosTabela(2, modeloLancamento));
                break;

            case 2:
                modeloServicoComparado.getOrcamentoComparadoPorAno(ano);
                jTableServicoComparado.setModel(modeloServicoComparado);
                break;

            case 3:
                modeloDespesaComparada.getDespesaComparadaPorAno(ano);
                jTableComparativoDespesas.setModel(modeloDespesaComparada);
                break;

            case 4:
                modeloServicoPrevisto.getServicoPrevisto(ano);
                jTableConsultaOrcamentoServico.setModel(modeloServicoPrevisto);
                jTableConsultaOrcamentoServico.getColumnModel().getColumn(0).setCellRenderer(new FormatacaoConteudo(Color.WHITE, jTableConsultaOrcamentoServico.getRowCount() - 1));

                break;
            case 5:
                modeloDespesaPrevista.getTodasDespesasPrevistas(ANOATUAL);
                jTableConsultaOrcamento.setModel(modeloDespesaPrevista);
                jTableConsultaOrcamento.getColumnModel().getColumn(0).setCellRenderer(new ApresentaFinancas.FormatacaoConteudo(Color.WHITE, jTableConsultaOrcamento.getRowCount() - 1));
                break;
        }

    }//GEN-LAST:event_jTabbedStateChanged

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

    private void jLabelSearch4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSearch4MousePressed

        if (jTextFieldAno5.getText().equals("")) {

            String ano = String.valueOf(LocalDate.now().getYear());
            modeloDespesaComparada.getDespesaComparadaPorAno(ano);
            jTableComparativoDespesas.setModel(modeloDespesaComparada);

        } else {
            modeloDespesaComparada.getDespesaComparadaPorAno(jTextFieldAno5.getText());
            jTableComparativoDespesas.setModel(modeloDespesaComparada);
        }
    }//GEN-LAST:event_jLabelSearch4MousePressed

    private void jComboBoxLancamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxLancamentoItemStateChanged

    }//GEN-LAST:event_jComboBoxLancamentoItemStateChanged

    private void jComboBoxVencimentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxVencimentoItemStateChanged


    }//GEN-LAST:event_jComboBoxVencimentoItemStateChanged

    private void jTextFieldAno3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAno3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAno3ActionPerformed

    private void jLabelEditarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEditarMousePressed
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
    }//GEN-LAST:event_jLabelEditarMousePressed

    private void jLabelExcluirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExcluirMousePressed
       int indice = jTableConsultaOrcamento.getSelectedRow();
        int opc = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este orçamento?: "
                + jTableConsultaOrcamento.getValueAt(indice, 0), "Excluir Orçamento", JOptionPane.YES_NO_OPTION);

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
    }//GEN-LAST:event_jLabelExcluirMousePressed

    private void jLabelEditar1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEditar1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelEditar1MousePressed

    private void jLabelExcluir1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExcluir1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelExcluir1MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ComparativoDespesas;
    private javax.swing.JPanel ComparativoServico;
    private javax.swing.JPanel DespesaPrevista;
    private javax.swing.JPanel Lancamentos;
    private javax.swing.JPanel ServicoPrevisto;
    private javax.swing.JPanel ServicosRealizados;
    private javax.swing.JLabel btnBuscaPorAno4;
    private javax.swing.JLabel buscaPorAnoServicoRealizado;
    private javax.swing.JButton jButtonEditar2;
    private javax.swing.JComboBox<String> jComboBoxLancamento;
    private javax.swing.JComboBox<String> jComboBoxVencimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelEditar;
    private javax.swing.JLabel jLabelEditar1;
    private javax.swing.JLabel jLabelExcluir;
    private javax.swing.JLabel jLabelExcluir1;
    private javax.swing.JLabel jLabelSearch;
    private javax.swing.JLabel jLabelSearch1;
    private javax.swing.JLabel jLabelSearch3;
    private javax.swing.JLabel jLabelSearch4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbed;
    private javax.swing.JTable jTableComparativoDespesas;
    private javax.swing.JTable jTableConsultaOrcamento;
    private javax.swing.JTable jTableConsultaOrcamentoServico;
    private javax.swing.JTable jTableConsultaServicoRealizado;
    private javax.swing.JTable jTableLancamentos;
    private javax.swing.JTable jTableServicoComparado;
    private javax.swing.JFormattedTextField jTextFieldAno;
    private javax.swing.JFormattedTextField jTextFieldAno1;
    private javax.swing.JFormattedTextField jTextFieldAno2;
    private javax.swing.JFormattedTextField jTextFieldAno3;
    private javax.swing.JFormattedTextField jTextFieldAno4;
    private javax.swing.JFormattedTextField jTextFieldAno5;
    // End of variables declaration//GEN-END:variables
}
