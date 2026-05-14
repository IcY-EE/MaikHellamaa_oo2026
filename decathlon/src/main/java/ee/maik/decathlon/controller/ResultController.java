package ee.maik.decathlon.controller;

import ee.maik.decathlon.entity.Athlete;
import ee.maik.decathlon.entity.Result;
import ee.maik.decathlon.repository.AthleteRepository;
import ee.maik.decathlon.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ResultController {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private AthleteRepository athleteRepository;

    @PostMapping("add-result")
    public ResponseEntity<?> addResult(@RequestParam Long athleteId, @RequestBody Result result) {
        Athlete athlete = athleteRepository.findById(athleteId).orElse(null);
        if (athlete == null) return ResponseEntity.badRequest().body("Viga: Sportlast ei leitud!");

        int points = 0;
        if ("100m".equals(result.getDiscipline())) {
            points = (int) (25.4347 * Math.pow(18 - result.getValue(), 1.81));
        } else if ("kaugushüpe".equals(result.getDiscipline())) {
            points = (int) (0.14354 * Math.pow(result.getValue() * 100 - 220, 1.4));
        }

        result.setPoints(points);
        result.setAthlete(athlete);
        resultRepository.save(result);

        int newTotal = resultRepository.findByAthleteId(athleteId).stream().mapToInt(Result::getPoints).sum();
        athlete.setTotalPoints(newTotal);
        athleteRepository.save(athlete);

        return ResponseEntity.ok(result);
    }
}