package dev.task.producttesttask.controller.payload;

import lombok.Data;

@Data
public class NewRefrigeratorPayload extends NewModelPayload {
    private int doorsCount;
    private String compressorType;
}
