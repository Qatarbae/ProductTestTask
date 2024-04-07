package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.FilterComputerModelSearch;
import dev.task.producttesttask.controller.payload.NewComputerPayload;
import dev.task.producttesttask.entity.ComputerModelEntity;
import dev.task.producttesttask.entity.DTO.ModelDto;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.entity.ProductEntity;
import dev.task.producttesttask.entity.ProductType;
import dev.task.producttesttask.mapper.ProductMapper;
import dev.task.producttesttask.repository.ComputerRepository;
import dev.task.producttesttask.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ComputerServiceImpl implements ComputerService {
    private final ComputerRepository computerRepository;
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ComputerServiceImpl(ComputerRepository computerRepository, ProductRepository productRepository, ProductMapper productMapper) {
        this.computerRepository = computerRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Iterable<ModelDto> getAllModelsByTypeAndColorAndPriceRange(FilterComputerModelSearch filterSearch) {
        return mapTvModelsToDto(computerRepository.findTvModelsByTypeAndColorAndPriceRange(
                filterSearch.getType(),
                filterSearch.getModelName(),
                filterSearch.getColor(),
                filterSearch.getMinPrice(),
                filterSearch.getMaxPrice()));
    }

    @Override
    public Iterable<ModelDto> findAllModels(FilterComputerModelSearch filterSearch) {
        return mapTvModelsToDto(computerRepository.findAllModels(
                filterSearch.getType(),
                filterSearch.getModelName(),
                filterSearch.getColor(),
                filterSearch.getMinPrice(),
                filterSearch.getMaxPrice(),
                filterSearch.getCategory(),
                filterSearch.getProcessorType()));
    }

    @Override
    public ModelDto getModelById(Long modelId) {
        ComputerModelEntity model = computerRepository.findById(modelId)
                .orElseThrow(() -> new RuntimeException("Model not found"));
        return productMapper.toModelDto(model);
    }

    @Override
    public ModelEntity createModel(Long productId, NewComputerPayload modelPayload) {
        ProductEntity product = productRepository.findById(productId)
                .filter(p -> p.getType() == ProductType.COMPUTER)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        ComputerModelEntity modelEntity = new ComputerModelEntity();
        modelEntity.setName(modelPayload.getName());
        modelEntity.setSerialNumber(modelPayload.getSerialNumber());
        modelEntity.setColor(modelPayload.getColor());
        modelEntity.setSize(modelPayload.getSize());
        modelEntity.setAvailable(modelPayload.isAvailable());
        modelEntity.setCategory(modelPayload.getCategory());
        modelEntity.setProcessorType(modelPayload.getProcessorType());
        modelEntity.setProduct(product);
        modelEntity.setModelType(product.getType());
        product.getComputerModels().add(modelEntity);
        return computerRepository.save(modelEntity);
    }

    @Override
    public void deleteModel(Long modelId) {
        this.computerRepository.deleteById(modelId);
    }

    @Override
    public Iterable<ModelDto> findAllSorted(String sortBy) {
        Iterable<ComputerModelEntity> tvModels;
        switch (sortBy) {
            case "name":
                tvModels = computerRepository.findAllByOrderByNameAsc();
                break;
            case "price":
                tvModels = computerRepository.findAllByOrderByPriceAsc();
                break;
            case "name-price":
                tvModels = computerRepository.findAllByOrderByNameAscPriceAsc();
                break;
            default:
                tvModels = computerRepository.findAll();
        }
        return mapTvModelsToDto(tvModels);
    }

    private Iterable<ModelDto> mapTvModelsToDto(Iterable<ComputerModelEntity> tvModels) {
        return StreamSupport.stream(tvModels.spliterator(), false)
                .map(productMapper::toModelDto)
                .collect(Collectors.toList());
    }
}
