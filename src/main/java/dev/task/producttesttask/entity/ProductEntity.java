package dev.task.producttesttask.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "t_product", schema = "schema_product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "manufacturer_country", nullable = false, length = 30)
    private String manufacturerCountry;
    @Column(name = "manufacturer", nullable = false, length = 30)
    private String manufacturer;
    @Column(name = "onlineOrderAvailable", nullable = false)
    private boolean onlineOrderAvailable;
    @Column(name = "installmentAvailable", nullable = false)
    private boolean installmentAvailable;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<TelevisionModelEntity> televisions;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<VacuumCleanerModelEntity> vacuumCleaners;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<RefrigeratorModelEntity> refrigerators;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<PhoneModelEntity> smartphones;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ComputerModelEntity> computers;
}