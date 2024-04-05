package dev.task.producttesttask.repository;

import dev.task.producttesttask.entity.ComputerModelEntity;
import dev.task.producttesttask.entity.ProductType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends CrudRepository<ComputerModelEntity, Long> {
    @Query("SELECT tv FROM ComputerModelEntity tv INNER JOIN tv.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName IS NULL OR LOWER(tv.name) LIKE LOWER(CONCAT('%', :modelName, '%'))) " +
            "AND (:color IS NULL OR LOWER(tv.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice IS NULL OR tv.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR tv.price <= :maxPrice)")
    Iterable<ComputerModelEntity> findTvModelsByTypeAndColorAndPriceRange(
            @Nullable @Param("type") ProductType type,
            @Nullable @Param("modelName") String modelName,
            @Nullable @Param("color") String color,
            @Nullable @Param("minPrice") Double minPrice,
            @Nullable @Param("maxPrice") Double maxPrice
    );


    @Query("SELECT tv FROM ComputerModelEntity tv INNER JOIN tv.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName IS NULL OR LOWER(tv.name) LIKE LOWER(CONCAT('%', :modelName, '%'))) " +
            "AND (:color IS NULL OR LOWER(tv.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice IS NULL OR tv.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR tv.price <= :maxPrice) " +
            "AND (:category IS NULL OR LOWER(tv.category) LIKE LOWER(CONCAT('%', :category, '%'))) " +
            "AND (:processorType IS NULL OR LOWER(tv.processorType) LIKE LOWER(CONCAT('%', :processorType, '%')))")
    Iterable<ComputerModelEntity> findAllModels(
            @Nullable @Param("type") ProductType type,
            @Nullable @Param("modelName") String modelName,
            @Nullable @Param("color") String color,
            @Nullable @Param("minPrice") Double minPrice,
            @Nullable @Param("maxPrice") Double maxPrice,
            @Nullable @Param("category") String category,
            @Nullable @Param("processorType") String processorType
    );

    Iterable<ComputerModelEntity> findAllByOrderByNameAsc();

    Iterable<ComputerModelEntity> findAllByOrderByPriceAsc();

    Iterable<ComputerModelEntity> findAllByOrderByNameAscPriceAsc();
}
