
package com.mycompany.pomodoro.controller;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.mycompany.pomodoro.Pomodoro;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;

public class SoundController {
    public static void playFirstAlarm() throws LineUnavailableException{
        try{
            URL soundURL = Pomodoro.class.getResource("/alarma1.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
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
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.addLineListener((LineEvent event) -> {
                if (event.getType() == javax.sound.sampled.LineEvent.Type.STOP) {
                    clip.close(); // ¡Libera la RAM y la tarjeta de sonido!
                }
             });

            clip.start();
        }catch(IOException | UnsupportedAudioFileException e){

        }
    }
}