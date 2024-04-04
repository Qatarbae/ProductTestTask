package dev.task.producttesttask.entity.DTO;

import lombok.Data;

@Data
public class ModelDto {
    private Long id;
    private String name;
    private String serialNumber;
    private String color;
    private String size;
    private double price;
    private boolean available;
}
