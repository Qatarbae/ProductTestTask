package dev.task.producttesttask.controller;

import dev.task.producttesttask.controller.payload.FilterTvModelSearch;
import dev.task.producttesttask.controller.payload.NewTvModelPayload;
import dev.task.producttesttask.entity.DTO.ModelDto;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.service.TvModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products/models/tv")
public class TvController {

    private final TvModelService tvModelService;

    public TvController(TvModelService tvModelService) {
        this.tvModelService = tvModelService;
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<ModelDto>> getModelsByTypeAndColorAndPriceRange(@RequestBody FilterTvModelSearch filter) {
        Iterable<ModelDto> tvModelDto = tvModelService.getAllModelsByTypeAndColorAndPriceRange(filter);
        return ResponseEntity.ok(tvModelDto);
    }

    @GetMapping("/{modelId}")
    public ResponseEntity<ModelDto> getModelById(@PathVariable Long modelId) {
        ModelDto tvModelDto = tvModelService.getModelById(modelId);
        return ResponseEntity.ok(tvModelDto);
    }

    @PostMapping("/")
    public ResponseEntity<ModelEntity> createTvModel(@RequestParam("productId") Long productId, @RequestBody NewTvModelPayload tvModelPayload) {
        ModelEntity createdTvModel = tvModelService.createModel(productId, tvModelPayload);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTvModel);
    }

    @DeleteMapping("/{modelId}")
    public ResponseEntity<Void> deleteModel(@PathVariable Long modelId) {
        tvModelService.deleteModel(modelId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sorted")
    public ResponseEntity<Iterable<ModelDto>> findAllSorted(@RequestParam String sortBy) {
        Iterable<ModelDto> sortedTvModels = tvModelService.findAllSorted(sortBy);
        return ResponseEntity.ok(sortedTvModels);
    }
}
