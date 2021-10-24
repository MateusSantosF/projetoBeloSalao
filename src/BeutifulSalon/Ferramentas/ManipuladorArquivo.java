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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mateus
 */
public class ManipuladorArquivo {

    private float TAMANHO_FONTE = 1;
    //Para compilar na dist
    private String diretorio = "conf.txt";
    private String netbeans = "C:\\Users\\Visitante\\Documents\\IF_BeautifulSalon\\pNovo\\projetoBeloSalao\\projetoBeloSalao\\src\\conf.txt";

    public void leitor() {
        diretorio = netbeans;
        File file = new File(diretorio);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ManipuladorArquivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        FileReader reader = null;

        try {
            reader = new FileReader(file);
        } catch (IOException ex) {
        }
        BufferedReader bufferedReader = null;
        bufferedReader = new BufferedReader(reader);

        String linha = "";

        try {
            while ((linha = bufferedReader.readLine()) != null) {

                if (linha.contains("FONT-SIZE@")) {
                    TAMANHO_FONTE = Float.valueOf(linha.substring(10, linha.length()));
                }
            }
        } catch (IOException ex) {
        }

        try {
            bufferedReader.close();
        } catch (IOException ex) {
        }
    }

    public void escritor(float tamanho) {
    diretorio = netbeans;
        BufferedWriter bufferedReader = null;
        File file = new File(diretorio);
        FileWriter writer = null;

        try {
            writer = new FileWriter(file, false);
        } catch (IOException ex) {
        }
        bufferedReader = new BufferedWriter(writer);

        if (file.length() == 0) {
            try {
                bufferedReader.append("FONT-SIZE@" + tamanho);
            } catch (IOException ex) {
            }
        } else {

            try {

                file.delete();

                file = new File("C:\\Users\\Mateus\\Desktop\\BeloSalaoNEW\\src\\conf.txt");

                try {
                    writer = new FileWriter(file, true);
                } catch (IOException ex) {
                }
                bufferedReader = new BufferedWriter(writer);
                bufferedReader.append("FONT-SIZE@" + tamanho);
            } catch (IOException ex) {
            }
        }

        try {
            bufferedReader.close();
        } catch (IOException ex) {
        }

    }

    public float getTamanhoFonte() {
        leitor();
        return TAMANHO_FONTE;
    }
}
