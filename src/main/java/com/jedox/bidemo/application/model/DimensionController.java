package com.jedox.bidemo.application.model;

import com.jedox.bidemo.domain.DimensionService;
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

@Tag(name = "Dimension")
@Validated
@RestController
@RequestMapping(DimensionController.V1_DIMENSION_URL)
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.104:3000"})
public class DimensionController {

    public static final String V1_DIMENSION_URL = "/v1/dimension";
    public static final String DIMENSION_HIERARCHY = "/{position}/{dimensionId}/hierarchy";
    private final DimensionService dimensionService;

    public DimensionController(DimensionService dimensionService) {
        this.dimensionService = dimensionService;
    }

    @Operation(summary = "Gets information about a dimension hierarchy.",
            description = "Returns information details about dimension hierarchy.",
            operationId = "Dimension.hierarchy", responses = {
            @ApiResponse(responseCode = "200", description = "The information is found.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DimensionDto.class))),
            @ApiResponse(responseCode = "404", description = "No dimension hierarchy exists for the given parent id.",
                    content = @Content) })
    @GetMapping(DIMENSION_HIERARCHY)
    public List<DimensionDto> getDimensionHierarchy(
            @PathVariable int position,
            @PathVariable Long dimensionId) {
        List<Dimension> dimensionHierarchy = dimensionService.getHierarchyDimension(position, dimensionId);
        return dimensionHierarchy.stream().map(el -> asDimensionDto(el)).collect(Collectors.toList());
    }

    private DimensionDto asDimensionDto(Dimension input) {
        return new DimensionDto(input.getDimensionId(), input.getDimensionName(), input.getParentId(), input.getPosition());
    }
}
