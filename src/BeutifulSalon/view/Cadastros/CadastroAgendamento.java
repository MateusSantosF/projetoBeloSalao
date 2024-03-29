package BeutifulSalon.view.Cadastros;

import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.Ferramentas.ManipulaFontes;
import BeutifulSalon.Ferramentas.RecuperaTabela;
import BeutifulSalon.Ferramentas.Valida;
import BeutifulSalon.Tabelas.ColaboradorTableModel;
import BeutifulSalon.Tabelas.DestacaTotalTabela;
import BeutifulSalon.Tabelas.ModalProdutosCompradosTableModel;
import BeutifulSalon.Tabelas.ModalServicoTableModel;
import BeutifulSalon.controller.AgendamentoController;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Colaborador;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Item;
import BeutifulSalon.model.Observador;
import BeutifulSalon.model.Orcamento;
import BeutifulSalon.model.Servico;
import BeutifulSalon.view.Apresenta.ApresentaFinancas;
import BeutifulSalon.view.modais.ModalCliente;
import BeutifulSalon.view.modais.ModalInputMonetarios;
import BeutifulSalon.view.modais.ModalProdutos;
import BeutifulSalon.view.modais.ModalServicos;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author mateus
 */
public class CadastroAgendamento extends javax.swing.JFrame implements Observador {

    /**
     * Creates new form CadastroAgendamento
     */
    private long idCliente;
    private boolean isDesconto;
    private ModalInputMonetarios modalDesconto;
    private ModalInputMonetarios modalValorAdicional;
    private ModalServicoTableModel servicosEscolhidos = new ModalServicoTableModel();
    private ModalProdutosCompradosTableModel produtosComprados = new ModalProdutosCompradosTableModel();
    private ColaboradorTableModel modeloColaborador = new ColaboradorTableModel();

