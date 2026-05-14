package ee.maik.decathlon.service;

import ee.maik.decathlon.dto.JudgeDto;
import ee.maik.decathlon.dto.LocationDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class MockApiService {

    private final String MOCK_API_URL = "https://6a0614e2c83ba8ad9b3d37d2.mockapi.io/";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<JudgeDto> getJudges() {
        JudgeDto[] judges = restTemplate.getForObject(MOCK_API_URL + "/judges", JudgeDto[].class);
        return Arrays.asList(judges);
    }

    public List<LocationDto> getLocations() {
        LocationDto[] locations = restTemplate.getForObject(MOCK_API_URL + "/locations", LocationDto[].class);
        return Arrays.asList(locations);
    }
}