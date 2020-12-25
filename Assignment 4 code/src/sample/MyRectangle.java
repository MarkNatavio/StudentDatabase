package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyRectangle extends MyShape{
    // Variables
    private double h, w; // rectangle's height and width
    private MyPoint p; // rectangle's top left corner point
    private Color color; // rectangle's color

    // Methods
    //a. getWidth, getHeight, getPerimeter, and getArea
    public double getWidth() { return w; } // returns the width
    public double getHeight() { return h; } // returns the height
    public double getPerimeter() { return 2*(w+h); } // returns the perimeter [P = (width+height) *2]
    public double getArea() { return w*h; } // returns area [A = base*height = width*height]

    //b. setWidth and setHeight
    public void setWidth(double width){ this.w = width; } // set the width
    public void setHeight(double height){ this.h = height; } // set the height

    //c. toString
    @Override // displays rectangle's info (top left corner point, width, height, perimeter, and area)
    public String toString() { return "This rectangle's top left corner is located at ("+p.getX()+","+p.getY()+"), it has a width of " + getWidth() + ", it has a height of " + getHeight() + "\nIt has a perimeter of " + getPerimeter() + ", and it has an area of " + getArea() + "\n"; }

    //d. draw
    @Override
    public void draw(GraphicsContext rectangle) { // draw rectangle
        rectangle.setFill(color); // set rectangle's color
        rectangle.fillRect(p.getX(),p.getY(), getWidth(), getHeight()); // draws rectangle of dimensions hxw anchored at p(x,y).
    }

    @Override // get rectangle's bounding rectangle
    public MyRectangle getMyBoundingRectangle() { return new MyRectangle(p, w, h, color); }

    @Override // get rectangle's area
    public double getMyArea() {
        return getArea();
    }

    // Constructors
    public MyRectangle(MyPoint p, double w, double h, Color color) {
        this.p = p; // set top left point to p
        setWidth(w); // set rectangle's width
        setHeight(h); // set rectangle's height
        this.color = color; // set rectangle's color
    }

    public MyRectangle(double w, double h) {
        this.p = new MyPoint(0,0); // no point, set rectangle to top left of canvas
        setWidth(w); // set rectangle's width
        setHeight(h); // set rectangle's height
        this.color = Color.SKYBLUE; // set rectangle's default color to blue
    }
}
