/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.view.Apresenta;

import BeutifulSalon.Ferramentas.JTextAreaJTable;
import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.Ferramentas.ManipulaFontes;
import BeutifulSalon.Ferramentas.ManipulaImagem;
import BeutifulSalon.Ferramentas.ManipulaStrings;
import BeutifulSalon.Tabelas.ClienteCompraTableModel;
import BeutifulSalon.Tabelas.ClienteServicoTableModel;
import BeutifulSalon.Tabelas.VendaTableModel;
import BeutifulSalon.controller.ClienteController;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Observador;
import BeutifulSalon.model.ObservadorCliente;
import BeutifulSalon.view.modais.ModalEmail;
import BeutifulSalon.view.modais.ModalFotoPerfil;
import java.awt.Font;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class DetalhesCliente extends javax.swing.JFrame implements ObservadorCliente {

    /**
     * Creates new form DetalhesCliente
     */
    private long idCliente;
    private Cliente cliente;
    private ModalEmail modEmail = null;
    private ModalFotoPerfil modFotoPerfil = null;
    private ClienteServicoTableModel modeloServicos = new ClienteServicoTableModel();
    private ClienteCompraTableModel modeloCompras = new ClienteCompraTableModel();

    public DetalhesCliente() {
        initComponents();

    }

    public DetalhesCliente(Cliente cliente) {
        initComponents();

        ManipulaFontes mf = new ManipulaFontes();
        jLabel12.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 30f)); //Endereço
        jLabel29.setFont(mf.getFont(mf.LIGHT, Font.PLAIN, 20f)); //Enviar Email

        jLabel15.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Rua
        jLabel17.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Número
        jLabel14.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Bairro
        jLabel16.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //CEP
        jLabel13.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //UF

        jLabel21.setFont(mf.getFont(mf.LIGHT, Font.BOLD, 25f)); //+ Informações
        jToggleButton1.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 12f)); //Alterar informações
        jButtonSalvar.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 11f)); //Salvar
        jLabel7.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Tipo do Cabelo
        jComboBoxTipoCabelo.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Box Tipo do Cabelo
        jLabel22.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Tamanho
        jComboBoxTamanhoCabelo.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Box Tamanho
        jLabel8.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Cor
        jTextFieldCorCabelo.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Box Cor
        jLabel23.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Como nos conheceu?
        jComboBoxComoNosConheceu.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Box Como nos conheceu?
        jLabel27.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Instagram
        jTextFieldInstagram.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Box Instagram
        jLabel28.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Facebook
        jTextFieldFacebook.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Box Facebook
        jTableCompras.setFont(mf.getFont(mf.SEMIBOLD, Font.PLAIN, 15f)); //Tabela
        jTableServicos.setFont(mf.getFont(mf.SEMIBOLD, Font.PLAIN, 15f)); //Tabela

        //MENU LATERAL
        jLabelAlterarFotoPerfil.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 10f)); //Alterar
        jLabelNome.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //NomeUsuario
        jLabelIdade.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 18f)); //Idade
        jLabelEmail.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f)); //Email
        jLabel19.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f)); //Telefone
        jLabelTelefone.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f)); //Tel2

        jLabel9.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f)); //Celular
        jLabelCelular.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f)); //Cel2
        jLabel3.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f)); //Última Visita
        jLabelUltimaViisita.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f)); //Sem informações
        jLabelClienteDesde.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f));
        jLabelDataClienteDesde.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f));
        jLabelClienteDesde1.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f));
        jLabelTotalGasto.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f));
        jLabel26.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f)); //Observações
        jTextAreaObservacoes.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f)); //Area Observacoes

        this.cliente = cliente;
        
        idCliente = cliente.getId();
        jLabelTotalGasto.setText(Dinheiro.parseString(cliente.calculaTotalAnualGasto(idCliente)));
        ClienteController cc = new ClienteController();
        ManipulaImagem mi = new ManipulaImagem();
        modeloServicos.listarServicos(cliente.getId());
        modeloCompras.getComprasCliente(idCliente);
        
        jTableServicos.setModel(modeloServicos); 
        jTableServicos.getColumnModel().getColumn(0).setCellRenderer(new JTextAreaJTable());
        jTableCompras.setModel(modeloCompras);
        jTableCompras.getColumnModel().getColumn(1).setCellRenderer(new JTextAreaJTable());

        byte[] imagemPerfil = cc.getImagemPerfil(cliente.getId());

        if (imagemPerfil != null) {
            try {
                jLabelFotoPerfil.setIcon(mi.redimensionaImg(imagemPerfil));
            } catch (IOException ex) {
                Logger.getLogger(DetalhesCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        jButtonSalvar.setVisible(false);
        jLabelNome.setText(new ManipulaStrings().abreviarNome(cliente.getNome() + " " + cliente.getSobrenome()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (cliente.getDataNasc() != null) {
            try {
                jLabelIdade.setText(String.valueOf(new ManipulaData().calculaIdade(LocalDate.parse(cliente.getDataNasc(), formatter))) + " Anos");
            } catch (Exception e) {

            }
        }
        if (cliente.getEmail() != null) {
            jLabelEmail.setText(cliente.getEmail());
        }

        jLabelEmail.setCaretPosition(0);
        jLabelTelefone.setText(cliente.getTelefoneResidencial());
        jLabelCelular.setText(cliente.getCelular());

        LocalDate ultimaVisita = cc.ultimaVisita(cliente.getId());
        LocalDate clienteDesde = cliente.getDataDeRegistro();

        if (ultimaVisita != null) {
            jLabelUltimaViisita.setText(ultimaVisita.format(formatter));
        }

        if (clienteDesde != null) {
            jLabelDataClienteDesde.setText(clienteDesde.format(formatter));
        }

        jTextFielRua.setText(cliente.getRua());
        jTextFieldBairro.setText(cliente.getBairro());
        jTextFieldCep.setText(cliente.getCep());
        jTextFieldUF.setText(cliente.getCidade());
        jTextFieldNumero.setText(cliente.getNumeroDaCasa());
        
        jTextFieldFacebook.setText(cliente.getFacebook());
        jTextFieldInstagram.setText(cliente.getInstagram());

        jComboBoxTipoCabelo.setSelectedIndex(cliente.getTipoDeCabelo());
        jComboBoxTamanhoCabelo.setSelectedIndex(cliente.getTamanhoCabelo());
        jComboBoxComoNosConheceu.setSelectedIndex(cliente.getDeOndeConheceu());
        jTextAreaObservacoes.setText(cliente.getObservacoes());
        jTextFieldCorCabelo.setText(cliente.getCorCabelo());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldUF = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldBairro = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldCep = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFielRua = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldNumero = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxTipoCabelo = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldCorCabelo = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jComboBoxTamanhoCabelo = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jComboBoxComoNosConheceu = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jTextFieldFacebook = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextFieldInstagram = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableServicos = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableCompras = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButtonSalvar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabelFotoPerfil = new javax.swing.JLabel();
        jLabelAlterarFotoPerfil = new javax.swing.JLabel();
        jLabelNome = new javax.swing.JLabel();
        jLabelIdade = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabelTelefone = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabelCelular = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelUltimaViisita = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaObservacoes = new javax.swing.JTextArea();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(24, 0), new java.awt.Dimension(24, 0), new java.awt.Dimension(20, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        jLabelEmail = new javax.swing.JTextField();
        jLabelClienteDesde = new javax.swing.JLabel();
        jLabelDataClienteDesde = new javax.swing.JLabel();
        jLabelClienteDesde1 = new javax.swing.JLabel();
        jLabelTotalGasto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setText("Endereço");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("UF");

        jTextFieldUF.setEditable(false);
        jTextFieldUF.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldUF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Bairro");

        jTextFieldBairro.setEditable(false);
        jTextFieldBairro.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldBairro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBairroActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText("Rua");

        jTextFieldCep.setEditable(false);
        jTextFieldCep.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldCep.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCepActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("CEP");

        jTextFielRua.setEditable(false);
        jTextFielRua.setBackground(new java.awt.Color(255, 255, 255));
        jTextFielRua.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Número");

        jTextFieldNumero.setEditable(false);
        jTextFieldNumero.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldNumero.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel21.setText("+ Informações");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Tipo do Cabelo");

        jComboBoxTipoCabelo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxTipoCabelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Liso 1A", "Liso 1B", "Liso 1C", "Ondulado 2A", "Ondulado 2B", "Ondulado 2C", "Cacheado 3A", "Cacheado 3B", "Cacheado 3C", "Crespo 4A", "Crespo 4B", "Crespo 4C", "Não Informado" }));
        jComboBoxTipoCabelo.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Cor do Cabelo");

        jTextFieldCorCabelo.setText("Não Informado");
        jTextFieldCorCabelo.setCaretPosition(0);
        jTextFieldCorCabelo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldCorCabelo.setEnabled(false);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setText("Tamanho");

        jComboBoxTamanhoCabelo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxTamanhoCabelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Curto", "Médio", "Longo", "Extra Longo", "Não Informado" }));
        jComboBoxTamanhoCabelo.setSelectedIndex(4);
        jComboBoxTamanhoCabelo.setEnabled(false);
        jComboBoxTamanhoCabelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTamanhoCabeloActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setText("Como nos conheceu?");

        jComboBoxComoNosConheceu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Internet", "Redes Sociais", "Amigos", "Família", "Panfletagem", "Outros" }));
        jComboBoxComoNosConheceu.setSelectedIndex(5);
        jComboBoxComoNosConheceu.setEnabled(false);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setText("Instagram");

        jTextFieldFacebook.setText("Não Informado");
        jTextFieldFacebook.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldFacebook.setEnabled(false);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setText("Facebook");

        jTextFieldInstagram.setText("Não Informado");
        jTextFieldInstagram.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldInstagram.setEnabled(false);

        jTabbedPane1.setName(""); // NOI18N

        jTableServicos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTableServicos);

        jTabbedPane1.addTab("Últimos Serviços", jScrollPane2);

        jTableCompras.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTableCompras);

        jTabbedPane1.addTab("Últimas Compras", jScrollPane3);

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Enviar Email");
        jLabel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel29.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel29MousePressed(evt);
            }
        });

        jToggleButton1.setBackground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/btnEditar.png"))); // NOI18N
        jToggleButton1.setText("Alterar informações");
        jToggleButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jToggleButton1StateChanged(evt);
            }
        });

        jButtonSalvar.setBackground(new java.awt.Color(255, 255, 255));
        jButtonSalvar.setText("Salvar");
        jButtonSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(36, 46, 65));

        jLabelFotoPerfil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFotoPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-pessoa.png"))); // NOI18N
        jLabelFotoPerfil.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelAlterarFotoPerfil.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAlterarFotoPerfil.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAlterarFotoPerfil.setText("Alterar");
        jLabelAlterarFotoPerfil.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jLabelAlterarFotoPerfil.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAlterarFotoPerfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAlterarFotoPerfilMousePressed(evt);
            }
        });

        jLabelNome.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelNome.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNome.setText("Mateus Santos");

        jLabelIdade.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelIdade.setForeground(new java.awt.Color(255, 255, 255));
        jLabelIdade.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIdade.setText("Idade Não Informada");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Telefone:");

        jLabelTelefone.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelTelefone.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTelefone.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTelefone.setText("29616298");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Celular:");

        jLabelCelular.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelCelular.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCelular.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelCelular.setText("991700362");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Última Visita:");

        jLabelUltimaViisita.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelUltimaViisita.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUltimaViisita.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelUltimaViisita.setText("--");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Observações");

        jTextAreaObservacoes.setColumns(20);
        jTextAreaObservacoes.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTextAreaObservacoes.setLineWrap(true);
        jTextAreaObservacoes.setRows(5);
        jTextAreaObservacoes.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextAreaObservacoes.setEnabled(false);
        jScrollPane1.setViewportView(jTextAreaObservacoes);

        jLabelEmail.setEditable(false);
        jLabelEmail.setBackground(new java.awt.Color(36, 46, 65));
        jLabelEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelEmail.setForeground(new java.awt.Color(255, 255, 255));
        jLabelEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jLabelEmail.setText("Email não Informado");
        jLabelEmail.setBorder(null);

        jLabelClienteDesde.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelClienteDesde.setForeground(new java.awt.Color(255, 255, 255));
        jLabelClienteDesde.setText("Cliente desde:");

        jLabelDataClienteDesde.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelDataClienteDesde.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDataClienteDesde.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelDataClienteDesde.setText("Sem informações");

        jLabelClienteDesde1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelClienteDesde1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelClienteDesde1.setText("Total Anual Gasto:");

        jLabelTotalGasto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelTotalGasto.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTotalGasto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTotalGasto.setText("Sem informações");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelFotoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelIdade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelNome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel26)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabelClienteDesde1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelTotalGasto))
                                    .addComponent(jLabelClienteDesde)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelUltimaViisita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabelDataClienteDesde)
                                            .addComponent(jLabelTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelCelular))))
                                .addGap(25, 25, 25)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabelAlterarFotoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(99, 99, 99))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabelFotoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelAlterarFotoPerfil)
                        .addGap(11, 11, 11)))
                .addComponent(jLabelNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelIdade)
                .addGap(5, 5, 5)
                .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabelTelefone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCelular)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUltimaViisita))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelClienteDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDataClienteDesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelClienteDesde1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTotalGasto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jTextFieldFacebook)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxComoNosConheceu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldInstagram)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxTamanhoCabelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCorCabelo)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextFielRua, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                        .addGap(24, 24, 24)
                        .addComponent(jTextFieldNumero, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldBairro, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCep, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldUF)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBoxTipoCabelo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFielRua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUF, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jToggleButton1)
                    .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel22)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTipoCabelo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTamanhoCabelo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCorCabelo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxComoNosConheceu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldInstagram, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldFacebook, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addGap(17, 17, 17))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 885, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 767, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jToggleButton1StateChanged

        if (jToggleButton1.isSelected()) {
            jToggleButton1.setText("Sair da Edição");
            jComboBoxTipoCabelo.setEnabled(true);
            jComboBoxTamanhoCabelo.setEnabled(true);
            jComboBoxComoNosConheceu.setEnabled(true);
            jTextFieldCorCabelo.setEnabled(true);
            jTextFieldFacebook.setEnabled(true);
            jTextFieldInstagram.setEnabled(true);
            jTextAreaObservacoes.setEnabled(true);
            jButtonSalvar.setVisible(true);

        } else {

            jToggleButton1.setText("Alterar Informações");
            jComboBoxTipoCabelo.setEnabled(false);
            jComboBoxTamanhoCabelo.setEnabled(false);
            jComboBoxComoNosConheceu.setEnabled(false);
            jTextFieldCorCabelo.setEnabled(false);
            jTextFieldFacebook.setEnabled(false);
            jTextFieldInstagram.setEnabled(false);
            jTextAreaObservacoes.setEnabled(false);
            jButtonSalvar.setVisible(false);
        }
    }//GEN-LAST:event_jToggleButton1StateChanged

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        int opc = JOptionPane.showConfirmDialog(null, "Deseja salvar as alterações?");
        boolean sucesso = false;

        if (opc == 0) {
            Cliente cliente = new Cliente();
            ClienteController cc = new ClienteController();
            cliente.setId(this.idCliente);
            cliente.setFacebook(jTextFieldFacebook.getText());
            cliente.setInstagram(jTextFieldInstagram.getText());
            cliente.setCorCabelo(jTextFieldCorCabelo.getText());
            cliente.setTipoDeCabelo(jComboBoxTipoCabelo.getSelectedIndex());
            cliente.setTamanhoCabelo(jComboBoxTamanhoCabelo.getSelectedIndex());
            cliente.setDeOndeConheceu(jComboBoxComoNosConheceu.getSelectedIndex());
            cliente.setObservacoes(jTextAreaObservacoes.getText());

            sucesso = cc.atualizarDetalhesCliente(cliente);

            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Atualização realizada com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar as informações, confira os campos novamente.");
            }

        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jTextFieldCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCepActionPerformed

    private void jTextFieldBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBairroActionPerformed

    private void jComboBoxTamanhoCabeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTamanhoCabeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTamanhoCabeloActionPerformed

    private void jLabel29MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MousePressed

        if (modEmail == null) {
            new ModalEmail(cliente).setVisible(true);
        } else {
            modEmail.setVisible(true);
        }

    }//GEN-LAST:event_jLabel29MousePressed

    private void jLabelAlterarFotoPerfilMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAlterarFotoPerfilMousePressed

        if (modFotoPerfil == null) {
            modFotoPerfil = new ModalFotoPerfil(idCliente);
            modFotoPerfil.registrarObservador(this);
            modFotoPerfil.setVisible(true);
        } else {
            modFotoPerfil.setVisible(true);
        }
    }//GEN-LAST:event_jLabelAlterarFotoPerfilMousePressed

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
            java.util.logging.Logger.getLogger(DetalhesCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetalhesCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetalhesCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetalhesCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetalhesCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<String> jComboBoxComoNosConheceu;
    private javax.swing.JComboBox<String> jComboBoxTamanhoCabelo;
    private javax.swing.JComboBox<String> jComboBoxTipoCabelo;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAlterarFotoPerfil;
    private javax.swing.JLabel jLabelCelular;
    private javax.swing.JLabel jLabelClienteDesde;
    private javax.swing.JLabel jLabelClienteDesde1;
    private javax.swing.JLabel jLabelDataClienteDesde;
    private javax.swing.JTextField jLabelEmail;
    private javax.swing.JLabel jLabelFotoPerfil;
    private javax.swing.JLabel jLabelIdade;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelTelefone;
    private javax.swing.JLabel jLabelTotalGasto;
    private javax.swing.JLabel jLabelUltimaViisita;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableCompras;
    private javax.swing.JTable jTableServicos;
    private javax.swing.JTextArea jTextAreaObservacoes;
    private javax.swing.JTextField jTextFielRua;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JTextField jTextFieldCep;
    private javax.swing.JTextField jTextFieldCorCabelo;
    private javax.swing.JTextField jTextFieldFacebook;
    private javax.swing.JTextField jTextFieldInstagram;
    private javax.swing.JTextField jTextFieldNumero;
    private javax.swing.JTextField jTextFieldUF;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(ImageIcon imagem) {
        jLabelFotoPerfil.setIcon(imagem);
    }

    @Override
    public void update(boolean gostouDaFoto) {
    }
}
