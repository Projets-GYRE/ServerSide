package ch.kalajdzic.sudoku.digit.recognition.neuron;

public class OutputNeuron extends Neuron {


    public void setErrorFromDesired(int desired) {
        this.error = desired - sortieAfterFunction;
    }

    @Override
    public void updateError() {
    }

    @Override
    public void updateWeights() {
    }
}
