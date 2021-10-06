/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BeutifulSalon.model;

/**
 *
 * @author Mateus
 */
public interface ObservadoCliente {
    public void registrarObservador(ObservadorCliente observador);

    public void removeObservador(ObservadorCliente observador);

    public void notificarObservadores();
}
