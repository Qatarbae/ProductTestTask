package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.FilterTvModelSearch;
import dev.task.producttesttask.controller.payload.NewTvModelPayload;
import dev.task.producttesttask.entity.DTO.ModelDto;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.entity.ProductEntity;
import dev.task.producttesttask.entity.ProductType;
import dev.task.producttesttask.entity.TvModelEntity;
import dev.task.producttesttask.mapper.ProductMapper;
import dev.task.producttesttask.repository.ProductRepository;
import dev.task.producttesttask.repository.TvRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TvModelServiceImpl implements TvModelService {

    private final TvRepository tvRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public TvModelServiceImpl(TvRepository tvRepository, ProductRepository productRepository, ProductMapper productMapper) {
        this.tvRepository = tvRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Iterable<ModelDto> getAllModelsByTypeAndColorAndPriceRange(FilterTvModelSearch filterSearch) {
        Iterable<TvModelEntity> model = tvRepository.findTvModelsByTypeAndColorAndPriceRange(
                filterSearch.getType(),
                filterSearch.getModelName(),
                filterSearch.getColor(),
                filterSearch.getMinPrice(),
                filterSearch.getMaxPrice());
        return mapTvModelsToDto(model);
    }

    @Override
    public Iterable<ModelDto> findAllModels(FilterTvModelSearch filterSearch) {
        return mapTvModelsToDto(tvRepository.findAllModels(
                filterSearch.getType(),
                filterSearch.getModelName(),
                filterSearch.getColor(),
                filterSearch.getMinPrice(),
                filterSearch.getMaxPrice(),
                filterSearch.getCategory(),
                filterSearch.getTechnology()));
    }

    @Override
    public ModelDto getModelById(Long modelId) {
        TvModelEntity model = tvRepository.findById(modelId)
                .orElseThrow(() -> new RuntimeException("Model not found"));
        return productMapper.toModelDto(model);
    }

    @Override
    public ModelEntity createModel(Long productId, NewTvModelPayload modelPayload) {
        ProductEntity product = productRepository.findById(productId)
                .filter(p -> p.getType() == ProductType.TV)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        TvModelEntity modelEntity = new TvModelEntity();
        modelEntity.setName(modelPayload.getName());
        modelEntity.setSerialNumber(modelPayload.getSerialNumber());
        modelEntity.setColor(modelPayload.getColor());
        modelEntity.setSize(modelPayload.getSize());
        modelEntity.setAvailable(modelPayload.isAvailable());
        modelEntity.setCategory(modelPayload.getCategory());
        modelEntity.setTechnology(modelPayload.getTechnology());
        modelEntity.setProduct(product);

        product.getTvModels().add(modelEntity);
        return tvRepository.save(modelEntity);
    }

    @Override
    public void deleteModel(Long modelId) {
        tvRepository.deleteById(modelId);
    }

    @Override
    public Iterable<ModelDto> findAllSorted(String sortBy) {
        Iterable<TvModelEntity> tvModels;
        switch (sortBy) {
            case "name":
                tvModels = tvRepository.findAllByOrderByNameAsc();
                break;
            case "price":
                tvModels = tvRepository.findAllByOrderByPriceAsc();
                break;
            case "name-price":
                tvModels = tvRepository.findAllByOrderByNameAscPriceAsc();
                break;
            default:
                tvModels = tvRepository.findAll();
        }
        return mapTvModelsToDto(tvModels);
    }

    private Iterable<ModelDto> mapTvModelsToDto(Iterable<TvModelEntity> tvModels) {
        return StreamSupport.stream(tvModels.spliterator(), false)
                .map(productMapper::toModelDto)
                .collect(Collectors.toList());
    }
}
