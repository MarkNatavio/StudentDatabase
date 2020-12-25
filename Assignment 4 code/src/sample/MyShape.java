package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class MyShape implements MyShapeInterface{
    // Variables
    private Color color; // shape's color
    private MyPoint p; // MyPoint object p

    // Methods
    public void setP(MyPoint p) { this.p = p; } // setting the x and y values of point p
    public void setColor(Color colorVal) { color = colorVal; } // setting the value of the shape color
    public MyPoint getP() { return p; } // get the coordinates of point p
    public Color getColor() { return color; } // getting the color
    @Override // Display the shape's size and color
    public String toString() { return "This space has a width of " + p.getX() + ", a height of " + p.getY() + ", and is of color " + color; }
    public abstract void draw(GraphicsContext shape); // empty draw method
}
