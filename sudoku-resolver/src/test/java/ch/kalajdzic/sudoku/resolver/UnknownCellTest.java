package ch.kalajdzic.sudoku.resolver;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class UnknownCellTest {
    @Test
    public void test_candidate() {
        final Grid grid = Sample.sample1();
        UnknownCell unknownCell = (UnknownCell) grid.cells.get(1);
        int[] candidates = unknownCell.candidates(grid).value;
        assertThat(candidates).containsOnly(2, 4, 5);
    }

    @Test
    public void test_boxes() {
        assertThat(new UnknownCell(6, 1).box()).isEqualTo(6);
        assertThat(new UnknownCell(1, 6).box()).isEqualTo(2);
        assertThat(new UnknownCell(7, 6).box()).isEqualTo(8);
        assertThat(new UnknownCell(5, 3).box()).isEqualTo(4);
    }
}