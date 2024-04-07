package dev.task.producttesttask.controller.payload;

import dev.task.producttesttask.entity.ProductType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NewProductPayload {

    @Schema(description = "Тип продукта", required = true)
    @NotNull(message = "Type cannot be null")
    private ProductType type = ProductType.DEFAULT_VALUE;

    @Schema(description = "Наименование продукта", required = true)
    @NotNull(message = "Name cannot be null")
    private String name = "";

    @Schema(description = "Страна производителя", required = true)
    @NotNull(message = "Manufacturer country cannot be null")
    private String manufacturerCountry = "";

    @Schema(description = "Производитель", required = true)
    @NotNull(message = "Manufacturer cannot be null")
    private String manufacturer = "";

    @Schema(description = "Доступность онлайн заказа", required = true)
    private boolean onlineOrderAvailable = false;

    @Schema(description = "Доступность рассрочки", required = true)
    private boolean installmentAvailable = false;
}
