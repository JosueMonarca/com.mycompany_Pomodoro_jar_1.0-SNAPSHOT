package com.mycompany.pomodoro.controller;
import com.mycompany.pomodoro.model.PomodoroConfig;
import com.mycompany.pomodoro.view.ITimeDisplay;
import com.mycompany.pomodoro.view.MainFrame;
import java.awt.event.ActionEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class PomodoroEngine {
    private Timer sessionTimer; 
    private final ITimeDisplay td;
    private final PomodoroConfig config;
    private boolean isWorkPhase; 
    private final MainFrame mainFrame;
    
    public PomodoroEngine(ITimeDisplay td, MainFrame mainFrame){
        this.td = td;
        this.config = PomodoroConfig.getInstance();
        this.mainFrame = mainFrame;
    }
    
    public void startSession(){
        int actualTime = config.getWorkTime();
        config.setTimeKeeper(actualTime);
        this.updateDisplay(actualTime);
        this.stopSession();
        this.isWorkPhase = true;
        mainFrame.getButtonPrincipal().setFocusable(false);
        config.setRepetitions((config.getRepetitions() * 2 ) - 1);
        
         // --- CONFIGURACIÓN DE LA BARRA ESPACIADORA ---
        InputMap inputMap = mainFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = mainFrame.getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "pauseClock");
        actionMap.put("pauseClock", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Solo permitimos pausar si el motor ya arrancó
                 togglePause();
            }
        });
        
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
    
    public void togglePause(){
        if(this.sessionTimer != null){
            if (this.sessionTimer.isRunning()){
                this.sessionTimer.stop();
                SoundController.stopSong();
            }
            else {
                this.sessionTimer.start();
                SoundController.restartSong();
            }
        }
    }

    public void skipPhase(){
        config.setTimeKeeper(0);
        SoundController.killSong();
        this.handlePhaseTransition();
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