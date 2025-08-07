/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pomodoro.controller.ControladorFlujoVista;

import com.mycompany.pomodoro.controller.IVista;

/**
 *
 * @author jmona
 */
public abstract class AbstractVista {
    protected IVista vista;
    
    protected String convertirASegundosAFormato(int totalSegundos) {
        int horas = totalSegundos / 3600;
        int minutos = (totalSegundos % 3600) / 60;
        int segundos = totalSegundos % 60;

        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }
    
    public abstract boolean changeView();
}
