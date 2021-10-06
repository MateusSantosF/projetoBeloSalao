/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.controller;

import BeutifulSalon.Ferramentas.Valida;
import BeutifulSalon.dao.CabeleireiroDAO;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Cabeleireiro;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Email;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class CabeleireiroController {

    public boolean cadastrarCabeleireiro(String nome, String cpf, String email, ArrayList<LocalTime> expediente, char[] senha, String metaDeLucro) {

        if (nome.length() > 0 && Valida.isCpf(cpf) && Valida.isEmail(email) && !expediente.isEmpty() && expediente.size() == 14) {

            try {
                Cabeleireiro cabeleireiro = new Cabeleireiro(cpf, nome.trim(), email.trim(), expediente);
                cabeleireiro.setSenha(String.copyValueOf(senha));
                cabeleireiro.setMetaDeLucro(Dinheiro.parseCent(Dinheiro.retiraCaracteres(metaDeLucro)));

                cabeleireiro.cadastrarCabeleireiro(cabeleireiro);
            } catch (ExceptionDAO e) {
                JOptionPane.showMessageDialog(null, "Controller" + e);
            }

        } else {
            return false;
        }

        return true;
    }

    public boolean atualizarCabeleireiro(String nome, String cpf, String email, ArrayList<LocalTime> expediente, char[] senha, String metaDeLucro) {

        if (nome.length() > 0 && Valida.isCpf(cpf) && Valida.isEmail(email) && !expediente.isEmpty() && expediente.size() == 14) {

            try {
                Cabeleireiro cabeleireiro = new Cabeleireiro(cpf, nome.trim(), email.trim(), expediente);
                cabeleireiro.setSenha(String.copyValueOf(senha));
                cabeleireiro.setMetaDeLucro(Dinheiro.parseCent(Dinheiro.retiraCaracteres(metaDeLucro)));
                cabeleireiro.atualizarCabeleireiro(cabeleireiro);

            } catch (ExceptionDAO e) {

                JOptionPane.showMessageDialog(null, "Controller" + e);
                return false;
            }

            return true;

        } else {
            return false;
        }

    }

    public Cabeleireiro selecionaCabeleireiro() {
        try {
            return new Cabeleireiro().selecionaCabeleireiro();
        } catch (ExceptionDAO e) {
            System.out.println("erro ao recuperar cabeleireiro no sistema.");
        }
        return null;
    }

    public int verificaRegistro() {
        return new Cabeleireiro().verificaRegistro();
    }

    public ArrayList<LocalTime> selecionaExpediente(int diaDaSemana) {
        return new Cabeleireiro().selecionaExpediente(diaDaSemana);
    }

    public boolean cadastrarEmailPadraoAniversario(Email email, String cpf) {

        if (email.getTitulo().length() > 0 && email.getTexto().length() > 24) {
            try {
                new Cabeleireiro().cadastrarEmailPadraoAniversario(email, cpf);
                return true;
            } catch (ExceptionDAO e) {
                System.out.println("Erro ao cadastrarEmail" + e);
                return false;
            }
        } else {
            return false;
        }

    }

    public boolean cadastrarEmailUltimaVisita(Email email, String cpf, int periodo) {

        if (email.getTitulo().length() > 0 && email.getTexto().length() > 24) {
            try {
                new Cabeleireiro().cadastrarEmailUltimaVisita(email, cpf, periodo);
                return true;
            } catch (ExceptionDAO e) {
                System.out.println("Erro ao cadastrarEmailUltimaVisita" + e);
                return false;
            }
        } else {
            return false;
        }

    }

    public boolean atualizarPreferencias(boolean verificar, int tempoEntreAgendamentos) {
        
        try {
            new CabeleireiroDAO().atualizarPreferencias(verificar, tempoEntreAgendamentos);
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao atualizar preferencias do sistema");
            return false;
        }
    }

}
