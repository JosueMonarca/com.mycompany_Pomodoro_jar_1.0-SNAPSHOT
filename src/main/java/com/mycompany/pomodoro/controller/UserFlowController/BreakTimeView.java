
package com.mycompany.pomodoro.controller.UserFlowController;

import com.mycompany.pomodoro.model.PomodoroConfig;
import com.mycompany.pomodoro.view.IView;
/**
 *
 * @author jmona
 */
public class BreakTimeView extends AbstractView{

    public BreakTimeView(IView view){
        this.view = view;
    }
    @Override
    public boolean changeView() {
        String[] time = view.getLabelClock().getText().split(":");
        
        PomodoroConfig config = PomodoroConfig.getInstance();
        int hours =  Integer.parseInt(time[0]);
        int minutes = Integer.parseInt(time[1]);
        int seconds = Integer.parseInt(time[2]);
        hours = hours * 3600;
        minutes = minutes * 60;
        int total = seconds + minutes + hours;
        config.setRest(total);
        
        if(config.getRest() != 0){
            view.setTextLabelReloj("00");
            view.setTextInstrucciones("Ingrese el numero de pomodoros a realizar");
            return true;
        }else{
            view.setTextJOptionPane("Por favor ingrese un numero valido");
            return false;
        }
    }
    
}