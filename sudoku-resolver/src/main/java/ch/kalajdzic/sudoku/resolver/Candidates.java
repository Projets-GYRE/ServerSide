package ch.kalajdzic.sudoku.resolver;


public class Candidates {
    final int row;
    final int col;
    final int[] value;

    Candidates(int row, int col, int[] value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int count() {
        return value.length;
    }
}