/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.view.Cadastros;

import BeutifulSalon.Ferramentas.ManipulaFontes;
import BeutifulSalon.controller.OrcamentoController;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Observador;
import BeutifulSalon.model.Orcamento;
import BeutifulSalon.model.Servico;
import BeutifulSalon.view.modais.modalServicoUnico;
import BeutifulSalon.view.modais.modalServicos;
import java.awt.Font;
import java.awt.HeadlessException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Mateus
 */
public class CadastroOrcamentoPrevisto extends javax.swing.JFrame implements Observador {

    /**
     * Creates new form OrcamentoPrevisto
     */
    public CadastroOrcamentoPrevisto() {
        initComponents();
        
        //SERVICOS
        ManipulaFontes mf = new ManipulaFontes(); 
        jLabel4.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 36f)); //Novo Orçamento Previsto de Serviços
        jLabel8.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Identificador
        jLabel7.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Nome do Serviço
        jLabel32.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Exercício (AAAA)

        jCheckBoxFixo1.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Repete
        jLabel31.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Informe a quantidade do serviço selecionado, que acredita realizar mensalmente.

        jLabel9.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Jan
        jLabel10.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Fev
        jLabel11.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Mar
        jLabel13.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Abril
        jLabel25.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Maio
        jLabel26.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Jun
        jLabel12.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Jul
        jLabel28.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Ago
        jLabel29.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Set
        jLabel9.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Out
        jLabel27.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Nov
        jLabel30.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Dez


        btnRegOrcServico.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Registrar
        btnCanc1.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Cancelar

        //DESPESAS

        jLabel1.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 36f)); //Nova Despesa Prevista
        jLabel2.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Nome da despesa
        jLabel3.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Exercício (AAAA)

        jCheckBoxFixo.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Fixo
        jLabel31.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Informe a quantidade do serviço selecionado, que acredita realizar mensalmente.

        jLabel5.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Jan
        jLabel6.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Fev
        jLabel15.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Mar
        jLabel16.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Abril
        jLabel17.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Maio
        jLabel18.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Jun

        jLabel19.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Jul
        jLabel20.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Ago
        jLabel21.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Set
        jLabel22.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Out
        jLabel23.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Nov
        jLabel24.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Dez


        btnReg.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Registrar
        btnCanc.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Cancelar

        String anoAtual = String.valueOf(LocalDate.now().getYear());
        jTextFieldAnoOrc.setText(anoAtual);
        jTextFieldAno.setText(anoAtual);
        DecimalFormat decimal = new DecimalFormat("#,###,###.00");
        NumberFormatter numFormatter = new NumberFormatter(decimal);
        numFormatter.setFormat(decimal);
        numFormatter.setAllowsInvalid(false);
        DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(numFormatter);
        jTextFieldJan.setFormatterFactory(dfFactory);
        jTextFieldFev.setFormatterFactory(dfFactory);
        jTextFieldMarc.setFormatterFactory(dfFactory);
        jTextFieldAbr.setFormatterFactory(dfFactory);
        jTextFieldMaio.setFormatterFactory(dfFactory);
        jTextFieldJun.setFormatterFactory(dfFactory);
        jTextFieldJul.setFormatterFactory(dfFactory);
        jTextFieldAgo.setFormatterFactory(dfFactory);
        jTextFieldSet.setFormatterFactory(dfFactory);
        jTextFieldOut.setFormatterFactory(dfFactory);
        jTextFieldNov.setFormatterFactory(dfFactory);
        jTextFieldDez.setFormatterFactory(dfFactory);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelServicos = new javax.swing.JPanel();
        jTextFieldIdServico = new javax.swing.JTextField();
        jTextFieldNomeServico = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabelAddServico = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jSpinnerJan = new javax.swing.JSpinner();
        jSpinnerFev = new javax.swing.JSpinner();
        jSpinnerMarc = new javax.swing.JSpinner();
        jSpinnerAbr = new javax.swing.JSpinner();
        jSpinnerMai = new javax.swing.JSpinner();
        jSpinnerJun = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jSpinnerAgo = new javax.swing.JSpinner();
        jSpinnerOut = new javax.swing.JSpinner();
        jSpinnerDez = new javax.swing.JSpinner();
        jSpinnerJul = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSpinnerSet = new javax.swing.JSpinner();
        jLabel27 = new javax.swing.JLabel();
        jSpinnerNov = new javax.swing.JSpinner();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        btnCanc1 = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jTextFieldAnoOrc = new javax.swing.JFormattedTextField();
        jCheckBoxFixo1 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        btnRegOrcServico = new javax.swing.JButton();
        jPanelDespesas = new javax.swing.JPanel();
        btnReg = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        JTextFieldNomeDespesa = new javax.swing.JTextField();
        jCheckBoxFixo = new javax.swing.JCheckBox();
        jPanel13 = new javax.swing.JPanel();
        jTextFieldJul = new javax.swing.JFormattedTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldAgo = new javax.swing.JFormattedTextField();
        jPanel14 = new javax.swing.JPanel();
        jTextFieldSet = new javax.swing.JFormattedTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldOut = new javax.swing.JFormattedTextField();
        jPanel15 = new javax.swing.JPanel();
        jTextFieldNov = new javax.swing.JFormattedTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextFieldDez = new javax.swing.JFormattedTextField();
        jLabel24 = new javax.swing.JLabel();
        btnCanc = new javax.swing.JButton();
        jTextFieldAno = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldJan = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldFev = new javax.swing.JFormattedTextField();
        jPanel11 = new javax.swing.JPanel();
        jTextFieldMarc = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldAbr = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldMaio = new javax.swing.JFormattedTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldJun = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.setMaximumSize(new java.awt.Dimension(1400, 720));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1200, 650));

        jPanelServicos.setBackground(new java.awt.Color(255, 255, 255));
        jPanelServicos.setPreferredSize(new java.awt.Dimension(1200, 650));

        jTextFieldIdServico.setEditable(false);
        jTextFieldIdServico.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldIdServico.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextFieldNomeServico.setEditable(false);
        jTextFieldNomeServico.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(34, 34, 34));
        jLabel7.setText("Nome do Serviço");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(34, 34, 34));
        jLabel8.setText("Identificador");

        jLabelAddServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-add.png"))); // NOI18N
        jLabelAddServico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddServicoMousePressed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jSpinnerJan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jSpinnerJan.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(0L), Long.valueOf(0L), Long.valueOf(1000L), Long.valueOf(1L)));
        jSpinnerJan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jSpinnerFev.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jSpinnerFev.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(0L), Long.valueOf(0L), Long.valueOf(1000L), Long.valueOf(1L)));
        jSpinnerFev.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jSpinnerMarc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jSpinnerMarc.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(0L), Long.valueOf(0L), Long.valueOf(1000L), Long.valueOf(1L)));
        jSpinnerMarc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jSpinnerAbr.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jSpinnerAbr.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(0L), Long.valueOf(0L), Long.valueOf(1000L), Long.valueOf(1L)));
        jSpinnerAbr.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jSpinnerMai.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jSpinnerMai.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(0L), Long.valueOf(0L), Long.valueOf(1000L), Long.valueOf(1L)));
        jSpinnerMai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jSpinnerJun.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jSpinnerJun.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(0L), Long.valueOf(0L), Long.valueOf(1000L), Long.valueOf(1L)));
        jSpinnerJun.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(34, 34, 34));
        jLabel9.setText("Janeiro");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(34, 34, 34));
        jLabel10.setText("Fevereiro");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(34, 34, 34));
        jLabel11.setText("Março");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(34, 34, 34));
        jLabel13.setText("Abril");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(34, 34, 34));
        jLabel25.setText("Maio");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(34, 34, 34));
        jLabel26.setText("Junho");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jSpinnerAgo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jSpinnerAgo.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(0L), Long.valueOf(0L), Long.valueOf(1000L), Long.valueOf(1L)));
        jSpinnerAgo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jSpinnerOut.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jSpinnerOut.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(0L), Long.valueOf(0L), Long.valueOf(1000L), Long.valueOf(1L)));
        jSpinnerOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jSpinnerDez.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jSpinnerDez.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(0L), Long.valueOf(0L), Long.valueOf(1000L), Long.valueOf(1L)));
        jSpinnerDez.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jSpinnerJul.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jSpinnerJul.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(0L), Long.valueOf(0L), Long.valueOf(1000L), Long.valueOf(1L)));
        jSpinnerJul.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(34, 34, 34));
        jLabel12.setText("Julho");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(34, 34, 34));
        jLabel14.setText("Setembro");

        jSpinnerSet.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jSpinnerSet.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(0L), Long.valueOf(0L), Long.valueOf(1000L), Long.valueOf(1L)));
        jSpinnerSet.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(34, 34, 34));
        jLabel27.setText("Novembro");

        jSpinnerNov.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jSpinnerNov.setModel(new javax.swing.SpinnerNumberModel(Long.valueOf(0L), Long.valueOf(0L), Long.valueOf(1000L), Long.valueOf(1L)));
        jSpinnerNov.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(34, 34, 34));
        jLabel28.setText("Agosto");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(34, 34, 34));
        jLabel29.setText("Outubro");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(34, 34, 34));
        jLabel30.setText("Dezembro");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSpinnerJul)
                        .addComponent(jSpinnerSet)
                        .addComponent(jSpinnerNov, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSpinnerDez, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                        .addComponent(jSpinnerAgo, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSpinnerOut, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerJul, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerSet, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerNov, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerAgo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerOut, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerDez, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jSpinnerMai, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                        .addComponent(jSpinnerMarc)
                        .addComponent(jSpinnerJan))
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel25))
                .addGap(57, 57, 57)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSpinnerJun, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                        .addComponent(jSpinnerFev, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSpinnerAbr, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel10)
                    .addComponent(jLabel13)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerFev, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerJan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerMarc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerMai, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerAbr, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerJun, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnCanc1.setBackground(new java.awt.Color(255, 255, 255));
        btnCanc1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnCanc1.setText("Cancelar");
        btnCanc1.setBorder(null);
        btnCanc1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCanc1.setPreferredSize(new java.awt.Dimension(150, 65));
        btnCanc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCanc1ActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(34, 34, 34));
        jLabel31.setText("Informe a quantidade do serviço selecionado que acredita realizar mensalmente");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(34, 34, 34));
        jLabel32.setText("Exercício (AAAA)");

        jTextFieldAnoOrc.setForeground(new java.awt.Color(34, 34, 34));
        try {
            jTextFieldAnoOrc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jCheckBoxFixo1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxFixo1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jCheckBoxFixo1.setForeground(new java.awt.Color(34, 34, 34));
        jCheckBoxFixo1.setText("Repete");
        jCheckBoxFixo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxFixo1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(36, 46, 65));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Novo Orçamento Previsto de Serviços");

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-financas-48.png"))); // NOI18N
        jLabel34.setText("jLabel34");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel34)
                    .addComponent(jLabel4))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        btnRegOrcServico.setBackground(new java.awt.Color(255, 255, 255));
        btnRegOrcServico.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnRegOrcServico.setText("Registrar");
        btnRegOrcServico.setBorder(null);
        btnRegOrcServico.setBorderPainted(false);
        btnRegOrcServico.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegOrcServico.setPreferredSize(new java.awt.Dimension(150, 65));
        btnRegOrcServico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRegOrcServicoMousePressed(evt);
            }
        });
        btnRegOrcServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegOrcServicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelServicosLayout = new javax.swing.GroupLayout(jPanelServicos);
        jPanelServicos.setLayout(jPanelServicosLayout);
        jPanelServicosLayout.setHorizontalGroup(
            jPanelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelServicosLayout.createSequentialGroup()
                .addGroup(jPanelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelServicosLayout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addComponent(btnRegOrcServico, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnCanc1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelServicosLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(jPanelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelServicosLayout.createSequentialGroup()
                                .addGroup(jPanelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldIdServico, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addGroup(jPanelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelServicosLayout.createSequentialGroup()
                                        .addComponent(jTextFieldNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabelAddServico))
                                    .addComponent(jLabel7))
                                .addGap(50, 50, 50)
                                .addGroup(jPanelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel32)
                                    .addGroup(jPanelServicosLayout.createSequentialGroup()
                                        .addComponent(jTextFieldAnoOrc, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(jCheckBoxFixo1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel31)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(649, Short.MAX_VALUE))
        );
        jPanelServicosLayout.setVerticalGroup(
            jPanelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelServicosLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldIdServico, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAddServico, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAnoOrc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxFixo1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegOrcServico, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCanc1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        jTabbedPane1.addTab("Serviços", jPanelServicos);

        jPanelDespesas.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDespesas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanelDespesas.setMaximumSize(new java.awt.Dimension(1400, 720));
        jPanelDespesas.setPreferredSize(new java.awt.Dimension(1200, 650));

        btnReg.setBackground(new java.awt.Color(255, 255, 255));
        btnReg.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnReg.setText("Registrar");
        btnReg.setBorder(null);
        btnReg.setBorderPainted(false);
        btnReg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReg.setPreferredSize(new java.awt.Dimension(150, 65));
        btnReg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRegMousePressed(evt);
            }
        });
        btnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.GridLayout(3, 3, 0, 5));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(34, 34, 34));
        jLabel2.setText("Nome da despesa");

        JTextFieldNomeDespesa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTextFieldNomeDespesa.setText("Ex: Conta de água");
        JTextFieldNomeDespesa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                JTextFieldNomeDespesaFocusLost(evt);
            }
        });
        JTextFieldNomeDespesa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                JTextFieldNomeDespesaKeyTyped(evt);
            }
        });

        jCheckBoxFixo.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxFixo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jCheckBoxFixo.setText("Fixo");
        jCheckBoxFixo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxFixoStateChanged(evt);
            }
        });
        jCheckBoxFixo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jCheckBoxClicado(evt);
            }
        });

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jTextFieldJul.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jTextFieldJul.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldJul.setPreferredSize(new java.awt.Dimension(120, 36));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(34, 34, 34));
        jLabel19.setText("Julho");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(34, 34, 34));
        jLabel20.setText("Agosto");

        jTextFieldAgo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jTextFieldAgo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldAgo.setPreferredSize(new java.awt.Dimension(120, 36));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jTextFieldJul, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jTextFieldAgo, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldAgo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldJul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jTextFieldSet.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jTextFieldSet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldSet.setPreferredSize(new java.awt.Dimension(120, 36));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(34, 34, 34));
        jLabel21.setText("Setembro");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(34, 34, 34));
        jLabel22.setText("Outubro");

        jTextFieldOut.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jTextFieldOut.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldOut.setPreferredSize(new java.awt.Dimension(120, 36));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jTextFieldSet, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldOut, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jTextFieldNov.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jTextFieldNov.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldNov.setPreferredSize(new java.awt.Dimension(120, 36));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(34, 34, 34));
        jLabel23.setText("Novembro");

        jTextFieldDez.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jTextFieldDez.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldDez.setPreferredSize(new java.awt.Dimension(120, 36));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(34, 34, 34));
        jLabel24.setText("Dezembro");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addContainerGap(45, Short.MAX_VALUE)
                        .addComponent(jTextFieldNov, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jTextFieldDez, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNov, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldDez, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCanc.setBackground(new java.awt.Color(255, 255, 255));
        btnCanc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnCanc.setText("Cancelar");
        btnCanc.setBorder(null);
        btnCanc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCanc.setPreferredSize(new java.awt.Dimension(150, 65));
        btnCanc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancActionPerformed(evt);
            }
        });

        jTextFieldAno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("y"))));
        jTextFieldAno.setToolTipText("Exercicio(AAAA)");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(34, 34, 34));
        jLabel3.setText("Exercício (AAAA)");

        jPanel1.setBackground(new java.awt.Color(36, 46, 65));

        jLabel1.setBackground(new java.awt.Color(34, 34, 34));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nova Despesa Prevista");

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-financas-48.png"))); // NOI18N
        jLabel33.setText("jLabel33");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel1))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(34, 34, 34));
        jLabel5.setText("Janeiro");

        jTextFieldJan.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jTextFieldJan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldJan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldJanKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldJanKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldJanKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(34, 34, 34));
        jLabel6.setText("Fevereiro");

        jTextFieldFev.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jTextFieldFev.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jTextFieldJan, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                        .addGap(45, 45, 45))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldFev, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                .addGap(66, 66, 66))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldJan, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldFev, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jTextFieldMarc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jTextFieldMarc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(34, 34, 34));
        jLabel15.setText("Março");

        jTextFieldAbr.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jTextFieldAbr.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(34, 34, 34));
        jLabel16.setText("Abril");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jTextFieldMarc, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addGap(45, 45, 45)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jTextFieldAbr, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                        .addGap(67, 67, 67))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldMarc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jTextFieldAbr, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(34, 34, 34));
        jLabel17.setText("Maio");

        jTextFieldMaio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jTextFieldMaio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldMaio.setPreferredSize(new java.awt.Dimension(120, 36));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(34, 34, 34));
        jLabel18.setText("Junho");

        jTextFieldJun.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jTextFieldJun.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldJun.setPreferredSize(new java.awt.Dimension(120, 36));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jTextFieldMaio, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                .addGap(43, 43, 43)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                        .addGap(99, 99, 99))
                    .addComponent(jTextFieldJun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(66, 66, 66))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldMaio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldJun, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelDespesasLayout = new javax.swing.GroupLayout(jPanelDespesas);
        jPanelDespesas.setLayout(jPanelDespesasLayout);
        jPanelDespesasLayout.setHorizontalGroup(
            jPanelDespesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelDespesasLayout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addComponent(btnReg, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnCanc, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelDespesasLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanelDespesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDespesasLayout.createSequentialGroup()
                        .addGroup(jPanelDespesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelDespesasLayout.createSequentialGroup()
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelDespesasLayout.createSequentialGroup()
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelDespesasLayout.createSequentialGroup()
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelDespesasLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanelDespesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(JTextFieldNomeDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanelDespesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanelDespesasLayout.createSequentialGroup()
                                .addComponent(jTextFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addComponent(jCheckBoxFixo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanelDespesasLayout.setVerticalGroup(
            jPanelDespesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDespesasLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelDespesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDespesasLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTextFieldNomeDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDespesasLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelDespesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldAno, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxFixo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanelDespesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDespesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDespesasLayout.createSequentialGroup()
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelDespesasLayout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanelDespesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanelDespesasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnReg, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(btnCanc, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Despesas", jPanelDespesas);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void limparJTextFields() {
        JTextFieldNomeDespesa.setText("");
        jTextFieldAno.setText("");
        jTextFieldJan.setText("");
        jTextFieldFev.setText("");
        jTextFieldMarc.setText("");
        jTextFieldAbr.setText("");
        jTextFieldMaio.setText("");
        jTextFieldJun.setText("");
        jTextFieldJul.setText("");
        jTextFieldAgo.setText("");
        jTextFieldSet.setText("");
        jTextFieldOut.setText("");
        jTextFieldNov.setText("");
        jTextFieldDez.setText("");
    }

    private void copiarValor() {
        if (jCheckBoxFixo.isSelected() == true) {
            String valor = jTextFieldJan.getText();
            jTextFieldFev.setText(valor);
            jTextFieldMarc.setText(valor);
            jTextFieldAbr.setText(valor);
            jTextFieldMaio.setText(valor);
            jTextFieldJun.setText(valor);
            jTextFieldJul.setText(valor);
            jTextFieldAgo.setText(valor);
            jTextFieldSet.setText(valor);
            jTextFieldOut.setText(valor);
            jTextFieldNov.setText(valor);
            jTextFieldDez.setText(valor);

        }
    }
    private void jLabelAddServicoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddServicoMousePressed
        modalServicoUnico modal = new modalServicoUnico();
        modal.registrarObservador(this);
        modal.setVisible(true);
    }//GEN-LAST:event_jLabelAddServicoMousePressed

    private void btnRegOrcServicoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegOrcServicoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegOrcServicoMousePressed

    private void btnRegOrcServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegOrcServicoActionPerformed

        boolean sucesso = false;

        try {

            OrcamentoController oc = new OrcamentoController();
            sucesso = oc.cadastraOrcamentoServico(true,
                    jTextFieldNomeServico.getText(),
                    Long.parseLong(jTextFieldIdServico.getText()),
                    Long.parseLong(jSpinnerJan.getValue().toString()),
                    Long.parseLong(jSpinnerFev.getValue().toString()),
                    Long.parseLong(jSpinnerMarc.getValue().toString()),
                    Long.parseLong(jSpinnerAbr.getValue().toString()),
                    Long.parseLong(jSpinnerMai.getValue().toString()),
                    Long.parseLong(jSpinnerJun.getValue().toString()),
                    Long.parseLong(jSpinnerJul.getValue().toString()),
                    Long.parseLong(jSpinnerAgo.getValue().toString()),
                    Long.parseLong(jSpinnerSet.getValue().toString()),
                    Long.parseLong(jSpinnerOut.getValue().toString()),
                    Long.parseLong(jSpinnerNov.getValue().toString()),
                    Long.parseLong(jSpinnerDez.getValue().toString()),
                    jTextFieldAnoOrc.getText());

            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso.");
                limparSpinners();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao registrar orçamento. Verifique os dados informados.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro no registro: " + e);
        }

    }//GEN-LAST:event_btnRegOrcServicoActionPerformed

    private void limparSpinners() {

        long valorNominal = 0;
        jSpinnerJan.setValue(valorNominal);
        jSpinnerFev.setValue(valorNominal);
        jSpinnerMarc.setValue(valorNominal);
        jSpinnerAbr.setValue(valorNominal);
        jSpinnerMai.setValue(valorNominal);
        jSpinnerJun.setValue(valorNominal);
        jSpinnerJul.setValue(valorNominal);
        jSpinnerAgo.setValue(valorNominal);
        jSpinnerSet.setValue(valorNominal);
        jSpinnerOut.setValue(valorNominal);
        jSpinnerNov.setValue(valorNominal);
        jSpinnerDez.setValue(valorNominal);
    }
    private void btnCanc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCanc1ActionPerformed

        int opc = JOptionPane.showConfirmDialog(null, "Realmente deseja sair?", "Cadastro Orçamento Previsto", JOptionPane.YES_NO_OPTION);

        if (opc == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_btnCanc1ActionPerformed

    private void jCheckBoxFixo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxFixo1ActionPerformed

        long valorRepete = Long.parseLong(jSpinnerJan.getValue().toString());
        long valorNominal = 0;
        if (jCheckBoxFixo1.isSelected()) {
            jSpinnerFev.setValue(valorRepete);
            jSpinnerMarc.setValue(valorRepete);
            jSpinnerAbr.setValue(valorRepete);
            jSpinnerMai.setValue(valorRepete);
            jSpinnerJun.setValue(valorRepete);
            jSpinnerJul.setValue(valorRepete);
            jSpinnerAgo.setValue(valorRepete);
            jSpinnerSet.setValue(valorRepete);
            jSpinnerOut.setValue(valorRepete);
            jSpinnerNov.setValue(valorRepete);
            jSpinnerDez.setValue(valorRepete);
        } else {
            jSpinnerFev.setValue(valorNominal);
            jSpinnerMarc.setValue(valorNominal);
            jSpinnerAbr.setValue(valorNominal);
            jSpinnerMai.setValue(valorNominal);
            jSpinnerJun.setValue(valorNominal);
            jSpinnerJul.setValue(valorNominal);
            jSpinnerAgo.setValue(valorNominal);
            jSpinnerSet.setValue(valorNominal);
            jSpinnerOut.setValue(valorNominal);
            jSpinnerNov.setValue(valorNominal);
            jSpinnerDez.setValue(valorNominal);
        }


    }//GEN-LAST:event_jCheckBoxFixo1ActionPerformed

    private void jTextFieldJanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldJanKeyTyped
        //copiarValor();
    }//GEN-LAST:event_jTextFieldJanKeyTyped

    private void jTextFieldJanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldJanKeyReleased
        copiarValor();
    }//GEN-LAST:event_jTextFieldJanKeyReleased

    private void jTextFieldJanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldJanKeyPressed
        //copiarValor();
    }//GEN-LAST:event_jTextFieldJanKeyPressed

    private void btnCancActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancActionPerformed
        int opc = JOptionPane.showConfirmDialog(null, "Realmente deseja sair?", "Cadastro Orçamento Previsto", JOptionPane.YES_NO_OPTION);

        if (opc == 0) {
            this.dispose();
        }
    }//GEN-LAST:event_btnCancActionPerformed

    private void jCheckBoxClicado(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxClicado

    }//GEN-LAST:event_jCheckBoxClicado

    private void jCheckBoxFixoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxFixoStateChanged
        copiarValor();
    }//GEN-LAST:event_jCheckBoxFixoStateChanged

    private void JTextFieldNomeDespesaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTextFieldNomeDespesaKeyTyped

        if (JTextFieldNomeDespesa.getText().equals("Ex: Conta de água")) {
            JTextFieldNomeDespesa.setText("");
        }
    }//GEN-LAST:event_JTextFieldNomeDespesaKeyTyped

    private void JTextFieldNomeDespesaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_JTextFieldNomeDespesaFocusLost
        copiarValor();
    }//GEN-LAST:event_JTextFieldNomeDespesaFocusLost

    private void btnRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegActionPerformed

    private void btnRegMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegMousePressed

        boolean sucessoAoCadastrar;

        try {
            OrcamentoController co = new OrcamentoController();

            sucessoAoCadastrar = co.CadastrarOrcamento(true,
                    JTextFieldNomeDespesa.getText(),
                    Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldJan.getText())),
                    Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldFev.getText())),
                    Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldMarc.getText())),
                    Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldAbr.getText())),
                    Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldMaio.getText())),
                    Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldJun.getText())),
                    Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldJul.getText())),
                    Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldAgo.getText())),
                    Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldSet.getText())),
                    Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldOut.getText())),
                    Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldNov.getText())),
                    Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldDez.getText())),
                    jTextFieldAno.getText());

            if (sucessoAoCadastrar) {
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso");
                limparJTextFields();
                jCheckBoxFixo.setSelected(false);

            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar orçamento. Preencha todos os campos!");
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }//GEN-LAST:event_btnRegMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CadastroOrcamentoPrevisto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroOrcamentoPrevisto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroOrcamentoPrevisto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroOrcamentoPrevisto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroOrcamentoPrevisto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JTextFieldNomeDespesa;
    private javax.swing.JButton btnCanc;
    private javax.swing.JButton btnCanc1;
    private javax.swing.JButton btnReg;
    private javax.swing.JButton btnRegOrcServico;
    private javax.swing.JCheckBox jCheckBoxFixo;
    private javax.swing.JCheckBox jCheckBoxFixo1;
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
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAddServico;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelDespesas;
    private javax.swing.JPanel jPanelServicos;
    private javax.swing.JSpinner jSpinnerAbr;
    private javax.swing.JSpinner jSpinnerAgo;
    private javax.swing.JSpinner jSpinnerDez;
    private javax.swing.JSpinner jSpinnerFev;
    private javax.swing.JSpinner jSpinnerJan;
    private javax.swing.JSpinner jSpinnerJul;
    private javax.swing.JSpinner jSpinnerJun;
    private javax.swing.JSpinner jSpinnerMai;
    private javax.swing.JSpinner jSpinnerMarc;
    private javax.swing.JSpinner jSpinnerNov;
    private javax.swing.JSpinner jSpinnerOut;
    private javax.swing.JSpinner jSpinnerSet;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JFormattedTextField jTextFieldAbr;
    private javax.swing.JFormattedTextField jTextFieldAgo;
    private javax.swing.JFormattedTextField jTextFieldAno;
    private javax.swing.JFormattedTextField jTextFieldAnoOrc;
    private javax.swing.JFormattedTextField jTextFieldDez;
    private javax.swing.JFormattedTextField jTextFieldFev;
    private javax.swing.JTextField jTextFieldIdServico;
    private javax.swing.JFormattedTextField jTextFieldJan;
    private javax.swing.JFormattedTextField jTextFieldJul;
    private javax.swing.JFormattedTextField jTextFieldJun;
    private javax.swing.JFormattedTextField jTextFieldMaio;
    private javax.swing.JFormattedTextField jTextFieldMarc;
    private javax.swing.JTextField jTextFieldNomeServico;
    private javax.swing.JFormattedTextField jTextFieldNov;
    private javax.swing.JFormattedTextField jTextFieldOut;
    private javax.swing.JFormattedTextField jTextFieldSet;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Object obj) {
    }

    @Override
    public void update(DefaultTableModel model) {
    }

    @Override
    public void update(String valorDesconto) {
    }

    @Override
    public void update(Cliente cliente) {
    }

    @Override
    public void update(Servico servico) {

        String nome = null;
        String id = null;
        try {
            nome = servico.getNome();
            id = String.valueOf(servico.getId());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        jTextFieldIdServico.setText(id);
        jTextFieldNomeServico.setText(nome);
    }

    @Override
    public void update(ArrayList<LocalTime> horarios) {
    }

    @Override
    public void update(Orcamento orcamento) {

    }
}
