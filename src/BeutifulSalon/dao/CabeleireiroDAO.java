/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import BeutifulSalon.model.Cabeleireiro;
import BeutifulSalon.model.Email;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author mateus
 */
public class CabeleireiroDAO {

    public void cadastrarCabeleireiro(Cabeleireiro cabeleireiro) {
        String sqlScript = "INSERT INTO CABELEIREIRO (CPF ,EMAIL, NOME"
                + ", SEGUNDAE, TERCAE, QUARTAE, QUINTAE, SEXTAE, SABADOE, DOMINGOE,"
                + "SEGUNDAS, TERCAS, QUARTAS, QUINTAS, SEXTAS,SABADOS,DOMINGOS, SENHA, METADELUCRO) "
                + "VALUES( ?, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ? , ? ,? ,? ,? ,?, ?, ?)";

        PreparedStatement pStatement = null;
        Connection connection = null;

        try {

            connection = new ConnectionMVC().getConnection();

            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setString(1, cabeleireiro.getCpf());
            pStatement.setString(2, cabeleireiro.getEmail());
            pStatement.setString(3, cabeleireiro.getNome());
            pStatement.setTime(4, java.sql.Time.valueOf(cabeleireiro.getSegundaE()));
            pStatement.setTime(5, java.sql.Time.valueOf(cabeleireiro.getTercaE()));
            pStatement.setTime(6, java.sql.Time.valueOf(cabeleireiro.getQuartaE()));
            pStatement.setTime(7, java.sql.Time.valueOf(cabeleireiro.getQuintaE()));
            pStatement.setTime(8, java.sql.Time.valueOf(cabeleireiro.getSextaE()));
            pStatement.setTime(9, java.sql.Time.valueOf(cabeleireiro.getSabadoE()));
            pStatement.setTime(10, java.sql.Time.valueOf(cabeleireiro.getDomingoE()));
            
            pStatement.setTime(11, java.sql.Time.valueOf(cabeleireiro.getSegundaS()));
            pStatement.setTime(12, java.sql.Time.valueOf(cabeleireiro.getTercaS()));
            pStatement.setTime(13, java.sql.Time.valueOf(cabeleireiro.getQuartaS()));
            pStatement.setTime(14, java.sql.Time.valueOf(cabeleireiro.getQuintaS()));
            pStatement.setTime(15, java.sql.Time.valueOf(cabeleireiro.getSextaS()));
            pStatement.setTime(16, java.sql.Time.valueOf(cabeleireiro.getSabadoS()));
            pStatement.setTime(17, java.sql.Time.valueOf(cabeleireiro.getDomingoS()));
            pStatement.setString(18, cabeleireiro.getSenha());
            pStatement.setLong(19, cabeleireiro.getMetaDeLucro());

            pStatement.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar cabeleireiro." + e);
        } finally {

            try {
                if (pStatement != null) {
                    pStatement.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar statement" + e);
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão" + e);
            }

        }

    }
    
    public int verificaRegistro(){
        
        
        String sql = "SELECT COUNT(1) AS QTD FROM CABELEIREIRO";
        
        Connection connection = null;
        PreparedStatement ps = null;
        int nRegistro = 0;
        
        try {
            connection = new ConnectionMVC().getConnection();
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    nRegistro = rs.getInt("QTD");
                }
            }
            
            return nRegistro;
            
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro CabeleireiroDAO teste1 " + e);
        } finally {

            try {
                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar statement" + e);
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão" + e);
            }

        }
        
