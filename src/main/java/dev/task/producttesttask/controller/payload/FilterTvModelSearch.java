package dev.task.producttesttask.controller.payload;

import lombok.Data;

@Data
public class FilterTvModelSearch extends FilterModelSearch {
    private String category;
    private String technology;
}
