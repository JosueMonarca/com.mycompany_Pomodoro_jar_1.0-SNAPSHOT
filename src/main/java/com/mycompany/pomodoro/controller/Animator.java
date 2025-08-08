/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pomodoro.controller;

import com.mycompany.pomodoro.model.PomodoroConfig;
import com.mycompany.pomodoro.view.ClockCanvas;
import java.awt.event.ActionEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author jmona
 */
public class Animator {
    private static ClockCanvas Canva ;
    private static ClockController controlador ;
    private static int pibote;
    private static JLabel labelPrincipal ;
    private static PomodoroConfig pcs;
    private static Integer contador = 1;
    private static Timer timer;
    private static Timer cuentaRegresiva;
    private static Timer listenerLabel;
    
    public Animator(ClockCanvas Canva,ClockController controlador,JLabel labelPrincipal){
        Animator.Canva = Canva;
        Animator.controlador = controlador;
        pibote = 0;
        Animator.labelPrincipal = labelPrincipal;
    }
    
    public void startCanvasAnimation(){
        int[] tikAnterior = {0}; // fuera del Timer
        pcs = PomodoroConfig.getInstance();
        
        timer = new Timer(10, (ActionEvent e) -> {
            int distancia = controlador.getfirstPointX()-controlador.getPosicionX();
            int tik = 0;
            
            if (distancia != pibote){
                if(distancia > 0){
                    tik = distancia / Canva.getSpace();
                    if(tik != tikAnterior[0] && tik > tikAnterior[0]){
                        Canva.animationLeft();
                        if(pcs.getTrabajo() == 0 || pcs.getRest() == 0)ajustarTiempo(+1);
                        else ajustarRepetitions(+1);
                    }
                    tikAnterior[0] = tik;
                    
                }else{
                    tik = distancia / Canva.getSpace();
                    if (tik != tikAnterior[0] && tik < tikAnterior[0]) {
                        Canva.animationRight();
                        if(pcs.getTrabajo() == 0 || pcs.getRest() == 0)ajustarTiempo(-1);
                        else ajustarRepetitions(-1);
                    }
                    tikAnterior[0] = tik;
                }
                
            }
            
            if(pcs.getRest() != 0){
                
            }
            
            pibote = distancia;
        });
        timer.start();
    } 
    
    private static void ajustarTiempo(int secondsDelta) {
        String time = labelPrincipal.getText();
        String[] partes = time.split(":");

        int hours = Integer.parseInt(partes[0]);
        int minutes = Integer.parseInt(partes[1]);
        int seconds = Integer.parseInt(partes[2]);

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

        labelPrincipal.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }
    
    private void ajustarRepetitions(int direccion){
        if(contador > 0) contador = contador + direccion;
        else{ JOptionPane.showMessageDialog(null, "No se puede repetir menos de 1 vez");contador = 1; }
        
        labelPrincipal.setText(String.format("%02d", contador));
    }
    
    public void stopAnimation() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }
    
    public void animationPostCanva(String time){
        final String[] timeActual = { time };
        PomodoroConfig config = PomodoroConfig.getInstance();
        
        if ((cuentaRegresiva != null && cuentaRegresiva.isRunning()) || config.getRepetitions() ==0) {
            cuentaRegresiva.stop();
        }

        cuentaRegresiva = new Timer(1000, (ActionEvent action) -> {
            String times = timeActual[0];
            String[] partes = times.split(":");
            int hours = Integer.parseInt(partes[0]);
            int minutes = Integer.parseInt(partes[1]);
            int seconds = Integer.parseInt(partes[2]);
            int total = hours * 3600 + minutes * 60 + seconds -1;
            hours = total / 3600;
            minutes = (total % 3600) / 60;
            seconds = total % 60;
            String pibote1 = hours +":"+minutes+":"+seconds;
            timeActual[0] = pibote1;
            labelPrincipal.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        });
        cuentaRegresiva.start();
    }
    
    public void handlePomodoros(){
        PomodoroConfig config = PomodoroConfig.getInstance();
        config.setRepetitions((config.getRepetitions() * 2)-1);
        boolean[] descanso = {false};
        boolean[] yaDisparo = {false};
        
        if (listenerLabel != null && listenerLabel.isRunning()) {
            listenerLabel.stop();
        }                
        
        listenerLabel = new Timer(500 , (ActionEvent action) -> {
            String times = labelPrincipal.getText();
            String[] partes = times.split(":");
            
            int hours = Integer.parseInt(partes[0]);
            int minutes = Integer.parseInt(partes[1]);
            int seconds = Integer.parseInt(partes[2]);
            
            int total = hours * 3600 + minutes * 60 + seconds;
            
            if(total == 0 && config.getRepetitions() != 0 && !yaDisparo[0]){
                yaDisparo[0] = true;
                config.setRepetitions(config.getRepetitions()-1);
                if(!descanso[0]){
                    descanso[0] = true;
                    StringBuilder sb = new StringBuilder();
                    int totalSeconds = config.getRest();
                    
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
                    int totalSeconds = config.getTrabajo();
                    
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
            if(total > 0)  yaDisparo[0] = false;
            if(config.getRepetitions() <=0){
                listenerLabel.stop();
                cuentaRegresiva.stop();
            }
        });
        listenerLabel.start();
    }
}
