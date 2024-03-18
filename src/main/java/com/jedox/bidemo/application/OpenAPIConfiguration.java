package com.jedox.bidemo.application;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(title = "BI application API", version = "1.0"),
        tags = {
                @Tag(name = "Dimension", description = "RESTful resources responsible for dimensions"),
                @Tag(name = "Datacube", description = "RESTful resources responsible for data")
        })
public class OpenAPIConfiguration {
}
