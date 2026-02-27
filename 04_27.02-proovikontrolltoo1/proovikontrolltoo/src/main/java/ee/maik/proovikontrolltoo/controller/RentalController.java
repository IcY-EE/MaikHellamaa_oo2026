package ee.maik.proovikontrolltoo.controller;

import ee.maik.proovikontrolltoo.dto.FilmRentalDto;
import ee.maik.proovikontrolltoo.entity.Film;
import ee.maik.proovikontrolltoo.entity.Rental;
import ee.maik.proovikontrolltoo.repository.FilmRepository;
import ee.maik.proovikontrolltoo.repository.RentalRepository;
import ee.maik.proovikontrolltoo.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RentalController {

    private final RentalRepository rentalRepository;
    private final FilmRepository filmRepository;
    private final FilmService filmService;

    @PostMapping("start-rental")
    public Rental startRental(@RequestBody List<FilmRentalDto> dtos) {
        Rental rental = rentalRepository.save(new Rental());
        double sum = 0;

        for (FilmRentalDto dto : dtos) {
            Film film = filmRepository.findById(dto.filmId()).orElseThrow();
            film.setRental(rental);
            film.setDays(dto.days());
            sum += filmService.calculatePrice(film, dto.days());
            filmRepository.save(film);
        }
        rental.setInitialFee(sum);
        return rentalRepository.save(rental);
    }

    @PostMapping("end-rental")
    public Rental endRental(@RequestBody List<FilmRentalDto> dtos) {
        double lateSum = 0;
        Rental rental = null;

        for (FilmRentalDto dto : dtos) {
            Film film = filmRepository.findById(dto.filmId()).orElseThrow();
            rental = film.getRental();
            lateSum += filmService.calculateLateFee(film, dto.days());

            film.setDays(0);
            film.setRental(null);
            filmRepository.save(film);
        }

        if (rental != null) {
            rental.setLateFee(lateSum);
            return rentalRepository.save(rental);
        }
        return new Rental();
    }
}