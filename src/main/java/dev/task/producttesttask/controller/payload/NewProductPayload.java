package dev.task.producttesttask.controller.payload;

public record NewProductPayload(
        String name,
        String manufacturerCountry,
        String manufacturer,
        boolean onlineOrderAvailable,
        boolean installmentAvailable
) {
}
