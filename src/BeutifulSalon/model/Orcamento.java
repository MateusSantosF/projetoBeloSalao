package BeutifulSalon.model;

import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.dao.OrcamentoDAO;
import java.util.ArrayList;

/**
 *
 * @author Mateus
 */
public class Orcamento {

    private boolean previsto; //previsto(true) | realizado(false)
    private long id_orcamento;
    private String nome;
    private String ano;
    private long jan;
    private long fev;
    private long mar;
    private long abr;
    private long mai;
    private long jun;
    private long jul;
    private long ago;
    private long set;
    private long out;
    private long nov;
    private long dez;

    public Orcamento() {
    };

    

    public Orcamento(boolean previsto, String nome, long jan, long fev, long mar,
            long abr, long mai, long jun, long jul, long ago, long set, long out, long nov, long dez, String ano) {
        this.previsto = previsto;
        this.nome = nome;
        this.jan = jan;
        this.fev = fev;
        this.mar = mar;
        this.abr = abr;
        this.mai = mai;
        this.jun = jun;
        this.jul = jul;
        this.ago = ago;
        this.set = set;
        this.out = out;
        this.nov = nov;
        this.dez = dez;
        this.ano = ano;
    }

    public Orcamento(boolean previsto, String nome, long jan, long fev, long mar,
            long abr, long mai, long jun, long jul, long ago, long set, long out, long nov, long dez, String ano, long id_orcamento) {
        this.previsto = previsto;
        this.nome = nome;
        this.jan = jan;
        this.fev = fev;
        this.mar = mar;
        this.abr = abr;
        this.mai = mai;
        this.jun = jun;
        this.jul = jul;
        this.ago = ago;
        this.set = set;
        this.out = out;
        this.nov = nov;
        this.dez = dez;
        this.ano = ano;
        this.id_orcamento = id_orcamento;
    }

    public long getId_orcamento() {
        return id_orcamento;
    }

    public void setId_orcamento(long id_orcamento) {
        this.id_orcamento = id_orcamento;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public boolean isPrevisto() {
        return previsto;
    }

    public void setPrevisto(boolean previsto) {
        this.previsto = previsto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getJan() {
        return jan;
    }

    public void setJan(long jan) {
        this.jan = jan;
    }

    public long getFev() {
        return fev;
    }

    public void setFev(long fev) {
        this.fev = fev;
    }

    public long getMar() {
        return mar;
    }

    public void setMar(long mar) {
        this.mar = mar;
    }

    public long getAbr() {
        return abr;
    }

    public void setAbr(long abr) {
        this.abr = abr;
    }

    public long getMai() {
        return mai;
    }

    public void setMai(long mai) {
        this.mai = mai;
    }

    public long getJun() {
        return jun;
    }

    public void setJun(long jun) {
        this.jun = jun;
    }

    public long getJul() {
        return jul;
    }

    public void setJul(long jul) {
        this.jul = jul;
    }

    public long getAgo() {
        return ago;
    }

    public void setAgo(long ago) {
        this.ago = ago;
    }

    public long getSet() {
        return set;
    }

    public void setSet(long set) {
        this.set = set;
    }

    public long getOut() {
        return out;
    }

    public void setOut(long out) {
        this.out = out;
    }

    public long getNov() {
        return nov;
    }

    public void setNov(long nov) {
        this.nov = nov;
    }

    public long getDez() {
        return dez;
    }

    public void setDez(long dez) {
        this.dez = dez;
    }

    public void cadastrarOrcamento(Orcamento orcamento) throws ExceptionDAO {
        new OrcamentoDAO().cadastrarOrcamento(orcamento);
    }

    public ArrayList<Orcamento> listarOrcamentos() throws ExceptionDAO {

        return new OrcamentoDAO().listarOrcamento();
    }
    
    public ArrayList<Orcamento> listarOrcamentoPorNome(String nome) throws ExceptionDAO {

        return new OrcamentoDAO().listarOrcamentoPorNome(nome);
    }

    public ArrayList<Orcamento> listarOrcamentos(String ano) throws ExceptionDAO {

        return new OrcamentoDAO().listarOrcamento(ano);
    }

    public long somarOrcamento(String mes, String ano) throws ExceptionDAO {
        return new OrcamentoDAO().somarOrcamento(mes, ano);
    }

    public boolean excluirOrcamento(long id_orcamento) throws ExceptionDAO {
       return new OrcamentoDAO().excluirOrcamento(id_orcamento);
    }

    public void editarOrcamento(long id_orcamento) throws ExceptionDAO {
        new OrcamentoDAO().editarOrcamento(id_orcamento);
    }

    public void AtualizarOrcamento(Orcamento orc) {
        new OrcamentoDAO().atualizarOrcamento(orc);
    }
    
    public Orcamento buscarOrcamento(long idOrcamento){
        return new OrcamentoDAO().buscarOrcamento(idOrcamento);
    }



}
