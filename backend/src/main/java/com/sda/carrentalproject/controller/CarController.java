package com.sda.carrentalproject.controller;

import com.sda.carrentalproject.dto.CarDto;
import com.sda.carrentalproject.mapper.CarMapper;
import com.sda.carrentalproject.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api")
public class CarController {
    private final CarService carService;
    private final CarMapper carMapper;


    public CarController(CarService carService, CarMapper carMapper, CarMapper carMapper1) {
        this.carService = carService;
        this.carMapper = carMapper1;
    }

    @GetMapping("/cars")
    List<CarDto> getAllCars(){
        log.info("all cars endpoint");
        var cars = carService.getAllCars();

        return cars.stream()
                .map(car -> carMapper.fromEntityToDto(car))
                .toList();

    }

}
