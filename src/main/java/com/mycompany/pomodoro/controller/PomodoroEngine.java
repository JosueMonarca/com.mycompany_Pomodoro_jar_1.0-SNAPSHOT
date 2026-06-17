
package com.mycompany.pomodoro.controller;

import com.mycompany.pomodoro.model.PomodoroConfig;
import com.mycompany.pomodoro.view.ITimeDisplay;
import java.awt.event.ActionEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.Timer;


public class PomodoroEngine {
    private Timer countdown;
    private Timer listenerLabel;
    private final ITimeDisplay td;
    
    public PomodoroEngine(ITimeDisplay td){
        this.td = td;
    }
    
     public void animationPostCanva(String time){
        final String[] currentTime = { time };
        PomodoroConfig config = PomodoroConfig.getInstance();
        
        if ((countdown != null && countdown.isRunning()) || config.getRepetitions() ==0) {
            countdown.stop();
        }

        countdown = new Timer(1000, (ActionEvent action) -> {
            String times = currentTime[0];
            String[] parts = times.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            int seconds = Integer.parseInt(parts[2]);
            int total = hours * 3600 + minutes * 60 + seconds -1;
            hours = total / 3600;
            minutes = (total % 3600) / 60;
            seconds = total % 60;
            String pibote1 = hours +":"+minutes+":"+seconds;
            currentTime[0] = pibote1;
            config.setTimeKeeper(total);
            td.updateTime(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        });
        countdown.start();
    }

    public void handlePomodoros(){
        PomodoroConfig config = PomodoroConfig.getInstance();
        config.setRepetitions((config.getRepetitions() * 2)-1);
        boolean[] descanso = {false};
        boolean[] alreadyShot = {false};
        
        if (listenerLabel != null && listenerLabel.isRunning()) {
            listenerLabel.stop();
        }                
        
        listenerLabel = new Timer(500 , (ActionEvent action) -> {
            
            int hours = 0;
            int minutes = 0;
            int seconds = 0;
            
            int total = config.getTimeKeeper();
            
            if(total == 0 && config.getRepetitions() != 0 && !alreadyShot[0]){
                alreadyShot[0] = true;
                config.setRepetitions(config.getRepetitions()-1);
                if(!descanso[0]){
                    descanso[0] = true;
                    StringBuilder sb = new StringBuilder();
                    int totalSeconds = config.getBreaktime();
                    
                    hours = totalSeconds / 3600;
                    sb.append(hours);
                    sb.append(":");
                    minutes = (totalSeconds % 3600) / 60;
                    sb.append(minutes);
                    sb.append(":");
                    seconds = totalSeconds % 60;
                    sb.append(seconds);
                    
                    animationPostCanva(sb.toString());
                    try {
                        SoundController.playFirstAlarm();
                        //SoundController.playFirstAlarm();
                    } catch (LineUnavailableException ex) {
                        System.getLogger(SetupController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        System.out.println("Error al reproducir");
                    }
                }else{
                    descanso[0] = false;
                    StringBuilder sb = new StringBuilder();
                    int totalSeconds = config.getWorkTime();
                    
                    hours = totalSeconds / 3600;
                    sb.append(hours);
                    sb.append(":");
                    minutes = (totalSeconds % 3600) / 60;
                    sb.append(minutes);
                    sb.append(":");
                    seconds = totalSeconds % 60;
                    sb.append(seconds);
                    
                    animationPostCanva(sb.toString());
                    
                    try {
                        SoundController.playFirstAlarm();
                    } catch (LineUnavailableException ex) {
                        System.getLogger(SetupController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        System.out.println("Error al reproducir");
                    }
                }
            }
            if(total > 0)  alreadyShot[0] = false;
            if(config.getRepetitions() <=0){
                listenerLabel.stop();
                countdown.stop();
            }
        });
        listenerLabel.start();
    }
}
