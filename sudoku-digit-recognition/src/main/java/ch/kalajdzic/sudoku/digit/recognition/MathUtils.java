package ch.kalajdzic.sudoku.digit.recognition;

public class MathUtils {


    public static float activation(double x) {
        return (float) (1 / (1 + Math.exp(-x))); // sigmoïd
//        return x >= 0.5 ? 1 : 0;
//        return Math.tanh(x);
    }

    public static float functionPrime(double x) {
        final float activation = activation(x);
        return activation * (1 - activation);
    }
}
