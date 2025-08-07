
package com.mycompany.pomodoro.controller.ControladorFlujoVista;

import java.util.function.Consumer;

import com.mycompany.pomodoro.controller.IVista;

public class ControladorDeFlujoDeUsuario {
    private Runnable detenerAnimacion;
    private Consumer<String> animationPostCanva;
    private Runnable controlarPomodoros;
    private EstadoPomodoro estadoPomodoro = EstadoPomodoro.CONFIGURAR_TRABAJO;
    private IVista vista = null;
    
    public ControladorDeFlujoDeUsuario(IVista vista){
        this.vista = vista;
    }

    public void setDetenerAnimacion(Runnable detenerAnimacion) {
        this.detenerAnimacion = detenerAnimacion;
    }

    public void setAnimationPostCanva(Consumer<String> animationPostCanva) {
        this.animationPostCanva = animationPostCanva;
    }

    public void setControlarPomodoros(Runnable controlarPomodoros) {
        this.controlarPomodoros = controlarPomodoros;
    }
    
    public void avanzar(){
        
        switch (estadoPomodoro) {
            case CONFIGURAR_TRABAJO -> {
                if(new VistaDeTiempoDeTrabajo(vista).changeView())
                    estadoPomodoro = EstadoPomodoro.CONFIGURAR_DESCANSO;
            }
            case CONFIGURAR_DESCANSO -> {
                if(new VistaDeTiempoDeDescanso(vista).changeView())
                    estadoPomodoro = EstadoPomodoro.CONFIGURAR_REPETICIONES;
            }
            case CONFIGURAR_REPETICIONES -> {
                if(new VistaDeNumeroDePomodoros(vista, detenerAnimacion).changeView())
                    estadoPomodoro = EstadoPomodoro.INICIAR_CONTEO;
            }
            case INICIAR_CONTEO -> {
                if(new IniciarConteoRegresivo(animationPostCanva,controlarPomodoros).changeView());
                estadoPomodoro = EstadoPomodoro.EN_PROGRESO;
            }
            case EN_PROGRESO -> new SaltarPomodoro(detenerAnimacion,animationPostCanva,controlarPomodoros,vista).changeView();
        }
    }
}
