package ee.maik.esimene_kontrolltoo.controller;

import ee.maik.esimene_kontrolltoo.entity.Word;
import ee.maik.esimene_kontrolltoo.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WordController {

    @Autowired
    private WordRepository wordRepository;

    @PostMapping("add-word")
    public ResponseEntity<?> addWord(@RequestBody Word word) {
        if (word.getContent() == null || word.getContent().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Viga: Sõna sisu ei tohi olla tühi!");
        }
        if (word.getContent().length() < 2) {
            return ResponseEntity.badRequest().body("Viga: Sõna peab olema vähemalt 2 tähte pikk!");
        }
        wordRepository.save(word);
        return ResponseEntity.ok(word);
    }

    @GetMapping("get-words")
    public List<Word> getWords() {
        return wordRepository.findAll();
    }

    @PatchMapping("replace-at-index")
    public List<Word> replaceAt(@RequestParam int index) {
        wordRepository.findAll().forEach(w -> {
            if (w.getContent().length() > index) {
                StringBuilder sb = new StringBuilder(w.getContent());
                sb.setCharAt(index, 'a');
                w.setContent(sb.toString());
                wordRepository.save(w);
            }
        });
        return wordRepository.findAll();
    }

    @PatchMapping("replace-limited")
    public List<Word> replaceLimited(@RequestParam int count, @RequestParam int index) {
        List<Word> allWords = wordRepository.findAll();
        int replacedCount = 0;

        for (Word w : allWords) {
            if (replacedCount >= count) break;

            if (w.getContent().length() > index) {
                StringBuilder sb = new StringBuilder(w.getContent());
                sb.setCharAt(index, 'a');
                w.setContent(sb.toString());
                wordRepository.save(w);
                replacedCount++;
            }
        }
        return wordRepository.findAll();
    }
}