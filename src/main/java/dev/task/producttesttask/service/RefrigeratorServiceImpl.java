package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.FilterRefrigeratorSearch;
import dev.task.producttesttask.controller.payload.NewRefrigeratorPayload;
import dev.task.producttesttask.entity.DTO.ModelDto;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.entity.ProductEntity;
import dev.task.producttesttask.entity.ProductType;
import dev.task.producttesttask.entity.RefrigeratorModelEntity;
import dev.task.producttesttask.mapper.ProductMapper;
import dev.task.producttesttask.repository.ProductRepository;
import dev.task.producttesttask.repository.RefrigeratorRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RefrigeratorServiceImpl implements RefrigeratorService {

    private final RefrigeratorRepository refrigeratorRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public RefrigeratorServiceImpl(RefrigeratorRepository refrigeratorRepository, ProductRepository productRepository, ProductMapper productMapper) {
        this.refrigeratorRepository = refrigeratorRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Iterable<ModelDto> getAllModelsByTypeAndColorAndPriceRange(FilterRefrigeratorSearch filterSearch) {
        return mapTvModelsToDto(refrigeratorRepository.findTvModelsByTypeAndColorAndPriceRange(
                filterSearch.getType(),
                filterSearch.getModelName(),
                filterSearch.getColor(),
                filterSearch.getMinPrice(),
                filterSearch.getMaxPrice()));
    }

    @Override
    public Iterable<ModelDto> findAllModels(FilterRefrigeratorSearch filterSearch) {
        return mapTvModelsToDto(refrigeratorRepository.findAllModels(
                filterSearch.getType(),
                filterSearch.getModelName(),
                filterSearch.getColor(),
                filterSearch.getMinPrice(),
                filterSearch.getMaxPrice(),
                filterSearch.getDoorsCount(),
                filterSearch.getCompressorType()));
    }

    @Override
    public ModelDto getModelById(Long modelId) {
        RefrigeratorModelEntity model = refrigeratorRepository.findById(modelId)
                .orElseThrow(() -> new RuntimeException("Model not found"));
        return productMapper.toModelDto(model);
    }

    @Override
    public ModelEntity createModel(Long productId, NewRefrigeratorPayload modelPayload) {
        ProductEntity product = productRepository.findById(productId)
                .filter(p -> p.getType() == ProductType.REFRIGERATOR)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        RefrigeratorModelEntity modelEntity = new RefrigeratorModelEntity();
        modelEntity.setName(modelPayload.getName());
        modelEntity.setSerialNumber(modelPayload.getSerialNumber());
        modelEntity.setColor(modelPayload.getColor());
        modelEntity.setSize(modelPayload.getSize());
        modelEntity.setAvailable(modelPayload.isAvailable());
        modelEntity.setDoorsCount(modelPayload.getDoorsCount());
        modelEntity.setCompressorType(modelPayload.getCompressorType());
        modelEntity.setProduct(product);
        modelEntity.setModelType(product.getType());
        product.getRefrigeratorModels().add(modelEntity);
        return refrigeratorRepository.save(modelEntity);
    }

    @Override
    public void deleteModel(Long modelId) {
        this.refrigeratorRepository.deleteById(modelId);
    }

    @Override
    public Iterable<ModelDto> findAllSorted(String sortBy) {
        Iterable<RefrigeratorModelEntity> tvModels;
        switch (sortBy) {
            case "name":
                tvModels = refrigeratorRepository.findAllByOrderByNameAsc();
                break;
            case "price":
                tvModels = refrigeratorRepository.findAllByOrderByPriceAsc();
                break;
            case "name-price":
                tvModels = refrigeratorRepository.findAllByOrderByNameAscPriceAsc();
                break;
            default:
                tvModels = refrigeratorRepository.findAll();
        }
        return mapTvModelsToDto(tvModels);
    }

    private Iterable<ModelDto> mapTvModelsToDto(Iterable<RefrigeratorModelEntity> tvModels) {
        return StreamSupport.stream(tvModels.spliterator(), false)
                .map(productMapper::toModelDto)
                .collect(Collectors.toList());
    }
}
