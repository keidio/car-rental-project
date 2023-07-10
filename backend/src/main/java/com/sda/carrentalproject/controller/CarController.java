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


    public CarController(CarService carService, CarMapper carMapper) {
        this.carService = carService;
    }




}
