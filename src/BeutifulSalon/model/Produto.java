package BeutifulSalon.model;


import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.dao.produtoDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
//import BeutifulSalon.dao.produtoDAO;

/**
 *
 * @author Melissa
 */

//MODEL

public class Produto {
    
    private long id_produto;
    private String nome; 
    private String marca;
    private long preco; 
    private LocalDate dataReg; //Data que o produto foi registrado no sistema
    private int rendimento;

    public int getRendimento() {
        return rendimento;
    }

    public void setRendimento(int rendimento) {
        this.rendimento = rendimento;
    }
    
    //construtor padrão
    public Produto(){}

   
    
    //Construtor não padrão
    public Produto(String nome, String marca, long preco, LocalDate dataReg) {
        this.nome = nome; 
        this.marca = marca;
        this.preco = preco; 
        this.dataReg = dataReg;
    }
    
    public Produto(String nome, String marca, long preco, long id_produto){
        this.nome = nome;
        this.marca = marca;
        this.preco = preco;
        this.id_produto = id_produto;
    }
    
    
    
    public Produto(String nome, String marca, long preco, LocalDate dataReg, long id_produto) {
        this.nome = nome; 
        this.marca = marca;
        this.preco = preco; 
        this.dataReg = dataReg;
        this.id_produto = id_produto;
    }

    
    //Getters & setters gerados 

    public long getId_produto() {
        return id_produto;
    }

    public void setId_produto(long id_produto) {
        this.id_produto = id_produto;
    }
   
   
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }
   
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public long getPreco() {
        return preco;
    }

    public void setPreco(long preco) {
        this.preco = preco;
    }

    public LocalDate getDataReg() {
        return dataReg;
    }

    public void setDataReg(LocalDate dataReg) {
        this.dataReg = dataReg;
    }

    
    //passar produto como parâmetro e mandar para DAo onde vai acessar o BD e salvar o produto
    
    public void cadastrarProduto(Produto produto) throws ExceptionDAO{
        new produtoDAO().cadastrarProduto(produto);
    }

 
    public ArrayList<Produto> listarProdutos() throws ExceptionDAO{
          return new produtoDAO().listarProdutos();
    }
    
    public ArrayList<Produto> listarProdutos(String nome) throws ExceptionDAO{
          return new produtoDAO().listarProdutos(nome);
    }

    public void excluirProduto(long idProdutoSelecionado) {
        new produtoDAO().deletarProduto(idProdutoSelecionado);   
    }

    public Produto editarProduto(long id_produto) {
        return new produtoDAO().editarProduto(id_produto);
    }

    public void atualizarProduto(Produto produto) throws ExceptionDAO{
       new produtoDAO().atualizarProduto(produto);
    }
    
    public Produto buscarProduto(long id) throws ExceptionDAO{
        return new produtoDAO().buscarProduto(id);
    }
}
