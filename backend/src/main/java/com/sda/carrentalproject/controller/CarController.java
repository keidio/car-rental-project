package com.sda.carrentalproject.controller;

import com.sda.carrentalproject.domain.Car;
import com.sda.carrentalproject.dto.CarDto;
import com.sda.carrentalproject.mapper.CarMapper;
import com.sda.carrentalproject.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/api")
@CrossOrigin("${frontend.trusted-url}")
public class CarController {
    private final CarService carService;
    private final CarMapper carMapper;

    private final String allowedOrigin;


    public CarController(CarService carService, CarMapper carMapper, @Value ("${frontend.trusted-url}") String allowedOrigin) {
        this.carService = carService;
        this.carMapper = carMapper;
        this.allowedOrigin = allowedOrigin;

        log.info("Allowed origin: [{}]", allowedOrigin);
    }

    public String getAllowedOrigin() {
        return allowedOrigin;
    }

    // /cars?available=true
    @GetMapping("/cars")
    public Page<CarDto> getAllCars(@RequestParam Map<String, String>queryParams, Pageable pageable){
        log.info("all cars endpoint");
        log.info("query params: {}",queryParams);
        log.info("paging parameters: [{}]", pageable);

        return carService.findCarsBasedOnQueryParameters(queryParams, pageable)
                .map(car -> carMapper.fromEntityToDto(car));
    }

    @PostMapping("/cars")
    public ResponseEntity<CarDto> createNewCar(@RequestBody CarDto carToSave, UriComponentsBuilder ucb) {
        log.info("trying to save new car: [{}]", carToSave);
        Car createdCar = carService.saveNewCar(carMapper.fromDtoToEntity(carToSave));

        URI path = ucb.path("/apu/car/{id}")
                .buildAndExpand(Map.of("id", createdCar.getId()))
                .toUri();

        return ResponseEntity.created(path).body(carMapper.fromEntityToDto(createdCar));
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable Long id){
        log.info("Deleting car by id: [{}]", id);
        carService.deleteCarById(id);
        return ResponseEntity.noContent().build();
    }
}
