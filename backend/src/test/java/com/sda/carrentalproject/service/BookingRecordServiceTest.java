package com.sda.carrentalproject.service;

import com.sda.carrentalproject.domain.Car;
import com.sda.carrentalproject.domain.PriceList;
import com.sda.carrentalproject.dto.CarBookingRequestDto;
import com.sda.carrentalproject.exception.PeriodCalculationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingRecordServiceTest {

    @Autowired
    private BookingRecordService service;

    @Test
    void calculateBookingPrice() {
        //given
        CarBookingRequestDto bookingRequestFOrTwoDays = new CarBookingRequestDto(0L, 0L, LocalDate.now(), LocalDate.now().plusDays(2));
        Car carToBook = Car.builder()
                .priceList(new PriceList(15_000))
                .build();

        long calculatedPriceInEuroCents = service.calculateBookingPrice(bookingRequestFOrTwoDays, carToBook);

        //when
        long expectedPriceInEuroCents = 2*15_000;
        //then
        Assertions.assertEquals(expectedPriceInEuroCents, calculatedPriceInEuroCents);
    }

    @Test
    void calculateBookingPriceWithNegativePeriod(){
        //given
        CarBookingRequestDto bookingRequestFOrTwoDays = new CarBookingRequestDto(0L, 0L, LocalDate.now(), LocalDate.now().minusDays(2));
        Car carToBook = Car.builder()
                .priceList(new PriceList(15_000))
                .build();

        //TODO:Check the message
        //then
        Assertions.assertThrows(PeriodCalculationException.class, () -> service.calculateBookingPrice(bookingRequestFOrTwoDays, carToBook));
    }
}