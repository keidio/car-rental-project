package com.sda.carrentalproject.configuration.data;

import com.sda.carrentalproject.domain.Car;
import com.sda.carrentalproject.domain.PriceList;
import com.sda.carrentalproject.domain.enumeration.Color;
import com.sda.carrentalproject.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.YearMonth;

@Component
@Slf4j
@Profile("develop")
public class CarDataInitializer implements CommandLineRunner {

    private final CarRepository carRepository;

    public CarDataInitializer(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("creating some cars");
         Car car1Available = Car.builder()
                 .brand("Skoda")
                 .model("Octavia")
                 .productionYear(YearMonth.of(2021, 12))
                 .color(Color.SILVER)
                 .available(true)
                 .priceList(new PriceList(50_000L))
                 .build();

        Car car2Available = Car.builder()
                .brand("Skoda")
                .model("Superb")
                .productionYear(YearMonth.of(2006, 9))
                .color(Color.BLACK)
                .available(true)
                .priceList(new PriceList(59_000L))
                .build();

        Car car32Unavailable = Car.builder()
                .brand("Ford")
                .model("Focus")
                .productionYear(YearMonth.of(2022, 5))
                .color(Color.BLUE)
                .available(false)
                .priceList(new PriceList(10_000L))
                .build();

         carRepository.save(car1Available);
         carRepository.save(car2Available);
         carRepository.save(car32Unavailable);

    }
}
