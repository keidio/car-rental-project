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
         Car car = Car.builder()
                 .brand("Skoda")
                 .model("Octavia")
                 .productionYear(YearMonth.of(2021, 12))
                 .color(Color.SILVER)
                 .available(true)
                 .priceList(new PriceList(5000L))
                 .build();

         carRepository.save(car);

    }
}
