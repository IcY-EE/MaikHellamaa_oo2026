package ee.maik.decathlon.controller;

import ee.maik.decathlon.dto.ResultAddDto;
import ee.maik.decathlon.entity.Result;
import ee.maik.decathlon.repository.ResultRepository;
import ee.maik.decathlon.service.CalculationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ResultController {

    private final ResultRepository resultRepository;
    private final CalculationService calculationService;

    @GetMapping("results")
    public List<Result> getResults() {
        return resultRepository.findAll();
    }

    @PostMapping("results")
    public Result addResult(@RequestBody ResultAddDto dto) {
        if (dto.performance() <= 0) {
            throw new RuntimeException("Tulemus peab olema suurem nullist!");
        }
        return calculationService.addResultAndCalculatePoints(dto);
    }
}