package dev.task.producttesttask.controller;

import dev.task.producttesttask.controller.payload.FilterComputerModelSearch;
import dev.task.producttesttask.controller.payload.NewComputerPayload;
import dev.task.producttesttask.entity.DTO.ModelDto;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.service.ComputerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products/models/computer")
public class ComputerController {

    private final ComputerService computerService;

    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<ModelDto>> getModelsByTypeAndColorAndPriceRange(@RequestBody FilterComputerModelSearch filter) {
        Iterable<ModelDto> tvModelDto = computerService.getAllModelsByTypeAndColorAndPriceRange(filter);
        return ResponseEntity.ok(tvModelDto);
    }

    @GetMapping("/{modelId}")
    public ResponseEntity<ModelDto> getModelById(@PathVariable Long modelId) {
        ModelDto tvModelDto = computerService.getModelById(modelId);
        return ResponseEntity.ok(tvModelDto);
    }

    @PostMapping("/")
    public ResponseEntity<ModelEntity> createTvModel(@RequestParam("productId") Long productId, @RequestBody NewComputerPayload tvModelPayload) {
        ModelEntity createdTvModel = computerService.createModel(productId, tvModelPayload);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTvModel);
    }

    @DeleteMapping("/{modelId}")
    public ResponseEntity<Void> deleteModel(@PathVariable Long modelId) {
        computerService.deleteModel(modelId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sorted")
    public ResponseEntity<Iterable<ModelDto>> findAllSorted(@RequestParam String sortBy) {
        Iterable<ModelDto> sortedTvModels = computerService.findAllSorted(sortBy);
        return ResponseEntity.ok(sortedTvModels);
    }
}
