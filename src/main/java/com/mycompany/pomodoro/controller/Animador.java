/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pomodoro.controller;

import com.mycompany.pomodoro.model.PomodoConfigSingle;
import com.mycompany.pomodoro.view.CanvaDelReloj;
import java.awt.event.ActionEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author jmona
 */
public class Animador {
    private static CanvaDelReloj Canva ;
    private static controladorReloj controlador ;
    private static int pibote;
    private static JLabel labelPrincipal ;
    private static PomodoConfigSingle pcs;
    private static Integer contador = 1;
    private static Timer timer;
    private static Timer cuentaRegresiva;
    private static Timer listenerLabel;
    
    public Animador(CanvaDelReloj Canva,controladorReloj controlador,JLabel labelPrincipal){
        Animador.Canva = Canva;
        Animador.controlador = controlador;
        pibote = 0;
        Animador.labelPrincipal = labelPrincipal;
    }
    
    public void animationOfCanva(){
        int[] tikAnterior = {0}; // fuera del Timer
        pcs = PomodoConfigSingle.getInstance();
        
        timer = new Timer(10, (ActionEvent e) -> {
            int distancia = controlador.getPrimerPuntox()-controlador.getPosicionX();
            int tik = 0;
            
            if (distancia != pibote){
                if(distancia > 0){
                    tik = distancia / Canva.getEspacio();
                    if(tik != tikAnterior[0] && tik > tikAnterior[0]){
                        Canva.animationLeft();
                        if(pcs.getTrabajo() == 0 || pcs.getDescanso() == 0)ajustarTiempo(+1);
                        else ajustarRepeticiones(+1);
                    }
                    tikAnterior[0] = tik;
                    
                }else{
                    tik = distancia / Canva.getEspacio();
                    if (tik != tikAnterior[0] && tik < tikAnterior[0]) {
                        Canva.animationRight();
                        if(pcs.getTrabajo() == 0 || pcs.getDescanso() == 0)ajustarTiempo(-1);
                        else ajustarRepeticiones(-1);
                    }
                    tikAnterior[0] = tik;
                }
                
            }
            
            if(pcs.getDescanso() != 0){
                
            }
            
            pibote = distancia;
        });
        timer.start();
    } 
    
    private static void ajustarTiempo(int segundosDelta) {
        String time = labelPrincipal.getText();
        String[] partes = time.split(":");

        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        int segundos = Integer.parseInt(partes[2]);

        int total = horas * 3600 + minutos * 60 + segundos + segundosDelta*10;

        if (total >= 86400) { // 24*60*60
            JOptionPane.showMessageDialog(null, "No se puede poner un pomodoro mayor a un día");
            total = 86400;
        } else if (total <= 0) {
            JOptionPane.showMessageDialog(null, "No se puede poner un pomodoro de 0");
            total = 0;
        }

        horas = total / 3600;
        minutos = (total % 3600) / 60;
        segundos = total % 60;

        labelPrincipal.setText(String.format("%02d:%02d:%02d", horas, minutos, segundos));
    }
    
    private void ajustarRepeticiones(int direccion){
        if(contador > 0) contador = contador + direccion;
        else{ JOptionPane.showMessageDialog(null, "No se puede repetir menos de 1 vez");contador = 1; }
        
        labelPrincipal.setText(String.format("%02d", contador));
    }
    
    public void detenerAnimacion() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }
    
    public void animationPostCanva(String tiempo){
        final String[] tiempoActual = { tiempo };
        PomodoConfigSingle config = PomodoConfigSingle.getInstance();
        
        if ((cuentaRegresiva != null && cuentaRegresiva.isRunning()) || config.getRepeticiones() ==0) {
            cuentaRegresiva.stop();
        }

        cuentaRegresiva = new Timer(1000, (ActionEvent action) -> {
            String times = tiempoActual[0];
            String[] partes = times.split(":");
            int horas = Integer.parseInt(partes[0]);
            int minutos = Integer.parseInt(partes[1]);
            int segundos = Integer.parseInt(partes[2]);
            int total = horas * 3600 + minutos * 60 + segundos -1;
            horas = total / 3600;
            minutos = (total % 3600) / 60;
            segundos = total % 60;
            String pibote1 = horas +":"+minutos+":"+segundos;
            tiempoActual[0] = pibote1;
            labelPrincipal.setText(String.format("%02d:%02d:%02d", horas, minutos, segundos));
        });
        cuentaRegresiva.start();
    }
    
    public void controlarPomodoros(){
        PomodoConfigSingle config = PomodoConfigSingle.getInstance();
        config.setRepeticiones((config.getRepeticiones() * 2)-1);
        boolean[] descanso = {false};
        boolean[] yaDisparo = {false};
        
        if (listenerLabel != null && listenerLabel.isRunning()) {
            listenerLabel.stop();
        }                
        
        listenerLabel = new Timer(500 , (ActionEvent action) -> {
            String times = labelPrincipal.getText();
            String[] partes = times.split(":");
            
            int horas = Integer.parseInt(partes[0]);
            int minutos = Integer.parseInt(partes[1]);
            int segundos = Integer.parseInt(partes[2]);
            
            int total = horas * 3600 + minutos * 60 + segundos;
            
            if(total == 0 && config.getRepeticiones() != 0 && !yaDisparo[0]){
                yaDisparo[0] = true;
                config.setRepeticiones(config.getRepeticiones()-1);
                if(!descanso[0]){
                    descanso[0] = true;
                    StringBuilder sb = new StringBuilder();
                    int totalSegundos = config.getDescanso();
                    
                    horas = totalSegundos / 3600;
                    sb.append(horas);
                    sb.append(":");
                    minutos = (totalSegundos % 3600) / 60;
                    sb.append(minutos);
                    sb.append(":");
                    segundos = totalSegundos % 60;
                    sb.append(segundos);
                    
                    animationPostCanva(sb.toString());
                    try {
                        ControladorDeSonidos.reproducirPrimeraAlarma();
                        //ControladorDeSonidos.reproducirPrimeraAlarma();
                    } catch (LineUnavailableException ex) {
                        System.getLogger(Animador.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        System.out.println("Error al reproducir");
                    }
                }else{
                    descanso[0] = false;
                    StringBuilder sb = new StringBuilder();
                    int totalSegundos = config.getTrabajo();
                    
                    horas = totalSegundos / 3600;
                    sb.append(horas);
                    sb.append(":");
                    minutos = (totalSegundos % 3600) / 60;
                    sb.append(minutos);
                    sb.append(":");
                    segundos = totalSegundos % 60;
                    sb.append(segundos);
                    
                    animationPostCanva(sb.toString());
                    
                    try {
                        ControladorDeSonidos.reproducirPrimeraAlarma();
                        ControladorDeSonidos.reproducirPrimeraAlarma();
                    } catch (LineUnavailableException ex) {
                        System.getLogger(Animador.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                        System.out.println("Error al reproducir");
                    }
                }
            }
            if(total > 0)  yaDisparo[0] = false;
            if(config.getRepeticiones() <=0){
                listenerLabel.stop();
                cuentaRegresiva.stop();
            }
        });
        listenerLabel.start();
    }
}
