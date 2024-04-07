package dev.task.producttesttask.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

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
