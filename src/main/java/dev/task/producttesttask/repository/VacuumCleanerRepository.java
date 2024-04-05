package dev.task.producttesttask.repository;

import dev.task.producttesttask.entity.VacuumCleanerModelEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacuumCleanerRepository extends CrudRepository<VacuumCleanerModelEntity, Long> {

}
