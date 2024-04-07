package dev.task.producttesttask.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

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
