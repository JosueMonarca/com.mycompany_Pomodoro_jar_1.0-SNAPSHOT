package com.mycompany.pomodoro.controller.UserFlowController;

import com.mycompany.pomodoro.model.PomodoroConfig;
import com.mycompany.pomodoro.view.ITimeDisplay;
import com.mycompany.pomodoro.view.IView;

public class WorkTimeView extends AbstractView {
    
    public WorkTimeView(IView view, ITimeDisplay td){
        this.view = view;
        this.td = td;
    }

    @Override
    public boolean changeView() {
        PomodoroConfig config = PomodoroConfig.getInstance();
  
        int total = config.getTimeKeeper();
        config.setWorkTime(total);
        
        if(config.getWorkTime() != 0){
            td.updateTime("00:00:00");
            view.setTextInstrucciones("Ingrese el tiempo de descanso");
            config.setTimeKeeper(0);
            return true;
        }else{
            view.setTextJOptionPane("Por favor ingrese un tiempo valido");
            return false;
        }
    }
}
