package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.FilterVacuumCleanerModelSearch;
import dev.task.producttesttask.controller.payload.NewVacuumCleanerModelPayload;
import dev.task.producttesttask.entity.DTO.ModelDto;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.entity.ProductEntity;
import dev.task.producttesttask.entity.ProductType;
import dev.task.producttesttask.entity.VacuumCleanerModelEntity;
import dev.task.producttesttask.mapper.ProductMapper;
import dev.task.producttesttask.repository.ProductRepository;
import dev.task.producttesttask.repository.VacuumCleanerRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VacuumCleanerServiceImpl implements VacuumCleanerService {

    private final VacuumCleanerRepository vacuumCleanerRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public VacuumCleanerServiceImpl(VacuumCleanerRepository vacuumCleanerRepository, ProductRepository productRepository, ProductMapper productMapper) {
        this.vacuumCleanerRepository = vacuumCleanerRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Iterable<ModelDto> getAllModelsByTypeAndColorAndPriceRange(FilterVacuumCleanerModelSearch filterSearch) {
        return mapTvModelsToDto(vacuumCleanerRepository.findTvModelsByTypeAndColorAndPriceRange(
                filterSearch.getType(),
                filterSearch.getModelName(),
                filterSearch.getColor(),
                filterSearch.getMinPrice(),
                filterSearch.getMaxPrice()));
    }

    @Override
    public Iterable<ModelDto> findAllModels(FilterVacuumCleanerModelSearch filterSearch) {
        return mapTvModelsToDto(vacuumCleanerRepository.findAllModels(
                filterSearch.getType(),
                filterSearch.getModelName(),
                filterSearch.getColor(),
                filterSearch.getMinPrice(),
                filterSearch.getMaxPrice(),
                filterSearch.getDustBagCapacity(),
                filterSearch.getModesCount()));
    }

    @Override
    public ModelDto getModelById(Long modelId) {
        VacuumCleanerModelEntity model = vacuumCleanerRepository.findById(modelId)
                .orElseThrow(() -> new RuntimeException("Model not found"));
        return productMapper.toModelDto(model);
    }

    @Override
    public ModelEntity createModel(Long productId, NewVacuumCleanerModelPayload modelPayload) {
        ProductEntity product = productRepository.findById(productId)
                .filter(p -> p.getType() == ProductType.VACUUM_CLEANER)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        VacuumCleanerModelEntity modelEntity = new VacuumCleanerModelEntity();
        modelEntity.setName(modelPayload.getName());
        modelEntity.setSerialNumber(modelPayload.getSerialNumber());
        modelEntity.setColor(modelPayload.getColor());
        modelEntity.setSize(modelPayload.getSize());
        modelEntity.setAvailable(modelPayload.isAvailable());
        modelEntity.setDustBagCapacity(modelPayload.getDustBagCapacity());
        modelEntity.setModesCount(modelPayload.getModesCount());
        modelEntity.setProduct(product);
        modelEntity.setModelType(product.getType());
        product.getVacuumCleanerModels().add(modelEntity);
        return vacuumCleanerRepository.save(modelEntity);
    }

    @Override
    public void deleteModel(Long modelId) {
        this.vacuumCleanerRepository.deleteById(modelId);
    }

    @Override
    public Iterable<ModelDto> findAllSorted(String sortBy) {
        Iterable<VacuumCleanerModelEntity> tvModels;
        switch (sortBy) {
            case "name":
                tvModels = vacuumCleanerRepository.findAllByOrderByNameAsc();
                break;
            case "price":
                tvModels = vacuumCleanerRepository.findAllByOrderByPriceAsc();
                break;
            case "name-price":
                tvModels = vacuumCleanerRepository.findAllByOrderByNameAscPriceAsc();
                break;
            default:
                tvModels = vacuumCleanerRepository.findAll();
        }
        return mapTvModelsToDto(tvModels);
    }

    private Iterable<ModelDto> mapTvModelsToDto(Iterable<VacuumCleanerModelEntity> tvModels) {
        return StreamSupport.stream(tvModels.spliterator(), false)
                .map(productMapper::toModelDto)
                .collect(Collectors.toList());
    }
}
