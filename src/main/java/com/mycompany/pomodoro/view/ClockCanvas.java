
package com.mycompany.pomodoro.view;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class ClockCanvas extends Canvas {
    
    private int[] tickHeights;
    private int space;
    
    public ClockCanvas(){
        tickHeights = initTicks();
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }
    
    private int[] initTicks(){
        int[] newArray = new int[20];
        for( int i = 0; i < 20; i++){
            newArray[i] = (i % 5 == 0)? 40 : 20;
        }
        return newArray;
    }
    
    public void setArrayOfTicks(int[] newArray){
        this.tickHeights = newArray;
    }
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.WHITE);
        space = (int)(this.getWidth()/tickHeights.length);
        
        for(int i = 0; i < tickHeights.length; i++){
            int thickness = (i < 10)? ((i < 9) ? 1 + i / 3 : 4):(3 - ((i - 11) / 3));
            g2.setStroke(new BasicStroke(thickness));
            int x1 = i * space;
            int y1 = (int)(this.getHeight()*.75);
            int x2 = i * space;
            int y2 = (int)(this.getHeight()*.75) - tickHeights[i];
            g2.drawLine(x1,y1,x2,y2);
        }
    }
    
    public void animationLeft(){
        for(int i = 0; i < tickHeights.length; i++ ){
            if(tickHeights[i] == 40 && i != 0){
                tickHeights[i] = 20;
                tickHeights[i-1] = 40;
                if ((i + 5) < tickHeights.length) {
                    tickHeights[i + 5] = 40;
                }
            }  else if( i == 0 && tickHeights[0] == 40){
                tickHeights[0] = 20;
            }
        }
        repaint();
    }
    
    public void animationRight(){
        for (int i = tickHeights.length - 1; i >= 0; i-- ){
            if (tickHeights[i] == 40 && i != tickHeights.length - 1) {
                tickHeights[i] = 20;
                tickHeights[i+1] = 40;
                if ((i - 5) >= 0) {
                    tickHeights[i - 5] = 40;
                }
            } else if (i == tickHeights.length - 1 && tickHeights[tickHeights.length - 1] == 40) {
                tickHeights[tickHeights.length - 1] = 20;
            }
        }
        repaint();
    }
}
