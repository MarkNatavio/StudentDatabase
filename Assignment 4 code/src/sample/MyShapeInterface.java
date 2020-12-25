package sample;

import javafx.scene.paint.Color;

public interface MyShapeInterface {
    //a. getMyBoundingRectangle
    public MyRectangle getMyBoundingRectangle(); // set empty method to get the shape's bounding rectangle

    //b. getMyArea
    public double getMyArea(); // set empty method to get shape's area

    //c. overlapMyShapes
    public static MyRectangle overlapMyShapes(MyShape shape1, MyShape shape2) { // get intersecting area of two overlapping shapes
        if (shape1 instanceof MyLine || shape2 instanceof MyLine) return null; // if 1 shape is a line, return null (no area)
        MyRectangle rect1 = shape1.getMyBoundingRectangle(); // get bounding rectangle of first shape
        MyRectangle rect2 = shape2.getMyBoundingRectangle(); // get bounding rectangle of second shape

        // Get values of the first rectangle
        double x1 = rect1.getP().getX(); // get first rectangle's top left point x value
        double y1 = rect1.getP().getY(); // get first rectangle's top left point y value
        double w1 = rect1.getWidth(); // get first rectangle's width
        double h1 = rect1.getHeight(); // get first rectangle's height

        double x2 = rect2.getP().getX(); // get second rectangle's top left point x value
        double y2 = rect2.getP().getY(); // get second rectangle's top left point y value
        double w2 = rect2.getWidth(); // get second rectangle's width
        double h2 = rect2.getHeight(); // get second rectangle's height

        if (y1 + h1 < y2 || y2 + h2 < y1) return null; // rectangles are above each other, do not overlap
        if (x1 + w1 < x2 || x2 + w2 < x1) return null; // rectangles are next to each other, do not overlap

        double maxX = Math.max(x1,x2); // get max x value between shapes
        double maxY = Math.max(y1,y2); // get max y value between shapes
        double minX = Math.min(x1+w1,x2+w2); // get min x value between shapes
        double minY = Math.min(y1+h1,y2+h2); // get min y value between shapes

        double x = Math.abs(minX-maxX); // get overlapping area's width
        double y = Math.abs(minY-maxY); // get overlapping area's height
        return new MyRectangle(new MyPoint(maxX, maxY), x, y, Color.BLACK); // return intersecting shape
    }
}
