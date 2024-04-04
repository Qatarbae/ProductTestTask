package dev.task.producttesttask.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_vacuum_cleaner")
public class VacuumCleanerModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "serial_number", nullable = false, length = 50)
    private String serialNumber;
    @Column(name = "color", nullable = false, length = 30)
    private String color;
    @Column(name = "size", nullable = false, length = 20)
    private String size;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "dust_bag_volume", nullable = false)
    private double dustBagVolume;
    @Column(name = "modes", nullable = false)
    private int modes;
    @Column(name = "available", nullable = false)
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
}
