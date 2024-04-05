package dev.task.producttesttask.repository;

import dev.task.producttesttask.entity.TvModelEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvRepository extends CrudRepository<TvModelEntity, Long> {

}
