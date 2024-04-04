package dev.task.producttesttask.repository;

import dev.task.producttesttask.entity.ModelEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelEntityRepository extends CrudRepository<ModelEntity, Long> {

    @Query("SELECT m FROM ModelEntity m " +
            "WHERE (:product IS NULL OR m.product.id = :product) " +
            "AND (:name IS NULL OR LOWER(m.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:color IS NULL OR LOWER(m.color) LIKE LOWER(CONCAT('%', :color, '%'))) " +
            "AND (:minPrice IS NULL OR m.price >= :minPrice) " +
            "AND (:maxPrice IS NULL OR m.price <= :maxPrice)")
    List<ModelEntity> findFilteredModels(
            @Param("product") Long product,
            @Param("name") String name,
            @Param("color") String color,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );

    @Query("SELECT m FROM ModelEntity m ORDER BY m.name ASC, m.price ASC")
    List<ModelEntity> findAllSortedByNameAndPrice();

    @Query("SELECT m FROM ModelEntity m ORDER BY m.name ASC")
    List<ModelEntity> findAllSortedByName();

    @Query("SELECT m FROM ModelEntity m ORDER BY m.price ASC")
    List<ModelEntity> findAllSortedByPrice();
}
