/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.view.Cadastros;

import BeutifulSalon.controller.DespesaController;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Observador;
import BeutifulSalon.model.Orcamento;
import BeutifulSalon.model.Servico;
import BeutifulSalon.view.modais.modalOrcamentoPrevisto;
import java.awt.Color;
import java.awt.Dialog;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Mateus
 */
public class CadastroDespesa extends javax.swing.JFrame implements Observador {

    /**
     * Creates new form CadastroDespesa
     */
    private modalOrcamentoPrevisto modal = null;

    public CadastroDespesa() {
        initComponents();
        DecimalFormat decimal = new DecimalFormat("#,###,###.00");
        NumberFormatter numFormatter = new NumberFormatter(decimal);
        numFormatter.setFormat(decimal);
        numFormatter.setAllowsInvalid(false);
        DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(numFormatter);
        jFormattedTextFieldValorPago.setFormatterFactory(dfFactory);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldNomeDespesa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldIdDespesa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDateChooserLacamento = new com.toedter.calendar.JDateChooser();
        jDateChooserVencimento = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jComboBoxFormaPagamento = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jFormattedTextFieldValorPago = new javax.swing.JFormattedTextField();
        jDateChooserDataPagamento = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaAnotacao = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jButtonCadastrar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jToggleButton = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Nome da Despesa");

        jTextFieldNomeDespesa.setEditable(false);
        jTextFieldNomeDespesa.setForeground(new java.awt.Color(0, 0, 0));
        jTextFieldNomeDespesa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldNomeDespesa.setEnabled(false);

        jLabel2.setText("ID");

        jTextFieldIdDespesa.setEditable(false);
        jTextFieldIdDespesa.setForeground(new java.awt.Color(0, 0, 0));
        jTextFieldIdDespesa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldIdDespesa.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldIdDespesa.setEnabled(false);
        jTextFieldIdDespesa.setFocusable(false);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-add.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        jLabel4.setText("Data de Lançamento");

        jDateChooserLacamento.setDateFormatString("dd/MM/yyyy");

        jDateChooserVencimento.setDateFormatString("dd/MM/yyyy");

        jLabel5.setText("Data de Vencimento");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBoxFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Boleto", "Crédito", "Débito", "Dinheiro" }));
        jComboBoxFormaPagamento.setEnabled(false);
        jComboBoxFormaPagamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jComboBoxFormaPagamentoMousePressed(evt);
            }
        });

        jLabel6.setText("Forma de Pagamento");

        jLabel7.setText("Data de pagamento");

        jLabel8.setText("Valor Pago");

        jFormattedTextFieldValorPago.setEnabled(false);

        jDateChooserDataPagamento.setDateFormatString("dd/MM/yyyy");
        jDateChooserDataPagamento.setEnabled(false);

        jTextAreaAnotacao.setColumns(20);
        jTextAreaAnotacao.setLineWrap(true);
        jTextAreaAnotacao.setRows(5);
        jTextAreaAnotacao.setEnabled(false);
        jScrollPane1.setViewportView(jTextAreaAnotacao);

        jLabel10.setText("Anotação/ Lembrete");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8)
                        .addComponent(jLabel7)
                        .addComponent(jLabel6)
                        .addComponent(jComboBoxFormaPagamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jFormattedTextFieldValorPago)
                        .addComponent(jDateChooserDataPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDateChooserDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextFieldValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jButtonCadastrar.setText("Confirmar");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel9.setText("Pagamento de Despesa");

        jToggleButton.setBackground(new java.awt.Color(248, 67, 69));
        jToggleButton.setForeground(new java.awt.Color(0, 0, 0));
        jToggleButton.setText("Pagamento Pendente");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(jLabel2)
                                        .addGap(45, 45, 45))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jTextFieldIdDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextFieldNomeDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3))))
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jDateChooserVencimento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                .addComponent(jDateChooserLacamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButtonCadastrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(519, 519, 519)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToggleButton)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel9)
                .addGap(19, 19, 19)
                .addComponent(jToggleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldNomeDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldIdDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserLacamento, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed

        if (modal == null) {
            modal = new modalOrcamentoPrevisto();
            modal.registrarObservador(this);
            modal.setVisible(true);
        } else {
            modal.setVisible(true);
        }
    }//GEN-LAST:event_jLabel3MousePressed

    private void jToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonActionPerformed

    }//GEN-LAST:event_jToggleButtonActionPerformed

    private void jToggleButtonPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jToggleButtonPropertyChange


    }//GEN-LAST:event_jToggleButtonPropertyChange

    private void jToggleButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButtonMousePressed


    }//GEN-LAST:event_jToggleButtonMousePressed

    private void jToggleButtonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jToggleButtonStateChanged
 
        if (jToggleButton.isSelected()) {
            jToggleButton.setText("Pagamento Realizado");
            jTextAreaAnotacao.setEnabled(true);
            jDateChooserDataPagamento.setEnabled(true);
            jFormattedTextFieldValorPago.setEnabled(true);
            jComboBoxFormaPagamento.setEnabled(true);
            Color verde = new Color(57, 201, 114);
            jToggleButton.setBackground(verde);
        } else {
            jToggleButton.setText("Pagamento Pendente");
            jTextAreaAnotacao.setEnabled(false);
            jDateChooserDataPagamento.setEnabled(false);
            jFormattedTextFieldValorPago.setEnabled(false);
            jComboBoxFormaPagamento.setEnabled(false);
            Color vermelho = new Color(248, 67, 69);
            jToggleButton.setBackground(vermelho);
        }
    }//GEN-LAST:event_jToggleButtonStateChanged

    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed

        boolean sucesso = false;
        DespesaController dc = new DespesaController();
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
        int ano = LocalDate.now().getYear();

        if (jToggleButton.isSelected()) {

            long idOrcamento = 0;
            String dataLancamento = "";
            String dataVencimento = "";
            String dataPagamento = "";
            try {
                dataLancamento = formater.format(jDateChooserLacamento.getDate());
                dataVencimento = formater.format(jDateChooserVencimento.getDate());
                dataPagamento = formater.format(jDateChooserDataPagamento.getDate());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao converter data");
            }

            try {
                idOrcamento = Long.parseLong(jTextFieldIdDespesa.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Selecione uma despesa antes.");
            }

            //true pois está sendo registrado um pagamento
            if (!dc.verificaExistenciaPagamento(idOrcamento, dataPagamento, true)) {
                sucesso = dc.CadastrarDespesa(idOrcamento,
                        dataLancamento,
                        dataVencimento,
                        dataPagamento,
                        jFormattedTextFieldValorPago.getText(),
                        jTextAreaAnotacao.getText(),
                        String.valueOf(ano),
                        String.valueOf(jComboBoxFormaPagamento.getSelectedItem()),
                        true
                );
                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Despesa registrada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao registrar despesa.");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Já existe um registro de pagamento desta despesa para o mês informado.");
            }

        } else {

            ano = LocalDate.now().getYear();
            long idOrcamento = 0;
            String dataLancamento = "";
            String dataVencimento = "";
            try {
                dataLancamento = formater.format(jDateChooserLacamento.getDate());
                dataVencimento = formater.format(jDateChooserVencimento.getDate());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao converter data");
            }

            try {
                idOrcamento = Long.parseLong(jTextFieldIdDespesa.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Selecione uma despesa antes.");
            }

            //false pois está sendo registrado apenas lançamento e vencimento
            if (!dc.verificaExistenciaPagamento(idOrcamento, dataLancamento, false)) {
                sucesso = dc.CadastrarDespesa(idOrcamento,
                        dataLancamento,
                        dataVencimento,
                        null,
                        null,
                        null,
                        String.valueOf(ano),
                        null,
                        false
                );

                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Despesa registrada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao registrar despesa.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Já existe um registro de desta despesa para o mês informado. Realize o pagamento!");
            }

        }
    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jComboBoxFormaPagamentoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBoxFormaPagamentoMousePressed

    }//GEN-LAST:event_jComboBoxFormaPagamentoMousePressed

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
            java.util.logging.Logger.getLogger(CadastroDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroDespesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroDespesa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JComboBox<String> jComboBoxFormaPagamento;
    private com.toedter.calendar.JDateChooser jDateChooserDataPagamento;
    private com.toedter.calendar.JDateChooser jDateChooserLacamento;
    private com.toedter.calendar.JDateChooser jDateChooserVencimento;
    private javax.swing.JFormattedTextField jFormattedTextFieldValorPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaAnotacao;
    private javax.swing.JTextField jTextFieldIdDespesa;
    private javax.swing.JTextField jTextFieldNomeDespesa;
    private javax.swing.JToggleButton jToggleButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Object obj) {
    }

    @Override
    public void update(Orcamento orcamento) {

        if (orcamento != null) {
            jTextFieldIdDespesa.setText(String.valueOf(orcamento.getId_orcamento()));
            jTextFieldNomeDespesa.setText(orcamento.getNome());
        }
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
    }
}
