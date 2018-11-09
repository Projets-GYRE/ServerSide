package ch.kalajdzic.sudoku.resolver;

public abstract class Cell {

    final int row;
    final int col;

    Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    abstract int value();

    @Override
    public abstract String toString();

    int box() {
        return (row / 3) * 3 + (col / 3);
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
