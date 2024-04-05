package dev.task.producttesttask.controller;

import dev.task.producttesttask.controller.payload.FilterPhoneModelSearch;
import dev.task.producttesttask.controller.payload.NewPhonePayload;
import dev.task.producttesttask.entity.DTO.ModelDto;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.service.PhoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products/models/phone")
public class PhoneController {

    private final PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<ModelDto>> getModelsByTypeAndColorAndPriceRange(@RequestBody FilterPhoneModelSearch filter) {
        Iterable<ModelDto> tvModelDto = phoneService.getAllModelsByTypeAndColorAndPriceRange(filter);
        return ResponseEntity.ok(tvModelDto);
    }

    @GetMapping("/{modelId}")
    public ResponseEntity<ModelDto> getModelById(@PathVariable Long modelId) {
        ModelDto tvModelDto = phoneService.getModelById(modelId);
        return ResponseEntity.ok(tvModelDto);
    }

    @PostMapping("/")
    public ResponseEntity<ModelEntity> createTvModel(@RequestParam("productId") Long productId, @RequestBody NewPhonePayload tvModelPayload) {
        ModelEntity createdTvModel = phoneService.createModel(productId, tvModelPayload);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTvModel);
    }

    @DeleteMapping("/{modelId}")
    public ResponseEntity<Void> deleteModel(@PathVariable Long modelId) {
        phoneService.deleteModel(modelId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sorted")
    public ResponseEntity<Iterable<ModelDto>> findAllSorted(@RequestParam String sortBy) {
        Iterable<ModelDto> sortedTvModels = phoneService.findAllSorted(sortBy);
        return ResponseEntity.ok(sortedTvModels);
    }
}
