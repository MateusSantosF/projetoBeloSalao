/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.view.Apresenta;

import BeutifulSalon.Ferramentas.ManipulaFontes;
import BeutifulSalon.Tabelas.AgendamentoTableModel;
import BeutifulSalon.Tabelas.CentralizaElementosTabela;
import BeutifulSalon.controller.AgendamentoController;
import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.ObservadorAgendamento;
import BeutifulSalon.model.ObservadorEdicao;
import BeutifulSalon.view.Edicao.EditarAgendamento;
import BeutifulSalon.view.modais.ModalDatas;
import BeutifulSalon.view.modais.ModalDetalhesAgendamento;
import java.awt.Font;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Mateus
 */
public class ApresentaAgendamentos extends javax.swing.JPanel implements ObservadorEdicao, ObservadorAgendamento {

    /**
     * Creates new form ApresentaAgendamentos
     */
    private final AgendamentoTableModel modelo = new AgendamentoTableModel();

    public ApresentaAgendamentos() {
        initComponents();

        ManipulaFontes mf = new ManipulaFontes();

        jLabel1.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 50f));
        jLabel2.setFont(mf.getFont(mf.LIGHT, Font.PLAIN, 30f));
        jLabel3.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f));
        jLabel5.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f));
        jLabel6.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f));
        jLabelExcluir.setFont(mf.getFont(mf.BOLD, Font.PLAIN, 15f));

 
        jTableAgendamentos.setFont(mf.getFont(mf.SEMIBOLD, Font.PLAIN, 15f));

        CentralizaElementosTabela render = new CentralizaElementosTabela();
        modelo.getAgendamentosHoje();
        ((DefaultTableCellRenderer) jTableAgendamentos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        jTableAgendamentos.setDefaultRenderer(Object.class, render);
        jTableAgendamentos.setModel(modelo);

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
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNomeCliente = new javax.swing.JTextField();
        jLabelBuscarCliente = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelExcluir = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAgendamentos = new javax.swing.JTable();
        jComboBoxFiltros = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(243, 244, 255));
        setPreferredSize(new java.awt.Dimension(969, 843));

        jPanel2.setBackground(new java.awt.Color(48, 63, 79));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Confira os agendamentos!");

        jLabel1.setBackground(new java.awt.Color(34, 34, 34));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Agendamentos");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/icon-agendamentos-48.png"))); // NOI18N
        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Buscar por cliente");

        jTextFieldNomeCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabelBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/iconLupa.png"))); // NOI18N
        jLabelBuscarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelBuscarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelBuscarClienteMousePressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(48, 63, 79));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Editar");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("+Detalhes");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
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

        jTableAgendamentos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTableAgendamentos.setForeground(new java.awt.Color(25, 25, 25));
        jTableAgendamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "Marca", "Preço de Venda", "Quantidade em Estoque"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAgendamentos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTableAgendamentos.setGridColor(new java.awt.Color(243, 244, 245));
        jTableAgendamentos.setRowHeight(22);
        jTableAgendamentos.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableAgendamentos.setShowGrid(false);
        jScrollPane1.setViewportView(jTableAgendamentos);

        jComboBoxFiltros.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jComboBoxFiltros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoje", "Amanhã", "1 Semana", "Não Pagos", "Não Realizados" }));
        jComboBoxFiltros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBoxFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFiltrosActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Buscar agendamentos:");

        jButton1.setText("Por Datas");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(jTextFieldNomeCliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBoxFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 40, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabelExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelExcluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelBuscarCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelBuscarClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelBuscarClienteMousePressed

        if (jTextFieldNomeCliente.getText().equals("")) {
            modelo.getTodosAgendamentos();
        } else {
            modelo.getAgendamentosPorNomeCliente(jTextFieldNomeCliente.getText());
        }
        jTableAgendamentos.setModel(modelo);
    }//GEN-LAST:event_jLabelBuscarClienteMousePressed

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        int index = jTableAgendamentos.getSelectedRow();
        AgendamentoController ag = new AgendamentoController();
        Agendamento agendamento;
        boolean sucesso = false;

        if (index > -1) {
            long idAgendamento = modelo.getAgendamento(index).getIdAgendamento();
            agendamento = ag.listarAgendamento(idAgendamento);
            EditarAgendamento ed = new EditarAgendamento(agendamento);
            ed.registrarObservador(this);
            sucesso = ag.editarAgendamento(agendamento, ed);

            if (!sucesso) {
                JOptionPane.showMessageDialog(null, "Erro ao selecionar PKAgendamento");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um agendamento");
        }
    }//GEN-LAST:event_jLabel5MousePressed
    private void atualizarTabela() {
        switch (jComboBoxFiltros.getSelectedIndex()) {
            case 0:
                modelo.getAgendamentosHoje();
                break;
            case 1:
                modelo.getAgendamentosAmanha();
                break;

            case 2:
                modelo.getAgendamentosSemana();
                break;

            case 3:
                modelo.getAgendamentosNaPagos();
                break;
            case 4:
                modelo.getAgendamentosNaoRealizados();
                break;
        }

        jTableAgendamentos.setModel(modelo);
    }
    private void jLabelExcluirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelExcluirMousePressed

        int indice = jTableAgendamentos.getSelectedRow();
        int opc = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este agendamento?", "Excluir Agendamento", JOptionPane.YES_NO_OPTION);

        if (opc == 0) {
            if (indice > -1) {

                Agendamento agendamento = modelo.getAgendamento(indice);
                boolean sucesso = new AgendamentoController().excluirAgendamento(agendamento);

                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Agendamento deletado com sucesso.");
                    atualizarTabela();
                    //  jRadioButtonHoje.setSelected(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível excluir este agendamento.");
                }

            }
        }


    }//GEN-LAST:event_jLabelExcluirMousePressed

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed

        int indice = jTableAgendamentos.getSelectedRow();

        if (indice > -1) {
            Agendamento agendamento = modelo.getAgendamento(indice);

            agendamento.setServicos(new AgendamentoController().listarServicosAgendamento(agendamento.getIdAgendamento()));

            new ModalDetalhesAgendamento(agendamento).setVisible(true);
        }
    }//GEN-LAST:event_jLabel6MousePressed

    private void jComboBoxFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFiltrosActionPerformed
        atualizarTabela();
    }//GEN-LAST:event_jComboBoxFiltrosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ModalDatas modal = new ModalDatas();
        modal.registrarObservador(this);
        modal.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBoxFiltros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelBuscarCliente;
    private javax.swing.JLabel jLabelExcluir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAgendamentos;
    private javax.swing.JTextField jTextFieldNomeCliente;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(boolean editou) {

        atualizarTabela();
    }

    @Override
    public void update(LocalDate inicio, LocalDate fim) {
        modelo.getAgendamentosPorData(inicio, fim);
        jTableAgendamentos.setModel(modelo);
    }
}
