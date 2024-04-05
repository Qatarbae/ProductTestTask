package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.FilterProductSearch;
import dev.task.producttesttask.controller.payload.NewProductPayload;
import dev.task.producttesttask.entity.DTO.ProductDto;
import dev.task.producttesttask.entity.ProductEntity;
import dev.task.producttesttask.mapper.ProductMapper;
import dev.task.producttesttask.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Iterable<ProductDto> getAllProducts(String filter) {
        Iterable<ProductEntity> allProducts;
        if (filter != null && !filter.isBlank()) {
            allProducts = this.productRepository.findAllByNameLikeIgnoreCase("%" + filter + "%");
        } else {
            allProducts = this.productRepository.findAll();
        }
        allProducts.forEach(product -> {
            product.getTvModels().size();
            product.getVacuumCleanerModels().size();
            product.getRefrigeratorModels().size();
            product.getPhoneModels().size();
            product.getComputerModels().size();
        });

        return StreamSupport.stream(allProducts.spliterator(), false)
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<ProductDto> getAllProductsFilter(FilterProductSearch filter) {
        Iterable<ProductEntity> allProducts = this.productRepository.findAllWithFilters(
                filter.getType(),
                filter.getName(),
                filter.getManufacturerCountry(),
                filter.getManufacturer(),
                filter.getOnlineOrderAvailable(),
                filter.getInstallmentAvailable()
        );
        allProducts.forEach(product -> {
            product.getTvModels().size();
            product.getVacuumCleanerModels().size();
            product.getRefrigeratorModels().size();
            product.getPhoneModels().size();
            product.getComputerModels().size();
        });
        return StreamSupport.stream(allProducts.spliterator(), false)
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long productId) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productEntity.getTvModels().size();
        productEntity.getVacuumCleanerModels().size();
        productEntity.getRefrigeratorModels().size();
        productEntity.getPhoneModels().size();
        productEntity.getComputerModels().size();

        return productMapper.toDto(productEntity);
    }

    @Override
    public ProductEntity createProduct(NewProductPayload payload) {
        return this.productRepository.save(new ProductEntity(
                payload.type(),
                payload.name(),
                payload.manufacturerCountry(),
                payload.manufacturer(),
                payload.installmentAvailable(),
                payload.onlineOrderAvailable()
        ));
    }

    @Override
    public void deleteProduct(Long productId) {
        this.productRepository.deleteById(productId);
    }

}
