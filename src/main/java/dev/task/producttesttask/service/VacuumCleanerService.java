package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.FilterVacuumCleanerModelSearch;
import dev.task.producttesttask.controller.payload.NewVacuumCleanerModelPayload;
import org.springframework.stereotype.Service;

@Service
public interface VacuumCleanerService extends ModelService<NewVacuumCleanerModelPayload, FilterVacuumCleanerModelSearch> {
}
