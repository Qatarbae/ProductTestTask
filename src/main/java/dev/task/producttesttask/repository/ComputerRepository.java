package dev.task.producttesttask.repository;

import dev.task.producttesttask.entity.ComputerModelEntity;
import dev.task.producttesttask.entity.ProductType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends CrudRepository<ComputerModelEntity, Long> {
    @Query("SELECT tv FROM ComputerModelEntity tv INNER JOIN tv.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName = '' OR LOWER(tv.name) LIKE LOWER(CONCAT('%', :modelName, '%'))) " +
            "AND (:color = '' OR LOWER(tv.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice = 0 OR tv.price >= :minPrice) " +
            "AND (:maxPrice = -1 OR tv.price <= :maxPrice)")
    Iterable<ComputerModelEntity> findTvModelsByTypeAndColorAndPriceRange(
            @NonNull @Param("type") ProductType type,
            @NonNull @Param("modelName") String modelName,
            @NonNull @Param("color") String color,
            @NonNull @Param("minPrice") Integer minPrice,
            @NonNull @Param("maxPrice") Integer maxPrice
    );


    @Query("SELECT c FROM ComputerModelEntity c INNER JOIN c.product p " +
            "ON (:type IS NULL OR p.type = :type) " +
            "WHERE (:modelName = '' OR LOWER(c.name) LIKE LOWER(CONCAT('%', :modelName, '%'))) " +
            "AND (:color = '' OR LOWER(c.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice = 0 OR c.price >= :minPrice) " +
            "AND (:maxPrice = -1 OR c.price <= :maxPrice) " +
            "AND (:category = '' OR LOWER(c.category) LIKE LOWER(CONCAT('%', :category, '%'))) " +
            "AND (:processorType = '' OR LOWER(c.processorType) LIKE LOWER(CONCAT('%', :processorType, '%')))")
    Iterable<ComputerModelEntity> findAllModels(
            @NonNull @Param("type") ProductType type,
            @NonNull @Param("modelName") String modelName,
            @NonNull @Param("color") String color,
            @NonNull @Param("minPrice") Integer minPrice,
            @NonNull @Param("maxPrice") Integer maxPrice,
            @NonNull @Param("category") String category,
            @NonNull @Param("processorType") String processorType
    );

    Iterable<ComputerModelEntity> findAllByOrderByNameAsc();

    Iterable<ComputerModelEntity> findAllByOrderByPriceAsc();

    Iterable<ComputerModelEntity> findAllByOrderByNameAscPriceAsc();
}
