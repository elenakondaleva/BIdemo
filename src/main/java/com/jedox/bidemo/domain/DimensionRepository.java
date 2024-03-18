package com.jedox.bidemo.domain;

import com.jedox.bidemo.domain.model.Dimension;
import com.jedox.bidemo.infrastructure.persistence.model.TemporalDimension;

import java.util.List;

public interface DimensionRepository {

    List<Dimension> dimension(int position);

    List<Dimension> hierarchyDimension(int position, Long parentId);
}
