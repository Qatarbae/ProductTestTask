package dev.task.producttesttask.controller.payload;

import dev.task.producttesttask.entity.ProductType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FilterProductSearch {

    @Schema(description = "Тип товара", required = true)
    @NotNull(message = "type cannot be null")
    private ProductType type = ProductType.DEFAULT_VALUE;

    @Schema(description = "Наименование товара", required = true)
    @NotNull(message = "Product name cannot be null")
    private String productName = "";

    @Schema(description = "Страна производитель", required = true)
    @NotNull(message = "Country cannot be null")
    private String manufacturerCountry = "";

    @Schema(description = "Производитель", required = true)
    @NotNull(message = "manufacturer cannot be null")
    private String manufacturer = "";

    @Schema(description = "Доступность для онлайн заказа", required = true)
    @NotNull(message = "online cannot be null")
    private Boolean onlineOrderAvailable = false;

    @Schema(description = "Доступность для рассрочки", required = true)
    @NotNull(message = "install cannot be null")
    private Boolean installmentAvailable = false;
}