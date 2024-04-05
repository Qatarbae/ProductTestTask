package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.FilterPhoneModelSearch;
import dev.task.producttesttask.controller.payload.NewPhonePayload;
import dev.task.producttesttask.entity.DTO.ModelDto;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.entity.PhoneModelEntity;
import dev.task.producttesttask.mapper.ProductMapper;
import dev.task.producttesttask.repository.PhoneRepository;
import dev.task.producttesttask.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;
    private final ProductRepository productRepository;

    public PhoneServiceImpl(PhoneRepository phoneRepository, ProductRepository productRepository) {
        this.phoneRepository = phoneRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<ModelDto> getAllModelsByTypeAndColorAndPriceRange(FilterPhoneModelSearch filterSearch) {
        return null;
    }

    @Override
    public Iterable<ModelDto> findAllModels(FilterPhoneModelSearch filterSearch) {
        return null;
    }

    @Override
    public ModelDto getModelById(Long modelId) {
        Optional<PhoneModelEntity> model = this.phoneRepository.findById(modelId);
        if (model.isPresent()) {
            ProductMapper mapper = new ProductMapper();
            return mapper.toModelDto(model.get());
        } else {
            throw new RuntimeException("Model not found");
        }
    }

    @Override
    public ModelEntity createModel(Long productId, NewPhonePayload modelPayload) {
        PhoneModelEntity modelEntity = new PhoneModelEntity();
        modelEntity.setName(modelPayload.getName());
        modelEntity.setSerialNumber(modelPayload.getSerialNumber());
        modelEntity.setColor(modelPayload.getColor());
        modelEntity.setSize(modelPayload.getSize());
        modelEntity.setAvailable(modelPayload.isAvailable());
        modelEntity.setCameraCount(modelPayload.getCameraCount());
        modelEntity.setMemory(modelPayload.getMemory());
        return this.phoneRepository.save(modelEntity);
    }

    @Override
    public void deleteModel(Long modelId) {
        this.phoneRepository.deleteById(modelId);
    }

    @Override
    public Iterable<ModelDto> findAllSorted(String sortBy) {
        return null;
    }
}
