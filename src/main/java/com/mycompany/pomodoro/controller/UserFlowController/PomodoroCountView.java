
package com.mycompany.pomodoro.controller.UserFlowController;

import com.mycompany.pomodoro.model.PomodoroConfig;
import com.mycompany.pomodoro.view.ITimeDisplay;
import com.mycompany.pomodoro.view.IView;

public class PomodoroCountView extends AbstractView {
    private final Runnable stopAnimation;
    
    public PomodoroCountView(IView view,Runnable stopAnimation,ITimeDisplay td){
        this.view = view;
        this.stopAnimation = stopAnimation;
        this.td = td;
    }

    @Override
    public boolean changeView() {
        PomodoroConfig config = PomodoroConfig.getInstance();
        
        config.setRepetitions(config.getTimeKeeper());
        StringBuilder sb = new StringBuilder();
        int totalSeconds = config.getWorkTime();

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
            td.updateTime(sb.toString());
            view.hideCanva();
            //Pomodoro.stopAnimation();
            stopAnimation.run();
            config.setTimeKeeper(0);
            return true;
        }else{
            view.setTextJOptionPane("Por favor ingrese un numero de veces valido");
            return false;
        }  
    }   
}
