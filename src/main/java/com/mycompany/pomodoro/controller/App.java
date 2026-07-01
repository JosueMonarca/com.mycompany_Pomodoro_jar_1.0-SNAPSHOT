package com.mycompany.pomodoro.controller;
import com.mycompany.pomodoro.controller.UserFlowController.UserFlowController;
import com.mycompany.pomodoro.view.ClockCanvas;
import com.mycompany.pomodoro.view.MainFrame;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class App {
    private MainFrame mainFrame;
    
    public void run() throws Exception{
        //Inicializar y acomadar el frame
        mainFrame = new MainFrame();
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
        PomodoroEngine pomEmgi = new PomodoroEngine(mainFrame,mainFrame);
        flowController.setStartSession(() -> {
            pomEmgi.startSession();
            this.stopListenerOfArrows();
        });
        flowController.setstopAnimation(() -> {
            animator.stopAnimation();
        });
        flowController.setSkipSession(() -> {
            pomEmgi.skipPhase();
        });
        mainFrame.setControlador(flowController);
        
        this.assingIcon();
        this.startListenerOfArrows(animator);
        
        animator.startCanvasAnimation();
    }
    
    private  void assingIcon(){
        URL urlIcon = getClass().getClassLoader().getResource("IconoPomodoro.png");
        
        if(urlIcon != null){
            ImageIcon icono = new ImageIcon(urlIcon);
            this.mainFrame.setIconImage(icono.getImage());
        }
    }
    
    private void startListenerOfArrows(SetupController animator) {
        InputMap inputMap = mainFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = mainFrame.getRootPane().getActionMap();

        // --- CONFIGURACIÓN PARA MOVER A LA IZQUIERDA ---
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "ArrowLeft");
        inputMap.put(KeyStroke.getKeyStroke("A"), "ArrowLeft"); // Se suma la letra A
        
        actionMap.put("ArrowLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 animator.ajustDial(Direction.LEFT);
            }
        });
        
        // --- CONFIGURACIÓN PARA MOVER A LA DERECHA ---
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "ArrowRight");
        inputMap.put(KeyStroke.getKeyStroke("D"), "ArrowRight"); // Se suma la letra D
        
        actionMap.put("ArrowRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 animator.ajustDial(Direction.RIGHT);
            }
        });
    }
    
    private void stopListenerOfArrows() {
        InputMap inputMap = mainFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = mainFrame.getRootPane().getActionMap();

        // 1. Desvinculamos las teclas físicas del menú (InputMap)
        inputMap.remove(KeyStroke.getKeyStroke("LEFT"));
        inputMap.remove(KeyStroke.getKeyStroke("A"));
        inputMap.remove(KeyStroke.getKeyStroke("RIGHT"));
        inputMap.remove(KeyStroke.getKeyStroke("D"));

        // 2. Borramos las acciones del diccionario lógico (ActionMap)
        // Esto es una excelente práctica para que el Garbage Collector libere la memoria RAM
        actionMap.remove("ArrowLeft");
        actionMap.remove("ArrowRight");
    }
}