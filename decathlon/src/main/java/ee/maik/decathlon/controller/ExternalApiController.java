package ee.maik.decathlon.controller;

import ee.maik.decathlon.dto.JudgeDto;
import ee.maik.decathlon.dto.LocationDto;
import ee.maik.decathlon.service.MockApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExternalApiController {

    @Autowired
    private MockApiService mockApiService;

    @GetMapping("external-judges")
    public List<JudgeDto> getExternalJudges() {
        return mockApiService.getJudges();
    }

    @GetMapping("external-locations")
    public List<LocationDto> getExternalLocations() {
        return mockApiService.getLocations();
    }
}