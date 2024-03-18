package com.jedox.bidemo.domain;

import com.jedox.bidemo.domain.model.DataCube;
import com.jedox.bidemo.infrastructure.persistence.model.TemporaryData;

import java.util.List;
/**
 * Repository for datacube
 */
public interface DataCubeRepository {

    /**
     * Gets data from datacube that includes all the data down the hierarchy
     *
     * @param dimensionOneId the dimensionOneId
     * @param dimensionTwoId the dimensionTwoId
     * @param dimensionThreeId the dimensionThreeId
     * @return a List of data cell object
     */
    List<DataCube> data(Long dimensionOneId, Long dimensionTwoId, Long dimensionThreeId);
}
