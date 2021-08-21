package BeutifulSalon.view.Cadastros;

import BeutifulSalon.controller.ProdutoController;
import java.text.DecimalFormat;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Melissa
 */

//VIEW
public class CadastroProduto extends javax.swing.JFrame {
    
    public CadastroProduto() {
        initComponents();
        new BeutifulSalon.model.AplicaLookAndFeel().pegaNimbus();
        
        DecimalFormat decimal = new DecimalFormat("#,###,###.00");
        NumberFormatter numFormatter = new NumberFormatter(decimal);
        numFormatter.setFormat(decimal);
        numFormatter.setAllowsInvalid(false);
        DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(numFormatter);
        jFormattedTextFieldPreco.setFormatterFactory(dfFactory);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        JPCadastroClientes1 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldProduto = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldMarca = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jFormattedTextFieldPreco = new javax.swing.JFormattedTextField();
        jLabel26 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();

        jLabel8.setText("jLabel8");

        jLabel9.setText("jLabel9");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Produto");

        JPCadastroClientes1.setBackground(new java.awt.Color(243, 244, 245));

        jLabel17.setBackground(new java.awt.Color(34, 34, 34));
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(34, 34, 34));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Novo Produto");

        jTextFieldProduto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("*Nome Produto");

        jTextFieldMarca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("*Marca");

        jFormattedTextFieldPreco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jFormattedTextFieldPreco.setEnabled(false);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("*Preço de Venda ");

        jButton3.setBackground(new java.awt.Color(57, 201, 114));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Cadastrar");
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.setPreferredSize(new java.awt.Dimension(150, 65));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(248, 67, 69));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Cancelar");
        jButton4.setBorder(null);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton4.setPreferredSize(new java.awt.Dimension(150, 65));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jCheckBox1.setText("É Vendido?");
        jCheckBox1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBox1StateChanged(evt);
            }
        });

        javax.swing.GroupLayout JPCadastroClientes1Layout = new javax.swing.GroupLayout(JPCadastroClientes1);
        JPCadastroClientes1.setLayout(JPCadastroClientes1Layout);
        JPCadastroClientes1Layout.setHorizontalGroup(
            JPCadastroClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(JPCadastroClientes1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(JPCadastroClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPCadastroClientes1Layout.createSequentialGroup()
                        .addGroup(JPCadastroClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPCadastroClientes1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36))
                            .addGroup(JPCadastroClientes1Layout.createSequentialGroup()
                                .addComponent(jTextFieldMarca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(JPCadastroClientes1Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(JPCadastroClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPCadastroClientes1Layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPCadastroClientes1Layout.createSequentialGroup()
                                .addGroup(JPCadastroClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jCheckBox1)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, JPCadastroClientes1Layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addGroup(JPCadastroClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jFormattedTextFieldPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel26))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())))
                    .addGroup(JPCadastroClientes1Layout.createSequentialGroup()
                        .addComponent(jTextFieldProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 38, Short.MAX_VALUE))))
        );
        JPCadastroClientes1Layout.setVerticalGroup(
            JPCadastroClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPCadastroClientes1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addGap(12, 12, 12)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(JPCadastroClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPCadastroClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jFormattedTextFieldPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(JPCadastroClientes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        getContentPane().add(JPCadastroClientes1, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int opc = JOptionPane.showConfirmDialog(null,"Realmente deseja sair?", "Cadastro Produto", JOptionPane.YES_NO_OPTION);

        if(opc == 0){
            this.dispose();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    //salvar produto
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        boolean sucesso;

        ProdutoController produtoController = new ProdutoController(); //instanciar o controlador, que recebe um novo controador

        sucesso = produtoController.cadastrarProduto(
            jTextFieldProduto.getText(),
            jTextFieldMarca.getText(),
            jFormattedTextFieldPreco.getText(),
            LocalDate.now(),
            jCheckBox1.isSelected()// data registro
        );

        if (sucesso) {
            JOptionPane.showMessageDialog(null, "Cadastro concluído com sucesso.");
            limparTelaCadastroProduto();
        } else {
            JOptionPane.showMessageDialog(null, "Os campos não foram preenchidos corretamente!");
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jCheckBox1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBox1StateChanged
       
        if(jCheckBox1.isSelected()){
            jFormattedTextFieldPreco.setEnabled(true);
        }else{
            jFormattedTextFieldPreco.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox1StateChanged

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
            java.util.logging.Logger.getLogger(CadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new CadastroProduto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPCadastroClientes1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JFormattedTextField jFormattedTextFieldPreco;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextFieldMarca;
    private javax.swing.JTextField jTextFieldProduto;
    // End of variables declaration//GEN-END:variables

    public void limparTelaCadastroProduto() {
                jTextFieldProduto.setText(""); 
                jTextFieldMarca.setText(""); 
                jFormattedTextFieldPreco.setText(""); 
    }
}
