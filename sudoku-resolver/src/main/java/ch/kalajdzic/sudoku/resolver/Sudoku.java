package ch.kalajdzic.sudoku.resolver;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Sudoku {
    private Grid grid;

    public Sudoku(Grid grid) {
        this.grid = grid;
    }

    public Grid resolve() {
        resolveGrid(grid);
        return this.grid;
    }

    private boolean resolveGrid(Grid grid) {
        this.grid = grid;

        Grid finalGrid = grid;
        List<UnknownCell> unknownCells = grid.unknown().stream().sorted(Comparator.comparingInt(o -> o.candidates(finalGrid).count())).collect(Collectors.toList());

        for (UnknownCell unknownCell : unknownCells) {
            int row = unknownCell.row;
            int col = unknownCell.col;

            int[] hisCandidates = unknownCell.candidates(grid).value;
            for (int i : hisCandidates) {
                grid = grid.replace(new KnownCell(row, col, i));
                if (grid.check() && resolveGrid(grid)) {
                    return true;
                }
                grid = grid.replace(new UnknownCell(row, col));
            }
            return false;
        }
        return true;
    }
}