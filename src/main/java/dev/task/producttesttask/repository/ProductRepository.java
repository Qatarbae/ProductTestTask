package dev.task.producttesttask.repository;

import dev.task.producttesttask.entity.ProductEntity;
import dev.task.producttesttask.entity.ProductType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    Iterable<ProductEntity> findAllByNameLikeIgnoreCase(String filter);

    @Query("SELECT p FROM ProductEntity p WHERE " +
            "(:type IS NULL OR p.type = :type) AND " +
            "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:manufacturerCountry IS NULL OR LOWER(p.manufacturerCountry) LIKE LOWER(CONCAT('%', :manufacturerCountry, '%'))) AND " +
            "(:manufacturer IS NULL OR LOWER(p.manufacturer) LIKE LOWER(CONCAT('%', :manufacturer, '%'))) AND " +
            "(:onlineOrderAvailable IS NULL OR p.onlineOrderAvailable = :onlineOrderAvailable) AND " +
            "(:installmentAvailable IS NULL OR p.installmentAvailable = :installmentAvailable)")
    Iterable<ProductEntity> findAllWithFilters(
            @Nullable @Param("type") ProductType type,
            @Nullable @Param("name") String name,
            @Nullable @Param("manufacturerCountry") String manufacturerCountry,
            @Nullable @Param("manufacturer") String manufacturer,
            @Nullable @Param("onlineOrderAvailable") Boolean onlineOrderAvailable,
            @Nullable @Param("installmentAvailable") Boolean installmentAvailable
    );
}
