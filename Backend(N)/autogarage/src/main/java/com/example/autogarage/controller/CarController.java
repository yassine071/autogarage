package com.example.autogarage.controller;

import com.example.autogarage.Service.CarService;
import com.example.autogarage.model.Action;
import com.example.autogarage.model.Car;
import com.example.autogarage.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping(value="/car")

// Enkel monteur en admin kunnen gegevens; inzien, maken, updaten en verwijderen
@PreAuthorize("hasAnyRole('ADMIN','MONTEUR')")

public class CarController {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    @GetMapping(value = "")
    public ResponseEntity<Collection<Car>>
    getCar() {
        return ResponseEntity.ok().body(carService.getCars());
    }


    @PostMapping(value = "")
    public ResponseEntity<URI> createCar(@RequestBody Car car) {
        long newId = carService.createCar(car);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable("id") long id, @RequestBody Car car) {
        carService.updateCar(id, car);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateCarPartial(@PathVariable("id") long id, @RequestBody Map<String, String> fields) {
        carService.partialUpdateCar(id,fields);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable("id") long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
