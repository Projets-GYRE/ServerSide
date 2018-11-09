package ch.kalajdzic.web.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PointData {

    @JsonProperty
    final int row;
    @JsonProperty
    final int col;

    public PointData(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
