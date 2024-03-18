package com.jedox.bidemo;

import com.jedox.bidemo.domain.DataCubeRepository;
import com.jedox.bidemo.domain.DataCubeService;
import com.jedox.bidemo.domain.DimensionRepository;
import com.jedox.bidemo.domain.DimensionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BiExampleConfiguration {
    @Bean
    public DimensionService dimensionService(DimensionRepository dimensionRepository) {
        return new DimensionService(dimensionRepository);
    }
    @Bean
    public DataCubeService dataCubeService(DataCubeRepository dataCubeRepository) {
        return new DataCubeService(dataCubeRepository);
    }

}
