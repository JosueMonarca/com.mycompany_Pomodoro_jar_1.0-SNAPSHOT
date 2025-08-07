
package com.mycompany.pomodoro.controller;

import com.mycompany.pomodoro.controller.ControladorFlujoVista.ControladorDeFlujoDeUsuario;
import com.mycompany.pomodoro.view.CanvaDelReloj;
import com.mycompany.pomodoro.view.FrameInicial;
import javax.swing.JLabel;

public class App {
    
    public static void run() throws Exception{
        //Inicializar y acomadar el frame
        FrameInicial frameDeInicio = new FrameInicial();
        frameDeInicio.setVisible(true);
        frameDeInicio.setLocationRelativeTo(null);
        frameDeInicio.setResizable(false);
        
        // Obtener el canvas y vincularlo a su controlador
        CanvaDelReloj canva = frameDeInicio.getCanvaDelReloj();
        controladorReloj controlador = new controladorReloj(canva);
        canva.addMouseListener(controlador);
        canva.addMouseMotionListener(controlador);
        
        // Crear animador principal con canvas, controlador y label del tiempo
        JLabel labelPrincipal = frameDeInicio.getLabelReloj();
        Animador animador = new Animador(canva,controlador,labelPrincipal);
        
        
        // Configurar el flujo de pantallas y las acciones de usuario
        ControladorDeFlujoDeUsuario controladorDeFlujo = new ControladorDeFlujoDeUsuario( frameDeInicio );
        controladorDeFlujo.setAnimationPostCanva(tiempo -> animador.animationPostCanva(tiempo));
        controladorDeFlujo.setControlarPomodoros(animador::controlarPomodoros);
        controladorDeFlujo.setDetenerAnimacion(animador::detenerAnimacion);
        frameDeInicio.setControlador(controladorDeFlujo);
        animador.animationOfCanva();//<- Iniciar animaciones y ejecución principal
    }
}
