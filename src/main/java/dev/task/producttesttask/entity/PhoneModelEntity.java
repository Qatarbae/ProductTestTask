package dev.task.producttesttask.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "t_phone")
@DiscriminatorValue("Phone")
public class PhoneModelEntity extends ModelEntity {
    @Column(name = "memory", nullable = false)
    private int memory;
    @Column(name = "cameraCount", nullable = false)
    private int cameraCount;
}
