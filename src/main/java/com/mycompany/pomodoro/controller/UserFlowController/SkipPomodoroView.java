
package com.mycompany.pomodoro.controller.UserFlowController;

import java.util.function.Consumer;

import javax.swing.JOptionPane;

import com.mycompany.pomodoro.model.PomodoroConfig;
import com.mycompany.pomodoro.view.IView;

public class SkipPomodoroView extends AbstractView {
    
    private final Runnable stopAnimation;
    private final Consumer<String> animationPostCanva;
    private final Runnable handlePomodoros;
    
    public SkipPomodoroView (Runnable stopAnimation,Consumer<String> animationPostCanva,Runnable handlePomodoros,IView viewFrame){
        this.stopAnimation = stopAnimation;
        this.animationPostCanva = animationPostCanva;
        this.handlePomodoros = handlePomodoros;
        this.view = viewFrame;
    }

    @Override
    public boolean changeView() {
        String text = view.getLabelClock().getText();

        // Si el reloj ya terminó, no cambiar de view
        if (text.equalsIgnoreCase("00:00:00")) {
            return false;
        }

        int answer = view.showMensajeOfConfirm("¿Desea saltar la sesión actual?");

        switch (answer) {
            case JOptionPane.NO_OPTION, JOptionPane.CANCEL_OPTION -> {
                return false;
            }
            case JOptionPane.YES_OPTION -> {
                this.skipPomodoroView();
                return true;
            }
            default -> {
                return false;
            }
        }
    }
    
    private void skipPomodoroView() {
        // Detener animación actual si existe
        if (stopAnimation != null) {
            stopAnimation.run();
        }

        // Obtener configuración actual
        PomodoroConfig config = PomodoroConfig.getInstance();

        // Reducir el número de repetitions pendientes
        int repetitions = config.getRepetitions();
        if (repetitions >= 1) {
            config.setRepetitions(repetitions - 1);

            // Iniciar siguiente descanso o pomodoro
            int time = config.getJob(); // O config.getRest() si estás en descanso
            String newTime = startCanvasAnimation(time);
        
            if (animationPostCanva != null) {
                animationPostCanva.accept(newTime);
            }

            if (handlePomodoros != null) {
                handlePomodoros.run();
            }
        } else {
            // Ya no quedan más pomodoros → Terminar ciclo
            view.setTextJOptionPane("Todos los pomodoros han terminado.");
            view.setTextLabelReloj("00:00:00");
            view.setTextInstrucciones("Has terminado");
            view.hideButton();
        }
    }
    
}
