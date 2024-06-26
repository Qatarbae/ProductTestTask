package dev.task.producttesttask.controller.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class FilterVacuumCleanerModelSearch extends FilterModelSearch {

    @Schema(description = "Вместимость пылесборника", required = true)
    @NotNull(message = "Dust bag capacity be null")
    @Min(value = 1, message = "minimum dust bag capacity 1")
    private double dustBagCapacity = 1;

    @Schema(description = "Количество режимов", required = true)
    @NotNull(message = "Modes count be null")
    @Min(value = 1, message = "minimum number modes 1")
    private int modesCount = 1;
}
