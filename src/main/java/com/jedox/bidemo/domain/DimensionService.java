package com.jedox.bidemo.domain;

import com.jedox.bidemo.domain.model.Dimension;
import com.jedox.bidemo.infrastructure.persistence.model.TemporalDimension;

import java.util.List;

public class DimensionService {
    private DimensionRepository dimensionRepository;

    public DimensionService(DimensionRepository dimensionRepository) {
        this.dimensionRepository = dimensionRepository;
    }

    public List<Dimension> getHierarchyDimension(int position, Long parentId) {
        return dimensionRepository.hierarchyDimension(position, parentId);
    }
}
