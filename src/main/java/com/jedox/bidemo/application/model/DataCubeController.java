package com.jedox.bidemo.application.model;


import com.jedox.bidemo.domain.DataCubeService;
import com.jedox.bidemo.domain.model.DataCube;
import com.jedox.bidemo.domain.model.Dimension;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Datacube")
@Validated
@RestController
@RequestMapping(DataCubeController.V1_DATACUBE_URL)
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.104:3000"})
public class DataCubeController {

    public static final String V1_DATACUBE_URL = "/v1/datacube";
    public static final String BY_DIMENSION_DATA = "/{dimensionOneId}/{dimensionTwoId}/{dimensionThreeId}";

    public static final String ALL_DATA = "/data";
    private DataCubeService dataCubeService;

    public DataCubeController(DataCubeService dataCubeService) {
        this.dataCubeService = dataCubeService;
    }

    @Operation(summary = "Gets data from the cube by choosing dimensions.",
            description = "Returns data from the cube by fixed dimensions. The data choosen gets down the hierarchy.",
            operationId = "Datacube.getData", responses = {
            @ApiResponse(responseCode = "200", description = "The information is found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DataCubeDto.class))),
            @ApiResponse(responseCode = "404", description = "No data is found.",
                    content = @Content) })
    @GetMapping(BY_DIMENSION_DATA + ALL_DATA)
    public List<DataCubeDto> getData(@PathVariable Long dimensionOneId,
                                     @PathVariable Long dimensionTwoId,
                                     @PathVariable Long dimensionThreeId) {
        List<DataCube> dataCubeList = dataCubeService.getData(dimensionOneId, dimensionTwoId, dimensionThreeId);
        return dataCubeList.stream().map(item -> asDataCubeDto(item)).collect(Collectors.toList());
    }

    @Operation(summary = "Gets all data from the cube.",
            description = "Returns data from the cube.",
            operationId = "Datacube.getRawData", responses = {
            @ApiResponse(responseCode = "200", description = "The information is found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DataCubeDto.class))),
            @ApiResponse(responseCode = "404", description = "No data is found.",
                    content = @Content) })
    @GetMapping(ALL_DATA)
    public List<RawDataDto> getRawData() {
        List<DataCube> dataCubeList = dataCubeService.getAllData();
        return dataCubeList.stream().map(item -> asRawDataDto(item)).collect(Collectors.toList());
    }

    private RawDataDto asRawDataDto(DataCube input) {
        return new RawDataDto(input.getDimensionOne().getDimensionId(), input.getDimensionTwo().getDimensionId(),
                input.getDimensionThree().getDimensionId(), input.getData());
    }

    private DataCubeDto asDataCubeDto(DataCube input) {
        DimensionDto dimension1 = asDimensionDto(input.getDimensionOne());
        DimensionDto dimension2 = asDimensionDto(input.getDimensionTwo());
        DimensionDto dimension3 = asDimensionDto(input.getDimensionThree());
        return new DataCubeDto(dimension1, dimension2, dimension3, input.getData());
    }
    private DimensionDto asDimensionDto(Dimension input) {
        return new DimensionDto(input.getDimensionId(), input.getDimensionName(), input.getParentId(), input.getPosition());
    }

}
