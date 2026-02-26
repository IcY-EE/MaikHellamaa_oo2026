package ee.maik.decathlon.controller;

import ee.maik.decathlon.entity.Athlete;
import ee.maik.decathlon.repository.AthleteRepository;
import ee.maik.decathlon.service.CalculationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AthleteController {

    private final AthleteRepository athleteRepository;
    private final CalculationService calculationService;

    @GetMapping("athletes")
    public List<Athlete> getAthletes() {
        return athleteRepository.findAll();
    }

    @PostMapping("athletes")
    public Athlete addAthlete(@RequestBody Athlete athlete) {
        if (athlete.getId() != null) {
            throw new RuntimeException("Uut sportlast ei saa lisada olemasoleva ID-ga!");
        }
        if (athlete.getFirstName() == null || athlete.getLastName() == null) {
            throw new RuntimeException("Sportlase eesnimi ja perekonnanimi on kohustuslikud!");
        }
        return athleteRepository.save(athlete);
    }

    @GetMapping("athletes/{id}/total-points")
    public int getAthleteTotalPoints(@PathVariable Long id) {
        return calculationService.getAthleteTotalPoints(id);
    }
}