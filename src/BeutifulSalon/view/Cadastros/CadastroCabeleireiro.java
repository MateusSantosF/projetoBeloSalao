/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.view.Cadastros;

import BeutifulSalon.Ferramentas.GraficoDeBarras;
import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.Ferramentas.ManipulaFontes;
import BeutifulSalon.Ferramentas.ManipuladorArquivo;
import BeutifulSalon.controller.CabeleireiroController;
import BeutifulSalon.controller.ColaboradorController;
import BeutifulSalon.controller.RelatorioController;
import BeutifulSalon.dao.CabeleireiroDAO;
import BeutifulSalon.dao.ColaboradorDAO;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Cabeleireiro;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Colaborador;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Email;
import BeutifulSalon.model.Observado;
import BeutifulSalon.model.Observador;
import BeutifulSalon.model.Orcamento;
import BeutifulSalon.model.Servico;
import BeutifulSalon.view.modais.ModalColaboradores;
import BeutifulSalon.view.modais.ModalExpediente;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author mateus
 */
public class CadastroCabeleireiro extends javax.swing.JFrame implements Observador, Observado {

    /**
     * Creates new form CadastroCabeleireiro
     */
    private ArrayList<LocalTime> expediente = null;
    private ModalExpediente modalExpediente = null;
    private ModalColaboradores modalColaaboradores = null;
    private String caminhoArquivo;
    private String caminhoArquivoUltimaVisita;
    private Email emailUltimaVisita = new Email();
    private Email email = new Email();
    private JFrame mainMenu;
    private int mesSelecionado = 1;

    Observador observador = null;

