package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.NewProductPayload;
import dev.task.producttesttask.entity.DTO.ProductDto;
import dev.task.producttesttask.entity.ProductEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Iterable<ProductDto> getAllProducts(String filter);

    ProductDto getProductById(Long productId);

    ProductEntity createProduct(NewProductPayload product);

    void deleteProduct(Long productId);
}
