package com.sda.carrentalproject.dto;

import com.sda.carrentalproject.domain.PriceList;
import com.sda.carrentalproject.domain.enumeration.Color;
import lombok.Builder;

import java.time.YearMonth;

@Builder
public record CarDto(Long id,
                     String brand,
                     String model,
                     YearMonth productionYear,
                     Color color,
                     Boolean available,
                     PriceList priceList
                     )  {
}
