package BeutifulSalon.view.Edicao;

import BeutifulSalon.Ferramentas.ApresentaTabela;
import BeutifulSalon.view.Cadastros.*;
import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.Ferramentas.ManipulaFontes;
import BeutifulSalon.Ferramentas.RecuperaTabela;
import BeutifulSalon.Ferramentas.Valida;
import BeutifulSalon.Tabelas.DestacaTotalTabela;
import BeutifulSalon.Tabelas.ModalServicoTableModel;
import BeutifulSalon.controller.AgendamentoController;
import BeutifulSalon.controller.ClienteController;
import BeutifulSalon.dao.AgendamentoDAO;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.ObservadoEdicao;
import BeutifulSalon.model.Observador;
import BeutifulSalon.model.ObservadorEdicao;
import BeutifulSalon.model.Orcamento;
import BeutifulSalon.model.Servico;
import BeutifulSalon.view.modais.ModalInputMonetarios;
import BeutifulSalon.view.modais.ModalServicos;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.SwingConstants.LEFT;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author mateus
 */
public class EditarAgendamento extends javax.swing.JFrame implements Observador, ObservadoEdicao {

    /**
     * Creates new form CadastroAgendamento
     */
    private LocalTime horarioInicioAntigo;
    private LocalTime horarioFinalAntigo;
    private Agendamento agendamento;
    private String idCliente;
    private boolean isDesconto;
    private ModalInputMonetarios modalDesconto;
    private ModalInputMonetarios modalValorAdicional;
    private ArrayList<ObservadorEdicao> observadores = new ArrayList<>();
    private ModalServicoTableModel servicosEscolhidos = new ModalServicoTableModel();

    public EditarAgendamento() {
        initComponents();
    }

