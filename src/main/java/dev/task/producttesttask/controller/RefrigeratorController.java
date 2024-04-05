package dev.task.producttesttask.controller;

import dev.task.producttesttask.controller.payload.FilterRefrigeratorSearch;
import dev.task.producttesttask.controller.payload.NewRefrigeratorPayload;
import dev.task.producttesttask.entity.DTO.ModelDto;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.service.RefrigeratorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products/models/refrigerator")
public class RefrigeratorController {

    private final RefrigeratorService refrigeratorService;

    public RefrigeratorController(RefrigeratorService refrigeratorService) {
        this.refrigeratorService = refrigeratorService;
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<ModelDto>> getModelsByTypeAndColorAndPriceRange(@RequestBody FilterRefrigeratorSearch filter) {
        Iterable<ModelDto> tvModelDto = refrigeratorService.getAllModelsByTypeAndColorAndPriceRange(filter);
        return ResponseEntity.ok(tvModelDto);
    }

    @GetMapping("/{modelId}")
    public ResponseEntity<ModelDto> getModelById(@PathVariable Long modelId) {
        ModelDto tvModelDto = refrigeratorService.getModelById(modelId);
        return ResponseEntity.ok(tvModelDto);
    }

    @PostMapping("/")
    public ResponseEntity<ModelEntity> createTvModel(@RequestParam("productId") Long productId, @RequestBody NewRefrigeratorPayload tvModelPayload) {
        ModelEntity createdTvModel = refrigeratorService.createModel(productId, tvModelPayload);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTvModel);
    }

    @DeleteMapping("/{modelId}")
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
