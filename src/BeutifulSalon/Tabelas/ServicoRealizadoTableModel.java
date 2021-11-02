/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Tabelas;

import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.controller.EstoqueController;
import BeutifulSalon.controller.OrcamentoController;
import BeutifulSalon.controller.ServicoController;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.OrcamentoProduto;
import BeutifulSalon.model.OrcamentoServico;
import BeutifulSalon.model.Produto;
import BeutifulSalon.model.Servico;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author mateu
 */
public class ServicoRealizadoTableModel extends AbstractTableModel {

    private final List<OrcamentoServico> dados;
    private final List<OrcamentoServico> quantidadesRealizadas;
    private LocalDate ano;
    private final String[] columns = {"Serviço", "Janeiro", "Fevereiro", "Março",
        "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    public ServicoRealizadoTableModel() {
        this.dados = new ArrayList<>();
        this.quantidadesRealizadas = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {

            case 0:
                return dados.get(rowIndex).getNome();
            case 1:
                return Dinheiro.parseString(dados.get(rowIndex).getJan());
            case 2:
                return Dinheiro.parseString(dados.get(rowIndex).getFev());
            case 3:
                return Dinheiro.parseString(dados.get(rowIndex).getMar());
            case 4:
                return Dinheiro.parseString(dados.get(rowIndex).getAbr());
            case 5:
                return Dinheiro.parseString(dados.get(rowIndex).getMai());
            case 6:
                return Dinheiro.parseString(dados.get(rowIndex).getJun());
            case 7:
                return Dinheiro.parseString(dados.get(rowIndex).getJul());
            case 8:
                return Dinheiro.parseString(dados.get(rowIndex).getAgo());
            case 9:
                return Dinheiro.parseString(dados.get(rowIndex).getSet());
            case 10:
                return Dinheiro.parseString(dados.get(rowIndex).getOut());
            case 11:
                return Dinheiro.parseString(dados.get(rowIndex).getNov());
            case 12:
                return Dinheiro.parseString(dados.get(rowIndex).getDez());
            default:
                return null;

        }

    }

