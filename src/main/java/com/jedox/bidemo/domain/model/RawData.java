package com.jedox.bidemo.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Represents rawdata of datacube. Each item is a cell in the spreadsheet that characterised by three dimensions.
 */
public class RawData {

    @NotNull
    @Positive
    private final Long dimensionOneId;

    @NotNull
    @Positive
    private final Long dimensionTwo;

    @NotNull
    @Positive
    private final Long dimensionThree;
    private final double data;

    public RawData(@NotNull Long dimensionOneId, @NotNull Long dimensionTwo, @NotNull Long dimensionThree, double data) {
        this.dimensionOneId = dimensionOneId;
        this.dimensionTwo = dimensionTwo;
        this.dimensionThree = dimensionThree;
        this.data = data;
    }

    public Long getDimensionOneId() {
        return dimensionOneId;
    }

    public Long getDimensionTwo() {
        return dimensionTwo;
    }

    public Long getDimensionThree() {
        return dimensionThree;
    }

    public double getData() {
        return data;
    }
}
