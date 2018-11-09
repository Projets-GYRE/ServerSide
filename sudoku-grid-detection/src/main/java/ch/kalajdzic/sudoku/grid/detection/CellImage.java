package ch.kalajdzic.sudoku.grid.detection;

import ch.kalajdzic.sudoku.digit.recognition.NeuralNetwork;
import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

import static org.opencv.core.CvType.CV_8UC1;

public class CellImage {
    private byte[] content;

    CellImage(byte[] content) {
        OpenCV.loadLocally();
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }

    public int detect(NeuralNetwork neuralNetwork) {
        List<Float> input = getInput();
        if (isThereNumber(input)){
            return -1;
        }
        return neuralNetwork.test(input);
    }

    private boolean isThereNumber(List<Float> input) {
        List<Float> test = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            test.add(input.get(406 + i));
        }
        if (test.stream().anyMatch(aFloat -> aFloat > 0)) return false;
        return true;
    }

    private List<Float> getInput() {
        List<Float> inputs = new ArrayList<>();
        Mat image = getNormalizedImage();
        for (int row = 0; row < 28; row++) {
            for (int col = 0; col < 28; col++) {
                inputs.add((float) (image.get(row, col)[0] / 255.0));
            }
        }
        return inputs;
    }

    /**
     * in other terms, it will convert into a white number in a black background & remove 10 pixels all around & then resize it to 28px -> read for NeuralNetwork
     */
    private Mat getNormalizedImage() {
        Mat image = Imgcodecs.imdecode(new MatOfByte(content), CV_8UC1);
        Imgproc.threshold(image, image, 0, 255, Imgproc.THRESH_BINARY_INV + Imgproc.THRESH_OTSU);
        image = image.submat(new Rect(new Point(10, 10), new Point(90, 90)));
        Imgproc.resize(image, image, new Size(28, 28));
        return image;
    }
}