    public void removeRow(int rowIndex) {
        dados.remove(rowIndex);
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(OrcamentoServico orcamentoServico) {
        dados.add(orcamentoServico);
        this.fireTableDataChanged();
    }

    public void addRow(List<OrcamentoServico> orcamentoServico) {
        orcamentoServico.forEach(orcS -> dados.add(orcS));
        this.fireTableDataChanged();
    }

    public void getTodosServicosRealizados(String anoReferente) {
        dados.clear();
        quantidadesRealizadas.clear();
        ServicoController sc = new ServicoController();
        OrcamentoController oc = new OrcamentoController();
        List<Servico> servicos = null;
        ArrayList<OrcamentoServico> orcamentos = new ArrayList<>();
        ManipulaData manipulaData = new ManipulaData();
        LocalDate ano = LocalDate.now();

        try {
            ano = LocalDate.ofYearDay(Integer.parseInt(anoReferente), 1);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao retornar ano" + e);
        }
        this.ano = ano;

        servicos = sc.listarServicos();

        if (servicos != null) {

            for (Servico s : servicos) {
                

                long janeiro = 0;
                long fevereiro = 0;
                long marco = 0;
                long abril = 0;
                long maio = 0;
                long junho = 0;
                long julho = 0;
                long agosto = 0;
                long setembro = 0;
                long outubro = 0;
                long novembro = 0;
                long dezembro = 0;

                OrcamentoServico ocQuantidades = new OrcamentoServico();
                ocQuantidades.setId_servico(s.getId());
                for (Month m : manipulaData.meses(ano)) {

                    switch (m) {
                        case JANUARY:
                            System.out.println(s.getId());
                            janeiro = oc.listarOrcamentoServicorRealizado(ano, Month.JANUARY, s.getId()).getQuantidadeRealizada();
                            ocQuantidades.setJan(janeiro);
                       
                            janeiro *= s.getPreco();
                            break;
                        case FEBRUARY:
                            fevereiro = oc.listarOrcamentoServicorRealizado(ano, Month.FEBRUARY, s.getId()).getQuantidadeRealizada();
                            ocQuantidades.setFev(fevereiro);
                            fevereiro *= s.getPreco();
                            break;
                        case MARCH:
                            marco = oc.listarOrcamentoServicorRealizado(ano, Month.MARCH, s.getId()).getQuantidadeRealizada();
                            ocQuantidades.setMar(marco);
                            marco *= s.getPreco();
                            break;
                        case APRIL:
                            abril = oc.listarOrcamentoServicorRealizado(ano, Month.APRIL, s.getId()).getQuantidadeRealizada();
                            ocQuantidades.setAbr(abril);
                            abril *= s.getPreco();
                            break;
                        case MAY:
                            maio = oc.listarOrcamentoServicorRealizado(ano, Month.MAY, s.getId()).getQuantidadeRealizada();
                            ocQuantidades.setMai(maio);
                            maio *= s.getPreco();
                            break;
                        case JUNE:
                            junho = oc.listarOrcamentoServicorRealizado(ano, Month.JUNE, s.getId()).getQuantidadeRealizada();
                            ocQuantidades.setJun(junho);
                            junho *= s.getPreco();
                            break;
                        case JULY:
                            julho = oc.listarOrcamentoServicorRealizado(ano, Month.JULY, s.getId()).getQuantidadeRealizada();
                            ocQuantidades.setJul(julho);
                            julho *= s.getPreco();
                            break;
                        case AUGUST:
                            agosto = oc.listarOrcamentoServicorRealizado(ano, Month.AUGUST, s.getId()).getQuantidadeRealizada();
                            ocQuantidades.setAgo(agosto);
                           
                            agosto *= s.getPreco();
                            break;
                        case SEPTEMBER:
                            setembro = oc.listarOrcamentoServicorRealizado(ano, Month.SEPTEMBER, s.getId()).getQuantidadeRealizada();
                            ocQuantidades.setSet(setembro);
                            setembro *= s.getPreco();
                            break;
                        case OCTOBER:
                            outubro = oc.listarOrcamentoServicorRealizado(ano, Month.OCTOBER, s.getId()).getQuantidadeRealizada();
                            ocQuantidades.setOut(outubro);
                            outubro *= s.getPreco();
                            break;
                        case NOVEMBER:
                            novembro = oc.listarOrcamentoServicorRealizado(ano, Month.NOVEMBER, s.getId()).getQuantidadeRealizada();
                            ocQuantidades.setNov(novembro);
                            novembro *= s.getPreco();
                            break;
                        case DECEMBER:
                            dezembro = oc.listarOrcamentoServicorRealizado(ano, Month.DECEMBER, s.getId()).getQuantidadeRealizada();
                            ocQuantidades.setDez(dezembro);
                            dezembro *= s.getPreco();
                            break;
                    }

                }
                quantidadesRealizadas.add(ocQuantidades);
                OrcamentoServico orcamentoAtual = new OrcamentoServico();
                orcamentoAtual.setNome(s.getNome());
                orcamentoAtual.setJan(janeiro);
                orcamentoAtual.setFev(fevereiro);
                orcamentoAtual.setMar(marco);
                orcamentoAtual.setAbr(abril);
                orcamentoAtual.setMai(maio);
                orcamentoAtual.setJun(junho);
                orcamentoAtual.setJul(julho);
                orcamentoAtual.setAgo(agosto);
                orcamentoAtual.setSet(setembro);
                orcamentoAtual.setOut(outubro);
                orcamentoAtual.setNov(novembro);
                orcamentoAtual.setDez(dezembro);

                orcamentos.add(orcamentoAtual);
            }
        }

        addRow(orcamentos);
        

    }

    public void calculaCustoProdutosMensal() {
        
     
        ManipulaData manipulaData = new ManipulaData();
        OrcamentoProduto gastoProdutos = new OrcamentoProduto();
        Double janeiro = 0.0;
        Double fevereiro = 0.0;
        Double marco = 0.0;
        Double abril = 0.0;
        Double maio = 0.0;
        Double junho = 0.0;
        Double julho = 0.0;
        Double agosto = 0.0;
        Double setembro = 0.0;
        Double outubro = 0.0;
        Double novembro = 0.0;
        Double dezembro = 0.0;
        for (OrcamentoServico s : quantidadesRealizadas) {

            Servico servicoAtual = new ServicoController().buscarServico(s.getId_servico());

            for (Month m : manipulaData.meses(ano)) {
                switch (m) {
                    case JANUARY:

                        if (s.getJan() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getJan();

                                    }
                                }
                                janeiro += temp.doubleValue();
                            }
                        }

                        break;
                    case FEBRUARY:
                        if (s.getFev() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                 Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getFev();

                                    }
                                }
                                fevereiro += temp.doubleValue();
                            }
                        }

                        break;
                    case MARCH:
                        if (s.getMar() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getMar();

                                    }
                                }
                                marco += temp.doubleValue();
                            }
                        }

                        break;
                    case APRIL:
                        if (s.getAbr() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                 Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getAbr();

                                    }
                                }
                                abril += temp.doubleValue();
                            }
                        }

                        break;
                    case MAY:
                        if (s.getMai() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                 Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getMai();

                                    }
                                }
                                maio += temp.doubleValue();
                            }
                        }
                        break;
                    case JUNE:
                        if (s.getJun() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getJun();

                                    }
                                }
                                junho += temp.doubleValue();
                            }
                        }
                        break;
                    case JULY:
                        if (s.getJul() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getJul();

                                    }
                                }
                                julho += temp.doubleValue();
                            }
                        }
                        break;
                    case AUGUST:
                        if (s.getAgo() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getAgo();

                                    }
                                }
                                agosto += temp.doubleValue();
                            }
                        }
                        break;
                    case SEPTEMBER:
                        if (s.getSet() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                 Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getSet();

                                    }
                                }
                                setembro += temp.doubleValue();
                            }
                        }
                        break;
                    case OCTOBER:
                        if (s.getOut() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                 Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getOut();

                                    }
                                }
                                outubro += temp.doubleValue();
                            }
                        }
                        break;
                    case NOVEMBER:
                        if (s.getNov() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                 Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getNov();

                                    }
                                }
                                novembro += temp.doubleValue();
                            }
                        }
                        break;
                    case DECEMBER:
                        if (s.getDez() > 0) {
                            
                            for (Produto p : servicoAtual.getProdutos()) {
                                Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getDez();

                                    }
                                }
                                dezembro += temp.doubleValue();
                            }
                        }
                        break;
                }
            }
        }

        gastoProdutos.setNome("Gastos Produtos");
        gastoProdutos.setJan(janeiro.longValue());
        gastoProdutos.setFev(fevereiro.longValue());
        gastoProdutos.setMar(marco.longValue());
        gastoProdutos.setAbr(abril.longValue());
        gastoProdutos.setMai(maio.longValue());
        gastoProdutos.setJun(junho.longValue());
        gastoProdutos.setJul(julho.longValue());
        gastoProdutos.setAgo(agosto.longValue());
        gastoProdutos.setSet(setembro.longValue());
        gastoProdutos.setOut(outubro.longValue());
        gastoProdutos.setNov(novembro.longValue());
        gastoProdutos.setDez(dezembro.longValue());

        addRow(gastoProdutos);
        calculaTotalServicosRealizados();
    }
    
    public OrcamentoProduto getOrcamentoProdutosMensal() {
        dados.clear();
        quantidadesRealizadas.clear();
        getTodosServicosRealizados(String.valueOf(LocalDate.now().getYear()));
        ManipulaData manipulaData = new ManipulaData();
        OrcamentoProduto gastoProdutos = new OrcamentoProduto();
        Double janeiro = 0.0;
        Double fevereiro = 0.0;
        Double marco = 0.0;
        Double abril = 0.0;
        Double maio = 0.0;
        Double junho = 0.0;
        Double julho = 0.0;
        Double agosto = 0.0;
        Double setembro = 0.0;
        Double outubro = 0.0;
        Double novembro = 0.0;
        Double dezembro = 0.0;
        for (OrcamentoServico s : quantidadesRealizadas) {
             
            
            Servico servicoAtual = new ServicoController().buscarServico(s.getId_servico());

            for (Month m : manipulaData.meses(ano)) {
                switch (m) {
                    case JANUARY:

                        if (s.getJan() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getJan();

                                    }
                                }
                                janeiro += temp.doubleValue();
                            }
                        }

                        break;
                    case FEBRUARY:
                        if (s.getFev() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                 Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getFev();

                                    }
                                }
                                fevereiro += temp.doubleValue();
                            }
                        }

                        break;
                    case MARCH:
                        if (s.getMar() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getMar();

                                    }
                                }
                                marco += temp.doubleValue();
                            }
                        }

                        break;
                    case APRIL:
                        if (s.getAbr() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                 Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getAbr();

                                    }
                                }
                                abril += temp.doubleValue();
                            }
                        }

                        break;
                    case MAY:
                        if (s.getMai() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                 Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getMai();

                                    }
                                }
                                maio += temp.doubleValue();
                            }
                        }
                        break;
                    case JUNE:
                        if (s.getJun() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getJun();

                                    }
                                }
                                junho += temp.doubleValue();
                            }
                        }
                        break;
                    case JULY:
                        if (s.getJul() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getJul();

                                    }
                                }
                                julho += temp.doubleValue();
                            }
                        }
                        break;
                    case AUGUST:
                        if (s.getAgo() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getAgo();

                                    }
                                }
                                agosto += temp.doubleValue();
                            }
                        }
                        break;
                    case SEPTEMBER:
                        if (s.getSet() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                 Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getSet();

                                    }
                                }
                                setembro += temp.doubleValue();
                            }
                        }
                        break;
                    case OCTOBER:
                        if (s.getOut() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                 Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getOut();

                                    }
                                }
                                outubro += temp.doubleValue();
                            }
                        }
                        break;
                    case NOVEMBER:
                        if (s.getNov() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                 Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getNov();

                                    }
                                }
                                novembro += temp.doubleValue();
                            }
                        }
                        break;
                    case DECEMBER:
                        if (s.getDez() > 0) {

                            for (Produto p : servicoAtual.getProdutos()) {
                                Long temp = Long.valueOf("0");
                                p.setPreco(new EstoqueController().ultimoValorPagoProduto(p.getId_produto()));
                                if (p.getPreco() > 0) {
                                    if (p.getRendimento() > 0) {

                                        temp += p.getPreco() / p.getRendimento();
                                        temp *= s.getDez();

                                    }
                                }
                                dezembro += temp.doubleValue();
                            }
                        }
                        break;
                }
            }
        }

        gastoProdutos.setNome("Gastos Produtos");
        gastoProdutos.setJan(janeiro.longValue());
        gastoProdutos.setFev(fevereiro.longValue());
        gastoProdutos.setMar(marco.longValue());
        gastoProdutos.setAbr(abril.longValue());
        gastoProdutos.setMai(maio.longValue());
        gastoProdutos.setJun(junho.longValue());
        gastoProdutos.setJul(julho.longValue());
        gastoProdutos.setAgo(agosto.longValue());
        gastoProdutos.setSet(setembro.longValue());
        gastoProdutos.setOut(outubro.longValue());
        gastoProdutos.setNov(novembro.longValue());
        gastoProdutos.setDez(dezembro.longValue());

        return gastoProdutos;
    }

    public void calculaTotalServicosRealizados() {

        long janeiro, fevereiro, marco, abril, maio, junho, julho, agosto, setembro, outubro, novembro, dezembro;
        janeiro = fevereiro = marco = abril = maio = junho = julho = agosto = setembro = outubro = novembro = dezembro = 0;

        for (OrcamentoServico ocs : dados) {

            if (ocs instanceof OrcamentoProduto) {
                janeiro -= ocs.getJan();
                fevereiro -= ocs.getFev();
                marco -= ocs.getMar();
                abril -= ocs.getAbr();
                maio -= ocs.getMai();
                junho -= ocs.getJun();
                julho -= ocs.getJul();
                agosto -= ocs.getAgo();
                setembro -= ocs.getSet();
                outubro -= ocs.getOut();
                novembro -= ocs.getNov();
                dezembro -= ocs.getDez();
            } else {
                janeiro += ocs.getJan();
                fevereiro += ocs.getFev();
                marco += ocs.getMar();
                abril += ocs.getAbr();
                maio += ocs.getMai();
                junho += ocs.getJun();
                julho += ocs.getJul();
                agosto += ocs.getAgo();
                setembro += ocs.getSet();
                outubro += ocs.getOut();
                novembro += ocs.getNov();
                dezembro += ocs.getDez();
            }

        }

        OrcamentoServico orcamentoTotal = new OrcamentoServico();
        orcamentoTotal.setNome("TOTAL");
        orcamentoTotal.setJan(janeiro);
        orcamentoTotal.setFev(fevereiro);
        orcamentoTotal.setMar(marco);
        orcamentoTotal.setAbr(abril);
        orcamentoTotal.setMai(maio);
        orcamentoTotal.setJun(junho);
        orcamentoTotal.setJul(julho);
        orcamentoTotal.setAgo(agosto);
        orcamentoTotal.setSet(setembro);
        orcamentoTotal.setOut(outubro);
        orcamentoTotal.setNov(novembro);
        orcamentoTotal.setDez(dezembro);
        addRow(orcamentoTotal);
    }

}
