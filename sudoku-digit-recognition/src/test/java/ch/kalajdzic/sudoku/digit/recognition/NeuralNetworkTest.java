package ch.kalajdzic.sudoku.digit.recognition;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NeuralNetworkTest {


    // 60'000
    static final File trainLabels = new File(NeuralNetworkTest.class.getClassLoader().getResource("ch.kalajdzic.sudoku.digit.recognition/MNIST/train-labels-idx1-ubyte").getFile());
    static final File trainImages = new File(NeuralNetworkTest.class.getClassLoader().getResource("ch.kalajdzic.sudoku.digit.recognition/MNIST/train-images-idx3-ubyte").getFile());

    // 10'000
    static final File testLabels = new File(NeuralNetworkTest.class.getClassLoader().getResource("ch.kalajdzic.sudoku.digit.recognition/MNIST/t10k-labels-idx1-ubyte").getFile());
    static final File testImages = new File(NeuralNetworkTest.class.getClassLoader().getResource("ch.kalajdzic.sudoku.digit.recognition/MNIST/t10k-images-idx3-ubyte").getFile());


    public static List<ImageMNIST> getImageMnistList(int howMany, File fileLabels, File fileImages) throws IOException {
        final List<ImageMNIST> imageMNISTS = new ArrayList<>();
        List<Integer> labels = new LabelsReader(fileLabels).read();
        List<List<Float>> images = new ImagesReader(fileImages).read();

        for (int i = 0; i < howMany; i++) { // or images.size() it is the same...
            int label = labels.get(i);
            List<Float> image = images.get(i);

            imageMNISTS.add(new ImageMNIST(label, image));
        }
        return imageMNISTS;
    }

    @Test
    @Ignore
    public void test_how_to_use_my_neuralNetwork() throws IOException {
        NeuralNetwork neuralNetwork = NeuralNetworkGenerator.create(new int[]{784, 100, 50, 10});

        neuralNetwork.learn(getImageMnistList(60000, trainLabels, trainImages), getImageMnistList(10000, testLabels, testImages));

        System.out.println("Finished learning, start using it");
//        neuralNetwork.logPerformanceOfNN(getImageMnistList(500));
    }


}