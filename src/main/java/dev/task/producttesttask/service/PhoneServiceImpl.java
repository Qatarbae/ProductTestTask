package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.FilterPhoneModelSearch;
import dev.task.producttesttask.controller.payload.NewPhonePayload;
import dev.task.producttesttask.entity.DTO.ModelDto;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.entity.PhoneModelEntity;
import dev.task.producttesttask.entity.ProductEntity;
import dev.task.producttesttask.entity.ProductType;
import dev.task.producttesttask.mapper.ProductMapper;
import dev.task.producttesttask.repository.PhoneRepository;
import dev.task.producttesttask.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public PhoneServiceImpl(PhoneRepository phoneRepository, ProductRepository productRepository, ProductMapper productMapper) {
        this.phoneRepository = phoneRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Iterable<ModelDto> getAllModelsByTypeAndColorAndPriceRange(FilterPhoneModelSearch filterSearch) {
        return mapTvModelsToDto(phoneRepository.findTvModelsByTypeAndColorAndPriceRange(
                filterSearch.getType(),
                filterSearch.getModelName(),
                filterSearch.getColor(),
                filterSearch.getMinPrice(),
                filterSearch.getMaxPrice()));
    }

    @Override
    public Iterable<ModelDto> findAllModels(FilterPhoneModelSearch filterSearch) {
        return mapTvModelsToDto(phoneRepository.findAllModels(
                filterSearch.getType(),
                filterSearch.getModelName(),
                filterSearch.getColor(),
                filterSearch.getMinPrice(),
                filterSearch.getMaxPrice(),
                filterSearch.getCameraCount(),
                filterSearch.getMemory()));
    }

    @Override
    public ModelDto getModelById(Long modelId) {
        PhoneModelEntity model = phoneRepository.findById(modelId)
                .orElseThrow(() -> new RuntimeException("Model not found"));
        return productMapper.toModelDto(model);
    }

    @Override
    public ModelEntity createModel(Long productId, NewPhonePayload modelPayload) {
        ProductEntity product = productRepository.findById(productId)
                .filter(p -> p.getType() == ProductType.TV)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        PhoneModelEntity modelEntity = new PhoneModelEntity();
        modelEntity.setName(modelPayload.getName());
        modelEntity.setSerialNumber(modelPayload.getSerialNumber());
        modelEntity.setColor(modelPayload.getColor());
        modelEntity.setSize(modelPayload.getSize());
        modelEntity.setAvailable(modelPayload.isAvailable());
        modelEntity.setCameraCount(modelPayload.getMemory());
        modelEntity.setMemory(modelPayload.getMemory());
        modelEntity.setProduct(product);

        product.getPhoneModels().add(modelEntity);
        return phoneRepository.save(modelEntity);
    }

    @Override
    public void deleteModel(Long modelId) {
        this.phoneRepository.deleteById(modelId);
    }

    @Override
    public Iterable<ModelDto> findAllSorted(String sortBy) {
        Iterable<PhoneModelEntity> tvModels;
        switch (sortBy) {
            case "name":
                tvModels = phoneRepository.findAllByOrderByNameAsc();
                break;
            case "price":
                tvModels = phoneRepository.findAllByOrderByPriceAsc();
                break;
            case "name-price":
                tvModels = phoneRepository.findAllByOrderByNameAscPriceAsc();
                break;
            default:
                tvModels = phoneRepository.findAll();
        }
        return mapTvModelsToDto(tvModels);
    }

    private Iterable<ModelDto> mapTvModelsToDto(Iterable<PhoneModelEntity> tvModels) {
        return StreamSupport.stream(tvModels.spliterator(), false)
                .map(productMapper::toModelDto)
                .collect(Collectors.toList());
    }
}
