package ee.maik.decathlon.controller;

import ee.maik.decathlon.entity.Athlete;
import ee.maik.decathlon.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AthleteController {

    @Autowired
    private AthleteRepository athleteRepository;

    @PostMapping("add-athlete")
    public ResponseEntity<?> addAthlete(@RequestBody Athlete athlete) {
        if (athlete.getName() == null || athlete.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Viga: Sportlase nimi ei tohi olla tühi!");
        }
        athleteRepository.save(athlete);
        return ResponseEntity.ok(athlete);
    }
}