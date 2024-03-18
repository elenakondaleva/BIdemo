package com.jedox.bidemo.application.model;

import com.jedox.bidemo.domain.model.DimensionPosition;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Dimension Heirarchy",
        description = "Represents all three dimensions. The position characterises what dimension it is.")
public record DimensionDto(@NotNull @Valid Long dimensionId, @NotNull @Valid String dimensionName, Long parentId,
                           @NotNull @Valid DimensionPosition position) {
}
