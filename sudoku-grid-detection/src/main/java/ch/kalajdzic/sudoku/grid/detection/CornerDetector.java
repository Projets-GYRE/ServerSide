package ch.kalajdzic.sudoku.grid.detection;

public abstract class CornerDetector {

    abstract Corners detect(byte[] content);
}
