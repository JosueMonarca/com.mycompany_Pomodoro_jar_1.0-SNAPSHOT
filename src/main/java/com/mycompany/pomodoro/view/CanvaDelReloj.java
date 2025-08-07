
package com.mycompany.pomodoro.view;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class CanvaDelReloj extends Canvas {
    
    private int[] array;
    private int espacio;
    
    public CanvaDelReloj(){
        array = IniciarArray();
    }

    public int getEspacio() {
        return espacio;
    }

    public void setEspacio(int espacio) {
        this.espacio = espacio;
    }
    
    private int[] IniciarArray(){
        int[] nuevoArray = new int[20];
        for( int i = 0; i < 20; i++){
            nuevoArray[i] = (i % 5 == 0)? 40 : 20;
        }
        return nuevoArray;
    }
    
    public void setArrayOfTicks(int[] nuevoArray){
        this.array = nuevoArray;
    }
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        espacio = (int)(this.getWidth()/array.length);
        
        for(int i = 0; i < array.length; i++){
            int grosor = (i < 10)? ((i < 9) ? 1 + i / 3 : 4):(3 - ((i - 11) / 3));
            g2.setStroke(new BasicStroke(grosor));
            int x1 = i * espacio;
            int y1 = (int)(this.getHeight()*.75);
            int x2 = i * espacio;
            int y2 = (int)(this.getHeight()*.75) - array[i];
            g2.drawLine(x1,y1,x2,y2);
        }
    }
    
    public void animationLeft(){
        for(int i = 0; i < array.length; i++ ){
            if(array[i] == 40 && i != 0){
                array[i] = 20;
                array[i-1] = 40;
                if ((i + 5) < array.length) {
                    array[i + 5] = 40;
                }
            }  else if( i == 0 && array[0] == 40){
                array[0] = 20;
            }
        }
        repaint();
    }
    
    public void animationRight(){
        for (int i = array.length - 1; i >= 0; i-- ){
            if (array[i] == 40 && i != array.length - 1) {
                array[i] = 20;
                array[i+1] = 40;
                if ((i - 5) >= 0) {
                    array[i - 5] = 40;
                }
            } else if (i == array.length - 1 && array[array.length - 1] == 40) {
                array[array.length - 1] = 20;
            }
        }
        repaint();
    }
}
