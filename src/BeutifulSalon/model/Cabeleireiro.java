/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

import BeutifulSalon.dao.CabeleireiroDAO;
import BeutifulSalon.dao.ExceptionDAO;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author mateus
 */
public class Cabeleireiro {

    private static String IV = "AAAAAAAAAAAAAAAA";
    private static String chaveencriptacao = "0123456789abcdef";
    private long id;
    private String cpf;
    private String nome;
    private String email;
    private byte[] senha;
    private String postit;
    private Email emailAniversario;
    private Email emailUltimaVisita;
    private long metaDeLucro;
    private LocalTime segundaE;
    private LocalTime tercaE;
    private LocalTime quartaE;
    private LocalTime quintaE;
    private LocalTime sextaE;
    private LocalTime sabadoE;
    private LocalTime domingoE;
    private LocalTime segundaS;
    private LocalTime tercaS;
    private LocalTime quartaS;
    private LocalTime quintaS;
    private LocalTime sextaS;
    private LocalTime sabadoS;
    private LocalTime domingoS;
    private boolean verificarHorariosDisponiveis;
    private int tempoEntreHorariosLivres;

    public Cabeleireiro() {

    }

    ;



    public Cabeleireiro(String cpf, String nome, String email, ArrayList<LocalTime> expediente) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.segundaE = expediente.get(0);
        this.segundaS = expediente.get(1);

        this.tercaE = expediente.get(2);
        this.tercaS = expediente.get(3);

        this.quartaE = expediente.get(4);
        this.quartaS = expediente.get(5);

        this.quintaE = expediente.get(6);
        this.quintaS = expediente.get(7);

        this.sextaE = expediente.get(8);
        this.sextaS = expediente.get(9);

        this.sabadoE = expediente.get(10);
        this.sabadoS = expediente.get(11);

        this.domingoE = expediente.get(12);
        this.domingoS = expediente.get(13);
    }

    public Email getEmailAniversario() {
        return emailAniversario;
    }

    public byte[] criptografaSenha(String senha) {

        byte[] textoencriptado = null;
        try {
            textoencriptado = encrypt(senha, chaveencriptacao);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return textoencriptado;
    }

    private static byte[] encrypt(String textopuro, String chaveencriptacao) {
        try {
            Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
            SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
            encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
            return encripta.doFinal(textopuro.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
        }
        return null;
    }

    public static String decrypt(byte[] textoencriptado, String chaveencriptacao) {

        try {
            Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
            SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
            decripta.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
            return new String(decripta.doFinal(textoencriptado), "UTF-8");
        } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
        }
        return null;
    }

    public void setEmailAniversario(Email emailAniversario) {
        this.emailAniversario = emailAniversario;
    }

    public long getMetaDeLucro() {
        return metaDeLucro;
    }

    public void setMetaDeLucro(long metaDeLucro) {
        this.metaDeLucro = metaDeLucro;
    }

    public int getTempoEntreHorariosLivres() {
        return tempoEntreHorariosLivres;
    }

    public void setTempoEntreHorariosLivres(int tempoEntreHorariosLivres) {
        this.tempoEntreHorariosLivres = tempoEntreHorariosLivres;
    }

    public boolean isVerificarHorariosDisponiveis() {
        return verificarHorariosDisponiveis;
    }

    public void setVerificarHorariosDisponiveis(boolean verificarHorariosDisponiveis) {
        this.verificarHorariosDisponiveis = verificarHorariosDisponiveis;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPostit() {
        return postit;
    }

    public void setPostit(String postit) {
        this.postit = postit;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {

        return decrypt(senha, chaveencriptacao);

    }

    public Email getEmailUltimaVisita() {
        return emailUltimaVisita;
    }

    public void setEmailUltimaVisita(Email emailUltimaVisita) {
        this.emailUltimaVisita = emailUltimaVisita;
    }

    public void setSenha(String senha) {

        this.senha = encrypt(senha, chaveencriptacao);

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalTime getSegundaE() {
        return segundaE;
    }

    public void setSegundaE(LocalTime segundaE) {
        this.segundaE = segundaE;
    }

    public LocalTime getTercaE() {
        return tercaE;
    }

    public void setTercaE(LocalTime tercaE) {
        this.tercaE = tercaE;
    }

    public LocalTime getQuartaE() {
        return quartaE;
    }

    public void setQuartaE(LocalTime quartaE) {
        this.quartaE = quartaE;
    }

    public LocalTime getQuintaE() {
        return quintaE;
    }

    public void setQuintaE(LocalTime quintaE) {
        this.quintaE = quintaE;
    }

    public LocalTime getSextaE() {
        return sextaE;
    }

    public void setSextaE(LocalTime sextaE) {
        this.sextaE = sextaE;
    }

    public LocalTime getSabadoE() {
        return sabadoE;
    }

    public void setSabadoE(LocalTime sabadoE) {
        this.sabadoE = sabadoE;
    }

    public LocalTime getDomingoE() {
        return domingoE;
    }

    public void setDomingoE(LocalTime domingoE) {
        this.domingoE = domingoE;
    }

    public LocalTime getSegundaS() {
        return segundaS;
    }

    public void setSegundaS(LocalTime segundaS) {
        this.segundaS = segundaS;
    }

    public LocalTime getTercaS() {
        return tercaS;
    }

    public void setTercaS(LocalTime tercaS) {
        this.tercaS = tercaS;
    }

    public LocalTime getQuartaS() {
        return quartaS;
    }

    public void setQuartaS(LocalTime quartaS) {
        this.quartaS = quartaS;
    }

    public LocalTime getQuintaS() {
        return quintaS;
    }

    public void setQuintaS(LocalTime quintaS) {
        this.quintaS = quintaS;
    }

    public LocalTime getSextaS() {
        return sextaS;
    }

    public void setSextaS(LocalTime sextaS) {
        this.sextaS = sextaS;
    }

    public LocalTime getSabadoS() {
        return sabadoS;
    }

    public void setSabadoS(LocalTime sabadoS) {
        this.sabadoS = sabadoS;
    }

    public LocalTime getDomingoS() {
        return domingoS;
    }

    public void setDomingoS(LocalTime domingoS) {
        this.domingoS = domingoS;
    }

    public void cadastrarCabeleireiro(Cabeleireiro cabeleireiro) throws ExceptionDAO {
        new CabeleireiroDAO().cadastrarCabeleireiro(cabeleireiro);
    }

    public void atualizarCabeleireiro(Cabeleireiro cabeleireiro) throws ExceptionDAO {
        new CabeleireiroDAO().atualizarCabeleireiro(cabeleireiro);
    }

    public Cabeleireiro selecionaCabeleireiro() throws ExceptionDAO {
        return new CabeleireiroDAO().selecionaCabeleireiro();
    }

    public ArrayList<LocalTime> selecionaExpediente(int diaDaSemana) {
        return new CabeleireiroDAO().selecionaExpediente(diaDaSemana);
    }

    public int verificaRegistro() {
        return new CabeleireiroDAO().verificaRegistro();
    }

    public void cadastrarEmailPadraoAniversario(Email email, String cpf) throws ExceptionDAO {
        new CabeleireiroDAO().cadastrarEmailPadraoAniversario(email, cpf);
    }

    public void cadastrarEmailUltimaVisita(Email mail, String cpf, int periodo) throws ExceptionDAO {
        new CabeleireiroDAO().cadastrarEmailUltimaVisita(mail, cpf, periodo);
    }
}
