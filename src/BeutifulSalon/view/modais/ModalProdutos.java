/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.view.modais;

import BeutifulSalon.Ferramentas.ManipulaFontes;
import BeutifulSalon.controller.ProdutoController;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Observado;
import BeutifulSalon.model.Observador;
import BeutifulSalon.model.Orcamento;
import BeutifulSalon.model.Produto;
import BeutifulSalon.model.Servico;
import java.awt.Font;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mateus
 */
public class ModalProdutos extends javax.swing.JFrame implements Observado, Observador {

    /**
     * Creates new form ModalProdutos
     */
    ArrayList<Observador> observadores = new ArrayList<>();
    private boolean cabeleleiro;
    private String valorPagoPeloProduto = "";

    public ModalProdutos() {
        initComponents();
        listarTodosProdutos();

        //Limpa linhas da tabela de produtos comprados
        DefaultTableModel model = (DefaultTableModel) jTableProdutosComprados.getModel();
        model.setRowCount(0);
        jTableProdutosComprados.setModel(model);
    }

    public ModalProdutos(boolean cabeleleiro) {

        initComponents();

        ManipulaFontes mf = new ManipulaFontes();

        jLabel2.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 25f)); //Busca por Nome
        jTextFieldNomeProduto.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 13f)); //Box Busca por Nome
        jLabel3.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 25f)); //Produtos Comprados
        jLabel4.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 25f)); //Produtos
        jButton1.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 18f)); //Concluir
        jTableConsultaProdutos.setFont(mf.getFont(mf.SEMIBOLD, Font.PLAIN, 13f)); //Tabela

        this.cabeleleiro = cabeleleiro;
        listarTodosProdutos();

        //Limpa linhas da tabela de produtos comprados
        DefaultTableModel model = (DefaultTableModel) jTableProdutosComprados.getModel();
        model.setRowCount(0);
        jTableProdutosComprados.setModel(model);

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
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableConsultaProdutos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProdutosComprados = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNomeProduto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Concluir");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTableConsultaProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "Marca", "Preço de Venda", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableConsultaProdutos);
        if (jTableConsultaProdutos.getColumnModel().getColumnCount() > 0) {
            jTableConsultaProdutos.getColumnModel().getColumn(3).setMinWidth(1);
            jTableConsultaProdutos.getColumnModel().getColumn(3).setPreferredWidth(1);
            jTableConsultaProdutos.getColumnModel().getColumn(3).setMaxWidth(1);
        }

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-seta.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
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

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Produtos Comprados");

        jTextFieldNomeProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldNomeProduto.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jTextFieldNomeProdutoCaretUpdate(evt);
            }
        });
        jTextFieldNomeProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldNomeProdutoKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Busca por Nome");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Produtos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(42, 42, 42)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(357, 357, 357)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(4, 4, 4)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(194, 194, 194))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        notificarObservadores();
        this.dispose();
    }//GEN-LAST:event_jButton1MousePressed

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed

        int indice = jTableConsultaProdutos.getSelectedRow();
        Produto produtoBuscado = null;

        if (indice > -1) {

            int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade comprada: "));
            long valorPago = 0;
            //Se for o cabeleleiro comprando
            if (cabeleleiro) {

                DialogValorProduto modalValorProduto = new DialogValorProduto(this, true);
                modalValorProduto.setName("Valor do Produto");
                modalValorProduto.registrarObservador(this);
                modalValorProduto.setVisible(true);
            }

            DefaultTableModel tabelaProdutosComprados = (DefaultTableModel) jTableProdutosComprados.getModel();

            ProdutoController po = new ProdutoController();

            long idProdutoBuscado = (long) jTableConsultaProdutos.getValueAt(indice, 3);

            produtoBuscado = po.buscarProduto(idProdutoBuscado);

            if (cabeleleiro) {
                valorPago = Dinheiro.parseCent(Dinheiro.retiraCaracteres(valorPagoPeloProduto));
            } else {
                valorPago = produtoBuscado.getPreco();
            }

            tabelaProdutosComprados.addRow(new Object[]{
                produtoBuscado.getNome(),
                produtoBuscado.getMarca(),
                Dinheiro.parseString(valorPago),
                quantidade,
                produtoBuscado.getId_produto()
            });

            jTableProdutosComprados.setModel(tabelaProdutosComprados);

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um produto antes.");
        }
    }//GEN-LAST:event_jLabel1MousePressed


    private void jTextFieldNomeProdutoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomeProdutoKeyPressed

        String nome = jTextFieldNomeProduto.getText(); // nome produto do TextField
        DefaultTableModel tabelaProdutoModel = (DefaultTableModel) jTableConsultaProdutos.getModel(); // tabela

        tabelaProdutoModel.setRowCount(0);

        ProdutoController pc = new ProdutoController();

        ArrayList<Produto> produtosListados = null;

        produtosListados = pc.listarProdutos(nome);

        try {
            produtosListados.forEach((Produto produto) -> {
                if (produto.getPreco() > 0) {
                    tabelaProdutoModel.addRow(new Object[]{
                        produto.getNome(),
                        produto.getMarca(),
                        Dinheiro.parseString(produto.getPreco()),
                        produto.getId_produto()
                    });
                }

            });

            jTableConsultaProdutos.setModel(tabelaProdutoModel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listarProdutos" + e);
        }
    }//GEN-LAST:event_jTextFieldNomeProdutoKeyPressed

    private void jTextFieldNomeProdutoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jTextFieldNomeProdutoCaretUpdate

        if (jTextFieldNomeProduto.getText().equals("")) {
            listarTodosProdutos();
        }
    }//GEN-LAST:event_jTextFieldNomeProdutoCaretUpdate

    private DefaultTableModel produtosEscolhidos() {

        return (DefaultTableModel) jTableProdutosComprados.getModel();
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
            java.util.logging.Logger.getLogger(ModalProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModalProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModalProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModalProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModalProdutos().setVisible(true);
                ModalInputMonetarios t = new ModalInputMonetarios();

            }
        });
    }

    private void listarTodosProdutos() {

        DefaultTableModel tabelaProdutoModel = (DefaultTableModel) jTableConsultaProdutos.getModel(); // tabela
        tabelaProdutoModel.setRowCount(0);

        ProdutoController pc = new ProdutoController();
        ArrayList<Produto> produtosListados = null;

        produtosListados = pc.listarProdutos();

        try {
            produtosListados.forEach((Produto produto) -> {

                if (cabeleleiro) {
                    
                    String preco = Dinheiro.parseString(produto.getPreco());
                    if(produto.getPreco() < 0){
                        preco = "Não vendido";
                    }
                    tabelaProdutoModel.addRow(new Object[]{
                        produto.getNome(),
                        produto.getMarca(),
                       preco,
                        produto.getId_produto()
                    });
                } else {
                    if (produto.getPreco() > 0) {
                        tabelaProdutoModel.addRow(new Object[]{
                            produto.getNome(),
                            produto.getMarca(),
                            Dinheiro.parseString(produto.getPreco()),
                            produto.getId_produto()
                        });
                    }
                }

            });

            jTableConsultaProdutos.setModel(tabelaProdutoModel);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listarProdutos" + e);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableConsultaProdutos;
    private javax.swing.JTable jTableProdutosComprados;
    private javax.swing.JTextField jTextFieldNomeProduto;
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
            ob.update(produtosEscolhidos());

        });
    }

    @Override
    public void update(Object obj) {
    }

    @Override
    public void update(DefaultTableModel model) {
    }

    @Override
    public void update(String valorDesconto) {

        //NÃO É VALOR DESCONTO, E SIM O VALOR PAGO PELO PRODUTO PELO CABELEIREIRO
        valorPagoPeloProduto = valorDesconto;
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

    @Override
    public void update(Orcamento orcamento) {
    }
}
