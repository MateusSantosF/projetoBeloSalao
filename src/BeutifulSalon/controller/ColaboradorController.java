/*
 * The MIT License
 *
 * Copyright 2021 Mateus.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package BeutifulSalon.controller;

import BeutifulSalon.dao.ColaboradorDAO;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Colaborador;
import java.time.Month;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class ColaboradorController {

    public boolean cadastrarColaborador(Colaborador colaborador) {

        if (colaborador.getNome().length() <= 100) {

            if (colaborador.isComissionado()) {

                if (colaborador.getPorcentagemComisao() >= 0) {
                    try {
                        colaborador.cadastrarColaborador(colaborador);
                    } catch (ExceptionDAO e) {
                        JOptionPane.showMessageDialog(null, e);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Verique a comissão, e tente novamente!");
                    return false;
                }
            } else {
                try {
                    colaborador.cadastrarColaborador(colaborador);
                } catch (ExceptionDAO e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Nome do colaborador muito grande!");
            return false;
        }

        return true;
    }

    public boolean atualizarColaborador(Colaborador colaborador) {

        if (colaborador.getNome().length() <= 100) {
            colaborador.setNome(colaborador.getNome().trim());
            if (colaborador.isComissionado()) {

                try {
                    if (colaborador.getPorcentagemComisao() >= 0) {
                        return colaborador.atualizarColaborador(colaborador);
                    } else {
                        JOptionPane.showMessageDialog(null, "Você não pode ter uma comissão menor ou igual a zero!");
                    }
                } catch (ExceptionDAO e) {
                    JOptionPane.showMessageDialog(null, e);
                    return false;
                }

            } else {
                try {
                    return colaborador.atualizarColaborador(colaborador);
                } catch (ExceptionDAO e) {
                    JOptionPane.showMessageDialog(null, e);
                    return false;
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Nome do colaborador muito grande!");
            return false;
        }
        return false;
    }

    public boolean cadastrarColaboradorCabeleireiro(Colaborador c) {
        return cadastrarColaborador(c);
    }

    public List<Colaborador> listarColaboradores() {
        try {
            return new ColaboradorDAO().listarColaboradores();
        } catch (ExceptionDAO e) {
        }
        return null;
    }

    public boolean excluirColaborador(Colaborador c) {
        return new ColaboradorDAO().excluirColaborador(c);
    }

    public Colaborador buscarColaborador(long idColaborador) {
        return new ColaboradorDAO().buscarColaborador(idColaborador);
    }

    public int getQuantidadeRealizada(Colaborador c, Month mes) {

        try {
            return new ColaboradorDAO().getQuantidadeRealizada(c, mes);
        } catch (ExceptionDAO e) {
            System.out.println("Erro ao retornar quantidade realizada de agendamentos de cada colaborador");
        }
        return 0;
    }

}
