package dev.task.producttesttask.service;

import dev.task.producttesttask.entity.DTO.ModelDto;
import dev.task.producttesttask.entity.ModelEntity;
import org.springframework.stereotype.Service;

@Service
public interface ModelService<T, S> {

    Iterable<ModelDto> getAllModelsByTypeAndColorAndPriceRange(S filterSearch);

    Iterable<ModelDto> findAllModels(S filterSearch);

    ModelDto getModelById(Long modelId);

    ModelEntity createModel(Long productId, T modelPayload);

    void deleteModel(Long modelId);

    Iterable<ModelDto> findAllSorted(String sortBy);
}
