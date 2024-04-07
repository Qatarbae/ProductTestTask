package dev.task.producttesttask.controller.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class NewVacuumCleanerModelPayload extends NewModelPayload {

    @Schema(description = "Вместимость пылесборника", required = true)
    @NotNull(message = "Dust bag capacity cannot be null")
    @Min(value = 1, message = "min 1")
    private double dustBagCapacity = 1;

    @Schema(description = "Количество режимов", required = true)
    @NotNull(message = "Modes count cannot be null")
    @Min(value = 1, message = "min 1")
    private int modesCount = 1;
}
