package Shapes;

import java.awt.*;

public class Triangle extends ShapeImp {

    private Point firstPoint;
    private Point secondPoint;
    private Point thirdPoint;

    public Triangle() {
        firstPoint = new Point(0, 0);
        secondPoint = new Point(0, 0);
        thirdPoint = new Point(0, 0);
    }

    @Override
    public void draw(Graphics canvas) {

    }
}
