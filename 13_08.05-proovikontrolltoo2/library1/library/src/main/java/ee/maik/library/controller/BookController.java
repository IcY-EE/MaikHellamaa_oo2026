package ee.maik.library.controller;

import ee.maik.library.models.BibleBook;
import ee.maik.library.repository.BibleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BibleRepository bibleRepository;

    @GetMapping("get-bibles")
    public Object getBibles() {
        String url = "https://holy-bible-api.com/bibles";
        return restTemplate.getForObject(url, Object.class);
    }

    @GetMapping("get-books")
    public Object getBooks(@RequestParam(defaultValue = "1") String page) {
        String url = "https://api.itbook.store/1.0/search/react?page=" + page;
        return restTemplate.getForObject(url, Object.class);
    }

    @PostMapping("add-books")
    public List<BibleBook> addBooks(@RequestBody List<BibleBook> books) {
        return bibleRepository.saveAll(books);
    }
}