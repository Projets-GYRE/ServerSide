package ch.kalajdzic.sudoku.grid.detection;

import ch.kalajdzic.sudoku.resolver.Cell;
import ch.kalajdzic.sudoku.resolver.Grid;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.Base64;
import java.util.Comparator;

public class SolvedPhoto {

    private final Grid initialGrid;
    private final Cell hintCell;

    private final FlatImage image;

    public SolvedPhoto(Grid initialGrid, Grid resolvedGrid, FlatImage image) {

        this.initialGrid = initialGrid;
        this.image = annotateImageWithAnswer(resolvedGrid, image);
        this.hintCell = initialGrid.unknown().stream().max(Comparator.comparingInt(o -> o.candidates(initialGrid).count())).get();
    }

    public FlatImage annotateImageWithAnswer(Grid solvedGrid, FlatImage image) {
        Mat imageMat = Imgcodecs.imdecode(new MatOfByte(image.getContent()), Imgcodecs.CV_LOAD_IMAGE_COLOR);
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                final String number = solvedGrid.cells.get(row * 9 + col).toString();
                Imgproc.putText(imageMat, number, new Point(col * 100, (row+1) * 100), Core.FONT_HERSHEY_COMPLEX, 4, new Scalar(255, 255, 0), 2);
            }
        }
        return new FlatImage(MatUtility.matToByte(imageMat));
    }


    public byte[] getContent() {
        return image.getContent();
    }

    public String getBase64() {
        return Base64.getEncoder().encodeToString(getContent());
    }

    public Grid getInitialGrid() {
        return initialGrid;
    }

    public Cell getHintCell() {
        return hintCell;
    }
}
