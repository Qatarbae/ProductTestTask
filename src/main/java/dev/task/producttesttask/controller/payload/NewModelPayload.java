package dev.task.producttesttask.controller.payload;

public record NewModelPayload(
        String name,
        String serialNumber,
        String color,
        String size,
        double price,
        boolean available
) {
}
