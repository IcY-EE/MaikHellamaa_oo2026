package ee.maik.cars.controller;

import ee.maik.cars.entity.Car;
import ee.maik.cars.repository.CarRepository;
import ee.maik.cars.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("autod")
public class CarController {

    private final CarRepository carRepository;
    private final CarService carService;

    @GetMapping
    public List<Car> getAutod() {
        return carRepository.findAll();
    }

    @PostMapping
    public Car addAuto(@RequestBody Car car) {
        carService.validateCar(car);
        return carRepository.save(car);
    }
}