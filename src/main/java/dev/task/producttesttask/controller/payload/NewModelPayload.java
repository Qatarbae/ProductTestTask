package dev.task.producttesttask.controller.payload;

import lombok.Data;

@Data
public class NewModelPayload {
    private String name;
    private String serialNumber;
    private String color;
    private String size;
    private double price;
    private boolean available;
}
