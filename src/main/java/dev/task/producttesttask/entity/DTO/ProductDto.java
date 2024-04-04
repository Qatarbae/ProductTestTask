package dev.task.producttesttask.entity.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String manufacturerCountry;
    private String manufacturer;
    private boolean onlineOrderAvailable;
    private boolean installmentAvailable;
    private List<ModelDto> models;
}
