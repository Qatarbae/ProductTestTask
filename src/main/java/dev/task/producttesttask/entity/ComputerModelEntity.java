package dev.task.producttesttask.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_computer", schema = "schema_product")
public class ComputerModelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name = "serial_number", nullable = false, length = 50)
    private String serialNumber;
    @Column(name = "color", nullable = false, length = 30)
    private String color;
    @Column(name = "size", nullable = false, length = 20)
    private String size;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "category", nullable = false, length = 50)
    private String category;
    @Column(name = "processor_type", nullable = false, length = 50)
    private String processorType;
    @Column(name = "available", nullable = false)
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
}
