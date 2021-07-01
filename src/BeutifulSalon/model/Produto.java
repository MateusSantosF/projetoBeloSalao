package BeutifulSalon.model;


import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.dao.produtoDAO;
import java.util.Date;
//import BeutifulSalon.dao.produtoDAO;

/**
 *
 * @author Melissa
 */

//MODEL

public class Produto {
    private String nome; 
    private String marca;
    private long preco; 
    private Date dataReg; //Data que o produto foi registrado no sistema
    private Date dataValidade; //Data de validade do produto 
    
    //construtor padrão
    public Produto(){}
    
    //Construtor não padrão
    public Produto(String nome, String marca, long preco, Date dataReg, Date dataValidade) {
        this.nome = nome; 
        this.marca = marca;
        this.preco = preco; 
        this.dataReg = dataReg;
        this.dataValidade = dataValidade;
    }
    
    //Getters & setters gerados 

   
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

    public Date getDataReg() {
        return dataReg;
    }

    public void setDataReg(Date dataReg) {
        this.dataReg = dataReg;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }
    
    //passar produto como parâmetro e mandar para DAo onde vai acessar o BD e salvar o produto
    
    public void cadastrarProduto(Produto produto) throws ExceptionDAO{
        new produtoDAO().cadastrarProduto(produto);
    }
    
  //salvar
    
    public boolean Salvar(){
        return true;
    }

    /*
     //Listar todos os produtos do sistema
    public ArrayList<Cliente> listarProdutos(){
        return new produtoDAO().cadastrarProduto();
    }
    
    //Excluir produto
    public void excluirProduto(String poduto) throws ExceptionDAO{
        new produtoDAO().deletarProduto(produto);
    }
    
    public void editarProduto(String produto) throws ExceptionDAO{
        new produtoDAO().editarProduto(produto);
    }

    */
}
