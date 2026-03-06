package ee.maik.esimene_kontrolltoo.repository;

import ee.maik.esimene_kontrolltoo.entity.CalculationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationLogRepository extends JpaRepository<CalculationLog, Long> {
}