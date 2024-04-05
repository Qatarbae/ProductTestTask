package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.NewFilterSearch;
import dev.task.producttesttask.controller.payload.NewRefrigeratorPayload;
import dev.task.producttesttask.entity.DTO.ModelDto;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.entity.RefrigeratorModelEntity;
import dev.task.producttesttask.mapper.ProductMapper;
import dev.task.producttesttask.repository.ProductRepository;
import dev.task.producttesttask.repository.RefrigeratorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RefrigeratorServiceImpl implements RefrigeratorService {

    private final RefrigeratorRepository refrigeratorRepository;
    private final ProductRepository productRepository;

    public RefrigeratorServiceImpl(RefrigeratorRepository refrigeratorRepository, ProductRepository productRepository) {
        this.refrigeratorRepository = refrigeratorRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<ModelDto> getAllModel(Long productId, NewFilterSearch filterSearch) {
        return null;
    }

    @Override
    public ModelDto getModelById(Long modelId) {
        Optional<RefrigeratorModelEntity> model = this.refrigeratorRepository.findById(modelId);
        if (model.isPresent()) {
            ProductMapper mapper = new ProductMapper();
            return mapper.toModelDto(model.get());
        } else {
            throw new RuntimeException("Model not found");
        }
    }

    @Override
    public ModelEntity createModel(Long productId, NewRefrigeratorPayload modelPayload) {
        RefrigeratorModelEntity modelEntity = new RefrigeratorModelEntity();
        modelEntity.setName(modelPayload.getName());
        modelEntity.setSerialNumber(modelPayload.getSerialNumber());
        modelEntity.setColor(modelPayload.getColor());
        modelEntity.setSize(modelPayload.getSize());
        modelEntity.setAvailable(modelPayload.isAvailable());
        modelEntity.setDoorsCount(modelPayload.getDoorsCount());
        modelEntity.setCompressorType(modelPayload.getCompressorType());
        return this.refrigeratorRepository.save(modelEntity);
    }

    @Override
    public void deleteModel(Long modelId) {
        this.refrigeratorRepository.deleteById(modelId);
    }

    @Override
    public Iterable<ModelDto> findAllSorted(String sortBy) {
        return null;
    }
}
