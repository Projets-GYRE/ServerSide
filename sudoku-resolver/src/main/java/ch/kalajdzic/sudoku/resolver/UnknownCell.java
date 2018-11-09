package ch.kalajdzic.sudoku.resolver;

import java.util.stream.IntStream;

public class UnknownCell extends Cell {

    UnknownCell(int row, int col) {
        super(row, col);
    }

    @Override
    int value() {
        return -1;
    }

    public Candidates candidates(Grid grid) {
        final int[] candidates = IntStream.range(1, 10)
            .filter(number -> !grid.row(row).contains(number))
            .filter(number -> !grid.column(col).contains(number))
            .filter(number -> !grid.box(box()).contains(number))
            .toArray();
        return new Candidates(row, col, candidates);
    }

    int box() {
        return (row / 3) * 3 + (col / 3);
    }

    @Override
    public String toString() {
        return ".";
    }
}