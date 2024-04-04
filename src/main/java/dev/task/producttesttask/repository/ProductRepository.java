package dev.task.producttesttask.repository;

import dev.task.producttesttask.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    Iterable<ProductEntity> findAllByNameLikeIgnoreCase(String filter);
}
