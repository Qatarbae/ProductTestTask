package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.NewFilterSearch;
import dev.task.producttesttask.controller.payload.NewModelPayload;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.entity.ProductEntity;
import dev.task.producttesttask.repository.ModelEntityRepository;
import dev.task.producttesttask.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelEntityRepository modelEntityRepository;
    private final ProductRepository productRepository;

    public ModelServiceImpl(ModelEntityRepository modelEntityRepository, ProductRepository productRepository) {
        this.modelEntityRepository = modelEntityRepository;
        this.productRepository = productRepository;
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
        Optional<ModelEntity> model = this.modelEntityRepository.findById(modelId);
        if (model.isPresent()) {
            return model;
        } else {
            throw new RuntimeException("Model not found");
        }
    }

    @Override
    public ModelEntity createModel(Long productId, NewModelPayload modelPayload) {
        ProductEntity product = this.productRepository
                .findById(productId).orElseThrow(RuntimeException::new);

        ModelEntity result = this.modelEntityRepository
                .save(mapModelPayloadToEntity(modelPayload, product));

        product.getModels().add(result);
        return result;
    }

    @Override
    public void deleteModel(Long modelId) {
        this.modelEntityRepository.deleteById(modelId);
    }

    @Override
    public Iterable<ModelEntity> findAllSorted(String sortBy) {
        List<ModelEntity> result;
        switch (sortBy) {
            case "name":
                result = modelEntityRepository.findAllSortedByName();
                break;
            case "price":
                result = modelEntityRepository.findAllSortedByPrice();
                break;
            case "name-price":
                result = modelEntityRepository.findAllSortedByNameAndPrice();
                break;
            default:
                throw new IllegalArgumentException("Invalid sort by parameter: " + sortBy);
        }
        return result;
    }

    private ModelEntity mapModelPayloadToEntity(NewModelPayload modelPayload, ProductEntity product) {
        ModelEntity model = new ModelEntity();
        model.setName(modelPayload.name());
        model.setSerialNumber(modelPayload.serialNumber());
        model.setColor(modelPayload.color());
        model.setSize(modelPayload.size());
        model.setPrice(modelPayload.price());
        model.setAvailable(modelPayload.available());
        model.setProduct(product);
        return model;
    }
}
