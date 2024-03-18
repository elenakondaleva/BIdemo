package com.jedox.bidemo.domain.model;

/**
 * Represents dimension.
 */
public class Dimension {

    private Long dimensionId;
    private String dimensionName;
    private Long parentId;
    private DimensionPosition position;

    public Dimension(Long dimensionId, String dimensionName, Long parentId, DimensionPosition position) {
        this.dimensionId = dimensionId;
        this.dimensionName = dimensionName;
        this.parentId = parentId;
        this.position = position;
    }
    /**
     *  dimension Id.
     */
    public Long getDimensionId() {
        return dimensionId;
    }
    /**
     *  dimension Name
     */
    public String getDimensionName() {
        return dimensionName;
    }
    /**
     *  parent Id
     */
    public Long getParentId() {
        return parentId;
    }
    /**
     *  position, it can be 1,2,3
     */
    public DimensionPosition getPosition() {
        return position;
    }
}
