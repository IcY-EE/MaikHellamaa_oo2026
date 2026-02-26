package ee.maik.decathlon.service;

import ee.maik.decathlon.dto.ResultAddDto;
import ee.maik.decathlon.entity.Athlete;
import ee.maik.decathlon.entity.Result;
import ee.maik.decathlon.repository.AthleteRepository;
import ee.maik.decathlon.repository.ResultRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CalculationService {

    private final AthleteRepository athleteRepository;
    private final ResultRepository resultRepository;

    public Result addResultAndCalculatePoints(ResultAddDto dto) {
        Athlete athlete = athleteRepository.findById(dto.athleteId())
                .orElseThrow(() -> new RuntimeException("Sportlast ei leitud ID-ga: " + dto.athleteId()));

        int points = calculatePoints(dto.event(), dto.performance());

        Result result = new Result();
        result.setAthlete(athlete);
        result.setEvent(dto.event().toUpperCase());
        result.setPerformance(dto.performance());
        result.setPoints(points);

        return resultRepository.save(result);
    }

    public int getAthleteTotalPoints(Long athleteId) {
        if (!athleteRepository.existsById(athleteId)) {
            throw new RuntimeException("Sportlast ei leitud!");
        }

        List<Result> results = resultRepository.findByAthleteId(athleteId);
        int totalSum = 0;
        for (Result r : results) {
            totalSum += r.getPoints();
        }
        return totalSum;
    }

    private int calculatePoints(String event, double performance) {
        return switch (event.toUpperCase()) {
            case "100M" -> (int) (25.4347 * Math.pow(18 - performance, 1.81));
            case "LONG_JUMP" -> (int) (0.14354 * Math.pow((performance * 100) - 220, 1.4));
            case "SHOT_PUT" -> (int) (51.39 * Math.pow(performance - 1.5, 1.05));
            default -> throw new RuntimeException("Tundmatu spordiala: " + event);
        };
    }
}