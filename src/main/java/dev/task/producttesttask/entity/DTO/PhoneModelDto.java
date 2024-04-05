package dev.task.producttesttask.entity.DTO;

import lombok.Data;

@Data
public class PhoneModelDto extends ModelDto {
    private int memory;
    private int cameraCount;
}
