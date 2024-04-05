package dev.task.producttesttask.repository;

import dev.task.producttesttask.entity.PhoneModelEntity;
import dev.task.producttesttask.entity.ProductType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends CrudRepository<PhoneModelEntity, Long> {
    @Query("SELECT tv FROM PhoneModelEntity tv INNER JOIN tv.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName IS NULL OR LOWER(tv.name) LIKE LOWER(CONCAT('%', :modelName, '%'))) " +
            "AND (:color IS NULL OR LOWER(tv.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice IS NULL OR tv.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR tv.price <= :maxPrice)")
    Iterable<PhoneModelEntity> findTvModelsByTypeAndColorAndPriceRange(
            @Nullable @Param("type") ProductType type,
            @Nullable @Param("modelName") String modelName,
            @Nullable @Param("color") String color,
            @Nullable @Param("minPrice") Double minPrice,
            @Nullable @Param("maxPrice") Double maxPrice
    );


    @Query("SELECT tv FROM PhoneModelEntity tv INNER JOIN tv.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName IS NULL OR LOWER(tv.name) LIKE LOWER(CONCAT('%', :modelName, '%'))) " +
            "AND (:color IS NULL OR LOWER(tv.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice IS NULL OR tv.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR tv.price <= :maxPrice) " +
            "AND (:cameraCount IS NULL OR LOWER(tv.cameraCount) LIKE LOWER(CONCAT('%', :cameraCount, '%'))) " +
            "AND (:memory IS NULL OR LOWER(tv.memory) LIKE LOWER(CONCAT('%', :memory, '%')))")
    Iterable<PhoneModelEntity> findAllModels(
            @Nullable @Param("type") ProductType type,
            @Nullable @Param("modelName") String modelName,
            @Nullable @Param("color") String color,
            @Nullable @Param("minPrice") Double minPrice,
            @Nullable @Param("maxPrice") Double maxPrice,
            @Nullable @Param("cameraCount") Double cameraCount,
            @Nullable @Param("memory") Double memory
    );

    Iterable<PhoneModelEntity> findAllByOrderByNameAsc();

    Iterable<PhoneModelEntity> findAllByOrderByPriceAsc();

    Iterable<PhoneModelEntity> findAllByOrderByNameAscPriceAsc();
}
