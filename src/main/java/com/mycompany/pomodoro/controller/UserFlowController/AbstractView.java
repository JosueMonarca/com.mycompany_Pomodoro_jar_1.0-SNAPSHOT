/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pomodoro.controller.UserFlowController;

import com.mycompany.pomodoro.view.IView;

/**
 *
 * @author jmona
 */
public abstract class AbstractView {
    protected IView view;
    
    protected String startCanvasAnimation(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
    
    public abstract boolean changeView();
}
