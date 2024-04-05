package dev.task.producttesttask.controller.payload;

import dev.task.producttesttask.entity.ProductType;

public record NewProductPayload(

        ProductType type,
        String name,
        String manufacturerCountry,
        String manufacturer,
        boolean onlineOrderAvailable,
        boolean installmentAvailable
) {
}
