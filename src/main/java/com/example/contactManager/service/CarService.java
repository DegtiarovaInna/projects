package com.example.contactManager.service;

import com.example.contactManager.model.Car;
import com.example.contactManager.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Optional<Car> updateCar(Long id, Car carDetails) {
        if (carRepository.existsById(id)) {
            carDetails.setId(id);
            return Optional.of(carRepository.save(carDetails));
        }
        return Optional.empty();
    }
//Проверяет, существует ли машина с указанным ID.
//Если существует, обновляет данные машины и сохраняет их в репозитории.
//Возвращает обновленную машину в Optional.
//Если не существует, возвращает пустой Optional.
    public boolean deleteCar(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }
//Проверяет, существует ли машина с указанным ID.
//Если существует, удаляет её из репозитория и возвращает true.
//Если не существует, возвращает false.
//    public List<Car> getCarsByEngineerId(Long engineerId) {
//        return carRepository.findByEngineerId(engineerId);
//    }
    //Получает список машин, связанных с определенным инженером, из репозитория CarRepository.

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    } //ищет машину по её id в репозитории.
}
