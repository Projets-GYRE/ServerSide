package ch.kalajdzic.sudoku.digit.recognition;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;


public class NeuralNetworkGeneratorTest {

    // here we just test if the construction failed, not the output comportment of the neural net...
    @Test
    public void testNeuralCustom() {
        final LinkedBlockingQueue<Float> weights = new LinkedBlockingQueue<>();
        // 1 to 2 layer weights
        weights.add((float) 0.9);
        weights.add((float) 0.9);

        weights.add((float) 0.9);
        weights.add((float) 0.9);

        // 2 to 3 layer weights
        weights.add((float) 0.9);
        weights.add((float) 0.9);

        // biais
        weights.add((float) 0.9);
        weights.add((float) 0.9);
        weights.add((float) 0.9);

        NeuralNetwork neuralNetwork = NeuralNetworkGenerator.create(new int[]{2, 2, 1}, weights);
    }

}