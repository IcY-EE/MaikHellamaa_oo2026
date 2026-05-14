package ee.maik.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin(origins = "*")
public class BibleController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("get-bibles-v2")
    public Object getBibles() {
        String url = "https://holy-bible-api.com/bibles";
        return restTemplate.getForObject(url, Object.class);
    }
}