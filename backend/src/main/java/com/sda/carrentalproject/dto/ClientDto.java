package com.sda.carrentalproject.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

// TODO: add nulls and not nulls annotations
@Builder
public record ClientDto( Long id,
                         String name,
                         String surname,
                         String phone,
                         String email,
                         String address,
                         boolean hasDrivingLicence,
                         LocalDateTime registrationDateTime,
                         LocalDate dateOfBirth) {

}
