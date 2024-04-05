package dev.task.producttesttask.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "t_vacuum_cleaner")
@DiscriminatorValue("VacuumCleaner")
public class VacuumCleanerModelEntity extends ModelEntity {

    @Column(name = "dustBagCapacity", nullable = false)
    private double dustBagCapacity;
    @Column(name = "modesCount", nullable = false)
    private int modesCount;

}
