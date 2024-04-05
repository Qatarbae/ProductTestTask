package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.NewFilterSearch;
import dev.task.producttesttask.controller.payload.NewModelPayload;
import dev.task.producttesttask.entity.DTO.ModelDto;
import dev.task.producttesttask.entity.ModelEntity;
import org.springframework.stereotype.Service;

@Service
public interface ModelService {

    Iterable<ModelDto> getAllModel(Long productId, NewFilterSearch filterSearch);

    ModelDto getModelById(Long modelId);

    ModelEntity createModel(Long productId, NewModelPayload modelPayload);

    void deleteModel(Long modelId);

    Iterable<ModelDto> findAllSorted(String sortBy);
}
