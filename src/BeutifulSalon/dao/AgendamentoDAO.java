/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.dao;

import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.ItemCompra;
import BeutifulSalon.model.Servico;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class AgendamentoDAO {

    public void cadastraAgendamento(Agendamento agendamento) throws SQLException, SQLException {

        String insertAgendamento = "INSERT INTO AGENDAMENTO (DATA, HORARIO, CPF_CLIENTE) VALUES (?, ?, ?)";

        String insertServicoAgendamento = "INSERT INTO AGENDAMENTO_SERVICO (ID_AGENDAMENTO, ID_SERVICO) "
                + "VALUES ((SELECT ID_AGENDAMENTO FROM AGENDAMENTO ORDER BY ID_AGENDAMENTO DESC LIMIT 1), ?)";

        Connection connection = null;
        PreparedStatement pStatement = null;

        try {

            connection = new ConnectionMVC().getConnection();
            connection.setAutoCommit(false);

            pStatement = connection.prepareStatement(insertAgendamento);
            pStatement.setDate(1, new Date(agendamento.getData().getTime()));
            pStatement.setDate(2, new Date(agendamento.getHorario().toNanoOfDay()));
            pStatement.setString(3, agendamento.getCpfCliente());

            int firstInsert = pStatement.executeUpdate();

            if (firstInsert > 0) {
                try {

                    ArrayList<Servico> servicos = agendamento.getServicos();

                    for (Servico s : servicos) {
                        pStatement = connection.prepareStatement(insertServicoAgendamento);
                        pStatement.setLong(1, s.getId());
                        pStatement.executeUpdate();
                    }

                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erro registrar serviço" + e);
                    connection.rollback();
                }
            }

            connection.commit();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro DAO" + e);
            connection.rollback();

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
