package dev.task.producttesttask.repository;

import dev.task.producttesttask.entity.ProductType;
import dev.task.producttesttask.entity.VacuumCleanerModelEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface VacuumCleanerRepository extends CrudRepository<VacuumCleanerModelEntity, Long> {
    @Query("SELECT tv FROM VacuumCleanerModelEntity tv INNER JOIN tv.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName = '' OR LOWER(tv.name) LIKE LOWER(CONCAT('%', :modelName, '%'))) " +
            "AND (:color = '' OR LOWER(tv.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice = 0 OR tv.price >= :minPrice) " +
            "AND (:maxPrice = -1 OR tv.price <= :maxPrice)")
    Iterable<VacuumCleanerModelEntity> findTvModelsByTypeAndColorAndPriceRange(
            @NonNull @Param("type") ProductType type,
            @NonNull @Param("modelName") String modelName,
            @NonNull @Param("color") String color,
            @NonNull @Param("minPrice") Integer minPrice,
            @NonNull @Param("maxPrice") Integer maxPrice
    );


    @Query("SELECT vc FROM VacuumCleanerModelEntity vc INNER JOIN vc.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName = '' OR LOWER(vc.name) LIKE LOWER(CONCAT('%', :modelName, '%'))) " +
            "AND (:color = '' OR LOWER(vc.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice = 0 OR vc.price >= :minPrice) " +
            "AND (:maxPrice = -1 OR vc.price <= :maxPrice) " +
            "AND (:dustBagCapacity = 0 OR vc.dustBagCapacity = :dustBagCapacity) " +
            "AND (:modesCount = 0 OR vc.modesCount = :modesCount)")
    Iterable<VacuumCleanerModelEntity> findAllModels(
            @NonNull @Param("type") ProductType type,
            @NonNull @Param("modelName") String modelName,
            @NonNull @Param("color") String color,
            @NonNull @Param("minPrice") Integer minPrice,
            @NonNull @Param("maxPrice") Integer maxPrice,
            @NonNull @Param("dustBagCapacity") Double dustBagCapacity,
            @NonNull @Param("modesCount") int modesCount
    );

    Iterable<VacuumCleanerModelEntity> findAllByOrderByNameAsc();

    Iterable<VacuumCleanerModelEntity> findAllByOrderByPriceAsc();

    Iterable<VacuumCleanerModelEntity> findAllByOrderByNameAscPriceAsc();
}
