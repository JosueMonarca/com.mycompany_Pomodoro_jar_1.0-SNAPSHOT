
package com.mycompany.pomodoro.view;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.mycompany.pomodoro.controller.UserFlowController.UserFlowController;

public class MainFrame extends javax.swing.JFrame implements IView{

    public MainFrame() {
        initComponents();
    }
    
    public MainFrame(UserFlowController controller){
        jButton1.addActionListener(e -> controller.next());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipal = new com.mycompany.pomodoro.view.componentes.CustomPanel();
        CustomPanel2 = new com.mycompany.pomodoro.view.componentes.CustomPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        labelDeReloj = new javax.swing.JLabel();
        ClockCanvas1 = new com.mycompany.pomodoro.view.ClockCanvas();
        instrucciones = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelPrincipal.setColor1(new java.awt.Color(231, 37, 85));
        PanelPrincipal.setColor2(new java.awt.Color(51, 255, 102));

        CustomPanel2.setColor1(new java.awt.Color(231, 37, 85));
        CustomPanel2.setColor2(new java.awt.Color(231, 37, 85));
        CustomPanel2.setTypeOfDegradation(3);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\jmona\\OneDrive\\Documentos\\NetBeansProjects\\com.mycompany_Pomodoro_jar_1.0-SNAPSHOT\\src\\main\\java\\com\\mycompany\\pomodoro\\view\\images\\IconoDeReloj.png")); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(220, 212, 212));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Pomodoro");

        jButton1.setBackground(new java.awt.Color(231, 37, 85));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        jButton1.setForeground(new java.awt.Color(220, 212, 212));
        jButton1.setText("Siguiente");
        jButton1.setToolTipText("");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(true);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.setHideActionText(true);

        javax.swing.GroupLayout CustomPanel2Layout = new javax.swing.GroupLayout(CustomPanel2);
        CustomPanel2.setLayout(CustomPanel2Layout);
        CustomPanel2Layout.setHorizontalGroup(
            CustomPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        CustomPanel2Layout.setVerticalGroup(
            CustomPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CustomPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(CustomPanel2Layout.createSequentialGroup()
                        .addGroup(CustomPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        labelDeReloj.setFont(new java.awt.Font("Monospaced", Font.PLAIN, 100));
        labelDeReloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDeReloj.setText("00:00:00");

        ClockCanvas1.setBackground(PanelPrincipal.getColor2());

        instrucciones.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        instrucciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        instrucciones.setText("Ingrese la duracion del pomodoro");

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CustomPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(instrucciones, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                    .addComponent(labelDeReloj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ClockCanvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 43, Short.MAX_VALUE))
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalLayout.createSequentialGroup()
                .addComponent(CustomPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(instrucciones, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelDeReloj, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(ClockCanvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public ClockCanvas getClockCanvas(){
        return ClockCanvas1;
    }
    
    @Override
    public JLabel getLabelClock(){
        return labelDeReloj;
    }

    @Override
    public JButton getButtonPrincipal(){
        return jButton1;
    }

    public void setControlador(UserFlowController controller) {
        jButton1.addActionListener(e -> controller.next());
    }
    
    /**
     *
     * @param mensaje
     */
    @Override
    public void setTextLabelReloj(String mensaje){
        labelDeReloj.setText(mensaje);
    }
    
    @Override
    public void setTextInstrucciones(String mensaje){
        instrucciones.setText(mensaje);
    }
    
    @Override
    public void setTextJOptionPane(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }

    @Override
    public int showMensajeOfConfirm(String mensaje) {
        return  JOptionPane.showConfirmDialog(
                null, 
                mensaje, 
                "Confirmación",
                JOptionPane.YES_NO_CANCEL_OPTION
            );   
    }
    
    @Override
    public void setTextButtonPrincipal(String text){
        jButton1.setText(text);
    }
    
    @Override
    public void hideCanva() {
        ClockCanvas1.setVisible(false);
    }
    
    @Override
    public void hideButton(){
        jButton1.setEnabled(false);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.pomodoro.view.componentes.CustomPanel PanelPrincipal;
    private com.mycompany.pomodoro.view.ClockCanvas ClockCanvas1;
    private javax.swing.JLabel instrucciones;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labelDeReloj;
    private com.mycompany.pomodoro.view.componentes.CustomPanel CustomPanel2;
    // End of variables declaration//GEN-END:variables
    
}
