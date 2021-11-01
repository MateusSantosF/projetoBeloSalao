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

import BeutifulSalon.controller.CabeleireiroController;
import BeutifulSalon.controller.RelatorioController;
import BeutifulSalon.model.Cabeleireiro;
import BeutifulSalon.model.Email;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class EmailAutomaticoRelatorio {
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate DATAULTIMOENVIO = LocalDate.now();


    private String diretorio = "sendEmail.txt";
    private String netbeans = "C:\\Users\\Mateus\\Desktop\\BeloSalaoNEW\\src\\sendEmail.txt";
    private String emailDestino = "beutifulsalontest@gmail.com";
    
    public void enviarRelatorio() {
        
        String diretorio = "";
        LocalDate dataAtual = LocalDate.now();
    
        LocalDate ultimoEnvio = getUltimoEnvioEmailRelatorios();
        Period tempoCorrido = dataAtual.until(ultimoEnvio);
        
        //verifica se a ultima data de envio não é igual a hoje, para n enviar novamente
        if (tempoCorrido.getDays() != 0 ) {
           
            if (tempoCorrido.getDays() <= -7 && dataAtual.isBefore(LocalDate.of(2022, Month.DECEMBER, 29))) {
                
                CabeleireiroController cc = new CabeleireiroController();
                RelatorioController rc = new RelatorioController();
                if (cc.verificaRegistro() == 1) {
                    System.out.println("Enviando email automático relatórios...");
                    Cabeleireiro cabeleireiro = cc.selecionaCabeleireiro();
                    Email relatorio = new Email();

                    //titulo email
                    relatorio.setTitulo("Relátorios " + cabeleireiro.getNome() + " - "
                            + ultimoEnvio.format(formatter) + " " + dataAtual.format(formatter));

                    //Corpo Email
                    relatorio.setTexto("Seguem em anexos os relatórios do salão do(a)" + cabeleireiro.getNome() + " no período"
                            + " referente a: " + ultimoEnvio.format(formatter) + " até " + dataAtual.format(formatter) + "."
                            + "\n\n"
                            + "Grato pela atenção.\n Att,\n\nBeautySalonApp");
                    
                    relatorio.setDestinatario(emailDestino);
                    relatorio.setRementente(cabeleireiro.getEmail());

                    //Criando relatórios          
                    String nomeAg = "agendamentos_" + cabeleireiro.getNome() + ".pdf";
                    String nomeDespesas = "despesas_" + cabeleireiro.getNome() + ".pdf";
                    String nomeVendas = "vendas_" + cabeleireiro.getNome() + ".pdf";
                    
                    rc.gerarRelatorioAgendamento(ultimoEnvio.format(formatter), dataAtual.format(formatter), diretorio + nomeAg);
                    rc.gerarRelatorioDespesas(ultimoEnvio.format(formatter), dataAtual.format(formatter), diretorio + nomeDespesas);
                    rc.gerarRelatorioVenda(ultimoEnvio.format(formatter), dataAtual.format(formatter), diretorio + nomeVendas);

                    //Criando arquivos dos relatórios
                    List<String> anexos = new ArrayList<>();
                    File rAgendamentos = new File(diretorio + nomeAg);
                    File rDespesas = new File(diretorio + nomeDespesas);
                    File rVendas = new File(diretorio + nomeVendas);
                    
                    try {
                        rAgendamentos.createNewFile();
                        rDespesas.createNewFile();
                        rVendas.createNewFile();
                    } catch (IOException ex) {
                        Logger.getLogger(EmailAutomaticoRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    anexos.add(rAgendamentos.getAbsolutePath());
                    anexos.add(rDespesas.getAbsolutePath());
                    anexos.add(rVendas.getAbsolutePath());
                    relatorio.setAnexos(anexos);
                    
                    boolean sucesso = false;
                    
                    try {
                        sucesso = relatorio.sendEmail(2);
                    } catch (MessagingException e) {
                        System.out.println(e);
                    }
                    if (sucesso) {
                        escritor(dataAtual);
                    }

                    //Deletando arquivos dos relatórios
                    rAgendamentos.delete();
                    rDespesas.delete();
                    rVendas.delete();
                    
                }
            }
        }
        
    }
    
    public void leitor() {
      
        File file = new File(diretorio);
        if (!file.exists()) {
            try {
                file.createNewFile();
                escritor(LocalDate.now().minusDays(1));
                JOptionPane.showMessageDialog(null, "Criou arquivo");
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
                
                if (Valida.isDataSemNotificar(linha)) {
                    
                    DATAULTIMOENVIO = LocalDate.parse(linha, formatter);
                    
                } else {
                    
                }
            }
        } catch (IOException ex) {
        }
        
        try {
            bufferedReader.close();
        } catch (IOException ex) {
        }
    }
    
 
    
    public void escritor(LocalDate data) {
        
        BufferedWriter bufferedReader = null;
        String novaData = data.format(formatter);
        File file = new File(diretorio);
        FileWriter writer = null;
        
        try {
            writer = new FileWriter(file, false);
        } catch (IOException ex) {
        }
        bufferedReader = new BufferedWriter(writer);
        
        if (file.length() == 0) {
            try {
                bufferedReader.append(novaData);
                
            } catch (IOException ex) {
            }
        } else {
            
            try {
                
                file.delete();
                
                file = new File(diretorio);
                
                try {
                    writer = new FileWriter(file, true);
                } catch (IOException ex) {
                }
                bufferedReader = new BufferedWriter(writer);
                bufferedReader.append(novaData);
            } catch (IOException ex) {
            }
        }
        
        try {
            bufferedReader.close();
        } catch (IOException ex) {
        }
        
    }
    
    public LocalDate getUltimoEnvioEmailRelatorios() {
        
        leitor();
        return DATAULTIMOENVIO;
    }
    
}
