package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.NewComputerPayload;
import dev.task.producttesttask.controller.payload.NewFilterSearch;
import dev.task.producttesttask.entity.ComputerModelEntity;
import dev.task.producttesttask.entity.DTO.ModelDto;
import dev.task.producttesttask.entity.ModelEntity;
import dev.task.producttesttask.entity.ProductEntity;
import dev.task.producttesttask.mapper.ProductMapper;
import dev.task.producttesttask.repository.ComputerRepository;
import dev.task.producttesttask.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComputerServiceImpl implements ComputerService {
    private final ComputerRepository computerRepository;
    private final ProductRepository productRepository;

    public ComputerServiceImpl(ComputerRepository computerRepository, ProductRepository productRepository) {
        this.computerRepository = computerRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<ModelDto> getAllModel(Long productId, NewFilterSearch filterSearch) {
        return null;
    }

    @Override
    public ModelDto getModelById(Long modelId) {
        Optional<ComputerModelEntity> model = this.computerRepository.findById(modelId);
        if (model.isPresent()) {
            ProductMapper mapper = new ProductMapper();
            return mapper.toModelDto(model.get());
        } else {
            throw new RuntimeException("Model not found");
        }
    }

    @Override
    public ModelEntity createModel(Long productId, NewComputerPayload modelPayload) {
        Optional<ProductEntity> product = this.productRepository.findById(productId);
        if (product.isPresent()) {
            ComputerModelEntity modelEntity = new ComputerModelEntity();
            modelEntity.setName(modelPayload.getName());
            modelEntity.setSerialNumber(modelPayload.getSerialNumber());
            modelEntity.setColor(modelPayload.getColor());
            modelEntity.setSize(modelPayload.getSize());
            modelEntity.setAvailable(modelPayload.isAvailable());
            modelEntity.setCategory(modelPayload.getCategory());
            modelEntity.setProcessorType(modelPayload.getProcessorType());
            modelEntity.setProduct(product.get());
            product.get().getComputerModels().add(modelEntity);
            return this.computerRepository.save(modelEntity);
        } else {
            throw new RuntimeException("Product Not Found");
        }

    }

    @Override
    public void deleteModel(Long modelId) {
        this.computerRepository.deleteById(modelId);
    }

    @Override
    public Iterable<ModelDto> findAllSorted(String sortBy) {
        return null;
    }
}
