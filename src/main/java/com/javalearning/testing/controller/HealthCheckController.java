package com.javalearning.testing.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/healthcheck")
@Tag(name = "Health check of service", description = "exposed the ping end point of the service")
public class HealthCheckController{

    @GetMapping
    @Operation(summary = "Operation to check the health check !!!")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("Service is up!!");
    }
}
