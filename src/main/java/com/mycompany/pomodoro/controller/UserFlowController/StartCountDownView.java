
package com.mycompany.pomodoro.controller.UserFlowController;

import com.mycompany.pomodoro.view.IView;

public class StartCountDownView extends AbstractView {

    private final Runnable startSession;
    private final IView view;

    public StartCountDownView(IView view,Runnable startSession){
        this.startSession =  startSession;
        this.view = view;
    }

    @Override
    public boolean changeView() {
        startSession.run();
        view.setTextButtonPrincipal("Siguiente");
        return true;
    }
}
