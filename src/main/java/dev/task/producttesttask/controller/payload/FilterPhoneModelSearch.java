package dev.task.producttesttask.controller.payload;

import lombok.Data;

@Data
public class FilterPhoneModelSearch extends FilterModelSearch {
    private int memory;
    private int cameraCount;
}
