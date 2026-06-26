package com.mycompany.pomodoro.controller.UserFlowController;
import com.mycompany.pomodoro.view.ITimeDisplay;
import com.mycompany.pomodoro.view.IView;

public class UserFlowController {
    private Runnable stopAnimation;
    private Runnable startSession;
    private Runnable skipSession;

    public void setSkipSession(Runnable skipSession) {
        this.skipSession = skipSession;
    }
    private PomodoroState pomodoroState = PomodoroState.CONFIGURE_WORK;
    private IView view = null;
    private final ITimeDisplay td;
    
    public UserFlowController(IView view, ITimeDisplay td){
        this.view = view;
        this.td = td;
    }

    public void setstopAnimation(Runnable stopAnimation) {
        this.stopAnimation = stopAnimation;
    }

    public void setStartSession(Runnable startSession) {
        this.startSession = startSession;
    }
    
    public void next(){
        
        switch (pomodoroState) {
            case CONFIGURE_WORK -> {
                if(new WorkTimeView(view,td).changeView())
                    pomodoroState = PomodoroState.CONFIGURE_BREAK;
            }
            case CONFIGURE_BREAK -> {
                if(new BreakTimeView(view,td).changeView())
                    pomodoroState = PomodoroState.CONFIGURE_REPETITIONS;
            }
            case CONFIGURE_REPETITIONS -> {
                if(new PomodoroCountView(view, stopAnimation,td).changeView())
                    pomodoroState = PomodoroState.START_COUNTDOWN;
            }
            case START_COUNTDOWN -> {
                if(new StartCountDownView(view,startSession).changeView())
                pomodoroState = PomodoroState.IN_PROGRESS;
            }
            case IN_PROGRESS -> new SkipPomodoroView(this.skipSession,view,td).changeView();
        }
    }
}
