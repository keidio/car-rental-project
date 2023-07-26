package com.sda.carrentalproject.domain;


import com.sda.carrentalproject.domain.enumeration.Color;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "CARS")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(nullable = false)
    private String brand;

    @NotNull
    @Size(min = 2)
    @Column(nullable = false)
    private String model;

    private YearMonth productionYear;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Color color;

    private boolean available;

    @Column(nullable = false)
    private PriceList priceList;

}
