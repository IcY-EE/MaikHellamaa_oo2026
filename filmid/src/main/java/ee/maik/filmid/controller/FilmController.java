package ee.maik.filmid.controller;

import ee.maik.filmid.entity.Film;
import ee.maik.filmid.repository.FilmRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// ... ülejäänud kood

@RestController
@RequestMapping("/api/filmid")
@CrossOrigin(origins = "*")
public class FilmController {

    private final FilmRepository repository;

    public FilmController(FilmRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Film> saaKoikFilmid() {
        return repository.findAll();
    }

    @PostMapping
    public Film lisaFilm(@RequestBody Film uusFilm) {
        return repository.save(uusFilm);
    }

    @DeleteMapping("/{id}")
    public void kustutaFilm(@PathVariable Long id) {
        repository.deleteById(id);
    }
}