package ch.kalajdzic.sudoku.digit.recognition;

import ch.kalajdzic.sudoku.digit.recognition.layer.InputLayer;
import ch.kalajdzic.sudoku.digit.recognition.layer.Layer;
import ch.kalajdzic.sudoku.digit.recognition.layer.OutputLayer;
import ch.kalajdzic.sudoku.digit.recognition.neuron.InputNeuron;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/***
 * A neural Network with any layers you want.
 * */
public class NeuralNetwork {

    public static double successRate;
    private final LinkedList<Layer> layers;
    private final InputNeuron bias;

    public NeuralNetwork(LinkedList<Layer> layers, InputNeuron bias) {
        this.bias = bias;
        this.layers = layers;
    }

    public int test(List<Float> input) { // image 784 values
        ((InputLayer) layers.getFirst()).setValues(input);
        for (Layer layer : layers) {
            layer.updateNeurons();
        }
        return getTheMostPossibleNumber(layers.getLast().calculateOutput());
    }


    private int getTheMostPossibleNumber(List<Float> outputLayer) {
        return outputLayer.indexOf(Collections.max(outputLayer));
    }


    private int[] desiredOutput(int label) {
        final int[] result = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        result[label] = 1;
        return result;
    }

    void learn(List<ImageMNIST> learnImages, List<ImageMNIST> testImages) {
        System.out.println("Start learning");
        for (int i = 0; i < 600; i++) {
            for (ImageMNIST learnImage : learnImages) {
                train(learnImage);
            }
            logPerformanceOfNN(testImages);
            saveOnFile();
        }
    }

    private void train(ImageMNIST imageMNIST) {
        List<Float> input = imageMNIST.getData(); // 784 values
        int[] desiredOutputLayerValues = desiredOutput(imageMNIST.getLabel());  // 10 values

        ((InputLayer) layers.getFirst()).setValues(input);
        for (Layer layer : layers) {
            layer.updateNeurons();
        }

//        System.out.println("starting computing error of each neuron");
        updateErrorForEachNeuron(desiredOutputLayerValues);

//        System.out.println("starting updating error of each cable");
        updateWeightForEachCable();
    }

    private void updateErrorForEachNeuron(int[] desiredOutputLayerValues) {
        ((OutputLayer) layers.getLast()).computeErrorByDesired(desiredOutputLayerValues);
        for (int i = layers.size() - 2; i >= 0; i--) {
            layers.get(i).updateError();
        }
    }

    private void updateWeightForEachCable() {
        layers.forEach(Layer::updateCableWeights);
    }


    void logPerformanceOfNN(List<ImageMNIST> testImages) {
        int errors = 0;
        for (ImageMNIST image : testImages) {
            int real = image.getLabel();
            ((InputLayer) layers.getFirst()).setValues(image.getData());
            int ia = test(image.getData());

            if (ia != real) {
                errors++;
            }
        }
        successRate = 100 - ((double) errors / (double) testImages.size()) * 100;
        System.out.println(errors + " errors  |     success rate : " + successRate + "%");
    }

    private void saveOnFile() {
        try (PrintStream out = new PrintStream(new FileOutputStream("OutputData.txt"))) {
            layers.forEach(layer -> out.print(layer.size() + " "));
            layers.forEach(layer -> layer.printWeights(out));
            bias.printWeights(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
