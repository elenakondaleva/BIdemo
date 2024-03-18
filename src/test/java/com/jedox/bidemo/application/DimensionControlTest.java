package com.jedox.bidemo.application;

import com.jedox.bidemo.application.model.DimensionController;
import com.jedox.bidemo.domain.DimensionService;
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
import java.util.Collections;
import java.util.List;

import static com.jedox.bidemo.application.model.DimensionController.V1_DIMENSION_URL;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(DimensionController.class)
public class DimensionControlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DimensionService dimensionService;

    @Test
    void getDimensionHierarchyShouldSucceed() throws Exception {
        Mockito.when(dimensionService.getHierarchyDimension(Mockito.anyInt(), Mockito.anyLong()))
                .thenReturn(dimension());

        mockMvc.perform(MockMvcRequestBuilders.get(V1_DIMENSION_URL + "/0/21/hierarchy").contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getFakeDimensionShouldReturnEmpty() throws Exception {
        Mockito.when(dimensionService.getHierarchyDimension(Mockito.anyInt(), Mockito.anyLong()))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get(V1_DIMENSION_URL + "/0/221/hierarchy").contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
    }

    @Test
    void getFakePositionShouldReturnEmpty() throws Exception {
        Mockito.when(dimensionService.getHierarchyDimension(Mockito.anyInt(), Mockito.anyLong()))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get(V1_DIMENSION_URL + "/4/21/hierarchy").contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
    }

    @Test
    void getNegativePositionShouldReturnEmpty() throws Exception {
        Mockito.when(dimensionService.getHierarchyDimension(Mockito.anyInt(), Mockito.anyLong()))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get(V1_DIMENSION_URL + "/-1/21/hierarchy").contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
    }

    @Test
    void getMinusOneDimensionShouldReturnAllList() throws Exception {
        Mockito.when(dimensionService.getHierarchyDimension(Mockito.anyInt(), Mockito.anyLong()))
                .thenReturn(rootDimension());

        mockMvc.perform(MockMvcRequestBuilders.get(V1_DIMENSION_URL + "/2/-1/hierarchy").contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private List<Dimension> dimension() {
        return Arrays.asList(new Dimension(22L,"San Jose", 21L, DimensionPosition.ONE));

    }

    private List<Dimension> rootDimension() {
        return Arrays.asList(new Dimension(68L,"Actual", null, DimensionPosition.THREE),
                new Dimension(72L,"San Jose", null, DimensionPosition.THREE),
                new Dimension(69L,"San Jose", 68L, DimensionPosition.THREE),
                new Dimension(70L,"San Jose", 68L, DimensionPosition.THREE),
                new Dimension(71L,"San Jose", 68L, DimensionPosition.THREE),
                new Dimension(73L,"San Jose", 72L, DimensionPosition.THREE),
                new Dimension(74L,"San Jose", 72L, DimensionPosition.THREE),
                new Dimension(75L,"San Jose", 72L, DimensionPosition.THREE));

    }
}
