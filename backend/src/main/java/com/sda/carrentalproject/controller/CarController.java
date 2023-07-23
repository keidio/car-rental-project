package com.sda.carrentalproject.controller;

import com.sda.carrentalproject.domain.Car;
import com.sda.carrentalproject.dto.CarDto;
import com.sda.carrentalproject.mapper.CarMapper;
import com.sda.carrentalproject.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api")
// TODO: make it safe
@CrossOrigin("*")
public class CarController {
    private final CarService carService;
    private final CarMapper carMapper;


    public CarController(CarService carService, CarMapper carMapper, CarMapper carMapper1) {
        this.carService = carService;
        this.carMapper = carMapper1;
    }


    // /cars?available=true
    @GetMapping("/cars")
    public List<CarDto> getAllCars(@RequestParam Map<String, String>queryParams, Pageable pageable){
        log.info("all cars endpoint");
        log.info("query params: {}",queryParams);
        log.info("paging parameters: [{}]", pageable);

        return carService.findCarsBasedOnQueryParameters(queryParams)
                .stream()
                .map(car -> carMapper.fromEntityToDto(car))
                .toList();
    }

    @PostMapping("/cars")
    ResponseEntity<CarDto> createNewCar(@RequestBody CarDto carToSave, UriComponentsBuilder ucb) {
        log.info("trying to save new car: [{}]", carToSave);
        Car createdCar = carService.saveNewCar(carMapper.fromDtoToEntity(carToSave));

        URI path = ucb.path("/apu/car/{id}")
                .buildAndExpand(Map.of("id", createdCar.getId()))
                .toUri();

        return ResponseEntity.created(path).body(carMapper.fromEntityToDto(createdCar));
    }



}
