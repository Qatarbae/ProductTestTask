package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.NewProductPayload;
import dev.task.producttesttask.entity.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProductService {

    Iterable<ProductEntity> getAllProducts(String filter);

    Optional<ProductEntity> getProductById(Long productId);

    ProductEntity createProduct(NewProductPayload product);

    void deleteProduct(Integer productId);
}