        return nRegistro;
    }

    public Cabeleireiro selecionaCabeleireiro() {

        String sqlScript = "SELECT * FROM CABELEIREIRO";

        PreparedStatement pStatement = null;
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sqlScript);

            rs = pStatement.executeQuery();
            Cabeleireiro cabeleireiro = new Cabeleireiro();

            if (rs != null) {
                while (rs.next()) {
                    cabeleireiro.setNome(rs.getString("NOME"));
                    cabeleireiro.setEmail(rs.getString("EMAIL"));
                    cabeleireiro.setCpf(rs.getString("CPF"));
                    cabeleireiro.setSegundaE(rs.getTime("SEGUNDAE").toLocalTime());
                    cabeleireiro.setTercaE(rs.getTime("TERCAE").toLocalTime());
                    cabeleireiro.setQuartaE(rs.getTime("QUARTAE").toLocalTime());
                    cabeleireiro.setQuintaE(rs.getTime("QUINTAE").toLocalTime());
                    cabeleireiro.setSextaE(rs.getTime("SEXTAE").toLocalTime());
                    cabeleireiro.setSabadoE(rs.getTime("SABADOE").toLocalTime());
                    cabeleireiro.setDomingoE(rs.getTime("DOMINGOE").toLocalTime());
                    
                    cabeleireiro.setSegundaS(rs.getTime("SEGUNDAS").toLocalTime());
                    cabeleireiro.setTercaS(rs.getTime("TERCAS").toLocalTime());
                    cabeleireiro.setQuartaS(rs.getTime("QUARTAS").toLocalTime());
                    cabeleireiro.setQuintaS(rs.getTime("QUINTAS").toLocalTime());
                    cabeleireiro.setSextaS(rs.getTime("SEXTAS").toLocalTime());
                    cabeleireiro.setSabadoS(rs.getTime("SABADOS").toLocalTime());
                    cabeleireiro.setDomingoS(rs.getTime("DOMINGOS").toLocalTime());
                    
                    Email emailAniversario = new Email();
                    emailAniversario.setRementente(cabeleireiro.getEmail());
                    emailAniversario.setTitulo(rs.getString("TITULOANIVERSARIO"));
                    emailAniversario.setTexto(rs.getString("TEXTOANIVERSARIO"));
                    emailAniversario.setEnviar(rs.getBoolean("ENVIARANIVERSARIO"));
                    emailAniversario.setDiretorioArquivo(rs.getString("NOMEANEXOANIVERSARIO"));
                    byte[] anexoAniversario = rs.getBytes("ANEXOANIVERSARIO");
                    if(anexoAniversario != null){
                        emailAniversario.setAnexo(anexoAniversario);
                    }
                    
                    Email emailUltimaVisita = new Email();
                    emailUltimaVisita.setEnviar(rs.getBoolean("ENVIARULTIMAVISITA"));
                    emailUltimaVisita.setRementente(cabeleireiro.getEmail());
                    emailUltimaVisita.setTitulo(rs.getString("TITULOULTIMAVISITA"));
                    emailUltimaVisita.setTexto(rs.getString("TEXTOULTIMAVISITA"));             
                    emailUltimaVisita.setDiretorioArquivo(rs.getString("NOMEANEXOULTIMAVISITA"));
                    emailUltimaVisita.setPeriodoReenvio(rs.getInt("PERIODOULTIMAVISITA"));
                    byte[] anexoUltimaVisita = rs.getBytes("ANEXOULTIMAVISITA");
                    if(anexoAniversario != null){
                        emailUltimaVisita.setAnexo(anexoUltimaVisita);
                    }
                    
                    cabeleireiro.setEmailUltimaVisita(emailUltimaVisita);
                    cabeleireiro.setEmailAniversario(emailAniversario);
                    cabeleireiro.setSenha(rs.getString("SENHA"));
                    cabeleireiro.setMetaDeLucro(rs.getLong("METADELUCRO"));

                }
            }

            return cabeleireiro;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro CabeleireiroDAO teste " + e);
        } finally {

            try {
                if (pStatement != null) {
                    pStatement.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar statement" + e);
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão" + e);
            }

        }

        return null;
    }

    public ArrayList<LocalTime> selecionaExpediente(int diaDaSemana) {
        
        String sqlScript = "";
        String entrada = "";
        String saida = "";
        ArrayList<LocalTime> horario = new ArrayList<>();

        switch(diaDaSemana){
            case 1:
                sqlScript = "SELECT SEGUNDAE,SEGUNDAS FROM CABELEIREIRO";
                entrada = "SEGUNDAE";
                saida = "SEGUNDAS";
                break;
            case 2:
                sqlScript = "SELECT TERCAE,TERCAS FROM CABELEIREIRO";
                entrada = "TERCAE";
                saida = "TERCAS";
                break;
            case 3:
                sqlScript = "SELECT QUARTAE,QUARTAS FROM CABELEIREIRO";
                entrada = "QUARTAE";
                saida = "QUARTAS";
                break;
            case 4:
                sqlScript = "SELECT QUINTAE,QUINTAS FROM CABELEIREIRO";
                entrada = "QUINTAE";
                saida = "QUINTAS";
                break;
            case 5:
                sqlScript = "SELECT SEXTAE,SEXTAS FROM CABELEIREIRO";
                entrada = "SEXTAE";
                saida = "SEXTAS";
                break;
            case 6:
                sqlScript = "SELECT SABADOE,SABADOS FROM CABELEIREIRO";
                entrada = "SABADOE";
                saida = "SABADOS";
                break;
            case 7:
                sqlScript = "SELECT DOMINGOE, DOMINGOS FROM CABELEIREIRO";
                entrada = "DOMINGOE";
                saida = "DOMINGOS";
                break;
                
        }
        
        PreparedStatement pStatement = null;
        Connection connection = null;
        ResultSet rs = null;

        try {
            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sqlScript);
            rs = pStatement.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    horario.add(rs.getTime(entrada).toLocalTime());
                    horario.add(rs.getTime(saida).toLocalTime());
                }
            }
           
            return horario;
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro CabeleireiroDAO teste " + e);
        } finally {

            try {
                if (pStatement != null) {
                    pStatement.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar statement" + e);
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão" + e);
            }

        }
                    
        return null;
    }

    public void atualizarCabeleireiro(Cabeleireiro cabeleireiro) {
        
        String sqlScript = "UPDATE CABELEIREIRO SET CPF = ? ,EMAIL = ? , NOME = ? ,"
                + " SEGUNDAE = ?, TERCAE = ?, QUARTAE = ?, QUINTAE = ?, SEXTAE = ?, SABADOE = ?, DOMINGOE = ?,"
                + " SEGUNDAS = ?, TERCAS = ?, QUARTAS =?, QUINTAS = ?, SEXTAS = ? , SABADOS = ?, DOMINGOS = ?, SENHA = ?, METADELUCRO = ? "
                + "WHERE ID = (SELECT MAX(ID) FROM CABELEIREIRO)";
        
  
  
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {

            connection = new ConnectionMVC().getConnection();

            pStatement = connection.prepareStatement(sqlScript);
            
           
            pStatement.setString(1, cabeleireiro.getCpf());
            pStatement.setString(2, cabeleireiro.getEmail());
            pStatement.setString(3, cabeleireiro.getNome());
            pStatement.setTime(4, java.sql.Time.valueOf(cabeleireiro.getSegundaE()));
            pStatement.setTime(5, java.sql.Time.valueOf(cabeleireiro.getTercaE()));
            pStatement.setTime(6, java.sql.Time.valueOf(cabeleireiro.getQuartaE()));
            pStatement.setTime(7, java.sql.Time.valueOf(cabeleireiro.getQuintaE()));
            pStatement.setTime(8, java.sql.Time.valueOf(cabeleireiro.getSextaE()));
            pStatement.setTime(9, java.sql.Time.valueOf(cabeleireiro.getSabadoE()));
            pStatement.setTime(10, java.sql.Time.valueOf(cabeleireiro.getDomingoE()));

            pStatement.setTime(11, java.sql.Time.valueOf(cabeleireiro.getSegundaS()));
            pStatement.setTime(12, java.sql.Time.valueOf(cabeleireiro.getTercaS()));
            pStatement.setTime(13, java.sql.Time.valueOf(cabeleireiro.getQuartaS()));
            pStatement.setTime(14, java.sql.Time.valueOf(cabeleireiro.getQuintaS()));
            pStatement.setTime(15, java.sql.Time.valueOf(cabeleireiro.getSextaS()));
            pStatement.setTime(16, java.sql.Time.valueOf(cabeleireiro.getSabadoS()));
            pStatement.setTime(17, java.sql.Time.valueOf(cabeleireiro.getDomingoS()));
            pStatement.setString(18, cabeleireiro.getSenha());
            pStatement.setLong(19, cabeleireiro.getMetaDeLucro());

            
            pStatement.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro atualizar dados do cabeleireiro" + e);
        } finally {

            try {
                if (pStatement != null) {
                    pStatement.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar statement" + e);
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão" + e);
            }

        }
   
    }
    
    public void cadastrarEmailPadraoAniversario(Email email, String cpf){
         String sqlScript = "UPDATE CABELEIREIRO SET ENVIARANIVERSARIO = ?, TITULOANIVERSARIO = ? ,TEXTOANIVERSARIO = ?,"
                 + "ANEXOANIVERSARIO = ?, NOMEANEXOANIVERSARIO = ? WHERE CPF = ?";
             
  
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {

            connection = new ConnectionMVC().getConnection();

            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setString(6, cpf);
            pStatement.setBoolean(1, email.isEnviar());
            pStatement.setString(2, email.getTitulo());
            pStatement.setString(3, email.getTexto());
            pStatement.setBytes(4, email.getAnexo());
            pStatement.setString(5, email.getDiretorioArquivo() );
            
            pStatement.execute();
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro atualizar dados do cabeleireiro" + e);
        } finally {

            try {
                if (pStatement != null) {
                    pStatement.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar statement" + e);
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão" + e);
            }

        }
  
    }
    
    
    public Email getEmailPadraoAniversario(){
        String sqlScript = "SELECT ENVIARANIVERSARIO,TITULOANIVERSARIO,TEXTOANIVERSARIO,ANEXOANIVERSARIO FROM CABELEIREIRO";
  
        PreparedStatement pStatement = null;
        Connection connection = null;
        Email email = null;
        try {

            connection = new ConnectionMVC().getConnection();

            pStatement = connection.prepareStatement(sqlScript);
            
            ResultSet rs = pStatement.executeQuery();
            
            if(rs != null){
                email = new Email();
                
                email.setEnviar(rs.getBoolean("ENVIARANIVERSARIO"));
                email.setTitulo(rs.getString("TITULOANIVERSARIO"));
                email.setTexto(rs.getString("TEXTOANIVERSARIO"));
                email.setAnexo(rs.getBytes("ANEXOANIVERSARIO"));
            }
            
            return email;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro atualizar dados do cabeleireiro" + e);
        } finally {

            try {
                if (pStatement != null) {
                    pStatement.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar statement" + e);
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão" + e);
            }

        }
        return email;
    }

    public void cadastrarEmailUltimaVisita(Email email, String cpf, int periodo) {
        
        String sqlScript = "UPDATE CABELEIREIRO SET ENVIARULTIMAVISITA = ?, TITULOULTIMAVISITA = ? ,TEXTOULTIMAVISITA = ?,"
                 + "ANEXOULTIMAVISITA = ?, NOMEANEXOULTIMAVISITA = ?, PERIODOULTIMAVISITA = ? WHERE CPF = ?";
             
        PreparedStatement pStatement = null;
        Connection connection = null;
        try {

            connection = new ConnectionMVC().getConnection();

            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setString(7, cpf);
            pStatement.setBoolean(1, email.isEnviar());
            pStatement.setString(2, email.getTitulo());
            pStatement.setString(3, email.getTexto());
            pStatement.setBytes(4, email.getAnexo());
            pStatement.setString(5, email.getDiretorioArquivo() );
            pStatement.setInt(6, periodo);
            
            pStatement.execute();
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro atualizar dados do cabeleireiro" + e);
        } finally {

            try {
                if (pStatement != null) {
                    pStatement.close();
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar statement" + e);
            }

            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão" + e);
            }

        }
    }

}
