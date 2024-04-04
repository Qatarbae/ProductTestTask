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
            "WHERE (:product is null or m.product.id = :product) " +
            "AND (:name is null or lower(m.name) like lower(concat('%', :name, '%'))) " +
            "AND (:color is null or lower(m.color) like lower(concat('%', :color, '%'))) " +
            "AND (:minPrice is null or m.price >= :minPrice) " +
            "AND (:maxPrice is null or m.price <= :maxPrice)")
    List<ModelEntity> findFilteredModels(
            @Param("product") Long product,
            @Param("name") String name,
            @Param("color") String color,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );
}
