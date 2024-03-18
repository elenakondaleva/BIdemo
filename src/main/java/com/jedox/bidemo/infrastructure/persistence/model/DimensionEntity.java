package com.jedox.bidemo.infrastructure.persistence.model;

import com.jedox.bidemo.domain.model.DimensionPosition;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;

@NamedNativeQueries({
        @NamedNativeQuery(name = "DimensionEntity.DimensionHierarchy",
                query = "with recursive DimensionHierarchy as ( select dimension_id, dimension_name, parent_id from dimension " +
                        "where (:dimension is null and parent_id is null or :dimension is not null and parent_id = :dimension) " +
                        "and position = :position " +
                        "union all " +
                        "select d.dimension_id, d.dimension_name, d.parent_id " +
                        "from dimension d " +
                        "join DimensionHierarchy dh on d.parent_id = dh.dimension_id) " +
                        "select dimension_id, dimension_name, parent_id from DimensionHierarchy", resultSetMapping = "DimensionResultMapping")
})
@SqlResultSetMapping(name = "DimensionResultMapping",
        columns = {@ColumnResult(name = "dimension_id", type = Long.class),
                @ColumnResult(name = "dimension_name", type = String.class),
                @ColumnResult(name = "parent_id", type = Long.class)}
)
@Entity
@Table(name = DimensionEntity.Persistence.TABLE_DIMENSION)
public class DimensionEntity implements Serializable {
    public static final class Persistence {
        public static final String TABLE_DIMENSION = "dimension";
        public static final String COLUMN_DIMENSION_ID = "dimension_id";
        private static final String COLUMN_DIMENSION_NAME = "dimension_name";
        private static final String COLUMN_DIMENSION_PARENT_ID = "parent_id";
        public static final String COLUMN_POSITION_DIMENSION= "position";
    }

    public DimensionEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = Persistence.COLUMN_DIMENSION_ID)
    private Long dimensionId;

    @Max(255)
    @NotBlank
    @Column(name = Persistence.COLUMN_DIMENSION_NAME, nullable = false)
    private String dimensionName;

    @ManyToOne(fetch = FetchType.LAZY)
    @Null
    @JoinColumn(name = Persistence.COLUMN_DIMENSION_PARENT_ID, foreignKey = @ForeignKey(name = "fk_dimension_parent_id"))
    private DimensionEntity parent;

    @Positive
    @Column(name = Persistence.COLUMN_POSITION_DIMENSION, nullable = false)
    private DimensionPosition position;

    public Long getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(Long dimensionId) {
        this.dimensionId = dimensionId;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public DimensionEntity getParent() {
        return this.parent != null ? this.parent : null;
    }

    public void setParent(DimensionEntity parent) {
        this.parent = parent;
    }

    public DimensionPosition getPosition() {
        return position;
    }

    public void setPosition(DimensionPosition position) {
        this.position = position;
    }

    public Long getParentId() {
        return parent != null ? parent.getDimensionId() : null;
    }
}
