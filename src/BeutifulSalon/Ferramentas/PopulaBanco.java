/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.controller.ClienteController;
import BeutifulSalon.controller.ProdutoController;
import BeutifulSalon.controller.ServicoController;
import BeutifulSalon.dao.CompraProdutoDAO;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.dao.ProdutoDAO;
import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Compra;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Item;
import BeutifulSalon.model.Orcamento;
import BeutifulSalon.model.Produto;
import BeutifulSalon.model.Servico;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author mateu
 */
public class PopulaBanco {

    Orcamento oc = new Orcamento();
    ClienteController cc = new ClienteController();
    ServicoController sc = new ServicoController();
    ProdutoController pc = new ProdutoController();

    public void CadastrarClientes() {

        BufferedReader br = null;
        String linha = "";
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(
                    "C:\\Users\\Mateus\\Desktop\\LUCASDEDILAINE\\Clientes.csv"), StandardCharsets.ISO_8859_1));

            br.readLine();//pula primeira linha
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
                cc.cadastrarCliente(criaCliente(linha));
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }

    private Cliente criaCliente(String linha) {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String[] dados = linha.split(";");

        Cliente cliente = new Cliente();
        String nome = "";
        String sobrenome = "";
        String celular = "";
        String telefone = "";
        String dataNascimento = "";
        String email = "";

        if (dados[0] != "" && dados[0] != " " && dados[0] != null) {

            dados[0] = dados[0].replaceAll(" ", " ");
            String[] nomes = dados[0].split(" ");
            nome = nomes[0].substring(0, 1).toUpperCase().concat(nomes[0].substring(1).toLowerCase());
            for (int i = 1; i < nomes.length; i++) {
                if (nomes[i].length() >= 1) {
                    sobrenome += " " + nomes[i].substring(0, 1).toUpperCase().concat(nomes[i].substring(1).toLowerCase());
                }

            }
        }

        if (dados.length >= 2) {
            if (dados[1] != "" && dados[1] != " " && dados[1] != null && dados[1].length() >= 9) {

                dataNascimento = LocalDate.parse(dados[1].toLowerCase(), df2).format(df2).toString().trim();
            }
        }

        if (dados.length >= 3) {
            if (dados[2] != "" && dados[2] != " " && dados[2] != null) {
                celular = dados[2].trim();
            }
        }

        if (dados.length >= 4) {
            if (dados[3] != "" && dados[3] != " " && dados[3] != null) {
                telefone = dados[3].trim();
            }
        }

        if (dados.length >= 5) {
            if (dados[4] != "" && dados[4] != " " && dados[4] != null) {
                email = dados[4].trim();
            }
        }

        cliente.setNome(nome.trim());
        cliente.setSobrenome(sobrenome.trim());
        cliente.setCelular(celular.trim());
        cliente.setTelefoneResidencial(telefone.trim());
        cliente.setDataNasc(dataNascimento.trim());
        cliente.setEmail(email);
        cliente.setDataDeRegistro(LocalDate.now());

        return cliente;

    }

    public void CadastrarServicos() {

        BufferedReader br = null;
        String linha = "";
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Mateus\\Desktop\\LUCASDEDILAINE\\Servicos.csv"), StandardCharsets.ISO_8859_1));

            br.readLine();
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
                sc.cadastrarServico(criaServico(linha));
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }

    private Servico criaServico(String linha) {

        Servico servico = new Servico();
        ArrayList<Produto> produtos = new ArrayList<>();

        String[] dados = linha.split(";");
        String nomeServico = "";
        long precoServico = 0;
        LocalTime duracao = null;

        //nome
        if (dados[0] != "" && dados[0] != " " && dados[0] != null) {

            dados[0] = dados[0].replaceAll(" ", " ");
            nomeServico = dados[0].trim();
        }

        //preço
        if (dados.length >= 2) {
            if (dados[1] != "" && dados[1] != " " && dados[1] != null) {

                precoServico = Dinheiro.parseCent(Dinheiro.retiraCaracteres(dados[1]));
            }
        }

        //duração
        if (dados.length >= 3) {
            if (dados[2] != "" && dados[2] != " " && dados[2] != null) {

                //salão roger
                int formatado = Integer.valueOf(dados[2].substring(0, dados[2].indexOf("M") - 1));
                //System.out.println("Formatado =>" + formatado);
                int minutos = formatado % 60;
                int horas = (formatado - minutos) / 60;

                // System.out.println("Horas =>" + horas);
                // System.out.println("mInutos =>" + minutos);
                LocalTime t = LocalTime.of(horas, minutos);
                duracao = t;
            }
        }

        if (dados.length >= 4) {

            int idProduto = 0;
            for (int i = 3; i < dados.length; i += 3) {

                Produto p = new Produto();
                int rendimento = 0;
                p.setDataReg(LocalDate.now());
                p.setId_produto(idProduto++);
                if (!dados[i].equals("") && !dados[i].equals(" ") && dados[i] != null) {
                    p.setNome(dados[i].trim());
                    System.out.println("Nome produto =>" + p.getNome());
                }
                if (!dados[i + 1].equals("") && !dados[i + 1].equals(" ") && dados[i + 1] != null) {
                    p.setMarca(dados[i + 1].trim());
                    System.out.println("Marca =>" + p.getMarca());
                }
                if (i + 2 < dados.length) {
                    if (dados[i + 2] != null) {
                        //System.out.println("Rendimento linha=>" + dados[i + 2]);
                        rendimento = Integer.valueOf(dados[i + 2]);
                        p.setRendimento(rendimento);
                        // System.out.println("Rendimento =>" + p.getRendimento());
                    }
                }

                if (new ProdutoDAO().verificaExistenciaProduto(p.getNome(), p.getMarca()) == 0) {

                    new ProdutoDAO().cadastrarProduto(p);
                    p = new ProdutoDAO().buscarUltimoProdutoCadastrado();
                    p.setRendimento(rendimento);
                } else {
                    p = new ProdutoDAO().buscarProdutoNomeEMarca(p.getNome(), p.getMarca());
                    p.setRendimento(rendimento);
                }

                produtos.add(p);
            }

        }

        servico.setNome(nomeServico);
        servico.setPreco(precoServico);
        servico.setProdutos(produtos);
        servico.setTempoGasto(duracao);

        return servico;
    }

    public void CadastrarOrcamentoPrevisto() {

        BufferedReader br = null;
        String linha = "";
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(
                    "C:\\Users\\Mateus\\Desktop\\LUCASDEDILAINE\\Orcamento.csv"), StandardCharsets.ISO_8859_1));

            br.readLine();
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
                try {
                    new Orcamento().cadastrarOrcamento(criaOrcamento(linha));
                } catch (ExceptionDAO e) {
                }

            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }

    public void CadastrarProdutos() {

        BufferedReader br = null;
        String linha = "";
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(
                    "C:\\Users\\Mateus\\Desktop\\DENISE\\Produtos.csv"), StandardCharsets.ISO_8859_1));

            br.readLine();
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
                Produto p = criaProduto(linha);
                new ProdutoDAO().cadastrarProduto(p);

                String[] dados = linha.split(";");
                if (dados.length >= 4) {
                    if (dados[3] != "" && dados[3] != " " && dados[3] != null) {

                        Compra c = new Compra();
                        ArrayList<Item> itc = new ArrayList<>();
                        Item i = new Item();

                        c.setValorTotal(Dinheiro.parseCent(Dinheiro.retiraCaracteres(dados[3])));
                        c.setValorDesconto(0);
                        c.setData(LocalDate.now());

                        i.setPreco(Dinheiro.parseCent(Dinheiro.retiraCaracteres(dados[3])));
                        i.setMarca(p.getMarca());
                        i.setQuantidade(1);
                        i.setPrecoTotal(Dinheiro.parseCent(Dinheiro.retiraCaracteres(dados[3])));
                        i.setId_produto(new CompraProdutoDAO().retornaMaxID());
                        itc.add(i);

                        c.setItensCompra(itc);
                        new CompraProdutoDAO().cadastraCompra(c);

                    }
                }

            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }
    
    public void CadastrarServicosRealizados() {

        BufferedReader br = null;
        String linha = "";
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(
                    "C:\\Users\\Mateus\\Desktop\\LUCASDEDILAINE\\servicosRealizados.csv"), StandardCharsets.ISO_8859_1));

            br.readLine();
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
                cadastraServicosRealizados(linha);
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }

    private Produto criaProduto(String linha) {

        String[] dados = linha.split(";");

        Produto produto = new Produto();

        if (dados[0] != "" && dados[0] != " " && dados[0] != null) {

            produto.setNome(dados[0].trim());
        }

        if (dados.length >= 2) {
            if (dados[1] != "" && dados[1] != " " && dados[1] != null) {
                produto.setMarca(dados[1]);
            }
        }

        if (dados.length >= 3) {
            if (dados[2] != "" && dados[2] != " " && dados[2] != null) {

                if (dados[2].equals("#")) {
                    produto.setPreco(-1);
                } else {
                    produto.setPreco(Dinheiro.parseCent(Dinheiro.retiraCaracteres(dados[2])));
                }
            }
        }

        produto.setDataReg(LocalDate.now());

        return produto;
    }

    private Orcamento criaOrcamento(String linha) {

        String[] dados = linha.split(";");

        Orcamento orcamento = new Orcamento();
        String nome = "";
        String sobrenome = "";
        String celular = "";
        String telefone = "";
        String dataNascimento = "";
        String email = "";

        if (dados[0] != "" && dados[0] != " " && dados[0] != null) {

            dados[0] = dados[0].replaceAll(" ", " ");
            String[] nomes = dados[0].split(" ");
            nome = nomes[0].substring(0, 1).toUpperCase().concat(nomes[0].substring(1).toLowerCase());
            for (int i = 1; i < nomes.length; i++) {
                if (nomes[i].length() >= 1) {
                    sobrenome += " " + nomes[i].substring(0, 1).toUpperCase().concat(nomes[i].substring(1).toLowerCase());
                }

            }

            orcamento.setNome(nome + sobrenome);
        }

        if (dados.length >= 2) {
            if (dados[1] != "" && dados[1] != " " && dados[1] != null) {
                orcamento.setJan(Dinheiro.parseCent(Dinheiro.retiraCaracteres(dados[1])));
            }
        }

        if (dados.length >= 3) {
            if (dados[2] != "" && dados[2] != " " && dados[2] != null) {
                orcamento.setFev(Dinheiro.parseCent(Dinheiro.retiraCaracteres(dados[2])));
            }
        }

        if (dados.length >= 4) {
            if (dados[3] != "" && dados[3] != " " && dados[3] != null) {
                orcamento.setMar(Dinheiro.parseCent(Dinheiro.retiraCaracteres(dados[3])));
            }
        }

        if (dados.length >= 5) {
            if (dados[4] != "" && dados[4] != " " && dados[4] != null) {
                orcamento.setAbr(Dinheiro.parseCent(Dinheiro.retiraCaracteres(dados[4])));
            }
        }
        if (dados.length >= 6) {
            if (dados[5] != "" && dados[5] != " " && dados[5] != null) {
                orcamento.setMai(Dinheiro.parseCent(Dinheiro.retiraCaracteres(dados[5])));
            }
        }
        if (dados.length >= 7) {
            if (dados[6] != "" && dados[6] != " " && dados[6] != null) {
                orcamento.setJun(Dinheiro.parseCent(Dinheiro.retiraCaracteres(dados[6])));
            }
        }

        if (dados.length >= 8) {
            if (dados[7] != "" && dados[7] != " " && dados[7] != null) {
                orcamento.setJul(Dinheiro.parseCent(Dinheiro.retiraCaracteres(dados[7])));
            }
        }

        if (dados.length >= 9) {
            if (dados[8] != "" && dados[8] != " " && dados[8] != null) {
                orcamento.setAgo(Dinheiro.parseCent(Dinheiro.retiraCaracteres(dados[8])));
            }
        }

        if (dados.length >= 10) {
            if (dados[9] != "" && dados[9] != " " && dados[9] != null) {
                orcamento.setSet(Dinheiro.parseCent(Dinheiro.retiraCaracteres(dados[9])));
            }
        }

        if (dados.length >= 11) {
            if (dados[10] != "" && dados[10] != " " && dados[10] != null) {
                orcamento.setOut(Dinheiro.parseCent(Dinheiro.retiraCaracteres(dados[10])));
            }
        }
        if (dados.length >= 12) {
            if (dados[11] != "" && dados[11] != " " && dados[11] != null) {
                orcamento.setNov(Dinheiro.parseCent(Dinheiro.retiraCaracteres(dados[11])));
            }
        }

        if (dados.length >= 13) {
            if (dados[12] != "" && dados[12] != " " && dados[12] != null) {
                orcamento.setDez(Dinheiro.parseCent(Dinheiro.retiraCaracteres(dados[12])));
            }
        }

        orcamento.setPrevisto(true);
        orcamento.setAno(String.valueOf(LocalDate.now().getYear()));

        return orcamento;

    }

    public int geraDiaAleatorio() {
        Random gerador = new Random();
        return gerador.nextInt(27)+1;
    }

    public int geraClienteAleatorio() {
        Random gerador = new Random();
        return gerador.nextInt(40) + 1;
    }
    
    public int geraHoraAleatoria() {
        Random gerador = new Random();
        return gerador.nextInt(10) + 7;
    }
    
     public int geraMinutoAleatorio() {
        Random gerador = new Random();
        return gerador.nextInt(45) + 1;
    }

    public void cadastraServicosRealizados(String linha) {

        String[] dados = linha.split(";");
        Servico servico = new Servico();
        ManipulaData mp = new ManipulaData();
        List<Servico> servicos = new ArrayList<>();

        if (dados[0] != "" && dados[0] != " " && dados[0] != null) {
            servico = new ServicoController().listarServicos(dados[0]).get(0);
            servicos.add(servico);
        }

        if (dados.length >= 2) {
            if (dados[1] != "" && dados[1] != " " && dados[1] != null) {
               
                int ant = 0;
                int atual = 0;
                LocalTime horario = LocalTime.of(geraHoraAleatoria(), geraMinutoAleatorio());
                for (int i = 0; i < Integer.parseInt(dados[1]); i++) {

                    if (i == 0) {
                        ant = geraDiaAleatorio();
                        atual = ant;
                    } else {
                        atual = geraDiaAleatorio();
                        if (ant == atual) {
                            atual = geraDiaAleatorio();
                        }
                    }
                    Agendamento ag = new Agendamento();
                    LocalDate janeiro = LocalDate.of(2021, Month.JANUARY, atual);
                    ag.setData(janeiro);
                    ag.setIdCliente(geraClienteAleatorio());
                    ag.setServicos(servicos);
                    ag.setTotal(servico.getPreco());
                    ag.setDesconto(0);
                    ag.setPago(true);
                    ag.setRealizado(true);
                    ag.setFormaDePagamento("Pix");
                    ag.setHorario(horario);
                    ag.setFimAgendamento(horario.plusHours(servico.getTempoGasto().getHour()).plusMinutes(servico.getTempoGasto().getMinute()));
                    try {
                         ag.cadastraAgendamento(ag);
                    } catch (ExceptionDAO | SQLException e) {
                        System.out.println(e);
                    }
                }

            }
        }

        if (dados.length >= 3) {
            if (dados[2] != "" && dados[2] != " " && dados[2] != null) {

                int ant = 0;
                int atual = 0;
                LocalTime horario = LocalTime.of(geraHoraAleatoria(), geraMinutoAleatorio());
                for (int i = 0; i < Integer.parseInt(dados[2]); i++) {

                    if (i == 0) {
                        ant = geraDiaAleatorio();
                        atual = ant;
                    } else {
                        atual = geraDiaAleatorio();
                        if (ant == atual) {
                            atual = geraDiaAleatorio();
                        }
                    }
                    Agendamento ag = new Agendamento();
                    LocalDate fevereiro = LocalDate.of(2021, Month.FEBRUARY ,atual);
                    ag.setData(fevereiro);
                    ag.setIdCliente(geraClienteAleatorio());
                    ag.setServicos(servicos);
                    ag.setTotal(servico.getPreco());
                    ag.setDesconto(0);
                     ag.setRealizado(true);
                    ag.setPago(true);
                    ag.setFormaDePagamento("Pix");
                    ag.setHorario(horario);
                    ag.setFimAgendamento(horario.plusHours(servico.getTempoGasto().getHour()).plusMinutes(servico.getTempoGasto().getMinute()));

                     try {
                         ag.cadastraAgendamento(ag);
                    } catch (ExceptionDAO | SQLException e) {
                        System.out.println(e);
                    }
                }
            }
        }

        if (dados.length >= 4) {
            if (dados[3] != "" && dados[3] != " " && dados[3] != null) {
                int ant = 0;
                int atual = 0;
                LocalTime horario = LocalTime.of(geraHoraAleatoria(), geraMinutoAleatorio());
                for (int i = 0; i < Integer.parseInt(dados[3]); i++) {

                    if (i == 0) {
                        ant = geraDiaAleatorio();
                        atual = ant;
                    } else {
                        atual = geraDiaAleatorio();
                        if (ant == atual) {
                            atual = geraDiaAleatorio();
                        }
                    }
                    Agendamento ag = new Agendamento();
                    LocalDate marco = LocalDate.of(2021, Month.MARCH ,atual);
                    ag.setData(marco);
                    ag.setIdCliente(geraClienteAleatorio());
                    ag.setServicos(servicos);
                    ag.setTotal(servico.getPreco());
                    ag.setDesconto(0);
                    ag.setPago(true);
                     ag.setRealizado(true);
                    ag.setFormaDePagamento("Pix");
                    ag.setHorario(horario);
                    ag.setFimAgendamento(horario.plusHours(servico.getTempoGasto().getHour()).plusMinutes(servico.getTempoGasto().getMinute()));

                    try {
                         ag.cadastraAgendamento(ag);
                    } catch (ExceptionDAO | SQLException e) {
                        System.out.println(e);
                    }
                }
            }
        }

        if (dados.length >= 5) {
            if (dados[4] != "" && dados[4] != " " && dados[4] != null) {
                int ant = 0;
                int atual = 0;
                LocalTime horario = LocalTime.of(geraHoraAleatoria(), geraMinutoAleatorio());
                for (int i = 0; i < Integer.parseInt(dados[4]); i++) {

                    if (i == 0) {
                        ant = geraDiaAleatorio();
                        atual = ant;
                    } else {
                        atual = geraDiaAleatorio();
                        if (ant == atual) {
                            atual = geraDiaAleatorio();
                        }
                    }
                    Agendamento ag = new Agendamento();
                    LocalDate abril = LocalDate.of(2021, Month.APRIL,atual);
                    ag.setData(abril);
                    ag.setIdCliente(geraClienteAleatorio());
                    ag.setServicos(servicos);
                    ag.setTotal(servico.getPreco());
                    ag.setDesconto(0);
                    ag.setPago(true);
                    ag.setRealizado(true);
                    ag.setFormaDePagamento("Pix");
                    ag.setHorario(horario);
                    ag.setFimAgendamento(horario.plusHours(servico.getTempoGasto().getHour()).plusMinutes(servico.getTempoGasto().getMinute()));

                    try {
                         ag.cadastraAgendamento(ag);
                    } catch (ExceptionDAO | SQLException e) {
                        System.out.println(e);
                    }
                }
            }
        }
        if (dados.length >= 6) {
            if (dados[5] != "" && dados[5] != " " && dados[5] != null) {
                int ant = 0;
                int atual = 0;
                LocalTime horario = LocalTime.of(geraHoraAleatoria(), geraMinutoAleatorio());
                for (int i = 0; i < Integer.parseInt(dados[5]); i++) {

                    if (i == 0) {
                        ant = geraDiaAleatorio();
                        atual = ant;
                    } else {
                        atual = geraDiaAleatorio();
                        if (ant == atual) {
                            atual = geraDiaAleatorio();
                        }
                    }
                    Agendamento ag = new Agendamento();
                    LocalDate fevereiro = LocalDate.of(2021, Month.MAY ,atual);
                    ag.setData(fevereiro);
                    ag.setIdCliente(geraClienteAleatorio());
                    ag.setServicos(servicos);
                    ag.setTotal(servico.getPreco());
                    ag.setDesconto(0);
                     ag.setRealizado(true);
                    ag.setPago(true);
                    ag.setFormaDePagamento("Pix");
                    ag.setHorario(horario);
                    ag.setFimAgendamento(horario.plusHours(servico.getTempoGasto().getHour()).plusMinutes(servico.getTempoGasto().getMinute()));

                    try {
                         ag.cadastraAgendamento(ag);
                    } catch (ExceptionDAO | SQLException e) {
                        System.out.println(e);
                    }
                }
            }
        }
        if (dados.length >= 7) {
            if (dados[6] != "" && dados[6] != " " && dados[6] != null) {
                 int ant = 0;
                int atual = 0;
                LocalTime horario = LocalTime.of(geraHoraAleatoria(), geraMinutoAleatorio());
                for (int i = 0; i < Integer.parseInt(dados[6]); i++) {

                    if (i == 0) {
                        ant = geraDiaAleatorio();
                        atual = ant;
                    } else {
                        atual = geraDiaAleatorio();
                        if (ant == atual) {
                            atual = geraDiaAleatorio();
                        }
                    }
                    Agendamento ag = new Agendamento();
                    LocalDate fevereiro = LocalDate.of(2021, Month.JUNE ,atual);
                    ag.setData(fevereiro);
                    ag.setIdCliente(geraClienteAleatorio());
                    ag.setServicos(servicos);
                    ag.setTotal(servico.getPreco());
                    ag.setDesconto(0);
                    ag.setRealizado(true);
                    ag.setPago(true);
                    ag.setFormaDePagamento("Pix");
                    ag.setHorario(horario);
                   ag.setFimAgendamento(horario.plusHours(servico.getTempoGasto().getHour()).plusMinutes(servico.getTempoGasto().getMinute()));

                     try {
                         ag.cadastraAgendamento(ag);
                    } catch (ExceptionDAO | SQLException e) {
                        System.out.println(e);
                    }
                }
            }
        }

        if (dados.length >= 8) {
            if (dados[7] != "" && dados[7] != " " && dados[7] != null) {
                int ant = 0;
                int atual = 0;
                LocalTime horario = LocalTime.of(geraHoraAleatoria(), geraMinutoAleatorio());
                for (int i = 0; i < Integer.parseInt(dados[7]); i++) {

                    if (i == 0) {
                        ant = geraDiaAleatorio();
                        atual = ant;
                    } else {
                        atual = geraDiaAleatorio();
                        if (ant == atual) {
                            atual = geraDiaAleatorio();
                        }
                    }
                    Agendamento ag = new Agendamento();
                    LocalDate fevereiro = LocalDate.of(2021, Month.JULY ,atual);
                    ag.setData(fevereiro);
                    ag.setIdCliente(geraClienteAleatorio());
                    ag.setServicos(servicos);
                    ag.setTotal(servico.getPreco());
                    ag.setDesconto(0);
                    ag.setRealizado(true);
                    ag.setPago(true);
                    ag.setFormaDePagamento("Pix");
                    ag.setHorario(horario);
                    ag.setFimAgendamento(horario.plusHours(servico.getTempoGasto().getHour()).plusMinutes(servico.getTempoGasto().getMinute()));

                    try {
                         ag.cadastraAgendamento(ag);
                    } catch (ExceptionDAO | SQLException e) {
                        System.out.println(e);
                    }
                }
            }
        }

        if (dados.length >= 9) {
            if (dados[8] != "" && dados[8] != " " && dados[8] != null) {
                 int ant = 0;
                int atual = 0;
                LocalTime horario = LocalTime.of(geraHoraAleatoria(), geraMinutoAleatorio());
                for (int i = 0; i < Integer.parseInt(dados[8]); i++) {

                    if (i == 0) {
                        ant = geraDiaAleatorio();
                        atual = ant;
                    } else {
                        atual = geraDiaAleatorio();
                        if (ant == atual) {
                            atual = geraDiaAleatorio();
                        }
                    }
                    Agendamento ag = new Agendamento();
                    LocalDate fevereiro = LocalDate.of(2021, Month.AUGUST ,atual);
                    ag.setData(fevereiro);
                    ag.setIdCliente(geraClienteAleatorio());
                    ag.setServicos(servicos);
                    ag.setTotal(servico.getPreco());
                    ag.setDesconto(0);
                    ag.setRealizado(true);
                    ag.setPago(true);
                    ag.setFormaDePagamento("Pix");
                    ag.setHorario(horario);
                    ag.setFimAgendamento(horario.plusHours(servico.getTempoGasto().getHour()).plusMinutes(servico.getTempoGasto().getMinute()));

                     try {
                         ag.cadastraAgendamento(ag);
                    } catch (ExceptionDAO | SQLException e) {
                        System.out.println(e);
                    }
                }
            }
        }

        if (dados.length >= 10) {
            if (dados[9] != "" && dados[9] != " " && dados[9] != null) {
                int ant = 0;
                int atual = 0;
                LocalTime horario = LocalTime.of(geraHoraAleatoria(), geraMinutoAleatorio());
                for (int i = 0; i < Integer.parseInt(dados[9]); i++) {

                    if (i == 0) {
                        ant = geraDiaAleatorio();
                        atual = ant;
                    } else {
                        atual = geraDiaAleatorio();
                        if (ant == atual) {
                            atual = geraDiaAleatorio();
                        }
                    }
                    Agendamento ag = new Agendamento();
                    LocalDate fevereiro = LocalDate.of(2021, Month.SEPTEMBER ,atual);
                    ag.setData(fevereiro);
                    ag.setIdCliente(geraClienteAleatorio());
                    ag.setServicos(servicos);
                    ag.setTotal(servico.getPreco());
                    ag.setDesconto(0);
                    ag.setPago(true);
                    ag.setRealizado(true);
                    ag.setFormaDePagamento("Pix");
                    ag.setHorario(horario);
                    ag.setFimAgendamento(horario.plusHours(servico.getTempoGasto().getHour()).plusMinutes(servico.getTempoGasto().getMinute()));

                     try {
                         ag.cadastraAgendamento(ag);
                    } catch (ExceptionDAO | SQLException e) {
                        System.out.println(e);
                    }
                }
            }
        }

        if (dados.length >= 11) {
            if (dados[10] != "" && dados[10] != " " && dados[10] != null) {
                 int ant = 0;
                int atual = 0;
                LocalTime horario = LocalTime.of(geraHoraAleatoria(), geraMinutoAleatorio());
                for (int i = 0; i < Integer.parseInt(dados[10]); i++) {

                    if (i == 0) {
                        ant = geraDiaAleatorio();
                        atual = ant;
                    } else {
                        atual = geraDiaAleatorio();
                        if (ant == atual) {
                            atual = geraDiaAleatorio();
                        }
                    }
                    Agendamento ag = new Agendamento();
                    LocalDate fevereiro = LocalDate.of(2021, Month.OCTOBER ,atual);
                    ag.setData(fevereiro);
                    ag.setIdCliente(geraClienteAleatorio());
                    ag.setServicos(servicos);
                    ag.setTotal(servico.getPreco());
                    ag.setDesconto(0);
                    ag.setPago(true);
                    ag.setRealizado(true);
                    ag.setFormaDePagamento("Pix");
                    ag.setHorario(horario);
                    ag.setFimAgendamento(horario.plusHours(servico.getTempoGasto().getHour()).plusMinutes(servico.getTempoGasto().getMinute()));

                    try {
                         ag.cadastraAgendamento(ag);
                    } catch (ExceptionDAO | SQLException e) {
                        System.out.println(e);
                    }
                }
            }
        }
        if (dados.length >= 12) {
            if (dados[11] != "" && dados[11] != " " && dados[11] != null) {
                int ant = 0;
                int atual = 0;
                LocalTime horario = LocalTime.of(geraHoraAleatoria(), geraMinutoAleatorio());
                for (int i = 0; i < Integer.parseInt(dados[11]); i++) {

                    if (i == 0) {
                        ant = geraDiaAleatorio();
                        atual = ant;
                    } else {
                        atual = geraDiaAleatorio();
                        if (ant == atual) {
                            atual = geraDiaAleatorio();
                        }
                    }
                    Agendamento ag = new Agendamento();
                    LocalDate fevereiro = LocalDate.of(2021, Month.NOVEMBER ,atual);
                    ag.setData(fevereiro);
                    ag.setIdCliente(geraClienteAleatorio());
                    ag.setServicos(servicos);
                    ag.setTotal(servico.getPreco());
                    ag.setDesconto(0);
                    ag.setRealizado(true);
                    ag.setPago(true);
                    ag.setFormaDePagamento("Pix");
                    ag.setHorario(horario);
                    ag.setFimAgendamento(horario.plusHours(servico.getTempoGasto().getHour()).plusMinutes(servico.getTempoGasto().getMinute()));

                     try {
                         ag.cadastraAgendamento(ag);
                    } catch (ExceptionDAO | SQLException e) {
                        System.out.println(e);
                    }
                }
            }
        }

        if (dados.length >= 13) {
            if (dados[12] != "" && dados[12] != " " && dados[12] != null) {
                int ant = 0;
                int atual = 0;
                LocalTime horario = LocalTime.of(geraHoraAleatoria(), geraMinutoAleatorio());
                for (int i = 0; i < Integer.parseInt(dados[11]); i++) {

                    if (i == 0) {
                        ant = geraDiaAleatorio();
                        atual = ant;
                    } else {
                        atual = geraDiaAleatorio();
                        if (ant == atual) {
                            atual = geraDiaAleatorio();
                        }
                    }
                    Agendamento ag = new Agendamento();
                    LocalDate fevereiro = LocalDate.of(2021, Month.DECEMBER ,atual);
                    ag.setData(fevereiro);
                    ag.setIdCliente(geraClienteAleatorio());
                    ag.setServicos(servicos);
                    ag.setTotal(servico.getPreco());
                    ag.setDesconto(0);
                     ag.setRealizado(true);
                    ag.setPago(true);
                    ag.setFormaDePagamento("Pix");
                    ag.setHorario(horario);
                    ag.setFimAgendamento(horario.plusHours(servico.getTempoGasto().getHour()).plusMinutes(servico.getTempoGasto().getMinute()));

                    try {
                         ag.cadastraAgendamento(ag);
                    } catch (ExceptionDAO | SQLException e) {
                        System.out.println(e);
                    }
                   
                }
            }
        }

    }

    public static void main(String[] args) {
        //new PopulaBanco().CadastrarClientes();
        //new PopulaBanco().CadastrarServicos();
        //new PopulaBanco().CadastrarOrcamentoPrevisto();
        //new PopulaBanco().CadastrarProdutos();
        new PopulaBanco().CadastrarServicosRealizados();
    }

}
