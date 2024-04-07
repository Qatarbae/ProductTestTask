package dev.task.producttesttask.repository;

import dev.task.producttesttask.entity.ProductEntity;
import dev.task.producttesttask.entity.ProductType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    Iterable<ProductEntity> findAllByNameLikeIgnoreCase(String filter);

    @Query("SELECT p FROM ProductEntity p WHERE " +
            "(:type IS NULL OR p.type = :type) AND " +
            "(:name = '' OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:manufacturerCountry = '' OR LOWER(p.manufacturerCountry) LIKE LOWER(CONCAT('%', :manufacturerCountry, '%'))) AND " +
            "(:manufacturer = '' OR LOWER(p.manufacturer) LIKE LOWER(CONCAT('%', :manufacturer, '%'))) AND " +
            "(:onlineOrderAvailable IS NULL OR p.onlineOrderAvailable = :onlineOrderAvailable) AND " +
            "(:installmentAvailable IS NUll OR p.installmentAvailable = :installmentAvailable)")
    Iterable<ProductEntity> findAllWithFilters(
            @NonNull @Param("type") ProductType type,
            @NonNull @Param("name") String name,
            @NonNull @Param("manufacturerCountry") String manufacturerCountry,
            @NonNull @Param("manufacturer") String manufacturer,
            @NonNull @Param("onlineOrderAvailable") Boolean onlineOrderAvailable,
            @NonNull @Param("installmentAvailable") Boolean installmentAvailable
    );
}
