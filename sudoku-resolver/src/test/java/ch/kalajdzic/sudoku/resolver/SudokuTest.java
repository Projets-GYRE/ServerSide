package ch.kalajdzic.sudoku.resolver;

import org.junit.Ignore;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class SudokuTest {

    @Test
    public void test_to_solve_sample1() {
        Sudoku sudoku = new Sudoku(Sample.sample1());
        Grid resolvedGrid = sudoku.resolve();
        resolvedGrid.print();
        assertThat(resolvedGrid.check()).isEqualTo(true);
    }

    @Test
    public void test_to_solve_sample2() {
        Sudoku sudoku = new Sudoku(Sample.sample2());
        Grid resolvedGrid = sudoku.resolve();
        resolvedGrid.print();
        assertThat(resolvedGrid.check()).isEqualTo(true);
    }

    @Test
    public void test_to_solve_hard() {
        Sudoku sudoku = new Sudoku(Sample.hard());
        Grid resolvedGrid = sudoku.resolve();
        resolvedGrid.print();
        assertThat(resolvedGrid.check()).isEqualTo(true);
    }
    @Test
    @Ignore
    public void test_to_solve_challenge() {
        Sudoku sudoku = new Sudoku(Sample.impossibleWithBruteForce());
        Grid resolvedGrid = sudoku.resolve();
        resolvedGrid.print();
        assertThat(resolvedGrid.check()).isEqualTo(true);
    }

}