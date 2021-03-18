package com.example.autogarage.Service;

import com.example.autogarage.model.Car;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface CarService {
    public abstract long createCar(Car car);
    public abstract void updateCar(long id, Car car);
    public abstract void partialUpdateCar(long id, Map<String, String > fields);
    public abstract void deleteCar(long id);
    public abstract Collection<Car> getCars();
    public abstract Optional<Car> getCarById(long id);
}
