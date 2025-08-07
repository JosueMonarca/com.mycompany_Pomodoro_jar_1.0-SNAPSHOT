                             
package com.mycompany.pomodoro.controller;

import com.mycompany.pomodoro.view.CanvaDelReloj;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

public class controladorReloj implements MouseInputListener {
    CanvaDelReloj Canva = new CanvaDelReloj();
    private int PrimerPuntox; 
    private int posicionX;
    int ticks = 0;
    
    public controladorReloj(CanvaDelReloj rj) {
        Canva = rj;
    }
    
    public int getPrimerPuntox() {
        return PrimerPuntox;
    }

    public int getPosicionX() {
        return posicionX;
    }
    public int getTicks() {
        return ticks;
    }

    public void setPrimerPuntox(int PrimerPuntox) {
        this.PrimerPuntox = PrimerPuntox;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }
    
    @Override public void mouseClicked(MouseEvent e) {
        //Canva.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }
    
    @Override public void mousePressed(MouseEvent e) {
        Canva.setCursor(new Cursor(Cursor.HAND_CURSOR));    
        PrimerPuntox = e.getX();
        //System.out.println(PrimerPuntox);
    }
    
    @Override public void mouseReleased(MouseEvent e) {
        Canva.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
    @Override public void mouseEntered(MouseEvent e) {
        //Canva.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    @Override public void mouseExited(MouseEvent e) {}
    
    @Override 
    public void mouseDragged(MouseEvent e) {
        if(PrimerPuntox > e.getX()){
            ticks --;
            //System.out.println(ticks);
        }else{
            ticks ++;
          //  System.out.println(ticks);
        }
        this.posicionX =e.getX();
    }
    
    @Override public void mouseMoved(MouseEvent e) {}
 
}
