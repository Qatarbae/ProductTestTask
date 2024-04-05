package dev.task.producttesttask.entity.DTO;

import lombok.Data;

@Data
public class VacuumCleanerModelDto extends ModelDto {
    private double dustBagCapacity;
    private int modesCount;
}
