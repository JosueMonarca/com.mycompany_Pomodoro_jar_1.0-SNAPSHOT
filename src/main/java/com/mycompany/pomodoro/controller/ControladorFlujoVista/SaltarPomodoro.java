
package com.mycompany.pomodoro.controller.ControladorFlujoVista;

import java.util.function.Consumer;

import javax.swing.JOptionPane;

import com.mycompany.pomodoro.controller.IVista;
import com.mycompany.pomodoro.model.PomodoConfigSingle;

public class SaltarPomodoro extends AbstractVista {
    
    private final Runnable detenerAnimacion;
    private final Consumer<String> animationPostCanva;
    private final Runnable controlarPomodoros;
    
    public SaltarPomodoro (Runnable detenerAnimacion,Consumer<String> animationPostCanva,Runnable controlarPomodoros,IVista vistaFrame){
        this.detenerAnimacion = detenerAnimacion;
        this.animationPostCanva = animationPostCanva;
        this.controlarPomodoros = controlarPomodoros;
        this.vista = vistaFrame;
    }

    @Override
    public boolean changeView() {
        String text = vista.getLabelReloj().getText();

        // Si el reloj ya terminó, no cambiar de vista
        if (text.equalsIgnoreCase("00:00:00")) {
            return false;
        }

        int respuesta = vista.showMensajeOfConfirm("¿Desea saltar la sesión actual?");

        switch (respuesta) {
            case JOptionPane.NO_OPTION, JOptionPane.CANCEL_OPTION -> {
                return false;
            }
            case JOptionPane.YES_OPTION -> {
                this.saltarPomodoro();
                return true;
            }
            default -> {
                return false;
            }
        }
    }
    
    private void saltarPomodoro() {
        // Detener animación actual si existe
        if (detenerAnimacion != null) {
            detenerAnimacion.run();
        }

        // Obtener configuración actual
        PomodoConfigSingle config = PomodoConfigSingle.getInstance();

        // Reducir el número de repeticiones pendientes
        int repeticiones = config.getRepeticiones();
        if (repeticiones >= 1) {
            config.setRepeticiones(repeticiones - 1);

            // Iniciar siguiente descanso o pomodoro
            int tiempo = config.getTrabajo(); // O config.getDescanso() si estás en descanso
            String nuevoTiempo = convertirASegundosAFormato(tiempo);
        
            if (animationPostCanva != null) {
                animationPostCanva.accept(nuevoTiempo);
            }

            if (controlarPomodoros != null) {
                controlarPomodoros.run();
            }
        } else {
            // Ya no quedan más pomodoros → Terminar ciclo
            vista.setTextJOptionPane("Todos los pomodoros han terminado.");
            vista.setTextLabelReloj("00:00:00");
            vista.setTextInstrucciones("Has terminado");
            vista.hideButton();
        }
    }
    
}
