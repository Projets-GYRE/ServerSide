package ch.kalajdzic.sudoku.grid.detection;

import org.opencv.core.Point;

public class Corners {
    private Point p1;
    private Point p2;
    private Point p3;
    private Point p4;

    public Corners(Point p1, Point p2, Point p3, Point p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public Point getP3() {
        return p3;
    }

    public Point getP4() {
        return p4;
    }
}
