package com.mycompany.pomodoro.controller;
import com.mycompany.pomodoro.model.PomodoroConfig;
import com.mycompany.pomodoro.view.ITimeDisplay;
import java.awt.event.ActionEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.Timer;

public class PomodoroEngine {
    private Timer sessionTimer; 
    private final ITimeDisplay td;
    private final PomodoroConfig config;
    private boolean isWorkPhase; 
    
    public PomodoroEngine(ITimeDisplay td){
        this.td = td;
        this.config = PomodoroConfig.getInstance();
    }
    
    public void startSession(){
        int actualTime = config.getWorkTime();
        config.setTimeKeeper(actualTime);
        this.updateDisplay(actualTime);
        this.stopSession();
        this.isWorkPhase = true;
        config.setRepetitions((config.getRepetitions() * 2 ) - 1);
        
        sessionTimer =  new Timer(1000, (ActionEvent action)->{
            this.handlePhaseTransition();
        });
        if (config.getWorkTime() >= 400) this.playSong();
        sessionTimer.start();
    }
    
    public void stopSession(){
        if(sessionTimer != null && sessionTimer.isRunning()){
            sessionTimer.stop();
        }
    }
    private void countdownTick(){
        config.setTimeKeeper(config.getTimeKeeper() - 1);
        this.updateDisplay(config.getTimeKeeper());
    }

    private void handlePhaseTransition(){
        
        if (config.getTimeKeeper() <= 0) {
            
            config.setRepetitions(config.getRepetitions() - 1);
            
            if (config.getRepetitions() <= 0) {
                this.updateDisplay(0); 
                this.stopSession();    
                return;                
            }

            isWorkPhase = !isWorkPhase;
            if (isWorkPhase) config.setTimeKeeper(config.getWorkTime());
            else config.setTimeKeeper(config.getBreaktime());
            
            this.playFirstAlarm();
            
            this.updateDisplay(config.getTimeKeeper());
            
        } else {
            countdownTick();
        }
    }

    public void skipPhase(){
        try {
            config.setTimeKeeper(0);
            SoundController.stopSong();
            this.handlePhaseTransition();
        } catch (LineUnavailableException ex) {
            System.getLogger(PomodoroEngine.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
 
    private void updateDisplay(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;
        this.td.updateTime(String.format("%02d:%02d:%02d",hours,minutes,seconds));
    }

    private int playFirstAlarm() {
        try {
            SoundController.playFirstAlarm();
        } catch (LineUnavailableException ex) {
            System.getLogger(PomodoroEngine.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return -1;
        }
        return 0;
    }
    
    private int playSong() {
        try {
            SoundController.playSecondAlarm();
        } catch (LineUnavailableException ex) {
            System.getLogger(PomodoroEngine.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            return -1;
        }
        return 0;
    }
}