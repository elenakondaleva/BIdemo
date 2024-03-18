package com.jedox.bidemo.application.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Raw Data of DataCube",
        description = "Represents raw data charachterized by all three dimensions.")
public record RawDataDto (@NotNull @Valid Long dimensionOneId, @NotNull @Valid Long dimensionTwoId,
                          @NotNull @Valid Long dimensionThreeId, double rawData) {
}
