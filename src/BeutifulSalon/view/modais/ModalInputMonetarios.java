/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.view.modais;

import BeutifulSalon.Ferramentas.ManipulaFontes;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Observado;
import BeutifulSalon.model.Observador;
import BeutifulSalon.view.Cadastros.CadastroFluxoDeCaixa;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author mateus
 */
public class ModalInputMonetarios extends javax.swing.JFrame implements Observado, Runnable {

    /**
     * Creates new form ModalInputMonetarios
     */
    ArrayList<Observador> observadores = new ArrayList<>();

    public ModalInputMonetarios() {
        initComponents();

        ManipulaFontes mf = new ManipulaFontes();
        jLabelTexto.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Salvar
        jFormattedTextFieldValor.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Descartar
        jLabelBtnInserir.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Descartar

        DecimalFormat decimal = new DecimalFormat("#,###,###.00");
        NumberFormatter numFormatter = new NumberFormatter(decimal);
        numFormatter.setFormat(decimal);
        numFormatter.setAllowsInvalid(false);
        DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(numFormatter);
        jFormattedTextFieldValor.setFormatterFactory(dfFactory);

    }

    public ModalInputMonetarios(String texto) {
        initComponents();
        jLabelTexto.setText(texto);

        DecimalFormat decimal = new DecimalFormat("#,###,###.00");
        NumberFormatter numFormatter = new NumberFormatter(decimal);
        numFormatter.setFormat(decimal);
        numFormatter.setAllowsInvalid(false);
        DefaultFormatterFactory dfFactory = new DefaultFormatterFactory(numFormatter);
        jFormattedTextFieldValor.setFormatterFactory(dfFactory);
    }

    private String valorMonetario() {
        return jFormattedTextFieldValor.getText();
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
        jLabelTexto = new javax.swing.JLabel();
        jLabelBtnInserir = new javax.swing.JLabel();
        jFormattedTextFieldValor = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabelTexto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelTexto.setText("jLabel1");

        jLabelBtnInserir.setBackground(new java.awt.Color(57, 201, 114));
        jLabelBtnInserir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelBtnInserir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelBtnInserir.setText("Confirmar");
        jLabelBtnInserir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelBtnInserir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelBtnInserirMousePressed(evt);
            }
        });

        jFormattedTextFieldValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        jFormattedTextFieldValor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(36, 46, 65));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabelBtnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTexto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jFormattedTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelBtnInserir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void jLabelBtnInserirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnInserirMousePressed

        notificarObservadores();
        dispose();
    }//GEN-LAST:event_jLabelBtnInserirMousePressed

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
            java.util.logging.Logger.getLogger(ModalInputMonetarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModalInputMonetarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModalInputMonetarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModalInputMonetarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModalInputMonetarios().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField jFormattedTextFieldValor;
    private javax.swing.JLabel jLabelBtnInserir;
    private javax.swing.JLabel jLabelTexto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
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

        observadores.forEach((Observador ob) -> {
            ob.update(valorMonetario());

        });
    }

    @Override
    public void run() {

        synchronized (this) {

        }

    }
}
