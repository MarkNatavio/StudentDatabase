package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyLine extends MyShape{ // inherits class MyShape
    // Variables
    private MyPoint sp,ep; // starting point (sp) and end point (ep)
    private Color color; // Line's color (can be any color)

    // Methods
    public double getLength(){ return Math.sqrt(Math.pow((ep.getX()- sp.getX()),2) + Math.pow((ep.getY()- sp.getY()),2)); } // Get the line's length
    public double get_xAngle(){ return Math.toDegrees(Math.atan2((ep.getY()- sp.getY()),(ep.getX()- sp.getX()))); } // Get the line's angle with the x-axis
    @Override // display line's info (starting point, end point, length, angle to the x-axis)
    public String toString() { return "The line's endpoints are ("+sp.getX()+","+sp.getY()+") and ("+ep.getX()+","+ep.getY()+"). The line's length is " + getLength() + ". The angle of the line with the x-axis is " + get_xAngle(); }
    @Override
    public void draw(GraphicsContext line){ // draw the line
        line.setStroke(color); // set line color
        line.setLineWidth(4); // set line width
        line.strokeLine(sp.getX(), sp.getY(), ep.getX(), ep.getY()); // set line starting and ending points
    }

    @Override // get line's bounding rectangle
    public MyRectangle getMyBoundingRectangle() { return new MyRectangle(sp, Math.abs(ep.getX()-sp.getX()), Math.abs(ep.getY()-sp.getY()), color); }

    @Override // get line's area (0)
    public double getMyArea() { return 0; }

    // Constructor
    public MyLine(MyPoint sp, MyPoint ep, Color color){ // set line constructor
        this.sp = sp; // set starting point
        this.ep = ep; // set end point
        this.color = color; // set line color
    }
}

