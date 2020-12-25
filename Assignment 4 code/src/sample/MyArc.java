package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class MyArc extends MyShape{
    private MyPoint center; // center point
    private double startingAngle, length, rx, ry; // starting angle, inner angle, radius of x, radius of y
    private Color color; // arc color

    //a. toString — returns a string representation of the MyArc object;
    @Override
    public String toString() { return "This arc has a center at ("+center.getX()+","+center.getY()+"), a x radius of " + rx +", a y radius of " + ry + ",\nan angle of " + length + ", an area of " + getMyArea() + ", a color of " + color; }

    //b. draw — draws a MyArc object.
    @Override
    public void draw(GraphicsContext arc) {
        arc.setFill(color);
        arc.fillArc(center.getX()-rx,center.getY()-ry,rx*2,ry*2,startingAngle,length,ArcType.ROUND);
    }

    @Override // gets the area of the arc [PI*(length/360)*r^2]
    public double getMyArea() { return rx*ry*Math.PI*length/360; }
    @Override // get bounding rectangle
    public MyRectangle getMyBoundingRectangle() {
        double start = startingAngle * (Math.PI / 180);
        double end = (startingAngle + length) * (Math.PI / 180);
        MyPoint p1 = new MyPoint(center.getX() + rx * Math.cos(start), center.getY() + ry * Math.sin(start)); // get first endpoint
        MyPoint p2 = new MyPoint(center.getX() + rx * Math.cos(end), center.getY() - ry * Math.sin(end)); // get second endpoint

        // Getting top left anchoring point
        double x = 0, y = 0;
        // Getting x
        if (startingAngle <= 180 && startingAngle + length > 180) x = center.getX() - rx; // left most corner side
        else if (startingAngle + length == 180 && startingAngle == 0)
            x = center.getX() - rx; // so values don't get confused
        else { // get minimum x value in arc
            x = (p2.getX() <= p1.getX()) ? p2.getX() : p1.getX();
            x = (center.getX() <= x) ? center.getX() : x;
        }
        // Getting y
        if (startingAngle <= 90 && startingAngle + length > 90) y = center.getY() - ry; // top most corner
        else if (p2.getY() == center.getY()) y = p1.getY() - 2 * (p1.getY() - p2.getY());
        else {
            y = (p2.getY() <= p1.getY()) ? p2.getY() : p1.getY();
            y = (center.getY() <= y) ? center.getY() : y;
        }
        if (startingAngle >= 180) y = center.getY(); // making sure y isn't too low
        MyPoint topLeft = new MyPoint(x, y);

        // Getting W & H
        double w = 0, h = 0, h2 = 0;
        // Getting W
        if (startingAngle == 0 || startingAngle == 360) w = center.getX() + rx;
        else if (startingAngle + length == 360) w = center.getX() + rx;
        else {
            w = (p2.getX() < p1.getX()) ? p1.getX() : p2.getX();
            w = (center.getX() <= w) ? w : center.getX();
        }
        w -= x;

        // Getting H
        if (startingAngle < 270 && startingAngle+length > 270) h = center.getY() + ry;
        else {
            h = (p2.getY() < p1.getY()) ? p1.getY() : p2.getY();
            h = (center.getY() < h) ? h : center.getY();
        }
        // making sure that h is not too high or too low
        if (startingAngle < 180 && h > ry) h = center.getY()-topLeft.getY();
        else if (startingAngle >= 180 && h > ry) h = ry;

        return new MyRectangle(topLeft, w, h, Color.BLACK);
    }

    public MyArc(MyPoint center, double startingAngle, double length, double rx, double ry, Color color){
        this.center = center;
        this.rx = rx;
        this.ry = ry;
        this.startingAngle = startingAngle;
        this.length = length;
        this.color = color;
    }
}
