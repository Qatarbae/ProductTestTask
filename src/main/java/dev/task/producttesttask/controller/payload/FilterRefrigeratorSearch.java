package dev.task.producttesttask.controller.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class FilterRefrigeratorSearch extends FilterModelSearch {

    @Schema(description = "Количество дверей", required = true)
    @NotNull(message = "message cannot be null")
    @Min(value = 1, message = "minimum number door 1")
    private int doorsCount = 1;

    @Schema(description = "Тип компрессора", required = true)
    @NotNull(message = "Compressor type cannot be null")
    private String compressorType = "";
}
