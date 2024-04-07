package dev.task.producttesttask.controller.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class NewComputerPayload extends NewModelPayload {

    @Schema(description = "Категория компьютера", required = true)
    @NotNull(message = "Category cannot be null")
    private String category = "";

    @Schema(description = "Тип процессора", required = true)
    @NotNull(message = "Processor type cannot be null")
    private String processorType = "";
}
