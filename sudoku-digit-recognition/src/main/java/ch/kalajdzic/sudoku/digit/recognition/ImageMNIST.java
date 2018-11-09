package ch.kalajdzic.sudoku.digit.recognition;

import java.util.List;

public class ImageMNIST {

    private int label;
    private List<Float> data;

    public ImageMNIST(int label, List<Float> data) {
        this.label = label;
        this.data = data;
    }

    int getLabel() {
        return label;
    }

    List<Float> getData() {
        return data;
    }

    public void print() {// 0 - 1
        System.out.println(label);
        for (int r = 0; r < 28; r++) {
            for (int c = 0; c < 28; c++) {
                double value = data.get(r * 28 + c);
                if (value == 0) {
                    System.out.print(" ");
                } else if (value < 1.0 / 3) {
                    System.out.print(".");
                } else if (value < 2 * (1.0 / 3)) {
                    System.out.print("x");
                } else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
        System.out.println("--------------");
    }
}
