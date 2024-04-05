package dev.task.producttesttask.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_product")
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

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ProductType type;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<TvModelEntity> tvModels;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<VacuumCleanerModelEntity> vacuumCleanerModels;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<RefrigeratorModelEntity> refrigeratorModels;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<PhoneModelEntity> phoneModels;
    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ComputerModelEntity> computerModels;

    public ProductEntity(ProductType type, String name, String manufacturerCountry, String manufacturer, boolean onlineOrderAvailable, boolean installmentAvailable) {
        this.type = type;
        this.name = name;
        this.manufacturerCountry = manufacturerCountry;
        this.manufacturer = manufacturer;
        this.onlineOrderAvailable = onlineOrderAvailable;
        this.installmentAvailable = installmentAvailable;
    }
}