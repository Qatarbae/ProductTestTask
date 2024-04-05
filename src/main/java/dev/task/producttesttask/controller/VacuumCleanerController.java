package dev.task.producttesttask.controller;

import dev.task.producttesttask.controller.payload.FilterVacuumCleanerModelSearch;
import dev.task.producttesttask.controller.payload.NewVacuumCleanerModelPayload;
import dev.task.producttesttask.entity.DTO.ModelDto;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.service.VacuumCleanerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products/models/vacuumCleaner")
public class VacuumCleanerController {

    private final VacuumCleanerService vacuumCleanerService;

    public VacuumCleanerController(VacuumCleanerService vacuumCleanerService) {
        this.vacuumCleanerService = vacuumCleanerService;
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<ModelDto>> getModelsByTypeAndColorAndPriceRange(@RequestBody FilterVacuumCleanerModelSearch filter) {
        Iterable<ModelDto> tvModelDto = vacuumCleanerService.getAllModelsByTypeAndColorAndPriceRange(filter);
        return ResponseEntity.ok(tvModelDto);
    }

    @GetMapping("/{modelId}")
    public ResponseEntity<ModelDto> getModelById(@PathVariable Long modelId) {
        ModelDto tvModelDto = vacuumCleanerService.getModelById(modelId);
        return ResponseEntity.ok(tvModelDto);
    }

    @PostMapping("/")
    public ResponseEntity<ModelEntity> createTvModel(@RequestParam("productId") Long productId, @RequestBody NewVacuumCleanerModelPayload tvModelPayload) {
        ModelEntity createdTvModel = vacuumCleanerService.createModel(productId, tvModelPayload);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTvModel);
    }

    @DeleteMapping("/{modelId}")
    public ResponseEntity<Void> deleteModel(@PathVariable Long modelId) {
        vacuumCleanerService.deleteModel(modelId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sorted")
    public ResponseEntity<Iterable<ModelDto>> findAllSorted(@RequestParam String sortBy) {
        Iterable<ModelDto> sortedTvModels = vacuumCleanerService.findAllSorted(sortBy);
        return ResponseEntity.ok(sortedTvModels);
    }
}
