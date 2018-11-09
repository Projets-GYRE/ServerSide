package ch.kalajdzic.sudoku.grid.detection;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

import static org.opencv.core.Core.bitwise_not;
import static org.opencv.imgproc.Imgproc.ADAPTIVE_THRESH_MEAN_C;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY;

/**
 * The best detector I made
 */
public class CornerDetectorA extends CornerDetector {

    @Override
    Corners detect(byte[] content) {
        Mat sudoku = Imgcodecs.imdecode(new MatOfByte(content), Imgcodecs.CV_LOAD_IMAGE_ANYCOLOR);

        sudoku = reduceAmountOfData(sudoku);
        sudoku = gaussianBlur(sudoku);
        sudoku = threshold(sudoku);

        List<MatOfPoint> contours = getAllContoursOnThisPicture(sudoku);
        MatOfPoint biggestContour = selectBiggest(contours);

        List<Point> points = biggestContour.toList();

        Point topRight = getNearerPoint(points, new Point(sudoku.width(), 0));
        Point topLeft = getNearerPoint(points, new Point(0, 0));
        Point bottomRight = getNearerPoint(points, new Point(sudoku.width(), sudoku.height()));
        Point bottomLeft = getNearerPoint(points, new Point(0, sudoku.height()));

        return new Corners(topLeft, topRight, bottomRight, bottomLeft);
    }

    private List<MatOfPoint> getAllContoursOnThisPicture(Mat picture) {
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(picture, contours, new Mat(), Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        return contours;
    }

    private MatOfPoint selectBiggest(List<MatOfPoint> contours) {
        MatOfPoint biggestContour = new MatOfPoint();

        double maxArea = 0;
        for (int i = 0; i < contours.size(); i++) {
            MatOfPoint currContour = contours.get(i);
            double currArea = Imgproc.contourArea(currContour);
            if (currArea > maxArea) {
                maxArea = currArea;
                biggestContour = currContour;
            }
        }
        return biggestContour;
    }

    private Point getNearerPoint(List<Point> points, Point extreme) {
        Point p = null;
        double distanceOfP = Integer.MAX_VALUE;
        for (Point point : points) {
            double d = distanceBetweenPoints(point, extreme);
            if (d < distanceOfP) {
                p = point;
                distanceOfP = d;
            }
        }
        return p;
    }

    private double distanceBetweenPoints(Point a, Point b) {
        return Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2);
    }

    private Mat reduceAmountOfData(Mat mat) {
        //convert the image to black and white
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY);
        return mat;
    }

    private Mat gaussianBlur(Mat sudoku) {
        Imgproc.GaussianBlur(sudoku, sudoku, new Size(15, 15), 0);
        return sudoku;
    }

    private Mat threshold(Mat mat) {
        Imgproc.adaptiveThreshold(mat, mat, 255, ADAPTIVE_THRESH_MEAN_C, THRESH_BINARY, 11, 2);
        bitwise_not(mat, mat);
        return mat;
    }
}
