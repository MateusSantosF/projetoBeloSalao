/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.Ferramentas;

import BeutifulSalon.controller.AgendamentoController;
import BeutifulSalon.controller.CabeleireiroController;
import BeutifulSalon.dao.ExceptionDAO;
import BeutifulSalon.model.Agendamento;
import BeutifulSalon.model.Servico;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author mateus
 */
public class testes {

    public static void main(String[] args) {

        //pegar todos orçamento servico por ano
        //pegar agendamentos por mes e verificar seus serviços 
        //pegar a quantidade mensal de um serviço e multiplicar pelo preço
         DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/M/uuuu");
         LocalDate data = LocalDate.parse("16 de ago. de 2021", formatterData);
         
         System.out.println(data);
      

    }
}
