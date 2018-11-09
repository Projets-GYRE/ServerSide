package ch.kalajdzic.sudoku.resolver;

import java.util.stream.Stream;

@FunctionalInterface
public interface Cells<T extends Cell> {
    Stream<T> stream();

    default boolean contains(int number) {
        return stream().anyMatch(cell -> cell.value() == number);
    }

    default boolean hasAnyDuplicates() {
        final IntSet set = new IntSet(10);
        return stream().anyMatch(cell -> cell.value() > 0 && !set.add(cell.value()));
    }

    class IntSet {
        private final boolean[] used;

        IntSet(int maxValue) {
            this.used = new boolean[maxValue];
        }

        public boolean add(int i) {
            if (used[i]) {
                return false;
            }
            return used[i] = true;
        }
    }
}
