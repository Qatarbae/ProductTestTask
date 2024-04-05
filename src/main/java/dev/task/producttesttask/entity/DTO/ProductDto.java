package dev.task.producttesttask.entity.DTO;

import dev.task.producttesttask.entity.ProductType;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private ProductType type;
    private String manufacturerCountry;
    private String manufacturer;
    private boolean onlineOrderAvailable;
    private boolean installmentAvailable;
    private List<ModelDto> models;
}
