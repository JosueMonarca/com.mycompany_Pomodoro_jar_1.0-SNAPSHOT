package com.mycompany.pomodoro.controller;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.Timer;
import com.mycompany.pomodoro.model.PomodoroConfig;
import com.mycompany.pomodoro.view.ClockCanvas;
import com.mycompany.pomodoro.view.ITimeDisplay;

public class SetupController {
    private final  ClockCanvas CANVA ;
    private final ClockController controller ;
    private int lastDistance;
    private PomodoroConfig instanceOfModel;
    private Integer counter = 1;
    private Timer timer;
    private final ITimeDisplay td;
    
    public SetupController(ClockCanvas Canva,ClockController controller, ITimeDisplay td){
        CANVA = Canva;
        this.controller = controller;
        lastDistance = 0;
        this.td = td;
    }
    
    public void startCanvasAnimation(){
        int[] tikPrevious = {0}; // fuera del Timer
        instanceOfModel = PomodoroConfig.getInstance();
        
        timer = new Timer(10, (ActionEvent e) -> {
            int distance = controller.getfirstPointX() - controller.getPosicionX();
            int tik = 0;
            
            if (distance != lastDistance){
                if(distance > 0){
                    tik = distance / CANVA.getSpace();
                    if(tik != tikPrevious[0] && tik > tikPrevious[0]){
                        CANVA.animationLeft();
                        if(instanceOfModel.getWorkTime() == 0 || instanceOfModel.getBreaktime() == 0)adjustTime(+1);
                        else adjustRepetitions(+1);
                    }
                    tikPrevious[0] = tik;
                    
                }else{
                    tik = distance / CANVA.getSpace();
                    if (tik != tikPrevious[0] && tik < tikPrevious[0]) {
                        CANVA.animationRight();
                        if(instanceOfModel.getWorkTime() == 0 || instanceOfModel.getBreaktime() == 0) adjustTime(-1);
                        else adjustRepetitions(-1);
                    }
                    tikPrevious[0] = tik;
                }
                
            }
            
            if(instanceOfModel.getBreaktime() != 0){
                
            }
            
            lastDistance = distance;
        });
        timer.start();
    } 
    
    private  void adjustTime(int secondsDelta) {
        PomodoroConfig config =  PomodoroConfig.getInstance();

        int total = config.getTimeKeeper() + secondsDelta*10;

        if (total >= 86400) { // 24*60*60
            JOptionPane.showMessageDialog(null, "No se puede poner un pomodoro mayor a un día");
            total = 86400;
        } else if (total <= 0) {
            total = 0;
        }

        int hours = total / 3600;
        int minutes = (total % 3600) / 60;
        int seconds = total % 60;
        
        config.setTimeKeeper(total);

        td.updateTime(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }
    
    private void adjustRepetitions(int direction){
        if(counter > 0) counter = counter + direction;
        else counter = 1; 
        PomodoroConfig config = PomodoroConfig.getInstance();
        config.setTimeKeeper(counter);
        
        td.updateTime(String.format("%02d", counter));
    }
    
    public void stopAnimation() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }
    
    public void ajustDial(Direction direction){
        if (direction == Direction.RIGHT) this.CANVA.animationRight();
        else this.CANVA.animationLeft();
        
        if (instanceOfModel.getBreaktime() == 0 || instanceOfModel.getWorkTime() == 0)
            this.adjustTime(direction == Direction.RIGHT? -1 : 1);
        else this.adjustRepetitions(direction == Direction.RIGHT? -1 : 1);
    }
}
