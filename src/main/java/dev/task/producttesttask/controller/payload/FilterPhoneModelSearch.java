package dev.task.producttesttask.controller.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class FilterPhoneModelSearch extends FilterModelSearch {

    @Schema(description = "Память", required = true)
    @NotNull(message = "Memory be null")
    @Min(value = 2, message = "min 2")
    private int memory = 2;

    @Schema(description = "Количество камер", required = true)
    @NotNull(message = "Camera count cannot be null")
    @Min(value = 0, message = "min 0")
    private int cameraCount = 0;
}
