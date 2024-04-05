package dev.task.producttesttask.controller.payload;

import dev.task.producttesttask.entity.ProductType;
import lombok.Data;

@Data
public class FilterProductSearch {
    private ProductType type = null;
    private String productName = null;
    private String manufacturerCountry = null;
    private String manufacturer = null;
    private Boolean onlineOrderAvailable = null;
    private Boolean installmentAvailable = null;

}