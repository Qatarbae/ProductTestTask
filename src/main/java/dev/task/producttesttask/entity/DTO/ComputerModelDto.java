package dev.task.producttesttask.entity.DTO;

import lombok.Data;

@Data
public class ComputerModelDto extends ModelDto {
    private String category;
    private String processorType;
}
