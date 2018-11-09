package ch.kalajdzic.sudoku.resolver;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.fest.assertions.Assertions.assertThat;


public class GridTest {
    @Test
    public void test_column_content() {
        Grid grid = Sample.sample1();
        assertThat(column(0, grid)).containsExactly(6, 7);

    }

    private List<Integer> column(int col, Grid grid) {
        return grid.column(col).stream()
                .filter(cell -> cell.value() > 0)
                .map(Cell::value)
                .collect(Collectors.toList());
    }

    @Test
    public void test_replace(){
        Grid grid = Sample.sample1();
        grid.print();
        System.out.println("\n\n");
        grid.replace(new KnownCell(8, 8, 8)).print();
//        assertThat(grid.replace(new KnownCell(0, 0, 1)).cells.get(0).value()).isEqualTo(1);
    }

    @Test
    public void test_box(){
        Grid grid = Sample.sample1();
        List<String> cells = grid.box(8).stream().map(Cell::toString).collect(Collectors.toList());
        assertThat(cells).containsExactly("9", ".", ".", ".", ".", "7", "2", ".", ".");
    }

    @Test
    public void test_column(){
        Grid grid = Sample.sample1();
        grid.column(1).stream().forEach(cell -> System.out.println(cell.row+" "+cell.col));
    }

    @Test
    public void test_row(){
        Grid grid = Sample.sample1();
        grid.row(1).stream().forEach(cell -> System.out.println(cell.row+" "+cell.col));
    }
}