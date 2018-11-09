package ch.kalajdzic.sudoku.digit.recognition.layer;

import ch.kalajdzic.sudoku.digit.recognition.neuron.InputNeuron;
import ch.kalajdzic.sudoku.digit.recognition.neuron.Neuron;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Layer {

    List<Neuron> neurons;

    Layer() {
    }

    public Layer(int size) {
        this.neurons = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            neurons.add(new Neuron());
        }
    }

    public List<Float> calculateOutput() {
        List<Float> output = new ArrayList<>();
        for (Neuron neuron : neurons) {
            output.add(neuron.sortieAfterFunction);
        }
        return output;
    }

    public void connect(Layer layer) {
        neurons.forEach(neuron -> layer.neurons.forEach(neuron::connect));
    }

    public void connect(Layer layer, Queue<Float> weights) {
        neurons.forEach(neuron -> {
            for (Neuron n : layer.neurons) {
                neuron.connect(n, weights.poll());
            }
        });
    }


    public void updateNeurons() {
        neurons.forEach(Neuron::update);
    }

    public void updateError() {
        neurons.forEach(Neuron::updateError);
    }

    public void updateCableWeights() {
        neurons.forEach(Neuron::updateWeights);
    }

    public int size() {
        return neurons.size();
    }


    public void attachBias(Queue<Float> biasesWeights, InputNeuron biasNeuron) {
        neurons.forEach(neuron -> biasNeuron.connect(neuron, biasesWeights.poll()));
    }

    public void printWeights(PrintStream out) {
        neurons.forEach(neuron -> neuron.printWeights(out));

    }

}
