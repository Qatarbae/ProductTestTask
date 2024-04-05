package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.NewFilterSearch;
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

import java.util.Optional;

@Service
public class TvModelServiceImpl implements TvModelService {

    private final TvRepository tvRepository;
    private final ProductRepository productRepository;

    public TvModelServiceImpl(TvRepository tvRepository, ProductRepository productRepository) {
        this.tvRepository = tvRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<ModelDto> getAllModel(Long productId, NewFilterSearch filterSearch) {
        return null;
    }

    @Override
    public ModelDto getModelById(Long modelId) {
        Optional<TvModelEntity> model = this.tvRepository.findById(modelId);
        if (model.isPresent()) {
            ProductMapper mapper = new ProductMapper();
            return mapper.toModelDto(model.get());
        } else {
            throw new RuntimeException("Model not found");
        }
    }

    @Override
    public ModelEntity createModel(Long productId, NewTvModelPayload modelPayload) {
        Optional<ProductEntity> product = this.productRepository.findById(productId);
        if (product.isPresent() && product.get().getType() == ProductType.TV) {
            TvModelEntity modelEntity = new TvModelEntity();
            modelEntity.setName(modelPayload.getName());
            modelEntity.setSerialNumber(modelPayload.getSerialNumber());
            modelEntity.setColor(modelPayload.getColor());
            modelEntity.setSize(modelPayload.getSize());
            modelEntity.setAvailable(modelPayload.isAvailable());
            modelEntity.setCategory(modelPayload.getCategory());
            modelEntity.setTechnology(modelPayload.getTechnology());
            modelEntity.setProduct(product.get());
            product.get().getTvModels().add(modelEntity);
            return this.tvRepository.save(modelEntity);
        } else {
            throw new RuntimeException("Product Not Found");
        }
    }

    @Override
    public void deleteModel(Long modelId) {
        this.tvRepository.deleteById(modelId);
    }

    @Override
    public Iterable<ModelDto> findAllSorted(String sortBy) {
        return null;
    }
}
