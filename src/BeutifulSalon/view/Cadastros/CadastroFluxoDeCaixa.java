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
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.ItemCompra;
import BeutifulSalon.model.Observador;
import BeutifulSalon.model.Servico;
import BeutifulSalon.view.modais.modalInputMonetarios;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Date;
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
        jTabbedPane = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanelProduto = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNome2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldCPF2 = new javax.swing.JTextField();
        jLabelAddCliente = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProdutosComprados = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldTotalBruto2 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jTextFieldDesconto2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jTextFieldTotal2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jCheckBoxDesconto2 = new javax.swing.JCheckBox();
        jButtonFinalizarCompra = new javax.swing.JButton();
        jRadioButtonCliente = new javax.swing.JRadioButton();
        jRadioButtonCabelereiro = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(243, 244, 245));
        setResizable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1006, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 568, Short.MAX_VALUE)
        );

        jTabbedPane.addTab("Orçamento", jPanel3);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Nome do Cliente");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("CPF");

        jLabelAddCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-add.png"))); // NOI18N
        jLabelAddCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddClienteMousePressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Produtos Comprados");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-add.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
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

        jButtonFinalizarCompra.setBackground(new java.awt.Color(57, 201, 114));
        jButtonFinalizarCompra.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonFinalizarCompra.setForeground(new java.awt.Color(255, 255, 255));
        jButtonFinalizarCompra.setText("Finalizar Compra");
        jButtonFinalizarCompra.setBorder(null);
        jButtonFinalizarCompra.setBorderPainted(false);
        jButtonFinalizarCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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

        buttonGroupCompraProduto.add(jRadioButtonCliente);
        jRadioButtonCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButtonCliente.setSelected(true);
        jRadioButtonCliente.setText("Cliente");
        jRadioButtonCliente.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonClienteStateChanged(evt);
            }
        });

        buttonGroupCompraProduto.add(jRadioButtonCabelereiro);
        jRadioButtonCabelereiro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jRadioButtonCabelereiro.setText("Cabelereiro");
        jRadioButtonCabelereiro.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioButtonCabelereiroStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanelProdutoLayout = new javax.swing.GroupLayout(jPanelProduto);
        jPanelProduto.setLayout(jPanelProdutoLayout);
        jPanelProdutoLayout.setHorizontalGroup(
            jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdutoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldCPF2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelProdutoLayout.createSequentialGroup()
                        .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                .addComponent(jRadioButtonCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButtonCabelereiro))
                            .addComponent(jLabel4)
                            .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                .addComponent(jTextFieldNome2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelAddCliente))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)))
                .addGap(67, 67, 67)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProdutoLayout.createSequentialGroup()
                            .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                    .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                            .addGap(16, 16, 16)
                                            .addComponent(jLabel9)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldDesconto2)
                                        .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                            .addComponent(jTextFieldTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))))
                                .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldTotalBruto2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelProdutoLayout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addComponent(jButtonFinalizarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addGap(41, 41, 41)))
                    .addGroup(jPanelProdutoLayout.createSequentialGroup()
                        .addComponent(jCheckBoxDesconto2)
                        .addGap(244, 244, 244))))
        );
        jPanelProdutoLayout.setVerticalGroup(
            jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdutoLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProdutoLayout.createSequentialGroup()
                        .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jCheckBoxDesconto2)
                                .addGap(18, 18, 18)
                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jTextFieldTotalBruto2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(jTextFieldDesconto2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldTotal2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonFinalizarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jRadioButtonCliente)
                                    .addComponent(jRadioButtonCabelereiro))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelAddCliente)
                                    .addComponent(jTextFieldNome2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCPF2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(58, 58, 58))
                    .addGroup(jPanelProdutoLayout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addGap(160, 160, 160))))
        );

        jTabbedPane.addTab("Compra Produto", jPanelProduto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane)
        );

        jTabbedPane.getAccessibleContext().setAccessibleName("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonCabelereiroStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonCabelereiroStateChanged

        if (jRadioButtonCabelereiro.isSelected()) {
            jTextFieldNome2.setEnabled(false);
            jTextFieldCPF2.setEnabled(false);
            jLabelAddCliente.setEnabled(false);
            jTextFieldNome2.setText("");
            jTextFieldCPF2.setText("");
        }
    }//GEN-LAST:event_jRadioButtonCabelereiroStateChanged

    private void jRadioButtonClienteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioButtonClienteStateChanged

        if (jRadioButtonCliente.isSelected()) {
            jTextFieldNome2.setEnabled(true);
            jTextFieldCPF2.setEnabled(true);
            jLabelAddCliente.setEnabled(true);

        }
    }//GEN-LAST:event_jRadioButtonClienteStateChanged

    private void jButtonFinalizarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarCompraActionPerformed
        boolean sucesso;

        try {
            CompraController cc = new CompraController();
            String cpf = "";

            //retorna o CPF de acordo com o RadioButton
            if (jRadioButtonCliente.isSelected()) {
                cpf = jTextFieldCPF2.getText();
            } else {
                CabeleireiroController cabc = new CabeleireiroController();
                cpf = cabc.selecionaCabeleireiro().getCpf();
            }

            sucesso = cc.RegistraCompra(new Date(),
                    Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldDesconto2.getText())),
                    cpf,
                    new RecuperaTabela().recuperaItensCompra(jTableProdutosComprados));

            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Venda registrada com sucesso.");
                limparTodosCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao registrar venda");
            }

        } catch (ExceptionDAO | HeadlessException e) {

            JOptionPane.showMessageDialog(null, "Erro: " + e);
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

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed

        modalProdutos modal;

        //diferencia se quem comprou foi o cliente ou o cabeleireiro, para aplicar lógicas no modal.
        if (jRadioButtonCabelereiro.isSelected()) {
            modal = new modalProdutos(true);
        } else {
            modal = new modalProdutos(false);
        }
        modal.registrarObservador(this);
        modal.setVisible(true);
    }//GEN-LAST:event_jLabel6MousePressed

    private void jLabelAddClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddClienteMousePressed

        if (jRadioButtonCliente.isSelected()) {
            modalCliente modal = new modalCliente();
            modal.registrarObservador(this);
            modal.setVisible(true);
        }
    }//GEN-LAST:event_jLabelAddClienteMousePressed

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

        ArrayList<ItemCompra> produtos = new RecuperaTabela().recuperaItensCompra(jTableProdutosComprados);
        long total = 0;

        try {
            for (ItemCompra prod : produtos) {
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
        jTextFieldNome2.setText(clienteSelecionado.getNOME());
        jTextFieldCPF2.setText(clienteSelecionado.getCPF());

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupCompraProduto;
    private javax.swing.JButton jButtonFinalizarCompra;
    private javax.swing.JCheckBox jCheckBoxDesconto2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAddCliente;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelProduto;
    private javax.swing.JRadioButton jRadioButtonCabelereiro;
    private javax.swing.JRadioButton jRadioButtonCliente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTableProdutosComprados;
    private javax.swing.JTextField jTextFieldCPF2;
    private javax.swing.JTextField jTextFieldDesconto2;
    private javax.swing.JTextField jTextFieldNome2;
    private javax.swing.JTextField jTextFieldTotal2;
    private javax.swing.JTextField jTextFieldTotalBruto2;
    // End of variables declaration//GEN-END:variables

}
