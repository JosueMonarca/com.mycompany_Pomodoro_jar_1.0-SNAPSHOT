
package com.mycompany.pomodoro.controller.UserFlowController;


import javax.swing.JOptionPane;

import com.mycompany.pomodoro.model.PomodoroConfig;
import com.mycompany.pomodoro.view.ITimeDisplay;
import com.mycompany.pomodoro.view.IView;

public class SkipPomodoroView extends AbstractView {
    
    private final Runnable skipSession;
    
    public SkipPomodoroView (Runnable skipSession,IView viewFrame, ITimeDisplay td){
        this.skipSession = skipSession;
        this.view = viewFrame;
        this.td = td;
    }

    @Override
    public boolean changeView() {
        // Si el reloj ya terminó, no cambiar de view
        PomodoroConfig config = PomodoroConfig.getInstance();
        if (config.getTimeKeeper()== 0) return false;
        
        int answer = view.showMensajeOfConfirm("¿Desea saltar la sesión actual?");

        switch (answer) {
            case JOptionPane.NO_OPTION, JOptionPane.CANCEL_OPTION -> {
                return false;
            }
            case JOptionPane.YES_OPTION -> {
                this.skipSession.run();
                return true;
            }
            default -> {
                return false;
            }
        }
    }
    
}
