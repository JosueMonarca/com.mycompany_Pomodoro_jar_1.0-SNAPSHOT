
package com.mycompany.pomodoro.controller.UserFlowController;

import com.mycompany.pomodoro.model.PomodoroConfig;
import com.mycompany.pomodoro.view.ITimeDisplay;
import com.mycompany.pomodoro.view.IView;
/**
 *
 * @author jmona
 */
public class BreakTimeView extends AbstractView{

    public BreakTimeView(IView view,ITimeDisplay td){
        this.view = view;
        this.td = td;
    }
    
    @Override
    public boolean changeView() {
        PomodoroConfig config = PomodoroConfig.getInstance();
        int total = config.getTimeKeeper();
        config.setBreakTime(total);
       
        if(config.getBreaktime() != 0){
            td.updateTime("00");
            view.setTextInstrucciones("Ingrese el numero de pomodoros a realizar");
            config.setTimeKeeper(0);
            return true;
        }else{
            view.setTextJOptionPane("Por favor ingrese un numero valido");
            return false;
        }
    }   
}