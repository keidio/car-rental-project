package com.sda.carrentalproject.repository;

import com.sda.carrentalproject.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByIdAndAvailableTrue(Long aLong);
=======


public interface CarRepository extends JpaRepository<Car, Long> {
>>>>>>> feature/new-car
}
