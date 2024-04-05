package dev.task.producttesttask.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "t_refrigerator")
@DiscriminatorValue("Refrigerator")
public class RefrigeratorModelEntity extends ModelEntity {

    @Column(name = "technology", nullable = false)
    private int doorsCount;
    @Column(name = "compressorType", nullable = false, length = 100)
    private String compressorType;
}
