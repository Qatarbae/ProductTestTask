package dev.task.producttesttask.repository;

import dev.task.producttesttask.entity.PhoneModelEntity;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<PhoneModelEntity, Long> {
}
