package dev.task.producttesttask.controller.payload;

import lombok.Data;

@Data
public class NewPhonePayload extends NewModelPayload {
    private int memory;
    private int cameraCount;
}
