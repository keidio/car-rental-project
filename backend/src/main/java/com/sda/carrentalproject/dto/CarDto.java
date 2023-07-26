package com.sda.carrentalproject.dto;

import com.sda.carrentalproject.domain.enumeration.Color;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.YearMonth;

@Builder
public record CarDto(

        Long id,
        @NotNull
        @Size(min = 3)
        String brand,
        @NotNull
        @Size(min = 2)
        String model,
        YearMonth productionYear,
        @NotNull
        Color color,
        boolean available,

        @NotNull
        @Size(min = 4)
        long pricePerDayInEuroCents
) {
}
