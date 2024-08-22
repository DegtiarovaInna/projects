package com.example.contactManager.controller;

import com.example.contactManager.model.Car;
import com.example.contactManager.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car createdCar = carService.createCar(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        Optional<Car> updatedCar = carService.updateCar(id, carDetails);
        return updatedCar.map(car -> ResponseEntity.status(HttpStatus.OK).body(car))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    //Получает ID машины из URL и новые данные из тела запроса.
    //Передает эти данные в сервис CarService для обновления.
    //Если машина найдена и обновлена, возвращает обновленные данные с HTTP-статусом 200 (OK).
    //Если машина не найдена, возвращает HTTP-статус 404 (Not Found).

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        boolean deleted = carService.deleteCar(id);
        return deleted ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
   // Если удаление успешно, возвращает HTTP-статус 204 (No Content).
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> car = carService.getCarById(id);
        return car.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    //@GetMapping("/search")
//    public ResponseEntity<List<Car>> getCarsByEngineerId(@RequestParam Long engineerId) {
//        List<Car> cars = carService.getCarsByEngineerId(engineerId);
//        if (cars.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(cars);
//    }
}
