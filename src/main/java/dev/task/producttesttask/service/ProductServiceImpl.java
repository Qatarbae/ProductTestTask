package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.NewProductPayload;
import dev.task.producttesttask.entity.DTO.ModelDto;
import dev.task.producttesttask.entity.DTO.ProductDto;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.entity.ProductEntity;
import dev.task.producttesttask.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Iterable<ProductDto> getAllProducts(String filter) {
        Iterable<ProductEntity> allProducts;
        if (filter != null && !filter.isBlank()) {
            allProducts = this.productRepository.findAllByNameLikeIgnoreCase("%" + filter + "%");
        } else {
            allProducts = this.productRepository.findAll();
        }
        allProducts.forEach(product -> product.getModels().size());
        Iterable<ProductDto> productDTOList = mapProductEntitiesToDTOs(allProducts);
        return productDTOList;
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

    public Iterable<ProductDto> mapProductEntitiesToDTOs(Iterable<ProductEntity> productEntities) {
        List<ProductDto> productDTOList = new ArrayList<>();
        productEntities.forEach(productEntity -> {
            ProductDto productDTO = mapProductEntityToDTO(productEntity);
            productDTOList.add(productDTO);
        });
        return productDTOList;
    }

    private ProductDto mapProductEntityToDTO(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        productDto.setId(productEntity.getId());
        productDto.setName(productEntity.getName());
        productDto.setManufacturerCountry(productDto.getManufacturerCountry());
        productDto.setManufacturer(productDto.getManufacturer());
        productDto.setInstallmentAvailable(productEntity.isInstallmentAvailable());
        productDto.setOnlineOrderAvailable(productDto.isOnlineOrderAvailable());
        List<ModelDto> modelDTOList = mapModelEntitiesToDTOs(productEntity.getModels());
        productDto.setModels(modelDTOList);

        return productDto;
    }

    private List<ModelDto> mapModelEntitiesToDTOs(List<ModelEntity> modelEntities) {
        List<ModelDto> modelDtoList = new ArrayList<>();
        modelEntities.forEach(modelEntity -> {
            ModelDto modelDto = new ModelDto();
            modelDto.setId(modelEntity.getId());
            modelDto.setName(modelEntity.getName());
            modelDto.setSerialNumber(modelEntity.getSerialNumber());
            modelDto.setColor(modelDto.getColor());
            modelDto.setSize(modelDto.getSize());
            modelDto.setPrice(modelDto.getPrice());
            modelDto.setAvailable(modelDto.isAvailable());
            modelDtoList.add(modelDto);
        });
        return modelDtoList;
    }
}
