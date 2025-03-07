package com.javalearning.testing.repo;

import com.javalearning.testing.dto.Car;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepo extends CrudRepository<Car,String> {
}
