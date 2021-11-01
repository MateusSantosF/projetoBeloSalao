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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class ClienteController {

    public boolean cadastrarCliente(String NOME, String SOBRENOME, String EMAIL, String DATANASC,
            String CEP, String BAIRRO, String RUA, String CIDADE, String NUMERO,
            String TELEFONE, String CELULAR) {

        if (NOME != null && NOME.length() > 0 && NUMERO.length() <= 6) {
                 
            
          
            
             if(CELULAR.replaceAll(" ", "").length() != 14){
                CELULAR = null;
            }
            
            if(DATANASC.replaceAll(" ","").length() == 10){
                if(Valida.isData(DATANASC) == false){
                    return false;
                }
            }else{
                DATANASC = null;
            }
            if(EMAIL.length() > 0){
                if(Valida.isEmail(EMAIL) == false){
                    return false;
                }
            }
            
            
            //Formatador
            //DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/M/uuuu");
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
            //Convertendo datas de String para Date
            LocalDate dataReg = LocalDate.now();
            //objeto cliente
            Cliente cliente = new Cliente(NOME.trim(), SOBRENOME.trim(), EMAIL.trim(), DATANASC, CEP,
                    BAIRRO, RUA.trim(), CIDADE.trim(), NUMERO.trim(), TELEFONE, CELULAR, dataReg);
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
    
    public boolean cadastrarCliente(Cliente cliente){
        try {
            cliente.cadastrarCliente(cliente);
        } catch (ExceptionDAO e) {
        }
        return false;
    }
    
    public boolean atualizarCliente(String NOME, String SOBRENOME, String EMAIL, String DATANASC,
            String CEP, String BAIRRO, String RUA, String CIDADE, String NUMERO,
            String TELEFONE, String CELULAR, long id) {

        if (NOME != null && NOME.length() > 0 && NUMERO.length() <= 6) {

            if(DATANASC.replaceAll(" ","").length() == 10){
                if(Valida.isData(DATANASC) == false){
                    return false;
                }
            }else{
                DATANASC = null;
            }
            if(CELULAR.replaceAll(" ", "").length() != 14){
                CELULAR = null;
            }
            
            if(EMAIL.length() > 0){
                if(Valida.isEmail(EMAIL) == false){
                    return false;
                }
            }
            
      
             
            //Formatador
            DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/M/uuuu");

            //objeto cliente
            
            Cliente cliente = new Cliente(NOME.trim(), SOBRENOME.trim(), EMAIL.trim(), DATANASC, CEP,
                    BAIRRO.trim(), RUA.trim(), CIDADE.trim(), NUMERO, TELEFONE, CELULAR);
            cliente.setId(id);
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
            System.out.println("Erro ao verificar existencia do cliente");
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

    public Cliente buscarCliente(long id) {
        try {
            return new Cliente().buscarCliente(id);
        } catch (ExceptionDAO e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public boolean excluirCliente(long id) {

        try {
            Cliente c = new Cliente();
            c.excluirCliente(id);

        } catch (ExceptionDAO e) {
            JOptionPane.showInputDialog(null, "Erro Controller, excluir cliente");
            return false;
        }

        return true;
    }

    public boolean editarCliente(long id) {

        try {
            Cliente c = new Cliente();
            Cliente clienteEditado;

            clienteEditado = c.editarCliente(id);

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

    public LocalDate ultimaVisita(long id) {

        try {
            return new Cliente().ultimaVisita(id);
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

    public boolean cadastraImagemPerfil(long id, byte[] imagem) {

        try {
            return new Cliente().cadastraImagemPerfil(id, imagem);
        } catch (ExceptionDAO e) {
            System.out.println("erro ao cadastrar imagem perfil");
        }

        return false;
    }

    public byte[] getImagemPerfil(long id) {

        byte[] imgNula = new byte[0];

        try {

            return new Cliente().recuperaImagemPerfil(id);

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

    public void atualizarUltimoEnvioEmailAniversario(long id) {
        try {
            new Cliente().atualizarUltimoEnvioEmailAniversario(id);
        } catch (ExceptionDAO e) {
            System.out.println("Erro ao atualizar ultiimo envio de email aniversario");
        }

    }

    public List<Cliente> listaClientesEmailUltimaVisita() {
        try {
            return new Cliente().listaClientesEmailUltimaVisita();
        } catch (ExceptionDAO e) {
            System.out.println("Erro ao listar emails de ultima visita" + e);
        }
        return null;
    }

    public void atualizarUltimoEnvioEmailUltimaVisita(long id) {
        try {
            new Cliente().atualizarUltimoEnvioEmailUltimaVisita(id);
        } catch (ExceptionDAO e) {
            System.out.println("Erro ao atualizar ultiimo envio de email de ultima visita" + e);
        }
    }
}
