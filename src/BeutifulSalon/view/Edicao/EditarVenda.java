/*
 * The MIT License
 *
 * Copyright 2021 Mateus.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package BeutifulSalon.view.Edicao;

import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.Ferramentas.ManipulaFontes;
import BeutifulSalon.Ferramentas.RecuperaTabela;
import BeutifulSalon.controller.ClienteController;
import BeutifulSalon.controller.VendaController;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Compra;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Item;
import BeutifulSalon.model.Observador;
import BeutifulSalon.model.Orcamento;
import BeutifulSalon.model.Servico;
import BeutifulSalon.model.Venda;
import BeutifulSalon.view.modais.ModalInputMonetarios;
import BeutifulSalon.view.modais.ModalProdutos;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mateus
 */
public class EditarVenda extends javax.swing.JFrame implements Observador {

    /**
     * Creates new form EditarVenda
     */
    private ModalInputMonetarios modalInputMonetarios;
    private long idVenda;
    private Cliente cliente;
    private List<Item> itensAntigos;

    public EditarVenda() {
        initComponents();
    }

    public EditarVenda(Venda v, List<Item> itensAntigos) {
        initComponents();
        
        ManipulaFontes mf = new ManipulaFontes();

        jLabel9.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 35f)); 
        jLabel4.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jTextFieldNomeCliente.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jLabel10.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jDateChooser1.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jLabel7.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f));
        jLabel8.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f));
        jLabel12.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f));
        jTextFieldTotalBruto.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f));
        jCheckBoxDesconto.setFont(mf.getFont(mf.LIGHT, Font.PLAIN, 15f));
        jTextFieldDesconto.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f));
        jTextFieldTotal.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f));
        jCheckBoxDesconto.setFont(mf.getFont(mf.LIGHT, Font.BOLD, 15f));
        
        jButtonAtualizar.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 15f));
        jLabel3.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jTableProdutosComprados.setFont(mf.getFont(mf.SEMIBOLD, Font.PLAIN, 15f));

        this.idVenda = v.getIdVenda();
        this.itensAntigos = itensAntigos;
        cliente = new ClienteController().buscarCliente(v.getIdCliente());
        jTextFieldTotalBruto.setText(Dinheiro.parseString(v.getTotal()));

        if (v.getValorDesconto() > 0) {
            jTextFieldDesconto.setText(Dinheiro.parseString(v.getValorDesconto()));
            jCheckBoxDesconto.setSelected(true);
        }

        jTextFieldTotal.setText(Dinheiro.parseString(v.getTotal() - v.getValorDesconto()));
        jTextFieldNomeCliente.setText(cliente.getNomeCompleto());
        jDateChooser1.setDate(new ManipulaData().localDateToDate(v.getData()));

        DefaultTableModel modelo = (DefaultTableModel) jTableProdutosComprados.getModel();
        modelo.setRowCount(0);
        List<Item> produtosComprados = v.getItensVenda();

        for (Item i : produtosComprados) {
            modelo.addRow(new Object[]{
                i.getNome(),
                i.getMarca(),
                Dinheiro.parseString(i.getPreco()),
                i.getQuantidade(),
                i.getId_produto()
            });

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

        jPanel1 = new javax.swing.JPanel();
        jCheckBoxDesconto = new javax.swing.JCheckBox();
        jTextFieldTotalBruto = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabelAddProdutos = new javax.swing.JLabel();
        jTextFieldDesconto = new javax.swing.JTextField();
        jTextFieldNomeCliente = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProdutosComprados = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButtonAtualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jCheckBoxDesconto.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBoxDesconto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jCheckBoxDesconto.setForeground(new java.awt.Color(34, 34, 34));
        jCheckBoxDesconto.setText("Desconto");
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

        jTextFieldTotalBruto.setEditable(false);
        jTextFieldTotalBruto.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldTotalBruto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldTotalBruto.setForeground(new java.awt.Color(34, 34, 34));
        jTextFieldTotalBruto.setText("R$ 0,00");
        jTextFieldTotalBruto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTotalBrutoActionPerformed(evt);
            }
        });

        jDateChooser1.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser1.setForeground(new java.awt.Color(255, 255, 255));
        jDateChooser1.setDateFormatString("dd/MM/yyyy");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Produtos Comprados");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Nome do Cliente");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Total Bruto: ");

        jLabelAddProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-add.png"))); // NOI18N
        jLabelAddProdutos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddProdutosMousePressed(evt);
            }
        });

        jTextFieldDesconto.setEditable(false);
        jTextFieldDesconto.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldDesconto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldDesconto.setForeground(new java.awt.Color(34, 34, 34));
        jTextFieldDesconto.setText("-R$ 0,00");

        jTextFieldNomeCliente.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Data");

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

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("TOTAL");

        jTextFieldTotal.setEditable(false);
        jTextFieldTotal.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextFieldTotal.setForeground(new java.awt.Color(34, 34, 34));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Valor Desconto:");

        jPanel3.setBackground(new java.awt.Color(36, 46, 65));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Editar Venda");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-financas-48.png"))); // NOI18N
        jLabel11.setText("jLabel11");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jButtonAtualizar.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAtualizar.setText("Atualizar");
        jButtonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(110, 110, 110))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelAddProdutos))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jCheckBoxDesconto)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel7))
                                        .addGap(20, 20, 20)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jTextFieldTotalBruto)
                                                .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jButtonAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(55, 55, 55))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap())))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBoxDesconto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldTotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jButtonAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAddProdutos)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelAddProdutosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddProdutosMousePressed

        ModalProdutos modal;

        //diferencia se quem comprou foi o cliente ou o cabeleireiro, para aplicar lógicas no modal.
        modal = new ModalProdutos(false);
        modal.registrarObservador(this);
        modal.setVisible(true);
    }//GEN-LAST:event_jLabelAddProdutosMousePressed

    private void jCheckBoxDescontoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxDescontoMousePressed

        if (!jCheckBoxDesconto.isSelected()) {
            jCheckBoxDesconto.setSelected(true);

            if (modalInputMonetarios == null) {
                modalInputMonetarios = new ModalInputMonetarios("Insira o valor do desconto");
                modalInputMonetarios.registrarObservador(this);
                modalInputMonetarios.setVisible(true);
            } else {
                modalInputMonetarios.setVisible(true);
            }

        } else {
            jTextFieldDesconto.setText("R$ 0,00");
            calculaTotal();
        }
    }//GEN-LAST:event_jCheckBoxDescontoMousePressed

    private void jTextFieldTotalBrutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTotalBrutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTotalBrutoActionPerformed

    private void jButtonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizarActionPerformed

        Venda v = new Venda();

        v.setIdCliente(cliente.getId());
        v.setNomeCliente(cliente.getNomeCompleto());
        v.setIdVenda(idVenda);
        v.setValorDesconto(Dinheiro.parseCent(Dinheiro.retiraCaracteres(jTextFieldDesconto.getText())));
        v.setItensVenda(new RecuperaTabela().recuperaItensCompra(jTableProdutosComprados));

        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/M/uuuu");
        String dataFormatada = "";
        try {
            dataFormatada = formater.format(jDateChooser1.getDate());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao converter data");
        }
        v.setData(LocalDate.parse(dataFormatada, formatterData));

        if (new VendaController().atualizarVenda(v, itensAntigos)) {
            JOptionPane.showMessageDialog(null, "Venda atualizada com sucesso!");
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar Venda. Verifique os dados inseridos e tente novamente.");
        }


    }//GEN-LAST:event_jButtonAtualizarActionPerformed

    private void jCheckBoxDescontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxDescontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxDescontoActionPerformed

    void limparTodosCampos() {
        jTextFieldDesconto.setText("-R$ 0,00");
        jTextFieldNomeCliente.setText("");
        jTextFieldTotal.setText("");
        jTextFieldTotalBruto.setText("");

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
            java.util.logging.Logger.getLogger(EditarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditarVenda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAtualizar;
    private javax.swing.JCheckBox jCheckBoxDesconto;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAddProdutos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProdutosComprados;
    private javax.swing.JTextField jTextFieldDesconto;
    private javax.swing.JTextField jTextFieldNomeCliente;
    private javax.swing.JTextField jTextFieldTotal;
    private javax.swing.JTextField jTextFieldTotalBruto;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Object obj) {
    }

    @Override
    public void update(Orcamento orcamento) {
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
        jTextFieldDesconto.setText("-" + Dinheiro.parseString(Dinheiro.retiraCaracteres(valorDesconto)));
        calculaTotal();
        jCheckBoxDesconto.setSelected(true);
    }

    @Override
    public void update(Cliente cliente) {
    }

    @Override
    public void update(Servico servico) {
    }

    @Override
    public void update(ArrayList<LocalTime> horarios) {
    }
}
