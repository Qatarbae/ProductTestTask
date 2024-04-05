package dev.task.producttesttask.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "t_tv")
@DiscriminatorValue("Tv")
public class TvModelEntity extends ModelEntity {
    @Column(name = "category", nullable = false, length = 100)
    private String category;
    @Column(name = "technology", nullable = false, length = 100)
    private String technology;

}
