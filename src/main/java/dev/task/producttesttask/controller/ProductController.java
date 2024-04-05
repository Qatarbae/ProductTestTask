package dev.task.producttesttask.controller;

import dev.task.producttesttask.controller.payload.FilterProductSearch;
import dev.task.producttesttask.controller.payload.NewProductPayload;
import dev.task.producttesttask.entity.DTO.ProductDto;
import dev.task.producttesttask.entity.ProductEntity;
import dev.task.producttesttask.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/products/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    @Transactional
    public ResponseEntity<Iterable<ProductDto>> getProducts(
            @RequestParam(name = "filter", required = false) String filter
    ) {
        return ResponseEntity.ok().body(productService.getAllProducts(filter));
    }

    @PostMapping("/filter")
    @Transactional
    public ResponseEntity<Iterable<ProductDto>> getProductsSearch(
            @RequestBody FilterProductSearch filter
    ) {
        return ResponseEntity.ok().body(productService.getAllProductsFilter(filter));
    }

    @GetMapping("{productId:\\d+}/product")
    @Transactional
    public ResponseEntity<ProductDto> getProduct(@PathVariable("productId") Long produсtId) {
        return ResponseEntity.ok().body(this.productService.getProductById(produсtId));
    }

    @PostMapping("")
    @Transactional
    public ResponseEntity<?> createProduct(@RequestBody NewProductPayload payload) {
        ProductEntity product = this.productService.createProduct(payload);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("{productId:\\d+}")
    @Transactional
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId) {
        this.productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }


}
