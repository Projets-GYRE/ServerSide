package ch.kalajdzic.sudoku.digit.recognition;

import org.junit.Test;

import java.io.IOException;

public class LoadNeuralNetworkTest {


    @Test
    public void test_how_to_load_my_neuralNetwork() {

        NeuralNetwork neuralNetwork = NeuralNetworkGenerator.loadFromFile();
        try {
            neuralNetwork.logPerformanceOfNN(NeuralNetworkTest.getImageMnistList(10000, NeuralNetworkTest.testLabels, NeuralNetworkTest.testImages));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
