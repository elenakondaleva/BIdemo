package com.jedox.bidemo.infrastructure.persistence.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.io.Serializable;
@Embeddable
public class DataPK implements Serializable {
    @OneToOne
    @JoinColumn(name = DataCubeEntity.Persistence.COLUMN_DIMENSION_ONE_ID,
            referencedColumnName = DimensionEntity.Persistence.COLUMN_DIMENSION_ID,
            foreignKey = @ForeignKey(name = "fk_datacube_dimension_1"), nullable = false)
    private DimensionEntity dimensionOne;

    @OneToOne
    @JoinColumn(name = DataCubeEntity.Persistence.COLUMN_DIMENSION_TWO_ID,
            referencedColumnName = DimensionEntity.Persistence.COLUMN_DIMENSION_ID,
            foreignKey = @ForeignKey(name = "fk_datacube_dimension_2"), nullable = false)
    private DimensionEntity dimensionTwo;

    @OneToOne
    @JoinColumn(name = DataCubeEntity.Persistence.COLUMN_DIMENSION_THREE_ID,
            referencedColumnName = DimensionEntity.Persistence.COLUMN_DIMENSION_ID,
            foreignKey = @ForeignKey(name = "fk_datacube_dimension_3"), nullable = false)
    private DimensionEntity dimensionThree;

    public DataPK(DimensionEntity dimensionOne, DimensionEntity dimensionTwo, DimensionEntity dimensionThree) {
        this.dimensionOne = dimensionOne;
        this.dimensionTwo = dimensionTwo;
        this.dimensionThree = dimensionThree;
    }

    public DataPK() {

    }

    public DimensionEntity getDimensionOne() {
        return dimensionOne;
    }

    public void setDimensionOne(DimensionEntity dimensionOne) {
        this.dimensionOne = dimensionOne;
    }

    public DimensionEntity getDimensionTwo() {
        return dimensionTwo;
    }

    public void setDimensionTwo(DimensionEntity dimensionTwo) {
        this.dimensionTwo = dimensionTwo;
    }

    public DimensionEntity getDimensionThree() {
        return dimensionThree;
    }

    public void setDimensionThree(DimensionEntity dimensionThree) {
        this.dimensionThree = dimensionThree;
    }
}
