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
package BeutifulSalon.Ferramentas;

import BeutifulSalon.controller.AgendamentoController;
import BeutifulSalon.controller.CompraController;
import BeutifulSalon.controller.DespesaController;
import BeutifulSalon.controller.VendaController;
import BeutifulSalon.model.Dinheiro;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Mateus
 */
public class teste {

    public static void main(String[] args) throws JRException {

        Month m = Month.DECEMBER;

        double entrada, saida = 0;

        VendaController vc = new VendaController();
        AgendamentoController ag = new AgendamentoController();
        //saidas
        CompraController cc = new CompraController();
        DespesaController dc = new DespesaController();

        entrada = (double)(vc.selecionaVendasPorMes(m) + ag.retornaSomaDeLucrosAgendamentosMensal(m))/100;
        saida = (double)(cc.retornaSomaDeComprasMensais(m) + dc.retornaSomaDeDespesasMensais(m))/100;
        
        System.out.println("entrada=>" + entrada);
        System.out.println("saída" + saida);

    }
}
