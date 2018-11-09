package ch.kalajdzic.sudoku.digit.recognition.layer;

import ch.kalajdzic.sudoku.digit.recognition.neuron.InputNeuron;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class InputLayer extends Layer {


    public InputLayer(int size) {
        this.neurons = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            neurons.add(new InputNeuron());
        }
    }

    public void setValues(List<Float> values) {
        for (int i = 0; i < neurons.size(); i++) {
            ((InputNeuron) neurons.get(i)).setValue(values.get(i));
        }
    }

    @Override
    public void attachBias(Queue<Float> biasesWeights, InputNeuron biasNeuron) {
    }
}
