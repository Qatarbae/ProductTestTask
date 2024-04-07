package dev.task.producttesttask.controller.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FilterComputerModelSearch extends FilterModelSearch {

    @Schema(description = "Категория", required = true)
    @NotNull(message = "Category cannot be null")
    private String category = "";

    @Schema(description = "Тип процессора", required = true)
    @NotNull(message = "Processor Type cannot be null")
    private String processorType = "";
}
