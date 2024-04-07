package dev.task.producttesttask.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

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
