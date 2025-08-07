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
public class VistaDeNumeroDePomodoros extends AbstractVista {
    private final Runnable detenerAnimacion;
    
    public VistaDeNumeroDePomodoros(IVista vista,Runnable detenerAnimacion){
        this.vista = vista;
        this.detenerAnimacion = detenerAnimacion;
    }

    @Override
    public boolean changeView() {
       PomodoConfigSingle config = PomodoConfigSingle.getInstance();
        String numeroDePomodoros = vista.getLabelReloj().getText();
        
        config.setRepeticiones(Integer.parseInt(numeroDePomodoros));
        StringBuilder sb = new StringBuilder();
        int totalSegundos = config.getTrabajo();

        int horas = totalSegundos / 3600;
        sb.append(horas);
        sb.append(":");
        int minutos = (totalSegundos % 3600) / 60;
        sb.append(minutos);
        sb.append(":");
        int segundos = totalSegundos % 60;
        sb.append(segundos);
        
        if(config.getRepeticiones() != 0){ 
            vista.setTextInstrucciones("");
            vista.setTextButtonPrincipal("Iniciar");
            vista.setTextLabelReloj(sb.toString());
            vista.hideCanva();
            //Pomodoro.detenerAnimacion();
            detenerAnimacion.run();
            return true;
        }else{
            vista.setTextJOptionPane("Por favor ingrese un numero de veces valido es mayor de 0");
            return false;
        }  
    }
    
}
