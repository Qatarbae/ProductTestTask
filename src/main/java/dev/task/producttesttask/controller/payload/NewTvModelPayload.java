package dev.task.producttesttask.controller.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NewTvModelPayload extends NewModelPayload {

    @Schema(description = "Категория", required = true)
    @NotNull(message = "Category cannot be null")
    private String category = "";

    @Schema(description = "Технология", required = true)
    @NotNull(message = "Technology cannot be null")
    private String technology = "";
}
