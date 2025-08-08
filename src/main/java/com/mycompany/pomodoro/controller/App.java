
package com.mycompany.pomodoro.controller;

import com.mycompany.pomodoro.controller.UserFlowController.UserFlowController;
import com.mycompany.pomodoro.view.ClockCanvas;
import com.mycompany.pomodoro.view.MainFrame;
import javax.swing.JLabel;

public class App {
    
    public static void run() throws Exception{
        //Inicializar y acomadar el frame
        MainFrame frameDeInicio = new MainFrame();
        frameDeInicio.setVisible(true);
        frameDeInicio.setLocationRelativeTo(null);
        frameDeInicio.setResizable(false);
        
        // Obtener el canvas y vincularlo a su controlador
        ClockCanvas canva = frameDeInicio.getClockCanvas();
        ClockController controlador = new ClockController(canva);
        canva.addMouseListener(controlador);
        canva.addMouseMotionListener(controlador);
        
        // Crear Animator principal con canvas, controlador y label del time
        JLabel labelPrincipal = frameDeInicio.getLabelClock();
        Animator Animator = new Animator(canva,controlador,labelPrincipal);
        
        
        // Configurar el flujo de pantallas y las acciones de usuario
        UserFlowController controladorDeFlujo = new UserFlowController( frameDeInicio );
        controladorDeFlujo.setAnimationPostCanva(time -> Animator.animationPostCanva(time));
        controladorDeFlujo.sethandlePomodoros(Animator::handlePomodoros);
        controladorDeFlujo.setstopAnimation(Animator::stopAnimation);
        frameDeInicio.setControlador(controladorDeFlujo);
        Animator.startCanvasAnimation();//<- Iniciar animaciones y ejecución principal
    }
}
