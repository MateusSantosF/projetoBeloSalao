/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.controller.ClienteController;
import BeutifulSalon.controller.ProdutoController;
import BeutifulSalon.controller.ServicoController;
import BeutifulSalon.model.Cliente;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Produto;
import BeutifulSalon.model.Servico;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author mateu
 */
public class PopulaBanco {
    
    ClienteController cc = new ClienteController();
    ServicoController sc = new ServicoController();
    ProdutoController pc = new ProdutoController();
    
    
    
    public void CadastrarClientes(){
        
        BufferedReader br = null;
        String linha = "";
         try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\mateu\\Desktop\\FrankeBiancaCSV.CSV"), StandardCharsets.ISO_8859_1));

            br.readLine();
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
    
    private Cliente criaCliente(String linha){
        
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
        String cpf = "";
        
        if(dados[0] != "" && dados[0] != " " && dados[0] != null){
            
            dados[0] = dados[0].replaceAll(" ", " "); 
            String[] nomes = dados[0].split(" ");
            nome = nomes[0];
            for( int i = 1; i < nomes.length; i++){
                sobrenome +=" "+ nomes[i];
            }
        }
      
        if(dados.length  >= 2){
            if(dados[1] != "" && dados[1] != " " && dados[1] != null){
             dataNascimento = LocalDate.parse(dados[1].toLowerCase(), df).format(df2).toString();
            } 
        }
        
        if(dados.length >= 3){
           if(dados[2] != "" && dados[2] != " " && dados[2] != null){
                celular = dados[2];
            }      
        }
        
        if(dados.length >= 4){
           if(dados[3] != "" && dados[3] != " " && dados[3] != null){
                telefone = dados[3];
            }      
        }
        
        if(dados.length >= 5){
           if(dados[4] != "" && dados[4] != " " && dados[4] != null){
                email = dados[4];
            }      
        }
        
        if(dados.length >= 6){
           if(dados[5] != "" && dados[5] != " " && dados[5] != null){
                cpf = dados[5];
            }      
        }
     
        cliente.setNome(nome);
        cliente.setSobrenome(sobrenome);
        cliente.setCelular(celular);
        cliente.setTelefoneResidencial(telefone);
        cliente.setDataNasc(dataNascimento);
        cliente.setCpf(cpf);
        cliente.setEmail(email);
        cliente.setDataDeRegistro(LocalDate.now());
       
      return cliente;

    }
    public void CadastrarServicos(){
        
        BufferedReader br = null;
        String linha = "";
         try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\mateu\\Desktop\\testesssss.txt.CSV"), StandardCharsets.ISO_8859_1));

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
    
    
    
    private Servico criaServico(String linha){
      
        Servico servico = new Servico();
        ArrayList<Produto> produtos = new ArrayList<>();
        
        String[] dados = linha.split(";");
        String nomeServico = "";
        long precoServico = 0;
        LocalTime duracao = null;
        
        
        //nome
        if(dados[0] != "" && dados[0] != " " && dados[0] != null){
            
            dados[0] = dados[0].replaceAll(" ", " "); 
            nomeServico = dados[0]; 
        }
        
        //preço
        if(dados.length >= 2){
            if(dados[1] != "" && dados[1] != " " && dados[1] != null){
                
                precoServico = Dinheiro.parseCent(Dinheiro.retiraCaracteres(dados[1]));
            }
        }
        
        //duração
        if (dados.length >= 3) {
            if (dados[2] != "" && dados[2] != " " && dados[2] != null) {

               
                int formatado = Integer.valueOf(dados[2].replaceAll(" min", ""));
                int minutos = formatado  % 60;
                int horas = (formatado - minutos) / 60;
                LocalTime t = LocalTime.of(horas, minutos);     
                duracao = t;
            }
        }
        
        if(dados.length >= 4){
            
            for( int i = 3; i < dados.length; i+= 3){
                Produto p = new Produto();
                
                if(dados[i] != "" && dados[i] != " " && dados[i] != null){
                    p.setNome(dados[i]);
                }
                if(dados[i+1] != "" && dados[i+1] != " " && dados[i+1] != null){
                    p.setMarca(dados[i+1]);
                }
                
                 if(dados[i+2] != "" && dados[i+2] != " " && dados[i+2] != null){
                     p.setRendimento(Integer.valueOf(dados[i+2])); 
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
    
    public static void main(String[] args) {
        //new PopulaBanco().CadastrarClientes();
        new PopulaBanco().CadastrarServicos();
    }
    
  
    
}

