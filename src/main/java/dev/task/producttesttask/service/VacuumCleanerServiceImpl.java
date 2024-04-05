package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.FilterVacuumCleanerModelSearch;
import dev.task.producttesttask.controller.payload.NewVacuumCleanerModelPayload;
import dev.task.producttesttask.entity.DTO.ModelDto;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.entity.VacuumCleanerModelEntity;
import dev.task.producttesttask.mapper.ProductMapper;
import dev.task.producttesttask.repository.ProductRepository;
import dev.task.producttesttask.repository.VacuumCleanerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VacuumCleanerServiceImpl implements VacuumCleanerService {

    private final VacuumCleanerRepository vacuumCleanerRepository;
    private final ProductRepository productRepository;

    public VacuumCleanerServiceImpl(VacuumCleanerRepository vacuumCleanerRepository, ProductRepository productRepository) {
        this.vacuumCleanerRepository = vacuumCleanerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<ModelDto> getAllModelsByTypeAndColorAndPriceRange(FilterVacuumCleanerModelSearch filterSearch) {
        return null;
    }

    @Override
    public Iterable<ModelDto> findAllModels(FilterVacuumCleanerModelSearch filterSearch) {
        return null;
    }

    @Override
    public ModelDto getModelById(Long modelId) {
        Optional<VacuumCleanerModelEntity> model = this.vacuumCleanerRepository.findById(modelId);
        if (model.isPresent()) {
            ProductMapper mapper = new ProductMapper();
            return mapper.toModelDto(model.get());
        } else {
            throw new RuntimeException("Model not found");
        }
    }

    @Override
    public ModelEntity createModel(Long productId, NewVacuumCleanerModelPayload modelPayload) {
        VacuumCleanerModelEntity modelEntity = new VacuumCleanerModelEntity();
        modelEntity.setName(modelPayload.getName());
        modelEntity.setSerialNumber(modelPayload.getSerialNumber());
        modelEntity.setColor(modelPayload.getColor());
        modelEntity.setSize(modelPayload.getSize());
        modelEntity.setAvailable(modelPayload.isAvailable());
        modelEntity.setDustBagCapacity(modelPayload.getDustBagCapacity());
        modelEntity.setModesCount(modelPayload.getModesCount());
        return this.vacuumCleanerRepository.save(modelEntity);
    }

    @Override
    public void deleteModel(Long modelId) {
        this.vacuumCleanerRepository.deleteById(modelId);
    }

    @Override
    public Iterable<ModelDto> findAllSorted(String sortBy) {
        return null;
    }
}
