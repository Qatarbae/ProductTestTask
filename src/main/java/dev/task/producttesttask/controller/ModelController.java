package dev.task.producttesttask.controller;

import dev.task.producttesttask.controller.payload.NewFilterSearch;
import dev.task.producttesttask.controller.payload.NewModelPayload;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.service.ModelService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Objects;

@RestController
@RequestMapping("api/products/models/")
public class ModelController {

    private final MessageSource messageSource;
    private final ModelService modelService;

    public ModelController(MessageSource messageSource, ModelService modelService) {
        this.messageSource = messageSource;
        this.modelService = modelService;
    }

    @GetMapping("/")
    @Transactional
    public ResponseEntity<Iterable<ModelEntity>> getModels(
            @RequestParam(name = "productId", required = false) Long productId,
            @RequestBody NewFilterSearch filterSearch
    ) {
        return ResponseEntity.ok()
                .body(this.modelService.getAllModel(productId, filterSearch));
    }

    @GetMapping("/{modelId:\\d+}")
    @Transactional
    public ResponseEntity<?> getModelById(@PathVariable("modelId") Long modelId) {
        return ResponseEntity.ok()
                .body(
                        this.modelService.getModelById(modelId)
                );
    }


    @PostMapping("/{productId:\\d+}")
    @Transactional
    public ResponseEntity<?> createModel(@PathVariable("productId") Long productId,
                                         @RequestBody NewModelPayload payload) {
        ModelEntity modelEntity = this.modelService.createModel(productId, payload);
        return ResponseEntity.ok().body(modelEntity);
    }

    @DeleteMapping("/{modelId:\\d+}")
    public ResponseEntity<?> deleteModel(
            @PathVariable("modelId") Long modelId) {
        this.modelService.deleteModel(modelId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sort/")
    public ResponseEntity<Iterable<ModelEntity>> getSortedModels(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "price", required = false) String price) {
        if (!name.isEmpty() && !price.isEmpty()) {
            return ResponseEntity.ok().body(this.modelService.findAllSorted(name + "-" + price));
        }
        return ResponseEntity.ok().body(this.modelService.findAllSorted(name + "" + price));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ProblemDetail> exception(RuntimeException exception,
                                                   Locale locale) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                        Objects.requireNonNull(this.messageSource.getMessage(
                                exception.getMessage(),
                                new Object[0],
                                exception.getMessage(), locale))));
    }
}
