package com.example.autogarage.Service;


import com.example.autogarage.exceptions.RecordNotFoundException;
import com.example.autogarage.model.Car;
import com.example.autogarage.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class CarImpl implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Override
    public long createCar(Car car) {
        Car newCar = carRepository.save(car);
        return newCar.getId();
    }

    @Override
    public void updateCar(long id, Car car) {
        if (!carRepository.existsById(id)) throw new RecordNotFoundException();
        Car existingCar = carRepository.findById(id).get();
        existingCar.setCostumerName(car.getCostumerName());
        existingCar.setModel(car.getModel());
        existingCar.setLicensePlate(car.getLicensePlate());
        existingCar.setBrand(car.getBrand());
        carRepository.save(existingCar);
    }

    @Override
    public void partialUpdateCar(long id, Map<String, String> fields) {
        if (!carRepository.existsById(id)) throw new RecordNotFoundException();
        Car car = carRepository.findById(id).get();
        for (String field : fields.keySet()) {
            switch (field.toLowerCase()) {
                case "model":
                    car.setModel((String) fields.get(field));
                    break;
                case "licensePlate":
                    car.setLicensePlate((String) fields.get(field));
                    break;
                case "brand":
                    car.setBrand((String) fields.get(field));
                    break;
            }
        }
        carRepository.save(car);
    }

    @Override
    public void deleteCar(long id) {

    }

    @Override
    public Collection<Car> getCars() {
        return carRepository.findAll();
    }


    @Override
    public Optional<Car> getCarById(long id) {
        if (!carRepository.existsById(id)) throw new RecordNotFoundException();
        return carRepository.findById(id);
    }
}
