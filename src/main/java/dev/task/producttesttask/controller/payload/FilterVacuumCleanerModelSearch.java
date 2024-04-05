package dev.task.producttesttask.controller.payload;

import lombok.Data;

@Data
public class FilterVacuumCleanerModelSearch extends FilterModelSearch {
    private double dustBagCapacity;
    private int modesCount;
}
