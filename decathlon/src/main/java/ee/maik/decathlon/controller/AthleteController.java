package ee.maik.decathlon.controller;

import ee.maik.decathlon.entity.Athlete;
import ee.maik.decathlon.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class AthleteController {

    @Autowired
    private AthleteRepository athleteRepository;

    @GetMapping("get-athletes-paged")
    public Page<Athlete> getAthletes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "totalPoints") String sort,
            @RequestParam(defaultValue = "desc") String dir,
            @RequestParam(required = false) String country
    ) {
        Sort.Direction direction = dir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));

        Specification<Athlete> spec = (root, query, cb) -> {
            if (country == null || country.trim().isEmpty()) return null;
            return cb.like(cb.lower(root.get("country")), "%" + country.toLowerCase() + "%");
        };

        return athleteRepository.findAll(spec, pageable);
    }

    @PostMapping("add-athlete")
    public ResponseEntity<?> addAthlete(@RequestBody Athlete athlete) {
        if (athlete.getName() == null || athlete.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Viga: Nimi on kohustuslik!");
        }
        athleteRepository.save(athlete);
        return ResponseEntity.ok(athlete);
    }
}