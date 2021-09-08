/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.Ferramentas.Valida;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.dao.clienteDAO;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.view.Apresenta.DetalhesCliente;
import BeutifulSalon.view.Edicao.EditarCliente;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class ClienteController {

    public boolean cadastrarCliente(String CPF, String NOME, String SOBRENOME, String EMAIL, String DATANASC,
            String CEP, String BAIRRO, String RUA, String CIDADE, String NUMERO,
            String TELEFONE, String CELULAR, String DATAREG) {

        if (NOME != null && NOME.length() > 0 && Valida.isCpf(CPF) && Valida.isEmail(EMAIL) && Valida.isData(DATANASC)
                && verificaExistenciaCliente(CPF) == false) {

            //Formatador
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/M/uuuu");
            //Convertendo datas de String para Date
            LocalDate dataReg = LocalDate.parse(DATAREG, formatterData);
            //objeto cliente
            Cliente cliente = new Cliente(CPF, NOME, SOBRENOME, EMAIL, DATANASC, CEP,
                    BAIRRO, RUA, CIDADE, NUMERO, TELEFONE, CELULAR, dataReg);
            try {
                //Chamando construtor de Cliente
                cliente.cadastrarCliente(cliente);
            } catch (ExceptionDAO ex) {
                JOptionPane.showMessageDialog(null, "Erro ao criar objeto cliente");
                return false;
            }
            return true;

        }
        return false;
    }

    public boolean atualizarCliente(String CPF, String NOME, String SOBRENOME, String EMAIL, String DATANASC,
            String CEP, String BAIRRO, String RUA, String CIDADE, String NUMERO,
            String TELEFONE, String CELULAR, String DATAREG) {

        if (NOME != null && NOME.length() > 0 && Valida.isCpf(CPF) && Valida.isEmail(EMAIL) && Valida.isData(DATANASC)) {

            //Formatador
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/M/uuuu");

            //objeto cliente
            Cliente cliente = new Cliente(CPF, NOME, SOBRENOME, EMAIL, DATANASC, CEP,
                    BAIRRO, RUA, CIDADE, NUMERO, TELEFONE, CELULAR);
            try {
                //Chamando construtor de Cliente
                cliente.atualizarCliente(cliente);
            } catch (ExceptionDAO ex) {
                JOptionPane.showMessageDialog(null, "Erro ao criar objeto cliente" + ex);
                return false;
            }
            return true;

        } else {
            return false;
        }

    }

    public boolean validarCPF(String cpf) {

        for (int i = 0; i < cpf.length(); i++) {
            if (!Character.isDigit(cpf.charAt(i))) {
                if (!(i == 3 || i == 7 || i == 11)) {
                    JOptionPane.showMessageDialog(null, "CPF Inválido");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean verificaExistenciaCliente(String cpf) {

        try {
            boolean existe = new Cliente().verificaExistenciaCliente(cpf);

            if (existe) {
                JOptionPane.showMessageDialog(null, "Já existe um cliente cadastrado com este CPF.");
                return true;
            } else {
                return false;
            }
        } catch (ExceptionDAO e) {
        }

        return false;
    }

    public List<Cliente> listarClientes(String nome) {
        try {
            return new Cliente().listarClientes(nome);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public List<Cliente> listarClientes() {
        try {
            return new Cliente().listarClientes();
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public Cliente buscarCliente(String cpf) {
        try {
            return new Cliente().buscarCliente(cpf);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public boolean excluirCliente(String cpf) {

        try {
            Cliente c = new Cliente();
            c.excluirCliente(cpf);

        } catch (ExceptionDAO e) {
            JOptionPane.showInputDialog(null, "Erro Controller, excluir cliente");
            return false;
        }

        return true;
    }

    public boolean editarCliente(String cpf) {

        try {
            Cliente c = new Cliente();
            Cliente clienteEditado;

            clienteEditado = c.editarCliente(cpf);

            if (clienteEditado != null) {
                new EditarCliente(clienteEditado).setVisible(true);
            } else {
                return false;
            }

        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar objeto cliente" + e);
            return false;
        }

        return true;
    }

    public void exibirMaisDetalhes(Cliente cliente) {
        new DetalhesCliente(cliente).setVisible(true);
    }

    public LocalDate ultimaVisita(String cpf) {

        try {
            return new Cliente().ultimaVisita(cpf);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar ultima visita do cliente " + e);
        }
        return null;
    }

    public boolean atualizarDetalhesCliente(Cliente cliente) {

        if (cliente.getCorCabelo().length() < 16 && cliente.getFacebook().length() < 32 && cliente.getInstagram().length() < 32) {
            try {
                cliente.atualizarDetalhesCliente(cliente);
            } catch (ExceptionDAO e) {
                JOptionPane.showMessageDialog(null, e);
                return false;
            }

            return true;
        } else {
            return false;
        }
    }

    public List<Cliente> top5Clientes(int anoReferente) {
        try {
            return new Cliente().top5Clientes(anoReferente);
        } catch (ExceptionDAO e) {
            System.out.println("Erro ao listar top 5 clientes" + e);
        }
        return null;
    }

    public boolean cadastraImagemPerfil(String cpf, byte[] imagem) {

        try {
            return new Cliente().cadastraImagemPerfil(cpf, imagem);
        } catch (ExceptionDAO e) {
            System.out.println("erro ao cadastrar imagem perfil");
        }

        return false;
    }

    public byte[] getImagemPerfil(String cpf) {

        byte[] imgNula = new byte[0];

        try {

            return new Cliente().recuperaImagemPerfil(cpf);

        } catch (ExceptionDAO e) {
            System.out.println(e);
        }
        return imgNula;
    }

    public List<Cliente> listarAniversariantesDoMes() {

        try {
            return new clienteDAO().listarAniversariantesDoMes();
        } catch (ExceptionDAO e) {
            System.out.println(e);
        }
        return null;
    }
}
