package dev.task.producttesttask.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

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
