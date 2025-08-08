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
public class WorkTimeView extends AbstractView {
    
    public WorkTimeView(IView view){
        this.view = view;
    }

    @Override
    public boolean changeView() {
        String[] time = view.getLabelClock().getText().split(":");
        
        int hours =  Integer.parseInt(time[0]);
        int minutes = Integer.parseInt(time[1]);
        int seconds = Integer.parseInt(time[2]);
        hours = hours * 3600;
        minutes = minutes * 60;
        int total = seconds+minutes+hours;
        PomodoroConfig config = PomodoroConfig.getInstance();
        config.setTrabajo(total);
        
        if(config.getJob() != 0){
            view.setTextLabelReloj("00:00:00");
            view.setTextInstrucciones("Ingrese el tiempo de descanso");
            return true;
        }else{
            view.setTextJOptionPane("Por favor ingrese un tiempo valido");
            return false;
        }
    }
    
}
