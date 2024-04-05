package dev.task.producttesttask.controller.payload;

import lombok.Data;

@Data
public class FilterRefrigeratorSearch extends FilterModelSearch {
    private int doorsCount;
    private String compressorType;
}
