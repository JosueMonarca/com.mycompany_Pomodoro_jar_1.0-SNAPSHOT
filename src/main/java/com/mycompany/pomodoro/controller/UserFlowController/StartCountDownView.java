/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pomodoro.controller.UserFlowController;

import java.util.function.Consumer;

import com.mycompany.pomodoro.model.PomodoroConfig;

/**
 *
 * @author jmona
 */
public class StartCountDownView extends AbstractView {

    private final Consumer<String> animationPostCanva;
    private final Runnable handlePomodoros;

    public StartCountDownView(Consumer<String> animationPostCanva,Runnable handlePomodoros){
        this.animationPostCanva = animationPostCanva;
        this.handlePomodoros =  handlePomodoros;
    }

    @Override
    public boolean changeView() {
        //Pomodoro.handlePomodoros();
        PomodoroConfig config = PomodoroConfig.getInstance();
        StringBuilder strBlr = new StringBuilder();
        int tseconds = config.getJob();
        
        int hours = tseconds / 3600;
        strBlr.append(hours);
        strBlr.append(":");
        int minutes = (tseconds % 3600) / 60;
        strBlr.append(minutes);
        strBlr.append(":");
        int second = tseconds % 60;
        strBlr.append(second);
        
        if(!strBlr.toString().equalsIgnoreCase("00:00:00")){
            //Pomodoro.animationPostCanva(strBlr.toString());
            animationPostCanva.accept(strBlr.toString());
            //Pomodoro.handlePomodoros();
            handlePomodoros.run();
            return true;
        }else{
            return false;
        }
    }
    
}
