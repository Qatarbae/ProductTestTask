package dev.task.producttesttask.repository;

import dev.task.producttesttask.entity.ProductType;
import dev.task.producttesttask.entity.TvModelEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface TvRepository extends CrudRepository<TvModelEntity, Long> {
    @Query("SELECT tv FROM TvModelEntity tv INNER JOIN tv.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName IS NULL OR LOWER(tv.name) LIKE LOWER(CONCAT('%', :modelName, '%'))) " +
            "AND (:color IS NULL OR LOWER(tv.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice IS NULL OR tv.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR tv.price <= :maxPrice)")
    Iterable<TvModelEntity> findTvModelsByTypeAndColorAndPriceRange(
            @Nullable @Param("type") ProductType type,
            @Nullable @Param("modelName") String modelName,
            @Nullable @Param("color") String color,
            @Nullable @Param("minPrice") Double minPrice,
            @Nullable @Param("maxPrice") Double maxPrice
    );


    @Query("SELECT tv FROM TvModelEntity tv INNER JOIN tv.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName IS NULL OR LOWER(tv.name) LIKE LOWER(CONCAT('%', :modelName, '%'))) " +
            "AND (:color IS NULL OR LOWER(tv.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice IS NULL OR tv.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR tv.price <= :maxPrice) " +
            "AND (:category IS NULL OR LOWER(tv.category) LIKE LOWER(CONCAT('%', :category, '%'))) " +
            "AND (:technology IS NULL OR LOWER(tv.technology) LIKE LOWER(CONCAT('%', :technology, '%')))")
    Iterable<TvModelEntity> findAllModels(
            @Nullable @Param("type") ProductType type,
            @Nullable @Param("modelName") String modelName,
            @Nullable @Param("color") String color,
            @Nullable @Param("minPrice") Double minPrice,
            @Nullable @Param("maxPrice") Double maxPrice,
            @Nullable @Param("category") String category,
            @Nullable @Param("technology") String technology
    );

    Iterable<TvModelEntity> findAllByOrderByNameAsc();

    Iterable<TvModelEntity> findAllByOrderByPriceAsc();

    Iterable<TvModelEntity> findAllByOrderByNameAscPriceAsc();
}
