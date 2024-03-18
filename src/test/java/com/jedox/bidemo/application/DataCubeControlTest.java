package com.jedox.bidemo.application;

import com.jedox.bidemo.application.model.DataCubeController;
import com.jedox.bidemo.domain.DataCubeService;
import com.jedox.bidemo.domain.model.DataCube;
import com.jedox.bidemo.domain.model.Dimension;
import com.jedox.bidemo.domain.model.DimensionPosition;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static com.jedox.bidemo.application.model.DataCubeController.V1_DATACUBE_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(DataCubeController.class)
public class DataCubeControlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataCubeService dataCubeService;

    @Test
    void getDatacubeShouldSucceed() throws Exception {
        Mockito.when(dataCubeService.getData(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(datacube());

        mockMvc.perform(MockMvcRequestBuilders.get(V1_DATACUBE_URL + "/22/50/68/data").contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getMinusOneDatacubeShouldSucceed() throws Exception {
        Mockito.when(dataCubeService.getData(Mockito.anyLong(), Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(datacube());

        mockMvc.perform(MockMvcRequestBuilders.get(V1_DATACUBE_URL + "/22/50/-1/data").contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private List<DataCube> datacube() {
        Dimension dimensionOne = new Dimension(22L,"San Jose", 21L, DimensionPosition.ONE);
        Dimension dimensionTwo = new Dimension(50L,"Bikes", 49L, DimensionPosition.TWO);
        Dimension dimensionThree = new Dimension(68L,"Actual", null, DimensionPosition.THREE);
        return Arrays.asList(new DataCube(dimensionOne,dimensionTwo, dimensionThree, 390.0));

    }
}
