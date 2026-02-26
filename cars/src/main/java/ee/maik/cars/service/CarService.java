package ee.maik.cars.service;

import ee.maik.cars.entity.Car;
import ee.maik.cars.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public void validateCar(Car car) {
        if (carRepository.findByNumbrimark(car.getNumbrimark()).isPresent()) {
            throw new RuntimeException("See numbrimärk on juba registris!");
        }

        if (car.getMark() == null || car.getMark().isBlank()) {
            throw new RuntimeException("Auto mark peab olema täidetud!");
        }

        if (car.getMudel() == null || car.getMudel().length() < 2) {
            throw new RuntimeException("Auto mudel on liiga lühike või puudu!");
        }

        if (car.getAasta() < 1886) {
            throw new RuntimeException("Auto aastaarv ei saa olla varasem kui 1886!");
        }

        if (car.getHind() <= 0) {
            throw new RuntimeException("Auto hind peab olema positiivne arv!");
        }

        if (car.getNumbrimark() == null || !car.getNumbrimark().matches(".*[0-9].*")) {
            throw new RuntimeException("Numbrimärk peab sisaldama vähemalt ühte numbrit!");
        }
    }
}