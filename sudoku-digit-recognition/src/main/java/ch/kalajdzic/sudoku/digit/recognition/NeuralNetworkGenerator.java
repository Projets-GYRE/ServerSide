package ch.kalajdzic.sudoku.digit.recognition;

import ch.kalajdzic.sudoku.digit.recognition.layer.InputLayer;
import ch.kalajdzic.sudoku.digit.recognition.layer.Layer;
import ch.kalajdzic.sudoku.digit.recognition.layer.OutputLayer;
import ch.kalajdzic.sudoku.digit.recognition.neuron.InputNeuron;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class NeuralNetworkGenerator {

    // custom
    public static NeuralNetwork create(int[] size, Queue<Float> weights) {
        LinkedList<Layer> layers = new LinkedList<>();
        layers.add(new InputLayer(size[0]));
        createLayersWithCustomWeight(layers, 1, size, weights);
        final InputNeuron bias = new InputNeuron();
        bias.setValue(1);
        attachBias(weights, layers, bias);
        return new NeuralNetwork(layers, bias);
    }

    private static void createLayersWithCustomWeight(LinkedList<Layer> layers, int layerIndex, int[] layerSize, Queue<Float> weights) {
        if (layerSize.length == layerIndex + 1) {
            final OutputLayer outputLayer = new OutputLayer(layerSize[layerIndex]);
            layers.getLast().connect(outputLayer, weights);
            layers.add(outputLayer);
            return;
        }
        Layer currentLayer = new Layer(layerSize[layerIndex]);
        layers.getLast().connect(currentLayer, weights);
        layers.add(currentLayer);
        createLayersWithCustomWeight(layers, layerIndex + 1, layerSize, weights);
    }

    private static void attachBias(Queue<Float> biasesWeights, LinkedList<Layer> layers, InputNeuron biasNeuron) {
        layers.forEach(layer -> layer.attachBias(biasesWeights, biasNeuron));
    }

    // random
    public static NeuralNetwork create(int[] size) {//generally 784 for us...
        final LinkedList<Layer> layers = new LinkedList<>();
        layers.add(new InputLayer(size[0]));
        createLayers(layers, 1, size);
        final InputNeuron bias = new InputNeuron();
        bias.setValue(1);
        attachBias(new Queue<Float>() {
            @Override
            public boolean add(Float aFloat) {
                return false;
            }

            @Override
            public boolean offer(Float aFloat) {
                return false;
            }

            @Override
            public Float remove() {
                return null;
            }

            @Override
            public Float poll() {
                return new Random().nextFloat();
            }

            @Override
            public Float element() {
                return null;
            }

            @Override
            public Float peek() {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Float> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Float> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        }, layers, bias);
        return new NeuralNetwork(layers, bias);
    }

    private static void createLayers(LinkedList<Layer> layers, int layerIndex, int[] layerSize) {
        if (layerSize.length == layerIndex + 1) {
            final OutputLayer outputLayer = new OutputLayer(layerSize[layerIndex]);
            layers.getLast().connect(outputLayer);
            layers.add(outputLayer);
            return;
        }
        Layer currentLayer = new Layer(layerSize[layerIndex]);
        layers.getLast().connect(currentLayer);
        layers.add(currentLayer);
        createLayers(layers, layerIndex + 1, layerSize);
    }

    // from file

    public static NeuralNetwork loadFromFile() {
        Scanner scanner = new Scanner(NeuralNetworkGenerator.class.getClassLoader().getResourceAsStream("ch.kalajdzic.sudoku.digit.recognition/LoadData/LoadData.txt"));
        String[] split = scanner.nextLine().split(" ");
        int[] dimensions = new int[split.length];
        for (int i = 0; i < dimensions.length; i++) {
            dimensions[i] = Integer.parseInt(split[i]);
        }

        LinkedBlockingQueue<Float> weights = new LinkedBlockingQueue<>();
        while (scanner.hasNextLine()) {
            double v = scanner.nextDouble();
            weights.add((float) v);
        }

        return create(dimensions, weights);

    }

}
