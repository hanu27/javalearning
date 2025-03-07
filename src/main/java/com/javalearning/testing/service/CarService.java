package com.javalearning.testing.service;

import com.javalearning.testing.dto.Car;
import com.javalearning.testing.repo.CarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired(required = false)
    private CarRepo carRepo;

    public void save(Car car) {
        carRepo.save(car);
    }

}
