package ee.maik.esimene_kontrolltoo.controller;

import ee.maik.esimene_kontrolltoo.entity.CalculationLog;
import ee.maik.esimene_kontrolltoo.entity.Word;
import ee.maik.esimene_kontrolltoo.repository.CalculationLogRepository;
import ee.maik.esimene_kontrolltoo.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CalculationController {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private CalculationLogRepository logRepository;

    @GetMapping("count-all-a")
    public int countAllA() {
        int totalA = 0;
        for (Word w : wordRepository.findAll()) {
            String s = w.getContent().toLowerCase();
            totalA += s.length() - s.replace("a", "").length();
        }
        logRepository.save(new CalculationLog(null, totalA));
        return totalA;
    }

    @GetMapping("words-with-a-count")
    public int wordsWithA() {
        int count = 0;
        for (Word w : wordRepository.findAll()) {
            if (w.getContent().toLowerCase().contains("a")) {
                count++;
            }
        }
        logRepository.save(new CalculationLog(null, count));
        return count;
    }

    @GetMapping("average-a")
    public double averageA() {
        double totalA = 0;
        double totalChars = 0;
        for (Word w : wordRepository.findAll()) {
            String s = w.getContent().toLowerCase();
            totalChars += s.length();
            totalA += s.length() - s.replace("a", "").length();
        }
        double result = totalChars == 0 ? 0 : totalA / totalChars;
        logRepository.save(new CalculationLog(null, result));
        return result;
    }

    @GetMapping("get-logs")
    public List<CalculationLog> getLogs() {
        return logRepository.findAll();
    }
}