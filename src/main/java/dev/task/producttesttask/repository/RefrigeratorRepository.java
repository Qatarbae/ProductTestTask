package dev.task.producttesttask.repository;

import dev.task.producttesttask.entity.ProductType;
import dev.task.producttesttask.entity.RefrigeratorModelEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface RefrigeratorRepository extends CrudRepository<RefrigeratorModelEntity, Long> {
    @Query("SELECT tv FROM RefrigeratorModelEntity tv INNER JOIN tv.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName IS NULL OR LOWER(tv.name) LIKE LOWER(CONCAT('%', :modelName, '%'))) " +
            "AND (:color IS NULL OR LOWER(tv.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice IS NULL OR tv.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR tv.price <= :maxPrice)")
    Iterable<RefrigeratorModelEntity> findTvModelsByTypeAndColorAndPriceRange(
            @Nullable @Param("type") ProductType type,
            @Nullable @Param("modelName") String modelName,
            @Nullable @Param("color") String color,
            @Nullable @Param("minPrice") Double minPrice,
            @Nullable @Param("maxPrice") Double maxPrice
    );


    @Query("SELECT tv FROM RefrigeratorModelEntity tv INNER JOIN tv.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName IS NULL OR LOWER(tv.name) LIKE LOWER(CONCAT('%', :modelName, '%'))) " +
            "AND (:color IS NULL OR LOWER(tv.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice IS NULL OR tv.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR tv.price <= :maxPrice) " +
            "AND (:doorsCount IS NULL OR LOWER(tv.doorsCount) LIKE LOWER(CONCAT('%', :doorsCount, '%'))) " +
            "AND (:compressorType IS NULL OR LOWER(tv.compressorType) LIKE LOWER(CONCAT('%', :compressorType, '%')))")
    Iterable<RefrigeratorModelEntity> findAllModels(
            @Nullable @Param("type") ProductType type,
            @Nullable @Param("modelName") String modelName,
            @Nullable @Param("color") String color,
            @Nullable @Param("minPrice") Double minPrice,
            @Nullable @Param("maxPrice") Double maxPrice,
            @Nullable @Param("doorsCount") Double doorsCount,
            @Nullable @Param("compressorType") Double compressorType
    );

    Iterable<RefrigeratorModelEntity> findAllByOrderByNameAsc();

    Iterable<RefrigeratorModelEntity> findAllByOrderByPriceAsc();

    Iterable<RefrigeratorModelEntity> findAllByOrderByNameAscPriceAsc();
}