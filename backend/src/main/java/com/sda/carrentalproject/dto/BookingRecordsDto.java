package com.sda.carrentalproject.dto;

import lombok.Builder;
import java.time.LocalDate;

@Builder
public record BookingRecordsDto(
        Long id,
        CarDto bookedCar,
        ClientDto client,
        LocalDate startDate,
        LocalDate endDate,
        long fullPriceInEuroCents

) {
}
