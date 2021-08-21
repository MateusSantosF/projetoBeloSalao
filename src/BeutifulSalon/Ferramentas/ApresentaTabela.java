/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.controller.AgendamentoController;
import BeutifulSalon.model.Dinheiro;
import BeutifulSalon.model.Servico;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mateus
 */
public class ApresentaTabela {

    public DefaultTableModel apresentaServicosAgendamento(JTable tabela, long idAgendamento) {

        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);
        AgendamentoController ag = new AgendamentoController();
        ArrayList<Servico> servicos = null;

        servicos = ag.listarServicosAgendamento(idAgendamento);

        if (servicos == null) {
            return modelo;
        }

        for (Servico s : servicos) {

            modelo.addRow(new Object[]{
                s.getNome(),
                Dinheiro.parseString(s.getPreco()),
                s.getId()
            });
        }

        return modelo;
    }

}
