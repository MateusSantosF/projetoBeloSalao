/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.view.Cadastros;

import BeutifulSalon.Ferramentas.RecuperaTabela;
import BeutifulSalon.controller.CabeleireiroController;
import BeutifulSalon.view.modais.modalCliente;
import BeutifulSalon.view.modais.modalProdutos;
import BeutifulSalon.controller.CompraController;
import BeutifulSalon.controller.VendaController;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Item;
import BeutifulSalon.model.Observador;
import BeutifulSalon.model.Orcamento;
import BeutifulSalon.model.Servico;
import BeutifulSalon.view.modais.modalInputMonetarios;
import java.awt.HeadlessException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mateus
 */
public class CadastroFluxoDeCaixa extends javax.swing.JFrame implements Observador {

    public static String nomeCliente;
    public static String CPF;
    public static long id_Cliente;

    public CadastroFluxoDeCaixa() {
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupCompraProduto = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanelProduto = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNome2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldCPF2 = new javax.swing.JTextField();
        jLabelAddCliente = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelAddProdutos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProdutosComprados = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldTotalBruto2 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jTextFieldDesconto2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldTotal2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jCheckBoxDesconto2 = new javax.swing.JCheckBox();
        jButtonFinalizarCompra = new javax.swing.JButton();
        jRadioButtonCliente = new javax.swing.JRadioButton();
        jRadioButtonCabelereiro = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(243, 244, 245));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(36, 46, 65));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-financas-48.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Fluxo de caixa");

        jPanelProduto.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Nome do Cliente");

        jTextFieldNome2.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("CPF");

        jTextFieldCPF2.setEditable(false);

        jLabelAddCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-add.png"))); // NOI18N
        jLabelAddCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddClienteMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Produtos Comprados");

        jLabelAddProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-add.png"))); // NOI18N
        jLabelAddProdutos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddProdutosMousePressed(evt);
            }
        });

        jTableProdutosComprados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Marca", "Preço", "Qtd.", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableProdutosComprados);
        if (jTableProdutosComprados.getColumnModel().getColumnCount() > 0) {
            jTableProdutosComprados.getColumnModel().getColumn(4).setMinWidth(1);
            jTableProdutosComprados.getColumnModel().getColumn(4).setPreferredWidth(20);
            jTableProdutosComprados.getColumnModel().getColumn(4).setMaxWidth(1);
        }

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Total Bruto: ");

        jTextFieldTotalBruto2.setEditable(false);
        jTextFieldTotalBruto2.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldTotalBruto2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldTotalBruto2.setForeground(new java.awt.Color(34, 34, 34));
        jTextFieldTotalBruto2.setText("R$ 0,00");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jTextFieldDesconto2.setEditable(false);
        jTextFieldDesconto2.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldDesconto2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldDesconto2.setForeground(new java.awt.Color(34, 34, 34));
        jTextFieldDesconto2.setText("-R$ 0,00");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Valor Desconto:");

        jTextFieldTotal2.setEditable(false);
        jTextFieldTotal2.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldTotal2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldTotal2.setForeground(new java.awt.Color(34, 34, 34));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("TOTAL");

        jCheckBoxDesconto2.setBackground(new java.awt.Color(243, 244, 245));
        jCheckBoxDesconto2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBoxDesconto2.setForeground(new java.awt.Color(34, 34, 34));
        jCheckBoxDesconto2.setText("Desconto");
        jCheckBoxDesconto2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jCheckBoxDesconto2MousePressed(evt);
            }
        });

        jButtonFinalizarCompra.setBackground(new java.awt.Color(255, 255, 255));
        jButtonFinalizarCompra.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonFinalizarCompra.setText("Finalizar Compra");
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

        jRadioButtonCliente.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupCompraProduto.add(jRadioButtonCliente);
        jRadioButtonCliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButtonCliente.setSelected(true);
        jRadioButtonCliente.setText("Cliente");
        jRadioButtonCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonClienteActionPerformed(evt);
            }
        });

        jRadioButtonCabelereiro.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroupCompraProduto.add(jRadioButtonCabelereiro);
        jRadioButtonCabelereiro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButtonCabelereiro.setText("Cabelereiro");
        jRadioButtonCabelereiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCabelereiroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelProdutoLayout = new javax.swing.GroupLayout(jPanelProduto);
        jPanelProduto.setLayout(jPanelProdutoLayout);
        jPanelProdutoLayout.setHorizontalGroup(
            jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdutoLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanelProdutoLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelAddProdutos))
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldCPF2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addGroup(jPanelProdutoLayout.createSequentialGroup()
                        .addComponent(jTextFieldNome2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelAddCliente))
                    .addGroup(jPanelProdutoLayout.createSequentialGroup()
                        .addComponent(jRadioButtonCliente)
                        .addGap(9, 9, 9)
                        .addComponent(jRadioButtonCabelereiro)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProdutoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProdutoLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldTotalBruto2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProdutoLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(37, 37, 37)
                                .addComponent(jTextFieldDesconto2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jCheckBoxDesconto2, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanelProdutoLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jButtonFinalizarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanelProdutoLayout.setVerticalGroup(
            jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdutoLayout.createSequentialGroup()
                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProdutoLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jCheckBoxDesconto2)
                        .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jTextFieldTotalBruto2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jTextFieldDesconto2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jTextFieldTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(93, 93, 93)
                                .addComponent(jButtonFinalizarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jRadioButtonCliente)
                                    .addComponent(jRadioButtonCabelereiro))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldNome2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelAddCliente))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldCPF2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelAddProdutos)))))
                    .addGroup(jPanelProdutoLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanelProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jPanelProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

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

    private void jButtonFinalizarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarCompraActionPerformed
       
        boolean sucesso;

       
            CompraController cc = new CompraController();
            VendaController vc = new VendaController();
            CabeleireiroController cabc = new CabeleireiroController();
            boolean isClienteComprando = jRadioButtonCliente.isSelected();
            String cpf = "";

            //retorna o CPF de acordo com o RadioButton
            if (isClienteComprando) {
                cpf = jTextFieldCPF2.getText();
                 sucesso = vc.RegistraVenda(
                        LocalDate.now(),
                        Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldDesconto2.getText())),
                        cpf,
                        new RecuperaTabela().recuperaItensCompra(jTableProdutosComprados));
            } else {
                    System.out.println("chegou");
                cpf = cabc.selecionaCabeleireiro().getCpf();
                sucesso = cc.RegistraCompra(
                    LocalDate.now(),
                    Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldDesconto2.getText())),
                    cpf,
                    new RecuperaTabela().recuperaItensCompra(jTableProdutosComprados));
            }
            
            if (sucesso) {
                if (isClienteComprando) {
                    JOptionPane.showMessageDialog(null, "Venda registrada com sucesso.");            
                } else {
                    JOptionPane.showMessageDialog(null, "Compra registrada com sucesso.");
                }

                limparTodosCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao registrar venda");
            }

 
    }//GEN-LAST:event_jButtonFinalizarCompraActionPerformed

    private void jButtonFinalizarCompraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonFinalizarCompraMousePressed

    }//GEN-LAST:event_jButtonFinalizarCompraMousePressed

    private void jCheckBoxDesconto2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxDesconto2MousePressed

        if (!jCheckBoxDesconto2.isSelected()) {
            jCheckBoxDesconto2.setSelected(true);
            modalInputMonetarios modalMonetarario = new modalInputMonetarios("Insira o valor do desconto");
            modalMonetarario.registrarObservador(this);
            modalMonetarario.setVisible(true);
        }
    }//GEN-LAST:event_jCheckBoxDesconto2MousePressed

    private void jLabelAddProdutosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddProdutosMousePressed

        modalProdutos modal;

        //diferencia se quem comprou foi o cliente ou o cabeleireiro, para aplicar lógicas no modal.
        if (jRadioButtonCabelereiro.isSelected()) {
            modal = new modalProdutos(true);
        } else {
            modal = new modalProdutos(false);
        }
        modal.registrarObservador(this);
        modal.setVisible(true);
    }//GEN-LAST:event_jLabelAddProdutosMousePressed

    private void jLabelAddClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddClienteMousePressed

        if (jRadioButtonCliente.isSelected()) {
            modalCliente modal = new modalCliente();
            modal.registrarObservador(this);
            modal.setVisible(true);
        }
    }//GEN-LAST:event_jLabelAddClienteMousePressed

    private void jRadioButtonClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonClienteActionPerformed
        if (jRadioButtonCliente.isSelected()) {
            jTextFieldNome2.setEnabled(true);
            jTextFieldCPF2.setEnabled(true);
            jLabelAddCliente.setEnabled(true);
            jTextFieldNome2.setEditable(false);
            jTextFieldCPF2.setEditable(false);
            limparTodosCampos();
        }
    }//GEN-LAST:event_jRadioButtonClienteActionPerformed

    private void jRadioButtonCabelereiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCabelereiroActionPerformed
        if (jRadioButtonCabelereiro.isSelected()) {
            jTextFieldNome2.setEnabled(false);
            jTextFieldCPF2.setEnabled(false);
            jLabelAddCliente.setEnabled(false);
            limparTodosCampos();
        }
    }//GEN-LAST:event_jRadioButtonCabelereiroActionPerformed

    void limparTodosCampos() {
        jTextFieldCPF2.setText("");
        jTextFieldDesconto2.setText("-R$ 0,00");
        jTextFieldNome2.setText("");
        jTextFieldTotal2.setText("");
        jTextFieldTotalBruto2.setText("");

        //limpar tabela
        try {
            DefaultTableModel model = (DefaultTableModel) jTableProdutosComprados.getModel();
            model.setRowCount(0);
            jTableProdutosComprados.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao limpar tabela " + e);
        }

    }

    private void calculaTotalBruto() {

        ArrayList<Item> produtos = new RecuperaTabela().recuperaItensCompra(jTableProdutosComprados);
        long total = 0;

        try {
            for (Item prod : produtos) {
                total += prod.getPrecoTotal();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }

        jTextFieldTotalBruto2.setText(Dinheiro.parseString(total));

    }

    private void calculaTotal() {

        try {

            long valorDesconto = 0;

            if (!jTextFieldDesconto2.getText().equals("")) {
                valorDesconto = Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldDesconto2.getText()));
            }

            long valorTotalBruto = Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldTotalBruto2.getText()));
            long valorTotal = valorTotalBruto - valorDesconto;
            jTextFieldTotal2.setText(Dinheiro.parseString(valorTotal));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao calcular total " + e);
        }

    }

    @Override
    public void update(DefaultTableModel model) {

        try {
            jTableProdutosComprados.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao converter ModelTabela");
        }
        calculaTotalBruto();
        calculaTotal();

    }

    @Override
    public void update(String valorDesconto) {
        jTextFieldDesconto2.setText("-" + Dinheiro.parseString(Dinheiro.retiraCaracteres(valorDesconto)));
        calculaTotal();
        jCheckBoxDesconto2.setSelected(true);

    }

    @Override
    public void update(Cliente cliente) {

        //int tabSelecionada = jTabbedPane.getSelectedIndex();
        //se for compra de produto
        // if (tabSelecionada == 2) {} 
        Cliente clienteSelecionado = cliente;
        jTextFieldNome2.setText(clienteSelecionado.getNome());
        jTextFieldCPF2.setText(clienteSelecionado.getCpf());

    }

    @Override
    public void update(Object obj) {

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
            java.util.logging.Logger.getLogger(CadastroFluxoDeCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroFluxoDeCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroFluxoDeCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroFluxoDeCaixa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroFluxoDeCaixa().setVisible(true);
            }
        });
    }

    @Override
    public void update(Servico servico) {
    }

    @Override
    public void update(Orcamento orcamento) {
      
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupCompraProduto;
    private javax.swing.JButton jButtonFinalizarCompra;
    private javax.swing.JCheckBox jCheckBoxDesconto2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAddCliente;
    private javax.swing.JLabel jLabelAddProdutos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelProduto;
    private javax.swing.JRadioButton jRadioButtonCabelereiro;
    private javax.swing.JRadioButton jRadioButtonCliente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableProdutosComprados;
    private javax.swing.JTextField jTextFieldCPF2;
    private javax.swing.JTextField jTextFieldDesconto2;
    private javax.swing.JTextField jTextFieldNome2;
    private javax.swing.JTextField jTextFieldTotal2;
    private javax.swing.JTextField jTextFieldTotalBruto2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(ArrayList<LocalTime> horarios) {
    }

}
