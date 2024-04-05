package dev.task.producttesttask.repository;

import dev.task.producttesttask.entity.ComputerModelEntity;
import org.springframework.data.repository.CrudRepository;

public interface ComputerRepository extends CrudRepository<ComputerModelEntity, Long> {
}
