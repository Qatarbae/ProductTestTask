package dev.task.producttesttask.controller.payload;

import lombok.Data;

@Data
public class FilterComputerModelSearch extends FilterModelSearch {
    private String category;
    private String processorType;
}
