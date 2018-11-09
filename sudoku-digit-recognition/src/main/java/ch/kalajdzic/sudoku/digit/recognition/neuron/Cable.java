package ch.kalajdzic.sudoku.digit.recognition.neuron;

import ch.kalajdzic.sudoku.digit.recognition.NeuralNetwork;

public class Cable {
    float weight;
    Neuron input;
    Neuron target;

    Cable(Neuron input, Neuron target, float weight) {
        this.input = input;
        this.target = target;
        this.weight = weight;
    }

    void updateWeight() {

        this.weight += (NeuralNetwork.successRate < 90 ? 0.02 : 0.002) * target.error * target.sortieAfterFunctionPrime * input.sortieAfterFunction;
    }

}
