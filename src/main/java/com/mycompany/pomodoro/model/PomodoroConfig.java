
package com.mycompany.pomodoro.model;

/**
 *
 * @author jmona
 */
public class PomodoroConfig {
    private static PomodoroConfig pcs;
    private int trabajo;
    private int descanso;
    private int repetitions;
    
    private PomodoroConfig(){
        this.trabajo = 0;
        this.descanso = 0;
        this.repetitions = 0;
    }
    
    public static PomodoroConfig getInstance(){
        if(pcs == null){
            pcs = new PomodoroConfig();
        }
        return pcs;
    }

    public int getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(int trabajo) {
        this.trabajo = trabajo;
    }

    public int getRest() {
        return descanso;
    }

    public void setRest(int descanso) {
        this.descanso = descanso;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }
    
}
