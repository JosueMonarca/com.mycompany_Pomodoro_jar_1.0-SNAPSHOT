/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pomodoro.controller.ControladorFlujoVista;

import com.mycompany.pomodoro.controller.IVista;
import com.mycompany.pomodoro.model.PomodoConfigSingle;

/**
 *
 * @author jmona
 */
public class VistaDeTiempoDeTrabajo extends AbstractVista {
    
    public VistaDeTiempoDeTrabajo(IVista vista){
        this.vista = vista;
    }

    @Override
    public boolean changeView() {
        String[] tiempo = vista.getLabelReloj().getText().split(":");
        
        int horas =  Integer.parseInt(tiempo[0]);
        int minutos = Integer.parseInt(tiempo[1]);
        int segundos = Integer.parseInt(tiempo[2]);
        horas = horas * 3600;
        minutos = minutos * 60;
        int total = segundos+minutos+horas;
        PomodoConfigSingle config = PomodoConfigSingle.getInstance();
        config.setTrabajo(total);
        
        if(config.getTrabajo() != 0){
            vista.setTextLabelReloj("00:00:00");
            vista.setTextInstrucciones("Ingrese el tiempo de descanso");
            return true;
        }else{
            vista.setTextJOptionPane("Por favor ingrese un tiempo valido");
            return false;
        }
    }
    
}
