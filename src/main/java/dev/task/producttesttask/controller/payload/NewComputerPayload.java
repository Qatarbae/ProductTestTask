package dev.task.producttesttask.controller.payload;

import lombok.Data;

@Data
public class NewComputerPayload extends NewModelPayload {
    private String category;
    private String processorType;
}
