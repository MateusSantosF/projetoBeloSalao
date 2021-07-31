/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.view.Apresenta;

import BeutifulSalon.Ferramentas.ApresentaTabela;


/**
 *
 * @author Mateus
 */
public class ApresentaAgendamentos extends javax.swing.JPanel {

    /**
     * Creates new form ApresentaAgendamentos
     */
    
    private static int NOME = 0;
    private static int HOJE = 1;
    private static int AMANHA = 2;
    private static int SEMANA = 3;
    private static int TODOS = 4;
    
    public ApresentaAgendamentos() {
        initComponents();
        
        new BeutifulSalon.model.AplicaLookAndFeel().pegaNimbus();
        listarAgendamentos(TODOS, null);
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAgendamentos = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNomeCliente = new javax.swing.JTextField();
        jLabelBuscarCliente = new javax.swing.JLabel();
        jRadioButtonHoje = new javax.swing.JRadioButton();
        jRadioButtonAmanha = new javax.swing.JRadioButton();
        jRadioButtonSemana = new javax.swing.JRadioButton();
        jRadioButtonTodos = new javax.swing.JRadioButton();

        setForeground(new java.awt.Color(243, 244, 255));

        jPanel2.setBackground(new java.awt.Color(48, 63, 79));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Confira os agendamentos pendentes.");

        jLabel1.setBackground(new java.awt.Color(34, 34, 34));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Agendamentos");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(671, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(37, 37, 37))
        );

        jTableAgendamentos.setBackground(new java.awt.Color(243, 244, 245));
        jTableAgendamentos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableAgendamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cliente", "Data", "Horario", "Status", "ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAgendamentos.setGridColor(new java.awt.Color(243, 244, 245));
        jTableAgendamentos.setPreferredSize(new java.awt.Dimension(465, 402));
        jTableAgendamentos.setRowHeight(22);
        jScrollPane1.setViewportView(jTableAgendamentos);
        if (jTableAgendamentos.getColumnModel().getColumnCount() > 0) {
            jTableAgendamentos.getColumnModel().getColumn(4).setMinWidth(1);
            jTableAgendamentos.getColumnModel().getColumn(4).setPreferredWidth(1);
            jTableAgendamentos.getColumnModel().getColumn(4).setMaxWidth(1);
        }

        jLabel3.setText("Busca por cliente");

        jTextFieldNomeCliente.setBackground(new java.awt.Color(255, 255, 255));

        jLabelBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconLupa.png"))); // NOI18N
        jLabelBuscarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelBuscarClienteMousePressed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonHoje);
        jRadioButtonHoje.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButtonHoje.setText("Hoje");
        jRadioButtonHoje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonHojeActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonAmanha);
        jRadioButtonAmanha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButtonAmanha.setText("Amanhã");
        jRadioButtonAmanha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonAmanhaActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonSemana);
        jRadioButtonSemana.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButtonSemana.setText("1 semana");
        jRadioButtonSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonSemanaActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonTodos);
        jRadioButtonTodos.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jRadioButtonTodos.setSelected(true);
        jRadioButtonTodos.setText("Todos");
        jRadioButtonTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelBuscarCliente)
                        .addGap(133, 133, 133)
                        .addComponent(jRadioButtonHoje, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButtonAmanha)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonSemana)
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButtonTodos)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(34, 34, 34))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBuscarCliente)
                    .addComponent(jRadioButtonSemana)
                    .addComponent(jRadioButtonTodos)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jRadioButtonHoje, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButtonAmanha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonHojeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonHojeActionPerformed
        
        listarAgendamentos(HOJE, null);
    }//GEN-LAST:event_jRadioButtonHojeActionPerformed

    private void jRadioButtonAmanhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonAmanhaActionPerformed
        listarAgendamentos(AMANHA, null);
    }//GEN-LAST:event_jRadioButtonAmanhaActionPerformed

    private void jRadioButtonSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonSemanaActionPerformed
        listarAgendamentos(SEMANA, null);
    }//GEN-LAST:event_jRadioButtonSemanaActionPerformed

    private void jRadioButtonTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTodosActionPerformed
        listarAgendamentos(TODOS, null);
    }//GEN-LAST:event_jRadioButtonTodosActionPerformed

    private void jLabelBuscarClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBuscarClienteMousePressed
        listarAgendamentos(NOME, jTextFieldNomeCliente.getText());
    }//GEN-LAST:event_jLabelBuscarClienteMousePressed
    
    private void listarAgendamentos(int opc, String nome){
        jTableAgendamentos.setModel(new ApresentaTabela().Agendamentos(jTableAgendamentos, opc, nome));
    }
    
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelBuscarCliente;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButtonAmanha;
    private javax.swing.JRadioButton jRadioButtonHoje;
    private javax.swing.JRadioButton jRadioButtonSemana;
    private javax.swing.JRadioButton jRadioButtonTodos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAgendamentos;
    private javax.swing.JTextField jTextFieldNomeCliente;
    // End of variables declaration//GEN-END:variables
}