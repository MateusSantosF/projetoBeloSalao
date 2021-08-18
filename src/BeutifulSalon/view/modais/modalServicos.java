/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.view.modais;

import BeutifulSalon.controller.ServicoController;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Observado;
import BeutifulSalon.model.Observador;
import BeutifulSalon.model.Servico;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mateus
 */
public class modalServicos extends javax.swing.JFrame implements Observado {

    /**
     * Creates new form modalServicos
     */
    ArrayList<Observador> observadores = new ArrayList<>();

    public modalServicos() {
        initComponents();
        listarServicos();

        DefaultTableModel model = (DefaultTableModel) jTableServicosRealizados.getModel();
        model.setRowCount(0);
        jTableServicosRealizados.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTableConsultaServicos = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableServicosRealizados = new javax.swing.JTable();
        jLabelSelecionar = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNomeServico = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTableConsultaServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
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
        jScrollPane2.setViewportView(jTableConsultaServicos);

        jTableServicosRealizados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
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
        jScrollPane1.setViewportView(jTableServicosRealizados);

        jLabelSelecionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-seta.png"))); // NOI18N
        jLabelSelecionar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelSelecionarMousePressed(evt);
            }
        });

        jLabel3.setText("Serviços Realizados");

        jTextFieldNomeServico.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldNomeServicoCaretUpdate(evt);
            }
        });
        jTextFieldNomeServico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldNomeServicoKeyPressed(evt);
            }
        });

        jLabel2.setText("Busca por Nome");

        jButton1.setText("Confirmar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(99, 99, 99))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addComponent(jLabelSelecionar)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addContainerGap(56, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(391, 391, 391))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabelSelecionar)
                                .addGap(99, 99, 99))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelSelecionarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSelecionarMousePressed

        int indice = jTableConsultaServicos.getSelectedRow();
        Servico produtoBuscado = null;

        if (indice > -1) {
            try {

                DefaultTableModel tabelaServicosRealizados = (DefaultTableModel) jTableServicosRealizados.getModel();

                ServicoController svc = new ServicoController();

                long idServicoBuscado = (long) jTableConsultaServicos.getValueAt(indice, 2);

                produtoBuscado = svc.buscarServico(idServicoBuscado);

                tabelaServicosRealizados.addRow(new Object[]{
                    produtoBuscado.getNome(),
                    Dinheiro.parseString(produtoBuscado.getPreco()),
                    produtoBuscado.getId()
                });

                jTableServicosRealizados.setModel(tabelaServicosRealizados);
            } catch (ExceptionDAO e) {
                JOptionPane.showMessageDialog(null, e);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um serviço antes.");
        }

    }//GEN-LAST:event_jLabelSelecionarMousePressed

    private void jTextFieldNomeServicoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldNomeServicoCaretUpdate
        if (jTextFieldNomeServico.getText().equals("")) {
            listarServicos();
        }

    }//GEN-LAST:event_jTextFieldNomeServicoCaretUpdate

    private void jTextFieldNomeServicoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomeServicoKeyPressed

        String nome = jTextFieldNomeServico.getText();
        DefaultTableModel model = (DefaultTableModel) jTableConsultaServicos.getModel();
        model.setRowCount(0);

        ServicoController sc = new ServicoController();

        ArrayList<Servico> servicos = null;

        try {
            servicos = sc.listarServicos(nome);
        } catch (ExceptionDAO ex) {
            java.util.logging.Logger.getLogger(modalServicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        try {
            servicos.forEach((Servico s) -> {
                model.addRow(new Object[]{
                    s.getNome(),
                    Dinheiro.parseString(s.getPreco()),
                    s.getId()
                });
            });

            jTableConsultaServicos.setModel(model);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao listar servicos");
        }

    }//GEN-LAST:event_jTextFieldNomeServicoKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        notificarObservadores();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void listarServicos() {

        DefaultTableModel model = (DefaultTableModel) jTableConsultaServicos.getModel();
        model.setRowCount(0);

        ServicoController sc = new ServicoController();

        List<Servico> servicos = null;

        servicos = sc.listarServicos();

        try {
            servicos.forEach((Servico s) -> {
                model.addRow(new Object[]{
                    s.getNome(),
                    Dinheiro.parseString(s.getPreco()),
                    s.getId()
                });
            });

            jTableConsultaServicos.setModel(model);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao listar servicos");
        }

    }

    private DefaultTableModel servisoSelecionados() {
        return (DefaultTableModel) jTableServicosRealizados.getModel();
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
            java.util.logging.Logger.getLogger(modalServicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(modalServicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(modalServicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(modalServicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new modalServicos().setVisible(true);
            }
        });
    }

    @Override
    public void registrarObservador(Observador observador) {
        observadores.add(observador);
    }

    @Override
    public void removeObservador(Observador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores() {
        observadores.forEach((Observador obs) -> {
            obs.update(servisoSelecionados());
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelSelecionar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableConsultaServicos;
    private javax.swing.JTable jTableServicosRealizados;
    private javax.swing.JTextField jTextFieldNomeServico;
    // End of variables declaration//GEN-END:variables
}
