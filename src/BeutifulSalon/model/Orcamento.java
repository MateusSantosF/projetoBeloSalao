
package BeutifulSalon.model;

/**
 *
 * @author Mateus
 */
public class Orcamento {
    
    private boolean previsto; //previsto ou realizado
    private String nome;
    private double jan;
    private double fev;
    private double mar;
    private double abr;
    private double mai;
    private double jun;
    private double jul;
    private double ago;
    private double set;
    private double out;
    private double nov;
    private double dez;
    
    public Orcamento(){};

    public Orcamento(boolean previsto, String nome, double jan, double fev, double mar, double abr, double mai, double jun, double jul, double ago, double set, double out, double nov, double dez) {
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

    public double getJan() {
        return jan;
    }

    public void setJan(double jan) {
        this.jan = jan;
    }

    public double getFev() {
        return fev;
    }

    public void setFev(double fev) {
        this.fev = fev;
    }

    public double getMar() {
        return mar;
    }

    public void setMar(double mar) {
        this.mar = mar;
    }

    public double getAbr() {
        return abr;
    }

    public void setAbr(double abr) {
        this.abr = abr;
    }

    public double getMai() {
        return mai;
    }

    public void setMai(double mai) {
        this.mai = mai;
    }

    public double getJun() {
        return jun;
    }

    public void setJun(double jun) {
        this.jun = jun;
    }

    public double getJul() {
        return jul;
    }

    public void setJul(double jul) {
        this.jul = jul;
    }

    public double getAgo() {
        return ago;
    }

    public void setAgo(double ago) {
        this.ago = ago;
    }

    public double getSet() {
        return set;
    }

    public void setSet(double set) {
        this.set = set;
    }

    public double getOut() {
        return out;
    }

    public void setOut(double out) {
        this.out = out;
    }

    public double getNov() {
        return nov;
    }

    public void setNov(double nov) {
        this.nov = nov;
    }

    public double getDez() {
        return dez;
    }

    public void setDez(double dez) {
        this.dez = dez;
    }
    
    
    
    
 
    
    
}
