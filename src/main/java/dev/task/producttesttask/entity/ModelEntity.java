package dev.task.producttesttask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class ModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "serialNumber", nullable = false, length = 100)
    private String serialNumber;

    @Column(name = "color", nullable = false, length = 100)
    private String color;

    @Column(name = "size", nullable = false, length = 100)
    private String size;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "available", nullable = false)
    private boolean available;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ProductType modelType;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    // Геттеры и сеттеры
}
