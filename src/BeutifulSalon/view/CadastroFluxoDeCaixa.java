/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.view;

import BeutifulSalon.controller.CompraController;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.ItemCompra;
import BeutifulSalon.model.Observador;
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

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanelProduto = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldCPF = new javax.swing.JTextField();
        jLabelAddCliente = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProdutosComprados = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldTotalBruto = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jTextFieldDesconto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jTextFieldTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jCheckBoxDesconto = new javax.swing.JCheckBox();
        jButtonFinalizarCompra = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(243, 244, 245));

        jTabbedPane2.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jLabel2.setText("teste3");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(jLabel2)
                .addContainerGap(664, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jLabel2)
                .addContainerGap(307, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("tab2", jPanel3);

        jLabel1.setText("teste");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(jLabel1)
                .addContainerGap(583, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jLabel1)
                .addContainerGap(305, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("tab3", jPanel4);

        jLabel4.setText("Nome Cliente");

        jLabel5.setText("CPF");

        jLabelAddCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/adicionar-icon.png"))); // NOI18N
        jLabelAddCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddClienteMousePressed(evt);
            }
        });

        jLabel3.setText("Produtos Comprados");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/adicionar-icon.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });

        jTableProdutosComprados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
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

        jTextFieldTotalBruto.setEditable(false);
        jTextFieldTotalBruto.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldTotalBruto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldTotalBruto.setForeground(new java.awt.Color(34, 34, 34));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jTextFieldDesconto.setEditable(false);
        jTextFieldDesconto.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldDesconto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldDesconto.setForeground(new java.awt.Color(34, 34, 34));
        jTextFieldDesconto.setText("-R$ 0,00");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Valor Desconto:");

        jTextFieldTotal.setEditable(false);
        jTextFieldTotal.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldTotal.setForeground(new java.awt.Color(34, 34, 34));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("TOTAL");

        jCheckBoxDesconto.setBackground(new java.awt.Color(243, 244, 245));
        jCheckBoxDesconto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBoxDesconto.setForeground(new java.awt.Color(34, 34, 34));
        jCheckBoxDesconto.setText("Desconto");
        jCheckBoxDesconto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jCheckBoxDescontoMousePressed(evt);
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

        javax.swing.GroupLayout jPanelProdutoLayout = new javax.swing.GroupLayout(jPanelProduto);
        jPanelProduto.setLayout(jPanelProdutoLayout);
        jPanelProdutoLayout.setHorizontalGroup(
            jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProdutoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanelProdutoLayout.createSequentialGroup()
                        .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelAddCliente))
                    .addGroup(jPanelProdutoLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                .addGap(26, 26, 26)
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
                                        .addComponent(jTextFieldDesconto)
                                        .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                            .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))))
                                .addGroup(jPanelProdutoLayout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldTotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelProdutoLayout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addComponent(jButtonFinalizarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addGap(41, 41, 41)))
                    .addGroup(jPanelProdutoLayout.createSequentialGroup()
                        .addComponent(jCheckBoxDesconto)
                        .addGap(177, 177, 177))))
        );
        jPanelProdutoLayout.setVerticalGroup(
            jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelProdutoLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jSeparator1)
                .addGap(160, 160, 160))
            .addGroup(jPanelProdutoLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel4)
                .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelProdutoLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelAddCliente))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanelProdutoLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jCheckBoxDesconto)
                        .addGap(49, 49, 49)
                        .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextFieldTotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonFinalizarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58))
        );

        jTabbedPane2.addTab("Compra Produto", jPanelProduto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        jTabbedPane2.getAccessibleContext().setAccessibleName("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelAddClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddClienteMousePressed

        modalCliente modal = new modalCliente();
        modal.registrarObservador(this);
        modal.setVisible(true);
    }//GEN-LAST:event_jLabelAddClienteMousePressed

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
        modalProdutos modalProduto = new modalProdutos();
        modalProduto.registrarObservador(this);
        modalProduto.setVisible(true);
    }//GEN-LAST:event_jLabel6MousePressed

    private void jCheckBoxDescontoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxDescontoMousePressed

        if (jCheckBoxDesconto.isSelected()) {
            jCheckBoxDesconto.setSelected(false);
        } else {
            jCheckBoxDesconto.setSelected(true);
        }

        if (jCheckBoxDesconto.isSelected()) {

            String desconto = JOptionPane.showInputDialog("Insira o valor do desconto em R$: ");
            jCheckBoxDesconto.setSelected(true);
            jTextFieldDesconto.setText("- " + Dinheiro.parseString(Dinheiro.retiraCaracteres(desconto)));

            if (!jTextFieldTotalBruto.getText().equals("")) {
                calculaTotal();
            }

        } else {
            jTextFieldDesconto.setText("R$ -0,00");
        }
    }//GEN-LAST:event_jCheckBoxDescontoMousePressed

    private void jButtonFinalizarCompraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonFinalizarCompraMousePressed


    }//GEN-LAST:event_jButtonFinalizarCompraMousePressed

    void limparTodosCampos() {
        jTextFieldCPF.setText("");
        jTextFieldDesconto.setText("-R$ 0,00");
        jTextFieldNome.setText("");
        jTextFieldTotal.setText("");
        jTextFieldTotalBruto.setText("");
        jTableProdutosComprados.removeAll();
    }
    private void jButtonFinalizarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarCompraActionPerformed
        boolean sucesso;

        try {
            CompraController cc = new CompraController();

            sucesso = cc.RegistraCompra(new Date(),
                    Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldDesconto.getText())),
                    jTextFieldCPF.getText(),
                    recuperaProdutosComprados());

            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Venda registrada com sucesso.");
                limparTodosCampos();
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao registrar venda");
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }//GEN-LAST:event_jButtonFinalizarCompraActionPerformed

    private ArrayList<ItemCompra> recuperaProdutosComprados() {

        ArrayList<ItemCompra> produtos = new ArrayList<>();

        for (int i = 0; i < jTableProdutosComprados.getRowCount(); i++) {
            ItemCompra produtoAtual = new ItemCompra();
            for (int j = 0; j < jTableProdutosComprados.getColumnCount(); j++) {

                if (j == 0) {
                    produtoAtual.setNome(jTableProdutosComprados.getValueAt(i, j).toString());
                } else if (j == 1) {
                    produtoAtual.setMarca(jTableProdutosComprados.getValueAt(i, j).toString());
                } else if (j == 2) {
                    produtoAtual.setPreco(Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTableProdutosComprados.getValueAt(i, j).toString())));
                } else if (j == 3) {
                    produtoAtual.setQuantidade(Integer.parseInt(jTableProdutosComprados.getValueAt(i, j).toString()));
                } else {
                    produtoAtual.setId_produto(Long.parseLong(jTableProdutosComprados.getValueAt(i, j).toString()));
                }
            }
            produtos.add(produtoAtual);
        }

        return produtos;

    }

    private void calculaTotalBruto() {

        ArrayList<ItemCompra> produtos = recuperaProdutosComprados();
        long total = 0;

        try {
            for (ItemCompra prod : produtos) {
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

            if (!jTextFieldDesconto.getText().equals("")) {
                valorDesconto = Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldDesconto.getText()));
            }

            long valorTotalBruto = Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldTotalBruto.getText()));
            long valorTotal = valorTotalBruto - valorDesconto;
            jTextFieldTotal.setText(Dinheiro.parseString(valorTotal));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao calcular total " + e);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFinalizarCompra;
    private javax.swing.JCheckBox jCheckBoxDesconto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAddCliente;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelProduto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTableProdutosComprados;
    private javax.swing.JTextField jTextFieldCPF;
    private javax.swing.JTextField jTextFieldDesconto;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldTotal;
    private javax.swing.JTextField jTextFieldTotalBruto;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Object obj) {

        if (obj instanceof Cliente) {
            Cliente cliente = (Cliente) obj;
            jTextFieldNome.setText(cliente.getNOME());
            jTextFieldCPF.setText(cliente.getCPF());

        } else {

            try {
                jTableProdutosComprados.setModel((DefaultTableModel) obj);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao converter MOdelTabela");
            }
            calculaTotalBruto();
            calculaTotal();

        }

    }

}
