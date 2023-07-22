package com.sda.carrentalproject.service;

import com.sda.carrentalproject.domain.Car;
<<<<<<< HEAD
import com.sda.carrentalproject.exception.WrongCarIdException;
=======
>>>>>>> feature/new-car
import com.sda.carrentalproject.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
@Service
@Slf4j
public class CarService {
=======
import java.util.List;

@Service
@Slf4j
public class CarService {

>>>>>>> feature/new-car
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

<<<<<<< HEAD
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

=======
    public List<Car> getAllCars() {
        log.info("getting all clients from repository");
        var result = carRepository.findAll();
        log.info("found [{}] cars", result.size());

        log.debug("results: {}", result);

        return result;
    }

    public Car saveCar(Car carEntity) {
        log.info("creating new car: [{}]", carEntity);

        var result = carRepository.save(carEntity);
        log.info("saved car: [{}]", result);

        return result;
>>>>>>> feature/new-car
    }
}
