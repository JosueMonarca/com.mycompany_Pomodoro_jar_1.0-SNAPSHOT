
package com.mycompany.pomodoro.model;

/**
 *
 * @author jmona
 */
public class PomodoConfigSingle {
    private static PomodoConfigSingle pcs;
    private int trabajo;
    private int descanso;
    private int repeticiones;
    
    private PomodoConfigSingle(){
        this.trabajo = 0;
        this.descanso = 0;
        this.repeticiones = 0;
    }
    
    public static PomodoConfigSingle getInstance(){
        if(pcs == null){
            pcs = new PomodoConfigSingle();
        }
        return pcs;
    }

    public int getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(int trabajo) {
        this.trabajo = trabajo;
    }

    public int getDescanso() {
        return descanso;
    }

    public void setDescanso(int descanso) {
        this.descanso = descanso;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }
    
}
