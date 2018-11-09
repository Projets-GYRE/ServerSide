package ch.kalajdzic.sudoku.grid.detection;

import java.io.File;

public class Test {
    // a main because this test shows a JFrame...!
    public static void main(String[] args) {

        File file = new File("/Users/david/Documents/WorkspaceIDEA/Sudoku/sudoku-grid-detection/src/main/resources/sudokupictures/goodluminosity/img1.jpeg");
//        File file = new File("/Users/david/Documents/WorkspaceIDEA/Sudoku/sudoku-grid-detection/src/main/resources/sudokupictures/hard/img1.jpeg");
//        File file = new File("/Users/david/Documents/WorkspaceIDEA/Sudoku/sudoku-grid-detection/src/main/resources/sudokupictures/journal/img5.jpeg");
        Photo image = new ImageFactory().create(file);
        FlatImage flatImage = image.unwrap(new CornerDetectorA(), new PerspectiveTransformer());

    }
}
