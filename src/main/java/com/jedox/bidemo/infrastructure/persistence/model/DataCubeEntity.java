package com.jedox.bidemo.infrastructure.persistence.model;


import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = DataCubeEntity.Persistence.TABLE_DATACUBE)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "DataCubeEntity.getAllData",
                query = "select cb.dimension_one_id, d1.dimension_name as dimension_one_name, d1.parent_id as parent_one_id, " +
                        "cb.dimension_two_id, d2.dimension_name as dimension_two_name, d2.parent_id as parent_two_id, " +
                        "cb.dimension_three_id, d3.dimension_name as dimension_three_name, d3.parent_id as parent_three_id, " +
                        "cb.data " +
                        "from datacube cb " +
                        " join dimension d1 ON cb.dimension_one_id = d1.dimension_id " +
                        "left join dimension d2 ON cb.dimension_two_id = d2.dimension_id " +
                        "left join dimension d3 ON cb.dimension_three_id = d3.dimension_id ",
                resultSetMapping = "DataCubeMapping"),
        @NamedNativeQuery(
                name = "DataCubeEntity.getDataByDimension",
                query = "select cb.dimension_one_id, d1.dimension_name as dimension_one_name, d1.parent_id as parent_one_id, " +
                        "cb.dimension_two_id, d2.dimension_name as dimension_two_name, d2.parent_id as parent_two_id, " +
                        "cb.dimension_three_id, d3.dimension_name as dimension_three_name, d3.parent_id as parent_three_id, " +
                        "cb.data " +
                        "from datacube cb " +
                        "join dimension d1 ON cb.dimension_one_id = d1.dimension_id " +
                        "left join dimension d2 ON cb.dimension_two_id = d2.dimension_id " +
                        "left join dimension d3 ON cb.dimension_three_id = d3.dimension_id " +
                        "where cb.dimension_one_id in ( " +
                        "    with recursive DimensionHierarchy1 as ( " +
                        "       select inner1.dimension_id " +
                        "       from dimension inner1 " +
                        "       where (:dimension1 is null and inner1.parent_id is null or :dimension1 is not null and inner1.dimension_id = :dimension1) " +
                        "            and inner1.position = 0 " +
                        "       union all " +
                        "       select dinner1.dimension_id " +
                        "       from dimension dinner1 " +
                        "       join DimensionHierarchy1 dh1 on dinner1.parent_id = dh1.dimension_id) " +
                        "       select dimension_id from DimensionHierarchy1) " +
                        "  and cb.dimension_two_id in ( " +
                        "    with recursive DimensionHierarchy2 as ( " +
                        "       select inner2.dimension_id " +
                        "       from dimension inner2 " +
                        "       where (:dimension2 is null and inner2.parent_id is null or :dimension2 is not null and inner2.dimension_id = :dimension2) " +
                        "            and inner2.position = 1 " +
                        "       union all " +
                        "       select dinner2.dimension_id " +
                        "       from dimension dinner2 " +
                        "       join DimensionHierarchy2 dh2 on dinner2.parent_id = dh2.dimension_id) " +
                        "       select dimension_id from DimensionHierarchy2) " +
                        "  and cb.dimension_three_id  in ( " +
                        "    with recursive DimensionHierarchy3 as ( " +
                        "       select inner3.dimension_id " +
                        "       from dimension inner3 " +
                        "       where (:dimension3 is null and inner3.parent_id is null or :dimension3 is not null and inner3.dimension_id = :dimension3) " +
                        "            and inner3.position = 2 " +
                        "       union all " +
                        "       select dinner3.dimension_id " +
                        "       from dimension dinner3 " +
                        "       join DimensionHierarchy3 dh3 on dinner3.parent_id = dh3.dimension_id) " +
                        "       select dimension_id from DimensionHierarchy3)",
                resultSetMapping = "DataCubeMapping")
})
@SqlResultSetMapping(name = "DataCubeMapping",
        columns = {@ColumnResult(name = "dimension_one_id", type = Long.class),
               @ColumnResult(name = "dimension_one_name", type = String.class),
               @ColumnResult(name = "parent_one_id", type = Long.class),
               @ColumnResult(name = "dimension_two_id", type = Long.class),
               @ColumnResult(name = "dimension_two_name", type = String.class),
               @ColumnResult(name = "parent_two_id", type = Long.class),
               @ColumnResult(name = "dimension_three_id", type = Long.class),
               @ColumnResult(name = "dimension_three_name", type = String.class),
               @ColumnResult(name = "parent_three_id", type = Long.class),
               @ColumnResult(name = "data", type = double.class)}
        )

public class DataCubeEntity implements Serializable {
    public static final class Persistence {
        public static final String TABLE_DATACUBE = "datacube";
        public static final String COLUMN_DIMENSION_ONE_ID = "dimension_one_id";
        public static final String COLUMN_DIMENSION_TWO_ID = "dimension_two_id";
        public static final String COLUMN_DIMENSION_THREE_ID = "dimension_three_id";
        public static final String COLUMN_DATA = "data";
    }
    @EmbeddedId
    private DataPK dataCubeId;
    @Column(name = Persistence.COLUMN_DATA)
    private double data;

    public DataPK getDataCubeId() {
        return dataCubeId;
    }

    public void setDataCubeId(DataPK dataCubeId) {
        this.dataCubeId = dataCubeId;
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }
}
