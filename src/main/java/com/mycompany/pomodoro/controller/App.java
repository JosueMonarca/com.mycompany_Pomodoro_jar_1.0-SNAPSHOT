package com.mycompany.pomodoro.controller;


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
        
        // Crear SetupController principal con canvas, controller y label del time
        SetupController animator = new SetupController(canva,controller,mainFrame);
        
        // Configurar el flujo de pantallas y las acciones de usuario
        UserFlowController flowController = new UserFlowController( mainFrame, mainFrame );
        PomodoroEngine pomEmgi = new PomodoroEngine(mainFrame);
        flowController.setAnimationPostCanva((String time) -> {
            pomEmgi.animationPostCanva(time);
        });
        flowController.sethandlePomodoros(() -> {
            pomEmgi.handlePomodoros();
        });
        flowController.setstopAnimation(() -> {
            animator.stopAnimation();
        });
        mainFrame.setControlador(flowController);
        animator.startCanvasAnimation();//<- Iniciar animaciones y ejecución principal
    }
}