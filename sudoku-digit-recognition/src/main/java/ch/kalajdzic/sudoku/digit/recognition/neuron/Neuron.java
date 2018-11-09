package ch.kalajdzic.sudoku.digit.recognition.neuron;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ch.kalajdzic.sudoku.digit.recognition.MathUtils.activation;
import static ch.kalajdzic.sudoku.digit.recognition.MathUtils.functionPrime;

public class Neuron {
    public final List<Cable> inputs = new ArrayList<>();
    public final List<Cable> outputs = new ArrayList<>();

    public float sortie;
    public float sortieAfterFunction;
    public float sortieAfterFunctionPrime;
    public float error;

    public Neuron() {
    }

    public void update() {
        updateSortie();
        updateSortieAfterFunction();
        updateSortieAfterFunctionPrime();
    }

    private void updateSortieAfterFunctionPrime() {
        sortieAfterFunctionPrime = functionPrime(sortie / 100);
    }

    private void updateSortieAfterFunction() {
        sortieAfterFunction = activation(sortie);
    }

    private void updateSortie() {
        // = (w1 * x1) + (w2 * x2) + ...;
        sortie = 0.0f;
        for (int i = 0; i < inputs.size(); i++) {
            sortie += inputs.get(i).weight * inputs.get(i).input.sortieAfterFunction;
        }
    }


    public void connect(Neuron other) {
        Cable cable = new Cable(this, other, new Random().nextFloat());
        outputs.add(cable);
        other.inputs.add(cable);
    }

    public void connect(Neuron other, Float customWeight) {
        Cable cable = new Cable(this, other, customWeight);
        outputs.add(cable);
        other.inputs.add(cable);
    }

    public void updateError() {
        error = 0;
        for (Cable cable : outputs) {
            error += cable.weight * cable.target.error;
        }
    }

    public void updateWeights() {
        outputs.forEach(Cable::updateWeight);
    }

    public void printWeights(PrintStream out) {
        outputs.forEach(cable -> out.print("\n" + cable.weight));
    }
}
