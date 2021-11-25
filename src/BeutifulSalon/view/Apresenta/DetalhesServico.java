/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.view.Apresenta;

import BeutifulSalon.Ferramentas.ManipulaFontes;
import BeutifulSalon.Tabelas.CentralizaElementosTabela;
import BeutifulSalon.controller.CabeleireiroController;
import BeutifulSalon.controller.EstoqueController;
import BeutifulSalon.controller.OrcamentoController;
import BeutifulSalon.controller.ServicoController;
import BeutifulSalon.dao.ServicoDAO;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Orcamento;
import BeutifulSalon.model.OrcamentoServico;
import BeutifulSalon.model.Produto;
import BeutifulSalon.model.Servico;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mateus
 */
public class DetalhesServico extends javax.swing.JFrame {

    /**
     * Creates new form DetalhesServico
     */
    private Color verde = new Color(57, 201, 114);
    private Color vermelho = new Color(248, 67, 69);

    public DetalhesServico() {
        initComponents();
    }

    public DetalhesServico(Servico servico) {
        initComponents();

        ManipulaFontes mf = new ManipulaFontes();;

        //Fontes
        jLabelNomeServico.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 30f));
        jLabelPreco.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 30f));
        jLabel3.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jLabel8.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jLabel4.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
      //  jLabel5.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jLabel1.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 15f));
        jLabelQuantidadeMensal.setFont(mf.getFont(mf.MEDIUM, Font.PLAIN, 12f));
        jTableProdutosUtilizados.setFont(mf.getFont(mf.SEMIBOLD, Font.PLAIN, 15f)); //Tabela   

        //Numeros
        jLabelTempoDuracao.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 25f));
        jLabelMargemContribuicao.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 25f));
        jLabelPontoDeEquilíbrio.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 25f));
        //jLabelMeta.setFont(mf.getFont(mf.MEDIUM, Font.BOLD, 25f));

        EstoqueController ec = new EstoqueController();
        ((DefaultTableCellRenderer) jTableProdutosUtilizados.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        CentralizaElementosTabela render = new CentralizaElementosTabela();
        jTableProdutosUtilizados.setDefaultRenderer(Object.class, render);

        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
        jLabelNomeServico.setText(servico.getNome());
        jLabelPreco.setText(Dinheiro.parseString(servico.getPreco()));
        jLabelTempoDuracao.setText(servico.getTempoGasto().format(formatterHora) + "h");
        
        //Quantidade mensal realizada
        long qtdMensal = 0;
        
        try {
           qtdMensal =  new ServicoDAO().buscaQtdServicoMensal(servico.getId(), LocalDate.now().getMonth());
        } catch (Exception e) {
        }
         jLabelQuantidadeMensal.setText( "Quantidade Mensal Realizada: "+String.valueOf(qtdMensal));
        
        //CALCULANDO MARGEM DE CONTRIBUIÇÃO
        Long margemContribuicao = servico.getPreco();
        long subtracao = 0;

        for (Produto p : servico.getProdutos()) {
            if (p.getRendimento() > 0) {
                if (p.getRendimento() > 0) {
                    subtracao += ec.ultimoValorPagoProduto(p.getId_produto()) / p.getRendimento();
                }

            }
        }

        margemContribuicao -= subtracao;

        if (margemContribuicao <= 0) {
            jLabelMargemContribuicao.setForeground(vermelho);
        }
        jLabelMargemContribuicao.setText(Dinheiro.parseString(margemContribuicao));

        //CALCULANDO PONTO DE EQUILÍBRIO  (Custos Fixos/qtdServiços)/margemContribuição
        Month mesAtual = LocalDate.now().getMonth();
        List<Orcamento> orcamentos = new OrcamentoController().listarOrcamentos(String.valueOf(LocalDate.now().getYear()));
        List<OrcamentoServico> orcamentoServico = new OrcamentoController().listarOrcamentosServico(String.valueOf(LocalDate.now().getYear()));
        double meta = new CabeleireiroController().selecionaCabeleireiro().getMetaDeLucro();
        double pontoDeEquilibrio = 0.0;
        long totalDespesaMensalPrevista = 0;
        long previstoServicoMensal = 0;
        long totalPrevistoServicosDoMes = 0;
        //System.out.println("NOME SERVIÇO =>" + servico.getNome());
      //  System.out.println("Preço Servico =>" + Dinheiro.parseString(servico.getPreco()));
        switch (mesAtual) {
            case JANUARY:
                for (Orcamento oc : orcamentos) {
                    totalDespesaMensalPrevista += oc.getJan();
                }
                
                for(OrcamentoServico ocs: orcamentoServico){
                    totalPrevistoServicosDoMes += ocs.getJan() * new ServicoController().buscarServico(ocs.getId_servico()).getPreco();
                }
                previstoServicoMensal = new OrcamentoController().buscarOrcamentoServicoPeloServico(servico.getId()).getJan() * servico.getPreco();
                break;
            case FEBRUARY:
                for (Orcamento oc : orcamentos) {
                    totalDespesaMensalPrevista += oc.getFev();
                }
                for(OrcamentoServico ocs: orcamentoServico){
                    totalPrevistoServicosDoMes += ocs.getFev()* new ServicoController().buscarServico(ocs.getId_servico()).getPreco() ;
                }
                previstoServicoMensal = new OrcamentoController().buscarOrcamentoServicoPeloServico(servico.getId()).getFev() * servico.getPreco();
                break;
            case MARCH:
                for (Orcamento oc : orcamentos) {
                    totalDespesaMensalPrevista += oc.getMar();
                }
                previstoServicoMensal = new OrcamentoController().buscarOrcamentoServico(servico.getId()).getMar() * servico.getPreco();
                
                 for(OrcamentoServico ocs: orcamentoServico){
                    totalPrevistoServicosDoMes += ocs.getMar()  * new ServicoController().buscarServico(ocs.getId_servico()).getPreco();
                }
                previstoServicoMensal = new OrcamentoController().buscarOrcamentoServicoPeloServico(servico.getId()).getMar()* servico.getPreco();

                break;
            case APRIL:
                for (Orcamento oc : orcamentos) {
                    totalDespesaMensalPrevista += oc.getAbr();
                }
                for(OrcamentoServico ocs: orcamentoServico){
                    totalPrevistoServicosDoMes += ocs.getAbr()  * new ServicoController().buscarServico(ocs.getId_servico()).getPreco();
                }
                previstoServicoMensal = new OrcamentoController().buscarOrcamentoServicoPeloServico(servico.getId()).getAbr() * servico.getPreco();
                break;
            case MAY:
                for (Orcamento oc : orcamentos) {
                    totalDespesaMensalPrevista += oc.getMai();
                }
                for(OrcamentoServico ocs: orcamentoServico){
                    totalPrevistoServicosDoMes += ocs.getMai()  * new ServicoController().buscarServico(ocs.getId_servico()).getPreco();
                }
                previstoServicoMensal = new OrcamentoController().buscarOrcamentoServicoPeloServico(servico.getId()).getMai() * servico.getPreco();
                break;
            case JUNE:
                for (Orcamento oc : orcamentos) {
                    totalDespesaMensalPrevista += oc.getJun();
                }
                for(OrcamentoServico ocs: orcamentoServico){
                    totalPrevistoServicosDoMes += ocs.getJun()  * new ServicoController().buscarServico(ocs.getId_servico()).getPreco();
                }
                previstoServicoMensal = new OrcamentoController().buscarOrcamentoServicoPeloServico(servico.getId()).getJun() * servico.getPreco();
                break;
            case JULY:
                for (Orcamento oc : orcamentos) {
                    totalDespesaMensalPrevista += oc.getJul();
                }
                for(OrcamentoServico ocs: orcamentoServico){
                    totalPrevistoServicosDoMes += ocs.getJul()  * new ServicoController().buscarServico(ocs.getId_servico()).getPreco();
                }
                previstoServicoMensal = new OrcamentoController().buscarOrcamentoServicoPeloServico(servico.getId()).getJul() * servico.getPreco();
                break;
            case AUGUST:
                for (Orcamento oc : orcamentos) {
                    totalDespesaMensalPrevista += oc.getAgo();
                }
                for(OrcamentoServico ocs: orcamentoServico){
                    totalPrevistoServicosDoMes += ocs.getAgo() * new ServicoController().buscarServico(ocs.getId_servico()).getPreco();
                }
                previstoServicoMensal = new OrcamentoController().buscarOrcamentoServicoPeloServico(servico.getId()).getAgo() * servico.getPreco();
                break;
            case SEPTEMBER:
                for (Orcamento oc : orcamentos) {
                    totalDespesaMensalPrevista += oc.getSet();
                }
                for(OrcamentoServico ocs: orcamentoServico){
                    totalPrevistoServicosDoMes += ocs.getSet()  * new ServicoController().buscarServico(ocs.getId_servico()).getPreco();
                }
                previstoServicoMensal = new OrcamentoController().buscarOrcamentoServicoPeloServico(servico.getId()).getSet() * servico.getPreco();
                break;
            case OCTOBER:
                for (Orcamento oc : orcamentos) {
                    totalDespesaMensalPrevista += oc.getOut();
                }
                
                for(OrcamentoServico ocs: orcamentoServico){           
                    totalPrevistoServicosDoMes += ocs.getOut()  * new ServicoController().buscarServico(ocs.getId_servico()).getPreco();
                }
       
                previstoServicoMensal = new OrcamentoController().buscarOrcamentoServicoPeloServico(servico.getId()).getOut() * servico.getPreco();
                break;
            case NOVEMBER:
                for (Orcamento oc : orcamentos) {
                    totalDespesaMensalPrevista += oc.getNov();
                }
                for(OrcamentoServico ocs: orcamentoServico){
                    totalPrevistoServicosDoMes += ocs.getNov()  * new ServicoController().buscarServico(ocs.getId_servico()).getPreco();
                }
                previstoServicoMensal = new OrcamentoController().buscarOrcamentoServicoPeloServico(servico.getId()).getNov() * servico.getPreco();
                break;
            case DECEMBER:
                for (Orcamento oc : orcamentos) {
                    totalDespesaMensalPrevista += oc.getDez();
                }
                for(OrcamentoServico ocs: orcamentoServico){
                    totalPrevistoServicosDoMes += ocs.getDez()  * new ServicoController().buscarServico(ocs.getId_servico()).getPreco();
                }
                previstoServicoMensal = new OrcamentoController().buscarOrcamentoServicoPeloServico(servico.getId()).getDez() * servico.getPreco();
                break;
        }
        
        
        if(totalPrevistoServicosDoMes > 0 && margemContribuicao > 0){
            
        //System.out.println("Total Despesas do Mes=>"+ Dinheiro.parseString(totalDespesaMensalPrevista) );
        ////System.out.println("Previsto do Servico Mensal=>"+  Dinheiro.parseString(previstoServicoMensal) );
        //System.out.println("Total Previsto de todos Servicos do Mes=>" +  Dinheiro.parseString(totalPrevistoServicosDoMes));
        //System.out.println("Margem de Contribuição =>" + Dinheiro.parseString(margemContribuicao));
           
            
            
        pontoDeEquilibrio = (double)(((double)previstoServicoMensal/totalPrevistoServicosDoMes)*totalDespesaMensalPrevista ) / margemContribuicao;
       
        meta /= margemContribuicao;
        
        }
       // System.out.println("PONTO DE EQ=>" + pontoDeEquilibrio);
        //jLabelMeta.setText(String.valueOf((int) Math.ceil(meta)));
        jLabelPontoDeEquilíbrio.setText(String.valueOf( Math.round(pontoDeEquilibrio)));

        //============================================
        if (servico.getQuantidadeRealizada() < pontoDeEquilibrio) {
            jLabelPontoDeEquilíbrio.setForeground(vermelho);
        } else {
            jLabelPontoDeEquilíbrio.setForeground(verde);
        }


        if (pontoDeEquilibrio < 0) {
            jLabelPontoDeEquilíbrio.setText("PREJUÍZO");
            jLabelPontoDeEquilíbrio.setForeground(vermelho);
        }


        //Tabela Produtos Utilizados
        DefaultTableModel model = (DefaultTableModel) jTableProdutosUtilizados.getModel();
        model.setRowCount(0);
        servico.getProdutos().forEach(p -> {
            model.addRow(new Object[]{
                p.getNome(),
                p.getMarca(),
                Dinheiro.parseString(ec.ultimoValorPagoProduto(p.getId_produto())),
                p.getRendimento() + "x"
            });
        });

        jTableProdutosUtilizados.setModel(model);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        painelNumeroAgendamentos = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabelTempoDuracao = new javax.swing.JLabel();
        painelNumeroAgendamentos3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabelMargemContribuicao = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProdutosUtilizados = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        painelPontoEquilibrio = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabelPontoDeEquilíbrio = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabelNomeServico = new javax.swing.JLabel();
        jLabelPreco = new javax.swing.JLabel();
        jLabelQuantidadeMensal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        painelNumeroAgendamentos.setBackground(new java.awt.Color(255, 255, 255));
        painelNumeroAgendamentos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        painelNumeroAgendamentos.setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tempo de Duração");
        jLabel3.setIconTextGap(40);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 50, 0);
        painelNumeroAgendamentos.add(jLabel3, gridBagConstraints);

        jLabelTempoDuracao.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelTempoDuracao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTempoDuracao.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        painelNumeroAgendamentos.add(jLabelTempoDuracao, gridBagConstraints);

        painelNumeroAgendamentos3.setBackground(new java.awt.Color(255, 255, 255));
        painelNumeroAgendamentos3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        painelNumeroAgendamentos3.setToolTipText("Valor real ganho pela realização do serviço.");
        painelNumeroAgendamentos3.setLayout(new java.awt.GridBagLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Margem Contribuição");
        jLabel8.setIconTextGap(40);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 50, 0);
        painelNumeroAgendamentos3.add(jLabel8, gridBagConstraints);

        jLabelMargemContribuicao.setBackground(new java.awt.Color(255, 255, 255));
        jLabelMargemContribuicao.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelMargemContribuicao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMargemContribuicao.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        painelNumeroAgendamentos3.add(jLabelMargemContribuicao, gridBagConstraints);

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTableProdutosUtilizados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome", "Marca", "Último Valor Pago", "Rendimento"
            }
        ));
        jScrollPane1.setViewportView(jTableProdutosUtilizados);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Produtos Utilizados");

        painelPontoEquilibrio.setBackground(new java.awt.Color(255, 255, 255));
        painelPontoEquilibrio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        painelPontoEquilibrio.setToolTipText("Quantidade Mínima necessária para não ter prejuízo.");
        painelPontoEquilibrio.setLayout(new java.awt.GridBagLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Ponto de Equilíbrio");
        jLabel4.setIconTextGap(40);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 50, 0);
        painelPontoEquilibrio.add(jLabel4, gridBagConstraints);

        jLabelPontoDeEquilíbrio.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelPontoDeEquilíbrio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPontoDeEquilíbrio.setText("PREJUIZO");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        painelPontoEquilibrio.add(jLabelPontoDeEquilíbrio, gridBagConstraints);

        jPanel1.setBackground(new java.awt.Color(36, 46, 66));

        jLabelNomeServico.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelNomeServico.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNomeServico.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNomeServico.setText("Progressiva");

        jLabelPreco.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelPreco.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPreco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPreco.setText("R$");

        jLabelQuantidadeMensal.setForeground(new java.awt.Color(255, 255, 255));
        jLabelQuantidadeMensal.setText("Quantidade Mensal realizada:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelNomeServico, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                            .addComponent(jLabelPreco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelQuantidadeMensal)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNomeServico, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelQuantidadeMensal, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(painelNumeroAgendamentos, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(painelNumeroAgendamentos3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(painelPontoEquilibrio, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)))
                .addGap(40, 40, 40))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(painelNumeroAgendamentos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(painelNumeroAgendamentos3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(painelPontoEquilibrio, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                .addGap(4, 4, 4)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(DetalhesServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetalhesServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetalhesServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetalhesServico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetalhesServico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelMargemContribuicao;
    private javax.swing.JLabel jLabelNomeServico;
    private javax.swing.JLabel jLabelPontoDeEquilíbrio;
    private javax.swing.JLabel jLabelPreco;
    private javax.swing.JLabel jLabelQuantidadeMensal;
    private javax.swing.JLabel jLabelTempoDuracao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProdutosUtilizados;
    private javax.swing.JPanel painelNumeroAgendamentos;
    private javax.swing.JPanel painelNumeroAgendamentos3;
    private javax.swing.JPanel painelPontoEquilibrio;
    // End of variables declaration//GEN-END:variables
}
