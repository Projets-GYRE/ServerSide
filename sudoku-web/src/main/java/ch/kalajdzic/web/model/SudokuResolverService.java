package ch.kalajdzic.web.model;

import ch.kalajdzic.sudoku.digit.recognition.NeuralNetwork;
import ch.kalajdzic.sudoku.digit.recognition.NeuralNetworkGenerator;
import ch.kalajdzic.sudoku.grid.detection.*;
import ch.kalajdzic.sudoku.resolver.Grid;
import ch.kalajdzic.sudoku.resolver.Sudoku;

import java.util.List;
import java.util.stream.Collectors;


public class SudokuResolverService {

    private final ImageFactory imageFactory = new ImageFactory();


    public SolvedPhoto resolve(String imgBase64) {

        Photo photo = imageFactory.create(imgBase64);
        FlatImage flatImage = photo.unwrap(new CornerDetectorA(), new PerspectiveTransformer());
        List<CellImage> cells = flatImage.decompose();

        NeuralNetwork neuralNetwork = NeuralNetworkGenerator.loadFromFile();
        Grid initialGrid = Grid.builder()
                .addWhole(cells.stream().map(cellImage -> cellImage.detect(neuralNetwork)).collect(Collectors.toList()))
                .build();

        initialGrid.print();
        Sudoku sudoku = new Sudoku(initialGrid);
        Grid resolvedGrid = sudoku.resolve();

        return new SolvedPhoto(initialGrid, resolvedGrid, flatImage);
    }
}
