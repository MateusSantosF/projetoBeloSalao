/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.view.Apresenta;

import BeutifulSalon.Ferramentas.ManipulaFontes;
import BeutifulSalon.Tabelas.CentralizaElementosTabela;
import BeutifulSalon.Tabelas.ProdutoTableModel;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Mateus
 */
public class ApresentaProduto extends javax.swing.JPanel {

    private ProdutoTableModel produtoTableModel = new ProdutoTableModel();

    public ApresentaProduto() {
        initComponents();
        
        ManipulaFontes mf = new ManipulaFontes(); 
        
        //PRODUTOS
        jLabel1.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 50f)); //Produtos
        jLabel2.setFont(mf.getFont(mf.LIGHT, Font.PLAIN, 30f)); //Confira os produtos cadastrados!
        jLabel3.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Buscar por nome
        jLabelDetalhes.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //+Detalhes
        jLabelEditar.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Editar
        jLabelExcluir.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f)); //Excluir
        jScrollPane2.setFont(mf.getFont(mf.SEMIBOLD, Font.PLAIN, 15f)); //ScrollPane
        jTableConsultaProdutos.setFont(mf.getFont(mf.SEMIBOLD, Font.PLAIN, 15f)); // tabela
        
        produtoTableModel.getTodosProdutos();
        CentralizaElementosTabela render = new CentralizaElementosTabela();
        ((DefaultTableCellRenderer) jTableConsultaProdutos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        jTableConsultaProdutos.setDefaultRenderer(Object.class, render);
        jTableConsultaProdutos.setModel(produtoTableModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTableConsultaProdutos = new javax.swing.JTable();
        jTextFieldNomeProduto = new javax.swing.JTextField();
        jLabelBtnBuscarProdutos = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelDetalhes = new javax.swing.JLabel();
        jLabelEditar = new javax.swing.JLabel();
        jLabelExcluir = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);
        jScrollPane2.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jTableConsultaProdutos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableConsultaProdutos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableConsultaProdutos.setGridColor(new java.awt.Color(243, 244, 245));
        jTableConsultaProdutos.setRowHeight(22);
        jTableConsultaProdutos.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableConsultaProdutos.setShowGrid(true);
        jScrollPane2.setViewportView(jTableConsultaProdutos);

        jTextFieldNomeProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabelBtnBuscarProdutos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconLupa.png"))); // NOI18N
        jLabelBtnBuscarProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelBtnBuscarProdutosConsultarCliente(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Buscar por nome");

        jPanel1.setBackground(new java.awt.Color(48, 63, 79));

        jLabel1.setBackground(new java.awt.Color(34, 34, 34));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Produtos");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Confira os produtos cadastrados!");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-produtos-48.png"))); // NOI18N
        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                        .addGap(702, 702, 702))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(48, 63, 79));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabelDetalhes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelDetalhes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDetalhes.setText("+Detalhes");
        jLabelDetalhes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelDetalhes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelDetalhes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelDetalhesMousePressed(evt);
            }
        });

        jLabelEditar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelEditar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelEditar.setText("Editar");
        jLabelEditar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelEditarMousePressed(evt);
            }
        });

        jLabelExcluir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelExcluir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelExcluir.setText("Excluir");
        jLabelExcluir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabelExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelExcluirMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldNomeProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelBtnBuscarProdutos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabelBtnBuscarProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(jTextFieldNomeProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(jLabelEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelDetalhes, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addGap(49, 49, 49)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelBtnBuscarProdutosConsultarCliente(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBtnBuscarProdutosConsultarCliente

        if (jTextFieldNomeProduto.getText().equals("")) {
            produtoTableModel.getTodosProdutos();
            jTableConsultaProdutos.setModel(produtoTableModel);
        } else {
            produtoTableModel.getProdutosPeloNome(jTextFieldNomeProduto.getText());
            jTableConsultaProdutos.setModel(produtoTableModel);
        }

    }//GEN-LAST:event_jLabelBtnBuscarProdutosConsultarCliente

    private void jLabelDetalhesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDetalhesMousePressed
        
    }//GEN-LAST:event_jLabelDetalhesMousePressed

    private void jLabelEditarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelEditarMousePressed
        int indice = jTableConsultaProdutos.getSelectedRow();

        if (indice > -1) {
            boolean sucesso = produtoTableModel.editProduto(indice);

            if (sucesso == false) {
                JOptionPane.showMessageDialog(null, "Erro ao editar produto");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um produto.");
        }
    }//GEN-LAST:event_jLabelEditarMousePressed

    private void jLabelExcluirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExcluirMousePressed
        int indice = jTableConsultaProdutos.getSelectedRow();

        int opc = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o produto:\nNome: "
                + jTableConsultaProdutos.getValueAt(indice, 0) + "\nMarca: " + jTableConsultaProdutos.getValueAt(indice, 1)
                + "\nExclui-lo significa apagar todos registros de vendas e compras dele.", "Excluir Produto", JOptionPane.YES_NO_OPTION);

        if (opc == 0) {
            if (indice > -1) {
                boolean sucesso = produtoTableModel.removeProduto(indice);

                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Produto excluido com sucesso");
                    produtoTableModel.getTodosProdutos();
                    jTableConsultaProdutos.setModel(produtoTableModel);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um produto antes.");
            }
        }
    }//GEN-LAST:event_jLabelExcluirMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelBtnBuscarProdutos;
    private javax.swing.JLabel jLabelDetalhes;
    private javax.swing.JLabel jLabelEditar;
    private javax.swing.JLabel jLabelExcluir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableConsultaProdutos;
    private javax.swing.JTextField jTextFieldNomeProduto;
    // End of variables declaration//GEN-END:variables
}
