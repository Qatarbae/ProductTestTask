package dev.task.producttesttask.repository;

import dev.task.producttesttask.entity.RefrigeratorModelEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefrigeratorRepository extends CrudRepository<RefrigeratorModelEntity, Long> {
}