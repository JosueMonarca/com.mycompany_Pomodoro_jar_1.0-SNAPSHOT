package com.mycompany.pomodoro.controller;

import com.mycompany.pomodoro.view.ClockCanvas;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputListener;

public class ClockController implements MouseInputListener {
    ClockCanvas Canva = new ClockCanvas();
    private int firstPointX; 
    private int positionX;
    int ticks = 0;
    
    public ClockController(ClockCanvas rj) {
        Canva = rj;
    }
    
    public int getfirstPointX() {
        return firstPointX;
    }

    public int getPosicionX() {
        return positionX;
    }
    public int getTicks() {
        return ticks;
    }

    public void setfirstPointX(int firstPointX) {
        this.firstPointX = firstPointX;
    }

    public void setPosicionX(int positionX) {
        this.positionX = positionX;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }
    
    @Override public void mouseClicked(MouseEvent e) {
        //Canva.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    }
    
    @Override public void mousePressed(MouseEvent e) {
        Canva.setCursor(new Cursor(Cursor.HAND_CURSOR));    
        firstPointX = e.getX();
        //System.out.println(firstPointX);
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
        if(firstPointX > e.getX()){
            ticks --;
            //System.out.println(ticks);
        }else{
            ticks ++;
          //  System.out.println(ticks);
        }
        this.positionX =e.getX();
    }
    
    @Override public void mouseMoved(MouseEvent e) {}
}
