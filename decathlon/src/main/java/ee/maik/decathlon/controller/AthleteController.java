package ee.maik.decathlon.controller;

import ee.maik.decathlon.entity.Athlete;
import ee.maik.decathlon.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AthleteController {

    @Autowired
    private AthleteRepository athleteRepository;

    @GetMapping("get-athletes")
    public List<Athlete> getAthletes() {
        return athleteRepository.findAll();
    }

    @PostMapping("add-athlete")
    public ResponseEntity<?> addAthlete(@RequestBody Athlete athlete) {
        if (athlete.getName() == null || athlete.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Viga: Sportlase nimi ei tohi olla tühi!");
        }
        athleteRepository.save(athlete);
        return ResponseEntity.ok(athlete);
    }

    @DeleteMapping("athletes/{id}")
    public ResponseEntity<?> deleteAthlete(@PathVariable Long id) {
        athleteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}