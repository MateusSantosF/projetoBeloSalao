
package BeutifulSalon.model;

import BeutifulSalon.dao.ExceptionDAO;
import java.util.Date;
import BeutifulSalon.dao.clienteDAO;
import java.util.ArrayList;

/**
 *
 * @author Mateus
 */
public class Cliente {
    
    private String CPF;
    private String NOME; 
    private String SOBRENOME;
    private String EMAIL;
    private Date DATANASC; 
    private String CEP; 
    private String BAIRRO;
    private String RUA; 
    private String CIDADE; 
    private String NUMERO;
    private String TELEFONE; 
    private String CELULAR; 
    private Date DATAREG; //Data que o cliente foi registrado no sistema
    
    //construtor padrão
    public Cliente(){}
    
    //Construtor não padrão
    public Cliente(String CPF, String NOME, String SOBRENOME, String EMAIL, Date DATANASC, 
            String CEP, String BAIRRO, String RUA, String CIDADE,String NUMERO,
            String TELEFONE, String CELULAR, Date DATAREG) {
        this.CPF = CPF;
        this.NOME = NOME;
        this.SOBRENOME = SOBRENOME;
        this.EMAIL = EMAIL;
        this.DATANASC = DATANASC;
        this.CEP = CEP;
        this.BAIRRO = BAIRRO;
        this.RUA = RUA;
        this.NUMERO = NUMERO;
        this.CIDADE = CIDADE;
        this.TELEFONE = TELEFONE;
        this.CELULAR = CELULAR;
        this.DATAREG = DATAREG;
    }
    
      public Cliente(String CPF, String NOME, String SOBRENOME, String EMAIL, Date DATANASC, 
            String CEP, String BAIRRO, String RUA, String CIDADE,String NUMERO,
            String TELEFONE, String CELULAR) {
        this.CPF = CPF;
        this.NOME = NOME;
        this.SOBRENOME = SOBRENOME;
        this.EMAIL = EMAIL;
        this.DATANASC = DATANASC;
        this.CEP = CEP;
        this.BAIRRO = BAIRRO;
        this.RUA = RUA;
        this.NUMERO = NUMERO;
        this.CIDADE = CIDADE;
        this.TELEFONE = TELEFONE;
        this.CELULAR = CELULAR;

    }
      
      
      //Cosntrutor para a tela de FluxoDeCaixa
    public Cliente (String nome, String CPF){
        this.NOME = nome;
        this.CPF = CPF;
    }

    public Date getDATAREG() {
        return DATAREG;
    }

    public void setDATAREG(Date DATAREG) {
        this.DATAREG = DATAREG;
    }
    
    
    //Getters and Setters
    public String getNUMERO() {
        return NUMERO;
    }

    public void setNUMERO(String NUMERO) {
        this.NUMERO = NUMERO;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNOME() {
        return NOME;
    }

    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    public String getSOBRENOME() {
        return SOBRENOME;
    }

    public void setSOBRENOME(String SOBRENOME) {
        this.SOBRENOME = SOBRENOME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public Date getDATANASC() {
        return DATANASC;
    }

    public void setDATANASC(Date DATANASC) {
        this.DATANASC = DATANASC;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getBAIRRO() {
        return BAIRRO;
    }

    public void setBAIRRO(String BAIRRO) {
        this.BAIRRO = BAIRRO;
    }

    public String getRUA() {
        return RUA;
    }

    public void setRUA(String RUA) {
        this.RUA = RUA;
    }

    public String getCIDADE() {
        return CIDADE;
    }

    public void setCIDADE(String CIDADE) {
        this.CIDADE = CIDADE;
    }

    public String getTELEFONE() {
        return TELEFONE;
    }

    public void setTELEFONE(String TELEFONE) {
        this.TELEFONE = TELEFONE;
    }

    public String getCELULAR() {
        return CELULAR;
    }

    public void setCELULAR(String CELULAR) {
        this.CELULAR = CELULAR;
    }
    
    
    @Override
    public String toString(){
        return String.format("Nome: %s\nSobrenome: %s\nEmail: %s\nData Nasc:",
                getNOME(),getSOBRENOME(), getEMAIL(), getDATANASC());
    }
    
    
    //Cadastrar clientes
    public void cadastrarCliente(Cliente cliente) throws Exception{
        new clienteDAO().cadastrarCliente(cliente);

    }
    
    //Atualizar Clientes
    public void atualizarCliente(Cliente cliente) throws ExceptionDAO{
        new clienteDAO().atualizarCliente(cliente);
    }
    
    //Listar cliente pelo nome
    public ArrayList<Cliente> listarClientes(String nome) throws ExceptionDAO{
        
        return new clienteDAO().listarClientes(nome);
       
    }
    //Listar todos os clientes do sistema
    public ArrayList<Cliente> listarClientes(){
        return new clienteDAO().listarClientes();
    }
    
    //Excluir cliente
    public void excluirCliente(String cpf) throws ExceptionDAO{
        new clienteDAO().deletarCliente(cpf);
    }
    
    public Cliente editarCliente(String cpf) throws ExceptionDAO{
        return new clienteDAO().editarCliente(cpf);
    }
    
}
