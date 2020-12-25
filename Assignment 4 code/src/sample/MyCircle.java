package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyCircle extends MyOval {
    // Variables
    private double r; // radius
    private Color color, rectColor; // circle's color (can be any color)

    // Methods
    public double getArea(){ return Math.PI*Math.pow(r,2); }
    public double getPerimeter(){ return 2*Math.PI*r; }
    @Override // display the circle's center point, radius, perimeter, and area
    public String toString() { return "The circle has a center point at ("+p.getX()+","+p.getY()+"), a radius of " + r + ", a perimeter of " + getPerimeter() + ", and an area of " + getArea() + "\n"; }
    @Override // draw the circle centered at (x, y) and of radius r.
    public void draw(GraphicsContext circle) {
        circle.setFill(color); // fill circle with specific color from MyColor
        double diameter = r*2; // get diameter
        circle.fillOval(p.getX()-r, p.getY()-r, diameter, diameter); // fill an oval with the dimensions of a square (this creates a circle)
    }

    // Constructor
    public MyCircle(double radius, MyPoint p, Color color, Color rectColor){
        super(p,radius, radius, color, rectColor); // set up the circle being created based on MyOval's constructor
        this.r = radius;
        this.color = color;
    }
}
