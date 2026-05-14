package ee.maik.library.repository;

import ee.maik.library.models.BibleBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BibleRepository extends JpaRepository<BibleBook, Long> {
}