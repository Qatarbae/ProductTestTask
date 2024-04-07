package dev.task.producttesttask.repository;

import dev.task.producttesttask.entity.ProductType;
import dev.task.producttesttask.entity.TvModelEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface TvRepository extends CrudRepository<TvModelEntity, Long> {
    @Query("SELECT tv FROM TvModelEntity tv INNER JOIN tv.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName = '' OR tv.name LIKE CONCAT('%', :modelName, '%')) " +
            "AND (:color = '' OR tv.color LIKE CONCAT('%', :color, '%')) " +
            "AND (:minPrice = -1 OR tv.price >= :minPrice) " +
            "AND (:maxPrice =  0 OR tv.price <= :maxPrice)")
    Iterable<TvModelEntity> findTvModelsByTypeAndColorAndPriceRange(
            @NonNull @Param("type") ProductType type,
            @NonNull @Param("modelName") String modelName,
            @NonNull @Param("color") String color,
            @NonNull @Param("minPrice") Integer minPrice,
            @NonNull @Param("maxPrice") Integer maxPrice
    );


    @Query("SELECT tv FROM TvModelEntity tv INNER JOIN tv.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName = '' OR LOWER(tv.name) LIKE LOWER(CONCAT('%', :modelName, '%'))) " +
            "AND (:color = '' OR LOWER(tv.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice = 0 OR tv.price >= :minPrice) " +
            "AND (:maxPrice = -1 OR tv.price <= :maxPrice) " +
            "AND (:category = '' OR LOWER(tv.category) LIKE LOWER(CONCAT('%', :category, '%'))) " +
            "AND (:technology = '' OR LOWER(tv.technology) LIKE LOWER(CONCAT('%', :technology, '%')))")
    Iterable<TvModelEntity> findAllModels(
            @NonNull @Param("type") ProductType type,
            @NonNull @Param("modelName") String modelName,
            @NonNull @Param("color") String color,
            @NonNull @Param("minPrice") Integer minPrice,
            @NonNull @Param("maxPrice") Integer maxPrice,
            @NonNull @Param("category") String category,
            @NonNull @Param("technology") String technology
    );

    Iterable<TvModelEntity> findAllByOrderByNameAsc();

    Iterable<TvModelEntity> findAllByOrderByPriceAsc();

    Iterable<TvModelEntity> findAllByOrderByNameAscPriceAsc();
}
