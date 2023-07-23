package com.sda.carrentalproject.service;

import com.sda.carrentalproject.domain.Car;
import com.sda.carrentalproject.exception.WrongCarIdException;
import com.sda.carrentalproject.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class CarService {

    private static final String availableKey = "available";

    private static final String colorKey = "color";

    private static final String brandKey = "brand";

    private static final String modelKey = "model";

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Page<Car> getAllCars(Pageable pageable) {
        log.info("trying to find all cars");
        var carResultPage = carRepository.findAll(pageable);
        log.info("number of all car: [{}]", carResultPage.getNumberOfElements());

        log.debug("all cars on current page: {}", carResultPage.getContent());

        return carResultPage;
    }

    public Car saveNewCar(Car carEntity) {
        log.info("creating new car: [{}]", carEntity);

        var result = carRepository.save(carEntity);
        log.info("saved car: [{}]", result);

        return result;
    }

    public Car findCarById(long id){
        log.info("Trying to find a car with id [{}]", id);
        return carRepository.findById(id)
                .map(car -> {
                    log.info("Found car: [{}]", car);
                    return car;
                })
                .orElseThrow(() ->  new WrongCarIdException("No car with given in: [%s]".formatted(id)));
    }

    public Car saveCar(Car carToBook) {
        log.info("Trying to save new car: [{}]", carToBook);

        return carRepository.save(carToBook);
    }

    public Car findAvailableCarById(long id) {
        log.info("Trying to find available car with given id [{}]", id);

        return carRepository.findByIdAndAvailableTrue(id)
                .map(car -> {
                    log.info("Found available car: [{}]", car);
                    return car;
                })
                .orElseThrow(() -> new WrongCarIdException("Car with given id: [%s] is unavailable".formatted(id)));

    }

    public Page<Car> findALlCarsAvailableForBooking(Pageable pageable){
        log.info("trying to find all cars available for booking");
        var availableCars = carRepository.findAllByAvailable(true, pageable);

        log.info("number of available cars: [{}]", availableCars.getNumberOfElements());
        log.debug("available cars: {}", availableCars.getContent());

        return availableCars;
    }

    public Page<Car> findRentedCars(Pageable pageable){
        log.info("trying to find rented cars");
        var rentedCars = carRepository.findAllByAvailable(false, pageable);

        log.info("number of rented cars: [{}]", rentedCars.getNumberOfElements());
        log.debug("rented cars: [{}}", rentedCars.getContent());

        return rentedCars;
    }
    public Page<Car> findCarsBasedOnQueryParameters(Map<String, String> queryParams, Pageable pageable) {
        log.info("finding cars based on query parameters: [{}]", queryParams);
        log.info("paging params: [{}]", pageable);

        Page<Car> result;
        if(!queryParams.containsKey(availableKey)){
            result = getAllCars(pageable);
        } else {
            boolean available = Boolean.parseBoolean(queryParams.get(availableKey));
            if(available) {
                result = findALlCarsAvailableForBooking(pageable);
            } else {
                result = findRentedCars(pageable);
            }
        }
         return result;

    }
}