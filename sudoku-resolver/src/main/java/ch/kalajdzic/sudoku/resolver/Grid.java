package ch.kalajdzic.sudoku.resolver;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Grid {
    public List<Cell> cells;

    Grid(List<Cell> cells) {
        this.cells = cells;
    }

    Cells<Cell> row(int row) {
        final int startIndex = row * 9;
        return () -> cells.subList(startIndex, startIndex + 9).stream();
    }

    Cells<Cell> column(int col) {
        final List<Cell> list = new ArrayList<>();
        for (int i = 0, index = col; i < 9; i++, index += 9) {
            list.add(cells.get(index));
        }
        return () -> list.stream();
    }

    Cells<Cell> box(int number) {
        final List<Cell> list = new ArrayList<>();
        for (int i = 0, index = (number - number % 3) * 9 + (number % 3 * 3); i < 3; i++, index += 9) {
            for (int j = 0; j < 3; j++) {
                list.add(cells.get(index + j));
            }
        }
        return () -> list.stream();
    }

    public Cells<UnknownCell> unknown() {
        return () -> cells.stream().filter(cell -> cell.value() < 0).map(UnknownCell.class::cast);
    }


    Grid replace(KnownCell replacement) {
        final List<Cell> newCells = new ArrayList<>(this.cells);
        newCells.set(replacement.row * 9 + replacement.col, replacement);
        return new Grid(newCells);
    }

    Grid replace(UnknownCell replacement) {
        final List<Cell> newCells = new ArrayList<>(this.cells);
        newCells.set(replacement.row * 9 + replacement.col, replacement);
        return new Grid(newCells);
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean check() {
        for (Cell cell : this.cells) {
            if (this.column(cell.col).hasAnyDuplicates()) {
                return false;
            }
            if (this.row(cell.row).hasAnyDuplicates()) {
                return false;
            }

            if (this.box(cell.box()).hasAnyDuplicates()) {
                return false;
            }

        }
        return true;
    }

    public List<KnownCell> getKnownCells() {
        List<KnownCell> knownCells = new ArrayList<>();
        for (Cell cell : cells) {
            if (!cell.toString().equals(".")) knownCells.add((KnownCell) cell);
        }
        return knownCells;
    }

    public static class Builder {
        private final List<Cell> cells = new ArrayList<>();

        public Builder() {
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    cells.add(new UnknownCell(row, col));
                }
            }
        }

        public Builder add(int row, int col, int number) {
            return add(new KnownCell(row, col, number));
        }

        public Builder add(Cell cell) {
            cells.set(cell.row * 9 + cell.col, cell);
            return this;
        }

        public Builder add(List<Cell> cellsList) {
            for (Cell cell : cellsList) {
                cells.set(cell.row * 9 + cell.col, cell);
            }
            return this;
        }

        public Builder addWhole(List<Integer> numbers) {
            for (int i = 0; i < numbers.size(); i++) {
                final int number = numbers.get(i);
                final int currentRow = i / 9;
                final int currentCol = i % 9;
                if (number <= 0) {
                    cells.set(currentRow * 9 + currentCol, new UnknownCell(currentRow, currentCol));
                } else {
                    cells.set(currentRow * 9 + currentCol, new KnownCell(currentRow, currentCol, number));
                }
            }
            return this;
        }

        public Grid build() {
            return new Grid(unmodifiableList(cells));
        }
    }

    public void print() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Cell c = this.cells.get(y * 9 + x);
                if (c.col == 0) System.out.print("|");
                else if (c.col == 3) System.out.print("|");
                else if (c.col == 6) System.out.print("|");

                System.out.print(c.toString());
            }
            if (y == 2) System.out.println("|\n --- --- ---");
            else if (y == 5) System.out.println("|\n --- --- ---");
            else System.out.print("|\n");
        }
        System.out.println("-----------------------------------------");

    }
}
