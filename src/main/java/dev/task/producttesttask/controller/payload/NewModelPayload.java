package dev.task.producttesttask.controller.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class NewModelPayload {

    @Schema(description = "Наименование модели", required = true)
    @NotNull(message = "Name cannot be null")
    private String name = "";

    @Schema(description = "Серийный номер", required = true)
    @NotNull(message = "Serial number cannot be null")
    private String serialNumber = "";

    @Schema(description = "Цвет модели", required = true)
    @NotNull(message = "Color cannot be null")
    private String color = "";

    @Schema(description = "Размер модели", required = true)
    @NotNull(message = "Size cannot be null")
    private String size = "";

    @Schema(description = "Цена модели", required = true)
    @NotNull(message = "Price cannot be null")
    @Min(value = 1, message = "Price must be at least 0")
    private double price = 1;

    @Schema(description = "Доступность модели", required = true)
    @NotNull(message = "Availability cannot be null")
    private boolean available = false;
}
