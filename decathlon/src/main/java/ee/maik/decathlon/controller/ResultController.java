package ee.maik.decathlon.controller;

import ee.maik.decathlon.entity.Athlete;
import ee.maik.decathlon.entity.Result;
import ee.maik.decathlon.repository.AthleteRepository;
import ee.maik.decathlon.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResultController {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private AthleteRepository athleteRepository;

    @PostMapping("add-result")
    public ResponseEntity<?> addResult(@RequestParam Long athleteId, @RequestBody Result result) {
        Athlete athlete = athleteRepository.findById(athleteId).orElse(null);
        if (athlete == null) {
            return ResponseEntity.badRequest().body("Viga: Sportlast ei leitud!");
        }
        if (result.getDiscipline() == null || result.getDiscipline().isEmpty()) {
            return ResponseEntity.badRequest().body("Viga: Spordiala peab olema määratud!");
        }

        // Back-end arvutab punktid (lihtsustatud näide)
        int points = 0;
        if (result.getDiscipline().equals("100m")) {
            points = (int) (25.4347 * Math.pow(18 - result.getValue(), 1.81));
        } else if (result.getDiscipline().equals("kaugushüpe")) {
            points = (int) (0.14354 * Math.pow(result.getValue() * 100 - 220, 1.4));
        }

        result.setPoints(points);
        result.setAthlete(athlete);
        resultRepository.save(result);
        return ResponseEntity.ok(result);
    }

    @GetMapping("total-points")
    public int getTotalPoints(@RequestParam Long athleteId) {
        List<Result> results = resultRepository.findByAthleteId(athleteId);
        int total = 0;
        for (Result r : results) {
            total += r.getPoints();
        }
        return total;
    }
}