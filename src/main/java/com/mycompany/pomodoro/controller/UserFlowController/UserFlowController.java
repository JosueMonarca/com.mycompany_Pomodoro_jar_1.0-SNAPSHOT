
package com.mycompany.pomodoro.controller.UserFlowController;

import com.mycompany.pomodoro.view.ITimeDisplay;
import java.util.function.Consumer;

import com.mycompany.pomodoro.view.IView;

public class UserFlowController {
    private Runnable stopAnimation;
    private Consumer<String> animationPostCanva;
    private Runnable handlePomodoros;
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

    public void setAnimationPostCanva(Consumer<String> animationPostCanva) {
        this.animationPostCanva = animationPostCanva;
    }

    public void sethandlePomodoros(Runnable handlePomodoros) {
        this.handlePomodoros = handlePomodoros;
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
                if(new StartCountDownView(animationPostCanva,handlePomodoros).changeView());
                pomodoroState = PomodoroState.IN_PROGRESS;
            }
            case IN_PROGRESS -> new SkipPomodoroView(stopAnimation,animationPostCanva,handlePomodoros,view,td).changeView();
        }
    }
}
