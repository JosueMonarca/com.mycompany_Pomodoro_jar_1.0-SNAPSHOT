
package com.mycompany.pomodoro.controller;

import java.awt.event.ActionEvent;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import com.mycompany.pomodoro.model.PomodoroConfig;
import com.mycompany.pomodoro.view.ClockCanvas;

public class Animator {
    private final  ClockCanvas CANVA ;
    private static ClockController controller ;
    private static int lastDistance;
    private static JLabel labelMain ;
    private static PomodoroConfig instanceOfModel;
    private static Integer counter = 1;
    private static Timer timer;
    private static Timer countdown;
    private static Timer listenerLabel;
    
    public Animator(ClockCanvas Canva,ClockController controller,JLabel labelMain){
        CANVA = Canva;
        Animator.controller = controller;
        lastDistance = 0;
        Animator.labelMain = labelMain;
    }
    
    public void startCanvasAnimation(){
        int[] tikPrevious = {0}; // fuera del Timer
        instanceOfModel = PomodoroConfig.getInstance();
        
        timer = new Timer(10, (ActionEvent e) -> {
            int distance = controller.getfirstPointX()-controller.getPosicionX();
            int tik = 0;
            
            if (distance != lastDistance){
                if(distance > 0){
                    tik = distance / CANVA.getSpace();
                    if(tik != tikPrevious[0] && tik > tikPrevious[0]){
                        CANVA.animationLeft();
                        if(instanceOfModel.getJob() == 0 || instanceOfModel.getBreaktime() == 0)adjustTime(+1);
                        else adjustRepetitions(+1);
                    }
                    tikPrevious[0] = tik;
                    
                }else{
                    tik = distance / CANVA.getSpace();
                    if (tik != tikPrevious[0] && tik < tikPrevious[0]) {
                        CANVA.animationRight();
                        if(instanceOfModel.getJob() == 0 || instanceOfModel.getBreaktime() == 0) adjustTime(-1);
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
    
    private static void adjustTime(int secondsDelta) {
        String time = labelMain.getText();
        String[] parts = time.split(":");

        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);

        int total = hours * 3600 + minutes * 60 + seconds + secondsDelta*10;

        if (total >= 86400) { // 24*60*60
            JOptionPane.showMessageDialog(null, "No se puede poner un pomodoro mayor a un día");
            total = 86400;
        } else if (total <= 0) {
            JOptionPane.showMessageDialog(null, "No se puede poner un pomodoro de 0");
            total = 0;
        }

        hours = total / 3600;
        minutes = (total % 3600) / 60;
        seconds = total % 60;

        labelMain.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }
    
    private void adjustRepetitions(int direccion){
        if(counter > 0) counter = counter + direccion;
        else{ JOptionPane.showMessageDialog(null, "No se puede repetir menos de 1 vez");counter = 1; }
        
        labelMain.setText(String.format("%02d", counter));
    }
    
    public void stopAnimation() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }
    
    public void animationPostCanva(String time){
        final String[] currentTime = { time };
        PomodoroConfig config = PomodoroConfig.getInstance();
        
        if ((countdown != null && countdown.isRunning()) || config.getRepetitions() ==0) {
            countdown.stop();
        }

        countdown = new Timer(1000, (ActionEvent action) -> {
            String times = currentTime[0];
            String[] partes = times.split(":");
            int hours = Integer.parseInt(partes[0]);
            int minutes = Integer.parseInt(partes[1]);
            int seconds = Integer.parseInt(partes[2]);
            int total = hours * 3600 + minutes * 60 + seconds -1;
            hours = total / 3600;
            minutes = (total % 3600) / 60;
            seconds = total % 60;
            String pibote1 = hours +":"+minutes+":"+seconds;
            currentTime[0] = pibote1;
            labelMain.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
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
            String times = labelMain.getText();
            String[] partes = times.split(":");
            
            int hours = Integer.parseInt(partes[0]);
            int minutes = Integer.parseInt(partes[1]);
            int seconds = Integer.parseInt(partes[2]);
            
            int total = hours * 3600 + minutes * 60 + seconds;
            
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
                        System.getLogger(Animator.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        System.out.println("Error al reproducir");
                    }
                }else{
                    descanso[0] = false;
                    StringBuilder sb = new StringBuilder();
                    int totalSeconds = config.getJob();
                    
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
                        SoundController.playFirstAlarm();
                    } catch (LineUnavailableException ex) {
                        System.getLogger(Animator.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
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
