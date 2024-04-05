package dev.task.producttesttask.entity.DTO;

import lombok.Data;

@Data
public class RefrigeratorModelDto extends ModelDto {
    private int doorsCount;
    private String compressorType;
}
