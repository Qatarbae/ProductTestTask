package dev.task.producttesttask.controller.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class FilterPhoneModelSearch extends FilterModelSearch {

    @Schema(description = "Память", required = true)
    @NotNull(message = "Memory be null")
    @Min(value = 2, message = "minimum memory 2")
    private int memory;

    @Schema(description = "Количество камер", required = true)
    @NotNull(message = "Camera count cannot be null")
    @Min(value = 1, message = "minimum number camera 2")
    private int cameraCount;
}
