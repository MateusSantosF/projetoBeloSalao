package BeutifulSalon.model;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.dao.clienteDAO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Mateus
 */
public class Cliente {

    private long id;
    private String cpf;
    private String nome;
    private String sobrenome;
    private String email;
    private String dataNasc;
    private LocalDate dataDeRegistro; //Data que o cliente foi registrado no sistema
    private LocalDate ultimaVisita;
    private String cep;
    private String bairro;
    private String rua;
    private String cidade;
    private String numeroDaCasa;
    private String telefoneResidencial;
    private String celular;
    private int qtdVisitas;
    private int tipoDeCabelo;
    private int tamanhoCabelo;
    private int deOndeConheceu;
    private String corCabelo;
    private String facebook;
    private String instagram;
    private String observacoes;

    //construtor padrão
    public Cliente() {
    }

    //Construtor não padrão
    public Cliente(String CPF, String NOME, String SOBRENOME, String EMAIL, String DATANASC,
            String CEP, String BAIRRO, String RUA, String CIDADE, String NUMERO,
            String TELEFONE, String CELULAR, LocalDate DATAREG) {
        this.cpf = CPF;
        this.nome = NOME;
        this.sobrenome = SOBRENOME;
        this.email = EMAIL;
        this.dataNasc = DATANASC;
        this.cep = CEP;
        this.bairro = BAIRRO;
        this.rua = RUA;
        this.numeroDaCasa = NUMERO;
        this.cidade = CIDADE;
        this.telefoneResidencial = TELEFONE;
        this.celular = CELULAR;
        this.dataDeRegistro = DATAREG;
    }

    public Cliente(String CPF, String NOME, String SOBRENOME, String EMAIL, String DATANASC,
            String CEP, String BAIRRO, String RUA, String CIDADE, String NUMERO,
            String TELEFONE, String CELULAR) {
        this.cpf = CPF;
        this.nome = NOME;
        this.sobrenome = SOBRENOME;
        this.email = EMAIL;
        this.dataNasc = DATANASC;
        this.cep = CEP;
        this.bairro = BAIRRO;
        this.rua = RUA;
        this.numeroDaCasa = NUMERO;
        this.cidade = CIDADE;
        this.telefoneResidencial = TELEFONE;
        this.celular = CELULAR;

    }

    //Cosntrutor para a tela de FluxoDeCaixa
    public Cliente(String nome, String CPF) {
        this.nome = nome;
        this.cpf = CPF;
    }

    public int getDeOndeConheceu() {
        return deOndeConheceu;
    }

    public LocalDate getUltimaVisita() {
        return ultimaVisita;
    }

    public void setUltimaVisita(LocalDate ultimaVisita) {
        this.ultimaVisita = ultimaVisita;
    }

    public int getQtdVisitas() {
        return qtdVisitas;
    }

    public void setQtdVisitas(int qtdVisitas) {
        this.qtdVisitas = qtdVisitas;
    }
    
    
    
    public void setDeOndeConheceu(int deOndeConheceu) {
        this.deOndeConheceu = deOndeConheceu;
    }

    public int getTipoDeCabelo() {
        return tipoDeCabelo;
    }

    public void setTipoDeCabelo(int tipoDeCabelo) {
        this.tipoDeCabelo = tipoDeCabelo;
    }

    public int getTamanhoCabelo() {
        return tamanhoCabelo;
    }

    public void setTamanhoCabelo(int tamanhoCabelo) {
        this.tamanhoCabelo = tamanhoCabelo;
    }

    public String getCorCabelo() {
        return corCabelo;
    }

    public void setCorCabelo(String corCabelo) {
        this.corCabelo = corCabelo;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public LocalDate getDataDeRegistro() {
        return dataDeRegistro;
    }

    public void setDataDeRegistro(LocalDate dataDeRegistro) {
        this.dataDeRegistro = dataDeRegistro;
    }

    //Getters and Setters
    public String getNumeroDaCasa() {
        return numeroDaCasa;
    }

    public void setNumeroDaCasa(String numeroDaCasa) {
        this.numeroDaCasa = numeroDaCasa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s\nSobrenome: %s\nEmail: %s\nData Nasc:",
                getNome(), getSobrenome(), getEmail(), getDataNasc());
    }

    //Cadastrar clientes
    public void cadastrarCliente(Cliente cliente) throws ExceptionDAO {
        new clienteDAO().cadastrarCliente(cliente);
    }

    //Atualizar Clientes
    public void atualizarCliente(Cliente cliente) throws ExceptionDAO {
        new clienteDAO().atualizarCliente(cliente);
    }

    //Listar cliente pelo nome
    public List<Cliente> listarClientes(String nome) throws ExceptionDAO {

        return new clienteDAO().listarClientes(nome);

    }

    //Listar todos os clientes do sistema
    public List<Cliente> listarClientes() throws ExceptionDAO {
        return new clienteDAO().listarClientes();
    }
    //Busca cliente por cpf

    public Cliente buscarCliente(long id) throws ExceptionDAO {
        return new clienteDAO().buscarCliente(id);
    }

    //Excluir cliente
    public void excluirCliente(long id) throws ExceptionDAO {
        new clienteDAO().deletarCliente(id);
    }

    public Cliente editarCliente(long id) throws ExceptionDAO {
        return new clienteDAO().editarCliente(id);
    }

    public LocalDate ultimaVisita(long id) throws ExceptionDAO {
        return new clienteDAO().ultimaVisita(id);
    }

    public boolean verificaExistenciaCliente(String cpf) throws ExceptionDAO {
        return new clienteDAO().verificaExistenciaCliente(cpf);
    }

    public void atualizarDetalhesCliente(Cliente cliente) throws ExceptionDAO {
        new clienteDAO().atualizarDetalhesCliente(cliente);
    }

    public List<Cliente> top5Clientes(int anoReferente) throws ExceptionDAO {
        return new clienteDAO().top5Clientes(anoReferente);
    }

    public boolean cadastraImagemPerfil(long id, byte[] imagem) throws ExceptionDAO {

        return new clienteDAO().cadastraImagemPerfil(id, imagem);
    }

    public byte[] recuperaImagemPerfil(long id) throws ExceptionDAO {
        return new clienteDAO().recuperaImagemPerfil(id);
    }
    
    public List<Cliente> listarAniversariantesDoMes() throws ExceptionDAO{
        return new clienteDAO().listarAniversariantesDoMes();
    }

    public void atualizarUltimoEnvioEmailAniversario(long id) throws ExceptionDAO {
        new clienteDAO().atualizarUltimoEnvioEmailAniversario(id);
    }

    public void atualizarUltimoEnvioEmailUltimaVisita(long id) throws ExceptionDAO {
        new clienteDAO().atualizarUltimoEnvioEmailUltimaVisita(id);
    }
    
   public List<Cliente> listaClientesEmailUltimaVisita() throws ExceptionDAO{
      return new clienteDAO().listaClientesEmailUltimaVisita();
   }

}
