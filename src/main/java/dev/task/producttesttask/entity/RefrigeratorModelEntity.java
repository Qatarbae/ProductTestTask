package dev.task.producttesttask.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_refrigerator")
public class RefrigeratorModelEntity {
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
    @Column(name = "count_door", nullable = false)
    private Integer countDoor;
    @Column(name = "compressor_type", nullable = false, length = 50)
    private String compressorType;
    @Column(name = "available", nullable = false)
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
}
