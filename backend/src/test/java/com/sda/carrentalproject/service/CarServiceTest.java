package com.sda.carrentalproject.service;

import com.sda.carrentalproject.domain.Car;
import com.sda.carrentalproject.domain.PriceList;
import com.sda.carrentalproject.domain.enumeration.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarServiceTest {

    @Autowired
    CarService carService;

    @Test
    void checkInjectedService() {
        Assertions.assertNotNull(carService, "Bean should be injected!");
    }

    @Test
    void creatingNewCar(){
        //create new car
        Car car = Car.builder()
                .brand("Audi")
                .model("A5")
                .productionYear(YearMonth.of(2021, 12))
                .color(Color.PINK)
                .available(true)
                .priceList(new PriceList(5000L))
                .build();

        //save it
         var saved = carService.saveCar(car);
        //verify it after saving

        Assertions.assertNotNull(saved.getId(), "Entity should have id!");
        Assertions.assertEquals(car.getBrand(), saved.getBrand());
        Assertions.assertEquals(car.getModel(), saved.getModel());
        Assertions.assertEquals(car.getProductionYear(), saved.getProductionYear());
        Assertions.assertEquals(car.getColor(), saved.getColor());
        Assertions.assertEquals(car.isAvailable(), saved.isAvailable());
        Assertions.assertEquals(car.getPriceList(), saved.getPriceList());

    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllCars() {
    }

    @Test
    void saveCar() {
    }
}