package com.mycompany.pomodoro.controller;

import javax.swing.JLabel;

import com.mycompany.pomodoro.controller.UserFlowController.UserFlowController;
import com.mycompany.pomodoro.view.ClockCanvas;
import com.mycompany.pomodoro.view.MainFrame;

public class App {
    
    public static void run() throws Exception{
        //Inicializar y acomadar el frame
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        
        // Obtener el canvas y vincularlo a su controller
        ClockCanvas canva = mainFrame.getClockCanvas();
        ClockController controller = new ClockController(canva);
        canva.addMouseListener(controller);
        canva.addMouseMotionListener(controller);
        
        // Crear Animator principal con canvas, controller y label del time
        JLabel labelMain = mainFrame.getLabelClock();
        Animator animator = new Animator(canva,controller,labelMain);
        
        // Configurar el flujo de pantallas y las acciones de usuario
        UserFlowController controllerDeFlujo = new UserFlowController( mainFrame );
        controllerDeFlujo.setAnimationPostCanva(time -> animator.animationPostCanva(time));
        controllerDeFlujo.sethandlePomodoros(animator::handlePomodoros);
        controllerDeFlujo.setstopAnimation(animator::stopAnimation);
        mainFrame.setControlador(controllerDeFlujo);
        animator.startCanvasAnimation();//<- Iniciar animaciones y ejecución principal
    }
}