package com.jedox.bidemo.domain;

import com.jedox.bidemo.domain.model.DataCube;

import java.util.List;
public class DataCubeService {
    private DataCubeRepository dataCubeRepository;

    public DataCubeService(DataCubeRepository datacubeRepository) {
        this.dataCubeRepository = datacubeRepository;
    }
    /**
     * Gets data from datacube that includes all the data down the hierarchy
     *
     * @param dimensionOneId the dimensionOneId
     * @param dimensionTwoId the dimensionTwoId
     * @param dimensionThreeId the dimensionThreeId
     * @return a List of data cell object
     */
    public List<DataCube> getData(Long dimensionOneId, Long dimensionTwoId, Long dimensionThreeId) {
        return dataCubeRepository.data(dimensionOneId, dimensionTwoId, dimensionThreeId);
    };

    public List<DataCube> getAllData() {
        return dataCubeRepository.data(-1L, -1L, -1L);
    };
}
