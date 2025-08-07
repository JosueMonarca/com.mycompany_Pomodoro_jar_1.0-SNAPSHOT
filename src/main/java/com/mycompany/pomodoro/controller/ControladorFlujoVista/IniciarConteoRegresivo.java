/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pomodoro.controller.ControladorFlujoVista;

import com.mycompany.pomodoro.model.PomodoConfigSingle;
import java.util.function.Consumer;

/**
 *
 * @author jmona
 */
public class IniciarConteoRegresivo extends AbstractVista {
    
    private final Consumer<String> animationPostCanva;
    private final Runnable controlarPomodoros;
    
    public IniciarConteoRegresivo(Consumer<String> animationPostCanva,Runnable controlarPomodoros){
        this.animationPostCanva = animationPostCanva;
        this.controlarPomodoros =  controlarPomodoros;
    }

    @Override
    public boolean changeView() {
        //Pomodoro.controlarPomodoros();
        PomodoConfigSingle config = PomodoConfigSingle.getInstance();
        StringBuilder strBlr = new StringBuilder();
        int tsegundos = config.getTrabajo();
        
        int hora = tsegundos / 3600;
        strBlr.append(hora);
        strBlr.append(":");
        int minuto = (tsegundos % 3600) / 60;
        strBlr.append(minuto);
        strBlr.append(":");
        int segundo = tsegundos % 60;
        strBlr.append(segundo);
        
        if(!strBlr.toString().equalsIgnoreCase("00:00:00")){
            //Pomodoro.animationPostCanva(strBlr.toString());
            animationPostCanva.accept(strBlr.toString());
            //Pomodoro.controlarPomodoros();
            controlarPomodoros.run();
            return true;
        }else{
            return false;
        }
    }
    
}
