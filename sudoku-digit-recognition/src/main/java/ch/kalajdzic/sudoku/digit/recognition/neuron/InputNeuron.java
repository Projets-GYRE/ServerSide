package ch.kalajdzic.sudoku.digit.recognition.neuron;

public class InputNeuron extends Neuron {


    @Override
    public void update() {
    }

    public void setValue(float x) {
        sortie = x;
        sortieAfterFunction = x;
    }
}
