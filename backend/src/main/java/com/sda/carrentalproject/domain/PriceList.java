package com.sda.carrentalproject.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable

public class PriceList {
     @NotNull
     @Size(min = 4)
     long pricePerDayInEuroCents;

}
