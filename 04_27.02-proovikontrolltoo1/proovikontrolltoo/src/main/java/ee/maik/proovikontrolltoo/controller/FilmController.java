package ee.maik.proovikontrolltoo.controller;

import ee.maik.proovikontrolltoo.dto.FilmSaveDto;
import ee.maik.proovikontrolltoo.entity.Film;
import ee.maik.proovikontrolltoo.repository.FilmRepository;
import ee.maik.proovikontrolltoo.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;
    private final FilmRepository filmRepository;

    @PostMapping("films")
    public Film saveFilm(@RequestBody FilmSaveDto dto) {
        return filmService.saveFilm(dto);
    }

    @GetMapping("films/price/{id}/{days}")
    public String getPrice(@PathVariable Long id, @PathVariable int days) {
        Film film = filmRepository.findById(id).orElseThrow();
        double price = filmService.calculatePrice(film, days);
        return "Hind: " + price + " EUR";
    }

    @GetMapping("films")
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @DeleteMapping("films/{id}")
    public void deleteFilm(@PathVariable Long id) {
        filmRepository.deleteById(id);
    }
}