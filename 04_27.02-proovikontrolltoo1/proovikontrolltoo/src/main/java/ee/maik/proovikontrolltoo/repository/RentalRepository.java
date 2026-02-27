package ee.maik.proovikontrolltoo.repository;

import ee.maik.proovikontrolltoo.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}