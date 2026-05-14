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

    @GetMapping("total-points")
    public int getTotalPoints(@RequestParam Long athleteId) {
        return resultRepository.findByAthleteId(athleteId)
                .stream()
                .mapToInt(Result::getPoints)
                .sum();
    }

    // Siia võid hiljem lisada ka add-result meetodi
}