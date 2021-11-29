/*
 * The MIT License
 *
 * Copyright 2021 Mateus.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package BeutifulSalon.dao;

import BeutifulSalon.Ferramentas.ManipulaData;
import BeutifulSalon.model.Colaborador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class ColaboradorDAO {

    public boolean cadastrarColaborador(Colaborador c) {

        String sqlScript = "INSERT INTO COLABORADOR (NOME, ISCOMISSIONADO, PORCENTAGEMCOMISSAO, COMISSAOPORLUCRO, COMISSAOPORQTD, EXCLUIDO)"
                + " VALUES (?,?,?,?,?,?)";

        PreparedStatement pStatement = null;
        Connection connection = null;

        try {

            connection = new ConnectionMVC().getConnection();

            pStatement = connection.prepareStatement(sqlScript);

            pStatement.setString(1, c.getNome());
            pStatement.setBoolean(2, c.isComissionado());
            pStatement.setLong(3, c.getPorcentagemComisao());
            pStatement.setBoolean(4, c.isComissaoPorLucro());
            pStatement.setBoolean(5, c.isComissaoPorQtd());
            pStatement.setBoolean(6, false);
            return pStatement.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar colaborador" + e);
            return false;
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

    public Colaborador buscarColaborador(long idColaborador) {
        String sqlScript = "SELECT * FROM COLABORADOR WHERE ID_COLABORADOR = ?";

        PreparedStatement pStatement = null;
        Connection connection = null;
        Colaborador c = new Colaborador();

        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setLong(1, idColaborador);
            ResultSet rs = pStatement.executeQuery();

            if (rs != null) {

                c.setIdColaborador(idColaborador);
                c.setNome(rs.getString("NOME"));
                c.setComissionado(rs.getBoolean("ISCOMISSIONADO"));
                c.setPorcentagemComisao(rs.getLong("PORCENTAGEMCOMISSAO"));
                c.setComissaoPorLucro(rs.getBoolean("COMISSAOPORLUCRO"));
                c.setComissaoPorQtd(rs.getBoolean("COMISSAOPORQTD"));
            }

            return c;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro buscar colaborador" + e);

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
    
    public int getQuantidadeRealizada(Colaborador c, Month mes) throws ExceptionDAO{
        
        String sqlScript = "SELECT COUNT(ID_AGENDAMENTO) AS QTD FROM AGENDAMENTO WHERE "
                + "ID_COLABORADOR = ? AND DATA BETWEEN ? AND ?";

        PreparedStatement pStatement = null;
        Connection connection = null;
         ManipulaData md = new ManipulaData();
        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sqlScript);

            pStatement.setLong(1, c.getIdColaborador());
            long inicio = md.inicioDoMes(LocalDate.now(), mes);
            long fim = md.fimDoMes(LocalDate.now(), mes);
            pStatement.setLong(2, inicio);
            pStatement.setLong(3, fim);
            
            ResultSet rs = pStatement.executeQuery();
            
            if(rs !=null){
                return rs.getInt("QTD");
            }
           

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar colaborador" + e);
           
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
        return 0;
    }

    public boolean excluirColaborador(Colaborador c) {

        String sqlScript = "UPDATE COLABORADOR SET EXCLUIDO = TRUE WHERE ID_COLABORADOR = ?";

        PreparedStatement pStatement = null;
        Connection connection = null;

        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sqlScript);

            pStatement.setLong(1, c.getIdColaborador());

            return pStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar colaborador" + e);
            return false;
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

    public List<Colaborador> listarColaboradores() throws ExceptionDAO {

        String sqlScript = "SELECT * FROM COLABORADOR WHERE EXCLUIDO = FALSE";

        PreparedStatement pStatement = null;
        Connection connection = null;
        List<Colaborador> colaboradores = new ArrayList<>();

        try {

            connection = new ConnectionMVC().getConnection();
            pStatement = connection.prepareStatement(sqlScript);

            ResultSet rs = pStatement.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Colaborador c = new Colaborador();
                    c.setIdColaborador(rs.getLong("ID_COLABORADOR"));
                    c.setNome(rs.getString("NOME"));
                    c.setComissionado(rs.getBoolean("ISCOMISSIONADO"));
                    c.setPorcentagemComisao(rs.getLong("PORCENTAGEMCOMISSAO"));
                    c.setComissaoPorLucro(rs.getBoolean("COMISSAOPORLUCRO"));
                    c.setComissaoPorQtd(rs.getBoolean("COMISSAOPORQTD"));
                    colaboradores.add(c);
                }
            }

            return colaboradores;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar colaborador" + e);

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

    public int verificaRegistroColaboradorCabeleireiro(long idCabeleireiro) {

        String sqlScript = "SELECT COUNT(1) AS QTD FROM COLABORADOR WHERE ID_COLABORADOR = ?";

        PreparedStatement pStatement = null;
        Connection connection = null;

        try {

            connection = new ConnectionMVC().getConnection();

            pStatement = connection.prepareStatement(sqlScript);

            pStatement.setLong(1, idCabeleireiro);

            ResultSet rs = pStatement.executeQuery();

            if (rs != null) {

                return rs.getInt("QTD");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar colaborador" + e);

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
        return -1;
    }

    public boolean cadastrarColaboradorCabeleireiro(Colaborador c) {

        String sqlScript = "INSERT INTO COLABORADOR (NOME, ISCOMISSIONADO, PORCENTAGEMCOMISSAO, COMISSAOPORLUCRO, "
                + "COMISSAOPORQTD, ID_COLABORADOR, EXCLUIDO)"
                + " VALUES (?,?,?,?,?,?,?)";

        PreparedStatement pStatement = null;
        Connection connection = null;

        try {

            connection = new ConnectionMVC().getConnection();

            pStatement = connection.prepareStatement(sqlScript);

            pStatement.setString(1, c.getNome());
            pStatement.setBoolean(2, c.isComissionado());
            pStatement.setLong(3, c.getPorcentagemComisao());
            pStatement.setBoolean(4, c.isComissaoPorLucro());
            pStatement.setBoolean(5, c.isComissaoPorQtd());
            pStatement.setLong(6, c.getIdColaborador());
            pStatement.setBoolean(7, false);

            return pStatement.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar colaborador" + e);
            return false;
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

    public void atualizarColaboradorCabeleireiro(String nome, long idCabeleireiro) {

        String sqlScript = "UPDATE COLABORADOR SET NOME = ? WHERE ID_COLABORADOR = ?";

        PreparedStatement pStatement = null;
        Connection connection = null;

        try {

            connection = new ConnectionMVC().getConnection();

            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setLong(2, idCabeleireiro);
            pStatement.setString(1, nome);

            pStatement.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar colaborador" + e);

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

    public boolean atualizarColaborador(Colaborador colaborador) {

        String sqlScript = "UPDATE COLABORADOR SET NOME = ?, ISCOMISSIONADO = ?, PORCENTAGEMCOMISSAO = ?,"
                + " COMISSAOPORLUCRO = ?, COMISSAOPORQTD = ? WHERE ID_COLABORADOR = ?";

        PreparedStatement pStatement = null;
        Connection connection = null;

        try {

            connection = new ConnectionMVC().getConnection();

            pStatement = connection.prepareStatement(sqlScript);
            pStatement.setString(1, colaborador.getNome());
            pStatement.setBoolean(2, colaborador.isComissionado());
            pStatement.setLong(3, colaborador.getPorcentagemComisao());
            pStatement.setBoolean(4, colaborador.isComissaoPorLucro());
            pStatement.setBoolean(5, colaborador.isComissaoPorQtd());
            pStatement.setLong(6, colaborador.getIdColaborador());

            return pStatement.executeUpdate() == 1;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar colaborador" + e);
            return false;

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
