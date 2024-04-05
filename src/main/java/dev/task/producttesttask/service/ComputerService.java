package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.NewComputerPayload;
import org.springframework.stereotype.Service;

@Service
public interface ComputerService extends ModelService<NewComputerPayload> {
}