    public CadastroAgendamento() {
        initComponents();

        Color verde = new Color(57, 201, 114);
        ManipulaFontes mf = new ManipulaFontes();;

        jLabel1.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 40f)); //Cadastro de Agendamento
        jLabel4.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Nome do Cliente
        jLabel3.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Serviços Solicitados
        jLabelProdutosComprados.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); // produtos Solicitados
        jLabelColaboradorResponsavel.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); // Colabrador Responsavel
        jComboBoxColaborador.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jLabel7.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Total Bruto: 
        jLabel8.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Valor Desconto:
        jLabel13.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Valor Adicional
        //jLabel14.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Forma de pagamento
        jLabel9.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //TOTAL :
        jLabel10.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Data
        jLabel2.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Horários disponíveis
        jLabel11.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Horário
        jTableServicosSolicitados.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Tabela Serviços Solicitados
        jTableProdutosComprados.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 13f)); //Tabela Produtos Comprados
        jListHorarios.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //ListaHorarios
        jCheckBoxDesconto.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Desconto 
        jCheckValorAdicional.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //valor adicional
        jButtonFinalizarCompra.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Finalizar Agendamento
        jDateChooser1.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 12f));
        jToggleButton.setText("Pagamento Realizado");
        jComboBoxFormaPagamento.setEnabled(true);

        jToggleButton.setBackground(verde);
        jDateChooser1.setDate(new ManipulaData().localDateToDate(LocalDate.now()));

        jTableServicosSolicitados.setModel(servicosEscolhidos);
        jTableProdutosComprados.setModel(produtosComprados);

        modeloColaborador.getTodosColaboradores();

        modeloColaborador.getDados().forEach(cob -> {

            jComboBoxColaborador.addItem(cob.getNome());
        });

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
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabelAddCliente = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldTotalBruto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldDesconto = new javax.swing.JTextField();
        jTextFieldTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jCheckBoxDesconto = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableServicosSolicitados = new javax.swing.JTable();
        jLabelAddServicos = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListHorarios = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldHorario = new javax.swing.JFormattedTextField();
        jButtonFinalizarCompra = new javax.swing.JButton();
        jCheckValorAdicional = new javax.swing.JCheckBox();
        jTextFieldValorAdicional = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jComboBoxFormaPagamento = new javax.swing.JComboBox<>();
        jToggleButton = new javax.swing.JToggleButton();
        jLabelProdutosComprados = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableProdutosComprados = new javax.swing.JTable();
        jLabelAddProdutos = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabelColaboradorResponsavel = new javax.swing.JLabel();
        jComboBoxColaborador = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(938, 774));

        jPanel2.setBackground(new java.awt.Color(36, 46, 65));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cadastro de Agendamento");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-agendamentos-48.png"))); // NOI18N
        jLabel12.setText("jLabel12");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel1))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(34, 34, 34));
        jLabel4.setText("Nome do Cliente");

        jTextFieldNome.setEditable(false);
        jTextFieldNome.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldNome.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeActionPerformed(evt);
            }
        });

        jLabelAddCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-add.png"))); // NOI18N
        jLabelAddCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddClienteMousePressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Total Bruto: ");

        jTextFieldTotalBruto.setEditable(false);
        jTextFieldTotalBruto.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldTotalBruto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldTotalBruto.setForeground(new java.awt.Color(34, 34, 34));
        jTextFieldTotalBruto.setText("R$ 0,00");
        jTextFieldTotalBruto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTotalBrutoActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Valor Desconto:");

        jTextFieldDesconto.setEditable(false);
        jTextFieldDesconto.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldDesconto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldDesconto.setForeground(new java.awt.Color(34, 34, 34));
        jTextFieldDesconto.setText("-R$ 0,00");

        jTextFieldTotal.setEditable(false);
        jTextFieldTotal.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldTotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldTotal.setForeground(new java.awt.Color(34, 34, 34));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("TOTAL :");

        jCheckBoxDesconto.setBackground(new java.awt.Color(243, 244, 245));
        jCheckBoxDesconto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBoxDesconto.setForeground(new java.awt.Color(34, 34, 34));
        jCheckBoxDesconto.setText("Desconto");
        jCheckBoxDesconto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCheckBoxDesconto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jCheckBoxDescontoMousePressed(evt);
            }
        });
        jCheckBoxDesconto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxDescontoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(34, 34, 34));
        jLabel3.setText("Serviços Solicitados");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTableServicosSolicitados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Preço", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableServicosSolicitados);
        if (jTableServicosSolicitados.getColumnModel().getColumnCount() > 0) {
            jTableServicosSolicitados.getColumnModel().getColumn(2).setMinWidth(1);
            jTableServicosSolicitados.getColumnModel().getColumn(2).setPreferredWidth(1);
            jTableServicosSolicitados.getColumnModel().getColumn(2).setMaxWidth(1);
        }

        jLabelAddServicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-add.png"))); // NOI18N
        jLabelAddServicos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddServicosMousePressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Data");

        jDateChooser1.setDateFormatString("dd/MM/yyyy");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Horários disponíveis");

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jListHorarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jListHorarios);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconLupa.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Horário");

        try {
            jTextFieldHorario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextFieldHorario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldHorario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jButtonFinalizarCompra.setBackground(new java.awt.Color(255, 255, 255));
        jButtonFinalizarCompra.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonFinalizarCompra.setText("Finalizar Agendamento");
        jButtonFinalizarCompra.setBorder(null);
        jButtonFinalizarCompra.setBorderPainted(false);
        jButtonFinalizarCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonFinalizarCompra.setFocusPainted(false);
        jButtonFinalizarCompra.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonFinalizarCompra.setPreferredSize(new java.awt.Dimension(150, 65));
        jButtonFinalizarCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonFinalizarCompraMousePressed(evt);
            }
        });
        jButtonFinalizarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalizarCompraActionPerformed(evt);
            }
        });

        jCheckValorAdicional.setBackground(new java.awt.Color(243, 244, 245));
        jCheckValorAdicional.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckValorAdicional.setForeground(new java.awt.Color(34, 34, 34));
        jCheckValorAdicional.setText("Valor Adicional");
        jCheckValorAdicional.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jCheckValorAdicional.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jCheckValorAdicionalMousePressed(evt);
            }
        });
        jCheckValorAdicional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckValorAdicionalActionPerformed(evt);
            }
        });

        jTextFieldValorAdicional.setEditable(false);
        jTextFieldValorAdicional.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldValorAdicional.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldValorAdicional.setForeground(new java.awt.Color(34, 34, 34));
        jTextFieldValorAdicional.setText("R$ 0,00");
        jTextFieldValorAdicional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldValorAdicionalActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Total Adicional: ");

        jComboBoxFormaPagamento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dinheiro", "Crédito", "Débito", "Pix" }));
        jComboBoxFormaPagamento.setEnabled(false);
        jComboBoxFormaPagamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jComboBoxFormaPagamentoMousePressed(evt);
            }
        });

        jToggleButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jToggleButton.setText("Pagamento Realizado");
        jToggleButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jToggleButton.setFocusable(false);
        jToggleButton.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jToggleButtonStateChanged(evt);
            }
        });
        jToggleButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jToggleButtonMousePressed(evt);
            }
        });
        jToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonActionPerformed(evt);
            }
        });
        jToggleButton.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jToggleButtonPropertyChange(evt);
            }
        });

        jLabelProdutosComprados.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelProdutosComprados.setForeground(new java.awt.Color(34, 34, 34));
        jLabelProdutosComprados.setText("Produtos Comprados");

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTableProdutosComprados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Marca", "Preço", "Qtd"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTableProdutosComprados);
        if (jTableProdutosComprados.getColumnModel().getColumnCount() > 0) {
            jTableProdutosComprados.getColumnModel().getColumn(3).setPreferredWidth(30);
        }

        jLabelAddProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-add.png"))); // NOI18N
        jLabelAddProdutos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddProdutosMousePressed(evt);
            }
        });

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-lixeira.png"))); // NOI18N
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel15MousePressed(evt);
            }
        });

        jLabelColaboradorResponsavel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelColaboradorResponsavel.setForeground(new java.awt.Color(34, 34, 34));
        jLabelColaboradorResponsavel.setText("Colaborador Responsável");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jToggleButton)
                        .addGap(1, 276, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabelColaboradorResponsavel)
                                    .addComponent(jComboBoxFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jComboBoxColaborador, javax.swing.GroupLayout.Alignment.LEADING, 0, 273, Short.MAX_VALUE)
                                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabelAddCliente)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelProdutosComprados, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabelAddServicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(20, 20, 20))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabelAddProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2)
                            .addComponent(jButtonFinalizarCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jCheckBoxDesconto)
                            .addGap(29, 29, 29)
                            .addComponent(jCheckValorAdicional, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(94, 94, 94)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldValorAdicional, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(jTextFieldTotalBruto, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabelColaboradorResponsavel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxColaborador, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelAddCliente))
                                .addGap(26, 26, 26)
                                .addComponent(jToggleButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelAddServicos)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelProdutosComprados)
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabelAddProdutos)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel15))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextFieldTotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextFieldValorAdicional, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jCheckBoxDesconto)
                                    .addComponent(jCheckValorAdicional))
                                .addGap(33, 33, 33)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(30, 30, 30)
                                .addComponent(jButtonFinalizarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1))))
        );

        jDateChooser1.setLocale(new Locale("pt", "BR"));
        jDateChooser1.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelAddClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddClienteMousePressed
        ModalCliente modal = new ModalCliente();
        modal.registrarObservador(this);
        modal.setVisible(true);
    }//GEN-LAST:event_jLabelAddClienteMousePressed

    private void jLabelAddServicosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddServicosMousePressed
        ModalServicos modal = new ModalServicos();
        modal.registrarObservador(this);
        modal.setVisible(true);
    }//GEN-LAST:event_jLabelAddServicosMousePressed

    private void jCheckBoxDescontoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxDescontoMousePressed
        jTextFieldDesconto.setText("R$ 0,00");
        isDesconto = true;
        calculaTotalBruto();
        calculaTotal();

        if (!jCheckBoxDesconto.isSelected()) {
            jCheckBoxDesconto.setSelected(true);

            if (modalDesconto == null) {
                modalDesconto = new ModalInputMonetarios("Insira o valor do desconto");
                modalDesconto.registrarObservador(this);
                modalDesconto.setVisible(true);
            } else {
                modalDesconto.setVisible(true);
            }

        }
    }//GEN-LAST:event_jCheckBoxDescontoMousePressed

    private void jButtonFinalizarCompraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonFinalizarCompraMousePressed

    }//GEN-LAST:event_jButtonFinalizarCompraMousePressed

    private void jButtonFinalizarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarCompraActionPerformed

        boolean sucesso;

        AgendamentoController ac = new AgendamentoController();

        try {
            SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
            String dataFormatada = "";
            String formaDePagamento = "";
            boolean pago;

            if (!jToggleButton.isSelected()) {
                pago = true;
                formaDePagamento = String.valueOf(jComboBoxFormaPagamento.getSelectedItem());
            } else {
                pago = false;
                formaDePagamento = "--";
            }
            try {
                dataFormatada = formater.format(jDateChooser1.getDate());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao converter data");
            }

            sucesso = ac.cadastraAgendamento(
                    dataFormatada,
                    jTextFieldHorario.getText(),
                    idCliente,
                    servicosEscolhidos.getDados(),
                    produtosComprados.getDados(),
                    calculaTotalFinal(),
                    Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldDesconto.getText())),
                    Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldValorAdicional.getText())),
                    true,
                    pago,
                    formaDePagamento,
                    modeloColaborador.getDados().get(jComboBoxColaborador.getSelectedIndex()).getIdColaborador());

            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Agendamento Realizado com sucesso");
                limparCampos();
                servicosEscolhidos.clearDados();
                jTableServicosSolicitados.setModel(servicosEscolhidos);
                modalDesconto = null;
                jCheckBoxDesconto.setSelected(false);
                jCheckValorAdicional.setSelected(false);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar Agendamento. Verifique os dados inseridos!");
            }
        } catch (ExceptionDAO ex) {
            Logger.getLogger(CadastroAgendamento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonFinalizarCompraActionPerformed

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed

        //Método para listar horários disponíveis
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = "0";
        try {
            if (jDateChooser1.getDate() != null) {
                dataFormatada = formater.format(jDateChooser1.getDate());

                if (Valida.isData(dataFormatada)) {
                    ManipulaData manipulaData = new ManipulaData();
                    DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/M/uuuu");
                    LocalDate dataDigitada = null;
                    try {
                        dataDigitada = LocalDate.parse(dataFormatada, formatterData);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Data inválida!" + e);
                    }
                    List<LocalTime> horariosLivres = manipulaData.recuperaHorariosDisponiveis(dataDigitada);
                    DefaultListModel<String> model = new DefaultListModel<String>();

                    ArrayList<String> horariosFormatados = manipulaData.formataHorariosDisponiveis(horariosLivres, dataDigitada);

                    horariosFormatados.forEach(t -> {
                        model.addElement(t);
                    });

                    jListHorarios.setModel(model);

                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma data antes.");
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao converter data");
        }


    }//GEN-LAST:event_jLabel6MousePressed

    private void jCheckBoxDescontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxDescontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxDescontoActionPerformed

    private void jTextFieldNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeActionPerformed

    private void jTextFieldTotalBrutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTotalBrutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTotalBrutoActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        modalDesconto = null;
    }//GEN-LAST:event_formWindowClosing

    private void jCheckValorAdicionalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckValorAdicionalMousePressed
        isDesconto = false;
        jTextFieldValorAdicional.setText("R$ 0,00");
        calculaTotalBruto();
        calculaTotal();
        if (!jCheckValorAdicional.isSelected()) {
            jCheckValorAdicional.setSelected(true);

            if (modalValorAdicional == null) {
                modalValorAdicional = new ModalInputMonetarios("Insira o valor Adicional");
                modalValorAdicional.registrarObservador(this);
                modalValorAdicional.setVisible(true);
            } else {
                modalValorAdicional.setVisible(true);
            }

        }
    }//GEN-LAST:event_jCheckValorAdicionalMousePressed

    private void jCheckValorAdicionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckValorAdicionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckValorAdicionalActionPerformed

    private void jTextFieldValorAdicionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldValorAdicionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldValorAdicionalActionPerformed

    private void jComboBoxFormaPagamentoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxFormaPagamentoMousePressed

    }//GEN-LAST:event_jComboBoxFormaPagamentoMousePressed

    private void jToggleButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jToggleButtonStateChanged

    }//GEN-LAST:event_jToggleButtonStateChanged

    private void jToggleButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButtonMousePressed

        if (jToggleButton.isSelected()) {
            jToggleButton.setText("Pagamento Realizado");
            jComboBoxFormaPagamento.setEnabled(true);
            Color verde = new Color(57, 201, 114);
            jToggleButton.setBackground(verde);
        } else {
            jToggleButton.setText("Pagamento Pendente");
            jComboBoxFormaPagamento.setEnabled(false);
            Color vermelho = new Color(248, 67, 69);
            jToggleButton.setBackground(vermelho);
        }
    }//GEN-LAST:event_jToggleButtonMousePressed

    private void jToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonActionPerformed

    }//GEN-LAST:event_jToggleButtonActionPerformed

    private void jToggleButtonPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jToggleButtonPropertyChange

    }//GEN-LAST:event_jToggleButtonPropertyChange

    private void jLabelAddProdutosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddProdutosMousePressed

        ModalProdutos modal = new ModalProdutos(false);
        modal.registrarObservador(this);
        modal.setVisible(true);
    }//GEN-LAST:event_jLabelAddProdutosMousePressed

    private void jLabel15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MousePressed

        int opc = JOptionPane.showConfirmDialog(null, "Realmente deseja apagar os produtos comprados?", "Limpar Produtos", JOptionPane.YES_NO_OPTION);

        if (opc == 0) {
            produtosComprados.limparTabela();
            jTableProdutosComprados.setModel(produtosComprados);
            calculaTotalBruto();
            calculaTotal();
        }


    }//GEN-LAST:event_jLabel15MousePressed

    private void limparCampos() {
        jTextFieldNome.setText("");
        jTextFieldDesconto.setText("-R$ 0,00");
        jTextFieldTotalBruto.setText("R$ 0,00");
        jTextFieldValorAdicional.setText("R$ 0,00");
        jTextFieldTotal.setText("");
        jTextFieldHorario.setText("");

    }

    private void calculaTotalBruto() {
        long total = 0;
        try {
            List<Servico> servicos = servicosEscolhidos.getDados();

            for (Servico s : servicos) {
                total += s.getPreco();
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao calcular total2" + e);
        }

        List<Item> produtos = produtosComprados.getDados();
        try {
            for (Item prod : produtos) {
                total += prod.getPrecoTotal();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }

        jTextFieldTotalBruto.setText(Dinheiro.parseString(total));

    }

    private void calculaTotal() {

        try {

            long valorDesconto = 0;
            long valorAdicional = 0;

            if (!jTextFieldDesconto.getText().equals("")) {
                valorDesconto = Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldDesconto.getText()));
            }
            if (!jTextFieldValorAdicional.getText().equals("")) {
                valorAdicional = Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldValorAdicional.getText()));
            }

            long valorTotalBruto = Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldTotalBruto.getText()));
            long valorTotal = valorTotalBruto - valorDesconto + valorAdicional;
            jTextFieldTotal.setText(Dinheiro.parseString(valorTotal));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao calcular total " + e);
        }
    }

    private long calculaTotalFinal() {

        long valorTotal = 0;
        try {

            long valorDesconto = 0;
            long valorAdicional = 0;

            if (!jTextFieldDesconto.getText().equals("")) {
                valorDesconto = Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldDesconto.getText()));
            }
            if (!jTextFieldValorAdicional.getText().equals("")) {
                valorAdicional = Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldValorAdicional.getText()));
            }

            long valorTotalBruto = Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldTotalBruto.getText()));
            valorTotal = valorTotalBruto - valorDesconto + valorAdicional;
            jTextFieldTotal.setText(Dinheiro.parseString(valorTotal));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao calcular total " + e);
        }

        return valorTotal;
    }

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
            java.util.logging.Logger.getLogger(CadastroAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroAgendamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFinalizarCompra;
    private javax.swing.JCheckBox jCheckBoxDesconto;
    private javax.swing.JCheckBox jCheckValorAdicional;
    private javax.swing.JComboBox<String> jComboBoxColaborador;
    private javax.swing.JComboBox<String> jComboBoxFormaPagamento;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAddCliente;
    private javax.swing.JLabel jLabelAddProdutos;
    private javax.swing.JLabel jLabelAddServicos;
    private javax.swing.JLabel jLabelColaboradorResponsavel;
    private javax.swing.JLabel jLabelProdutosComprados;
    private javax.swing.JList<String> jListHorarios;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableProdutosComprados;
    private javax.swing.JTable jTableServicosSolicitados;
    private javax.swing.JTextField jTextFieldDesconto;
    private javax.swing.JFormattedTextField jTextFieldHorario;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldTotal;
    private javax.swing.JTextField jTextFieldTotalBruto;
    private javax.swing.JTextField jTextFieldValorAdicional;
    private javax.swing.JToggleButton jToggleButton;
    // End of variables declaration//GEN-END:variables

    public class OcultaPreco extends DefaultTableCellRenderer implements TableCellRenderer {

        private Color color;
        private int row = -1;

        public OcultaPreco(Color color, int row) {
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
                    c.setBackground(Color.BLACK);
                    c.setForeground(Color.BLACK);
                    this.setHorizontalAlignment(LEFT);

                } else {
                    c.setBackground(Color.WHITE);
                    c.setForeground(Color.BLACK);
                }
            }

            return c;
        }

    }

    @Override
    public void update(Object obj) {

        if (obj instanceof ModalServicoTableModel) {
            servicosEscolhidos = (ModalServicoTableModel) obj;
            servicosEscolhidos.calculaTempoTotal();
            jTableServicosSolicitados.setModel(servicosEscolhidos);
            jTableServicosSolicitados.getColumnModel().getColumn(1).setCellRenderer(new OcultaPreco(Color.BLACK, (jTableServicosSolicitados.getRowCount() - 1)));
            jTableServicosSolicitados.getColumnModel().getColumn(0).setCellRenderer(new DestacaTotalTabela(Color.WHITE, jTableServicosSolicitados.getRowCount() - 1));
            calculaTotalBruto();
            calculaTotal();
        }

        if (obj instanceof ModalProdutosCompradosTableModel) {

            produtosComprados = (ModalProdutosCompradosTableModel) obj;
            jTableProdutosComprados.setModel(produtosComprados);
            calculaTotalBruto();
            calculaTotal();
        }

    }

    @Override
    public void update(DefaultTableModel model) {
    }

    @Override
    public void update(String valorDesconto) {

        if (isDesconto) {
            jTextFieldDesconto.setText("-" + Dinheiro.parseString(Dinheiro.retiraCaracteres(valorDesconto)));
            jCheckBoxDesconto.setSelected(true);

        } else {
            jTextFieldValorAdicional.setText(Dinheiro.parseString(Dinheiro.retiraCaracteres(valorDesconto)));
            jCheckValorAdicional.setSelected(true);
        }
        calculaTotalBruto();
        calculaTotal();

    }

    @Override
    public void update(Cliente cliente) {

        jTextFieldNome.setText(cliente.getNome());
        idCliente = cliente.getId();
    }

    @Override
    public void update(Servico servico) {
    }

    @Override
    public void update(ArrayList<LocalTime> horarios) {
    }

    @Override
    public void update(Orcamento orcamento) {
    }
}
