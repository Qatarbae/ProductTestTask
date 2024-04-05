package dev.task.producttesttask.repository;

import dev.task.producttesttask.entity.ProductType;
import dev.task.producttesttask.entity.VacuumCleanerModelEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface VacuumCleanerRepository extends CrudRepository<VacuumCleanerModelEntity, Long> {
    @Query("SELECT tv FROM VacuumCleanerModelEntity tv INNER JOIN tv.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName IS NULL OR LOWER(tv.name) LIKE LOWER(CONCAT('%', :modelName, '%'))) " +
            "AND (:color IS NULL OR LOWER(tv.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice IS NULL OR tv.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR tv.price <= :maxPrice)")
    Iterable<VacuumCleanerModelEntity> findTvModelsByTypeAndColorAndPriceRange(
            @Nullable @Param("type") ProductType type,
            @Nullable @Param("modelName") String modelName,
            @Nullable @Param("color") String color,
            @Nullable @Param("minPrice") Double minPrice,
            @Nullable @Param("maxPrice") Double maxPrice
    );


    @Query("SELECT tv FROM VacuumCleanerModelEntity tv INNER JOIN tv.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName IS NULL OR LOWER(tv.name) LIKE LOWER(CONCAT('%', :modelName, '%'))) " +
            "AND (:color IS NULL OR LOWER(tv.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice IS NULL OR tv.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR tv.price <= :maxPrice) " +
            "AND (:dustBagCapacity IS NULL OR LOWER(tv.dustBagCapacity) LIKE LOWER(CONCAT('%', :dustBagCapacity, '%'))) " +
            "AND (:modesCount IS NULL OR LOWER(tv.modesCount) LIKE LOWER(CONCAT('%', :modesCount, '%')))")
    Iterable<VacuumCleanerModelEntity> findAllModels(
            @Nullable @Param("type") ProductType type,
            @Nullable @Param("modelName") String modelName,
            @Nullable @Param("color") String color,
            @Nullable @Param("minPrice") Double minPrice,
            @Nullable @Param("maxPrice") Double maxPrice,
            @Nullable @Param("dustBagCapacity") Double dustBagCapacity,
            @Nullable @Param("modesCount") int modesCount
    );

    Iterable<VacuumCleanerModelEntity> findAllByOrderByNameAsc();

    Iterable<VacuumCleanerModelEntity> findAllByOrderByPriceAsc();

    Iterable<VacuumCleanerModelEntity> findAllByOrderByNameAscPriceAsc();
}
