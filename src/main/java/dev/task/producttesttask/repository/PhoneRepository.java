package dev.task.producttesttask.repository;

import dev.task.producttesttask.entity.PhoneModelEntity;
import dev.task.producttesttask.entity.ProductType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends CrudRepository<PhoneModelEntity, Long> {
    @Query("SELECT tv FROM PhoneModelEntity tv INNER JOIN tv.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName = '' OR LOWER(tv.name) LIKE LOWER(CONCAT('%', :modelName, '%'))) " +
            "AND (:color = '' OR LOWER(tv.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice = 0 OR tv.price >= :minPrice) " +
            "AND (:maxPrice = -1 OR tv.price <= :maxPrice)")
    Iterable<PhoneModelEntity> findTvModelsByTypeAndColorAndPriceRange(
            @NonNull @Param("type") ProductType type,
            @NonNull @Param("modelName") String modelName,
            @NonNull @Param("color") String color,
            @NonNull @Param("minPrice") Integer minPrice,
            @NonNull @Param("maxPrice") Integer maxPrice
    );


    @Query("SELECT ph FROM PhoneModelEntity ph INNER JOIN ph.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName = '' OR LOWER(ph.name) LIKE LOWER(CONCAT('%', :modelName, '%'))) " +
            "AND (:color = '' OR LOWER(ph.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice = -1 OR ph.price >= :minPrice) " +
            "AND (:maxPrice = -1 OR ph.price <= :maxPrice) " +
            "AND (:cameraCount = -1 OR ph.cameraCount = :cameraCount) " +
            "AND (:memory = -1 OR ph.memory = :memory )")
    Iterable<PhoneModelEntity> findAllModels(
            @NonNull @Param("type") ProductType type,
            @NonNull @Param("modelName") String modelName,
            @NonNull @Param("color") String color,
            @NonNull @Param("minPrice") Integer minPrice,
            @NonNull @Param("maxPrice") Integer maxPrice,
            @NonNull @Param("cameraCount") int cameraCount,
            @NonNull @Param("memory") int memory
    );

    Iterable<PhoneModelEntity> findAllByOrderByNameAsc();

    Iterable<PhoneModelEntity> findAllByOrderByPriceAsc();

    Iterable<PhoneModelEntity> findAllByOrderByNameAscPriceAsc();
}
