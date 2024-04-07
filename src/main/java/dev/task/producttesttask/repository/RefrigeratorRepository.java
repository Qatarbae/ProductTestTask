package dev.task.producttesttask.repository;

import dev.task.producttesttask.entity.ProductType;
import dev.task.producttesttask.entity.RefrigeratorModelEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface RefrigeratorRepository extends CrudRepository<RefrigeratorModelEntity, Long> {
    @Query("SELECT tv FROM RefrigeratorModelEntity tv INNER JOIN tv.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName = '' OR LOWER(tv.name) LIKE LOWER(CONCAT('%', :modelName, '%'))) " +
            "AND (:color = '' OR LOWER(tv.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice = 0 OR tv.price >= :minPrice) " +
            "AND (:maxPrice = -1 OR tv.price <= :maxPrice)")
    Iterable<RefrigeratorModelEntity> findTvModelsByTypeAndColorAndPriceRange(
            @NonNull @Param("type") ProductType type,
            @NonNull @Param("modelName") String modelName,
            @NonNull @Param("color") String color,
            @NonNull @Param("minPrice") Integer minPrice,
            @NonNull @Param("maxPrice") Integer maxPrice
    );


    @Query("SELECT r FROM RefrigeratorModelEntity r INNER JOIN r.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName = '' OR LOWER(r.name) LIKE LOWER(CONCAT('%', :modelName, '%'))) " +
            "AND (:color = '' OR LOWER(r.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice = 0 OR r.price >= :minPrice) " +
            "AND (:maxPrice = -1 OR r.price <= :maxPrice) " +
            "AND (:doorsCount = 0 OR r.doorsCount  = :doorsCount)" +
            "AND (:compressorType = '' OR LOWER(r.compressorType) LIKE LOWER(CONCAT('%', :compressorType, '%')))")
    Iterable<RefrigeratorModelEntity> findAllModels(
            @NonNull @Param("type") ProductType type,
            @NonNull @Param("modelName") String modelName,
            @NonNull @Param("color") String color,
            @NonNull @Param("minPrice") Integer minPrice,
            @NonNull @Param("maxPrice") Integer maxPrice,
            @NonNull @Param("doorsCount") int doorsCount,
            @NonNull @Param("compressorType") String compressorType
    );

    Iterable<RefrigeratorModelEntity> findAllByOrderByNameAsc();

    Iterable<RefrigeratorModelEntity> findAllByOrderByPriceAsc();

    Iterable<RefrigeratorModelEntity> findAllByOrderByNameAscPriceAsc();
}