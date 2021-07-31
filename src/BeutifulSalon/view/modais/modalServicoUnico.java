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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mateus
 */
public class modalServicoUnico extends javax.swing.JFrame implements Observado{

    /**
     * Creates new form modalServicoUnico
     */
    ArrayList<Observador> observadores = new ArrayList<>();
    
    public modalServicoUnico() {
        initComponents();
        listarServicos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldNomeServico = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableConsultaServicos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        if (jTableConsultaServicos.getColumnModel().getColumnCount() > 0) {
            jTableConsultaServicos.getColumnModel().getColumn(2).setMinWidth(1);
            jTableConsultaServicos.getColumnModel().getColumn(2).setPreferredWidth(1);
            jTableConsultaServicos.getColumnModel().getColumn(2).setMaxWidth(1);
        }

        jLabel3.setText("Selecione o Serviço que deseja registrar o orçamento previsto");

        jButton1.setText("Confirmar");
        jButton1.setToolTipText("");
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
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(148, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNomeServicoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldNomeServicoCaretUpdate
        if(jTextFieldNomeServico.getText().equals("")){
            listarServicos();
        }

    }//GEN-LAST:event_jTextFieldNomeServicoCaretUpdate
    private void listarServicos(){
        
        DefaultTableModel model = (DefaultTableModel) jTableConsultaServicos.getModel();
        model.setRowCount(0);
        
        ServicoController sc = new ServicoController();
        
        ArrayList<Servico> servicos = null;
        
        try {       
            servicos = sc.listarServicos();
        } catch (ExceptionDAO ex) {
            java.util.logging.Logger.getLogger(modalServicos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        try {
            servicos.forEach((Servico s) ->  {            
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
            servicos.forEach((Servico s) ->  {
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
    private Servico retornarServico(){
        Servico servicoSelecionado = new Servico();
        DefaultTableModel model = (DefaultTableModel) jTableConsultaServicos.getModel();
        int indice = jTableConsultaServicos.getSelectedRow();
        
        if( indice > -1){
            try {
                servicoSelecionado.setNome(jTableConsultaServicos.getValueAt(indice, 0).toString());
                servicoSelecionado.setId((Long.parseLong(jTableConsultaServicos.getValueAt(indice, 2).toString())));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro ao recuperar servico." + e);
            }
           return servicoSelecionado;            
        }else{
            JOptionPane.showMessageDialog(null, "Indice selecionado inválido.");
        }
        
        return null;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        notificarObservadores();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(modalServicoUnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(modalServicoUnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(modalServicoUnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(modalServicoUnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new modalServicoUnico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableConsultaServicos;
    private javax.swing.JTextField jTextFieldNomeServico;
    // End of variables declaration//GEN-END:variables

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
        observadores.forEach((Observador ob)->{ 
            if(jTableConsultaServicos.getSelectedRow() > -1){
                
                Servico servico = retornarServico();
                if(servico != null){
                    ob.update(servico);
                }               
            }
           
        });
        
    }
}