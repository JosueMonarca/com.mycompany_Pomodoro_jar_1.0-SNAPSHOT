package com.mycompany.pomodoro.view.components;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

public class CustomPanel extends javax.swing.JPanel { 

    private Color color1 = new Color(177, 110, 178); 
    private Color color2 = new Color(143, 153, 232);
    private int typeOfDegradation = 1; // 1: vertical, 2: diagonal, 3: diagonal inversa
	private int radio = 0;

    public CustomPanel() {
        setOpaque(false); // Para que el degradado se dibuje bien
    }

	public CustomPanel(Color color1, Color color2) {
		this.color1 = color1; 
		this.color2 = color2; 
		setOpaque(false); // Para que el degradado se dibuje bien
	}

	public CustomPanel(Color color1, Color color2, int typeOfDegradation) {
		this.color1 = color1; 
		this.color2 = color2; 
		this.typeOfDegradation = typeOfDegradation; 
		setOpaque(false); // Para que el degradado se dibuje bien
	}

	public CustomPanel(Color color1, Color color2, int typeOfDegradation, int radio) {
		this.color1 = color1;
		this.color2 = color2;
		this.typeOfDegradation = typeOfDegradation;
		this.radio = radio;
		setOpaque(false); // Para que el degradado se dibuje bien
	}

	public CustomPanel(int radio){
		this.radio = radio;
		setOpaque(false); // Para que el degradado se dibuje bien
	}

	public CustomPanel(int radio, int typeOfDegradation){
		this.radio = radio;
		this.typeOfDegradation = typeOfDegradation;
		setOpaque(false); // Para que el degradado se dibuje bien
	}

    @Override
    protected void paintComponent(Graphics g) { 
        super.paintComponent(g); // Siempre llamar al super

        float w = getWidth(); 
        float h = getHeight(); 
        Paint gp;

        gp = switch (typeOfDegradation) {
            case 1 -> new GradientPaint(0, 0, color1, 0, h, color2);           // Vertical (arriba a abajo)
            case 2 -> new GradientPaint(0, 0, color1, w, h, color2);            // Diagonal superior izquierda → inferior derecha
            case 3 -> new GradientPaint(w, 0, color1, 0, h, color2);            // Diagonal superior derecha → inferior izquierda
            case 4 -> new RadialGradientPaint(new Point2D.Float(w/2, h/2),
                            Math.max(w, h)/2,
                            new float[] {0f, 1f},
                            new Color[] {color2, color1});
            case 5 -> new GradientPaint(0,h/2,color1,w,h/2,color2);              // De derecha a izquierda
            default -> new GradientPaint(0, 0, color1, 0, h, color2);           // Default: vertical
        };

        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(gp);
        g2.fillRoundRect(0, 0, (int) w, (int) h, radio, radio); // Panel redondeado
    }

    // Métodos set y get
    public Color getColor1() { return color1; } 
    public void setColor1(Color color1) { this.color1 = color1; repaint(); } 

    public Color getColor2() { return color2; } 
    public void setColor2(Color color2) { this.color2 = color2; repaint(); } 

	public void setRadio(int radio) {this.radio = radio; repaint();}
	public int getRadio() {return radio;}

    public void setColor(Color color1, Color color2) { 
        this.color1 = color1; 
        this.color2 = color2;
        repaint();
    }

    public void setTypeOfDegradation(int type) { 
        this.typeOfDegradation = type; 
        repaint();
    }
}
