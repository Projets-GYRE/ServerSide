package ch.kalajdzic.sudoku.grid.detection;

import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.ArrayList;
import java.util.List;

public class FlatImage {

    private byte[] content;

    FlatImage(byte[] content) {
        OpenCV.loadLocally();
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }

    public List<CellImage> decompose() {
        Mat src = Imgcodecs.imdecode(new MatOfByte(content), Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        int cellSide = 100;

        List<CellImage> images = new ArrayList<>();

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Rect roi = new Rect(x * cellSide, y * cellSide, cellSide, cellSide);
                Mat dst = new Mat(src, roi);
                images.add(new CellImage(MatUtility.matToByte(dst)));
            }
        }
        return images;
    }

}
