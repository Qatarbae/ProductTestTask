package dev.task.producttesttask.controller;

import dev.task.producttesttask.controller.payload.FilterTvModelSearch;
import dev.task.producttesttask.controller.payload.NewTvModelPayload;
import dev.task.producttesttask.entity.DTO.ModelDto;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.service.TvModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/products/models/tv")
public class TvController {

    private final TvModelService tvModelService;

    public TvController(TvModelService tvModelService) {
        this.tvModelService = tvModelService;
    }

    @GetMapping("/search")
    @Transactional
    @Operation(summary = "Фильтрация модели",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    type = "object",
                                    implementation = FilterTvModelSearch.class
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Успешный ответ",
                            headers = @Header(name = "Content-type", description = "Тип данных"),
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            array = @ArraySchema(
                                                    schema = @Schema(
                                                            implementation = ModelDto.class
                                                    )
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Model not found"
                    )
            }
    )
    public ResponseEntity<Iterable<ModelDto>> getModelsByTypeAndColorAndPriceRange(
            @Valid @RequestBody FilterTvModelSearch filter,
            BindingResult bindingResult
    ) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Iterable<ModelDto> tvModelDto = tvModelService.getAllModelsByTypeAndColorAndPriceRange(filter);
            return ResponseEntity.ok(tvModelDto);
        }
    }

    @GetMapping("/{modelId}")
    @Transactional
    @Operation(summary = "Получение информации о модели по ID",
            parameters = {
                    @Parameter(name = "modelId", description = "ID модели", required = true)
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            headers = @Header(name = "Content-type", description = "Тип данных"),
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(
                                                    type = "object",
                                                    implementation = ModelDto.class
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Model not found",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(
                                                    type = "object",
                                                    implementation = Void.class
                                            )
                                    )
                            }
                    )
            }
    )
    public ResponseEntity<ModelDto> getModelById(@PathVariable Long modelId) {
        ModelDto tvModelDto = tvModelService.getModelById(modelId);
        return ResponseEntity.ok(tvModelDto);
    }

    @PostMapping("/")
    @Transactional
    @Operation(summary = "Создать модель TV",
            parameters = {
                    @Parameter(name = "productId", description = "ID товара", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    type = "object",
                                    implementation = NewTvModelPayload.class
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            headers = @Header(name = "Content-type", description = "Тип данных"),
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(
                                                    type = "object",
                                                    implementation = ModelEntity.class
                                            )
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Model has not been created",
                            content = {
                                    @Content(
                                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(
                                                    type = "object",
                                                    implementation = Void.class
                                            )
                                    )
                            }
                    )
            })
    public ResponseEntity<ModelEntity> createTvModel(@RequestParam("productId") Long productId, @RequestBody NewTvModelPayload tvModelPayload) {
        ModelEntity createdTvModel = tvModelService.createModel(productId, tvModelPayload);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTvModel);
    }

    @DeleteMapping("/{modelId}")
    @Transactional
    @Operation(summary = "Удаление модели TV",
            parameters = {
                    @Parameter(name = "modelId", description = "ID модели", required = true)
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Delete"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Model not found"
                    )
            }
    )
    public ResponseEntity<Void> deleteModel(@PathVariable Long modelId) {
        tvModelService.deleteModel(modelId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sorted")
    @Transactional
    public ResponseEntity<Iterable<ModelDto>> findAllSorted(@RequestParam String sortBy) {
        Iterable<ModelDto> sortedTvModels = tvModelService.findAllSorted(sortBy);
        return ResponseEntity.ok(sortedTvModels);
    }
}
