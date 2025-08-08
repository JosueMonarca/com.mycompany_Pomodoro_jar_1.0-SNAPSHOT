/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pomodoro.controller.UserFlowController;

import com.mycompany.pomodoro.model.PomodoroConfig;
import com.mycompany.pomodoro.view.IView;

/**
 *
 * @author jmona
 */
public class PomodoroCountView extends AbstractView {
    private final Runnable stopAnimation;
    
    public PomodoroCountView(IView view,Runnable stopAnimation){
        this.view = view;
        this.stopAnimation = stopAnimation;
    }

    @Override
    public boolean changeView() {
        PomodoroConfig config = PomodoroConfig.getInstance();
        String numberPomodoros = view.getLabelClock().getText();
        
        config.setRepetitions(Integer.parseInt(numberPomodoros));
        StringBuilder sb = new StringBuilder();
        int totalSeconds = config.getTrabajo();

        int hours = totalSeconds / 3600;
        sb.append(hours);
        sb.append(":");
        int minutes = (totalSeconds % 3600) / 60;
        sb.append(minutes);
        sb.append(":");
        int seconds = totalSeconds % 60;
        sb.append(seconds);
        
        if(config.getRepetitions() != 0){ 
            view.setTextInstrucciones("");
            view.setTextButtonPrincipal("Iniciar");
            view.setTextLabelReloj(sb.toString());
            view.hideCanva();
            //Pomodoro.stopAnimation();
            stopAnimation.run();
            return true;
        }else{
            view.setTextJOptionPane("Por favor ingrese un numero de veces valido es mayor de 0");
            return false;
        }  
    }
    
}
