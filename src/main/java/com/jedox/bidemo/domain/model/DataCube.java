package com.jedox.bidemo.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Represents datacube. Each item is a cell in the spreadsheet that characterised by three dimensions.
 */
public class DataCube {
    @NotNull
    @Positive
    private final Dimension dimensionOne;
    private final Dimension dimensionTwo;
    private final Dimension dimensionThree;
    private final double data;

    public DataCube(@NotNull Dimension dimensionOne, Dimension dimensionTwo, Dimension dimensionThree, double data) {
        this.dimensionOne = dimensionOne;
        this.dimensionTwo = dimensionTwo;
        this.dimensionThree = dimensionThree;
        this.data = data;
    }
    /**
     * Dimension one, which depends on the bd data. In this case this is Geographical hierarchy.
     */
    public Dimension getDimensionOne() {
        return dimensionOne;
    }
    /**
     * Dimension two, which depends on the bd data. In this case this is Product hierarchy.
     */
    public Dimension getDimensionTwo() {
        return dimensionTwo;
    }
    /**
     * Dimension three, which depends on the bd data. In this case this is Financial Categories hierarchy.
     */
    public Dimension getDimensionThree() {
        return dimensionThree;
    }

    public double getData() {
        return data;
    }


}
