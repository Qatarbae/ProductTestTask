package dev.task.producttesttask.controller;

import dev.task.producttesttask.controller.payload.FilterRefrigeratorSearch;
import dev.task.producttesttask.controller.payload.NewRefrigeratorPayload;
import dev.task.producttesttask.entity.DTO.ModelDto;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.service.RefrigeratorService;
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
@RequestMapping("api/products/models/refrigerator")
public class RefrigeratorController {

    private final RefrigeratorService refrigeratorService;

    public RefrigeratorController(RefrigeratorService refrigeratorService) {
        this.refrigeratorService = refrigeratorService;
    }

    @GetMapping("/search")
    @Transactional
    @Operation(summary = "Фильтрация модели",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    type = "object",
                                    implementation = FilterRefrigeratorSearch.class
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
                            description = "Product not found"
                    )
            }
    )
    public ResponseEntity<Iterable<ModelDto>> getModelsByTypeAndColorAndPriceRange(
            @Valid @RequestBody FilterRefrigeratorSearch filter,
            BindingResult bindingResult
    ) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Iterable<ModelDto> tvModelDto = refrigeratorService.getAllModelsByTypeAndColorAndPriceRange(filter);
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
                            description = "Model not found"
                    )
            }
    )
    public ResponseEntity<ModelDto> getModelById(@PathVariable Long modelId) {
        ModelDto tvModelDto = refrigeratorService.getModelById(modelId);
        return ResponseEntity.ok(tvModelDto);
    }

    @PostMapping("/")
    @Transactional
    @Operation(summary = "Создать модель Refrigerator",
            parameters = {
                    @Parameter(name = "productId", description = "ID товара", required = true)
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(
                                    type = "object",
                                    implementation = NewRefrigeratorPayload.class
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
                            description = "Model has not been created"
                    )
            })
    public ResponseEntity<ModelEntity> createTvModel(@RequestParam("productId") Long productId, @RequestBody NewRefrigeratorPayload tvModelPayload) {
        ModelEntity createdTvModel = refrigeratorService.createModel(productId, tvModelPayload);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTvModel);
    }

    @DeleteMapping("/{modelId}")
    @Transactional
    @Operation(summary = "Удаление модели Refrigerator",
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
        refrigeratorService.deleteModel(modelId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sorted")
    public ResponseEntity<Iterable<ModelDto>> findAllSorted(@RequestParam String sortBy) {
        Iterable<ModelDto> sortedTvModels = refrigeratorService.findAllSorted(sortBy);
        return ResponseEntity.ok(sortedTvModels);
    }
}
