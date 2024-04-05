package dev.task.producttesttask.controller.payload;

import lombok.Data;

@Data
public class NewVacuumCleanerModelPayload extends NewModelPayload {
    private double dustBagCapacity;
    private int modesCount;
}
