package ch.kalajdzic.web.service;

import ch.kalajdzic.sudoku.grid.detection.SolvedPhoto;
import ch.kalajdzic.sudoku.resolver.Cell;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class WholeData {

    @JsonProperty
    private final String fullyResolvedImage;
    @JsonProperty
    private final List<PointData> defaultNumbers;
    @JsonProperty
    private final PointData hint;

    public WholeData(SolvedPhoto solvedPhoto) {
        fullyResolvedImage = solvedPhoto.getBase64();
        defaultNumbers = new ArrayList<>();
        for (Cell cell : solvedPhoto.getInitialGrid().getKnownCells()) {
            defaultNumbers.add(new PointData(cell.getRow(), cell.getCol()));
        }
        hint = new PointData(solvedPhoto.getHintCell().getRow(), solvedPhoto.getHintCell().getCol());
    }
}
