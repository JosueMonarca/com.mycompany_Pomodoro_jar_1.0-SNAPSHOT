
package com.mycompany.pomodoro.view;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class ClockCanvas extends Canvas {
    
    private int[] array;
    private int space;
    
    public ClockCanvas(){
        array = StartArray();
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }
    
    private int[] StartArray(){
        int[] newArray = new int[20];
        for( int i = 0; i < 20; i++){
            newArray[i] = (i % 5 == 0)? 40 : 20;
        }
        return newArray;
    }
    
    public void setArrayOfTicks(int[] newArray){
        this.array = newArray;
    }
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        space = (int)(this.getWidth()/array.length);
        
        for(int i = 0; i < array.length; i++){
            int grosor = (i < 10)? ((i < 9) ? 1 + i / 3 : 4):(3 - ((i - 11) / 3));
            g2.setStroke(new BasicStroke(grosor));
            int x1 = i * space;
            int y1 = (int)(this.getHeight()*.75);
            int x2 = i * space;
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
