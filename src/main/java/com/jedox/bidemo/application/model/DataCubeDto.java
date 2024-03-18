package com.jedox.bidemo.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Schema(name = "DataCube Information",
        description = "Represents data of the cell in spreadsheet. It characterised by three dimensions.")
public record DataCubeDto (@NotNull @Valid DimensionDto dimensionOne, @NotNull @Valid DimensionDto dimensionTwo,
                          @NotNull @Valid DimensionDto dimensionThree, @NotNull @Valid double data) {
}
