
package com.mycompany.pomodoro.controller.UserFlowController;

import java.util.function.Consumer;

import com.mycompany.pomodoro.view.IView;

public class UserFlowController {
    private Runnable stopAnimation;
    private Consumer<String> animationPostCanva;
    private Runnable handlePomodoros;
    private PomodoroState pomodoroState = PomodoroState.CONFIGURE_WORK;
    private IView view = null;
    
    public UserFlowController(IView view){
        this.view = view;
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
                if(new WorkTimeView(view).changeView())
                    pomodoroState = PomodoroState.CONFIGURE_BREAK;
            }
            case CONFIGURE_BREAK -> {
                if(new BreakTimeView(view).changeView())
                    pomodoroState = PomodoroState.CONFIGURE_REPETITIONS;
            }
            case CONFIGURE_REPETITIONS -> {
                if(new PomodoroCountView(view, stopAnimation).changeView())
                    pomodoroState = PomodoroState.START_COUNTDOWN;
            }
            case START_COUNTDOWN -> {
                if(new StartCountDownView(animationPostCanva,handlePomodoros).changeView());
                pomodoroState = PomodoroState.IN_PROGRESS;
            }
            case IN_PROGRESS -> new SkipPomodoroView(stopAnimation,animationPostCanva,handlePomodoros,view).changeView();
        }
    }
}
