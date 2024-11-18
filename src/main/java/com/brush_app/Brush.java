package com.brush_app;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Brush {

    private int size = 5;
    private Color color = Color.ORANGE;
    private String colorString = "orange";
    private String shape = "circle";

    public Brush() {}

    public int getSize() {
        return this.size;
    }

    public Color getColor() {
        return this.color;
    }

    public String getColorString() {
        return this.colorString;
    }

    public String getShape() {
        return this.shape;
    }

    public void setSize(int size) {
        this.size += size;
    }

    public void setColor (Color color) {
        this.color = color;
        if (color.equals(Color.ORANGE)) this.colorString = "orange";
        if (color.equals(Color.BLUE)) this.colorString = "blue";
        if (color.equals(Color.RED)) this.colorString = "red";
        
    }

    public void setShape (String shape) {
        this.shape = shape;
    }
    
}