    public EditarAgendamento(Agendamento ag) {

        initComponents();

        ManipulaFontes mf = new ManipulaFontes();;
        horarioInicioAntigo = ag.getHorario();
        horarioFinalAntigo = ag.getFimAgendamento();
        jLabel1.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 40f)); //Cadastro de Agendamento
        jCheckBoxClienteVeio.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Cliente veio?
        jLabel4.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Nome do Cliente
        jLabel3.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Serviços Solicitados
        jLabel7.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Total Bruto: 
        jLabel8.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Valor Desconto:
        jLabel9.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //TOTAL :
        jLabel10.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Data
        jLabel2.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Horários disponíveis
        jLabel11.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Horário
        jLabel13.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Valor Adicional
        jTableServicosSolicitados.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Tabela Serviços Solicitados
        jListHorarios.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //ListaHorarios
        jCheckBoxDesconto.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //Desconto 
        jCheckValorAdicional.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f)); //valor Adicional
        jButtonFinalizarEdicao.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Finalizar Edição
        jDateChooser1.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));//Data dentro
        jTextFieldHorario.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));//Hora dentro

        this.agendamento = ag;
        ClienteController cc = new ClienteController();
        Cliente clienteAgendamento = cc.buscarCliente(ag.getIdCliente());
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/M/uuuu");
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");

        String dataFormatada = "";

        jTextFieldNome.setText(clienteAgendamento.getNome());
        this.idCliente = String.valueOf(clienteAgendamento.getId());
        jTextFieldTotal.setText(Dinheiro.parseString(ag.getTotal()));
        jTextFieldTotalBruto.setText(Dinheiro.parseString(ag.getTotal() + ag.getDesconto() - ag.getValorAdicional()));

        jTextFieldHorario.setValue(ag.getHorario().toString());

        if (ag.getDesconto() > 0) {
            jCheckBoxDesconto.setSelected(true);
            jTextFieldDesconto.setText("-" + Dinheiro.parseString(ag.getDesconto()));
        }

        if (ag.getValorAdicional() > 0) {
            jTextFieldValorAdicional.setText(Dinheiro.parseString(ag.getValorAdicional()));
            jCheckValorAdicional.setSelected(true);
        }
        if (!ag.getRealizado()) {
            jCheckBoxClienteVeio.setSelected(true);
        }

        try {
            jDateChooser1.setDate(formater.parse(ag.getData().format(formatterData)));
        } catch (ParseException ex) {
            Logger.getLogger(EditarAgendamento.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (agendamento.isPago()) {

            jToggleButton.setText("Pagamento Realizado");
            jComboBoxFormaPagamento.setEnabled(true);
            jComboBoxFormaPagamento.setSelectedItem(agendamento.getFormaDePagamento());
            Color verde = new Color(57, 201, 114);
            jToggleButton.setBackground(verde);
        } else {
            jToggleButton.setText("Pagamento Pendente");
            jComboBoxFormaPagamento.setEnabled(false);
            Color vermelho = new Color(248, 67, 69);
            jToggleButton.setBackground(vermelho);
        }

        servicosEscolhidos.getServicosAgendamento(ag.getIdAgendamento());
        servicosEscolhidos.calculaTempoTotal();
        jTableServicosSolicitados.setModel(servicosEscolhidos);
        jTableServicosSolicitados.getColumnModel().getColumn(1).setCellRenderer(new OcultaPreco(Color.BLACK, (jTableServicosSolicitados.getRowCount() - 1)));
        jTableServicosSolicitados.getColumnModel().getColumn(0).setCellRenderer(new DestacaTotalTabela(Color.WHITE, jTableServicosSolicitados.getRowCount() - 1));

     
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
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jCheckBoxClienteVeio = new javax.swing.JCheckBox();
        jCheckBoxDesconto = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldTotalBruto = new javax.swing.JTextField();
        jTextFieldDesconto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldTotal = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListHorarios = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldHorario = new javax.swing.JFormattedTextField();
        jButtonFinalizarEdicao = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableServicosSolicitados = new javax.swing.JTable();
        jLabelAddServicos = new javax.swing.JLabel();
        jCheckValorAdicional = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldValorAdicional = new javax.swing.JTextField();
        jToggleButton = new javax.swing.JToggleButton();
        jLabel14 = new javax.swing.JLabel();
        jComboBoxFormaPagamento = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(36, 46, 65));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Editar Agendamento");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-agendamentos-48.png"))); // NOI18N
        jLabel12.setText("jLabel12");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel1))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jCheckBoxClienteVeio.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxClienteVeio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jCheckBoxClienteVeio.setText("Cliente não compareceu");
        jCheckBoxClienteVeio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCheckBoxClienteVeio.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jCheckBoxClienteVeio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxClienteVeioActionPerformed(evt);
            }
        });

        jCheckBoxDesconto.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxDesconto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jCheckBoxDesconto.setForeground(new java.awt.Color(34, 34, 34));
        jCheckBoxDesconto.setText("Desconto");
        jCheckBoxDesconto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jCheckBoxDescontoMousePressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Total Bruto: ");

        jTextFieldTotalBruto.setEditable(false);
        jTextFieldTotalBruto.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldTotalBruto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldTotalBruto.setForeground(new java.awt.Color(34, 34, 34));
        jTextFieldTotalBruto.setText("R$ 0,00");

        jTextFieldDesconto.setEditable(false);
        jTextFieldDesconto.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldDesconto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldDesconto.setForeground(new java.awt.Color(34, 34, 34));
        jTextFieldDesconto.setText("-R$ 0,00");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Valor Desconto:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("TOTAL");

        jTextFieldTotal.setEditable(false);
        jTextFieldTotal.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldTotal.setForeground(new java.awt.Color(34, 34, 34));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Data");

        jDateChooser1.setDateFormatString("dd/MM/yyyy");

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jListHorarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jListHorarios);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Horários disponíveis");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Horário");

        try {
            jTextFieldHorario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jTextFieldHorario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldHorario.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextFieldHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldHorarioActionPerformed(evt);
            }
        });

        jButtonFinalizarEdicao.setBackground(new java.awt.Color(255, 255, 255));
        jButtonFinalizarEdicao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonFinalizarEdicao.setText("Concluir Edição");
        jButtonFinalizarEdicao.setBorder(null);
        jButtonFinalizarEdicao.setBorderPainted(false);
        jButtonFinalizarEdicao.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonFinalizarEdicao.setFocusPainted(false);
        jButtonFinalizarEdicao.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonFinalizarEdicao.setPreferredSize(new java.awt.Dimension(150, 65));
        jButtonFinalizarEdicao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonFinalizarEdicaoMousePressed(evt);
            }
        });
        jButtonFinalizarEdicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalizarEdicaoActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconLupa.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(34, 34, 34));
        jLabel4.setText("Nome do Cliente");

        jTextFieldNome.setEditable(false);
        jTextFieldNome.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(34, 34, 34));
        jLabel3.setText("Serviços Solicitados");

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

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Valor Adicional: ");

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

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Forma de Pagamento");

        jComboBoxFormaPagamento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dinheiro", "Crédito", "Débito", "Pix" }));
        jComboBoxFormaPagamento.setEnabled(false);
        jComboBoxFormaPagamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jComboBoxFormaPagamentoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(18, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jToggleButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBoxFormaPagamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelAddServicos)))
                        .addGap(12, 12, 12)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jTextFieldHorario)
                                .addGap(45, 45, 45)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jCheckBoxDesconto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jCheckValorAdicional, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldValorAdicional, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldTotalBruto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jCheckBoxClienteVeio, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonFinalizarEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addGap(324, 324, 324)))
                .addGap(15, 15, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jToggleButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelAddServicos)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGap(12, 12, 12))))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(jLabel7)
                                .addGap(23, 23, 23)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel8)
                                .addGap(32, 32, 32)
                                .addComponent(jLabel9)
                                .addGap(62, 62, 62))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jCheckBoxClienteVeio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldTotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldValorAdicional, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jCheckBoxDesconto)
                                    .addComponent(jCheckValorAdicional))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonFinalizarEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41))
        );

        jDateChooser1.setLocale(new Locale("pt", "BR"));
        jDateChooser1.setDateFormatString("dd/MM/yyyy");

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void jButtonFinalizarEdicaoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonFinalizarEdicaoMousePressed

    }//GEN-LAST:event_jButtonFinalizarEdicaoMousePressed

    private void jButtonFinalizarEdicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarEdicaoActionPerformed

        boolean sucesso = false;

        AgendamentoController ac = new AgendamentoController();

        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = "";
        String formaDePagamento = "";
        boolean pago;

        try {
            dataFormatada = formater.format(jDateChooser1.getDate());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao converter data");
        }
        if (!jToggleButton.isSelected()) {
            pago = true;
            formaDePagamento = String.valueOf(jComboBoxFormaPagamento.getSelectedItem());
        } else {
            pago = false;
            formaDePagamento = "--";
        }

        //editar
        try {
            sucesso = ac.atualizarAgendamento(
                    dataFormatada,
                    jTextFieldHorario.getText(),
                    Long.valueOf(this.idCliente),
                    servicosEscolhidos.getDados(),
                    calculaTotalFinal(),
                    Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldDesconto.getText())),
                    !jCheckBoxClienteVeio.isSelected(),
                    agendamento.getIdAgendamento(),
                    Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldValorAdicional.getText())),
                    pago,
                    formaDePagamento,
                    horarioInicioAntigo,
                    horarioFinalAntigo);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }

        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Agendamento atualizado com sucesso");
            notificarObservadores();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Agendamento. Verifique os dados inseridos!");
        }


    }//GEN-LAST:event_jButtonFinalizarEdicaoActionPerformed

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed

        //Método para listar horários disponíveis
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = "";
        try {
            dataFormatada = formater.format(jDateChooser1.getDate());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao converter data");
        }

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
    }//GEN-LAST:event_jLabel6MousePressed

    private void jCheckBoxClienteVeioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxClienteVeioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxClienteVeioActionPerformed

    private void jTextFieldHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldHorarioActionPerformed

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

    private void jComboBoxFormaPagamentoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxFormaPagamentoMousePressed

    }//GEN-LAST:event_jComboBoxFormaPagamentoMousePressed

    private void limparCampos() {
        jTextFieldNome.setText("");
        jTextFieldDesconto.setText("-R$ 0,00");
        jTextFieldTotalBruto.setText("R$ 0,00");
        jTextFieldValorAdicional.setText("R$ 0,00");
        jTextFieldTotal.setText("");
        jTextFieldHorario.setText("");

        DefaultTableModel modal = (DefaultTableModel) jTableServicosSolicitados.getModel();
        modal.setRowCount(0);
        jTableServicosSolicitados.setModel(modal);
    }

    private void calculaTotalBruto() {
        long total = 0;
        try {
            List<Servico> servicos = servicosEscolhidos.getDados();

            try {
                for (Servico s : servicos) {
                    total += s.getPreco();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao calcular total " + e);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Erro ao calcular total2" + e);
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
            java.util.logging.Logger.getLogger(EditarAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarAgendamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFinalizarEdicao;
    private javax.swing.JCheckBox jCheckBoxClienteVeio;
    private javax.swing.JCheckBox jCheckBoxDesconto;
    private javax.swing.JCheckBox jCheckValorAdicional;
    private javax.swing.JComboBox<String> jComboBoxFormaPagamento;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAddServicos;
    private javax.swing.JList<String> jListHorarios;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableServicosSolicitados;
    private javax.swing.JTextField jTextFieldDesconto;
    private javax.swing.JFormattedTextField jTextFieldHorario;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldTotal;
    private javax.swing.JTextField jTextFieldTotalBruto;
    private javax.swing.JTextField jTextFieldValorAdicional;
    private javax.swing.JToggleButton jToggleButton;
    // End of variables declaration//GEN-END:variables
    
    
    
    
    @Override
    public void update(Object obj) {
        servicosEscolhidos = (ModalServicoTableModel) obj;
        servicosEscolhidos.calculaTempoTotal();
        jTableServicosSolicitados.setModel(servicosEscolhidos);
        jTableServicosSolicitados.getColumnModel().getColumn(1).setCellRenderer(new OcultaPreco(Color.BLACK, (jTableServicosSolicitados.getRowCount() - 1)));
        jTableServicosSolicitados.getColumnModel().getColumn(0).setCellRenderer(new DestacaTotalTabela(Color.WHITE, jTableServicosSolicitados.getRowCount() - 1));
        calculaTotalBruto();
        calculaTotal();
    }

    @Override
    public void update(DefaultTableModel model) {

        try {
            jTableServicosSolicitados.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir serviços na tabela." + e);
        }

        calculaTotalBruto();
        calculaTotal();

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
        this.idCliente = String.valueOf(cliente.getId());
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

    @Override
    public void registrarObservador(ObservadorEdicao observador) {
        observadores.add(observador);
    }

    @Override
    public void removeObservador(ObservadorEdicao observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores() {
        observadores.forEach(ob -> {
            ob.update(true);
        });
    }
}
