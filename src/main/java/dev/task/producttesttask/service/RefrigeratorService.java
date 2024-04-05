package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.FilterRefrigeratorSearch;
import dev.task.producttesttask.controller.payload.NewRefrigeratorPayload;
import org.springframework.stereotype.Service;

@Service
public interface RefrigeratorService extends ModelService<NewRefrigeratorPayload, FilterRefrigeratorSearch> {

}
