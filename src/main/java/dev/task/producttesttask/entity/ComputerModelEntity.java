package dev.task.producttesttask.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_computer")
@DiscriminatorValue("Tv")
public class ComputerModelEntity extends ModelEntity {
    @Column(name = "category", nullable = false, length = 100)
    private String category;
    @Column(name = "processorType", nullable = false, length = 100)
    private String processorType;

}