    public CadastroCabeleireiro() {
        initComponents();

        this.mainMenu = mainMenu;
        ManipulaFontes mf = new ManipulaFontes();;

        //Fontes
        jLabel4.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 40f)); //Informe seus dados pessoais
        jLabel1.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Primeiro Nome 
        jLabel3.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //CPF
        jLabel2.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //E-mail
        jLabel12.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Senha E-mail
        // jLabel21.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); // meta de lucro

        jLabel5.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Configurar Expediente
        jButton1.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 13f)); //Abrir grade 

        jButtonCadastrar.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Confirmar

        //Fontes email aniversario
        jLabel6.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 25f));
        jCheckBoxAniversario.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jLabel7.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jTextAreaAniversario.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jLabel10.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jLabelNomeArquivoAniversario.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jLabel11.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jLabel8.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jLabel9.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));

        //Fontes email visita
        jLabel14.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 25f));
        jComboBoxPeriodo.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jCheckBoxUltimaVisita.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jLabel15.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jTextFieldTituloUltimaVisita.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jTextAreaUltimaVisita.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jLabel18.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jLabelNomeArquivoUltimaVisita.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jLabel19.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jLabel16.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jLabel17.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));

        //Configurações
        jLabel24.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 25f));
        jCheckBoxVerificaHorarios.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f));
        jLabel23.setFont(mf.getFont(mf.LIGHT, Font.PLAIN, 15f));
        jLabel25.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 20f));
        jLabel30.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 20f));
        jLabel26.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f));
        jButton2.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f));
        jButtonCadastrar1.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Confirmar

        //Relatorios
        jLabel27.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 25f));
        jLabel28.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f));
        jLabel29.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f));
        jFormattedTextFieldDataInicio.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 20f));
        jFormattedTextFieldDataFim.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 20f));

        jRadioButtonRelatorioVenda.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jRadioButtonAgendamentos.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jRadioButtonDespesas.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jRadioButtonRelatorioVenda1.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));

        jButton3.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f));

        // ===================================================//
        DecimalFormat decimal = new DecimalFormat("#,###,###.00");
        NumberFormatter numFormatter = new NumberFormatter(decimal);
        numFormatter.setFormat(decimal);
        numFormatter.setAllowsInvalid(false);
        DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(numFormatter);
        //  jFormattedTextFieldPreco.setFormatterFactory(dfFactory);

        CabeleireiroController cc = new CabeleireiroController();

        if (cc.verificaRegistro() == 1) {
            Cabeleireiro cabeleireiro = cc.selecionaCabeleireiro();

            jTextFieldNome.setText(cabeleireiro.getNome());
            jFormattedTextFieldCPF.setText(cabeleireiro.getCpf());
            jTextFieldEmail.setText(cabeleireiro.getEmail());

            jTextFieldTituloAniversario.setText(cabeleireiro.getEmailAniversario().getTitulo());
            jTextFieldTituloUltimaVisita.setText(cabeleireiro.getEmailUltimaVisita().getTitulo());

            jComboBoxPeriodo.setSelectedIndex(cabeleireiro.getEmailUltimaVisita().getPeriodoReenvio() - 2);

            if (cabeleireiro.getEmailAniversario().getTexto().length() < 2) {
                jTextAreaAniversario.setText("Feliz aniversário <nome>,");
                jTextAreaUltimaVisita.setText("Caro <nome>,");
            } else {
                jTextAreaAniversario.setText(cabeleireiro.getEmailAniversario().getTexto().replaceAll("<br>", "\n"));
                jTextAreaUltimaVisita.setText(cabeleireiro.getEmailUltimaVisita().getTexto().replaceAll("<br>", "\n"));
            }

            if (cabeleireiro.getEmailUltimaVisita().getTexto().length() < 2) {
                jTextAreaUltimaVisita.setText("Caro <nome>,");

            } else {
                jTextAreaUltimaVisita.setText(cabeleireiro.getEmailUltimaVisita().getTexto().replaceAll("<br>", "\n"));
            }

            jCheckBoxUltimaVisita.setSelected(cabeleireiro.getEmailUltimaVisita().isEnviar());
            jCheckBoxAniversario.setSelected(cabeleireiro.getEmailAniversario().isEnviar());
            jLabelNomeArquivoAniversario.setText(cabeleireiro.getEmailAniversario().getNomeDoArquivo());
            // jFormattedTextFieldPreco.setValue(Dinheiro.parseDecimal(cabeleireiro.getMetaDeLucro()));
            jPasswordField.setText(cabeleireiro.getSenha());
            jCheckBoxVerificaHorarios.setSelected(cabeleireiro.isVerificarHorariosDisponiveis());
            jSliderTempoEntreAgendamentos.setValue(cabeleireiro.getTempoEntreHorariosLivres());
            jSliderTamanhoFonte.setValue((int) new ManipuladorArquivo().getTamanhoFonte());

            ManipulaData md = new ManipulaData();

            LocalDate hoje = LocalDate.now();
            md.meses(hoje).forEach(mes -> {
                jComboBoxMeses.addItem(mes.getDisplayName(TextStyle.FULL, new Locale("pt", "BR")));
            });

            jComboBoxMeses.setSelectedIndex(hoje.getMonthValue() - 1);

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

        jFileChooser = new javax.swing.JFileChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldEmail = new javax.swing.JTextField();
        jFormattedTextFieldCPF = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jButtonCadastrar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPasswordField = new javax.swing.JPasswordField();
        jLabel12 = new javax.swing.JLabel();
        jPanelEmails = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jCheckBoxAniversario = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldTituloAniversario = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaAniversario = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabelNomeArquivoAniversario = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jCheckBoxUltimaVisita = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldTituloUltimaVisita = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaUltimaVisita = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabelNomeArquivoUltimaVisita = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jComboBoxPeriodo = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jCheckBoxVerificaHorarios = new javax.swing.JCheckBox();
        jLabel23 = new javax.swing.JLabel();
        jButtonCadastrar1 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jSliderTempoEntreAgendamentos = new javax.swing.JSlider();
        jLabel26 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jSliderTamanhoFonte = new javax.swing.JSlider();
        jLabel30 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jFormattedTextFieldDataInicio = new javax.swing.JFormattedTextField();
        jFormattedTextFieldDataFim = new javax.swing.JFormattedTextField();
        jButton3 = new javax.swing.JButton();
        jRadioButtonRelatorioVenda = new javax.swing.JRadioButton();
        jRadioButtonAgendamentos = new javax.swing.JRadioButton();
        jRadioButtonDespesas = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jRadioButtonRelatorioVenda1 = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanelGraficoColab = new javax.swing.JPanel();
        jComboBoxMeses = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Primeiro Nome");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("CPF");

        jTextFieldNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextFieldEmail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmailActionPerformed(evt);
            }
        });

        jFormattedTextFieldCPF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(34, 34, 34)));
        try {
            jFormattedTextFieldCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldCPF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextFieldCPF.setText("");
        jFormattedTextFieldCPF.setToolTipText("Informe o CPF");
        jFormattedTextFieldCPF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("E-mail");

        jButtonCadastrar.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCadastrar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonCadastrar.setText("Confirmar");
        jButtonCadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Configurar Expediente");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Abrir grade ");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(36, 46, 65));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Informe seus dados Pessoais");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel4)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        jPasswordField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Senha do E-mail");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(389, 389, 389))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(48, 48, 48)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jFormattedTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(49, 49, 49))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addComponent(jLabel2))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(281, 281, 281))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Perfil", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jCheckBoxAniversario.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxAniversario.setText("Enviar automáticamente");
        jCheckBoxAniversario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAniversarioActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Título");

        jTextFieldTituloAniversario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextAreaAniversario.setColumns(20);
        jTextAreaAniversario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextAreaAniversario.setRows(5);
        jTextAreaAniversario.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextAreaAniversarioCaretUpdate(evt);
            }
        });
        jTextAreaAniversario.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jTextAreaAniversarioCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jTextAreaAniversario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextAreaAniversarioKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTextAreaAniversario);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-anexo.png"))); // NOI18N
        jLabel8.setText("Inserir Anexo");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-excluir.png"))); // NOI18N
        jLabel9.setText("Excluir Anexo");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel9MousePressed(evt);
            }
        });

        jLabelNomeArquivoAniversario.setText("Não existem arquivos anexados.");

        jLabel10.setText("Arquivo Anexado:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Salvar");
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel11MousePressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/dica.png"))); // NOI18N
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel13MousePressed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(36, 46, 65));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Configure o e-mail padrão de aniversário");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelNomeArquivoAniversario))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxAniversario)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextFieldTituloAniversario, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxAniversario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTituloAniversario, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNomeArquivoAniversario))
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(58, 58, 58))
        );

        jTabbedPane2.addTab("Aniversário", jPanel3);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jCheckBoxUltimaVisita.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxUltimaVisita.setText("Enviar automáticamente");
        jCheckBoxUltimaVisita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxUltimaVisitaActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Título");

        jTextAreaUltimaVisita.setColumns(20);
        jTextAreaUltimaVisita.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextAreaUltimaVisita.setRows(5);
        jTextAreaUltimaVisita.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextAreaUltimaVisitaCaretUpdate(evt);
            }
        });
        jTextAreaUltimaVisita.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                jTextAreaUltimaVisitaCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jTextAreaUltimaVisita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextAreaUltimaVisitaKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTextAreaUltimaVisita);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-anexo.png"))); // NOI18N
        jLabel16.setText("Inserir Anexo");
        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel16MousePressed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-excluir.png"))); // NOI18N
        jLabel17.setText("Excluir Anexo");
        jLabel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel17MousePressed(evt);
            }
        });

        jLabelNomeArquivoUltimaVisita.setText("Não existem arquivos anexados.");

        jLabel18.setText("Arquivo Anexado:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Salvar");
        jLabel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel19MousePressed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/dica.png"))); // NOI18N
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel20MousePressed(evt);
            }
        });

        jComboBoxPeriodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2 Meses", "3 Meses", "4 Meses", "5 meses", "6 meses", "7 Meses", "8 Meses", "9 Meses", "10 Meses" }));

        jPanel12.setBackground(new java.awt.Color(36, 46, 65));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Configure o e-mail padrão de última visita");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelNomeArquivoUltimaVisita))
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTextFieldTituloUltimaVisita, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addComponent(jLabel20))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jComboBoxPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBoxUltimaVisita)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxUltimaVisita)
                    .addComponent(jComboBoxPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTituloUltimaVisita, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNomeArquivoUltimaVisita))
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(63, 63, 63))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 727, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 558, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane2.addTab("Última Visita", jPanel4);

        javax.swing.GroupLayout jPanelEmailsLayout = new javax.swing.GroupLayout(jPanelEmails);
        jPanelEmails.setLayout(jPanelEmailsLayout);
        jPanelEmailsLayout.setHorizontalGroup(
            jPanelEmailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanelEmailsLayout.setVerticalGroup(
            jPanelEmailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 578, Short.MAX_VALUE)
        );

        jTabbedPane2.getAccessibleContext().setAccessibleName("");

        jTabbedPane1.addTab("Emails Automáticos", jPanelEmails);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jCheckBoxVerificaHorarios.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxVerificaHorarios.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBoxVerificaHorarios.setForeground(new java.awt.Color(0, 0, 0));
        jCheckBoxVerificaHorarios.setText("Desativar verificação de Horário Disponível em Agendamentos*");
        jCheckBoxVerificaHorarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxVerificaHorariosActionPerformed(evt);
            }
        });

        jLabel23.setText("*Ativar esta opção pode bagunçar a grade de horários disponíveis em até 3 dias em diante.");

        jButtonCadastrar1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCadastrar1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonCadastrar1.setText("Confirmar");
        jButtonCadastrar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonCadastrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrar1ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Tempo Mínimo Permitido entre Horários disponíveis (Em Minutos)");

        jSliderTempoEntreAgendamentos.setBackground(new java.awt.Color(255, 255, 255));
        jSliderTempoEntreAgendamentos.setForeground(new java.awt.Color(0, 0, 0));
        jSliderTempoEntreAgendamentos.setMajorTickSpacing(5);
        jSliderTempoEntreAgendamentos.setMaximum(55);
        jSliderTempoEntreAgendamentos.setMinimum(5);
        jSliderTempoEntreAgendamentos.setPaintLabels(true);
        jSliderTempoEntreAgendamentos.setPaintTicks(true);
        jSliderTempoEntreAgendamentos.setValue(5);

        jLabel26.setBackground(new java.awt.Color(204, 0, 0));
        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(204, 0, 0));
        jLabel26.setText("Resetar Banco de Dados");

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Resetar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jSliderTamanhoFonte.setBackground(new java.awt.Color(255, 255, 255));
        jSliderTamanhoFonte.setForeground(new java.awt.Color(0, 0, 0));
        jSliderTamanhoFonte.setMajorTickSpacing(1);
        jSliderTamanhoFonte.setMaximum(10);
        jSliderTamanhoFonte.setMinimum(-10);
        jSliderTamanhoFonte.setMinorTickSpacing(1);
        jSliderTamanhoFonte.setPaintLabels(true);
        jSliderTamanhoFonte.setPaintTicks(true);
        jSliderTamanhoFonte.setValue(0);

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Tamanho da Fonte");

        jPanel10.setBackground(new java.awt.Color(36, 46, 65));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Configurações do Sistema");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel24)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel24)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCadastrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(261, 261, 261))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jSliderTamanhoFonte, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel25)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxVerificaHorarios)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22))
                    .addComponent(jSliderTempoEntreAgendamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 587, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jCheckBoxVerificaHorarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSliderTempoEntreAgendamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30)
                .addGap(10, 10, 10)
                .addComponent(jSliderTamanhoFonte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButtonCadastrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        jTabbedPane1.addTab("Configurações", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setText("Início");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setText("Fim");

        jFormattedTextFieldDataInicio.setBackground(new java.awt.Color(255, 255, 255));
        jFormattedTextFieldDataInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jFormattedTextFieldDataInicio.setForeground(new java.awt.Color(0, 0, 0));
        try {
            jFormattedTextFieldDataInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldDataInicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextFieldDataInicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jFormattedTextFieldDataFim.setBackground(new java.awt.Color(255, 255, 255));
        jFormattedTextFieldDataFim.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jFormattedTextFieldDataFim.setForeground(new java.awt.Color(0, 0, 0));
        try {
            jFormattedTextFieldDataFim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldDataFim.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jFormattedTextFieldDataFim.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("Gerar Relatório");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jRadioButtonRelatorioVenda.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButtonRelatorioVenda);
        jRadioButtonRelatorioVenda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButtonRelatorioVenda.setForeground(new java.awt.Color(34, 34, 3));
        jRadioButtonRelatorioVenda.setText("Relatório de Vendas");
        jRadioButtonRelatorioVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonRelatorioVendaActionPerformed(evt);
            }
        });

        jRadioButtonAgendamentos.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButtonAgendamentos);
        jRadioButtonAgendamentos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButtonAgendamentos.setForeground(new java.awt.Color(34, 34, 3));
        jRadioButtonAgendamentos.setText("Relatório de Agendamentos");

        jRadioButtonDespesas.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButtonDespesas);
        jRadioButtonDespesas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButtonDespesas.setForeground(new java.awt.Color(34, 34, 3));
        jRadioButtonDespesas.setText("Relatório de Despesas");

        jPanel9.setBackground(new java.awt.Color(36, 46, 65));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Insira o Período que deseja gerar o Relatório:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel27)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel27)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jRadioButtonRelatorioVenda1.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(jRadioButtonRelatorioVenda1);
        jRadioButtonRelatorioVenda1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRadioButtonRelatorioVenda1.setForeground(new java.awt.Color(34, 34, 3));
        jRadioButtonRelatorioVenda1.setText("Relatório de Compras");
        jRadioButtonRelatorioVenda1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonRelatorioVenda1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(241, 241, 241))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(jFormattedTextFieldDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addComponent(jFormattedTextFieldDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButtonRelatorioVenda)
                                    .addComponent(jRadioButtonRelatorioVenda1))
                                .addGap(212, 212, 212)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButtonAgendamentos)
                                    .addComponent(jRadioButtonDespesas))))
                        .addGap(74, 74, 74))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jFormattedTextFieldDataFim)
                    .addComponent(jFormattedTextFieldDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonRelatorioVenda)
                    .addComponent(jRadioButtonDespesas))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonAgendamentos)
                    .addComponent(jRadioButtonRelatorioVenda1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );

        jTabbedPane1.addTab("Relatórios", jPanel7);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel13.setBackground(new java.awt.Color(36, 46, 65));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 729, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Colaboradores");

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-eye.png"))); // NOI18N
        jLabel31.setToolTipText("");
        jLabel31.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel31MousePressed(evt);
            }
        });

        jPanelGraficoColab.setLayout(new java.awt.BorderLayout());

        jComboBoxMeses.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBoxMeses.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxMesesItemStateChanged(evt);
            }
        });
        jComboBoxMeses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMesesActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("Mes Referente");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelGraficoColab, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelGraficoColab, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Colaboradores", jPanel8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmailActionPerformed

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed

        boolean sucesso;
        CabeleireiroController cc = new CabeleireiroController();
        int existe = cc.verificaRegistro();
        try {

            //se for != de 1 não existe um cabeleireiro cadastrado
            if (existe != 1) {
                sucesso = cc.cadastrarCabeleireiro(jTextFieldNome.getText(),
                        jFormattedTextFieldCPF.getText(),
                        jTextFieldEmail.getText(),
                        this.expediente,
                        jPasswordField.getPassword(),
                        "0");

                Colaborador c = new Colaborador();
                Cabeleireiro cabeleireiro = cc.selecionaCabeleireiro();
                c.setNome(cabeleireiro.getNome());
                c.setIdColaborador(cabeleireiro.getId());
                c.setComissionado(false);
                c.setPorcentagemComisao(0L);
                c.cadastrarColaboradorCabeleireiro(c);

                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Registro realizado com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro! verifique as informações e tente novamente");
                }
            } else {
                Cabeleireiro c = null;
                c = cc.selecionaCabeleireiro();

                if (expediente == null) {

                    expediente = new ArrayList<>();
                    expediente.add(c.getSegundaE());
                    expediente.add(c.getSegundaS());

                    expediente.add(c.getTercaE());
                    expediente.add(c.getTercaS());

                    expediente.add(c.getQuartaE());
                    expediente.add(c.getQuartaS());

                    expediente.add(c.getQuintaE());
                    expediente.add(c.getQuintaS());

                    expediente.add(c.getSextaE());
                    expediente.add(c.getSextaS());

                    expediente.add(c.getSabadoE());
                    expediente.add(c.getSabadoS());

                    expediente.add(c.getDomingoE());
                    expediente.add(c.getDomingoS());
                }

                sucesso = cc.atualizarCabeleireiro(jTextFieldNome.getText(),
                        jFormattedTextFieldCPF.getText(),
                        jTextFieldEmail.getText(),
                        this.expediente,
                        jPasswordField.getPassword(),
                        "0");

                new ColaboradorDAO().atualizarColaboradorCabeleireiro(jTextFieldNome.getText(), c.getId());

                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Atualização realizada com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro! verifique as informações e tente novamente");
                }

            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro" + e);
        }
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (modalExpediente == null) {
            modalExpediente = new ModalExpediente();
            modalExpediente.registrarObservador(this);
            modalExpediente.setVisible(true);
        } else {
            modalExpediente.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBoxAniversarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAniversarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxAniversarioActionPerformed

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        FileFilter filter = new FileNameExtensionFilter("Imagem ou Zip", "jpg", "jpeg", "png", "pdf", ".zip", ".7z");
        jFileChooser.setFileFilter(filter);
        jFileChooser.addChoosableFileFilter(filter);

        int returnVal = jFileChooser.showOpenDialog(this);

        if (returnVal == jFileChooser.APPROVE_OPTION) {
            caminhoArquivo = jFileChooser.getSelectedFile().getAbsolutePath();

            email.setDiretorioArquivo(caminhoArquivo);
            jLabelNomeArquivoAniversario.setText(email.getNomeDoArquivo());
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma imagem e tente novamente");
            jLabelNomeArquivoAniversario.setText("Não existem arquivos anexados.");
        }
    }//GEN-LAST:event_jLabel8MousePressed

    private void jLabel9MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MousePressed
        int opc = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o arquivo ?");

        if (opc == 0) {
            caminhoArquivo = null;
            jLabelNomeArquivoAniversario.setText("Não existem arquivos anexados.");
        }
    }//GEN-LAST:event_jLabel9MousePressed

    private void jLabel11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MousePressed

        CabeleireiroController cc = new CabeleireiroController();
        email.setTitulo(jTextFieldTituloAniversario.getText());
        email.setTexto(jTextAreaAniversario.getText());
        email.setEnviar(jCheckBoxAniversario.isSelected());
        if (cc.verificaRegistro() == 1) {
            if (email.getDiretorioArquivo() != null) {

                try {
                    File file = new File(email.getDiretorioArquivo());
                    byte[] imagem = new byte[(int) file.length()];
                    DataInputStream dis;
                    dis = new DataInputStream(new FileInputStream(file));
                    dis.readFully(imagem);
                    dis.close();

                    email.setAnexo(imagem);

                    email.setDiretorioArquivo(caminhoArquivo);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao ler anexo" + e);
                }

            }
            boolean sucesso = cc.cadastrarEmailPadraoAniversario(email, cc.selecionaCabeleireiro().getCpf());

            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Email padrão de aniversário atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar email de aniversário.");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Você não pode cadastrar um email, pois ainda não se cadastrou no sistema.");
        }


    }//GEN-LAST:event_jLabel11MousePressed

    private void jTextAreaAniversarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaAniversarioKeyPressed


    }//GEN-LAST:event_jTextAreaAniversarioKeyPressed

    private void jTextAreaAniversarioCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextAreaAniversarioCaretUpdate


    }//GEN-LAST:event_jTextAreaAniversarioCaretUpdate

    private void jTextAreaAniversarioCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTextAreaAniversarioCaretPositionChanged

    }//GEN-LAST:event_jTextAreaAniversarioCaretPositionChanged

    private void jLabel13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MousePressed
        JOptionPane.showMessageDialog(null, "Digite '<nome>' ao longo do texto ou do título, para inserir o \n"
                + "nome do cliente para qual o email será enviado\n\n"
                + "Ex: Feliz aniversário <nome>!,\nA equipe do Salão deseja[...]");
    }//GEN-LAST:event_jLabel13MousePressed

    private void jCheckBoxUltimaVisitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxUltimaVisitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxUltimaVisitaActionPerformed

    private void jTextAreaUltimaVisitaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextAreaUltimaVisitaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextAreaUltimaVisitaCaretUpdate

    private void jTextAreaUltimaVisitaCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTextAreaUltimaVisitaCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextAreaUltimaVisitaCaretPositionChanged

    private void jTextAreaUltimaVisitaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextAreaUltimaVisitaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextAreaUltimaVisitaKeyPressed

    private void jLabel16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MousePressed
        FileFilter filter = new FileNameExtensionFilter("Imagem ou Zip", "jpg", "jpeg", "png", "pdf", ".zip", ".7z");
        jFileChooser.setFileFilter(filter);
        jFileChooser.addChoosableFileFilter(filter);

        int returnVal = jFileChooser.showOpenDialog(this);

        if (returnVal == jFileChooser.APPROVE_OPTION) {
            caminhoArquivoUltimaVisita = jFileChooser.getSelectedFile().getAbsolutePath();

            emailUltimaVisita.setDiretorioArquivo(caminhoArquivoUltimaVisita);
            jLabelNomeArquivoUltimaVisita.setText(emailUltimaVisita.getNomeDoArquivo());
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma imagem e tente novamente");
            jLabelNomeArquivoUltimaVisita.setText("Não existem arquivos anexados.");
        }
    }//GEN-LAST:event_jLabel16MousePressed

    private void jLabel17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel17MousePressed

    private void jLabel19MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MousePressed

        CabeleireiroController cc = new CabeleireiroController();
        emailUltimaVisita.setTitulo(jTextFieldTituloUltimaVisita.getText());
        emailUltimaVisita.setTexto(jTextAreaUltimaVisita.getText());
        emailUltimaVisita.setEnviar(jCheckBoxUltimaVisita.isSelected());

        if (cc.verificaRegistro() == 1) {
            if (emailUltimaVisita.getDiretorioArquivo() != null) {

                try {
                    File file = new File(emailUltimaVisita.getDiretorioArquivo());
                    byte[] imagem = new byte[(int) file.length()];
                    DataInputStream dis;
                    dis = new DataInputStream(new FileInputStream(file));
                    dis.readFully(imagem);
                    dis.close();

                    emailUltimaVisita.setAnexo(imagem);

                    emailUltimaVisita.setDiretorioArquivo(caminhoArquivoUltimaVisita);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao ler anexo" + e);
                }

            }
            boolean sucesso = cc.cadastrarEmailUltimaVisita(emailUltimaVisita, cc.selecionaCabeleireiro().getCpf(),
                    jComboBoxPeriodo.getSelectedIndex() + 2);

            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Email padrão de última visita atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar email de última visita.");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Você não pode cadastrar um email, pois ainda não se cadastrou no sistema.");
        }

    }//GEN-LAST:event_jLabel19MousePressed

    private void jLabel20MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MousePressed
        JOptionPane.showMessageDialog(null, "Digite '<nome>' ao longo do texto ou do título, para inserir o \n"
                + "nome do cliente para qual o email será enviado\n\n"
                + "Ex: Caro <nome>!,\nA equipe do Salão notou que[...]");
    }//GEN-LAST:event_jLabel20MousePressed

    private void jCheckBoxVerificaHorariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxVerificaHorariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxVerificaHorariosActionPerformed

    private void jButtonCadastrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrar1ActionPerformed

        boolean sucesso = new CabeleireiroController().atualizarPreferencias(jCheckBoxVerificaHorarios.isSelected(),
                jSliderTempoEntreAgendamentos.getValue());

        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Preferências atualizadas com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar preferências");
        }

        new ManipuladorArquivo().escritor(Float.valueOf(jSliderTamanhoFonte.getValue()));
        notificarObservadores();

    }//GEN-LAST:event_jButtonCadastrar1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int opc = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja resetar o Banco de Dados?\n"
                + "Após feito isso, não será possível recuperar nenhuma das informações cadastradas.", "RESETAR BANCO DE DADOS", JOptionPane.YES_NO_OPTION);

        String msg = JOptionPane.showInputDialog("Digite:\nDELETAR");

        if (opc == 0) {
            if (msg.equals("DELETAR")) {

                File bancoAntigo = new File("BancoDeDados/beutifulsalondb.db");

                if (bancoAntigo.delete()) {
                    File bancoNovo = new File("BancoDeDados/beutifulsalondb.db");
                    try {
                        bancoNovo.createNewFile();
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, ex + "erro");
                    }

                    new CabeleireiroDAO().recriarBanco();
                    JOptionPane.showMessageDialog(null, "Banco de dados resetado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "ERRO ao resetar.");

                }

            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButtonRelatorioVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonRelatorioVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonRelatorioVendaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (jRadioButtonRelatorioVenda.isSelected()) {
            boolean sucesso = new RelatorioController().gerarRelatorioVenda(jFormattedTextFieldDataInicio.getValue().toString(), jFormattedTextFieldDataFim.getValue().toString());
            if (!sucesso) {
                JOptionPane.showMessageDialog(null, "Verifique as datas inseridas e tente novamente.");
            }
        } else if (jRadioButtonAgendamentos.isSelected()) {
            boolean sucesso = new RelatorioController().gerarRelatorioAgendamento(jFormattedTextFieldDataInicio.getValue().toString(), jFormattedTextFieldDataFim.getValue().toString());
            if (!sucesso) {
                JOptionPane.showMessageDialog(null, "Verifique as datas inseridas e tente novamente.");
            }
        } else if (jRadioButtonDespesas.isSelected()) {
            boolean sucesso = new RelatorioController().gerarRelatorioDespesas(jFormattedTextFieldDataInicio.getValue().toString(), jFormattedTextFieldDataFim.getValue().toString());
            if (!sucesso) {
                JOptionPane.showMessageDialog(null, "Verifique as datas inseridas e tente novamente.");
            }
        } else {
            boolean sucesso = new RelatorioController().gerarRelatorioCompras(jFormattedTextFieldDataInicio.getValue().toString(), jFormattedTextFieldDataFim.getValue().toString());
            if (!sucesso) {
                JOptionPane.showMessageDialog(null, "Verifique as datas inseridas e tente novamente.");
            }
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jRadioButtonRelatorioVenda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonRelatorioVenda1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonRelatorioVenda1ActionPerformed

    private void jLabel31MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MousePressed
        if (modalColaaboradores == null) {
            modalColaaboradores = new ModalColaboradores();
        }

        modalColaaboradores.setVisible(true);
    }//GEN-LAST:event_jLabel31MousePressed

    private void plotarGrafico(int mes) {
        ColaboradorController cc = new ColaboradorController();

        List<Colaborador> colaboradores = cc.listarColaboradores();

        colaboradores.forEach(c -> {
            c.setQtdRealizada(cc.getQuantidadeRealizada(c, Month.of(mes)));
        });

        jPanelGraficoColab.removeAll();
        jPanelGraficoColab.revalidate();
        jPanelGraficoColab.repaint();
        GraficoDeBarras grafico = new GraficoDeBarras();
        grafico.plotaGraficoColaborador(jPanelGraficoColab, colaboradores);
    }
    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged

        switch (jTabbedPane1.getSelectedIndex()) {
            case 4:
                ColaboradorController cc = new ColaboradorController();

                List<Colaborador> colaboradores = cc.listarColaboradores();

                colaboradores.forEach(c -> {
                    c.setQtdRealizada(cc.getQuantidadeRealizada(c, LocalDate.now().getMonth()));
                });

                GraficoDeBarras grafico = new GraficoDeBarras();

                grafico.plotaGraficoColaborador(jPanelGraficoColab, colaboradores);

                break;
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jComboBoxMesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMesesActionPerformed

        if (jTabbedPane1.getSelectedIndex() == 4) {
            plotarGrafico(jComboBoxMeses.getSelectedIndex() + 1);
        }

    }//GEN-LAST:event_jComboBoxMesesActionPerformed

    private void jComboBoxMesesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxMesesItemStateChanged

    }//GEN-LAST:event_jComboBoxMesesItemStateChanged

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
            java.util.logging.Logger.getLogger(CadastroCabeleireiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroCabeleireiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroCabeleireiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroCabeleireiro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroCabeleireiro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonCadastrar1;
    private javax.swing.JCheckBox jCheckBoxAniversario;
    private javax.swing.JCheckBox jCheckBoxUltimaVisita;
    private javax.swing.JCheckBox jCheckBoxVerificaHorarios;
    private javax.swing.JComboBox<String> jComboBoxMeses;
    private javax.swing.JComboBox<String> jComboBoxPeriodo;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JFormattedTextField jFormattedTextFieldCPF;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataFim;
    private javax.swing.JFormattedTextField jFormattedTextFieldDataInicio;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelNomeArquivoAniversario;
    private javax.swing.JLabel jLabelNomeArquivoUltimaVisita;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelEmails;
    private javax.swing.JPanel jPanelGraficoColab;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JRadioButton jRadioButtonAgendamentos;
    private javax.swing.JRadioButton jRadioButtonDespesas;
    private javax.swing.JRadioButton jRadioButtonRelatorioVenda;
    private javax.swing.JRadioButton jRadioButtonRelatorioVenda1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSlider jSliderTamanhoFonte;
    private javax.swing.JSlider jSliderTempoEntreAgendamentos;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextArea jTextAreaAniversario;
    private javax.swing.JTextArea jTextAreaUltimaVisita;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldTituloAniversario;
    private javax.swing.JTextField jTextFieldTituloUltimaVisita;
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
    }

    @Override
    public void update(ArrayList<LocalTime> horarios) {

        expediente = horarios;
    }

    @Override
    public void update(Orcamento orcamento) {

    }

    @Override
    public void registrarObservador(Observador observador) {

        this.observador = observador;
    }

    @Override
    public void removeObservador(Observador observador) {
        this.observador = null;
    }

    @Override
    public void notificarObservadores() {
        //código inventado para indicar que estou mudando a fonte
        this.observador.update("403@");

    }
}
