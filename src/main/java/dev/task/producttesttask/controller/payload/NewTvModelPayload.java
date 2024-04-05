package dev.task.producttesttask.controller.payload;

import lombok.Data;

@Data
public class NewTvModelPayload extends NewModelPayload {
    private String category;
    private String technology;
}
