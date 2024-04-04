package dev.task.producttesttask.controller;

import dev.task.producttesttask.controller.payload.NewProductPayload;
import dev.task.producttesttask.entity.DTO.ProductDto;
import dev.task.producttesttask.entity.ProductEntity;
import dev.task.producttesttask.service.ProductService;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequestMapping("api/products/")
public class ProductController {

    private final MessageSource messageSource;
    private final ProductService productService;

    public ProductController(MessageSource messageSource, ProductService productService) {
        this.messageSource = messageSource;
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<ProductDto>> getProducts(
            @RequestParam(name = "filter", required = false) String filter
    ) {
        return ResponseEntity.ok().body(productService.getAllProducts(filter));
    }

    @GetMapping("{productId:\\d+}/product")
    public ResponseEntity<?> getProduct(@PathVariable("productId") Long produсtId) {
        return ResponseEntity.ok().body(this.productService.getProductById(produсtId)
                .orElseThrow(() -> new NoSuchElementException("Not Found Product")));
    }

    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody NewProductPayload payload) {
        ProductEntity product = this.productService.createProduct(payload);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("{productId:\\d+}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Integer productId) {
        this.productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
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
