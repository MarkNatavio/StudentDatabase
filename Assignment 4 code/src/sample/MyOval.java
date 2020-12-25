package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyOval extends MyShape {
    // Variables
    MyPoint p, center; // oval's top left corner point
    double w, h;
    private Color color, rectColor; // oval's color

    // Methods
    // a. getPerimeter, getArea, getCenter
    public double getPerimeter(){ return 2 * Math.PI * Math.sqrt((Math.pow(w/2,2) + Math.pow(h/2,2))/2); } // returns oval's perimeter [P=2*PI*√(width^2 + height^2)/2]
    public double getArea(){ return Math.PI * w/2 * h/2; } // returns oval's area [A=PI*(width/2)*(height/2)]
    public MyPoint getCenter() { return center; } // return's oval's center point

    //b. setAxes, setCenter
    public void setAxes(double width, double height) {
        this.w = width; // sets axes width
        this.h = height; // sets axes height
    }
    public void setCenter(MyPoint center) { this.center = center; } // sets center point

    //c. toString
    @Override // display oval's info (center point, axes lengths, perimeter, and area)
    public String toString() { return "This Oval's has a center point at ("+getCenter().getX()+","+getCenter().getY()+"), a width of " + w + ", a height of " + h + "\nA perimeter of " + getPerimeter() + ", and an area of " + getArea() + "\n"; }

    //d. draw
    @Override
    public void draw(GraphicsContext oval) { // draw oval
        oval.setFill(color); // set oval's color
        oval.fillOval(p.getX(), p.getY(), w, h); // draw oval
    }

    @Override // get oval's bounding rectangle
    public MyRectangle getMyBoundingRectangle() { return new MyRectangle(p, w, h, rectColor); }

    @Override // get oval's area
    public double getMyArea() { return getArea(); }

    // Constructor
    public MyOval (MyPoint p, double width, double height, Color color, Color rectColor){
        this.p = p; // set top left point p as point p
        setAxes(width, height); // set axes of the bounding rectangle
        setCenter(new MyPoint(p.getX()+width/2, p.getY()+height/2)); // set center point
        this.color = color; // set color
        this.rectColor = rectColor;
    }
}
