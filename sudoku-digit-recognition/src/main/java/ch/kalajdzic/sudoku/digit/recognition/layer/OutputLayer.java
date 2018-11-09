package ch.kalajdzic.sudoku.digit.recognition.layer;

import ch.kalajdzic.sudoku.digit.recognition.neuron.OutputNeuron;

import java.util.ArrayList;

public class OutputLayer extends Layer {


    public OutputLayer(int size) {
        this.neurons = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            neurons.add(new OutputNeuron());
        }
    }

    public void computeErrorByDesired(int[] desiredValues) {
        for (int i = 0; i < neurons.size(); i++) {
            ((OutputNeuron) neurons.get(i)).setErrorFromDesired(desiredValues[i]);
        }
    }
}
