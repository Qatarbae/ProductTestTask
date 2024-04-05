package dev.task.producttesttask.service;

import dev.task.producttesttask.controller.payload.NewPhonePayload;
import org.springframework.stereotype.Service;

@Service
public interface PhoneService extends ModelService<NewPhonePayload> {
}
