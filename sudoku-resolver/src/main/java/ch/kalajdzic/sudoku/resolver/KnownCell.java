package ch.kalajdzic.sudoku.resolver;

class KnownCell extends Cell {

    private final int number;

    KnownCell(int row, int col, int number) {
        super(row, col);
        this.number = number;
    }

    @Override
    int value() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
