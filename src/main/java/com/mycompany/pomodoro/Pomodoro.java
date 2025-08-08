package com.mycompany.pomodoro;

import com.mycompany.pomodoro.controller.App;
import javax.swing.JOptionPane;

public class Pomodoro {
    
    public static void main(String[] args) {
        try {
            App.run();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
