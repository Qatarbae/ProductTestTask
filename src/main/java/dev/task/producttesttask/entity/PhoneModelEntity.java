package dev.task.producttesttask.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

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
