package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.NewFilterSearch;
import dev.task.producttesttask.controller.payload.NewModelPayload;
import dev.task.producttesttask.entity.ModelEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ModelService {

    Iterable<ModelEntity> getAllModel(Long productId, NewFilterSearch filterSearch);

    Optional<ModelEntity> getModelById(Long modelId);

    ModelEntity createModel(Long productId, NewModelPayload modelPayload);

    void deleteModel(Long modelId);

    Iterable<ModelEntity> findAllSorted(String sortBy);
}
