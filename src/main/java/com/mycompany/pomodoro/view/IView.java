/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pomodoro.view;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author jmona
 */
public interface IView {
    public void setTextLabelReloj(String mensaje);
    public void setTextInstrucciones(String mensaje);
    public void setTextJOptionPane(String mensaje);
    public int showMensajeOfConfirm(String mensaje);
    public void setTextButtonPrincipal(String text);
    public JButton getButtonPrincipal();
    public JLabel getLabelClock();
    public void hideCanva();
    public void hideButton();
}
