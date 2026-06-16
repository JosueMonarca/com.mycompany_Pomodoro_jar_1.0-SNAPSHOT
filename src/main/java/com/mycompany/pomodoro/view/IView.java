
package com.mycompany.pomodoro.view;

import javax.swing.JButton;
import javax.swing.JLabel;

public interface IView {
    public void setTextInstrucciones(String mensaje);
    public void setTextJOptionPane(String mensaje);
    public int showMensajeOfConfirm(String mensaje);
    public void setTextButtonPrincipal(String text);
    public JButton getButtonPrincipal();
    public void hideCanva();
    public void hideButton();
    //public void showText(String text);
}
