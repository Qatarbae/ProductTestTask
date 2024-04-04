package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.NewProductPayload;
import dev.task.producttesttask.entity.ProductEntity;
import dev.task.producttesttask.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<ProductEntity> getAllProducts(String filter) {
        if (filter != null && !filter.isBlank()) {
            return this.productRepository.findAllByNameLikeIgnoreCase("%" + filter + "%");
        } else {
            return this.productRepository.findAll();
        }
    }

    @Override
    public Optional<ProductEntity> getProductById(Long productId) {
        return this.productRepository.findById(productId);
    }

    @Override
    public ProductEntity createProduct(NewProductPayload payload) {
        return this.productRepository.save(new ProductEntity(
                payload.name(),
                payload.manufacturerCountry(),
                payload.manufacturer(),
                payload.installmentAvailable(),
                payload.onlineOrderAvailable()
        ));
    }

    @Override
    public void deleteProduct(Integer productId) {

    }
}
