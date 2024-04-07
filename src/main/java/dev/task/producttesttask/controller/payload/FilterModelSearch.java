package dev.task.producttesttask.controller.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class FilterModelSearch extends FilterProductSearch {

    @Schema(description = "Наименование модели", example = "Model X", required = true)
    @NotNull(message = "modelName cannot be null")
    private String modelName = "";

    @Schema(description = "Серийный номер модели", example = "12345", required = true)
    @NotNull(message = "serialNumber cannot be null")
    private String serialNumber = "";

    @Schema(description = "Цвет модели", example = "Red", required = true)
    @NotNull(message = "color cannot be null")
    private String color = "";

    @Schema(description = "Размер модели", example = "Large", required = true)
    @NotNull(message = "size cannot be null")
    private String size = "";

    @Schema(description = "Минимальная цена модели", example = "100", required = true)
    @Min(value = 0, message = "minimum minPrice 0")
    @NotNull(message = "minPrice cannot be null")
    private Integer minPrice = 0;

    @Schema(description = "Максимальная цена модели", example = "500", required = true)
    @Min(value = 0, message = "minimum maxPrice 0")
    @NotNull(message = "maxPrice cannot be null")
    private Integer maxPrice = -1;

    @Schema(description = "Доступность модели", example = "true", required = true)
    private boolean available = false;
}
