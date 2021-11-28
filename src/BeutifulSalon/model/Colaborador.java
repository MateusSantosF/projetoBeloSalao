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
package BeutifulSalon.model;

import BeutifulSalon.dao.ColaboradorDAO;
import BeutifulSalon.dao.ExceptionDAO;

/**
 *
 * @author Mateus
 */
public class Colaborador {

    private int qtdRealizada;
    private String nome;
    private Long porcentagemComisao;
    private long idColaborador;
    private boolean comissionado;
    private boolean comissaoPorLucro;
    private boolean comissaoPorQtd;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getPorcentagemComisao() {
        return porcentagemComisao;
    }

    public void setPorcentagemComisao(Long porcentagemComisao) {
        this.porcentagemComisao = porcentagemComisao;
    }

    public long getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(long idColaborador) {
        this.idColaborador = idColaborador;
    }

    public boolean isComissionado() {
        return comissionado;
    }

    public void setComissionado(boolean comissionado) {
        this.comissionado = comissionado;
    }

    public boolean isComissaoPorLucro() {
        return comissaoPorLucro;
    }

    public void setComissaoPorLucro(boolean comissaoPorLucro) {
        this.comissaoPorLucro = comissaoPorLucro;
    }

    public boolean isComissaoPorQtd() {
        return comissaoPorQtd;
    }

    public void setComissaoPorQtd(boolean comissaoPorQtd) {
        this.comissaoPorQtd = comissaoPorQtd;
    }

    public boolean cadastrarColaborador(Colaborador c) throws ExceptionDAO {
        return new ColaboradorDAO().cadastrarColaborador(c);
    }

    public boolean cadastrarColaboradorCabeleireiro(Colaborador c) {
        return new ColaboradorDAO().cadastrarColaboradorCabeleireiro(c);
    }

    public boolean atualizarColaborador(Colaborador colaborador) throws ExceptionDAO {
        return new ColaboradorDAO().atualizarColaborador(colaborador);
    }

    public int getQtdRealizada() {
        return qtdRealizada;
    }

    public void setQtdRealizada(int qtdRealizada) {
        this.qtdRealizada = qtdRealizada;
    }

}
