package com.jedox.bidemo.infrastructure.persistence;

import com.jedox.bidemo.domain.DataCubeRepository;
import com.jedox.bidemo.domain.model.DataCube;
import com.jedox.bidemo.domain.model.Dimension;
import com.jedox.bidemo.domain.model.DimensionPosition;
import com.jedox.bidemo.infrastructure.persistence.model.DimensionEntity;
import com.jedox.bidemo.infrastructure.persistence.model.TemporaryData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class JpaDataCubeRepository implements DataCubeRepository {

    @PersistenceContext
    private EntityManager em;


    List<TemporaryData> getData(@Param("dimension1") Long dimension1,
                                @Param("dimension2") Long dimension2,
                                @Param("dimension3") Long dimension3) {
        Query query;
        if (dimension1.equals(-1L) && dimension2.equals(-1L) && dimension3.equals(-1L)) {
            query = em.createNamedQuery("DataCubeEntity.getAllData");
        }
        else {
            query = em.createNamedQuery("DataCubeEntity.getDataByDimension")
                .setParameter("dimension1", dimension1.equals(-1L) ? null : Long.valueOf(dimension1))
                .setParameter("dimension2", dimension2.equals(-1L) ? null : Long.valueOf(dimension2))
                .setParameter("dimension3", dimension3.equals(-1L) ? null : Long.valueOf(dimension3));
        }
        List<Object[]> rawResult = query.getResultList();
        List<TemporaryData> result = new ArrayList<>();
        rawResult.stream().forEach(el -> {
            result.add(new TemporaryData(((Long) el[0]), ((String) el[1]), ((Long) el[2]),
                    ((Long) el[3]), ((String) el[4]), ((Long) el[5]),
                    ((Long) el[6]), ((String) el[7]), ((Long) el[8]), (double) el[9]));
        });
        return result;

    }

    @Override
    public List<DataCube> data(Long dimensionOneId, Long dimensionTwoId, Long dimensionThreeId) {
        List<TemporaryData> resultSet = getData(dimensionOneId, dimensionTwoId, dimensionThreeId);
        return transformDateCubeList(resultSet);
    }

    private List<DataCube> transformDateCubeList(List<TemporaryData> dataCubeList) {
        return dataCubeList.stream().map(entity -> asDataCube(entity)).collect(Collectors.toList());
    }

    private DataCube asDataCube(TemporaryData entity) {
        Dimension dimensionEntity1 = asDimensionModel(entity.dimensionOneId(), DimensionPosition.ONE);
        Dimension dimensionEntity2 = asDimensionModel(entity.dimensionTwoId(), DimensionPosition.TWO);
        Dimension dimensionEntity3 = asDimensionModel(entity.dimensionThreeId(), DimensionPosition.THREE);
        return new DataCube(dimensionEntity1, dimensionEntity2, dimensionEntity3, entity.data());
    }

    private Dimension asDimensionModel(Long dimensionId, DimensionPosition position) {
        return Optional.ofNullable(em.find(DimensionEntity.class, dimensionId))
                .map(el -> asDimension(el, position))
                .orElseThrow(EntityNotFoundException::new);
    }

    private Dimension asDimension(DimensionEntity dimension, DimensionPosition position) {
        return new Dimension(dimension.getDimensionId(), dimension.getDimensionName(), dimension.getParentId(), position);
    }
}
