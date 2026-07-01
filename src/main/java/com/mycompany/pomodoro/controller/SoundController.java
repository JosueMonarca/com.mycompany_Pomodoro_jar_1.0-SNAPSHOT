package com.mycompany.pomodoro.controller;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import com.mycompany.pomodoro.Pomodoro;
import javax.sound.sampled.LineEvent;

public class SoundController {
    private static Clip clip;
    private static Clip song;

    public static Clip getClip() {
        return clip;
    }

    public static Clip getSong() {
        return song;
    }
    
    public static void stopFirstAlarm() throws LineUnavailableException{
       if(clip != null && clip.isRunning()){
           clip.stop();
           clip.close();
       }
    }
    
   public static void stopSong() {
       if (song != null && song.isRunning()) {
           song.stop();
       }
    }
    
    public static void killSong() {
       if (song != null) {
           song.stop();  // La detenemos por si acaso estaba sonando
           song.close(); // La destruimos para liberar la memoria
       }
    }
    
    public static void restartSong() {
       if (song != null && !song.isRunning()) {
           song.start();
       }
    }
    
    public static void playFirstAlarm() throws LineUnavailableException{
        try{
            URL soundURL = Pomodoro.class.getResource("/alarma1.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.addLineListener((LineEvent event) -> {
                if (event.getType() == javax.sound.sampled.LineEvent.Type.STOP) {
                    clip.close(); // ¡Libera la RAM y la tarjeta de sonido!
                }
            });

            clip.start();
        }catch(IOException | UnsupportedAudioFileException e){
            System.out.println("error : " + e.getMessage());
        }
    }
    public static void playSecondAlarm() throws LineUnavailableException{
        try{
            URL soundURL = Pomodoro.class.getResource("/Project_X.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            song = AudioSystem.getClip();
            song.open(audioIn);
            song.addLineListener((LineEvent event) -> {
                if (event.getType() == javax.sound.sampled.LineEvent.Type.STOP) {
                    // ¿El tiempo actual de la canción es igual o mayor a su duración total?
                    // Si es así, significa que terminó naturalemente. Si no, fue una pausa.
                    if (song.getMicrosecondPosition() >= song.getMicrosecondLength()) {
                        song.close(); // ¡Solo libera la RAM si de verdad terminó!
                    }
                }
             });

            song.start();
        }catch(IOException | UnsupportedAudioFileException e){

        }
    }
}