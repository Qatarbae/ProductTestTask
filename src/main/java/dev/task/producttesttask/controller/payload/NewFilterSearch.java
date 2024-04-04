package dev.task.producttesttask.controller.payload;

public record NewFilterSearch(
        String name,
        String serialNumber,
        String color,
        String size,
        double minPrice,
        double maxPrice,
        boolean available
) {
}
