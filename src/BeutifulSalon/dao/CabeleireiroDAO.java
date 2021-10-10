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

    private static String chaveencriptacao = "0123456789abcdef";

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
            pStatement.setBytes(18, cabeleireiro.criptografaSenha(cabeleireiro.getSenha()));
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

    public void cadastrarPostIt(String texto) throws ExceptionDAO {

        String sql = "UPDATE CABELEIREIRO SET POSTIT = ?";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = new ConnectionMVC().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, texto);

            ps.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro CabeleireiroDAO " + e);
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

    }

    public int verificaRegistro() {

        String sql = "SELECT COUNT(1) AS QTD FROM CABELEIREIRO";

        Connection connection = null;
        PreparedStatement ps = null;
        int nRegistro = 0;

        try {
            connection = new ConnectionMVC().getConnection();
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    nRegistro = rs.getInt("QTD");
                }
            }

            return nRegistro;

        } catch (SQLException e) {
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
                    if (anexoAniversario != null) {
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
                    if (anexoAniversario != null) {
                        emailUltimaVisita.setAnexo(anexoUltimaVisita);
                    }

                    cabeleireiro.setEmailUltimaVisita(emailUltimaVisita);
                    cabeleireiro.setEmailAniversario(emailAniversario);
                    cabeleireiro.setSenha(Cabeleireiro.decrypt(rs.getBytes("SENHA"), chaveencriptacao));
                    cabeleireiro.setMetaDeLucro(rs.getLong("METADELUCRO"));
                    cabeleireiro.setPostit(rs.getString("POSTIT"));
                    cabeleireiro.setVerificarHorariosDisponiveis(rs.getBoolean("VERIFICACAOHORARIOS"));
                    cabeleireiro.setTempoEntreHorariosLivres(rs.getInt("TEMPOENTREHORARIOS"));

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

        switch (diaDaSemana) {
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
            pStatement.setBytes(18, cabeleireiro.criptografaSenha(cabeleireiro.getSenha()));
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

    public void cadastrarEmailPadraoAniversario(Email email, String cpf) {
        String sqlScript = "UPDATE CABELEIREIRO SET ENVIARANIVERSARIO = ?, TITULOANIVERSARIO = ? ,TEXTOANIVERSARIO = ?,"
                + "ANEXOANIVERSARIO = ?, NOMEANEXOANIVERSARIO = ?";

        PreparedStatement pStatement = null;
        Connection connection = null;
        try {

            connection = new ConnectionMVC().getConnection();

            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setBoolean(1, email.isEnviar());
            pStatement.setString(2, email.getTitulo());
            pStatement.setString(3, email.getTexto());
            pStatement.setBytes(4, email.getAnexo());
            pStatement.setString(5, email.getDiretorioArquivo());

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

    public Email getEmailPadraoAniversario() {
        String sqlScript = "SELECT ENVIARANIVERSARIO,TITULOANIVERSARIO,TEXTOANIVERSARIO,ANEXOANIVERSARIO FROM CABELEIREIRO";

        PreparedStatement pStatement = null;
        Connection connection = null;
        Email email = null;
        try {

            connection = new ConnectionMVC().getConnection();

            pStatement = connection.prepareStatement(sqlScript);

            ResultSet rs = pStatement.executeQuery();

            if (rs != null) {
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
                + "ANEXOULTIMAVISITA = ?, NOMEANEXOULTIMAVISITA = ?, PERIODOULTIMAVISITA = ?";

        PreparedStatement pStatement = null;
        Connection connection = null;
        try {

            connection = new ConnectionMVC().getConnection();

            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setBoolean(1, email.isEnviar());
            pStatement.setString(2, email.getTitulo());
            pStatement.setString(3, email.getTexto());
            pStatement.setBytes(4, email.getAnexo());
            pStatement.setString(5, email.getDiretorioArquivo());
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

    public void atualizarPreferencias(boolean verificar, int tempoEntreAgendamentos) {

        String sqlScript = "UPDATE CABELEIREIRO SET VERIFICACAOHORARIOS = ?,TEMPOENTREHORARIOS = ?  ";

        PreparedStatement pStatement = null;
        Connection connection = null;
        try {

            connection = new ConnectionMVC().getConnection();

            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setBoolean(1, verificar);
            pStatement.setInt(2, tempoEntreAgendamentos);

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

    public void recriarBanco() {

        String tabelaCliente = "CREATE TABLE IF NOT EXISTS CLIENTE ("
                + " NOME          VARCHAR (30) NOT NULL,"
                + " SOBRENOME     VARCHAR (40) NOT NULL,"
                + " EMAIL         VARCHAR (60),"
                + " DATANASC      CHAR (10),"
                + " CEP           CHAR (9),"
                + " BAIRRO        VARCHAR (50),"
                + " RUA           VARCHAR (60),"
                + " NUMERO        VARCHAR (6),"
                + " CIDADE        CHAR (4),"
                + " TELEFONE      VARCHAR (15),"
                + " CELULAR       VARCHAR (15),"
                + " DATAREG       DATE,"
                + " TIPODECABELO  INT          DEFAULT (12),"
                + " TAMANHOCABELO INT          DEFAULT (4),"
                + " CORCABELO     VARCHAR (16) DEFAULT [Não Informado],"
                + " CONHECEU      INT          DEFAULT (5),"
                + " FACEBOOK      VARCHAR (35) DEFAULT [Não Informado],"
                + " INSTAGRAM     VARCHAR (35) DEFAULT [Não Informado],"
                + " OBSERVACOES   TEXT         DEFAULT [Não Informado.],"
                + " FOTOPERFIL    BLOB,"
                + " ID            INTEGER      PRIMARY KEY AUTOINCREMENT,"
                + "EXCLUIDO      BOOLEAN      DEFAULT (false) "
                + "); ";

        String triggerCliente1 = "CREATE TRIGGER IF NOT EXISTS registraEmailUltimaVisita"
                + "    AFTER INSERT"
                + "      ON CLIENTE"
                + " BEGIN"
                + "    INSERT INTO EMAILULTIMAVISITA ("
                + "                                      ID_CLIENTE,"
                + "                                      ULTIMOENVIO"
                + "                                  )"
                + "                                  VALUES ("
                + "                                      ("
                + "                                          SELECT ID"
                + "                                            FROM CLIENTE"
                + "                                           ORDER BY ID DESC"
                + "                                           LIMIT 1"
                + "                                      ),"
                + "                                      NULL"
                + "                                  );"
                + "END; ";
        String triggerCliente2 = "CREATE TRIGGER IF NOT EXISTS registraEmailAniversario"
                + "         AFTER INSERT"
                + "            ON CLIENTE"
                + " BEGIN"
                + "    INSERT INTO EMAILANIVERSARIO ("
                + "                                     ID_CLIENTE,"
                + "                                     ULTIMOENVIO"
                + "                                 )"
                + "                                 VALUES ("
                + "                                     ("
                + "                                         SELECT ID"
                + "                                           FROM CLIENTE"
                + "                                          ORDER BY ID DESC"
                + "                                          LIMIT 1"
                + "                                     ),"
                + "                                     NULL"
                + "                                 );"
                + "END;";

        String tabelaEmailAniversario = "CREATE TABLE IF NOT EXISTS EMAILANIVERSARIO ("
                + "    ID_CLIENTE  INTEGER NOT NULL"
                + "                        REFERENCES CLIENTE (ID),"
                + "    ULTIMOENVIO DATE,"
                + "    FOREIGN KEY ("
                + "        ID_CLIENTE"
                + "    )"
                + "    REFERENCES CLIENTE"
                + ");";
        String tabelaEmailUltimaVisita = "CREATE TABLE IF NOT EXISTS EMAILULTIMAVISITA ("
                + "  ID_CLIENTE  INTEGER NOT NULL"
                + "                       REFERENCES CLIENTE (ID),"
                + "  ULTIMOENVIO DATE,"
                + "  FOREIGN KEY ("
                + "      ID_CLIENTE"
                + "  )"
                + "  REFERENCES CLIENTE"
                + ");";

        String tabelaCompra = "CREATE TABLE IF NOT EXISTS COMPRA ("
                + "   ID_COMPRA     INTEGER  PRIMARY KEY AUTOINCREMENT,"
                + "  DATA          DATE,"
                + " VALORTOTAL    INTEGER,"
                + " VALORDESCONTO INTENGER"
                + ");";
        String tabelItemCompra = "CREATE TABLE IF NOT EXISTS ITEM_COMPRA ("
                + "   ID_ITEMCOMPRA INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "  PRECOUNITARIO INTEGER,"
                + " QUANTIDADE    INT,"
                + " PRECOTOTAL    INTEGER,"
                + " ID_PRODUTO    INTEGER,"
                + " ID_COMPRA     INTEGER,"
                + " FOREIGN KEY ("
                + "     ID_PRODUTO"
                + ")"
                + " REFERENCES PRODUTO (IDPRODUTO),"
                + " FOREIGN KEY ("
                + "     ID_COMPRA"
                + " )"
                + " REFERENCES COMPRA (ID_COMPRA) "
                + ");";

        String tabelaVenda = "CREATE TABLE IF NOT EXISTS VENDA ("
                + "   ID_VENDA      INTEGER   PRIMARY KEY AUTOINCREMENT,"
                + "  DATA          DATE,"
                + " VALORTOTAL    INTEGER,"
                + "VALORDESCONTO INTENGER,"
                + "CPF_CLIENTE   CHAR (14),"
                + "ID_CLIENTE    INTEGER   NOT NULL"
                + "                        REFERENCES CLIENTE (ID),"
                + "FOREIGN KEY ("
                + "    CPF_CLIENTE"
                + ")"
                + "REFERENCES CLIENTE"
                + ");";

        String tabelaItemVenda = "CREATE TABLE IF NOT EXISTS ITEM_VENDA ("
                + "    ID_ITEMVENDA  INTEGER    PRIMARY KEY AUTOINCREMENT,"
                + "    PRECOUNITARIO INTEGER,"
                + "    QUANTIDADE    INT,"
                + "    PRECOTOTAL    INTEGER,"
                + "    ID_PRODUTO    INTEGER,"
                + "    ID_VENDA      INTEGER,"
                + "    BOOLEAN       DADO_ATIVO,"
                + "    FOREIGN KEY ("
                + "        ID_PRODUTO"
                + "    )"
                + "    REFERENCES PRODUTO (IDPRODUTO),"
                + "    FOREIGN KEY ("
                + "        ID_VENDA"
                + "    )"
                + "    REFERENCES COMPRA (ID_VENDA) "
                + ");";

        String tabelaProduto = "CREATE TABLE PRODUTO ("
                + "    IDPRODUTO INTEGER      PRIMARY KEY AUTOINCREMENT,"
                + "    NOME      VARCHAR (45) NOT NULL,"
                + "    MARCA     VARCHAR (45) NOT NULL,"
                + "    PRECO     INTEGER      NOT NULL,"
                + "    DATAREG   DATE         NOT NULL,"
                + "   EXCLUIDO  BOOLEAN      DEFAULT (false),"
                + "   CONSTRAINT iPRODUTO UNIQUE ("
                + "      NOME,"
                + "       MARCA"
                + "  )"
                + ");";

        String triggerExclusaoProduto = "CREATE TRIGGER IF NOT EXISTS exclusaoLogicaProduto"
                + "         AFTER UPDATE OF EXCLUIDO"
                + "            ON PRODUTO"
                + " BEGIN"
                + "    DELETE FROM PRODUTO_SERVICO"
                + "         WHERE ID_PRODUTO = old.IDPRODUTO;"
                + "  DELETE FROM ESTOQUE"
                + "       WHERE ESTOQUE.ID_PRODUTO = old.IDPRODUTO;"
                + "END;";

        String tabelaServico = "CREATE TABLE IF NOT EXISTS SERVICO ("
                + "   ID_SERVICO INTEGER      PRIMARY KEY AUTOINCREMENT,"
                + "  NOME       VARCHAR (30) NOT NULL,"
                + " PRECO      INTEGER      NOT NULL,"
                + "TEMPOGASTO DATIME,"
                + "EXCLUIDO   BOOLEAN      DEFAULT (false) "
                + ");";

        String tabelaProdutoServico = "CREATE TABLE IF NOT EXISTS PRODUTO_SERVICO ("
                + "   ID_PRODUTO INT,"
                + "  RENDIMENTO INT,"
                + " ID_SERVICO INT,"
                + "FOREIGN KEY ("
                + "   ID_PRODUTO"
                + ")"
                + "REFERENCES PRODUTO,"
                + "FOREIGN KEY ("
                + "   ID_SERVICO"
                + ")"
                + "REFERENCES SERVICO (ID_SERVICO) "
                + ");";

        String tabelaCabeleireiro = "CREATE TABLE IF NOT EXISTS CABELEIREIRO ("
                + "   CPF                   CHAR (14),"
                + "  EMAIL                 VARCHAR (60)                              NOT NULL,"
                + " NOME                  VARCHAR (45)                              NOT NULL,"
                + "SEGUNDAE              DATETIME,"
                + "TERCAE                DATETIME,"
                + "QUARTAE               DATETIME,"
                + "QUINTAE               DATETIME,"
                + "SEXTAE                DATETIME,"
                + "SABADOE               DATETIME,"
                + "DOMINGOE              DATETIME,"
                + "SEGUNDAS              DATETIME,"
                + "TERCAS                DATETIME,"
                + "QUARTAS               DATETIME,"
                + "QUINTAS               DATETIME,"
                + "SEXTAS                DATETIME,"
                + "SABADOS               DATETIME,"
                + "DOMINGOS              DATETIME,"
                + "ENVIARANIVERSARIO     BOOLEAN                                   DEFAULT 0,"
                + "TITULOANIVERSARIO     VARCHAR (60)                              DEFAULT [Título não informado.],"
                + "TEXTOANIVERSARIO      TEXT                                      DEFAULT '',"
                + "ANEXOANIVERSARIO      [BLOOB NOMEANEXOANIVERSARIO VARCHAR] (60),"
                + "NOMEANEXOANIVERSARIO  STRING (60),"
                + "SENHA                 BLOB (45),"
                + "ENVIARULTIMAVISITA    BOOLEAN                                   DEFAULT (FALSE),"
                + "TITULOULTIMAVISITA    VARCHAR (60)                              DEFAULT [Título não informado],"
                + "TEXTOULTIMAVISITA     TEXT                                      DEFAULT '',"
                + "ANEXOULTIMAVISITA     BLOB,"
                + "NOMEANEXOULTIMAVISITA VARCHAR (60),"
                + "PERIODOULTIMAVISITA   INTEGER                                   DEFAULT (2),"
                + "METADELUCRO           INTEGER                                   DEFAULT (0),"
                + "ID                    INTEGER                                   PRIMARY KEY,"
                + "POSTIT                TEXT,"
                + "TEMPOENTREHORARIOS    INTEGER                                   DEFAULT(6),"
                + "VERIFICACAOHORARIOS   BOOLEAN                                   DEFAULT (FALSE)"
                + ");";

        String tabelaEstoque = "CREATE TABLE IF NOT EXISTS ESTOQUE ("
                + "   ID_ESTOQUE     INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "  ID_PRODUTO     INTEGER NOT NULL,"
                + "  QUANTIDADE     INTEGER NOT NULL"
                + "                         DEFAULT 0,"
                + " VALOR_UNITARIO INTEGER"
                + ");";

        String tabelaAgendamento = "CREATE TABLE IF NOT EXISTS AGENDAMENTO ("
                + "   ID_AGENDAMENTO   INTEGER      PRIMARY KEY AUTOINCREMENT,"
                + "  DATA             DATE         NOT NULL,"
                + " HORARIO          DATETIME     NOT NULL,"
                + "REALIZADO        BOOLEAN,"
                + "TOTAL            INTEGER,"
                + "DESCONTO         INTEGER,"
                + "ID_CLIENTE       INTEGER      NOT NULL"
                + "                              REFERENCES CLIENTE (ID),"
                + "VALORADICIONAL   INTEGER,"
                + "PAGO             BOOLEAN,"
                + "FORMADEPAGAMENTO VARCHAR (15),"
                + "FIMAGENDAMENTO   TIME"
                + ");";

        String tabelaAgendamentoServico = "CREATE TABLE IF NOT EXISTS AGENDAMENTO_SERVICO ("
                + "   ID_AGENDAMENTO INTEGER,"
                + "  ID_SERVICO     INTEGER,"
                + " FOREIGN KEY ("
                + "    ID_AGENDAMENTO"
                + ")"
                + "REFERENCES AGENDAMENTO (ID_AGENDAMENTO),"
                + "FOREIGN KEY ("
                + "    ID_SERVICO"
                + ")"
                + "REFERENCES SERVICO (ID_SERVICO) "
                + ");";

        String tabelaOrcamento = "CREATE TABLE IF NOT EXISTS ORCAMENTO ("
                + "   ID_ORCAMENTO INTEGER      PRIMARY KEY AUTOINCREMENT,"
                + "  NOME         VARCHAR (45) NOT NULL,"
                + " JANEIRO      INTEGER      NOT NULL,"
                + "FEVEREIRO    INTEGER      NOT NULL,"
                + "MARCO        INTEGER      NOT NULL,"
                + "ABRIL        INTEGER      NOT NULL,"
                + "MAIO         INTEGER      NOT NULL,"
                + "JUNHO        INTEGER      NOT NULL,"
                + "JULHO        INTEGER      NOT NULL,"
                + "AGOSTO       INTEGER      NOT NULL,"
                + "SETEMBRO     INTEGER      NOT NULL,"
                + "OUTUBRO      INTEGER      NOT NULL,"
                + "NOVEMBRO     INTEGER      NOT NULL,"
                + "DEZEMBRO     INTEGER      NOT NULL,"
                + "PREVISTO     BOOLEAN,"
                + "ANO          CHAR (5)"
                + "REFERENCES CABELEIREIRO (CPF),"
                + "CONSTRAINT iORCAMENTO UNIQUE ("
                + "    NOME,"
                + "    ANO"
                + ")"
                + ");";
        String tabelaOrcamentoServico = "CREATE TABLE IF NOT EXISTS  ORCAMENTOSERVICO ("
                + "   ID_ORCAMENTO INTEGER      PRIMARY KEY AUTOINCREMENT,"
                + "  NOMESERV     VARCHAR (45) NOT NULL,"
                + " JANEIRO      INTEGER      NOT NULL,"
                + "FEVEREIRO    INTEGER      NOT NULL,"
                + "MARCO        INTEGER      NOT NULL,"
                + "ABRIL        INTEGER      NOT NULL,"
                + "MAIO         INTEGER      NOT NULL,"
                + "JUNHO        INTEGER      NOT NULL,"
                + "JULHO        INTEGER      NOT NULL,"
                + "AGOSTO       INTEGER      NOT NULL,"
                + "SETEMBRO     INTEGER      NOT NULL,"
                + "OUTUBRO      INTEGER      NOT NULL,"
                + "NOVEMBRO     INTEGER      NOT NULL,"
                + "DEZEMBRO     INTEGER      NOT NULL,"
                + "PREVISTO     BOOLEAN,"
                + "ANO          CHAR (5)     NOT NULL,"
                + "ID_SERVICO   INTEGER      NOT NULL,"
                + "CONSTRAINT iORCAMENTOSERVICO UNIQUE ("
                + "    NOMESERV,"
                + "    ANO"
                + " )"
                + "); ";

        String tabelaDespesaMensal = "CREATE TABLE DESPESAMENSAL ("
                + "   ID_DESPESA     INTEGER      PRIMARY KEY AUTOINCREMENT,"
                + "  VALORPAGO      INTEGER,"
                + " FORMAPAGAMENTO VARCHAR (15),"
                + "ANO            CHAR(5)      NOT NULL,"
                + "DATALANCAMENTO DATE         NOT NULL,"
                + "DATAVENCIMENTO DATE         NOT NULL,"
                + "DATAPAGAMENTO  DATE,"
                + "STATUS         BOOLEAN      NOT NULL,"
                + "ANOTACAO       TEXT,"
                + "ID_ORCAMENTO   INTEGER      NOT NULL,"
                + "FOREIGN KEY ("
                + "    ID_ORCAMENTO"
                + ")"
                + "REFERENCES ORCAMENTO (ID_ORCAMENTO) );";

        PreparedStatement pStatement = null;
        Connection connection = null;
        try {

            connection = new ConnectionMVC().getConnection();

            connection.prepareStatement(tabelaCliente).execute();
            connection.prepareStatement(tabelaEmailAniversario).execute();
            connection.prepareStatement(tabelaEmailUltimaVisita).execute();
            connection.prepareStatement(triggerCliente1).execute();
            connection.prepareStatement(triggerCliente2).execute();
            connection.prepareStatement(tabelaCliente).execute();
            connection.prepareStatement(tabelaCompra).execute();
            connection.prepareStatement(tabelItemCompra).execute();
            connection.prepareStatement(tabelaVenda).execute();
            connection.prepareStatement(tabelaItemVenda).execute();
            connection.prepareStatement(tabelaProduto).execute();
            connection.prepareStatement(triggerExclusaoProduto).execute();
            connection.prepareStatement(tabelaServico).execute();
            connection.prepareStatement(tabelaProdutoServico).execute();
            connection.prepareStatement(tabelaCabeleireiro).execute();
            connection.prepareStatement(tabelaEstoque).execute();
            connection.prepareStatement(tabelaAgendamento).execute();
            connection.prepareStatement(tabelaAgendamentoServico).execute();
            connection.prepareStatement(tabelaOrcamento).execute();
            connection.prepareStatement(tabelaOrcamentoServico).execute();
            connection.prepareStatement(tabelaDespesaMensal).execute();

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
