package ee.maik.proovikontrolltoo.service;

import ee.maik.proovikontrolltoo.dto.FilmSaveDto;
import ee.maik.proovikontrolltoo.entity.Film;
import ee.maik.proovikontrolltoo.entity.FilmType;
import ee.maik.proovikontrolltoo.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;

    public Film saveFilm(FilmSaveDto dto) {
        Film film = new Film();
        film.setTitle(dto.title());
        film.setType(dto.type());
        film.setDays(0);
        return filmRepository.save(film);
    }

    public double calculatePrice(Film film, int days) {
        return switch (film.getType()) {
            case NEW -> 4.0 * days;
            case REGULAR -> days <= 3 ? 3.0 : 3.0 + (days - 3) * 3.0;
            case OLD -> days <= 5 ? 3.0 : 3.0 + (days - 5) * 3.0;
        };
    }

    public double calculateLateFee(Film film, int actualDays) {
        int lateDays = actualDays - film.getDays();
        if (lateDays > 0) {
            return lateDays * (film.getType() == FilmType.NEW ? 4.0 : 3.0);
        }
        return 0;
    }
}