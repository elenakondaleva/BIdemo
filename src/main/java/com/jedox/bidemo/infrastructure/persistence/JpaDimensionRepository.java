package com.jedox.bidemo.infrastructure.persistence;

import com.jedox.bidemo.domain.DimensionRepository;
import com.jedox.bidemo.domain.model.Dimension;
import com.jedox.bidemo.domain.model.DimensionPosition;
import com.jedox.bidemo.infrastructure.persistence.model.TemporalDimension;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaDimensionRepository implements DimensionRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Dimension> dimension(int position) {
        List<TemporalDimension> dimension = getDimension(position, -1L);
        return transformDimensionList(dimension, position);
    }

    @Override
    public List<Dimension> hierarchyDimension(int position, Long parentId) {
        List<TemporalDimension> result = getDimension(position, parentId);
        return transformDimensionList(result, position);
    }


    public List<TemporalDimension> getDimension(int position, Long dimensionId) {
        List<Object[]> rawResult = em.createNamedQuery("DimensionEntity.DimensionHierarchy")
                .setParameter("dimension", dimensionId == -1 ? null : dimensionId)
                .setParameter("position", position)
                .getResultList();
        List<TemporalDimension> result = new ArrayList<>();
        rawResult.stream().forEach(el -> {
            result.add(new TemporalDimension(((Long) el[0]), ((String) el[1]), ((Long) el[2])));
        });
        return result;
    }

    private List<Dimension> transformDimensionList(List<TemporalDimension> dimensionList, final int position) {
        return dimensionList.stream().map(entity -> asDimension(entity, position)).collect(Collectors.toList());
    }

    private Dimension asDimension(TemporalDimension temp, final int position) {
        return new Dimension(temp.dimensionId(), temp.dimensionName(), temp.parentId(),
                DimensionPosition.values()[position]);
    }

}
