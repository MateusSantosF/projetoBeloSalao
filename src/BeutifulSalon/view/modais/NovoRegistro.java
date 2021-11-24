/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.view.modais;

import BeutifulSalon.Ferramentas.ManipulaFontes;
import BeutifulSalon.view.Cadastros.CadastroAgendamento;
import BeutifulSalon.view.Cadastros.CadastroProduto;
import BeutifulSalon.view.Cadastros.CadastroCliente;
import BeutifulSalon.view.Cadastros.CadastroDespesa;
import BeutifulSalon.view.Cadastros.CadastroFluxoDeCaixa;
import BeutifulSalon.view.Cadastros.CadastroOrcamentoPrevisto;
import BeutifulSalon.view.Cadastros.CadastroServico;
import java.awt.Component;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class NovoRegistro extends javax.swing.JFrame {

    /**
     * Creates new form novoRegistro
     */
    private  final ImageIcon PRODUTO_ICON = new ImageIcon(getClass().getResource("/imagens/iconProdutosRegistro.png"));
    private  final ImageIcon ORCAMENTO_ICON = new ImageIcon(getClass().getResource("/imagens/iconOrcamentoRegistro.png"));
    private  final ImageIcon CLIENTE_ICON = new ImageIcon(getClass().getResource("/imagens/iconClienteRegistro.png"));
    private  final ImageIcon VENDA_ICON = new ImageIcon(getClass().getResource("/imagens/iconVendaCompraRegistro.png"));
    private  final ImageIcon SERVICO_ICON = new ImageIcon(getClass().getResource("/imagens/iconServicoRegistro.png"));
    private  final ImageIcon AGENDAMENTO_ICON = new ImageIcon(getClass().getResource("/imagens/iconAgendamentoRegistro.png"));
    private  final ImageIcon DESPESA_ICON = new ImageIcon(getClass().getResource("/imagens/iconeDespesaRegistro.png"));
    public final String PRODUTO_STR = "Novo Produto";
    private final String ORCAMENTO_STR = "Novo Orçamento";
    public final String CLIENTE_STR = "Novo Cliente";
    private final String VENDA_STR = "Nova Venda | Compra";
    public final String SERVICO_STR = "Novo Serviço";
    private final String AGENDAMENTO_STR = "Novo Agendamento";
    public final String DESPESA_STR = "Nova Despesa";

    public NovoRegistro() {
        initComponents();

        ManipulaFontes mf = new ManipulaFontes();
        jLabel1.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 30f)); //Selecione o tipo 
        jLabel2.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 30f)); //de Registro
        jComboBoxTipoRegistro.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Combobox
        jButtonConfirmar.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 20f)); //Confirmar

        Map<Object, Icon> icons = new HashMap<Object, Icon>();

        icons.put(PRODUTO_STR, PRODUTO_ICON);
        icons.put(ORCAMENTO_STR, ORCAMENTO_ICON);
        icons.put(CLIENTE_STR, CLIENTE_ICON);
        icons.put(VENDA_STR, VENDA_ICON);
        icons.put(SERVICO_STR, SERVICO_ICON);
        icons.put(AGENDAMENTO_STR, AGENDAMENTO_ICON);
        icons.put(DESPESA_STR, DESPESA_ICON);
        

        jComboBoxTipoRegistro.setRenderer(new IconListRenderer(icons));

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
        jComboBoxTipoRegistro = new javax.swing.JComboBox<>();
        jButtonConfirmar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jComboBoxTipoRegistro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jComboBoxTipoRegistro.setForeground(new java.awt.Color(34, 34, 34));
        jComboBoxTipoRegistro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Novo Produto", "Novo Orçamento", "Novo Cliente", "Nova Venda | Compra", "Novo Serviço", "Novo Agendamento", "Nova Despesa" }));
        jComboBoxTipoRegistro.setBorder(null);
        jComboBoxTipoRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBoxTipoRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoRegistroActionPerformed(evt);
            }
        });

        jButtonConfirmar.setBackground(new java.awt.Color(255, 255, 255));
        jButtonConfirmar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.setBorder(null);
        jButtonConfirmar.setBorderPainted(false);
        jButtonConfirmar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonConfirmar.setPreferredSize(new java.awt.Dimension(150, 65));
        jButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(36, 46, 65));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Selecione o tipo ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("de Registro");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBoxTipoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jButtonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxTipoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxTipoRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTipoRegistroActionPerformed

    class IconListRenderer extends DefaultListCellRenderer {

      
        private Map<Object, Icon> icons = null;

        public IconListRenderer(Map<Object, Icon> icons) {
            this.icons = icons;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

          
            this.setIcon(icons.get(value.toString()));
            this.setText(value.toString());
            return this;
        }
    }

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed

        int opc = jComboBoxTipoRegistro.getSelectedIndex();

        switch (opc) {
            case 0:
                new CadastroProduto().setVisible(true);
                dispose();
                break;
            case 1:
                new CadastroOrcamentoPrevisto().setVisible(true);
                dispose();
                break;
            case 2:
                new CadastroCliente().setVisible(true);
                dispose();
                break;
            case 3:
                new CadastroFluxoDeCaixa().setVisible(true);
                dispose();
                break;
            case 4:
                new CadastroServico().setVisible(true);
                dispose();
                break;
            case 5:
                new CadastroAgendamento().setVisible(true);
                dispose();
                break;
            case 6:
                new CadastroDespesa().setVisible(true);
                dispose();
                break;

            default:
                JOptionPane.showMessageDialog(null, "Erro ao selecionar");
                break;
        }
    }//GEN-LAST:event_jButtonConfirmarActionPerformed

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
            java.util.logging.Logger.getLogger(NovoRegistro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NovoRegistro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NovoRegistro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NovoRegistro.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NovoRegistro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JComboBox<String> jComboBoxTipoRegistro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
