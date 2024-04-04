package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.NewFilterSearch;
import dev.task.producttesttask.controller.payload.NewModelPayload;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.entity.ProductEntity;
import dev.task.producttesttask.repository.ModelEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelEntityRepository modelEntityRepository;
    private final ProductService productService;

    public ModelServiceImpl(ModelEntityRepository modelEntityRepository, ProductService productService) {
        this.modelEntityRepository = modelEntityRepository;
        this.productService = productService;
    }

    @Override
    public Iterable<ModelEntity> getAllModel(Long productId, NewFilterSearch filterSearch) {
        return this.modelEntityRepository.findFilteredModels(
                productId,
                filterSearch.name(),
                filterSearch.color(),
                filterSearch.minPrice(),
                filterSearch.maxPrice()
        );
    }

    @Override
    public Optional<ModelEntity> getModelById(Long modelId) {
        return this.modelEntityRepository.findById(modelId);
    }

    @Override
    public ModelEntity createModel(Long productId, NewModelPayload modelPayload) {
        ProductEntity product = this.productService.getProductById(productId).orElseThrow(RuntimeException::new);
        ModelEntity model = new ModelEntity();

        model.setName(modelPayload.name());
        model.setSerialNumber(modelPayload.serialNumber());
        model.setColor(modelPayload.color());
        model.setSize(modelPayload.size());
        model.setPrice(modelPayload.price());
        model.setAvailable(model.isAvailable());
        model.setProduct(product);

        ModelEntity result = this.modelEntityRepository.save(model);
        product.getModels().add(result);
        return result;
    }

    @Override
    public void deleteModel(Long modelId) {
        this.modelEntityRepository.deleteById(modelId);
    }
}
