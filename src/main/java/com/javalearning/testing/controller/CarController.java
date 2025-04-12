package com.javalearning.testing.controller;

import com.javalearning.testing.dto.Car;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/cars/v2")
@Slf4j
@Tag(name = "Car apis using hashmap", description = "here we will get , and insert the car records using hashmap")
public class CarController {

    ConcurrentHashMap<Long, Car> carRecords = new ConcurrentHashMap<>();

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getCars() {
        List<Car> list = carRecords.values().stream().toList();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public Long createCar(@RequestBody Car car){
        AtomicLong id = new AtomicLong(new Random().nextLong(0,10000));
        car.setId(id.get());
        carRecords.put(id.get(),car);
        return id.get();
    }

    @DeleteMapping(value = "/{id}" , produces = MediaType.TEXT_HTML_VALUE )
    @Operation(summary = "Delete the car for the given id")
    public ResponseEntity<?> deleteCar(@PathVariable Long id){
        if(carRecords.containsKey(id)){
            carRecords.remove(id);
        }else {
            return ResponseEntity.internalServerError().body(id + " not found");
        }
        return ResponseEntity.ok("Deleted Successfully");
    }
}
