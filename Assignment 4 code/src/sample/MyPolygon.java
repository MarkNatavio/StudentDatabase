package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class MyPolygon extends MyShape {
    // Variables
    private int N; // number of the polygon’s equal side lengths and equal interior angles
    private double r; // radius
    private Color color, rectColor; // polygon's color (can be any color)
    private MyPoint p; // polygon's center point

    // Methods
    public double getArea () { return (Math.pow(getSide(),2)*N)/(4*Math.tan(Math.PI/N)); } // get area of any regular polygon
    public double getPerimeter(){ return N*getSide(); } // get perimeter of any regular polygon
    public double getAngle(){ return (180*(N-2))/N; } // get the interior angle (in degrees) of the polygon
    public double getSide(){ return (2*r)/Math.sin(Math.PI/N); } // get the polynomial's side length
    @Override // display polygon's info (number of sides, side length, interior angle, perimeter, and area)
    public String toString() { return "The polygon has "+N+" sides each of length "+getSide()+", an interior angle of "+getAngle()+", a perimeter of "+getPerimeter()+", and an area of "+getArea()+"\n"; }
    @Override
    public void draw(GraphicsContext hexagon) { // draws a polygon centered at (x, y) and of radius r
        double[] xValues = new double[N]; // array of all x points
        double[] yValues = new double[N]; // array of all y points
        double angle = Math.toRadians(getAngle()) * N + Math.PI/2; // get the angle to go to the next value
        for(int i = 0; i < N; i++){
            xValues[i] = r*Math.cos(angle) + p.getX(); // get the x value of the point
            yValues[i] = r*Math.sin(angle) + p.getY(); // get the y value of the point
            angle += 2*(Math.PI/N); // get next angle
        }
        hexagon.setFill(color); // fill polygon with specific color from MyColor
        hexagon.fillPolygon(xValues, yValues, N); // fill polygon
    }

    @Override // get polygon's bounding rectangle
    public MyRectangle getMyBoundingRectangle() {
        MyPoint topL = new MyPoint(p.getX()-r, p.getY()-r); // set point on polygon's bounding rectangle's top left corner
        return new MyRectangle(topL, 2*r, 2*r, rectColor); // get polygon's bounding rectangle
    }

    @Override // get polygon's area
    public double getMyArea() { return getArea(); }

    // Constructor
    public MyPolygon(int N, double r, MyPoint p, Color color, Color rectColor) { // constructor to set up the polygon being created
        this.N = N; // sets polygon's number of sides
        this.r = r; // sets polygon's radius
        this.p = p; // sets polygon's center point
        this.color = color; // sets polygon's color
        this.rectColor = rectColor;
    }
}
