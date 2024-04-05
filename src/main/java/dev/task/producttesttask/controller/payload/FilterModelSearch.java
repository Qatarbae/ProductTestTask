package dev.task.producttesttask.controller.payload;

import lombok.Data;

@Data
public class FilterModelSearch extends FilterProductSearch {
    private String name;
    private String serialNumbr;
    private String color;
    private String size;
    private double minPrice;
    private double maxPrice;
    private boolean availabl;
}
